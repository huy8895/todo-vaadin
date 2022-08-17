package com.example.todo.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

import java.util.HashMap;
import java.util.Map;

public class RouteTabs extends Tabs implements BeforeEnterObserver {
    private final Map<RouterLink, Tab> routerLinkTabMap = new HashMap<>();

    public void add(RouterLink routerLink) {
        routerLink.setHighlightCondition(HighlightConditions.sameLocation());
        routerLink.setHighlightAction(
                (link, shouldHighlight) -> {
                    if (shouldHighlight) setSelectedTab(routerLinkTabMap.get(routerLink));
                }
        );
        routerLinkTabMap.put(routerLink, new Tab(routerLink));
        add(routerLinkTabMap.get(routerLink));
    }

    public void add(Span span, Component... components) {
        span.getChildren()
            .findFirst()
            .filter(component -> component instanceof RouterLink)
            .map(component -> (RouterLink) component)
            .ifPresent(routerLink -> {
                routerLink.setHighlightCondition(HighlightConditions.sameLocation());
                routerLink.setHighlightAction(
                        (link, shouldHighlight) -> {
                            if (shouldHighlight) {
                                setSelectedTab(routerLinkTabMap.get(routerLink));
                            }
                        }
                );

                routerLinkTabMap.put(routerLink, new Tab(span, components[0]));
                final var tab = routerLinkTabMap.get(routerLink);
                add(tab);
            });

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // In case no tabs will match
        setSelectedTab(null);
    }
}