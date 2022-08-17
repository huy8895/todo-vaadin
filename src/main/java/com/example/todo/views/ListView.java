package com.example.todo.views;

import com.example.todo.components.RouteTabs;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.*;

@Route(value = "list", layout = MainLayout.class)
@PageTitle("List")
public class ListView extends Div implements RouterLayout, BeforeEnterObserver {

    public ListView() {
        RouteTabs routeTabs = new RouteTabs();

        routeTabs.add(new RouterLink("Today", Today.class));
        routeTabs.add(new RouterLink("Schedule", Schedule.class, "text"));
        routeTabs.add(new RouterLink("All", All.class));
        routeTabs.add(new RouterLink("Flagged", Flagged.class));

        routeTabs.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
        add(routeTabs);
    }
    private Span createBadge(int value) {
        Span badge = new Span(String.valueOf(value));
        badge.getElement().getThemeList().add("badge small contrast");
        badge.getStyle().set("margin-inline-start", "var(--lumo-space-xs)");
        return badge;
    }

    @Route(value = "list/today", layout = MainLayout.class)
    @PageTitle("List")
     private static class Today extends ListView {

        public Today() {
            add(new Text("Content of Today"));
        }
    }

    @Route(value = "list/schedule", layout = MainLayout.class)
    @PageTitle("List")
     static class Schedule extends ListView implements HasUrlParameter<String> {

        @Override
        public void setParameter(BeforeEvent beforeEvent, String s) {
            add(new Text("Content of Schedule with parameter " + s));
        }
    }    
    
    @Route(value = "list/all", layout = MainLayout.class)
    @PageTitle("List")
     static class All extends ListView {

        public All() {
        }
    }    
    
    @Route(value = "list/flagged", layout = MainLayout.class)
    @PageTitle("List")
     static class Flagged extends ListView {

        public Flagged() {
            add(new Text("Content of Flagged"));
        }

    }
    

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getNavigationTarget() == ListView.class) {
            event.forwardTo(Today.class);
        }
    }
}

