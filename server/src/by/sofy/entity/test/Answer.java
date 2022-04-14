package by.sofy.entity.test;

import com.google.gson.annotations.Expose;

public class Answer {
    @Expose
    private String text = "";
    @Expose
    private boolean isTrue = false;

    public Answer() {
    }

    public Answer(String text, boolean isTrue) {
        this.text = text;
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
    public String toString() {
        return "Answer{" +
                "answer='" + text + '\'' +
                ", isTrue=" + isTrue +
                '}';
    }
}
