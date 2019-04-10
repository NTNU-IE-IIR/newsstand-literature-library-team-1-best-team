package no.ntnu.trygvew;

import javafx.application.Application;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import no.ntnu.trygvew.litratureTypes.*;
import no.ntnu.trygvew.messingAround.UserLoggin;
import no.ntnu.trygvew.messingAround.encryption.Password;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * quality of life interface to simplefy and reduce dupication
 */
@FunctionalInterface
interface emptyFuncCall{
    void exe();
}

@FunctionalInterface
interface tooltipVisibilityFunc{
    void exe(CustomMenuItem cmi, Tooltip t);
}


/**
 * TODO: legg inn en varsel på når stock av en ting e tomm
 * todo: se om du finn en api t goodread elns så du får fylt registeret orgentlig
 * TODO: du MÅ legg inn boksere det e en av kravan
 * TODO: Ha kun admin brukera så hvis et kjøp blir gennomført så logges det til den som solgt
 * TODO: muligens ha en basket så flere ting blir kjøpt samtidig
 * TODO: må ha mulighet til og jøre enkeltstående bøker omm til serier
 */
public class Main extends Application {
    private boolean isLoggedInn = false;
    private String currentShownTable = "all";

    private Stage primaryStage;

    private UserLoggin loggin = new UserLoggin();

    private emptyFuncCall updateMenuVisebility;

    //private ArrayList<>

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();
        VBox topContainer = new VBox();
        VBox midContainer = this.makeTable();
        MenuBar menuBar = this.makeMenuBar();

        topContainer.getChildren().addAll(menuBar);
        root.setTop(topContainer);
        root.setCenter(midContainer);


        primaryStage.setTitle("9uaselkjv coiu");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private VBox makeTable(){
        LiteratureStockRegister literatureStockRegister = new LiteratureStockRegister("Data/inventory.json");
        VBox tableBox = new VBox();




        TableView literatureTableView = new TableView<>();
        ObservableList<Literature> manList = FXCollections.observableArrayList(literatureStockRegister.getStock());






        // common columns

        TableColumn<Literature, String> tableColumn = new TableColumn<>("Title");
        tableColumn.setId("title");
        tableColumn.setCellValueFactory(dat -> new SimpleStringProperty(dat.getValue().getTitle()));

        TableColumn<Literature, String> publisherColumn = new TableColumn<>("Publisher");
        publisherColumn.setId("publisher");
        publisherColumn.setCellValueFactory(dat -> new SimpleStringProperty(dat.getValue().getPublisher()));


        TableColumn<Literature, Float> priceColumn = new TableColumn<>("price");
        priceColumn.setId("price");
        priceColumn.setCellValueFactory(dat -> new SimpleFloatProperty(dat.getValue().getPrice()).asObject());


        TableColumn<Literature, Integer> stockColumn = new TableColumn<>("stock");
        stockColumn.setId("stock");
        stockColumn.setCellValueFactory(dat -> new SimpleIntegerProperty(dat.getValue().getNumberInStock()).asObject());

        // book columns

        TableColumn<StandaloneLiterature, Integer> editionColumn = new TableColumn<>("Edition");
        editionColumn.setId("edition");
        editionColumn.setCellValueFactory(dat -> new SimpleIntegerProperty(dat.getValue().getEdition()).asObject());

        TableColumn<StandaloneLiterature, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setId("author");
        authorColumn.setCellValueFactory(dat -> new SimpleStringProperty(dat.getValue().getAuthor()));

        TableColumn<StandaloneLiterature, String> pubDColumn = new TableColumn<>("publication date");
        pubDColumn.setId("pubD");
        pubDColumn.setCellValueFactory(dat -> new SimpleStringProperty(dat.getValue().getPublicationDate()));

        TableColumn<StandaloneLiterature, String> seriesColumn = new TableColumn<>("Series");
        seriesColumn.setId("series");
        seriesColumn.setCellValueFactory(dat -> new SimpleStringProperty(dat.getValue().getSeries()));

        // paper and magazine cols
        TableColumn<SerializedLiterature, Integer> yDColumn = new TableColumn<>("yearly Distributions");
        yDColumn.setId("YD");
        yDColumn.setCellValueFactory(dat -> new SimpleIntegerProperty(dat.getValue().getYearlyDistributions()).asObject());

        TableColumn<SerializedLiterature, String> genreColumn = new TableColumn<>("genre");
        genreColumn.setId("genre");
        genreColumn.setCellValueFactory(dat -> new SimpleStringProperty(dat.getValue().getGenre()));


        // so all the values wil initialy show
        FilteredList<Literature> filteredData = new FilteredList<>(manList, d -> true);

        ChoiceBox choseTblBox = new ChoiceBox();

        choseTblBox.setItems(FXCollections.observableArrayList("All litrature", "books", "magazines", "papers"));



        TextField searchBar = new TextField();


        searchBar.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate(literature -> {
                //literatureTableView.getItems().remove(literatureTableView.getItems().get(0));
                // if no serthing return all
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }



                Boolean suc = false;
                String qVal = newValue.toLowerCase().trim();
                ObservableList<TableColumn> sortColList = literatureTableView.getSortOrder();
                if (sortColList.size() > 0){
                    for (TableColumn tb: sortColList){
                        // if sorting over multiple categories
                        String id = tb.getId();


                        // The litrature columns
                        if (id.equals("title")){
                            suc = literature.getTitle().toLowerCase().contains(qVal);
                        }else if (id.equals("publisher")){
                            suc = literature.getPublisher().toLowerCase().contains(qVal);
                        } else if (id.equals("price")) {
                            suc = Float.toString(literature.getPrice()).equals(qVal);
                        } else if (id.equals("stock")) {
                            suc = Integer.toString(literature.getNumberInStock()).equals(qVal);
                        } else if (id.equals("edition")) {
                            suc = Integer.toString(((Book)literature).getEdition()).equals(qVal);
                        } else if (id.equals("author")) {
                            suc = ((Book)literature).getAuthor().equals(qVal);
                        } else if (id.equals("pubD")) {
                            suc = ((Book)literature).getPublicationDate().equals(qVal);
                        } else if (id.equals("series")) {
                            suc = ((Book)literature).getSeries().equals(qVal);
                        } else if (id.equals("YD")) {
                            suc = Integer.toString(((SerializedLiterature)literature).getYearlyDistributions()).equals(qVal);
                        } else if (id.equals("genre")) {
                            suc = ((SerializedLiterature)literature).getGenre().equals(qVal);
                        }


                    }
                } else {
                    // defaults to title if no column is selected
                    suc = literature.getTitle().toLowerCase().contains(qVal);
                }
                return suc;
            });
        });
        literatureTableView.setOnSort(event -> {
            // recalls the sertch on table update a tad hacky but it works
            String a = searchBar.textProperty().getValue();
            searchBar.textProperty().setValue(a + " ");
            searchBar.textProperty().setValue(a);
        });

        emptyFuncCall updateShownCols = () -> {
            switch (this.currentShownTable){
                case "all":
                    literatureTableView.getColumns().setAll(tableColumn, publisherColumn, priceColumn, stockColumn);

                    break;
                case "books":


                    literatureTableView.getColumns().removeAll(yDColumn, genreColumn);
                    literatureTableView.getColumns().addAll(authorColumn, editionColumn, pubDColumn, seriesColumn);
                    manList.setAll(literatureStockRegister.getStock());
                    manList.removeAll(manList.stream().filter(l -> !(l instanceof StandaloneLiterature)).collect(Collectors.toCollection(ArrayList::new)));
                    break;
                case "magazines":


                    literatureTableView.getColumns().removeAll(authorColumn, editionColumn, pubDColumn, seriesColumn);
                    if (!literatureTableView.getColumns().contains(genreColumn)){
                        literatureTableView.getColumns().addAll(yDColumn, genreColumn);
                    }
                    manList.setAll(literatureStockRegister.getStock());
                    manList.removeAll(manList.stream().filter(l -> !(l instanceof Magazine)).collect(Collectors.toCollection(ArrayList::new)));
                    break;
                case "papers":


                    literatureTableView.getColumns().removeAll(authorColumn, editionColumn, pubDColumn, seriesColumn);
                    if (!literatureTableView.getColumns().contains(genreColumn)){
                        literatureTableView.getColumns().addAll(yDColumn, genreColumn);
                    }
                    manList.setAll(literatureStockRegister.getStock());
                    manList.removeAll(manList.stream().filter(l -> !(l instanceof Paper)).collect(Collectors.toCollection(ArrayList::new)));
                    break;
            }
        };

        // selects the first element


        choseTblBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() == 0){
                    currentShownTable = "all";
                } else if (newValue.intValue() == 1){
                    currentShownTable = "books";
                } else if (newValue.intValue() == 2){
                    currentShownTable = "magazines";
                } else if (newValue.intValue() == 3){
                    currentShownTable = "papers";
                }

                updateShownCols.exe();
            }
        });

        choseTblBox.getSelectionModel().selectFirst();



        //literatureTableView.getItems().

        SortedList<Literature> sortedData = new SortedList<>(filteredData);


        sortedData.comparatorProperty().bind(literatureTableView.comparatorProperty());

        literatureTableView.setItems(sortedData);

        tableBox.getChildren().addAll(choseTblBox,searchBar, literatureTableView);

        //tableColumn


        return tableBox;
    }


    private MenuBar makeMenuBar(){

        MenuBar menuBar = new MenuBar();

        // The add-menu
        Menu addMenu = new Menu("Add");

        CustomMenuItem addBook = new CustomMenuItem(new Label("add new Book"));
        addBook.addEventHandler(new ActionEvent().getEventType(),event -> {
            this.showAddLiteratureWindow("book");
        });

        CustomMenuItem addBookSeries = new CustomMenuItem(new Label("add new addBookSeries"));
        addBookSeries.addEventHandler(new ActionEvent().getEventType(),event -> {
            System.out.println("BS");
        });

        CustomMenuItem addMagazine = new CustomMenuItem(new Label("add new magazine"));
        addMagazine.addEventHandler(new ActionEvent().getEventType(),event -> {
            System.out.println("mag");
        });

        CustomMenuItem addPaper = new CustomMenuItem(new Label("add new paper"));
        addPaper.addEventHandler(new ActionEvent().getEventType(),event -> {
            System.out.println("pap");
        });

        addMenu.getItems().addAll(addBook, addBookSeries, addMagazine, addPaper);

        // The edit-menu
        Menu editMenu = new Menu("Edit");

        CustomMenuItem editLiterature = new CustomMenuItem(new Label("Add book to series-"));
        editLiterature.addEventHandler(new ActionEvent().getEventType(),event -> {
            System.out.println("Add book to series");
        });

        CustomMenuItem removeLiterature = new CustomMenuItem(new Label("remove Literature"));
        removeLiterature.addEventHandler(new ActionEvent().getEventType(),event -> {
            System.out.println("rm itm");
        });

        editMenu.getItems().addAll(editLiterature, removeLiterature);


        // The user-menu
        Menu userMenu = new Menu("User");

        MenuItem tryLogInn = new MenuItem("Log inn"); // hide on login

        MenuItem addUser = new MenuItem("add new user"); // hide on logout
        addUser.addEventHandler(new ActionEvent().getEventType(),event -> {
            System.out.println("rm itm");
        });

        MenuItem tryLogOut = new MenuItem("log out"); // hide on logout

        ///  loggin/ loggout events ///

        Tooltip disabledAddItmTT = new Tooltip("Login to add items");
        Tooltip disabledEditItmTT = new Tooltip("Login to edit items");


        tooltipVisibilityFunc changeTooltipStatus = (CustomMenuItem m, Tooltip t) -> {
            if (isLoggedInn){
                Tooltip.uninstall(m.getContent(), t);
            } else {
                Tooltip.install(m.getContent(), t);
            }
        };

        this.updateMenuVisebility = () -> {
            tryLogInn.setVisible(!isLoggedInn);

            addUser.setVisible(isLoggedInn);
            tryLogOut.setVisible(isLoggedInn);

            addMenu.getItems().forEach(b -> {
                b.setDisable(!isLoggedInn);
                changeTooltipStatus.exe((CustomMenuItem) b, disabledAddItmTT);
            });

            editMenu.getItems().forEach(b -> {
                b.setDisable(!isLoggedInn);
                changeTooltipStatus.exe((CustomMenuItem) b, disabledEditItmTT);
            });


        };
        updateMenuVisebility.exe();

        tryLogInn.addEventHandler(new ActionEvent().getEventType(),event -> {
            this.tryLoggin();
        });

        tryLogOut.addEventHandler(new ActionEvent().getEventType(),event -> {
            this.tryLoggout();
        });


        userMenu.getItems().addAll(tryLogInn, tryLogOut, addUser);

        menuBar.getMenus().addAll(addMenu, editMenu, userMenu);
        return menuBar;

    }

    private void showAddLiteratureWindow(String typeToAdd){
        /*
         if (id.equals("title")){
                            suc = literature.getTitle().toLowerCase().contains(qVal);
                        }else if (id.equals("publisher")){
                            suc = literature.getPublisher().toLowerCase().contains(qVal);
                        } else if (id.equals("price")) {
                            suc = Float.toString(literature.getPrice()).equals(qVal);
                        } else if (id.equals("stock")) {
                            suc = Integer.toString(literature.getNumberInStock()).equals(qVal);
                        } else if (id.equals("edition")) {
                            suc = Integer.toString(((Book)literature).getEdition()).equals(qVal);
                        } else if (id.equals("author")) {
                            suc = ((Book)literature).getAuthor().equals(qVal);
                        } else if (id.equals("pubD")) {
                            suc = ((Book)literature).getPublicationDate().equals(qVal);
                        } else if (id.equals("series")) {
                            suc = ((Book)literature).getSeries().equals(qVal);
                        } else if (id.equals("YD")) {
                            suc = Integer.toString(((SerializedLiterature)literature).getYearlyDistributions()).equals(qVal);
                        } else if (id.equals("genre")) {
                            suc = ((SerializedLiterature)literature).getGenre().equals(qVal);
         */

        String title;
        String publisher;
        Float price;
        Integer stock;

        int edition;
        String author;
        String pubD;

        int yearlyDist;
        String Genre;

        Text titletxt = new Text(" s"); // treng ikke mer en selve klassan
        TextField titleFieald = new TextField();


        Text missingInputtMessage = new Text();
        Button submitt = new Button("submit");



        StackPane secondaryLayout = new StackPane();

        DatePicker dpTest = new DatePicker();

        dpTest.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = dpTest.getValue();
                System.err.println("Selected date: " + date);
            }
        });

        secondaryLayout.getChildren().add(dpTest);

        Scene secondScene = new Scene(secondaryLayout, 230, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Add New " + typeToAdd);
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();
    }

    private void tryLoggin(){

        Text uNameP = new Text("inputt Username: Uname=a Pass=a");
        TextField uNameF = new TextField();
        Text passwordP = new Text("inputt Password:");
        PasswordField passwordF = new PasswordField();
        VBox inp = new VBox(uNameP,uNameF, passwordP, passwordF);

        Button loginBtn = new Button("loggin");
        Button cansleBtn = new Button("cansle");
        HBox btn = new HBox(cansleBtn, loginBtn);

        Text messageBox = new Text();

        BorderPane secondaryLayout = new BorderPane();
        secondaryLayout.setTop(messageBox);
        secondaryLayout.setCenter(inp);
        secondaryLayout.setBottom(btn);

        Scene secondScene = new Scene(secondaryLayout, 230, 150);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Login");
        newWindow.setScene(secondScene);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(primaryStage);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();


        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userName = uNameF.textProperty().getValue();
                String userPass = passwordF.textProperty().getValue();

                if (userName.length() == 0 || userPass.length() == 0){
                    messageBox.setText("missing inputt");
                } else {
                    isLoggedInn = loggin.isValidLoggin(userName, userPass);
                    if (isLoggedInn){
                        newWindow.close();
                        updateMenuVisebility.exe();
                    } else {
                        messageBox.setText("wrong username or password");
                    }
                }
            }
        });

        cansleBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newWindow.close();
            }
        });
    }

    private void tryLoggout(){
        this.isLoggedInn = false;
        this.updateMenuVisebility.exe();
    }

    // helpers (burde flyttes)
}
