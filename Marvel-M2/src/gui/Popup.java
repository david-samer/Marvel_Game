package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Popup {
	 
	public static  void display (String title , String message)
	{
		Stage window = new Stage();
		
		window.setOpacity(0.8);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		//Label
		Label label = new Label();
		label.setText(message);
		label.setTextFill(Color.RED);
		label.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		//close button 
		Button close = new Button("Close the window");
		close.setOnAction(e ->window.close());
			
		//layout
		VBox layout = new VBox (10);
		layout.getChildren().addAll(label,close);
		layout.setAlignment(Pos.CENTER);
		
		//Scene 
		Scene scene = new Scene(layout , 600 , 480);
		

		Image back = new Image("popup.gif");
		BackgroundImage bImg = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background deadpoolPop = new Background(bImg);
		layout.setBackground(deadpoolPop);
		
		//layout.getStylesheets().add("popStyle.css");
		
		window.initStyle(StageStyle.UNDECORATED);
		window.setScene(scene);
		window.showAndWait();
	
	}
}
