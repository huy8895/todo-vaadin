package com.example.todo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "reminder",
        indexes = {
                @Index(columnList = "list_id"),
                @Index(columnList = "tag_id")
        }
)
public class Reminder {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String notes;
    private String url;
    private boolean isFlag;
    private String image;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate date;

    private LocalTime time;

    @Column(name = "tag_id")
    private String tagId;

    @Column(name = "list_id")
    private String listId;
}
