import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

public class Login extends Application {
    private int attempt=1; //default amount of attempts made

    public enum AccountType {
        SelectAccount,Admin, Student, Staff, Guest //state account types
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final int LIMIT = 3; //maximum amount of attempts

        Alert alert = new Alert(AlertType.INFORMATION); //set up for all alerts

        //correct information
        String cUserName = "Jude";
        String cPassword = "NOVA";
        AccountType cAccountType = AccountType.Student;

        TextField usernameTxt = new TextField();

        //set up username input
        usernameTxt.setText("username");
        PasswordField passwordTxt = new PasswordField();

        //set up password input
        passwordTxt.setText("password");
        ComboBox <AccountType>comboBox = new ComboBox<>();

        //set up account type button
        comboBox.getItems().addAll(AccountType.SelectAccount, AccountType.Admin, AccountType.Student, AccountType.Staff, AccountType.Guest);
        comboBox.setValue(AccountType.SelectAccount);
        comboBox.setVisible(true);

        //log in button
        Button submitBtn = new Button("Log in");
        submitBtn.setOnAction(e-> {
            if (comboBox.getValue().equals(cAccountType) && cUserName.equals(usernameTxt.getText()) && cPassword.equals(passwordTxt.getText())) {
                primaryStage.close();
                alert.setContentText("Welcome " + cUserName + "!");
                alert.showAndWait();

                //start group count
                TextInputDialog peopleInput = new TextInputDialog("number of people");
                peopleInput.setTitle("Number of People");
                peopleInput.setContentText("Enter the numbers of people. It must be above 3.");
                Optional<String> pInput = peopleInput.showAndWait();

                double dInput = Double.parseDouble(pInput.get());

                int numPeople = (int) dInput;

                if (numPeople > 10) {

                    int groupSize = numPeople / 2;

                    alert.setTitle("Results");
                    alert.setContentText("The amount of people you entered is " + numPeople + "\nThe group size is " + groupSize);
                    alert.showAndWait();

                } else if ((numPeople >= 3) && (numPeople <= 10)) {

                    int groupSize = numPeople / 3;

                    alert.setTitle("Results");
                    alert.setContentText("The amount of people you entered is " + numPeople + "\nThe group size is " + groupSize);
                    alert.showAndWait();

                } else {

                    alert.setTitle("Error");
                    alert.setContentText("The number of players must be at least 3.");
                    alert.showAndWait();

                }

                //start team count
                TextInputDialog playerInput = new TextInputDialog("Number of players");
                playerInput.setTitle("Number of Players");
                playerInput.setContentText("Enter the numbers of players. It must be above 3.");
                Optional<String> ptInput = playerInput.showAndWait();

                double dbInput = Double.parseDouble(ptInput.get());

                int numPlayers = (int) dbInput;

                if ((numPlayers >= 11) && (numPlayers <= 55)) {
                    int teamSize = numPlayers / 11;


                    alert.setTitle("Results");
                    alert.setContentText("The amount of people you entered is " + numPlayers + "\nThe group size is " + teamSize);
                    alert.showAndWait();

                } else {

                    int teamSize = 1;

                    alert.setTitle("Results");
                    alert.setContentText("The amount of people you entered is " + numPlayers + "\nThe group size is " + teamSize);
                    alert.showAndWait();

                }
                //end team and group count

                //more tries
            }else if (!comboBox.getValue().equals(cAccountType) && cUserName.equals(usernameTxt.getText()) && cPassword.equals(passwordTxt.getText())) {
                    alert.setContentText("Wrong Account Type! Please select the account type again!");
                    alert.showAndWait();
            }else if(attempt>=LIMIT) {
                alert.setContentText("Please contact your administrator to unlock your account!");
                alert.showAndWait();
                System.exit(0);
                //no more tries
            }else {
                alert.setContentText("Your username or password is incorrect!");
                alert.showAndWait();
                attempt++;
                
            }
        });

        VBox pane = new VBox();
        pane.getChildren().addAll(usernameTxt,passwordTxt,comboBox,submitBtn);

        //show everything
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Authentication");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
