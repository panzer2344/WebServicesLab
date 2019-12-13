package com.lab.ws.client;

import com.lab.ws.LibraryService;
import com.lab.ws.dto.Book;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import javax.swing.event.HyperlinkEvent;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

public class HelloClient extends Application {
    /*public static void main(String[] args) throws MalformedURLException {
//        URL url = new URL("http://localhost:1986/wss/hello?wsdl");
//        QName qName = new QName("http://server.ws.lab.com/", "HelloWebServiceImplService");
//
//        Service service = Service.create(url, qName);
//        HelloWebService helloWebService = service.getPort(HelloWebService.class);
//
//        System.out.println(helloWebService.sayHi("me"));

        //RadioButton radioButton = new RadioButton("userType");


        //JFrame mainFrame = new JFrame();
        //mainFrame.add()

    }*/

    private RadioButton adminRb;
    private RadioButton clientRb;

    private LibraryService clientLibrary;
    private LibraryService adminLibrary;

    public HelloClient() throws MalformedURLException {
        clientLibrary = getService("library/client", "LibraryClientService");
        adminLibrary = getService("library/admin", "LibraryAdminService");
    }

    private LibraryService getService(String postfixUrl, String serviceName) throws MalformedURLException {
        URL url = new URL("http://localhost:1986/ws/" + postfixUrl + "?wsdl");
        QName qName = new QName("http://service.server.ws.lab.com/", serviceName + "Service");
        Service service = Service.create(url, qName);
        LibraryService libraryService = service.getPort(LibraryService.class);

        return libraryService;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ToggleGroup clientTypeTG = new ToggleGroup();

        RadioButton adminRb = new RadioButton("Admin"){{ setToggleGroup(clientTypeTG); }};
        RadioButton clientRb = new RadioButton("Client"){{ setToggleGroup(clientTypeTG); }};

        FlowPane rbPane = new FlowPane(Orientation.VERTICAL, adminRb, clientRb);
        rbPane.setPrefWrapLength(70);


        Button addBookBtn   = new Button("AddBook");
        Button addCopyBtn   = new Button("AddCopy");
        Button getAllBtn    = new Button("GetAll");
        Button takeBtn      = new Button("Take");
        Button returnBtn    = new Button("Return");

        FlowPane btnPane = new FlowPane(addBookBtn, addCopyBtn, getAllBtn, takeBtn, returnBtn);
        FlowPane topPane = new FlowPane(rbPane, btnPane);

        TextField codeTF = new TextField("code"){{ setWidth(100); }};
        Label codeLabel = new Label("Code:");

        FlowPane codeFP = new FlowPane(codeLabel, codeTF);

        TextField bookNameTF = new TextField("bookName"){{ setWidth(100); }};
        Label bookNameLabel = new Label("BookName:");

        FlowPane bookNameFP = new FlowPane(bookNameLabel, bookNameTF);

        TextArea authorsTA = new TextArea("authors"){{ setMaxSize(200, 100); }};
        Label authorsLabel = new Label("Authors:");

        FlowPane authorsFP = new FlowPane(authorsLabel, authorsTA);

        TextField publisherNameTF = new TextField("publisherName"){{ setWidth(100); }};
        Label publisherNameLabel = new Label("PublisherName:");

        FlowPane publisherNameFP = new FlowPane(publisherNameLabel, publisherNameTF);

        TextField publishYearTF = new TextField("publishYear"){{ setWidth(100); }};
        Label publishYearLabel = new Label("PublishYear:");

        FlowPane publishYearFP = new FlowPane(publishYearLabel, publishYearTF);

        FlowPane inputPane = new FlowPane(bookNameFP, codeFP, authorsFP, publishYearFP, publisherNameFP);

        TextArea resultTA = new TextArea("result"){{ setMaxSize(250, 150); }};
        Label resultLabel = new Label("Result:");

        FlowPane resultFP = new FlowPane(Orientation.VERTICAL, resultLabel, resultTA);

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(inputPane);
        mainPane.setTop(topPane);
        mainPane.setRight(resultFP);

        Scene scene = new Scene(mainPane);

        primaryStage.setScene(scene);
        primaryStage.setHeight(400);
        primaryStage.setWidth(900);
        primaryStage.setTitle("Client");
        primaryStage.show();


        //final ClientTypeContainer clientTypeContainer = new ClientTypeContainer(ClientType.CLIENT);

        ValueContainer<LibraryService> libraryServiceContainer = new ValueContainer<>(clientLibrary);

        clientRb.setOnAction(t -> libraryServiceContainer.value = clientLibrary );
        adminRb.setOnAction(t -> libraryServiceContainer.value = adminLibrary );

        getAllBtn.setOnAction(t -> {
            LinkedList<Book> books = libraryServiceContainer.value.getAllBooks();
            StringBuilder stringBuilder = new StringBuilder();
            books.forEach(b -> stringBuilder.append(b).append("\n"));
            resultTA.setText(stringBuilder.toString());
        });

        addBookBtn.setOnAction(t -> {
            libraryServiceContainer.value.addNewBook(
                    new BigInteger(codeTF.getText()),
                    new LinkedList<>(Arrays.asList(authorsTA.getText().split("\n"))),
                    Integer.valueOf(publishYearTF.getText()),
                    publisherNameTF.getText(),
                    bookNameTF.getText());
        });

        addCopyBtn.setOnAction(t -> {
            libraryServiceContainer.value.addNewCopies(
                    new BigInteger(codeTF.getText()),
                    1);
        });

        takeBtn.setOnAction(t -> {
            libraryServiceContainer.value.getBook(
                    new BigInteger(codeTF.getText()));
        });

        returnBtn.setOnAction(t -> {
            libraryServiceContainer.value.returnBook(
                    new BigInteger(codeTF.getText()));
        });

    }

    private class ValueContainer<T> {
        public T value;
        public ValueContainer (T value) { this.value = value; }
    }



//    private class ClientTypeContainer {
//        public ClientType clientType;
//        public ClientTypeContainer(ClientType clientType) { this.clientType = clientType; }
//    }
//
//    private enum ClientType { CLIENT, ADMIN };
}
