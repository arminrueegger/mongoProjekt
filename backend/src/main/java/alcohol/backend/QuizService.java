package alcohol.backend;

import alcohol.backend.domain.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.bson.Document;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private QuestionDTO currentQuestion;

    public List<QuestionDTO> getQuizData(String category) {
        try {
            String collectionName = "alcohols";
            
            Aggregation randomAggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where(category).exists(true)),
                Aggregation.sample(1)
            );
            
            Document randomDoc = mongoTemplate.aggregate(randomAggregation, collectionName, Document.class)
                .getUniqueMappedResult();
                
            if (randomDoc == null) {
                return new ArrayList<>();
            }

            double referenceValue;
            Object value = randomDoc.get(category);
            if (value instanceof Integer) {
                referenceValue = ((Integer) value).doubleValue();
            } else if (value instanceof Double) {
                referenceValue = (Double) value;
            } else {
                throw new IllegalArgumentException("Unexpected value type for category: " + category);
            }
            
            Criteria rangeCriteria = Criteria.where(category)
                .gte(referenceValue * 0.5)
                .lte(referenceValue * 1.5);

            Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(rangeCriteria),
                Aggregation.sample(3)
            );

            AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, collectionName, Document.class);
            List<Document> documents = results.getMappedResults();

            List<QuestionDTO> questions = documents.stream()
                .map(doc -> new QuestionDTO(doc.getString("name")))
                .collect(Collectors.toList());

            if (!questions.isEmpty()) {
                setCurrentQuestion(questions.get(0));
            }

            return questions;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void setCurrentQuestion(QuestionDTO question) {
        this.currentQuestion = question;
    }

    public QuestionDTO getCurrentQuestion() {
        return currentQuestion;
    }
}
