package accounting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;

public class Main extends Application {

    private static Window mainWindow;

    /**
     * List of current accounts
     */
    private final static ArrayList<Account> accountList = new ArrayList<Account>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        FXMLLoader mainWindow = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = mainWindow.load();
        primaryStage.setTitle("Simple Accounting");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * Tests if accounts are of the same type
     *
     * @param account1 First account
     * @param account2 Second account
     * @return True if accounts are of same type
     */
    public static boolean areSameAccountType(Account account1, Account account2) {
        return account1.getClass() == account2.getClass();
    }

    /**
     * Adds an account to the accountList
     * @param account account to be added
     */
    public static void addAccount(Account account) {
        accountList.add(account);
    }

    /**
     * Returns the current accountList
     * @return accountList
     */
    public static ArrayList<Account> getAccountList() {
        return accountList;
    }

    public static Window getMainWindow() {
        return mainWindow;
    }

    public static void main(String[] args) {
        launch(args);
    }
}