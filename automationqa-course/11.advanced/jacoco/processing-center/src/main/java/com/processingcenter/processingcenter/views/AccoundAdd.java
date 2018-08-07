package com.processingcenter.processingcenter.views;

import com.processingcenter.processingcenter.entity.Account;
import com.processingcenter.processingcenter.repositories.AccountRepository;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by davlet on 1/31/18.
 */
@SpringComponent
@UIScope
public class AccoundAdd extends VerticalLayout {
    private final AccountRepository accountRepository;

    /**
     * The currently adding account object
     */
    private Account account;

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    CssLayout actions = new CssLayout(save, cancel);

    Binder<Account> binder = new Binder<>(Account.class);
    AddClickListener addClickListener;

    public void setAddClickListener(AddClickListener addClickListener) {
        this.addClickListener = addClickListener;
    }

    @Autowired
    public AccoundAdd(AccountRepository accountRepository) {

        firstName.setPlaceholder("first name");
        lastName.setPlaceholder("last name");

        this.accountRepository = accountRepository;
        addComponents(firstName, lastName, actions);

        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // wire action buttons to save and cancel
        save.addClickListener(e -> addAccount(account));
        cancel.addClickListener(e -> cancelAddingAccount());
        setVisible(false);
    }

    private void addAccount(Account account) {
        if (firstName.getValue() != "" && lastName.getValue() != "") {
            account = new Account(firstName.getValue(), lastName.getValue(), 0);
            accountRepository.save(account);
            account = null;
            addClickListener.updateList();
            Notification.show("Added new account");
            setVisible(false);
            firstName.clear();
            lastName.clear();
        } else {
            Notification.show("Error adding new account, first fill out the form!", Notification.Type.ERROR_MESSAGE);
        }
    }

    public void editAccount(Account account) {
        setVisible(true);
    }

    public void cancelAddingAccount(){
        setVisible(false);
        firstName.clear();
        lastName.clear();
        Notification.show("Canceled");
    }

    public interface AddClickListener {
        void updateList();
    }
}
