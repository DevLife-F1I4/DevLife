package com.example.devlife.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "upgrade")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Upgrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upgrade_id", updatable = false)
    private Long id;

    @Column(name = "comment_cnt", nullable = false)
    private int commentCnt;

    @Column(name = "board_cnt", nullable = false)
    private int boardCnt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
