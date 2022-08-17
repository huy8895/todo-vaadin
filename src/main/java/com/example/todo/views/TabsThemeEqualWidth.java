package com.example.todo.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "tabs-theme-equal-width" , layout = MainLayout.class)
public class TabsThemeEqualWidth  extends Div {

    public TabsThemeEqualWidth() {
        Tab open = new Tab(
                new Span("Open"),
                createBadge(24)
        );

//        open.addAttachListener(event -> UI.getCurrent().navigate(TabsWithRoutes.ViewA.class));
        Tab completed = new Tab(
                new Span("Completed"),
                createBadge(439)
        );

//        Tab cancelled = new Tab(
//                new Span("Cancelled"),
//                createBadge(5)
//        );

        Tabs tabs = new Tabs(open, completed);
        tabs.addSelectedChangeListener(event -> this.navigate(event.getSelectedTab()));
        tabs.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
        add(tabs);
    }

    private void navigate(Tab selectedTab) {
        selectedTab.getUI().ifPresent(ui -> ui.navigate(ViewA.class));
    }

    @Route(value = "tabs-theme-equal-width/a", layout = MainLayout.class)
    private static class ViewA extends TabsWithRoutes {

        public ViewA() {
            add(new Text("Content of A"));
        }
    }

    @Route(value = "tabs-theme-equal-width/b", layout = MainLayout.class)
    private static class ViewB extends TabsWithRoutes implements HasUrlParameter<String> {

        @Override
        public void setParameter(BeforeEvent beforeEvent, String s) {
            add(new Text("Content of B with parameter " + s));
        }
    }

    private Span createBadge(int value) {
        Span badge = new Span(String.valueOf(value));
        badge.getElement().getThemeList().add("badge small contrast");
        badge.getStyle().set("margin-inline-start", "var(--lumo-space-xs)");
        return badge;
    }

}
