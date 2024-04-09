package loginPage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class loginPage extends Application {
    private String pPass = "12345";
    private VBox root = new VBox();
    private Scene originalScene;
    private Stage primaryStage;
    private String userInput = "";
    private String passInput = "";
    Font timesNewRomanFont = Font.font("Times New Roman", 20);

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
    	
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Health Co. Login Page");

        root.setPadding(new Insets(200, 200, 250, 200));
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        TextField user = new TextField();
        user.setPromptText("User ID");
        user.setStyle("-fx-font-size: 1.25em;");

        TextField password = new TextField();
        password.setPromptText("Password");
        password.setStyle("-fx-font-size: 1.25em;");

        String currentDirectory = System.getProperty("user.dir");
        String imageName = "HealthCoLogo.jpg";
        String imagePath = "file:" + currentDirectory + "/src/resources/" + imageName;
        Image logoImage = new Image(imagePath);
        ImageView logo = new ImageView(logoImage);
        logo.setPreserveRatio(true);
        logo.setFitWidth(225);
        logo.setFitHeight(225);

        imageName = "lock.png";
        imagePath = "file:" + currentDirectory + "/src/resources/" + imageName;
        Image lockImage = new Image(imagePath);
        ImageView lock = new ImageView(lockImage);
        lock.setPreserveRatio(true);
        lock.setFitWidth(35);
        lock.setFitHeight(35);

        imageName = "profile.png";
        imagePath = "file:" + currentDirectory + "/src/resources/" + imageName;
        Image profileImage = new Image(imagePath);
        ImageView profile = new ImageView(profileImage);
        profile.setPreserveRatio(true);
        profile.setFitWidth(35);
        profile.setFitHeight(35);

        HBox userPane = new HBox(5);
        HBox passPane = new HBox(5);
        userPane.setAlignment(Pos.CENTER);
        passPane.setAlignment(Pos.CENTER);
        VBox.setMargin(userPane, new Insets(0, 15, 0, 0));
        VBox.setMargin(passPane, new Insets(0, 15, 0, 0));

        userPane.getChildren().addAll(profile, user);
        passPane.getChildren().addAll(lock, password);

        Label respondString = new Label("");
        respondString.setAlignment(Pos.CENTER);

        Button logButton = new Button("Login");
        logButton.setAlignment(Pos.CENTER);

        Label newPatient = new Label("New Patient? Create an account below");
        newPatient.setAlignment(Pos.CENTER);

        Button createButton = new Button("Create Account");
        logButton.setAlignment(Pos.CENTER);

        logButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userInput = user.getText();
                passInput = password.getText();
                respondString.setText("");
                if(userInput.isBlank() || passInput.isBlank()) {
                	respondString.setText("Please fill out all fields.");
                }
                else if (userInput.charAt(0) == 'P' && passInput.equals(pPass)) {
                    buttonResponse('P');
                    respondString.setText("");
                    user.setText("");
                    password.setText("");
                } 
                else if (userInput.charAt(0) == 'D' && passInput.equals(pPass)) {
                	buttonResponse('D');
                	respondString.setText("");
                	user.setText("");
                    password.setText("");
                    
                } 
                else if (userInput.charAt(0) == 'N' && passInput.equals(pPass)) {
                	buttonResponse('N');
                	respondString.setText("");
                	user.setText("");
                    password.setText("");
                    
                } 
                else {
                    respondString.setText("Incorrect username or password. Please try again.");
                    password.setText("");
                }
            }
        });

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	createAccount();
            	respondString.setText("");
            	user.setText("");
                password.setText("");
            }
        });

        root.setStyle("-fx-background-color: #fefffa");
        root.getChildren().addAll(logo, userPane, passPane, respondString, logButton, newPatient, createButton);

        originalScene = new Scene(root, 800, 600);
        primaryStage.setScene(originalScene);
        primaryStage.show();
    }

    private void buttonResponse(char role) {
    	
        VBox overlayLayout = new VBox();
        overlayLayout.setStyle("-fx-background-color: white;");
        overlayLayout.setAlignment(Pos.CENTER);
        overlayLayout.setSpacing(20);

        Label viewLabel = new Label("");
        if(role == 'P') {
        	viewLabel.setText("Implementation of Patient View");
        }
        else if(role == 'D') {
        	viewLabel.setText("Implementation of Doctor View");
        }
        else if(role == 'N') {
        	viewLabel.setText("Implementation of Nurse View");
        }
        
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
        	
        	primaryStage.setTitle("Health Co. Login Page");
            primaryStage.setScene(originalScene);
            
        });

        overlayLayout.getChildren().addAll(viewLabel, backButton);

        Scene overlayScene = new Scene(overlayLayout, 800, 600);

        primaryStage.setScene(overlayScene);
        primaryStage.setTitle("Placeholder View");
        primaryStage.show();
    }
    
    private void createAccount() {
    	
    	VBox createAccountLayout = new VBox ();
    	Label backButton = new Label("<--Back");
        backButton.setFont(Font.font("Arial", 20));
        backButton.setStyle("-fx-underline: true; -fx-text-fill: blue;");
        backButton.setAlignment(Pos.TOP_LEFT);
        
        backButton.setOnMouseClicked(e -> {
        	
        	primaryStage.setTitle("Health Co. Login Page");
            primaryStage.setScene(originalScene);
            
        });
    	
    	HBox labelBox = new HBox();
    	Label pIntakeLabel = new Label("Account Creation: ");
    	labelBox.getChildren().addAll(pIntakeLabel);
    	labelBox.setPadding(new Insets(0, 0, 0, 250));
    	pIntakeLabel.setAlignment(Pos.CENTER);
    	pIntakeLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");

    	HBox fNameBox = new HBox();
    	fNameBox.setSpacing(75);
    	Label fnLabel = new Label("First Name: ");
    	fnLabel.setAlignment(Pos.CENTER_LEFT);
    	fnLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField fnInput = new TextField();
    	fnInput.setPrefWidth(300);
    	fnInput.setPrefHeight(25);
    	fNameBox.getChildren().addAll(fnLabel, fnInput);
    	fNameBox.setAlignment(Pos.CENTER_LEFT);

    	HBox lNameBox = new HBox();
    	lNameBox.setSpacing(75);
    	Label lnLabel = new Label("Last Name: ");
    	lnLabel.setAlignment(Pos.CENTER_LEFT);
    	lnLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField lnInput = new TextField();
    	lnInput.setPrefWidth(300);
    	lnInput.setPrefHeight(25);
    	lNameBox.getChildren().addAll(lnLabel, lnInput);
    	lNameBox.setAlignment(Pos.CENTER_LEFT);
    	
    	HBox bdBox = new HBox();
    	bdBox.setSpacing(78);
    	Label bdLabel = new Label("Birthday:    ");
    	bdLabel.setAlignment(Pos.CENTER_LEFT);
    	bdLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField bdInput = new TextField();
    	bdInput.setPrefWidth(300);
    	bdInput.setPrefHeight(25);
    	bdInput.setPromptText("MM/DD/YYYY");
    	bdBox.getChildren().addAll(bdLabel, bdInput);
    	bdBox.setAlignment(Pos.CENTER_LEFT);
    	
    	//Phone Box Input HBox Stack
    	HBox passBox = new HBox();
    	passBox.setSpacing(40);
    	Label passLabel = new Label("Password:          ");
    	passLabel.setAlignment(Pos.CENTER_LEFT);
    	passLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField psInput = new TextField();
    	psInput.setPrefWidth(300);
    	psInput.setPrefHeight(25);
    	passBox.getChildren().addAll(passLabel, psInput);
    	passBox.setAlignment(Pos.CENTER_LEFT);
    	
    	Button submitButton = new Button("Submit");
    	submitButton.setPrefWidth(175);
    	submitButton.setPrefHeight(75);
    	submitButton.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(!fnInput.getText().isEmpty() && !lnInput.getText().isEmpty() && !bdInput.getText().isEmpty() && !psInput.getText().isEmpty()) {
            		if(bdInput.getText().length() == 10) {
            			
            	        String currentDirectory = System.getProperty("user.dir");
            	        String dirName = "P" + fnInput.getText() + lnInput.getText() + "_" + bdInput.getText().substring(0,2) + "-" + bdInput.getText().substring(3,5) + "-" + bdInput.getText().substring(6,10);  
            			String directoryPath = currentDirectory + File.separator + "src" + File.separator + "accounts" + File.separator + dirName;
            			String directoryPathChat = directoryPath + File.separator + "chat";
            			String fileName = "patientInfo.txt";
            			String filePath = directoryPath + File.separator + fileName;
            	        
            	        File directory = new File(directoryPath);
            	        if (!directory.exists()) {
            	            directory.mkdirs();
            	            File chats = new File(directoryPathChat);
            	            chats.mkdirs();
            	        }
            	        else {
            	        	pIntakeLabel.setText("Patient Already Exists.");
            	        }
            			
            	        File file = new File(directory, fileName);
            	        try {
            	            boolean success = file.createNewFile();
            	            if (success) {
            	                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            	                PrintWriter out = new PrintWriter(writer);
            	                out.print("Password: " + psInput.getText());
            	                out.close();
            	            } else {
            	                System.err.println("Failed to create file!");
            	            }
            	        } catch (IOException e) {
            	        }
            	        
            			primaryStage.setTitle("Health Co. Login Page");
                        primaryStage.setScene(originalScene);
            		}
            		else {
            			pIntakeLabel.setText("Birthday input incorrectly.");
            		}
            	}
            	else {
            		pIntakeLabel.setText("Please complete all fields.");
            	}
            }
    	});
    	
    	createAccountLayout.getChildren().addAll(backButton, labelBox, fNameBox, lNameBox, bdBox, passBox, submitButton);
    	createAccountLayout.setAlignment(Pos.TOP_LEFT);
    	createAccountLayout.setSpacing(50);
    	createAccountLayout.setPadding(new Insets(50));
    	
    	 Scene createAccountView = new Scene(createAccountLayout, 800, 600);

         primaryStage.setScene(createAccountView);
         primaryStage.setTitle("Create Account");
         primaryStage.show();
    }
}
