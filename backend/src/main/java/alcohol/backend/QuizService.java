package alcohol.backend;

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

    public List<Document> getQuizData(String category, double value) {
        Criteria matchCategory = Criteria.where(category).exists(true);

        Criteria rangeCriteria = Criteria.where(category)
                .gte(value * 0.5)
                .lte(value * 1.5);

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(matchCategory),
                Aggregation.match(rangeCriteria),
                Aggregation.sample(3)
        );

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "your_collection_name", Document.class);

        return results.getMappedResults();
    }
}

