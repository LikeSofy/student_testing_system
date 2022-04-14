package sample.entity.test;

import com.google.gson.annotations.Expose;

import java.util.Arrays;

public class Question implements Cloneable{
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

    public int lenth(){
        return answers.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (textQuestion != null ? !textQuestion.equals(question.textQuestion) : question.textQuestion != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        int result = textQuestion != null ? textQuestion.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(answers);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Answer[] answersCopy = new Answer[answers.length];
        for (int i = 0; i < answers.length; i++){
            answersCopy[i] = (Answer) answers[i].clone();
        }
        return new Question(textQuestion, answersCopy);
    }

    @Override
    public String toString() {
        return "Question{" +
                "textQuestion='" + textQuestion + '\'' +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }
}
