package patientPortal;
	


import javafx.application.Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import CommunicationPage.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class patientPortal extends Application {
	
	public String uid;

	public patientPortal(String uid) {
		this.uid = uid;
		
	}
	
    private BorderPane borderPane = new BorderPane();
    private Stage primaryStage = new Stage();
    private VBox mainContent = new VBox(40); // Increased vertical spacing for the main content

    private void showContent(String text) {
        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        Text contentText = new Text("");
        contentText.setFont(Font.font("Arial", 20));
        contentText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> borderPane.setCenter(mainContent));
        backButton.setFont(Font.font("Arial", 16));
        backButton.setStyle("-fx-background-color: #4A90E2; -fx-text-fill: white;");

        contentBox.getChildren().addAll(contentText, backButton);
        contentBox.setStyle("-fx-background-color: white; -fx-padding: 20;");
        contentBox.setMaxSize(300, 300);
        
        String txtFile = "";
        if(text.equals("Known Allergies")) {
        	txtFile = "knownAllergies.txt";
        }
        else if(text.equals("Prescribed Medication")) {
        	txtFile = "previousMedication.txt";
        }
        else if(text.equals("Immunizations")) {
        	txtFile = "immunizationRecords.txt";
        }
        else if(text.equals("Health Concerns")) {
        	txtFile = "healthConcerns.txt";
        }
        else if(text.equals("Doctor Recommendations")) {
        	txtFile = "recommendations.txt";
        }
        else if(text.equals("Physical Test Results")) {
        	//txtFile = "physicalTest.txt";
        }
        else if(text.equals("Previous Health Concerns")) {
        	txtFile = "previousHealthIssues.txt";
        }
        else {
        	System.out.print("You should never get here");
        }
        
        Path largerDirectoryPath = Paths.get(System.getProperty("user.dir") + File.separator + "accounts" + File.separator + uid + File.separator + "visits");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(largerDirectoryPath, Files::isDirectory)) {
            for (Path dir : stream) {
                String fileDir = dir + File.separator + txtFile;
                try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        contentText.setText(contentText.getText() + "\n" + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
                e.printStackTrace();
            }
        
        borderPane.setCenter(contentBox);
    }
    
    private void showVitalContent(String text) {
        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        Text contentText = new Text("");
        contentText.setFont(Font.font("Arial", 20));
        contentText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> borderPane.setCenter(mainContent));
        backButton.setFont(Font.font("Arial", 16));
        backButton.setStyle("-fx-background-color: #4A90E2; -fx-text-fill: white;");

        contentBox.getChildren().addAll(contentText, backButton);
        contentBox.setStyle("-fx-background-color: white; -fx-padding: 20;");
        contentBox.setMaxSize(300, 300);
        
        String txtFile = "vitals.txt";
        int lineNum = 0;
        if(text.equals("Body Temperature")) {
        	lineNum = 0;
        }
        else if(text.equals("Heart Rate")) {
        	lineNum = 1;
        }
        else if(text.equals("Blood Pressure")) {
        	lineNum = 2;
        }
        else if(text.equals("Respiration Rate")) {
        	lineNum = 3;
        }
        else {
        	System.out.print("You should never get here");
        }
        
        Path largerDirectoryPath = Paths.get(System.getProperty("user.dir") + File.separator + "accounts" + File.separator + uid + File.separator + "visits");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(largerDirectoryPath, Files::isDirectory)) {
            for (Path dir : stream) {
                String fileDir = dir + File.separator + txtFile;
                try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
                    String line;
                    for(int i = 0; i < lineNum; i++) {
                    	line = reader.readLine();
                    }
                    line = reader.readLine();
                    contentText.setText(contentText.getText() + "\n" + line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
                e.printStackTrace();
            }
        
        borderPane.setCenter(contentBox);
    }
    

    private Button createButton(String text, Color bgColor, Color textColor) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMinHeight(50);
        button.setFont(Font.font("Arial", 16));
        button.setStyle("-fx-background-color: #" + bgColor.toString().substring(2) + "; -fx-text-fill: #" + textColor.toString().substring(2) + ";");
        button.setOnAction(e -> showContent(text));
        return button;
    }
    
    private Button createVitalsButton(String text, Color bgColor, Color textColor) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMinHeight(50);
        button.setFont(Font.font("Arial", 16));
        button.setStyle("-fx-background-color: #" + bgColor.toString().substring(2) + "; -fx-text-fill: #" + textColor.toString().substring(2) + ";");
        button.setOnAction(e -> showVitalContent(text));
        return button;
    }



    private HBox createVitalSignsSection() {
        HBox vitalSigns = new HBox(10);
        vitalSigns.setAlignment(Pos.CENTER);
        vitalSigns.getChildren().addAll(
                createVitalsButton("Body Temperature", Color.web("#759FDA"), Color.WHITE),
                createVitalsButton("Heart Rate", Color.web("#559E83"), Color.WHITE),
                createVitalsButton("Blood Pressure", Color.web("#AE76A6"), Color.WHITE),
                createVitalsButton("Respiration Rate", Color.web("#4A90E2"), Color.WHITE)
        );
        return vitalSigns;
    }

    private VBox createOtherCategoriesSection() {
        VBox otherCategories = new VBox(30); // Increased spacing
        otherCategories.setAlignment(Pos.TOP_CENTER);
        otherCategories.getChildren().addAll(
                createButton("Known Allergies", Color.web("#4A90E2"), Color.WHITE),
                createButton("Prescribed Medication", Color.web("#759FDA"), Color.WHITE),
                createButton("Immunizations", Color.web("#559E83"), Color.WHITE),
                createButton("Health Concerns", Color.web("#AE76A6"), Color.WHITE),
                createButton("Physical Test Results", Color.web("#4A90E2"), Color.WHITE),
                createButton("Previous Health Concerns", Color.web("#bc98eb"), Color.WHITE)
        );
        return otherCategories;
    }

    @Override
    public void start(Stage primaryStage) {
        VBox leftMenu = createLeftMenu();
        HBox vitalSigns = createVitalSignsSection();
        VBox otherCategories = createOtherCategoriesSection();

        mainContent.getChildren().addAll(vitalSigns, otherCategories);
        mainContent.setAlignment(Pos.TOP_CENTER); // Align the main content to the top center
        borderPane.setLeft(leftMenu);
        borderPane.setCenter(mainContent);
        borderPane.setPadding(new Insets(50, 50, 10, 0)); // Insets: top, right, bottom, left
        
        Scene scene = new Scene(borderPane, 1024, 768);
        primaryStage.setTitle("Health App Interface");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createLeftMenu() {
        VBox leftMenu = new VBox(30); // Increased spacing
        leftMenu.setPadding(new Insets(15));
        leftMenu.getChildren().addAll(
                createButton("Doctor Recommendations", Color.web("#4A90E2"), Color.WHITE)
        );
        
        Button messageDoctor =  createButton("Message Doctor", Color.web("#4A90E2"), Color.WHITE);
        communicationPage chat = new communicationPage(this.uid);
        messageDoctor.setOnAction(e -> chat.start(primaryStage));
        leftMenu.getChildren().addAll(
        		messageDoctor);

        
        return leftMenu;
    }

}
