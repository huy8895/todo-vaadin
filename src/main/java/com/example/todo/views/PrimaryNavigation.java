package com.example.todo.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

public class PrimaryNavigation extends Div {
    public PrimaryNavigation() {
        Tab profile = new Tab(
                VaadinIcon.USER.create(),
                new Span("Profile")
        );
        Tab settings = new Tab(
                VaadinIcon.COG.create(),
                new Span("Settings")
        );
        Tab notifications = new Tab(
                VaadinIcon.BELL.create(),
                new Span("Notifications")
        );
        Tab dashboard = new Tab(
                VaadinIcon.DASHBOARD.create(),
                new Span("Dashboard")
        );

        Tabs tabs = new Tabs(dashboard,profile, settings, notifications);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        add(tabs);
    }
}
