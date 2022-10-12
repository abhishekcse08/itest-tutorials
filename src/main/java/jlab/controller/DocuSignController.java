package jlab.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import jlab.model.Employee;
import jlab.model.ExamQuestionData;
import jlab.service.EmployeeService;
import jlab.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DocuSignController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ExamService examService;

    @CrossOrigin("*")
    @GetMapping("/getDoc")
    public String getDoc(){

        return "hello";
    }


    @CrossOrigin("*")
    @GetMapping("/export-employees")
    public void exportCSV(HttpServletResponse response) throws Exception {
        String filename = "employees.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

        StatefulBeanToCsv<Employee> writer =
                new StatefulBeanToCsvBuilder
                        <Employee>(response.getWriter())
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).
                        withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                        .withOrderedResults(false).build();

        writer.write(employeeService.fetchAll());

    }

    @CrossOrigin("*")
    @GetMapping("/exam-service")
    public ExamQuestionData getQuestions(@RequestParam (name = "page") int page){
        return examService.callExamService(page);
    }

}
