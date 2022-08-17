package com.example.todo.views;

import com.example.todo.components.RouteTabs;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.*;

@Route(value = "tabs-with-routes", layout = MainLayout.class)
public class TabsWithRoutes extends Div implements RouterLayout, BeforeEnterObserver {

    public TabsWithRoutes() {
        new Tabs();
        RouteTabs routeTabs = new RouteTabs();
        routeTabs.add(new Span(new RouterLink("View A", ViewA.class)), createBadge(10));
        routeTabs.add(new Span(new RouterLink("View B with parameter 'text'", ViewB.class, "text")),createBadge(20));

        routeTabs.add(new RouterLink("View A", ViewA.class));
        routeTabs.add(new RouterLink("View B with parameter 'text'", ViewB.class, "text"));

        routeTabs.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
        add(routeTabs);
    }

//    public AppNavItem setIconClass(String iconClass) {
//        Span icon = new Span();
//        icon.setClassName(iconClass);
//        icon.getStyle().set("font-size", "var(--lumo-icon-size-m)");
//        setIcon(icon);
//        return this;
//    }

    private Span createBadge(int value) {
        Span badge = new Span(String.valueOf(value));
        badge.getElement().getThemeList().add("badge small contrast");
        badge.getStyle().set("margin-inline-start", "var(--lumo-space-xs)");
        return badge;
    }

    @Route(value = "tabs-with-routes/a", layout = MainLayout.class)
     static class ViewA extends TabsWithRoutes {

        public ViewA() {
            add(new Text("Content of A"));
        }
    }

    @Route(value = "tabs-with-routes/b", layout = MainLayout.class)
     static class ViewB extends TabsWithRoutes implements HasUrlParameter<String> {

        @Override
        public void setParameter(BeforeEvent beforeEvent, String s) {
            add(new Text("Content of B with parameter " + s));
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getNavigationTarget() == TabsWithRoutes.class) {
            event.forwardTo(ViewA.class);
        }
    }
}

