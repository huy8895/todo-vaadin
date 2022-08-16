package com.example.todo.views;

import com.example.todo.appnav.AppNav;
import com.example.todo.appnav.AppNavItem;
import com.example.todo.components.RouteTabs;
import com.example.todo.views.dashboard.DashboardView;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

@Route(value = "todo2", layout = MainLayout.class)
@PageTitle("todo2")
public class Test extends Div implements RouterLayout, BeforeEnterObserver{


    public Test() {
        add(this.createNavigation());
    }

    protected Tabs createNavigation() {
        Tab open = new Tab(
                new AppNavItem("open", ViewA.class, "la la-globe"),
                createBadge(24)
        );
        Tab completed = new Tab(
                new AppNavItem("completed", ViewB.class, "la la-globe"),
                createBadge(439)
        );

        Tabs tabs = new Tabs(open, completed);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
        return tabs;
    }



//    @Route(value = "todo2/b", layout = Test.class)
//    private static class ViewB extends Div implements HasUrlParameter<String> {
//
//        @Override
//        public void setParameter(BeforeEvent beforeEvent, String s) {
//            add(new Text("Content of B with parameter " + s));
//        }
//    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getNavigationTarget() == Test.class) {
            event.forwardTo(ViewA.class);
        }
    }

    private Span createBadge(int value) {
        Span badge = new Span(String.valueOf(value));
        badge.getElement().getThemeList().add("badge small contrast");
        badge.getStyle().set("margin-inline-start", "var(--lumo-space-xs)");
        return badge;
    }
}
@Route(value = "todo2/a", layout = MainLayout.class)
@PageTitle("todo2")
class ViewA extends Test {

    public ViewA() {
        add(new Text("Content of A"));
    }
}

@Route(value = "todo2/b", layout = MainLayout.class)
@PageTitle("todo2")
class ViewB extends Test {

    public ViewB() {
        add(new Text("Content of B"));
    }
}