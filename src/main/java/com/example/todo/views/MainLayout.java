package com.example.todo.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        this.createHeader();
        this.createDrawer();
        setPrimarySection(Section.DRAWER);
    }


    private void createHeader() {
//        DrawerToggle toggle = new DrawerToggle();
//
//        H2 viewTitle = new H2("Orders");
//        viewTitle.getStyle()
//                 .set("font-size", "var(--lumo-font-size-l)")
//                 .set("margin", "0");
//
//        var subViews = this.getSecondaryNavigation();
//
//        HorizontalLayout wrapper = new HorizontalLayout(toggle, viewTitle);
//        wrapper.setAlignItems(FlexComponent.Alignment.CENTER);
//        wrapper.setSpacing(false);
//
//        VerticalLayout viewHeader = new VerticalLayout(wrapper, subViews);
//        viewHeader.setPadding(false);
//        viewHeader.setSpacing(false);
//
//        addToNavbar(viewHeader);
    }

    private Tabs getSecondaryNavigation() {
        Tab details = new Tab("Details");
        Tab payment = new Tab("Payment");
        Tab shipping = new Tab("Shipping");

        Tabs tabs = new Tabs(details, payment, shipping);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
        return tabs;
    }

    private void createDrawer() {
        H1 appTitle = new H1("MyApp");
        appTitle.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("line-height", "var(--lumo-size-l)")
                .set("margin", "0 var(--lumo-space-m)");
        var views = this.getPrimaryNavigation();
        addToDrawer(appTitle, views);
    }

    private Tabs getPrimaryNavigation() {
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
                new RouterLink("Todo", Todo.class)
        );

        Tabs tabs = new Tabs(dashboard,profile, settings, notifications);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

}
