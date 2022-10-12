package jlab.utils;

import jlab.model.ExamQuestionDetails;

import java.util.ArrayList;
import java.util.List;

public class ExamUtils {

    public static List<String> formatData(List<ExamQuestionDetails> ques){
        String q="";
        String opt1="";
        String opt2="";
        String opt3="";
        String opt4="";
        String ans="";
        List<String> al =new ArrayList<>();
        for(int i=3;i<ques.size();i++){
            if(ques.get(i).getQuestion()!=null){
                q=ques.get(i).getQuestion();
                al.add(q);
            }
            if(ques.get(i).getOption1()!=null){
                opt1=ques.get(i).getOption1();
                al.add(opt1);
            }
            if(ques.get(i).getOption2()!=null){
                opt2=ques.get(i).getOption2();
                al.add(opt2);
            }
            if(ques.get(i).getOption3()!=null){
                opt3=ques.get(i).getOption3();
                al.add(opt3);
            }
            if(ques.get(i).getOption4()!=null){
                opt4=ques.get(i).getOption4();
                al.add(opt4);
            }
            if(ques.get(i).getAnswer()!=null){
                ans=ques.get(i).getAnswer();
                al.add(ans);
            }
        }
        return al;
    }

    public static List<List<String> >mapToPojo(List<String> data){
        List<String> strings = data.subList(0, 6);
        List<String> strings1 = data.subList(6, 12);
        List<String> strings2 = data.subList(12, 18);
        List<String> strings3 = data.subList(18, 24);
        List<String> strings4 = data.subList(24, 30);
        List<String> strings5 = data.subList(30, 36);
        List<String> strings6 = data.subList(36, 42);
        List<String> strings7 = data.subList(42, data.size());
        List<List<String>> ll = new ArrayList<>();
        ll.add(strings);
        ll.add(strings1);
        ll.add(strings2);
        ll.add(strings3);
        ll.add(strings4);
        ll.add(strings5);
        ll.add(strings6);
        ll.add(strings7);
        return ll;
    }

    public static List<ExamQuestionDetails> pojoData(List<List<String>> data){
        List<ExamQuestionDetails> res = new ArrayList<>();
        for(int i=0;i<data.size();i++){
            ExamQuestionDetails questionDetails =  new ExamQuestionDetails();
            questionDetails.setQuestion(data.get(i).get(0));
            questionDetails.setOption1(data.get(i).get(1));
            questionDetails.setOption2(data.get(i).get(2));
            questionDetails.setOption3(data.get(i).get(3));
            questionDetails.setOption4(data.get(i).get(4));
            questionDetails.setAnswer(data.get(i).get(5));
            res.add(questionDetails);
        }

        return res;
    }

}
