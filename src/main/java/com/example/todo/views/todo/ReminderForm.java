package com.example.todo.views.todo;

import com.example.todo.entity.Catelog;
import com.example.todo.entity.Priority;
import com.example.todo.entity.Reminder;
import com.example.todo.entity.Tag;
import com.example.todo.entity.dto.ReminderDto;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class ReminderForm extends FormLayout {
    private Reminder reminder = new Reminder();
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
    Binder<Reminder> binder = new Binder<>(Reminder.class);

    public ReminderForm(List<Tag> tagList, List<Catelog> catelogs) {
        addClassName("reminder-form");
        binder.forField(title)
              .bind(Reminder::getTitle, Reminder::setTitle);

        binder.bind(notes, Reminder::getNotes, Reminder::setNotes);

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

        this.done.addClickListener(event -> validateAndSave());
        this.cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));
        return new HorizontalLayout(done, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(reminder);
            fireEvent(new SaveEvent(this, reminder));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setReminder(Reminder reminder){
        this.reminder = reminder;
        binder.readBean(reminder);
    }

    public static abstract class ReminderFormEvent extends ComponentEvent<ReminderForm> {
        private final Reminder reminder;

        public ReminderFormEvent(ReminderForm source, Reminder reminder) {
            super(source, false);
            this.reminder = reminder;
        }

        public Reminder getReminder(){
            return this.reminder;
        }
    }

    public static class SaveEvent extends ReminderFormEvent {

        public SaveEvent(ReminderForm source, Reminder reminder) {
            super(source, reminder);
        }
    }

    public static class CloseEvent extends ReminderFormEvent {
        public CloseEvent(ReminderForm source) {
            super(source, null);
        }
    }

    @Override
    protected <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                     ComponentEventListener<T> listener) {
        return super.addListener(eventType, listener);
    }
}
