package com.example.todo.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "todo", layout = MainLayout.class)
@PageTitle("todo")
public class Todo extends VerticalLayout {

    public Todo() {
        VerticalLayout todosList = new VerticalLayout();
        TextField taskField = new TextField();
//        taskField.getStyle().set("")
        Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            Checkbox checkbox = new Checkbox(taskField.getValue());
            todosList.add(checkbox);
            taskField.clear();
        });
        addButton.addClickShortcut(Key.ENTER);

        final var header = new H1("Vaadin Todo");
        header.getStyle().set("margin", "0px");
        add(
                header,
                new HorizontalLayout(
                        taskField,
                        addButton
                ),
                todosList

                );
    }
}
