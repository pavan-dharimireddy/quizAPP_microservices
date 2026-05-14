package com.microservicesQuiz.question_service.repo;


import com.microservicesQuiz.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question,Integer> {


    List<Question> findByCategory(String value);

    @Query(value = "Select q.id from question q where q.category=:category ORDER BY RANDOM() Limit :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}
