package jlab.model;

import java.util.List;


public class ExamQuestionData {

    private List<ExamQuestionDetails> questions;

    public ExamQuestionData() {
    }

    public ExamQuestionData(List<ExamQuestionDetails> questions) {
        this.questions = questions;
    }

    public List<ExamQuestionDetails> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ExamQuestionDetails> questions) {
        this.questions = questions;
    }

}
