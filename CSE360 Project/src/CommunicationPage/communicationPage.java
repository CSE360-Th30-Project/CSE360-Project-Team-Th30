package CommunicationPage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class communicationPage extends Application {
	private VBox chatMessages = new VBox();
    private List<String> messages = new ArrayList<>();
    private ListView<String> userList = new ListView<>();
    private TextField searchField = new TextField();
    private Button searchButton = new Button("Search");
    private String enteredUsername;
    private VBox root;

    @Override
    public void start(Stage primaryStage) {
        // Sample list of users
    	root = new VBox();
    	
        TextField usernameField = new TextField();
        TextField passwordField = new TextField();
        Button loginButton = new Button("Login");
    	
        loginButton.setOnAction(event -> {
            enteredUsername = usernameField.getText().trim();
            String enteredPassword = passwordField.getText().trim();
            if (login(enteredUsername, enteredPassword)) {
                // Successful login, remove login section
                VBox root = (VBox) primaryStage.getScene().getRoot();
                root.getChildren().remove(0); // Remove login box
                root.setPadding(new Insets(10)); // Adjust padding
                root.setSpacing(10); // Adjust spacing
                loadUserIds();
            } else {
                // Incorrect credentials
                System.out.println("Invalid username or password");
            }
        });
        VBox loginBox = new VBox(10, new Label("Username:"), usernameField, new Label("Password:"), passwordField, loginButton);
        loginBox.setPadding(new Insets(10));

        // VBox for list of users
        VBox leftBox = new VBox(userList);
        VBox.setVgrow(userList, Priority.ALWAYS);
        leftBox.setPrefWidth(200);
        
        
     
        HBox searchBox = new HBox(searchField, searchButton);
        searchBox.setSpacing(5);
        searchBox.setPadding(new Insets(10));
        
        searchButton.setOnAction(event -> {
            String patientId = searchField.getText().trim();
            if (!patientId.isEmpty()) {
                String patientAccountPath = "src/accounts/" + patientId;
                String patientChatPath = "src/accounts/" + enteredUsername + "/chat/" +
                patientId + ".txt";
                if (Files.exists(Paths.get(patientAccountPath))) {
                    // Patient exists
                    if (!Files.exists(Paths.get(patientChatPath))) {
                        // Chat file does not exist, create a new one
                        try {
                            Files.createFile(Paths.get(patientChatPath));
                            Files.createFile(Paths.get("src/accounts/" + patientId + "/chat/"
                            		+ enteredUsername + ".txt"));
                            loadUserIds();
                            System.out.println("Chat file created for patient: " + patientId);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Chat file already exists for patient: " + patientId);
                    }
                } else {
                    System.out.println("Patient not found: " + patientId);
                }
            }
        });

        // Scroll pane for chat messages
        ScrollPane scrollPane = new ScrollPane(chatMessages);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // VBox for chat messages
        VBox rightBox = new VBox(scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        rightBox.setPrefWidth(300);
        rightBox.setPadding(new Insets(0, 0, 10, 0));
        
        TextField messageField = new TextField();
        Button sendButton = new Button("Send");     
        
        sendButton.setOnAction(event -> {
            String selectedUser = userList.getSelectionModel().getSelectedItem();
            if (selectedUser != null && !messageField.getText().trim().isEmpty()) {
                sendMessage(selectedUser, messageField.getText().trim());
                messageField.clear();
            }
        });
        

        VBox chatBox = new VBox(rightBox, new HBox(messageField, sendButton));
        VBox.setVgrow(chatBox.getChildren().get(0), Priority.ALWAYS); 


        // Combine left and right box
        HBox root = new HBox(leftBox, chatBox);
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        
        
        userList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Clear previous messages
            chatMessages.getChildren().clear();
            // Load messages for selected user
            loadMessagesForUser(newValue);
           
        });
   
        primaryStage.setScene(new Scene(new VBox(loginBox, searchBox, root), 600, 400));
        primaryStage.setTitle("Chat Application");
        primaryStage.show();
        
        loadUserIds();
    }
    
    private void loadUserIds() {
        if (userList == null) {
            userList = new ListView<>(); // Initialize user list if not already initialized
            root.getChildren().add(userList); // Add user list to the root layout
        } else {
            userList.getItems().clear(); // Clear existing items from the user list
        }
        File accountsDir = new File("src/accounts/" + enteredUsername + "/chat");
        if (accountsDir.exists() && accountsDir.isDirectory()) {
        	System.out.println("Hello");
            File[] accountFiles = accountsDir.listFiles((dir, name) -> name.endsWith(".txt"));
            if (accountFiles != null) {
                for (File accountFile : accountFiles) {
                	System.out.println(accountFile.toString());
                    String userId = accountFile.getName().replace(".txt", "");
                    userList.getItems().add(userId);
                }
            }
        }
    }
   
    private void loadMessagesForUser(String userId) {
        String userFilePath = "src/accounts/" + enteredUsername + "/chat/" + userId + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(userFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
            	if (line.equals("")) {
     
            	} else {
                    String[] lists = line.split("\\@\\$\\%\\@\\%\\%\\$\\#\\@\\#\\$\\%");
                    System.out.println("IN HERE");
                    for(String a : lists) {
                    	System.out.println(a);
                    }

                    if (lists[1].equals(enteredUsername)) {
                        Label label = new Label(lists[0]);
                        label.setWrapText(true);
                        label.setStyle("-fx-background-color: #007aff; -fx-text-fill: white");
                        StackPane stackPane = new StackPane(label);
                        stackPane.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
                        chatMessages.getChildren().add(stackPane);
                    } else {
                        Label label = new Label(lists[0]);
                        label.setWrapText(true);
                        label.setStyle("-fx-background-color: #d4d4d4; -fx-text-fill: black;");
                        StackPane stackPane = new StackPane(label);
                        stackPane.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                        chatMessages.getChildren().add(stackPane);
                    }
                   
                    
            		
            	}

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void sendMessage(String userId, String message) {
        String userFilePath = "src/accounts/" + enteredUsername + "/chat/" + userId + ".txt";
        String message2 = message;
        message = message.concat("@$%@%%$#@#$%" + enteredUsername);
        try (FileWriter writer = new FileWriter(userFilePath, true)) {
            writer.write(message + "\n");
            // Add the message to the chat interface
            Label label = new Label(message2);
            label.setWrapText(true);
            StackPane stackPane = new StackPane(label);
            stackPane.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
            chatMessages.getChildren().add(stackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String senderFilePath = "src/accounts/" + userId + "/chat/" + enteredUsername + ".txt";
        try (FileWriter writer = new FileWriter(senderFilePath, true)) {
        	writer.write(message + "\n");
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
    }
    
    private boolean login(String username, String password) {
        String userAccountPath = "src/accounts/" + username;
        if (Files.exists(Paths.get(userAccountPath))) {
            // User exists, try to read password from the user's account directory
            String passwordFilePath = userAccountPath + "/patientInfo.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(passwordFilePath))) {
                String storedPassword = br.readLine();
                String fixedPassword = storedPassword.substring(10, storedPassword.length() -1 );
                System.out.println(fixedPassword);
                return storedPassword != null && fixedPassword.equals(password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false; // User does not exist or incorrect password
    }



    public static void main(String[] args) {
        launch(args);
    }


}
    
   