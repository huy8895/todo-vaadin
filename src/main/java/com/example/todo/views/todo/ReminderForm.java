package com.example.todo.views.todo;

import com.example.todo.entity.Catelog;
import com.example.todo.entity.Priority;
import com.example.todo.entity.Tag;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;

import java.util.List;

public class ReminderForm extends FormLayout {
    TextField title = new TextField("Title");
    TextField notes = new TextField("Notes");
    EmailField url = new EmailField("URL");

    DatePicker datePicker = new DatePicker();

    TimePicker timePicker = new TimePicker();


    ComboBox<Tag> tags = new ComboBox<>("Tags");
    ComboBox<Catelog> list = new ComboBox<>("List");
    ComboBox<Priority> priority = new ComboBox<>("Priority");

    Button done = new Button("Done");
    Button cancel = new Button("Cancel");

    public ReminderForm(List<Tag> tagList, List<Catelog> catelogs) {
        addClassName("reminder-form");
        list.setItems(catelogs);
        list.setItemLabelGenerator(Catelog::getName);
        tags.setItems(tagList);
        tags.setItemLabelGenerator(Tag::getName);

        priority.setItems(Priority.values());
        priority.setItemLabelGenerator(Priority::name);
        priority.setRequired(true);

        datePicker.setLabel("Date and time");
        timePicker.setLabel("time");
        add(title,
            notes,
            url,
            datePicker,
            timePicker,
            tags,
            priority,
            this.createButtonsLayout()
            );
    }

    private Component createButtonsLayout() {
        this.done.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        this.done.addClickShortcut(Key.ENTER);
        this.cancel.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(done, cancel);
    }
}
