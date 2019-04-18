import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {

    private TextHandler textHandler;

    public MainWindow() {
        textHandler = new TextHandler();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        VBox topContainer = new VBox();
        MenuBar mainMenu = createMenues();
        createWelcomeText();

        topContainer.getChildren().add(mainMenu);

        root.setTop(topContainer);
        root.setCenter(createWelcomeText());

        Scene scene = new Scene(root, 500, 450);

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
        MenuItem listByType = new MenuItem("By type");
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
