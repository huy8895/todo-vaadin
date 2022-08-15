package com.example.todo.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AppLayoutSecondaryNavigation extends AppLayout {

    public AppLayoutSecondaryNavigation() {
        H1 appTitle = new H1("MyApp");
        appTitle.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("line-height", "var(--lumo-size-l)")
                .set("margin", "0 var(--lumo-space-m)");

        var views = this.getPrimaryNavigation();

        DrawerToggle toggle = new DrawerToggle();

        H2 viewTitle = new H2("Orders");
        viewTitle.getStyle()
                 .set("font-size", "var(--lumo-font-size-l)")
                 .set("margin", "0");

        var subViews = this.getSecondaryNavigation();

        HorizontalLayout wrapper = new HorizontalLayout(toggle, viewTitle);
        wrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        wrapper.setSpacing(false);

        VerticalLayout viewHeader = new VerticalLayout(wrapper, subViews);
        viewHeader.setPadding(false);
        viewHeader.setSpacing(false);

        addToDrawer(appTitle, views);
        addToNavbar(viewHeader);

        setPrimarySection(Section.DRAWER);
    }

    private SecondaryNavigation getSecondaryNavigation() {
        return new SecondaryNavigation();
    }

    private PrimaryNavigation getPrimaryNavigation() {
        return new PrimaryNavigation();
    }
}
