package com.example.todo.service;

import com.example.todo.entity.Reminder;

import java.util.List;

public interface ReminderService {
    Reminder save(Reminder reminder);
    List<Reminder> list();
}
