package application;
	

import javafx.application.Application;
import java.util.HashMap;
import java.util.Map;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

<<<<<<< HEAD


public class Main extends Application {
=======
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


>>>>>>> branch 'main' of https://github.com/CSE360-Th30-Project/CSE360-Project-Team-Th30.git
	@Override
	public void start(Stage primaryStage) {
		
		//V1
//		Label l1 = new Label("Eat:");
		Button n1 = new Button("Search Patient");
		Button n3 = new Button("Doctors View");
		
		 // Set preferred width and height for all buttons
        double preferredWidth = 150;
        double preferredHeight = 50;
        n1.setPrefWidth(preferredWidth);
        n1.setPrefHeight(preferredHeight);
        n3.setPrefWidth(preferredWidth);
        n3.setPrefHeight(preferredHeight);

		
		//Layout
		VBox v1 = new VBox();
		v1.setPadding(new Insets(60,0,0,0));
		v1.setSpacing(50);
		v1.getChildren().addAll(n1, n3);
		
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


        // Event handling for Button 3
        n3.setOnAction(e -> {
<<<<<<< HEAD

//        	 HealthHistoryDoctor hhd = new  HealthHistoryDoctor();
//             hhd.start(new Stage());
=======
            // Navigate to Class3 when Button 3 is clicked
//            PatientID pid = new PatientID();
//            pid.start(new Stage());
            HealthHistoryDoctor hhd = new HealthHistoryDoctor("accounts/" + uid);
        	hhd.start(new Stage());
//        	HealthInformationForm hif = new HealthInformationForm();
//        	hif.start(new Stage());
>>>>>>> branch 'main' of https://github.com/CSE360-Th30-Project/CSE360-Project-Team-Th30.git
        
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
        	String patientFolderName = "P" + nameField.getText() + "_" + birthdayField.getText();
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
<<<<<<< HEAD
        	 String pathToPatientFolder = "accounts/" + PATIENT_FOLDER_NAME + File.separator + "visits/"; 
            newVisit.createFolder(pathToPatientFolder);
=======
        	 String pathToPatientFolder = "accounts/" + PATIENT_FOLDER_NAME +"/"; 
            newVisit.createFolder();
>>>>>>> branch 'main' of https://github.com/CSE360-Th30-Project/CSE360-Project-Team-Th30.git
        });
        browseButton.setOnAction(e -> {
            String pathToPatientFolder = "accounts/" + PATIENT_FOLDER_NAME + File.separator + "visits/"; // Modify this with the actual path
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

<<<<<<< HEAD
    public static void createFolder(String folderPath){
    	String directoryPath = folderPath;
=======
    public static void createFolder(String patientName){
        // Specify the directory path where you want to create the patient folder
        String directoryPath = "accounts/" + patientName + "/visits/";

>>>>>>> branch 'main' of https://github.com/CSE360-Th30-Project/CSE360-Project-Team-Th30.git
        // Get current date in MM-DD-YYYY format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String currentDate = dateFormat.format(new Date());

        // Create folder with current date in the specified directory
        File folder = new File(directoryPath + File.separator + currentDate + File.separator);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder created: ");
            } else {
                System.err.println("Failed to create folder!");
                return;
            }
        } else {
            System.err.println("Folder already exists: ");
            return;
        }

        // Create text files inside the folder
        String[] fileNames = {"healthConcerns.txt", "immunizationRecords.txt", "knownAllergies.txt",
                "previousHealthIssues.txt", "previousMedication.txt", "recommendations.txt", "vitals.txt"};
        for (String fileName : fileNames) {
            File file = new File(folder, fileName);
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: ");
                } else {
                    System.err.println("File already exists: ");
                }
            } catch (IOException e) {
                System.err.println("Failed to create file: ");
            }
        }
    }
}

class fileOpen extends Application{
	public File folder;
	public String folderStr = folder.toString();
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
                        	readVitals rv = new readVitals(folderStr);
                        	rv.start(new Stage());
                        }else {
                        	HealthHistoryNurse hhn = new HealthHistoryNurse(folderStr);
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
		        this.folderPath = folderPath;
		}
		
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

	        // HBoxes for labels l4 to l8
	        TextField t3 = new TextField(Age);
	        t3.setEditable(true);
	        HBox h4 = new HBox(new Label("Age:"), t3);
	        h4.setSpacing(10);
	        
	        TextField t4 = new TextField(Height);
	        t4.setEditable(true);
	        HBox h5 = new HBox(new Label("Height:"), t4);
	        h5.setSpacing(10);
	        
	        TextField t5 = new TextField(Weight);
	        t5.setEditable(true);
	        HBox h6 = new HBox(new Label("Weight:"), t5);
	        h6.setSpacing(10);
	        
	        TextField t6 = new TextField(BodyTemperature);
	        t6.setEditable(true);
	        HBox h7 = new HBox(new Label("Body Temperature:"), t6);
	        h7.setSpacing(10);
	        
	        TextField t7 = new TextField(BloodPressure);
	        t7.setEditable(true);
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
	String[] FILE_PATHS;
	String folderPath; 

	public HealthHistoryNurse(String folderPath) {
		System.out.println("Constructor called");
		FILE_PATHS = new String[]{
                folderPath + "/previousHealthIssues.txt",
                folderPath + "/healthConcerns.txt",
                folderPath + "/recommendations.txt",
                folderPath + "/knownAllergies.txt",
                folderPath + "/previousMedication.txt",
                folderPath + "/immunizationRecords.txt"
        };
    }


    private TextArea[] textAreas = new TextArea[6];

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < FILE_PATHS.length; i++) {
            Label label = new Label(setLabel(i));
            TextArea textArea = new TextArea();
            if(i == 2) {
            	textArea.setEditable(false);
            }
            textAreas[i] = textArea;
            readFileIntoTextArea(textArea, FILE_PATHS[i]);
            gridPane.add(label, i % 3, 2 * (i / 3));
            gridPane.add(textArea, i % 3, 2 * (i / 3) + 1);
        }

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            for (int i = 0; i < FILE_PATHS.length; i++) {
                writeToTextFile(FILE_PATHS[i], textAreas[i]);
            }
        });

        gridPane.add(submitButton, 0, 4, 3, 1);

        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public String setLabel(int i) {
    	if(i == 0) {
    		return "Previous Health Issues";
    	}else if(i == 1) {
    		return "Health Concerns";
    	}else if(i == 2) {
    		return "Reccomendations";
    	}else if(i == 3) {
    		return "Known Allergies";
    	}else if(i == 4) {
    		return "Previous Medication";
    	}else if(i == 5) {
    		return "Immunization Records";
    	}
    	return "----";
    }
    
    private void readFileIntoTextArea(TextArea textArea, String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            textArea.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToTextFile(String filePath, TextArea textArea) {
        try {
            String content = textArea.getText();
            Files.write(Paths.get(filePath), content.getBytes());
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
                folderPath + "/previousHealthIssues.txt",
                folderPath + "/healthConcerns.txt",
                folderPath + "/recommendations.txt",
                folderPath + "/knownAllergies.txt",
                folderPath + "/previousMedication.txt",
                folderPath + "/immunizationRecords.txt"
        };
    }

    // Array to hold file paths
    private String[] FILE_PATHS;
    


    private TextArea[] textAreas = new TextArea[FILE_PATHS.length];

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < FILE_PATHS.length; i++) {
            Label label = new Label(setLabel(i));
            TextArea textArea = new TextArea();
            textAreas[i] = textArea;
            readFileIntoTextArea(textArea, FILE_PATHS[i]);
            gridPane.add(label, i % 3, 2 * (i / 3));
            gridPane.add(textArea, i % 3, 2 * (i / 3) + 1);
        }

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            for (int i = 0; i < FILE_PATHS.length; i++) {
                writeToTextFile(FILE_PATHS[i], textAreas[i]);
            }
        });

        gridPane.add(submitButton, 0, 4, 3, 1);

        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public String setLabel(int i) {
    	if(i == 0) {
    		return "Previous Health Issues";
    	}else if(i == 1) {
    		return "Health Concerns";
    	}else if(i == 2) {
    		return "Reccomendations";
    	}else if(i == 3) {
    		return "Known Allergies";
    	}else if(i == 4) {
    		return "Previous Medication";
    	}else if(i == 5) {
    		return "Immunization Records";
    	}
    	return "NULL";
    }
    
    private void readFileIntoTextArea(TextArea textArea, String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            textArea.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToTextFile(String filePath, TextArea textArea) {
        try {
            String content = textArea.getText();
            Files.write(Paths.get(filePath), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
=======
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
    
>>>>>>> branch 'main' of https://github.com/CSE360-Th30-Project/CSE360-Project-Team-Th30.git
    public static void main(String[] args) {
        launch(args);
    }
}
