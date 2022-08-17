package com.example.todo.service.impl;

import com.example.todo.entity.Reminder;
import com.example.todo.repository.ReminderRepository;
import com.example.todo.service.ReminderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ReminderServiceImpl implements ReminderService {
    private final ReminderRepository repository;

    @Override
    public Reminder save(Reminder reminder) {
        return repository.save(reminder);
    }

    @Override
    public List<Reminder> list() {
        return repository.findAll();
    }
}
