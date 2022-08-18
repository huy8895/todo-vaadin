package com.example.todo.views.todo;

import com.example.todo.entity.Reminder;
import com.example.todo.repository.CatelogRepository;
import com.example.todo.repository.TagRepository;
import com.example.todo.service.ReminderService;
import com.example.todo.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "todo", layout = MainLayout.class)
@PageTitle("todo")
public class Todo extends VerticalLayout {
    ReminderForm reminderForm;
    private final TagRepository tagRepository;
    private final CatelogRepository catelogRepository;

    private final ReminderService reminderService;
    public Todo(TagRepository tagRepository, CatelogRepository catelogRepository, ReminderService reminderService) {
        this.tagRepository = tagRepository;
        this.catelogRepository = catelogRepository;
        this.reminderService = reminderService;
        addClassName("todo-view");
        setSizeFull();
        configureForm();
        add(configureForm());
    }

    private ReminderForm configureForm() {
        reminderForm = new ReminderForm(tagRepository.findAll(), catelogRepository.findAll());
        reminderForm.setWidth("25em");
        reminderForm.addListener(ReminderForm.SaveEvent.class, event -> this.saveReminder(event));
        return reminderForm;
    }

    private void saveReminder(ReminderForm.SaveEvent event) {
        final var reminder = event.getReminder();
        reminderService.save(reminder);
    }
}
