package alcohol.backend;

import alcohol.backend.domain.QuestionDTO;
import alcohol.backend.domain.AnswerDTO;
import alcohol.backend.domain.ResultsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/question/{category}")
    public List<QuestionDTO> quiz(@PathVariable String category) {
        return quizService.getQuizData(category.toLowerCase());
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