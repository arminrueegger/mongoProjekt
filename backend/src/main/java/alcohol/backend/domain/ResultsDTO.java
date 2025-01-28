package alcohol.backend.domain;

public class ResultsDTO {
    Boolean wasRight;
    AnswerDTO result;

    public ResultsDTO(Boolean wasRight, AnswerDTO result){
        this.wasRight = wasRight;
        this.result = result;
    }
}
