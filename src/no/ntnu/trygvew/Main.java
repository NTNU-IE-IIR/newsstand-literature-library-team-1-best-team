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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import no.ntnu.trygvew.litratureTypes.*;
import no.ntnu.trygvew.messingAround.UserLoggin;
import no.ntnu.trygvew.messingAround.encryption.Password;


import javax.swing.text.StyledEditorKit;
import javax.xml.bind.Unmarshaller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * quality of life interface to simplefy and reduce dupication
 */
@FunctionalInterface
interface emptyFuncCall{
    void exe();
}

/**
 * Function intf to ubdate the visibility of the menu
 */
@FunctionalInterface
interface tooltipVisibilityFunc{
    void exe(CustomMenuItem cmi, Tooltip t);
}

/**
 * Function intf to validate different knd of inputts
 */
@FunctionalInterface
interface inputtsAreValid{
    Boolean exe();
}


/**
 * The main app
 */
public class Main extends Application {
    private boolean isLoggedInn = false;
    private String currentShownTable = "all";
    private LiteratureStockRegister literatureStockRegister = new LiteratureStockRegister("Data/inventory.json");

    private Stage primaryStage;

    private TableView mainTable;

    private UserLoggin loggin = new UserLoggin();

    private emptyFuncCall updateMenuVisebility;
    private emptyFuncCall updateShownCols;

    //private ArrayList<>

    /**
     * starts the gui of the application
     */
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


        primaryStage.setTitle("Litrature registre");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }

    /**
     * Starts the application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * makes the main litrature table with the integrated sertch bar
     * @return a vbox with the table and sertch bar
     */
    private VBox makeTable(){

        VBox tableBox = new VBox();




        TableView literatureTableView = new TableView<>();
        this.mainTable = literatureTableView;
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
                            suc = Integer.toString(((Book)literature).getEdition()).contains(qVal);
                        } else if (id.equals("author")) {
                            suc = ((Book)literature).getAuthor().contains(qVal);
                        } else if (id.equals("pubD")) {
                            suc = ((Book)literature).getPublicationDate().contains(qVal);
                        } else if (id.equals("series")) {
                            suc = ((Book)literature).getSeries().contains(qVal);
                        } else if (id.equals("YD")) {
                            suc = Integer.toString(((SerializedLiterature)literature).getYearlyDistributions()).contains(qVal);
                        } else if (id.equals("genre")) {
                            suc = ((SerializedLiterature)literature).getGenre().contains(qVal);
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

        updateShownCols = () -> {
            switch (this.currentShownTable){
                case "all":
                    literatureTableView.getColumns().setAll(tableColumn, publisherColumn, priceColumn, stockColumn);
                    manList.setAll(literatureStockRegister.getStock());

                    break;
                case "books":


                    literatureTableView.getColumns().setAll(tableColumn, publisherColumn, priceColumn, stockColumn);

                    manList.setAll(literatureStockRegister.getStock());
                    manList.removeAll(manList.stream().filter(l -> !(l instanceof StandaloneLiterature)).collect(Collectors.toCollection(ArrayList::new)));

                    literatureTableView.getColumns().addAll(authorColumn, editionColumn, pubDColumn, seriesColumn);

                    break;
                case "magazines":


                    literatureTableView.getColumns().setAll(tableColumn, publisherColumn, priceColumn, stockColumn);

                    manList.setAll(literatureStockRegister.getStock());
                    manList.removeAll(manList.stream().filter(l -> !(l instanceof Magazine)).collect(Collectors.toCollection(ArrayList::new)));

                    //if (!literatureTableView.getColumns().contains(genreColumn)){
                        literatureTableView.getColumns().addAll(yDColumn, genreColumn);


                    break;
                case "papers":


                    literatureTableView.getColumns().setAll(tableColumn, publisherColumn, priceColumn, stockColumn);

                    manList.setAll(literatureStockRegister.getStock());
                    manList.removeAll(manList.stream().filter(l -> !(l instanceof Paper)).collect(Collectors.toCollection(ArrayList::new)));


                    literatureTableView.getColumns().addAll(yDColumn, genreColumn);


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


    /**
     * makes the gui menubar
     * @return the gui menubar
     */
    private MenuBar makeMenuBar(){

        MenuBar menuBar = new MenuBar();

        // The add-menu
        Menu addMenu = new Menu("Add");

        CustomMenuItem addBook = new CustomMenuItem(new Label("add new Book"));
        addBook.addEventHandler(new ActionEvent().getEventType(),event -> {
            this.showAddLiteratureWindow("Book");
        });

        //CustomMenuItem addBookSeries = new CustomMenuItem(new Label("add new addBookSeries"));
        //addBookSeries.addEventHandler(new ActionEvent().getEventType(),event -> {
        //    showAddLiteratureWindow("BookSeries");
        //});

        CustomMenuItem addMagazine = new CustomMenuItem(new Label("add new magazine"));
        addMagazine.addEventHandler(new ActionEvent().getEventType(),event -> {
            showAddLiteratureWindow("magazine");
        });

        CustomMenuItem addPaper = new CustomMenuItem(new Label("add new paper"));
        addPaper.addEventHandler(new ActionEvent().getEventType(),event -> {
            showAddLiteratureWindow("paper");
        });

        addMenu.getItems().addAll(addBook, addMagazine, addPaper);

        // The edit-menu
        Menu editMenu = new Menu("Edit");

        CustomMenuItem addBookToSeries = new CustomMenuItem(new Label("Add book to series-"));
        addBookToSeries.addEventHandler(new ActionEvent().getEventType(),event -> {
            try {
                Literature selItm = (Literature) mainTable.getSelectionModel().getSelectedItem();
                if (selItm instanceof Book){
                    this.setBookSeries((Book) selItm);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("add book to series");
                    alert.setHeaderText(null);
                    alert.setContentText("Selected itm is not a book");

                    alert.showAndWait();
                }
            } catch (Exception e){e.printStackTrace();}
        });

        CustomMenuItem removeLiterature = new CustomMenuItem(new Label("remove Literature"));
        removeLiterature.addEventHandler(new ActionEvent().getEventType(),event -> {
            Literature lit = (Literature) mainTable.getSelectionModel().getSelectedItem();
            literatureStockRegister.removeLiterature(lit);
            this.updateShownCols.exe();
        });

        editMenu.getItems().addAll(addBookToSeries, removeLiterature);


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

    /**
     * validates that there is som kind of inputt in the inputt fieald if not notefies the user
     * @param itemInput the inputt bar whre som inputt is provided
     * @param itemText the static text fealt where the user notification message is diplayed
     * @return true i there atre som inputt false if there are none
     */
    private Boolean validateInput(TextField itemInput, Text itemText){
        boolean valid = false;
        String inpText = itemInput.textProperty().getValue();

        String displayTxt = itemText.getText();

        // is any inputt chek
        String noInputMessage = "No inputt provided";
        if (inpText.length() > 0){
            valid = true;
            itemText.setText(displayTxt.replaceAll(noInputMessage, ""));
            itemText.setFill(Color.BLACK);
        } else{
            valid = false;

            if (!displayTxt.contains(noInputMessage)){
                itemText.setText(displayTxt + noInputMessage);
                itemText.setFill(Color.RED);
            }
        }
        return valid;
    }


    /**
     * makes and displays the add litrature window
     * @param typeToAdd what kint of litraturre to add
     */
    private void showAddLiteratureWindow(String typeToAdd){

        Stage addLittWindow = new Stage();
        VBox inpBox = new VBox();
        Button submitButton = new Button("submit");
        Button closeButton = new Button("close");




        // all litrature types
        Text titleText = new Text("Inputt title: ");
        TextField titleFieald = new TextField();

        Text publisherText = new Text("Inputt publisher: ");
        TextField publisherFieald = new TextField();

        Text priceText = new Text("Inputt price: ");
        TextField priceFieald = new TextField();
        makeTextFiealdFloatOnly(priceFieald);

        Text stockText = new Text("Inputt stock: ");
        TextField stockFieald = new TextField();
        makeTextFiealdIntOnly(stockFieald);

        inpBox.getChildren().addAll(titleText, titleFieald, publisherText, publisherFieald,priceText, priceFieald, stockText, stockFieald);
        inputtsAreValid validBase = () -> {
          boolean v1 = this.validateInput(titleFieald, titleText);
          boolean v2 = this.validateInput(publisherFieald, publisherText);
          boolean v3 = this.validateInput(priceFieald, priceText);
          boolean v4 = this.validateInput(stockFieald, stockText);
          return v1 && v2 && v3 && v4;
        };


        // books and book series
        Text editionText = new Text("Inputt edition: ");
        TextField editionFieald = new TextField();
        makeTextFiealdIntOnly(editionFieald);

        Text authorText = new Text("Inputt author: ");
        TextField authorFieald = new TextField();

        Text pubDText = new Text("Inputt publication date: ");
        DatePicker datePicker = new DatePicker();


        inputtsAreValid validBook = () -> {
            boolean v1 = this.validateInput(editionFieald, editionText);
            boolean v2 = this.validateInput(authorFieald, authorText);
            boolean v3 = false;
            if (datePicker.getValue() != null){
                v3 = true;
            }
            return v3 && v2 && v1;
        };

        // magazines and news papers
        Text yearlyDistText = new Text("Inputt yearlyDist: ");
        TextField yearlyDistFieald = new TextField();
        makeTextFiealdIntOnly(yearlyDistFieald);

        Text genreText = new Text("Inputt genre: ");
        TextField genreFieald = new TextField();

        inputtsAreValid validserial = () -> {
            boolean v1 = this.validateInput(yearlyDistFieald, yearlyDistText);
            boolean v2 = this.validateInput(genreFieald, genreText);
            return v2 && v1;
        };

        if ("Book".equals(typeToAdd)){
            inpBox.getChildren().addAll(editionText, editionFieald, authorText, authorFieald, pubDText, datePicker);
        }  else if ("magazine paper".contains(typeToAdd)){
            inpBox.getChildren().addAll(yearlyDistText, yearlyDistFieald,genreText, genreFieald );
        }

        submitButton.setOnAction(event -> {
            if (validBase.exe()){
                String title = titleFieald.getText();
                String publisher = publisherFieald.getText();
                Float price = Float.parseFloat(priceFieald.getText());
                Integer stock = Integer.parseInt(stockFieald.getText());

                if ("Book".equals(typeToAdd)){
                    if (validBook.exe()){
                        int edition = Integer.parseInt(editionFieald.getText());
                        String author = authorFieald.getText();
                        String pubDate = java.sql.Date.valueOf(datePicker.getValue()).toString();

                        Book newBook = new Book(0, title, publisher, stock, price, edition, author, pubDate);
                        literatureStockRegister.addLiterature(newBook);
                        updateShownCols.exe();
                        addLittWindow.close();
                    }

                }  else if ("magazine paper".contains(typeToAdd)){
                    if (validserial.exe()){
                        int yearlyDist = Integer.parseInt(yearlyDistFieald.getText());
                        String genre = genreFieald.getText();

                        Literature newlit;
                        if ("magazine".equals(typeToAdd)){
                            newlit = new Magazine(0, title, publisher, stock, price, yearlyDist, genre);
                        } else {
                            newlit = new Paper(0, title, publisher, stock, price, yearlyDist, genre);
                        }

                        literatureStockRegister.addLiterature(newlit);
                        updateShownCols.exe();
                        addLittWindow.close();
                    }

                }
            }
        });


        closeButton.setOnAction(event -> {
            addLittWindow.close();
        });

        HBox btnBox = new HBox(submitButton, closeButton);
        BorderPane winLayout = new BorderPane();

        winLayout.setCenter(inpBox);
        winLayout.setBottom(btnBox);

        Scene secondScene = new Scene(winLayout, 230, 400);


        // New window (Stage)

        addLittWindow.setTitle("Add New " + typeToAdd);
        addLittWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        addLittWindow.setX(primaryStage.getX() + 200);
        addLittWindow.setY(primaryStage.getY() + 100);

        addLittWindow.show();
    }

    /**
     * shows the set book series window
     * @param selectedBook the book to select series for
     */
    private void setBookSeries(Book selectedBook){
        Text serNtxt = new Text("Inputt series name:");
        TextField serNfe = new TextField();
        VBox inp = new VBox(serNtxt,serNfe);

        Button submittBtn = new Button("submitt");
        Button cansleBtn = new Button("cansle");
        HBox btn = new HBox(cansleBtn, submittBtn);

        Text messageBox = new Text();

        BorderPane secondaryLayout = new BorderPane();
        secondaryLayout.setTop(messageBox);
        secondaryLayout.setCenter(inp);
        secondaryLayout.setBottom(btn);

        Scene secondScene = new Scene(secondaryLayout, 230, 150);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Add new series");
        newWindow.setScene(secondScene);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(primaryStage);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();


        submittBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String seriesName = serNfe.textProperty().getValue();

                if (seriesName.length() == 0){
                    messageBox.setText("missing inputt");
                } else {
                    literatureStockRegister.setBookSeries(selectedBook, seriesName);
                    updateShownCols.exe();
                    newWindow.close();
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

    /**
     * shows the loggin window for the application
     */
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

    /**
     * loggs the user out
     */
    private void tryLoggout(){
        this.isLoggedInn = false;
        this.updateMenuVisebility.exe();
    }

    /**
     * maks the provided texfeald only accsept ints as inputt
     * @param target the text fealt to make int only
     */
    private void makeTextFiealdIntOnly(TextField target){
        target.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    target.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    /**
     * maks the provided texfeald only accsept numbers with a single comma as inputt
     * @param target the textfeald to modify
     */
    private void makeTextFiealdFloatOnly(TextField target){
        target.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) { // not super stable on rege but this shold only acsept 0-7 dgt alternativly . and 0-4 dgt
                    target.setText(oldValue);
                }
            }
        });
    }

}
