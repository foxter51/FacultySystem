package com.network.faculty.repos;

import com.network.faculty.entities.Quiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class QuizRepositoryTest {
    @Autowired
    QuizRepository repo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void testCreateQuiz(){
        Quiz quiz = new Quiz();
        quiz.setSender(userRepo.getUserByEmail("test@gmail.com"));
        quiz.setQuestion("test question");
        Quiz savedQuiz = repo.save(quiz);
        Quiz existQuiz = entityManager.find(Quiz.class, savedQuiz.getId());
        assertThat(existQuiz.getId()).isEqualTo(savedQuiz.getId());
    }

    @Test
    void testGetQuizzesBySenderId(){
        List<Quiz> foundQuizzes = repo.getQuizzesBySenderId(1L);
        List<Quiz> expectedQuizzes = List.of(repo.getById(1L));
        assertThat(foundQuizzes).isEqualTo(expectedQuizzes);
    }
}
