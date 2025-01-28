package alcohol.backend.domain;

import java.util.List;

public class QuestionDTO {
    public String name;

    public QuestionDTO(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
