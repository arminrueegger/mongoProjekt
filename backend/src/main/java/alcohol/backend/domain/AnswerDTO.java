package alcohol.backend.domain;

public class AnswerDTO {
    String name;
    int worth;

    public AnswerDTO(String name, int worth) {
        this.name = name;
        this.worth = worth;
    }

    public String getName() {
        return name;
    }
}
