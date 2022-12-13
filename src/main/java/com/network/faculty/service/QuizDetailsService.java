package com.network.faculty.service;

import com.network.faculty.entities.Message;
import com.network.faculty.entities.Quiz;
import com.network.faculty.repos.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizDetailsService {
    @Autowired
    protected QuizRepository repo;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public boolean saveQuiz(Long teacherId, Quiz quiz){
        quiz.setSender(userDetailsService.getUserById(teacherId));
        repo.save(quiz);
        return true;
    }

    public List<Quiz> getQuizzes(){
        return repo.findAll();
    }

    public List<Quiz> getQuizzesByTeacherId(Long teacherId){
        return repo.getQuizzesBySenderId(teacherId);
    }

    public boolean addQuizAnswer(Long quizId, Message answer){
        Quiz quiz = repo.getReferenceById(quizId);
        quiz.addAnswer(answer);
        repo.save(quiz);
        return true;
    }
}
