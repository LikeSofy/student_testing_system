package by.sofy.entity.test;

import com.google.gson.annotations.Expose;

import java.util.Arrays;

public class Question {
    @Expose
    private String textQuestion = "";
    @Expose
    private Answer[] answers = new Answer[5];

    {
        for (int i = 0; i < answers.length; i++){
            answers[i] = new Answer();
        }
    }

    public Question() {
    }

    public Question(String textQuestion, Answer[] answers) {
        this.textQuestion = textQuestion;
        this.answers = answers;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public Answer findAnswer(int i) {
        return answers[i];
    }

    public void setAnswer(int i, Answer answer) {
        this.answers[i] = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "textQuestion='" + textQuestion + '\'' +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }
}
