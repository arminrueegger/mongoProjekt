package alcohol.backend;

import alcohol.backend.domain.QuestionDTO;
import alcohol.backend.domain.AnswerDTO;
import alcohol.backend.domain.ResultsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    /**
     * GET endpoint to send the question name only.
     */
    @GetMapping("/question")
    public QuestionDTO quiz() {
        String category = "prozentanteil"; // Example category
        double value = 4.6;               // Example value
        return quizService.getQuizData(category, value);
    }

    /**
     * POST endpoint to check the answer and return the result.
     */
    @PostMapping("/check-answer")
    public ResultsDTO checkAnswer(@RequestBody AnswerDTO answer) {
        QuestionDTO currentQuestion = quizService.getCurrentQuestion();

        if (currentQuestion == null) {
            return new ResultsDTO(false, null); // No current question available
        }

        // Check if the provided answer matches the expected answer
        boolean isCorrect = currentQuestion.name.equals(answer.getName());

        // Create the correct answer for reference (this is just an example)
        AnswerDTO correctAnswer = new AnswerDTO(currentQuestion.name, 5);

        return new ResultsDTO(isCorrect, correctAnswer);
    }
}
