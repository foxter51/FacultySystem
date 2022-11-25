package com.network.faculty.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Lob
    private byte[] attachment;
}
