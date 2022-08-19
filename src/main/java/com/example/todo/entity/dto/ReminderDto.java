package com.example.todo.entity.dto;

import com.example.todo.entity.Priority;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReminderDto implements Serializable {
    private final Long id;
    private final String title;
    private final String notes;
    private final String url;
    private final boolean isFlag;
    private final String image;
    private final Priority priority;
    private final LocalDate date;
    private final LocalTime time;
    private final String tagId;
    private final String listId;
}
