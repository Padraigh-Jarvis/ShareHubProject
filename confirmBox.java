package shareHub;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class confirmBox {
	
	static boolean answer;
	public static boolean display(String title , String message)
	{
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.getIcons().add(new Image("file:exit.jpg"));
		window.setTitle(title);
		window.setMinWidth(250);		
		Label label = new Label();
		label.setText(message);
	
		Button yesButton= new Button("Yes");
		Button noButton= new Button("No");
		
		yesButton.setOnAction(e -> {
			answer = true;
			window.close();
		});
		
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});

		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label,yesButton,noButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		//scene.getStylesheets().add("myStyle.css");
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}

}