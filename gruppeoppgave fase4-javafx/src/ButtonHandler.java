import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ButtonHandler {

    private TableHandler tableHandler;

    public ButtonHandler() {
        tableHandler = new TableHandler();
    }

    public GridPane createListByTypeForm(Register register, BorderPane root) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text gridTitle = new Text("List literature by:");
        gridTitle.setFont(Font.font("Sans-serif", FontWeight.NORMAL, 20));
        grid.add(gridTitle, 0, 0, 2, 1);

        Button newspaperButton = new Button("Newspaper");
        grid.add(newspaperButton, 0, 1);
        newspaperButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.setCenter(tableHandler.listLiteratureByTypeAsTable(register, "newspaper"));
            }
        });
        /*
         * When the user is done looking at the newspapers, we have to
         * delete all the objects from the literature by type list so that
         * we avoid duplicates.
         */
        register.removeLiteratureFromLiteratureByTypeList();

        Button magazineButton = new Button("Magazine");
        grid.add(magazineButton, 1, 1);
        magazineButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.setCenter(tableHandler.listLiteratureByTypeAsTable(register, "magazine"));
            }
        });
        /*
         * When the user is done looking at the magazines, we have to
         * delete all the objects from the literature by type list so that
         * we avoid duplicates.
         */
        register.removeLiteratureFromLiteratureByTypeList();

        Button bookButton = new Button("Book");
        grid.add(bookButton, 2, 1);
        bookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.setCenter(tableHandler.listLiteratureByTypeAsTable(register, "book"));
            }
        });
        /*
         * When the user is done looking at the books, we have to
         * delete all the objects from the literature by type list so that
         * we avoid duplicates.
         */
        register.removeLiteratureFromLiteratureByTypeList();

        return grid;
    }
}
