package com.network.faculty.entities;

import lombok.Data;

import javax.persistence.*;

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
}
