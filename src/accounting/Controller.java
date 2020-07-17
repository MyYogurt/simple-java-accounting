package accounting;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Controller {

    @FXML
    private TableView<Account> accountTableView;

    @FXML
    private TableColumn<Account, String> typeColumn, nameColumn, balanceColumn;

    @FXML
    private Label accountCount;

    @FXML
    public void initialize() {
        typeColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("type"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("name"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("balanceString"));
    }

    /**
     * Exits the application
     */
    public void exitApplication() {
        System.exit(0);
    }

    /**
     * Displays an about popup
     */
    public void showAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("simple-java-accounting");
        alert.setContentText("A program to aid in simple finance management.");
        alert.showAndWait();
    }

    /**
     * Generates pie charts
     * @throws Exception
     */
    public void createPieChart() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pieChart.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.getMainWindow());
        stage.setTitle("Account Overview");
        stage.setScene(new Scene(root));
        PieChart allAccounts = (PieChart) root.lookup("#pieChartAllAccounts");
        ArrayList<Account> accountList = Main.getAccountList();
        ArrayList<PieChart.Data> pieChartData = new ArrayList<PieChart.Data>();
        for (Account account : accountList) {
            pieChartData.add(new PieChart.Data(account.getName(), account.getBalanceRaw()));
        }
        for (PieChart.Data pieChartDatum : pieChartData) {
            allAccounts.getData().add(pieChartDatum);
        }
        PieChart byType = (PieChart) root.lookup("#pieChartByType");
        ArrayList<PieChart.Data> pieChartDataByType = new ArrayList<PieChart.Data>();
        int bank = 0;
        int creditCard = 0;
        for (Account account : accountList) {
            if (account.getType() == AccountType.BANK)
                bank += account.getBalanceRaw();
            else if (account.getType() == AccountType.CREDITCARD)
                creditCard += account.getBalanceRaw();
        }
        pieChartDataByType.add(new PieChart.Data("BANK", bank));
        pieChartDataByType.add(new PieChart.Data("CREDIT CARD", creditCard));
        for (PieChart.Data pieChartDatum : pieChartDataByType) {
            byType.getData().add(pieChartDatum);
        }
        stage.show();

    }

    /**
     * Creates a new account
     * @throws Exception
     */
    public void createNewAccount() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newAccount.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.getMainWindow());
        stage.setTitle("Create new account");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
