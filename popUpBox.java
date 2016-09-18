package shareHub;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class popUpBox {

	//static boolean answer;
	public static void displayText(String title,String message)
	{
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);		
		Label label = new Label();
		label.setText(message);

		Button continueButton= new Button("Continue");


		continueButton.setOnAction(e -> {
			window.close();
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label,continueButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		//scene.getStylesheets().add("myStyle.css");
		window.setScene(scene);
		window.showAndWait();


	}
	public static void displayImage(String title,Image image)
	{
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);		
		ImageView imageViewer = new ImageView();
		imageViewer.setImage(image);


		Button continueButton= new Button("Continue");


		continueButton.setOnAction(e -> {
			window.close();
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(imageViewer,continueButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		//scene.getStylesheets().add("myStyle.css");
		window.setScene(scene);
		window.showAndWait();


	}
	public static void displayMediaPlayer(String title,Media song)
	{

		Stage window = new Stage();
		BorderPane borderPane = new BorderPane();
		Scene menu = new Scene(borderPane,400,400);
	//	menu.getStylesheets().add("myStyle.css");
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);		
		Label label = new Label("Currently playing " + title);
		MediaPlayer mediaPlayer = new MediaPlayer(song);

		Button continueButton= new Button("Continue");
		continueButton.setOnAction(e -> {
			mediaPlayer.stop();
			window.close();
		});

		Slider volume = new Slider(0,100,0.50);
		volume.setShowTickMarks(true);
		volume.setShowTickLabels(true);
		volume.setValue(mediaPlayer.getVolume() * 100);
		volume.valueProperty().addListener(new InvalidationListener()
		{
			@Override
			public void invalidated(Observable observable)
			{
				mediaPlayer.setVolume(volume.getValue() / 100);
			}
		}
		);
		

		mediaPlayer.play();
		Button play= new Button("Resume");
		play.setOnAction(e -> {
			mediaPlayer.play();
		});

		Button pause=new Button("Pause");
		pause.setOnAction(e -> {
			mediaPlayer.pause();
		});
		mediaPlayer.currentTimeProperty();


		VBox vbox = new VBox(20,label);
		vbox.setAlignment(Pos.CENTER);
		HBox hbox = new HBox(20,play,pause,volume);
		hbox.setAlignment(Pos.CENTER);
		HBox continuePosition = new HBox(20,continueButton);
		continuePosition.setAlignment(Pos.CENTER);
		StackPane stack = new StackPane(hbox);
		stack.setAlignment(Pos.CENTER);

		borderPane.setTop(vbox);
		borderPane.setCenter(stack);
		borderPane.setBottom(continuePosition);

		window.setScene(menu);
		window.showAndWait();





	}

}

