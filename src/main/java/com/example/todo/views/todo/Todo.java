package com.example.todo.views.todo;

import com.example.todo.repository.CatelogRepository;
import com.example.todo.repository.TagRepository;
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
    public Todo(TagRepository tagRepository, CatelogRepository catelogRepository) {
        this.tagRepository = tagRepository;
        this.catelogRepository = catelogRepository;
        addClassName("todo-view");
        setSizeFull();
        configureForm();
        add(configureForm());
    }

    private ReminderForm configureForm() {
        reminderForm = new ReminderForm(tagRepository.findAll(), catelogRepository.findAll());
        reminderForm.setWidth("25em");
        return reminderForm;
    }
}
