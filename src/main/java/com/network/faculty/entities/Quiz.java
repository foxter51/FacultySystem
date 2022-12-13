package com.network.faculty.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender", orphanRemoval = true)
    List<Message> answers;
}
