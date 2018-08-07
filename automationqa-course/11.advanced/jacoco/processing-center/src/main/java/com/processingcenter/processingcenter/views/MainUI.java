package com.processingcenter.processingcenter.views;

import com.processingcenter.processingcenter.entity.Account;
import com.processingcenter.processingcenter.entity.Transaction;
import com.processingcenter.processingcenter.repositories.AccountRepository;
import com.processingcenter.processingcenter.repositories.TransactionRepository;
import com.processingcenter.processingcenter.services.PaymentService;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by davlet on 1/31/18.
 */
@SpringUI
@Theme("valo")
@EnableTransactionManagement
public class MainUI extends UI implements AccountTopup.UpdateList, AccountWithdraw.UpdateClickListener, AccoundAdd.AddClickListener {
    public Navigator navigator;
    AccountRepository accountRepository;
    Grid<Account> accountGrid;
    TransactionRepository transactionRepository;
    Grid<Transaction> transactionGrid;
    TextField filterByLastName;
    Button addNewAccountBtn, addNewTransactionBtn;
    TextField fromAccount, toAccount, amount;
    Label title;
    PaymentService paymentService;
    AccoundAdd accoundAdd;
    AccountTopup accountTopup;
    AccountWithdraw accountWithdraw;

    @Autowired
    public MainUI(AccountRepository accountRepository, TransactionRepository transactionRepository, PaymentService paymentService, AccoundAdd accoundAdd, AccountTopup accountTopup, AccountWithdraw accountWithdraw) {
        initViewElements(accountRepository, transactionRepository, paymentService);
        this.accoundAdd = accoundAdd;
        this.accountTopup = accountTopup;
        this.accountWithdraw = accountWithdraw;
        this.accountTopup.setUpdateList(this);
        this.accountWithdraw.setClickUpdate(this);
        this.accoundAdd.setAddClickListener(this);

        accountGrid.addComponentColumn(this::buildTopupButton).setCaption("Topup");
        accountGrid.addComponentColumn(this::buildWithdrawButton).setCaption("Withdraw");
        accountGrid.addComponentColumn(this::buildDeleteButton).setCaption("Delete");
//        accountGrid.addComponentColumn(this::buildShowBalanceButton).setCaption("Balance");

        accountGrid.setWidth(700, Unit.PIXELS);
        accountGrid.setHeight(800, Unit.PIXELS);
        HorizontalLayout actionsAcc = new HorizontalLayout(filterByLastName, addNewAccountBtn);
        VerticalLayout accountsWithActions = new VerticalLayout(actionsAcc, accountGrid);
        HorizontalLayout accountsAll = new HorizontalLayout(accountsWithActions, accoundAdd, accountTopup, accountWithdraw);

        transactionGrid.setWidth(70, Unit.PERCENTAGE);
        transactionGrid.setHeight(1000, Unit.PIXELS);
        HorizontalLayout actionsTrx = new HorizontalLayout(fromAccount, toAccount, amount, addNewTransactionBtn);
        VerticalLayout transactionsWithActions = new VerticalLayout(actionsTrx, transactionGrid);

        VerticalLayout mainLayout = new VerticalLayout(title, accountsAll, transactionsWithActions);
        setContent(mainLayout);

        listAccounts(null);
        listTransactions();
    }

    @Transactional
    private void initViewElements(AccountRepository accountRepository, TransactionRepository transactionRepository, PaymentService paymentService) {
        this.paymentService = paymentService;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.accountGrid = new Grid<Account>(Account.class);
        this.accountGrid.setCaption("Bank Accounts");
        this.transactionGrid = new Grid<>(Transaction.class);
        this.transactionGrid.setCaption("Last Transactions");
        this.filterByLastName = new TextField();
        this.addNewAccountBtn = new Button("Add new account", FontAwesome.PLUS);
        this.addNewTransactionBtn = new Button("Make transfer", FontAwesome.PLUS);
        this.addNewAccountBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);
        this.addNewTransactionBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);
        this.fromAccount = new TextField();
        this.toAccount = new TextField();
        this.amount = new TextField();
        this.title = new Label("PROCESSING CENTER");
        filterByLastName.addValueChangeListener(x -> listAccounts(x.getValue()));
        accountGrid.setColumns("accId", "firstName", "lastName", "balance");
        transactionGrid.setColumns("trxId", "from_id", "to_id", "amount");
        addNewTransactionBtn.addClickListener(x -> addNewTransaction(fromAccount.getValue(), toAccount.getValue(), amount.getValue()));
        addNewAccountBtn.addClickListener(x -> accoundAdd.editAccount(new Account("", "", 0)));

        Label title = new Label("PROCESSING CENTER");
        this.fromAccount.setPlaceholder("from account id");
        this.toAccount.setPlaceholder("to account id");
        this.amount.setPlaceholder("amountField");
        filterByLastName.setPlaceholder("Filter by last name");
        filterByLastName.setValueChangeMode(ValueChangeMode.LAZY);
    }

    private Button buildTopupButton(Account ac){
        Button button = new Button(VaadinIcons.PLUS_CIRCLE_O);
        button.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        button.addClickListener(e -> topUpButtonHandle(ac));
        return button;
    }

    private void topUpButtonHandle(Account account){
        accountTopup.enable(account);
    }

    private Button buildWithdrawButton(Account ac){
        Button button = new Button(VaadinIcons.MINUS_CIRCLE);
        button.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        button.addClickListener(e -> withdrawButtonHandle(ac));
        return button;
    }

    private void withdrawButtonHandle(Account account){
        accountWithdraw.enable(account);
    }

    private Button buildDeleteButton(Account ac){
        Button button = new Button(VaadinIcons.TRASH);
        button.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        button.addClickListener(e -> deleteButton(ac));
        return button;
    }

    private void deleteButton(Account account){
        accountRepository.delete(account);
        Notification.show("Deleted account with id = " + account.getAccId(), Notification.Type.HUMANIZED_MESSAGE);
        listAccounts(null);
        accountGrid.sort("accId");
    }

    private Button buildShowBalanceButton(Account account){
        Button button = new Button(VaadinIcons.INFO);
        button.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        button.addClickListener(e -> Notification.show("Balance on this account equals = " + account.getBalance().toString(), Notification.Type.HUMANIZED_MESSAGE));
        return button;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    }

    private void listAccounts(String filter){
        if (StringUtils.isEmpty(filter)){
            accountGrid.setItems(accountRepository.findAll());
            accountGrid.sort("accId");
        }
        else{
            accountGrid.setItems(accountRepository.findAllByLastNameStartsWithIgnoreCase(filter));
            accountGrid.sort("accId");
        }
    }

    private void listTransactions(){
        transactionGrid.setItems(transactionRepository.findAll());
    }

    private void addNewTransaction(String fromid, String toid, String amountOfMoney) {
        if (fromid.equals("") || toid.equals("") || amountOfMoney.equals("") || Integer.parseInt(amountOfMoney) <= 0){
            Notification.show("Fill the transaction form properly!", Notification.Type.ERROR_MESSAGE);
            return;
        }

        Boolean paymentMade = paymentService.makePayment(Long.parseLong(fromid), Long.parseLong(toid), Integer.parseInt(amountOfMoney));
        if (paymentMade) {
            listTransactions();
            listAccounts(null);
            fromAccount.clear();
            toAccount.clear();
            amount.clear();
            Notification.show("Transaction succeed! Transferred " + amountOfMoney + " from id " + fromid + " to id " + toid + "!", Notification.Type.HUMANIZED_MESSAGE);
        } else {
            Notification.show("Transaction failed! Insufficient funds on balance of account with id = " + fromid + "!", Notification.Type.HUMANIZED_MESSAGE);
        }

    }

    @Override
    public void updateList() {
        listAccounts(null);
        accountGrid.sort("accId");
    }
}
