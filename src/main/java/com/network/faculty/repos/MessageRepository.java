package com.network.faculty.repos;

import com.network.faculty.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getMessagesBySenderId(Long senderId);

    @Query(value = "SELECT * FROM Message WHERE MATCH (`text`) AGAINST (?1)", nativeQuery = true)
    List<Message> search(String messagePart);
}
