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

@Service
public class QuizService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private QuestionDTO currentQuestion;

    public QuestionDTO getQuizData(String category, double value) {
        Criteria matchCategory = Criteria.where(category).exists(true);

        Criteria rangeCriteria = Criteria.where(category)
                .gte(value * 0.5)
                .lte(value * 1.5);

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(matchCategory),
                Aggregation.match(rangeCriteria),
                Aggregation.sample(1)
        );

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "alcohol_db", Document.class);
        List<Document> documents = results.getMappedResults();

        if (!documents.isEmpty()) {
            Document doc = documents.get(0);

            currentQuestion = new QuestionDTO(doc.getString("name"));
            return currentQuestion;
        }

        return new QuestionDTO("Keine Daten verfügbar");
    }

    public QuestionDTO getCurrentQuestion() {
        return currentQuestion;
    }
}
