package com.network.faculty;

import com.network.faculty.entities.Message;
import com.network.faculty.repos.MessageRepository;
import com.network.faculty.repos.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class MessageRepositoryTest {
    @Autowired
    MessageRepository repo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void testCreateMessage(){
        Message message = new Message();
        message.setSender(userRepo.getUserByEmail("test@gmail.com"));
        message.setText("Test mesage Test mesage Test mesage Test mesage Test mesage");
        byte[] randBytes = new byte[128];
        Random random = new Random();
        random.nextBytes(randBytes);
        message.setAttachment(randBytes);
        Message savedMessage = repo.save(message);
        Message existMessage = entityManager.find(Message.class, savedMessage.getId());
        assertThat(existMessage.getId()).isEqualTo(message.getId());
    }

    @Test
    void testGetMessagesBySenderId(){
        List<Message> expectedMessages = userRepo.getUserByEmail("test@gmail.com").getMessages();
        List<Message> foundMessages = repo.getMessagesBySenderId(1L);
        assertArrayEquals(expectedMessages.toArray(), foundMessages.toArray());
    }

    @Test
    void testFullTextSearch(){
        String toSearch = "test";
        List<Message> foundMessages = repo.search(toSearch);
        List<String> messageTexts = foundMessages.stream().map(Message::getText).collect(Collectors.toList());
        assertThat(messageTexts, everyItem(containsStringIgnoringCase(toSearch)));
    }
}
