package alcohol.backend;

import alcohol.backend.domain.QuestionDTO;
import alcohol.backend.domain.AnswerDTO;
import alcohol.backend.domain.ResultsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.domain.Sort;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/question/{category}")
    public List<QuestionDTO> quiz(@PathVariable String category) {
        List<QuestionDTO> questions = quizService.getQuizData(category.toLowerCase());
        
        // Find the question with the highest/lowest value based on category
        if (!questions.isEmpty()) {
            String collectionName = "alcohols";
            Document highestDoc = null;
            
            if (category.equalsIgnoreCase("erstellungsjahr")) {
                // For Erstellungsjahr, we want the oldest (lowest) year
                highestDoc = mongoTemplate.aggregate(
                    Aggregation.newAggregation(
                        Aggregation.match(Criteria.where("name").in(
                            questions.stream().map(QuestionDTO::getName).collect(Collectors.toList())
                        )),
                        Aggregation.sort(Sort.Direction.ASC, category),
                        Aggregation.limit(1)
                    ),
                    collectionName,
                    Document.class
                ).getUniqueMappedResult();
            } else {
                // For other categories, we want the highest value
                highestDoc = mongoTemplate.aggregate(
                    Aggregation.newAggregation(
                        Aggregation.match(Criteria.where("name").in(
                            questions.stream().map(QuestionDTO::getName).collect(Collectors.toList())
                        )),
                        Aggregation.sort(Sort.Direction.DESC, category),
                        Aggregation.limit(1)
                    ),
                    collectionName,
                    Document.class
                ).getUniqueMappedResult();
            }
            
            if (highestDoc != null) {
                quizService.setCurrentQuestion(new QuestionDTO(highestDoc.getString("name")));
            }
        }
        
        return questions;
    }

    @PostMapping("/check-answer")
    public ResultsDTO checkAnswer(@RequestBody AnswerDTO answer) {
        QuestionDTO currentQuestion = quizService.getCurrentQuestion();

        if (currentQuestion == null) {
            return new ResultsDTO(false, null);
        }

        boolean isCorrect = currentQuestion.getName().equalsIgnoreCase(answer.getName());
        AnswerDTO correctAnswer = new AnswerDTO(currentQuestion.getName(), 5);

        return new ResultsDTO(isCorrect, correctAnswer);
    }
}