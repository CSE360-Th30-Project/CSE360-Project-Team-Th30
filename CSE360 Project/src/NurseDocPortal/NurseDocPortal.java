package NurseDocPortal;
	

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import java.text.SimpleDateFormat;
import java.util.Date;

//public void File Reader() {
//    public void start(Stage primaryStage) {
//        VBox root = new VBox(10);
//        root.setPadding(new Insets(20));
//
//        Button browseButton = new Button("Browse Patient Directory");
//
//        VBox fileListContainer = new VBox(5);
//
//        browseButton.setOnAction(e -> {
//            String pathToPatientFolder = "C:\\Users\\aksha\\OneDrive\\Documents\\Java Programs\\NursePortal\\" + PATIENT_FOLDER_NAME; // Modify this with the actual path
//            File patientFolder = new File(pathToPatientFolder);
//            fileListContainer.getChildren().clear();
//            if (patientFolder.exists() && patientFolder.isDirectory()) {
//                File[] visitFiles = patientFolder.listFiles();
//                if (visitFiles != null) {
//                    for (File visitFile : visitFiles) {
//                        if (visitFile.isFile() && visitFile.getName().endsWith(".txt")) {
//                        	Button fileButton = new Button(visitFile.getName());
//                            fileListContainer.getChildren().add(fileButton);
//                        }
//                    }
//                }
//            } else {
//                fileListContainer.getChildren().add(new Label("Patient folder not found"));
//            }
//        });
//
//        root.getChildren().addAll(browseButton, fileListContainer);
//
//        Scene scene = new Scene(root, 400, 200);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Doctor's Office App");
//        primaryStage.show();
//    }
//}
public class NurseDocPortal extends Application {
	
	public String uid;
	
	public NurseDocPortal(String userInput) {
		// TODO Auto-generated constructor stub
		this.uid = userInput;
	}


	@Override
	public void start(Stage primaryStage) {
		
		//V1
//		Label l1 = new Label("Eat:");
		Button n1 = new Button("Search Patient");
		Button n2 = new Button("CT Scan Tech View");
		Button n3 = new Button("Doctors View");
		
		 // Set preferred width and height for all buttons
        double preferredWidth = 150;
        double preferredHeight = 50;
        n1.setPrefWidth(preferredWidth);
        n1.setPrefHeight(preferredHeight);
        n2.setPrefWidth(preferredWidth);
        n2.setPrefHeight(preferredHeight);
        n3.setPrefWidth(preferredWidth);
        n3.setPrefHeight(preferredHeight);

		
		//Layout
		VBox v1 = new VBox();
		v1.setPadding(new Insets(60,0,0,0));
		v1.setSpacing(50);
		v1.getChildren().addAll(n1, n2, n3);
		
		HBox h = new HBox();
		 h.setPadding(new Insets(10));
//		h.setPadding(new Insets(0, 0, 0, 0));
		h.getChildren().addAll(v1);
		 h.setAlignment(Pos.CENTER);
		
		
		//TL
		Label TL = new Label("Welcome to Heart Health Imaging and Recording System");
		TL.setStyle("-fx-font-size: 12px;");
		TL.setPadding(new Insets(0, 0, 0, 40));
		StackPane.setAlignment(TL, Pos.CENTER);
		
		//Dynamic Size
		VBox.setVgrow(v1, Priority.ALWAYS);
		
        // Set up BorderPane
        BorderPane bp = new BorderPane();
        bp.setTop(TL);
        bp.setCenter(h);
        
        //Scene
		Scene scene = new Scene(bp, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Heart Health Imaging and Recording System");
		primaryStage.show();
		
		
		// Event handling for Button 1
        n1.setOnAction(e -> {
            // Navigate to Class1 when Button 1 is clicked
//            PatientIntake pi = new PatientIntake();
//            pi.start(new Stage());
//        	dp DP = new dp();
        	PatientSearch DP = new PatientSearch();
        	DP.start(new Stage());
        });

        // Event handling for Button 2
        n2.setOnAction(e -> {
            // Navigate to Class2 when Button 2 is clicked
            CT ct = new CT();
            ct.start(new Stage());
        });

        // Event handling for Button 3
        n3.setOnAction(e -> {
            // Navigate to Class3 when Button 3 is clicked
//            PatientID pid = new PatientID();
//            pid.start(new Stage());
            HealthHistoryDoctor hhd = new HealthHistoryDoctor("accounts/" + uid);
        	hhd.start(new Stage());
//        	HealthInformationForm hif = new HealthInformationForm();
//        	hif.start(new Stage());
        
        });
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


class PatientSearch extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        TextField nameField = new TextField();
        nameField.setPromptText("Enter patient's name");

        TextField birthdayField = new TextField();
        birthdayField.setPromptText("Enter patient's birthday (MM-DD-YYYY)");

        Button browseButton = new Button("Browse Patient Directory");

        Label resultLabel = new Label();

        browseButton.setOnAction(e -> {
        	String patientFolderName = nameField.getText() + "_" + birthdayField.getText();
        	PatientFolderOpen pfo = new PatientFolderOpen(patientFolderName);
        	pfo.start(new Stage());
        	
        });

        root.getChildren().addAll(nameField, birthdayField, browseButton, resultLabel);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Doctor's Office App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
class PatientFolderOpen extends Application {

	

    private String PATIENT_FOLDER_NAME; 
    
    public PatientFolderOpen(String PATIENT_FOLDER_NAME) {
    	this.PATIENT_FOLDER_NAME = PATIENT_FOLDER_NAME;
    }
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        Button browseButton = new Button("Browse Patient Directory");
        Button newVisitButton = new Button("New Visit");

        VBox folderListContainer = new VBox(5);
        VBox fileListContainer = new VBox(5);
        
        newVisitButton.setOnAction(e -> {
        	 String pathToPatientFolder = "accounts/" + PATIENT_FOLDER_NAME +"/"; 
            newVisit.createFolder();
        });
        browseButton.setOnAction(e -> {
            String pathToPatientFolder = "C:\\Users\\aksha\\OneDrive\\Documents\\Java Programs\\NursePortal\\" + PATIENT_FOLDER_NAME; // Modify this with the actual path
            File patientFolder = new File(pathToPatientFolder);
            folderListContainer.getChildren().clear();
            fileListContainer.getChildren().clear();
            if (patientFolder.exists() && patientFolder.isDirectory()) {
                File[] subFolders = patientFolder.listFiles(File::isDirectory);
                if (subFolders != null) {
                    for (File subFolder : subFolders) {
                        Button folderButton = new Button(subFolder.getName());
                        folderButton.setOnAction(event -> {
                            // Perform actions when folder button is clicked
                            fileOpen fo = new fileOpen(subFolder);
                            fo.start(new Stage());
                        });
                        folderListContainer.getChildren().add(folderButton);
                    }
                }
            } else {
                folderListContainer.getChildren().add(new Label("Patient folder not found"));
            }
        });

        root.getChildren().addAll(newVisitButton, browseButton, folderListContainer, fileListContainer);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Doctor's Office App");
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

class newVisit{

    public static void createFolder(String patientName){
        // Specify the directory path where you want to create the patient folder
        String directoryPath = "accounts/" + patientName + "/visits/";

        // Get current date in MM-DD-YYYY format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String currentDate = dateFormat.format(new Date());

        // Create folder with current date in the specified directory
        File folder = new File(directoryPath + File.separator + currentDate + File.separator);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder created: " + folder.getAbsolutePath());
            } else {
                System.err.println("Failed to create folder!");
                return;
            }
        } else {
            System.err.println("Folder already exists: " + folder.getAbsolutePath());
            return;
        }

        // Create text files inside the folder
        String[] fileNames = {"healthConcerns.txt", "immunizationRecords.txt", "knownAllergies.txt",
                "previousHealthIssues.txt", "previousMedication.txt", "recommendations.txt", "vitals.txt"};
        for (String fileName : fileNames) {
            File file = new File(folder, fileName);
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.err.println("File already exists: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                System.err.println("Failed to create file: " + file.getAbsolutePath());
            }
        }
    }
}

class fileOpen extends Application{
	File folder;
	public fileOpen(File folder) {
		this.folder = folder;
	}
	
	
	 public void start(Stage primaryStage) {
		 
		 File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
		 VBox fileListContainer = new VBox(5);
		 if (files != null) {
	            for (File file : files) {
	                Button fileButton = new Button(file.getName());
	                fileButton.setOnAction(event -> {
                        // Perform actions when folder button is clicked
                        if(file.getName().equals("vitals.txt")) {
                        	readVitals rv = new readVitals(folder.getAbsolutePath());
                        	rv.start(new Stage());
                        }else {
                        	HealthHistoryDoctor hhn = new HealthHistoryDoctor(folder.getAbsolutePath());
                        	hhn.start(new Stage());
                        }
                    });
	                fileListContainer.getChildren().add(fileButton);
	            }
		 } 
		 VBox root = new VBox(10);
	     root.setPadding(new Insets(20));
	     root.getChildren().addAll(fileListContainer);
	     
	     Scene scene = new Scene(root, 400, 300);
	     primaryStage.setScene(scene);
	     primaryStage.setTitle("Doctor's Office App");
	     primaryStage.show();
	     
	 }
}

class readVitals extends Application{

		
		 String folderPath = "";
	     
	     String Age = "";
	     String Height = "";
	     String Weight = "";
	     String BodyTemperature = "";
	     String BloodPressure = "";
	     
	     public readVitals(String folderPath) {
	    	 	folderPath = folderPath + "\\vitals.txt";
		        this.folderPath = folderPath;
		}
	     
//	     public String getName() {
//	    	 String[] parts = folderPath.split("_");
//	         return parts[0];
//	     }
		
	    public void start(Stage primaryStage) {

	    	 try (BufferedReader reader = new BufferedReader(new FileReader(folderPath))) {
	             // Read each line from the file
	             Age = reader.readLine();
	             Height = reader.readLine();
	             Weight = reader.readLine();
	             BodyTemperature = reader.readLine();
	             BloodPressure = reader.readLine();
	             
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	    	 
	    	 


//	        // Labels and TextFields for HBox h2
//	        Label l2 = new Label("The total Agatston CAC score:");
//	        TextField t2 = new TextField(totalAgatstonCACScore);
//	        t2.setEditable(false);
//
//	        HBox h2 = new HBox(50, l2, t2);

	        // HBoxes for labels l4 to l8
	        TextField t3 = new TextField(Age);
	        t3.setEditable(false);
	        HBox h4 = new HBox(new Label("Age:"), t3);
	        h4.setSpacing(10);
	        
	        TextField t4 = new TextField(Height);
	        t4.setEditable(false);
	        HBox h5 = new HBox(new Label("Height:"), t4);
	        h5.setSpacing(10);
	        
	        TextField t5 = new TextField(Weight);
	        t5.setEditable(false);
	        HBox h6 = new HBox(new Label("Weight:"), t5);
	        h6.setSpacing(10);
	        
	        TextField t6 = new TextField(BodyTemperature);
	        t6.setEditable(false);
	        HBox h7 = new HBox(new Label("Body Temperature:"), t6);
	        h7.setSpacing(10);
	        
	        TextField t7 = new TextField(BloodPressure);
	        t7.setEditable(false);
	        HBox h8 = new HBox(new Label("Blood Pressure:"), t7);
	        h8.setSpacing(10);

	        // VBox to stack HBoxes vertically
	        VBox v = new VBox(20, h4, h5, h6, h7, h8);
	        v.setAlignment(Pos.CENTER);
	        v.setPadding(new Insets(10));

	        // Title label
	        Label TL = new Label("Vitals");
	        TL.setStyle("-fx-font-size: 12px;");
	        TL.setPadding(new Insets(0, 0, 0, 150));

	        // Set up BorderPane
	        BorderPane bp = new BorderPane();
	        bp.setTop(TL);
	        bp.setCenter(v);
	        bp.setAlignment(v, Pos.CENTER);

	        // Set up the scene
	        Scene scene = new Scene(bp, 400, 400);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Vitals");
	        primaryStage.show();
	}
	public static void main(String[] args) {
        launch(args);
   }
}

class HealthHistoryNurse extends Application {
	
	String folderPath = ""; 

	public HealthHistoryNurse(String folderPath) {
        this.folderPath = folderPath;
        initializeFilePaths(); 
    }

    
    private void initializeFilePaths() {
        FILE_PATHS = new String[]{
                folderPath + "\\previousHealthIssues.txt",
                folderPath + "\\healthConcerns.txt",
                folderPath + "\\recommendations.txt",
                folderPath + "\\knownAllergies.txt",
                folderPath + "\\previousMedication.txt",
                folderPath + "\\immunizationRecords.txt"
        };
    }

    
    private String[] FILE_PATHS;
    

    @Override
    public void start(Stage primaryStage) {
    	System.out.println(FILE_PATHS[0]);
        VBox vbox1 = new VBox(10);
        VBox vbox2 = new VBox(10);
        VBox vbox3 = new VBox(10);
        VBox vbox4 = new VBox(10);
        VBox vbox5 = new VBox(10);
        VBox vbox6 = new VBox(10);

        String[] labels = {"Previous Health Issues", "Health Concerns", "Recommendations", "Known Allergies", "Previous Medication", "Immunization Records"};

        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i]);
            TextArea textArea = new TextArea();
            textArea.setPrefRowCount(19);
            textArea.setPrefColumnCount(40);
            textArea.setWrapText(true);

            VBox vbox;
            if (i < 2) {
                vbox = vbox1;
            } else if (i < 4) {
                vbox = vbox2;
            } else if (i < 6) {
                vbox = vbox3;
            } else if (i < 8) {
                vbox = vbox4;
            } else if (i < 10) {
                vbox = vbox5;
            } else {
                vbox = vbox6;
            }

            vbox.getChildren().addAll(label, textArea);

            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATHS[i]))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        HBox hbox1 = new HBox(10, vbox1, vbox2, vbox3);
        hbox1.setPadding(new Insets(10));
        HBox hbox2 = new HBox(10, vbox4, vbox5, vbox6);
        hbox2.setPadding(new Insets(10));

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            for (int i = 0; i < FILE_PATHS.length; i++) {
                VBox vbox = i < 3 ? vbox1 : i < 6 ? vbox2 : i < 9 ? vbox3 : i < 12 ? vbox4 : i < 15 ? vbox5 : vbox6;
                TextArea textArea = (TextArea) vbox.getChildren().get(1);
                writeToTextFile(FILE_PATHS[i], textArea.getText());
            }
        });

        BorderPane root = new BorderPane();
        root.setTop(hbox1);
        root.setCenter(hbox2);
        root.setBottom(submitButton);
       BorderPane.setMargin(submitButton, new Insets(20, 200, 20 ,200));

        Scene scene = new Scene(root, 1300, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Health History Form");
        primaryStage.show();
    }

    private void writeToTextFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}

class HealthHistoryDoctor extends Application {
	
	String folderPath = ""; 

	public HealthHistoryDoctor(String folderPath) {
        this.folderPath = folderPath;
        initializeFilePaths(); // Call method to initialize FILE_PATHS
    }

    // Method to initialize FILE_PATHS
    private void initializeFilePaths() {
        FILE_PATHS = new String[]{
                folderPath + "\\previousHealthIssues.txt",
                folderPath + "\\healthConcerns.txt",
                folderPath + "\\recommendations.txt",
                folderPath + "\\knownAllergies.txt",
                folderPath + "\\previousMedication.txt",
                folderPath + "\\immunizationRecords.txt"
        };
    }

    // Array to hold file paths
    private String[] FILE_PATHS;
    

    @Override
    public void start(Stage primaryStage) {
    	System.out.println(FILE_PATHS[0]);
        VBox vbox1 = new VBox(10);
        VBox vbox2 = new VBox(10);
        VBox vbox3 = new VBox(10);
        VBox vbox4 = new VBox(10);
        VBox vbox5 = new VBox(10);
        VBox vbox6 = new VBox(10);

        String[] labels = {"Previous Health Issues", "Health Concerns", "Recommendations", "Known Allergies", "Previous Medication", "Immunization Records"};

        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i]);
            TextArea textArea = new TextArea();
            textArea.setPrefRowCount(19);
            textArea.setPrefColumnCount(40);
            textArea.setWrapText(true);

            VBox vbox;
            if (i < 2) {
                vbox = vbox1;
            } else if (i < 4) {
                vbox = vbox2;
            } else if (i < 6) {
                vbox = vbox3;
            } else if (i < 8) {
                vbox = vbox4;
            } else if (i < 10) {
                vbox = vbox5;
            } else {
                vbox = vbox6;
            }

            vbox.getChildren().addAll(label, textArea);

            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATHS[i]))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        HBox hbox1 = new HBox(10, vbox1, vbox2, vbox3);
        hbox1.setPadding(new Insets(10));
        HBox hbox2 = new HBox(10, vbox4, vbox5, vbox6);
        hbox2.setPadding(new Insets(10));

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            for (int i = 0; i < FILE_PATHS.length; i++) {
                VBox vbox = i < 3 ? vbox1 : i < 6 ? vbox2 : i < 9 ? vbox3 : i < 12 ? vbox4 : i < 15 ? vbox5 : vbox6;
                TextArea textArea = (TextArea) vbox.getChildren().get(1);
                writeToTextFile(FILE_PATHS[i], textArea.getText());
            }
        });
        submitButton.setPrefSize(300, 40); 

        BorderPane root = new BorderPane();
        root.setTop(hbox1);
        root.setCenter(hbox2);
        root.setBottom(submitButton);
        BorderPane.setMargin(submitButton, new Insets(10, 10, 40, 500));

        Scene scene = new Scene(root, 1300, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Health History Form");
        primaryStage.show();
    }

    private void writeToTextFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class  PatientIntake extends Application{
	public void start(Stage primaryStage) {
		
		//V1
		Label l1 = new Label("First Name:");
		Label l2 = new Label("Last Name:");
		Label l3 = new Label("Email:");
		Label l4 = new Label("Phone Number:");
		Label l5 = new Label("Health History:");
		Label l6 = new Label("Insurance ID:");
		
		//Layout
		VBox v1 = new VBox();
		v1.setPadding(new Insets(0,0,0,0));
		v1.setSpacing(50);
		v1.getChildren().addAll(l1, l2, l3, l4, l5, l6);

		//V2
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		TextField t4 = new TextField();
		TextField t5 = new TextField();
		TextField t6 = new TextField();
		Button sb = new Button("Submit");
		
		//Layout
		VBox v2 = new VBox();
		v2.setPadding(new Insets(0,0,0,0));
		v2.setSpacing(42);
		v2.getChildren().addAll(t1, t2, t3, t4, t5, t6, sb);
		
		HBox h = new HBox();
		h.setPadding(new Insets(10));
//		h.setPadding(new Insets(0, 0, 0, 0));
		h.setSpacing(20);
		h.getChildren().addAll(v1, v2);
		h.setAlignment(Pos.CENTER);
		
		
		//TL
		Label TL = new Label("Patient Intake Form");
		TL.setStyle("-fx-font-size: 12px;");
		TL.setPadding(new Insets(0, 0, 0, 150));
		 StackPane.setAlignment(TL, Pos.CENTER);
		
		//Dynamic Size
		VBox.setVgrow(v1, Priority.ALWAYS);
		
        // Set up BorderPane
        BorderPane bp = new BorderPane();
        bp.setTop(TL);
        bp.setCenter(h);
        
        //Scene
		Scene scene = new Scene(bp, 400, 480);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Patient Intake Form");
		primaryStage.show();	
		
		sb.setOnAction(e -> {
            String firstName = t1.getText();
            String lastName = t2.getText();
            String email = t3.getText();
            String phoneNumber = t4.getText();
            String healthHistory = t5.getText();
            String insuranceID = t6.getText();

            // Generate unique patient ID
            String patientID = String.valueOf((int) (Math.random() * 90000) + 10000);

            // Create patient information string
            String patientInfo = "Patient ID: " + patientID + "\n" +
                    "First Name: " + firstName + "\n" +
                    "Last Name: " + lastName + "\n" +
                    "Email: " + email + "\n" +
                    "Phone Number: " + phoneNumber + "\n" +
                    "Health History: " + healthHistory + "\n" +
                    "Insurance ID: " + insuranceID;

            // Write patient information to file
            try {
                FileWriter writer = new FileWriter(patientID + "_PatientInfo.txt");
                writer.write(patientInfo);
                writer.close();
                System.out.println("Patient information saved successfully.");
                System.out.println("Patient ID: " + patientID);
            } catch (IOException ex) {
                System.out.println("An error occurred while saving patient information.");
                ex.printStackTrace();
            }
        });

		
	}
	
	public static void main(String[] args) {
        launch(args);
   }


}

class CT extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Labels and TextFields for HBox h1
        Label l1 = new Label("Patient ID:");
        TextField t1 = new TextField();
        	
        HBox h1 = new HBox(150, l1, t1);

        // Labels and TextFields for HBox h2
        Label l2 = new Label("The total Agatston CAC score:");
        TextField t2 = new TextField();

        HBox h2 = new HBox(50, l2, t2);

        // Labels for HBox h3
        Label l3 = new Label("Vessel level Agatson CAC score");
        HBox h3 = new HBox(l3);
        h3.setSpacing(50);

        // HBoxes for labels l4 to l8
        TextField t3 = new TextField();
        HBox h4 = new HBox(new Label("LM:"), t3);
        h4.setSpacing(10);
        
        TextField t4 = new TextField();
        HBox h5 = new HBox(new Label("LAD:"), t4);
        h5.setSpacing(10);
        
        TextField t5 = new TextField();
        HBox h6 = new HBox(new Label("LCX:"), t5);
        h6.setSpacing(10);
        
        TextField t6 = new TextField();
        HBox h7 = new HBox(new Label("RCA:"), t6);
        h7.setSpacing(10);
        
        TextField t7 = new TextField();
        HBox h8 = new HBox(new Label("PDA:"), t7);
        h8.setSpacing(10);
        
        Button submitButton = new Button("Submit");
        

        // VBox to stack HBoxes vertically
        VBox v = new VBox(20, h1, h2, h3, h4, h5, h6, h7, h8, submitButton);
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(10));

        // Title label
        Label TL = new Label("CT Scan Technician View");
        TL.setStyle("-fx-font-size: 12px;");
        TL.setPadding(new Insets(0, 0, 0, 150));

        // Set up BorderPane
        BorderPane bp = new BorderPane();
        bp.setTop(TL);
        bp.setCenter(v);

        // Set up the scene
        Scene scene = new Scene(bp, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CT Scan Technician View");
        primaryStage.show();
        
        submitButton.setOnAction(e -> {
            String patientID = t1.getText();
            String totalAgatstonCACScore = t2.getText();
            String lm = t3.getText();
            String lad = t4.getText();
            String lcx = t5.getText();
            String rca = t6.getText();
            String pda = t7.getText();
            
            // Check if any field is empty
            if (patientID.isEmpty() || totalAgatstonCACScore.isEmpty() || lm.isEmpty() || lad.isEmpty() || lcx.isEmpty() || rca.isEmpty() || pda.isEmpty()) {
                System.out.println("Alert: Please fill in all fields.");
                return;
            }
            
            // Save CT scan data to file
            saveCTScanData(patientID, totalAgatstonCACScore, lm, lad, lcx, rca, pda);
        });
    }
    
    private void saveCTScanData(String patientID, String totalAgatstonCACScore, String lm, String lad, String lcx, String rca, String pda) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(patientID + "CTResults.txt"))) {
			writer.write("" + patientID + "\n");
			writer.write("" + totalAgatstonCACScore + "\n");
			writer.write("" + lm + "\n");
			writer.write("" + lad + "\n");
			writer.write("" + lcx + "\n");
			writer.write("" + rca + "\n");
			writer.write("" + pda + "\n");
			System.out.println("CT scan data saved successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while saving CT scan data.");
			e.printStackTrace();
		}
	}

    public static void main(String[] args) {
        launch(args);
    }
}

//Example Class3


class PatientID extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Label and TextField for patient ID input
        Label idLabel = new Label("Enter Patient ID:");
        TextField idField = new TextField();

        // Submit button
        Button submitButton = new Button("Submit");

        // Layout
        VBox v = new VBox(20, idLabel, idField, submitButton);
        v.setPrefWidth(300);
        v.setPrefHeight(200);
        v.setStyle("-fx-padding: 10px;");

        // Title label
        Label title = new Label("Patient View");

        // Set up BorderPane
        BorderPane bp = new BorderPane();
        bp.setTop(title);
        bp.setCenter(v);

        // Set up the scene
        Scene scene = new Scene(bp, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Patient View");
        primaryStage.show();
        
        

        // Event handling for Submit button
        submitButton.setOnAction(e -> {
            String patientID = idField.getText();
            if (!patientID.isEmpty()) {
            	 PatientView pv = new PatientView(patientID);
                 pv.start(new Stage());
            }
        });
    }

    // Method to retrieve patient data from file and display
    private void displayPatientData(String patientID) {
        String filename = patientID + "CTResults.txt";
       
        int totalAgatstonCACScore = 0;
        int lm = 0;
        int lad = 0;
        int lcx = 0;
        int rca = 0;
        int pda = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Read each line from the file
            totalAgatstonCACScore = Integer.parseInt(reader.readLine());
            lm = Integer.parseInt(reader.readLine());
            lad = Integer.parseInt(reader.readLine());
            lcx = Integer.parseInt(reader.readLine());
            rca = Integer.parseInt(reader.readLine());
            pda = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("totalAgatstonCACScore: " + totalAgatstonCACScore);
        System.out.println("lm: " + lm);
        System.out.println("lad: " + lad);
        System.out.println("lcx: " + lcx);
        System.out.println("rca: " + rca);
        System.out.println("pda: " + pda);
        
    }

    // Method to display patient information
    private void showPatientInfo(int totalAgatstonCACScore, int lm, int lad, int lcx, int rca, int pda) {
        // Implement logic to display patient information in your application
        System.out.println("Total Agatston CAC score: " + totalAgatstonCACScore);
        System.out.println("LM: " + lm);
        System.out.println("LAD: " + lad);
        System.out.println("LCX: " + lcx);
        System.out.println("RCA: " + rca);
        System.out.println("PDA: " + pda);
    }

    // Method to show alert message
    private void showAlert(String message) {
        // Implement alert message display here
        System.out.println("Alert: " + message);
    }
    
   

    public static void main(String[] args) {
        launch(args);
    }
}

class PatientView extends Application{
	
	private String patientID;
	 String filename = patientID + "CTResults.txt";
     
     String totalAgatstonCACScore = "";
     String lm = "";
     String lad = "";
     String lcx = "";
     String rca = "";
     String pda = "";
     String name = "";
	 
	public PatientView(String patientID) {
	        this.patientID = patientID;
	}
	
	
    public void start(Stage primaryStage) {

    	 try (BufferedReader reader = new BufferedReader(new FileReader(patientID + "CTResults.txt"))) {
             // Read each line from the file
             totalAgatstonCACScore = reader.readLine();
             lm = reader.readLine();
             lad = reader.readLine();
             lcx = reader.readLine();
             rca = reader.readLine();
             pda = reader.readLine();
         } catch (IOException e) {
             e.printStackTrace();
         }
    	 
    	 try (BufferedReader reader = new BufferedReader(new FileReader(patientID + "_PatientInfo.txt"))) {
             // Read each line from the file
             reader.readLine();
             name = reader.readLine();
         } catch (IOException e) {
             e.printStackTrace();
         }
    	 
        // Labels and TextFields for HBox h2
        Label l2 = new Label("The total Agatston CAC score:");
        TextField t2 = new TextField(totalAgatstonCACScore);
        t2.setEditable(false);

        HBox h2 = new HBox(50, l2, t2);

        // HBoxes for labels l4 to l8
        TextField t3 = new TextField(lm);
        t3.setEditable(false);
        HBox h4 = new HBox(new Label("LM:"), t3);
        h4.setSpacing(10);
        
        TextField t4 = new TextField(lad);
        t4.setEditable(false);
        HBox h5 = new HBox(new Label("LAD:"), t4);
        h5.setSpacing(10);
        
        TextField t5 = new TextField(lcx);
        t5.setEditable(false);
        HBox h6 = new HBox(new Label("LCX:"), t5);
        h6.setSpacing(10);
        
        TextField t6 = new TextField(rca);
        t6.setEditable(false);
        HBox h7 = new HBox(new Label("RCA:"), t6);
        h7.setSpacing(10);
        
        TextField t7 = new TextField(pda);
        t7.setEditable(false);
        HBox h8 = new HBox(new Label("PDA:"), t7);
        h8.setSpacing(10);

        // VBox to stack HBoxes vertically
        VBox v = new VBox(20, h2, h4, h5, h6, h7, h8);
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(10));

        // Title label
        Label TL = new Label("Hello Patient " + name.substring(12));
        TL.setStyle("-fx-font-size: 12px;");
        TL.setPadding(new Insets(0, 0, 0, 150));

        // Set up BorderPane
        BorderPane bp = new BorderPane();
        bp.setTop(TL);
        bp.setCenter(v);

        // Set up the scene
        Scene scene = new Scene(bp, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Patient View");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
