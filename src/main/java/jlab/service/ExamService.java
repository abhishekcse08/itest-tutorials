package jlab.service;

import jlab.model.ExamQuestionData;
import org.springframework.stereotype.Service;


public interface ExamService {

    public ExamQuestionData callExamService(int pageNo);
}
