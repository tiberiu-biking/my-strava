package com.tpo.strava.gui.view.login;

import com.tpo.strava.gui.event.DashboardEvent;
import com.tpo.strava.gui.event.DashboardEventBus;
import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@SpringComponent
public class LoginScreen extends CustomComponent {

    public LoginScreen() {
        FormLayout loginForm = new FormLayout();
        loginForm.setSizeUndefined();
        TextField userNameTextField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");
        Button login = new Button("Login");
        loginForm.addComponent(userNameTextField);
        loginForm.addComponent(passwordField);
        loginForm.addComponent(login);
        login.addStyleName(ValoTheme.BUTTON_PRIMARY);
        login.setDisableOnClick(true);
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addClickListener((Button.ClickListener) event -> DashboardEventBus.post(new DashboardEvent.UserLoginRequestedEvent(userNameTextField.getValue(), passwordField.getValue())));

        VerticalLayout loginLayout = new VerticalLayout();
        loginLayout.setSizeUndefined();

        Label loginFailedLabel = new Label();
        loginLayout.addComponent(loginFailedLabel);
        loginLayout.setComponentAlignment(loginFailedLabel, Alignment.BOTTOM_CENTER);
        loginFailedLabel.setSizeUndefined();
        loginFailedLabel.addStyleName(ValoTheme.LABEL_FAILURE);
        loginFailedLabel.setVisible(false);

        Label loggedOutLabel = new Label("Good bye!");
        loginLayout.addComponent(loggedOutLabel);
        loginLayout.setComponentAlignment(loggedOutLabel, Alignment.BOTTOM_CENTER);
        loggedOutLabel.setSizeUndefined();
        loggedOutLabel.addStyleName(ValoTheme.LABEL_SUCCESS);
        loggedOutLabel.setVisible(false);

        loginLayout.addComponent(loginForm);
        loginLayout.setComponentAlignment(loginForm, Alignment.TOP_CENTER);

        VerticalLayout rootLayout = new VerticalLayout(loginLayout);
        rootLayout.setSizeFull();
        rootLayout.setComponentAlignment(loginLayout, Alignment.MIDDLE_CENTER);
        setCompositionRoot(rootLayout);
        setSizeFull();
    }
}
