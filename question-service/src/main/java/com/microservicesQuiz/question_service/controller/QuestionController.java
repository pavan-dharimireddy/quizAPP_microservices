package com.microservicesQuiz.question_service.controller;


import com.microservicesQuiz.question_service.model.Question;
import com.microservicesQuiz.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<?> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{value}")
    public List<Question> getQuestionsbyCategory(@PathVariable String value){
        return questionService.getQuestionsbyCategory(value);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
            return questionService.addQuestion(question);
    }

    //create or generate questions
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam int numQ){
        return questionService.getQuestionsForQuiz(categoryName,numQ);
    }


    //get the question based on question id for quiz id

    //get score
}
