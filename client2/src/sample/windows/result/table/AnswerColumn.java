package sample.windows.result.table;

import sample.entity.test.Question;

public class AnswerColumn {
    private Question question;
    private boolean  rightAnswers;

    public AnswerColumn(Question question, boolean rightAnswers) {
        this.question = question;
        this.rightAnswers = rightAnswers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(boolean rightAnswers) {
        this.rightAnswers = rightAnswers;
    }
}
