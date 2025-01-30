package alcohol.backend.domain;

public class AnswerDTO {
    private String name;
    private int worth;

    public AnswerDTO(String name, int worth) {
        this.name = name;
        this.worth = worth;
    }

    public AnswerDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorth() {
        return worth;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }
}
