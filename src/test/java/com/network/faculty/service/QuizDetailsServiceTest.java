package com.network.faculty.service;

import com.network.faculty.entities.Message;
import com.network.faculty.entities.Quiz;
import com.network.faculty.repos.QuizRepository;
import com.network.faculty.repos.RoleRepository;
import com.network.faculty.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class QuizDetailsServiceTest {

    private final QuizRepository quizRepository = Mockito.mock(QuizRepository.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final RoleRepository roleRepository = Mockito.mock(RoleRepository.class);

    private QuizDetailsService quizDetailsService;

    @BeforeEach
    void setQuizDetailsService(){
        RoleDetailsService roleDetailsService = new RoleDetailsService(roleRepository);
        CustomUserDetailsService userDetailsService = new CustomUserDetailsService(userRepository, roleDetailsService);
        quizDetailsService = new QuizDetailsService(quizRepository, userDetailsService);
    }

    @Test
    void saveQuiz() {
        Quiz quiz = new Quiz();
        quiz.setSender(userRepository.getUserByEmail("test@gmail.com"));
        quiz.setQuestion("test question");
        boolean success = quizDetailsService.saveQuiz(4L, quiz);
        assertTrue(success);
    }
}