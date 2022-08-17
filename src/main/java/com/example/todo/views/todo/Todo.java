package com.example.todo.views.todo;

import com.example.todo.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;

@Route(value = "todo", layout = MainLayout.class)
@PageTitle("todo")
public class Todo extends VerticalLayout {
    ReminderForm reminderForm;
    public Todo() {
        addClassName("todo-view");
        setSizeFull();
        configureForm();
        add(configureForm());
    }

    private ReminderForm configureForm() {
        reminderForm = new ReminderForm(Collections.emptyList(), Collections.emptyList());
        reminderForm.setWidth("25em");
        return reminderForm;
    }
}
