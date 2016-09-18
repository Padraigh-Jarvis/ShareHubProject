package shareHub;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.javafx.css.StyleManager;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {
	Stage window;
	Desktop desktop = Desktop.getDesktop();
	File file;
	popUpBox popUp;
	Label label;
	CopyFile cf;

	Scene startUpMenu;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch (args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {

		StyleManager.getInstance().addUserAgentStylesheet("myStyle.css");
		window=primaryStage;
		window.setTitle("Share Hub");
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});

		BorderPane borderPane = new BorderPane();
		startUpMenu = new Scene(borderPane,800,600);
		label=new Label("This projects goal is to make a program that lets a user browse a directory for music and pictures");
		//startUpMenu.getStylesheets().add("myStyle.css");

		Button pictureSection = new Button ("Browse pictures");
		pictureSection.setOnAction(e -> {
			file=null;
			pictureMenu(startUpMenu);
		});
		Button musicSection = new Button ("Browse music");
		musicSection.setOnAction(e -> {
			file=null;
			musicMenu(startUpMenu);
		});
		Button exit = new Button ("Close program");
		exit.setOnAction(e -> {
			closeProgram();
		});
		HBox hbox = new HBox(20,label);
		VBox vbox = new VBox(10,hbox,pictureSection,musicSection,exit);

		hbox.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		StackPane stack = new StackPane(vbox);
		stack.setAlignment(Pos.CENTER);

		borderPane.setTop(hbox);
		borderPane.setCenter(stack);

		window.getIcons().add(new Image("file:Shareicon.jpg"));


		window.setScene(startUpMenu);


		window.show();

	}
	private void closeProgram()
	{
		boolean answer = confirmBox.display("Exit?", "Sure you want to exit?");
		if (answer)
		{
			window.close();
		}
	}
	private void pictureMenu(Scene startUpMenu)
	{


		BorderPane borderPane = new BorderPane();
		Scene pictureMenu = new Scene(borderPane,800,600);
		//pictureMenu.getStylesheets().add("myStyle.css");
		if (file==null){
			label=new Label("No file selected");
		}
		else 
		{
			label = new Label(file.getName() + " is selected");
		}
		FileChooser fileChooser = new FileChooser();

		Button openButton = new Button("Select a picture");

		openButton.setOnAction(e -> {
			configureFileChooserSelect(fileChooser,"Select PNG");
			file = fileChooser.showOpenDialog(window);
			if (file != null) {
				pictureMenu(startUpMenu);
			}
		});

		Button viewPicture= new Button("View selected picture");
		viewPicture.setOnAction(e ->{
			if (file==null)
			{
				popUpBox.displayText("Error","No file selected");
			}
			else 
			{
				Image image = new Image("File:"+file.toString());
				popUpBox.displayImage(file.getName(), image);
			}

		});

		Button copyPicture= new Button ("Copy selected picture");
		copyPicture.setOnAction(e -> {
			if (file!=null)
			{
				configureFileChooserSave(fileChooser,"Select PNG");
				File copyFile = fileChooser.showSaveDialog(window);
				ShareHubFile file1 = CopyFile.getFile(file);
				if (file1.copy(file,copyFile)==true)
				{
					popUpBox.displayText("Success","File has been copyed");
				}
				else
				{
					popUpBox.displayText("Error", "File already exits");
				}


			}
			else 
			{
				popUpBox.displayText("Error","No file selected");
			}
		});

		Button exitButton = new Button ("Back");
		exitButton.setOnAction(e -> {
			window.setScene(startUpMenu);
		});

		HBox hbox = new HBox(20,label);
		hbox.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(10,openButton,viewPicture,copyPicture,exitButton);
		vbox.setAlignment(Pos.CENTER);

		StackPane stack = new StackPane(vbox);
		stack.setAlignment(Pos.CENTER);

		borderPane.setTop(hbox);
		borderPane.setCenter(stack);


		window.setScene(pictureMenu);

	}
	private void musicMenu(Scene startUpMenu)
	{

		BorderPane borderPane = new BorderPane();
		Scene musicMenu = new Scene(borderPane,800,600);
		//musicMenu.getStylesheets().add("myStyle.css");
		if (file==null)
		{
			label=new Label("No file selected");
		}
		else
		{
			label = new Label(file.getName() + " is selected");
		}

		FileChooser fileChooser = new FileChooser();

		Button openButton = new Button("Select a MP3");

		openButton.setOnAction(e -> {
			configureFileChooserSelect(fileChooser,"Select MP3");
			file = fileChooser.showOpenDialog(window);
			if (file != null) 
			{
				musicMenu(startUpMenu);         	 
			}
		});  
		Button play = new Button("Play MP3");
		play.setOnAction(e -> {
			if (file!=null)
			{
				Media song = new Media(file.toURI().toString());
				popUpBox.displayMediaPlayer(file.getName(), song);

			}   
			else 
			{
				popUpBox.displayText("Error","No mp3 selected");
			}
		});

		Button copySong= new Button ("Copy selected song");
		copySong.setOnAction(e -> {
			if (file!=null)
			{

				configureFileChooserSave(fileChooser,"Select MP3");
				File copyFile = fileChooser.showSaveDialog(window);
				ShareHubFile file1 = CopyFile.getFile(file);
				if (file1.copy(file,copyFile)==true)
				{
					popUpBox.displayText("Success","File has been copyed");
				}
				else
				{
					popUpBox.displayText("Error", "File already exits");
				}

			}
			else 
			{
				popUpBox.displayText("Error","No file selected");
			}
		});

		Button exitButton = new Button ("Back");
		exitButton.setOnAction(e -> {
			window.setScene(startUpMenu);
		});

		HBox hbox = new HBox(20,label);
		hbox.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(10,openButton,play,copySong,exitButton);
		vbox.setAlignment(Pos.CENTER);

		StackPane stack = new StackPane(vbox);
		stack.setAlignment(Pos.CENTER);

		borderPane.setTop(hbox);
		borderPane.setCenter(stack);


		window.setScene(musicMenu);

	}

	
	private static void configureFileChooserSelect(FileChooser fileChooser,String title){                           
		fileChooser.setTitle(title);
		fileChooser.setInitialDirectory( 
				new File(System.getProperty("user.home") + System.getProperty("file.separator")+"Desktop"+System.getProperty("file.separator")+"collage stuff"+System.getProperty("file.separator")
				+"Year 3 semester 1"+System.getProperty("file.separator")+"Advanced object programming"+System.getProperty("file.separator")+"workspace"+System.getProperty("file.separator")+"shareHub"+System.getProperty("file.separator")
				+"mp3 and pictures")
				);
		if (title=="Select PNG")
		{
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
		}
		else if (title=="Select MP3")
		{
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3", "*.mp3"));
		}
	}
	private static void configureFileChooserSave(FileChooser fileChooser,String title){                           
		fileChooser.setTitle(title);
		if (title=="Select PNG")
		{
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
		}
		else if (title=="Select MP3")
		{
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3", "*.mp3"));
		}
	}




}
