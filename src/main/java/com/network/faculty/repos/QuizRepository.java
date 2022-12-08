package com.network.faculty.repos;

import com.network.faculty.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> getQuizzesBySenderId(Long senderId);
}
