import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FormHandler  {

    public FormHandler() {

    }

    public GridPane createAddLiteratureForm(Register register) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text gridTitle = new Text("Add literature");
        gridTitle.setFont(Font.font("Sans-serif", FontWeight.NORMAL, 20));
        grid.add(gridTitle, 0, 0, 2, 1);

        //The title form field for adding literature
        Label literatureTitle = new Label("Title:");
        grid.add(literatureTitle, 0, 1);
        TextField literatureTitleTextField = new TextField();
        grid.add(literatureTitleTextField, 1, 1);

        //The publisher form field for adding literature
        Label literaturePublisher = new Label("Publisher:");
        grid.add(literaturePublisher, 0, 2);
        TextField literaturePublisherTextField = new TextField();
        grid.add(literaturePublisherTextField, 1, 2);

        //The published date form field for adding literature
        Label literaturePublishedDate = new Label("Published date:");
        grid.add(literaturePublishedDate, 0, 3);
        TextField literaturePublishedDateTextField = new TextField();
        grid.add(literaturePublishedDateTextField, 1, 3);

        //The price form field for adding literature
        Label literaturePrice = new Label("Price:");
        grid.add(literaturePrice, 0, 4);
        TextField literaturePriceTextField = new TextField();
        grid.add(literaturePriceTextField, 1, 4);

        //The type of literature form field for adding literature
        Label literatureType = new Label("Type:");
        grid.add(literatureType, 0, 5);
        TextField literatureTypeTextField = new TextField();
        grid.add(literatureTypeTextField, 1, 5);

        /*
         * The add literature button, when the add literature button is
         * pressed, it will check if any of the fields are empty.
         */
        Button addButton = new Button("Add");
        HBox hBoxAddButton = new HBox(10);
        hBoxAddButton.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxAddButton.getChildren().add(addButton);
        grid.add(hBoxAddButton, 1, 6);
        //The text that will be displayed when the user presses add button
        final Text actionTarget = new Text();
        grid.add(actionTarget, 1,  8);
        addButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Checks if any of the form field's are empty
                        if (literatureTitleTextField.getText().isEmpty() ||
                            literaturePublisherTextField.getText().isEmpty() ||
                            literaturePublishedDateTextField.getText().isEmpty() ||
                            literaturePriceTextField.getText().isEmpty() ||
                            literatureTypeTextField.getText().isEmpty()) {

                            actionTarget.setFill(Color.FIREBRICK);
                            actionTarget.setText("You have to fill all the form's");
                        }
                        //If the form field's are not empty, the literature will be added to the list
                        else {
                            register.addLiteratureToRegister(
                                    literatureTitleTextField.getText(),
                                    literaturePublisherTextField.getText(),
                                    literaturePublishedDateTextField.getText(),
                                    literaturePriceTextField.getText(),
                                    literatureTypeTextField.getText().toLowerCase()
                            );

                            actionTarget.setFill(Color.GREEN);
                            actionTarget.setText("The literature was added");
                            //Update the register's observable list
                            register.updateObservableList();
                            //Clear the text fields for better re-usability
                        }
                    }
                }
        );

        return grid;
    }

}
