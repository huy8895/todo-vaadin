package com.example.todo.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.*;

import java.util.HashMap;
import java.util.Map;

//@Route(value = "tabs-with-routes", layout = MainLayout.class)
public class PrimaryNavigation extends VerticalLayout implements RouterLayout, BeforeEnterObserver {
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
                new RouterLink("View A", ViewB.class),
                VaadinIcon.BELL.create(),
                new Span("Notifications")
        );
        Tab dashboard = new Tab(
                new RouterLink("View A", ViewA.class),
                VaadinIcon.DASHBOARD.create(),
                new Span("Dashboard")
        );

        Tabs tabs = new Tabs(dashboard,profile, settings, notifications);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        add(tabs);

//        RouteTabs routeTabs = new RouteTabs();
//        final var view_a = new RouterLink("View A", ViewA.class);
//        final var view_b = new RouterLink("View A", ViewB.class);
//        routeTabs.add(view_a);
//        routeTabs.add(new RouterLink("View B with parameter 'text'", ViewB.class, "text"));
//        add(routeTabs);
    }

//    @Route(value = "tabs-with-routes/a", layout = PrimaryNavigation.class)
    private static class ViewA extends Div {
        public ViewA() {
            add(new Text("Content of A"));
        }
    }

//    @Route(value = "tabs-with-routes/b", layout = PrimaryNavigation.class)
    private static class ViewB extends Div implements HasUrlParameter<String> {

        @Override
        public void setParameter(BeforeEvent beforeEvent, String s) {
            add(new Text("Content of B with parameter " + s));
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getNavigationTarget() == PrimaryNavigation.class) {
            event.forwardTo(ViewA.class);
        }
    }

    private static class RouteTabs extends Tabs implements BeforeEnterObserver {
        private final Map<RouterLink, Tab> routerLinkTabMap = new HashMap<>();

        public RouteTabs(Tab... tabs) {
            super(tabs);
        }

        public void add(RouterLink routerLink) {
            routerLink.setHighlightCondition(HighlightConditions.sameLocation());
            routerLink.setHighlightAction(
                    (link, shouldHighlight) -> {
                        if (shouldHighlight) setSelectedTab(routerLinkTabMap.get(routerLink));
                    }
            );
            routerLinkTabMap.put(routerLink, new Tab(routerLink));
            final var tab = routerLinkTabMap.get(routerLink);
            add(tab);
        }

        @Override
        public void beforeEnter(BeforeEnterEvent event) {
            // In case no tabs will match
            setSelectedTab(null);
        }
    }
}
