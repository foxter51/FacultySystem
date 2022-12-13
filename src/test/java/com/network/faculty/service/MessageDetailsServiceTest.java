package com.network.faculty.service;

import com.network.faculty.entities.Message;
import com.network.faculty.repos.MessageRepository;
import com.network.faculty.repos.RoleRepository;
import com.network.faculty.repos.UserRepository;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.everyItem;
import static org.junit.jupiter.api.Assertions.*;

class MessageDetailsServiceTest {

    private final MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final RoleRepository roleRepository = Mockito.mock(RoleRepository.class);

    private MessageDetailsService messageDetailsService;

    @BeforeEach
    void setMessageDetailsService(){
        RoleDetailsService roleDetailsService = new RoleDetailsService(roleRepository);
        CustomUserDetailsService userDetailsService = new CustomUserDetailsService(userRepository, roleDetailsService);
        messageDetailsService = new MessageDetailsService(messageRepository, userDetailsService);
    }

    @Test
    void saveMessage() {
        Message message = new Message();
        message.setSender(userRepository.getUserByEmail("test@gmail.com"));
        message.setText("Test mesage Test mesage Test mesage Test mesage Test mesage");
        byte[] randBytes = new byte[128];
        Random random = new Random();
        random.nextBytes(randBytes);
        MultipartFile file = new MockMultipartFile("test", randBytes);
        boolean success = messageDetailsService.saveMessage(4L, message, file);
        assertTrue(success);
    }

    @Test
    void searchMessages() {
        String toSearch = "test";
        List<Message> foundMessages = messageDetailsService.searchMessages(toSearch);
        List<String> messageTexts = foundMessages.stream().map(Message::getText).collect(Collectors.toList());
        MatcherAssert.assertThat(messageTexts, everyItem(containsStringIgnoringCase(toSearch)));
    }
}