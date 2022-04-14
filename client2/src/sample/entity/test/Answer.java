package sample.entity.test;

import com.google.gson.annotations.Expose;

public class Answer implements Cloneable{
    @Expose
    private String text = "";
    @Expose
    private boolean isTrue = false;

    public Answer() {
    }

    public Answer(String answer, boolean isTrue) {
        this.text = answer;
        this.isTrue = isTrue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (isTrue != answer.isTrue) return false;
        return text != null ? text.equals(answer.text) : answer.text == null;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (isTrue ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + text + '\'' +
                ", isTrue=" + isTrue +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Answer(text, isTrue);
    }
}
