package patientPortal;
	


import javafx.application.Application;
import CommunicationPage.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        Text contentText = new Text(text + " content");
        contentText.setFont(Font.font("Arial", 20));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> borderPane.setCenter(mainContent));
        backButton.setFont(Font.font("Arial", 16));
        backButton.setStyle("-fx-background-color: #4A90E2; -fx-text-fill: white;");

        contentBox.getChildren().addAll(contentText, backButton);
        contentBox.setStyle("-fx-background-color: white; -fx-padding: 20;");
        contentBox.setMaxSize(300, 300);

        borderPane.setCenter(contentBox); // Replace the center content with the new content
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



    private HBox createVitalSignsSection() {
        HBox vitalSigns = new HBox(10);
        vitalSigns.setAlignment(Pos.CENTER);
        vitalSigns.getChildren().addAll(
                createButton("Body Temperature", Color.web("#759FDA"), Color.WHITE),
                createButton("Heart Rate", Color.web("#559E83"), Color.WHITE),
                createButton("Blood Pressure", Color.web("#AE76A6"), Color.WHITE),
                createButton("Respiration Rate", Color.web("#4A90E2"), Color.WHITE)
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
                createButton("Physical Test Results", Color.web("#4A90E2"), Color.WHITE)
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

        Scene scene = new Scene(borderPane, 1024, 768);
        primaryStage.setTitle("Health App Interface");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createLeftMenu() {
        VBox leftMenu = new VBox(30); // Increased spacing
        leftMenu.setPadding(new Insets(15));
        leftMenu.getChildren().addAll(
                createButton("Patient Info", Color.web("#4A90E2"), Color.WHITE),
                createButton("Doctor Recommendations", Color.web("#4A90E2"), Color.WHITE)
        );
        
        Button messageDoctor =  createButton("Message Doctor", Color.web("#4A90E2"), Color.WHITE);
        communicationPage chat = new communicationPage(this.uid);
        messageDoctor.setOnAction(e -> chat.start(primaryStage));
        leftMenu.getChildren().addAll(
        		messageDoctor,
        		createButton("Settings", Color.web("#4A90E2"), Color.WHITE)
        );

        
        return leftMenu;
    }

}
