import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

public class Login1 extends Application {
    private int attempt=0;

    public enum AccountType {
        SelectAccount,Admin, Student, Staff, Guest
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final int LIMIT = 3;

        Alert alert = new Alert(AlertType.INFORMATION);

        String cUserName = "Jude";
        String cPassword = "NOVA";
        AccountType cAccountType = AccountType.Student;
        TextField usernameTxt = new TextField();
        usernameTxt.setText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setText("password");
        ComboBox <AccountType>comboBox = new ComboBox<>();
        comboBox.getItems().addAll(AccountType.SelectAccount, AccountType.Admin, AccountType.Student, AccountType.Staff, AccountType.Guest);
        comboBox.setValue(AccountType.SelectAccount);
        comboBox.setVisible(false);
        Button submitBtn = new Button("Log in");
        submitBtn.setOnAction(e-> {
            if (usernameTxt.getText().equals(cUserName) && passwordField.getText().equals(cPassword) && attempt <= LIMIT) {
                comboBox.setVisible(true);
            } else if(attempt<LIMIT) {
                alert.setContentText("Your username or password is incorrect!");
                alert.showAndWait();
                attempt++;
            } else if(attempt>=LIMIT) {
                alert.setContentText("Please contact your administrator to unlock your account!");
                alert.showAndWait();
            }
        });

        comboBox.setOnAction(e-> {
        if (comboBox.getValue().equals(cAccountType)) {
                alert.setContentText("Welcome " + cUserName + "!");
                alert.showAndWait();

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

            }else {

                alert.setTitle("Error");
                alert.setContentText("The number of players must be at least 3.");
                alert.showAndWait();

            }

            TextInputDialog playerInput = new TextInputDialog("Number of players");
            playerInput.setTitle("Number of Players");
            playerInput.setContentText("Enter the numbers of players. It must be above 3.");
            Optional<String> ptInput = playerInput.showAndWait();

            double dbInput = Double.parseDouble(ptInput.get());

            int numPlayers = (int) dbInput;

            if ((numPlayers >= 11) && (numPlayers <= 55)) {
                int teamSize = numPlayers/11;


                alert.setTitle("Results");
                alert.setContentText("The amount of people you entered is " + numPlayers + "\nThe group size is " + teamSize);
                alert.showAndWait();

            } else {

                int teamSize = 1;
                
                alert.setTitle("Results");
                alert.setContentText("The amount of people you entered is " + numPlayers + "\nThe group size is " + teamSize);
                alert.showAndWait();

            }


        } else if (!comboBox.getValue().equals(cAccountType)) {
                alert.setContentText("Wrong Account Type! Please select the account type again!");
                alert.showAndWait();
        }

    });

     VBox pane = new VBox();
     pane.getChildren().addAll(usernameTxt,passwordField,comboBox,submitBtn);

     Scene scene = new Scene(pane);
     primaryStage.setTitle("Authentication");
     primaryStage.setScene(scene);
     primaryStage.show();

    }
}
