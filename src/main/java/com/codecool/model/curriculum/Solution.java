package com.codecool.model.curriculum;

import java.io.Serializable;
import java.util.Objects;

public class Solution implements Serializable {
   private String title;
    private String answer;
    private String question;

    public Solution(String title, String question, String answer) {
        this.title = title;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Objects.equals(title, solution.title) &&
            Objects.equals(answer, solution.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, answer);
    }

    public String getTitle() {
        return title;
    }

    public String getAnswer() {
        return answer;
    }
}
