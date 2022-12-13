package com.network.faculty.service;

import com.network.faculty.entities.Message;
import com.network.faculty.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MessageDetailsService {
    @Autowired
    protected MessageRepository repo;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public boolean saveMessage(Long userId, Message message, MultipartFile file){
        message.setSender(userDetailsService.getUserById(userId));
        try{
            message.setAttachment(!file.isEmpty() ? file.getBytes() : null);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        repo.save(message);
        return true;
    }

    public List<Message> getMessages(){
        return repo.findAll();
    }

    public List<Message> getMessagesBySender(Long senderId){
        return repo.getMessagesBySenderId(senderId);
    }

    public List<Message> searchMessages(String pattern){
        return repo.search(pattern);
    }
}
