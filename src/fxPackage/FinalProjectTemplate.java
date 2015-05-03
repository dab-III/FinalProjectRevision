package fxPackage; /**
 * Created by Dan Blocker on 5/2/2015.
 */
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import partOne.*;
import commonFiles.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;



public class FinalProjectTemplate extends Application {
    //TODO: This program initializes the GUI for the Baby names program.
    public static String name, year, gender, result, labelNum;
    Label clearLabel, newLabel = new Label();
    int i = 4;
    @Override
    public void start(Stage primaryStage)  {
        //a GridPane arrange nodes in a grid(matrix) formation
        try {
            final GridPane mainPane = new GridPane();
            mainPane.setVgap(10);//sets the gap between rows

            //add(Node, colIndex, rowIndex)
            mainPane.add(new Label("Select a year: "), 0, 0);
            mainPane.add(new Label("Male or Female? "), 0, 1);
            mainPane.add(new Label("Enter a name: "), 0, 2);
            //Place the next text field on the same row with the last label
            //(that is column 1 (next column) and row 2 (same row))


            //The Radio button creation and positioning
            final RadioButton rbMale = new RadioButton("Male");
            final RadioButton rbFemale = new RadioButton("Female");
            final ToggleGroup group = new ToggleGroup();
            rbMale.setToggleGroup(group);
            rbFemale.setToggleGroup(group);
            mainPane.add(rbMale, 1, 1);
            mainPane.add(rbFemale, 2, 1);

            rbMale.setOnAction(e -> {
                gender = "M";
            });
            rbFemale.setOnAction(e -> {
                gender = "F";
            });

            //Placing the ComboBox for the year selection
            final ComboBox<String> cboYear = new ComboBox<String>();
            cboYear.getItems().add("All");
            for (int i = 2001; i <= 2010; i++)
                cboYear.getItems().add(Integer.toString(i));
            //Handler for the ComboBox
            cboYear.setOnAction(e -> {
                year = cboYear.getValue();
            });
            final TextField textField = new TextField();
            mainPane.add(textField, 1, 2);
            textField.setOnMouseMoved(e -> {
                name = textField.getText();
                name = name.replaceAll("\\s", "");
            });

            mainPane.add(cboYear, 1, 0);
            //Placing the Buttons for Clear, Find Ranking, and Print Top 5
            final Button btClear = new Button("Clear");
            final Button btFindRank = new Button("Find Ranking");
            final Button btPrintTop5 = new Button("Print Top 5");
            //Handler for the btFindRank button

            btFindRank.setOnAction(e -> {
                TemplateChecker.run();

                try{
                    System.out.println("\n\nFile parsing successful");
                    if(i > 4) {
                        newLabel.setText(" ");
                    }

                    result = Part1.resultReturn();
                    newLabel = new Label(result);

                    if(gender.equals("F")){

                    }
                    mainPane.add(newLabel, 1,4);
                    ++i;

                } catch (Exception e1){
                    System.out.println("File parsing failed.\nrestarting");

                }

            });
            btPrintTop5.setOnAction(e -> {

                System.out.println(name);
                //GridPane pane = new GridPane();

            });

            btClear.setOnAction(e -> {
                textField.setText(" ");
                newLabel.setText(" ");
            });

            //add all three buttons on the same row
            mainPane.add(btClear, 0, 3);
            mainPane.add(btFindRank, 1, 3);
            mainPane.add(btPrintTop5, 2, 3);

            Scene scene = new Scene(mainPane, 400, 200);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static String getGender(){
        return gender;
    }
    public static String getName(){
        return name;
    }
    public static String getYear(){
        return year;
    }
}