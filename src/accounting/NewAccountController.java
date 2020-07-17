package accounting;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NewAccountController {

    @FXML
    private RadioButton bankRadio, creditCardRadio;

    @FXML
    private TextField nameField, balanceField;

    @FXML
    private Button createButton;

    @FXML
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        bankRadio.setSelected(true);
        bankRadio.setToggleGroup(group);
        creditCardRadio.setToggleGroup(group);
    }

    public Account createAccount() {
        if (balanceField.getText().contains(".")) {
            String balance = balanceField.getText().replaceAll("\\$", "").replaceAll("\\.", "").replaceAll(",", "");
            if (bankRadio.isSelected())
                return new Account(nameField.getText(), Integer.parseInt(balance), AccountType.BANK);
            return new Account(nameField.getText(), Integer.parseInt(balance), AccountType.CREDITCARD);
        }
        String balance = balanceField.getText().replaceAll("\\$", "").replaceAll(",", "");
        if (bankRadio.isSelected())
            return new Account(nameField.getText(), Integer.parseInt(balance) * 100, AccountType.BANK);
        return new Account(nameField.getText(), Integer.parseInt(balance) * 100, AccountType.CREDITCARD);
    }

    public void displayAccount() {
        Stage window = (Stage) createButton.getScene().getWindow();
        Stage owner = (Stage) window.getOwner();
        Scene scene = owner.getScene();
        Parent root = scene.getRoot();
        TableView<Account> listView = (TableView<Account>) root.lookup("#accountTableView");
        Account userAccount = createAccount();
        listView.getItems().add(userAccount);
        Main.addAccount(userAccount);
        Label accountLabel = (Label) root.lookup("#accountCount");
        ArrayList<Account> accountList = Main.getAccountList();
        int sum = 0;
        for (Account account : accountList) {
            if (account.getType() == AccountType.BANK)
                sum += account.getBalanceRaw();
            else if (account.getType() == AccountType.CREDITCARD)
                sum -= account.getBalanceRaw();
        }
        accountLabel.setText("# of accounts: "+listView.getItems().size()+" Total: $"+sum/100+"."+sum%100);
        window.close();
    }
}