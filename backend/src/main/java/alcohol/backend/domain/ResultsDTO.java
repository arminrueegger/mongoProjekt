package alcohol.backend.domain;

public class ResultsDTO {
    private Boolean wasRight;
    private AnswerDTO result;

    public ResultsDTO(Boolean wasRight, AnswerDTO result){
        this.wasRight = wasRight;
        this.result = result;
    }

    public ResultsDTO() {}

    public Boolean getWasRight() {
        return wasRight;
    }

    public void setWasRight(Boolean wasRight) {
        this.wasRight = wasRight;
    }

    public AnswerDTO getResult() {
        return result;
    }

    public void setResult(AnswerDTO result) {
        this.result = result;
    }
}
