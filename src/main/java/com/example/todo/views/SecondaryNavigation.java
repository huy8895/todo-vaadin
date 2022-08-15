package com.example.todo.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;

public class SecondaryNavigation extends Div {
    public SecondaryNavigation() {
        Tab details = new Tab("Details");
        Tab payment = new Tab("Payment");
        Tab shipping = new Tab("Shipping");

        Tabs tabs = new Tabs(details, payment, shipping);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
        add(tabs);
    }
}
