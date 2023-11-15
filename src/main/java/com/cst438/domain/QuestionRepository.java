package com.cst438.domain;

import com.cst438.domain.Question;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	@Query(value = "SELECT * FROM Questions ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Question> findRandomQuestion();
	
//	@Override
//	@Query(value = "SELECT * FROM Questions", nativeQuery = true)
//    List<Question> findAll();
}
