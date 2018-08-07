package com.processingcenter.processingcenter.views;

/**
 * Created by davlet on 2/2/18.
 */

import com.processingcenter.processingcenter.entity.Account;
import com.processingcenter.processingcenter.repositories.AccountRepository;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by davlet on 2/2/18.
 */
@SpringComponent
@UIScope
public class AccountWithdraw extends VerticalLayout {
    private final AccountRepository accountRepository;
    TextField amountField;
    Button executeOperationButton;
    Button cancelOperationButton;
    CssLayout buttons;
    Account account;
    UpdateClickListener clickUpdate;

    public void setClickUpdate(UpdateClickListener clickUpdate) {
        this.clickUpdate = clickUpdate;
    }

    @Autowired
    public AccountWithdraw(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        amountField = new TextField("Amount");
        executeOperationButton = new Button("Execute");
        executeOperationButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        executeOperationButton.addClickListener(e -> topup());
        cancelOperationButton = new Button("Cancel");
        cancelOperationButton.addClickListener(e -> setVisible(false));
        buttons = new CssLayout(executeOperationButton, cancelOperationButton);

        addComponents(amountField, buttons);

        // Configure and style components
        setSpacing(true);
        setVisible(false);
    }

    private void topup() {
        Integer amountToWithdraw = Integer.parseInt(amountField.getValue());
        if ((amountToWithdraw <= accountRepository.findByAccId(account.getAccId()).getBalance()) & amountToWithdraw > 0){
            account.setBalance(account.getBalance() - Integer.parseInt(amountField.getValue()));
            accountRepository.save(account);
            Notification.show("Successfully withdrawed " + amountToWithdraw + " from balance!");
            setVisible(false);
            amountField.clear();
            clickUpdate.updateList();
        } else {
            Notification.show("Amount must be number and greater zero and less than balance!", Notification.Type.ERROR_MESSAGE);
            amountField.clear();
        }
    }

    public interface UpdateClickListener {
        void updateList();
    }

    public void enable(Account account){
        this.account = account;
        setVisible(true);
    }
}

