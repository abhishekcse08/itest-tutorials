package jlab.service;

import jlab.model.ExamQuestionData;
import jlab.model.ExamQuestionDetails;
import jlab.utils.ExamUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService{
    
    /*@Autowired
    RestTemplate restTemplate;*/

    private static String url = "https://www.examsbook.com/indian-gk-questions-and-answers-in-english-for-competitive-exam";
    
    @Override
    public ExamQuestionData callExamService(int pageNo) {
        RestTemplate restTemplate = new RestTemplate();
        String htmlResponse="";
        if(pageNo==0) {
             htmlResponse = restTemplate.getForObject(url, String.class);
        }
        else{
            htmlResponse = restTemplate.getForObject(url+"/"+pageNo, String.class);
        }

        Document doc = Jsoup.parse(htmlResponse);
        Elements elements = doc.select("strong,div,p");
        int count=0;
        ExamQuestionData examQuestionData = new ExamQuestionData();
        List<ExamQuestionDetails> qList = new ArrayList<>();
        for(Element el : elements){

                if(!el.ownText().isEmpty()) {

                    ExamQuestionDetails questionDetails = new ExamQuestionDetails();
                    System.out.println(el.ownText());

                    if (el.ownText().startsWith("Q.")) {
                        if(el.ownText()!=null) {
                            String strPattern = "^.{3}";
                            questionDetails.setQuestion(el.ownText().replaceAll(strPattern,""));
                        }
                    }
                    if (el.ownText().startsWith("7")) {
                        if(el.ownText()!=null)
                            questionDetails.setQuestion(el.ownText().replace("7.",""));
                    }
                    if (el.ownText().startsWith("(A)")) {
                        if(el.ownText()!=null)
                        questionDetails.setOption1(el.ownText().replace("(A)",""));
                    }
                    if (el.ownText().startsWith("(B)")) {
                        if(el.ownText()!=null)
                        questionDetails.setOption2(el.ownText().replace("(B)",""));
                    }
                    if (el.ownText().startsWith("(C)")) {
                        if(el.ownText()!=null)
                        questionDetails.setOption3(el.ownText().replace("(C)",""));
                    }
                    if (el.ownText().startsWith("(D)")) {
                        if(el.ownText()!=null)
                        questionDetails.setOption4(el.ownText().replace("(D)",""));
                    }
                    if (el.ownText().startsWith("Ans")) {
                        if(el.ownText()!=null)
                        questionDetails.setAnswer(el.ownText().replace("Ans .",""));
                    }
                    qList.add(questionDetails);
                }
               // if(questionDetails!=null && questionDetails.getQuestion()!=null)


              //  examQuestionData.setQuestions(qList);
        }
        List<String> strings = ExamUtils.formatData(qList);
        List<List<String>> lists = ExamUtils.mapToPojo(strings);
        List<ExamQuestionDetails> examQuestionDetails = ExamUtils.pojoData(lists);
       /* for (String s:strings){
            System.out.println(s);
        }
*/
        examQuestionData.setQuestions(examQuestionDetails);
        return examQuestionData;
    }



}
