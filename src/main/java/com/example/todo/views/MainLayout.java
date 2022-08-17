package com.example.todo.views;

import com.example.todo.appnav.AppNav;
import com.example.todo.appnav.AppNavItem;
import com.example.todo.views.dashboard.DashboardView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainLayout extends AppLayout {
    private H1 viewTitle;
    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, this.createHeaderContent());
        addToDrawer(this.createDrawerContent());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassNames("view-toggle");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("view-title");

        Header header = new Header(toggle, viewTitle);
        header.addClassNames("view-header");
        return header;
    }

    private Component createDrawerContent() {
        H2 appName = new H2("My App");
        appName.addClassNames("app-name");

        var section =
                new com.vaadin.flow.component.html.Section(appName,
                                                           this.createNavigation(),
                                                           this.createFooter());
        section.addClassNames("drawer-section");
        return section;
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("app-nav-footer");
        // TODO: 16/8/2022 add user avatar login
        Avatar avatar = new Avatar("huy8895", "https://randomuser.me/api/portraits/men/24.jpg");
        avatar.addClassNames("me-xs");

        ContextMenu userMenu = new ContextMenu(avatar);
        userMenu.setOpenOnClick(true);
        userMenu.addItem("Logout", e -> {
            log.info("logout click!");
        });
        Span name = new Span("huy trịnh");
        name.addClassNames("font-medium", "text-s", "text-secondary");

        layout.add(avatar, name);
        return layout;
    }

    private AppNav createNavigation() {
        AppNav nav = new AppNav();
        nav.addClassNames("app-nav");

        nav.addItem(new AppNavItem("Dashboard", DashboardView.class, "la la-globe"));
        nav.addItem(new AppNavItem("Todo", Todo.class, "la la-phone"));
        nav.addItem(new AppNavItem("test", Test.class, "las la-universal-access"));
        nav.addItem(new AppNavItem("list", ListView.class, "las la-universal-access"));
        nav.addItem(new AppNavItem("tabs-theme-equal-width", TabsThemeEqualWidth.class, "las la-universal-access"));


        return nav;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }

}
