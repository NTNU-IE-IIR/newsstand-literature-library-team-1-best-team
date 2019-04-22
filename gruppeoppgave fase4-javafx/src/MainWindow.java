import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {

    private TextHandler textHandler;
    private TableHandler tableHandler;
    private FormHandler formHandler;
    private ButtonHandler buttonHandler;

    private BorderPane root;

    private Register displayRegister;

    public MainWindow() {
        textHandler = new TextHandler();
        tableHandler = new TableHandler();
        root = new BorderPane();
        formHandler = new FormHandler();
        buttonHandler = new ButtonHandler();
        displayRegister = new Register();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox topContainer = new VBox();
        MenuBar mainMenu = createMenues();
        createWelcomeText();

        topContainer.getChildren().add(mainMenu);

        root.setTop(topContainer);
        root.setCenter(createWelcomeText());

        Scene scene = new Scene(root, 1000, 800);

        primaryStage.setTitle("Elton & Schei Kiosk");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private MenuBar createMenues() {
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        MenuItem openFile = new MenuItem("Open");
        MenuItem printFile = new MenuItem("Print");
        menuFile.getItems().addAll(openFile, printFile);

        Menu menuEdit = new Menu("Edit");

        Menu menuView = new Menu("View");

        Menu menuList = new Menu("List");
        MenuItem listAll = new MenuItem("All");
        //When the "all" menu gets pressed, the whole literature list will be displayed
        listAll.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        root.setCenter(tableHandler.listAllLiteratureAsTable(displayRegister));
                    }
                }
        );

        MenuItem listByType = new MenuItem("By type");
        listByType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.setCenter(buttonHandler.createListByTypeForm(displayRegister, root));
            }
        });

        MenuItem listByPublisher = new MenuItem("By publisher");
        menuList.getItems().addAll(listAll, listByType, listByPublisher);

        Menu menuSearch = new Menu("Search");
        MenuItem searchTitle = new MenuItem("Title");
        MenuItem searchPublisher = new MenuItem("Publisher");
        menuSearch.getItems().addAll(searchTitle, searchPublisher);

        Menu menuConvert = new Menu("Convert");
        MenuItem bookToSeries = new MenuItem("Book to book-series");
        menuConvert.getItems().add(bookToSeries);

        Menu menuAdd = new Menu("Add");
        MenuItem addLiterature = new MenuItem("Add literature");
        menuAdd.getItems().add(addLiterature);
        addLiterature.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        root.setCenter(formHandler.createAddLiteratureForm(displayRegister));
                    }
                }
        );

        menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuList, menuSearch, menuConvert, menuAdd);

        return menuBar;
    }

    private GridPane createWelcomeText() {
        GridPane welcomeText = new GridPane();
        Text text = textHandler.welcomeText();

        welcomeText.setAlignment(Pos.CENTER);
        welcomeText.getChildren().add(text);

        return welcomeText;
    }

}
