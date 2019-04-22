import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


public class TableHandler{

    public TableHandler() {

    }

    public Node listAllLiteratureAsTable(Register register) {
        VBox vbox = new VBox();
        TableView tableView;

        //The title column
        TableColumn<Literature, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        //The publisher column
        TableColumn<Literature, String> publisherColumn = new TableColumn<>("Publisher");
        publisherColumn.setMinWidth(200);
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        //The published date column
        TableColumn<Literature, String> publishedDateColumn = new TableColumn<>("Date");
        publishedDateColumn.setMinWidth(200);
        publishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));

        //The price column
        TableColumn<Literature, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //The type of literature column
        TableColumn<Literature, String> typeOfLiteratureColumn = new TableColumn<>("Type");
        typeOfLiteratureColumn.setMinWidth(200);
        typeOfLiteratureColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfLiterature"));

        tableView = new TableView();
        tableView.setItems(register.getLiteraturesListAsObservarbleList());
        tableView.getColumns().addAll(titleColumn, publisherColumn, publishedDateColumn, priceColumn, typeOfLiteratureColumn);

        vbox.getChildren().add(tableView);

        return vbox;
    }

    public Node listLiteratureByTypeAsTable(Register register, String typeOfliterature) {
        VBox vbox = new VBox();
        TableView tableView;

        //The title column
        TableColumn<Literature, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        //The publisher column
        TableColumn<Literature, String> publisherColumn = new TableColumn<>("Publisher");
        publisherColumn.setMinWidth(200);
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        //The published date column
        TableColumn<Literature, String> publishedDateColumn = new TableColumn<>("Date");
        publishedDateColumn.setMinWidth(200);
        publishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));

        //The price column
        TableColumn<Literature, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //The type of literature column
        TableColumn<Literature, String> typeOfLiteratureColumn = new TableColumn<>("Type");
        typeOfLiteratureColumn.setMinWidth(200);
        typeOfLiteratureColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfLiterature"));

        tableView = new TableView();
        register.findLiterautreByType(typeOfliterature);
        tableView.setItems(register.getLiteratureByTypeListAsObservableList());
        tableView.getColumns().addAll(titleColumn, publisherColumn, publishedDateColumn, priceColumn, typeOfLiteratureColumn);

        vbox.getChildren().add(tableView);

        return vbox;
    }
}
