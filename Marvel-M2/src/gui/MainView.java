package gui;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

import engine.Game;
import engine.Player;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import junit.framework.Test;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class MainView extends Application {
	Stage window;
	Scene startScreen;
	Scene nameScreen;
	Scene selectTeamScreen;
	Scene playScreen;
	Scene winScene;
	int firstSel = 1;
	TextField name1;
	TextField name2;
	String p1;
	String p2;
	Label n1 ;
	Label n2 ;
	Game game;
	ArrayList <Champion> team1;
	ArrayList <Champion> team2;
	ArrayList <Champion> turn;
	Pane winPane;
	MediaPlayer mediaplayer;

	
	
	

	public void start(Stage s) throws Exception {
		Image icon = new Image("marvelicon1.png");
		s.getIcons().add(icon);
		music();
	//	window.getIcons().add(new Image(this.getClass().getResourceAsStream("D:\\GUC\\Semester 4\\CS 401\\Game\\Images\\gameIcon.jfif")));
		team1 = new ArrayList<>();
		team2 = new ArrayList<>();
		window = s;
		window.setTitle("Endgame");
		window.setMaximized(true);
		window.setResizable(false);
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		window.toFront();
		window.initStyle(StageStyle.UNDECORATED);
//
//
		
		
//		  String path = "\\Users\\David\\Downloads\\Music\\AvengersTheme.mp3";  
//          
//	        //Instantiating Media class  
//	        Media media = new Media(new File(path).toURI().toString());  
//	          
//	        //Instantiating MediaPlayer class   
//	        MediaPlayer mediaPlayer = new MediaPlayer(media);  
//	          
//	        //by setting this property to true, the audio will be played   
//	        mediaPlayer.setAutoPlay(true);  
		//window.setFullScreenExitHint("");
		//System.out.println(screenBounds.getWidth() + " "+ screenBounds.getHeight());
		// start screen ....................................................

		
		Image back = new Image("Avengers.jpg");
		BackgroundImage bImg = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background bGround = new Background(bImg);

		VBox layout = new VBox(50);
		layout.setBackground(bGround);
		Button startButton = new Button("Start");
		Button exitButton = new Button("Quit");
		layout.getChildren().addAll(startButton, exitButton);
		startButton.setOnAction(e ->{
			window.setScene(nameScreen);
			
			
		});
		exitButton.setOnAction(e -> window.close());
		startScreen = new Scene(layout, 1100, 700);

		layout.setAlignment(Pos.CENTER);
		VBox fp = new VBox(2);
		VBox sp = new VBox(2);
		

		// name Screen ...........................................................
		HBox heading= new HBox();
		// Image back2 = new Image("D:\\GUC\\Semester 4\\CS
		// 401\\Game\\Images\\AvengersBlank.jpg");
		Image back2 = new Image("AvengersBlank.jpg");
		BackgroundImage bImg2 = new BackgroundImage(back2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background blankBack = new Background(bImg2);

		VBox firstV = new VBox();
		Label firstPlayer = new Label("First Player");
		name1 = new TextField();
		name1.setStyle("-fx-background-color:#606060 ;  -fx-text-inner-color: white;");
		firstV.getChildren().addAll(firstPlayer, name1);
		firstV.setAlignment(Pos.CENTER);

		VBox secondV = new VBox();
		Label secondPlayer = new Label("Second Player");
		name2 = new TextField();
		name2.setStyle("-fx-background-color:#606060 ;  -fx-text-inner-color: white;");
		secondV.getChildren().addAll(secondPlayer, name2);
		secondV.setAlignment(Pos.CENTER);

		
		Button captainB = new Button();
		Button deadpoolB = new Button();
		Button doctorB = new Button();
		Button electroB = new Button();
		Button ghostB = new Button();
		Button helaB = new Button();
		Button hulkB = new Button();
		Button icemanB = new Button();
		Button ironmanB = new Button();
		Button lokiB = new Button();
		Button quicksilverB = new Button();
		Button spidermanB = new Button();
		Button thorB = new Button();
		Button venomB = new Button();
		Button yellowjacketB = new Button();
		
		
		Button playButton = new Button("Play Now!");
		playButton.setOnAction(e -> {
			
			window.setScene(selectTeamScreen);
			
			p2 = name2.getText();
			p1 = name1.getText();
			
			Player player1 = new Player(p1);
			Player player2 = new Player(p2);
			//n1  =  new Label(p1);
			// System.out.println(p1 + "hello");
			game = new Game(player1, player2);
			//System.out.println(game.getTurnOrder().isEmpty());
			try {

				Game.loadAbilities("Abilities.csv");
			} catch (IOException e2) {
				
			}

			try {
				Game.loadChampions("Champions.csv");
			} catch (IOException e2) {
				
			}
			n1 = new Label(p1);
			n2 = new Label(p2);
			fp.getChildren().add(n1);
			sp.getChildren().addAll(n2);
			
			Label fullHead = new Label(p1 + " SELECT YOUR LEADER CHAMPION");
			heading.getChildren().addAll(fullHead);
			captainB.setTooltip(new Tooltip(info("Captain America") + allAbilities("Captain America")));
			deadpoolB.setTooltip(new Tooltip(info("Deadpool") + allAbilities("Deadpool")));
			doctorB.setTooltip(new Tooltip(info("Dr Strange") + allAbilities("Dr Strange")));
			electroB.setTooltip(new Tooltip(info("Electro") + allAbilities("Electro")));
			ghostB.setTooltip(new Tooltip(info("Ghost Rider") + allAbilities("Ghost Rider")));
			helaB.setTooltip(new Tooltip(info("Hela") + allAbilities("Hela")));
			hulkB.setTooltip(new Tooltip(info("Hulk") + allAbilities("Hulk")));
			icemanB.setTooltip(new Tooltip(info("Iceman") + allAbilities("Iceman")));
			ironmanB.setTooltip(new Tooltip(info("Ironman") + allAbilities("Ironman")));
			lokiB.setTooltip(new Tooltip(info("Loki") + allAbilities("Loki")));
			quicksilverB.setTooltip(new Tooltip(info("Quicksilver") + allAbilities("Quicksilver")));
			spidermanB.setTooltip(new Tooltip(info("Spiderman") + allAbilities("Spiderman")));
			thorB.setTooltip(new Tooltip(info("Thor") + allAbilities("Thor")));
			venomB.setTooltip(new Tooltip(info("Venom") + allAbilities("Venom")));
			yellowjacketB.setTooltip(new Tooltip(info("Yellow Jacket") + allAbilities("Yellow Jacket")));






		});

		GridPane grid = new GridPane();
		grid.setBackground(blankBack);
		grid.add(firstV, 0, 1);
		grid.add(secondV, 2, 1);
		grid.add(playButton, 1, 2);
		grid.setVgap(50);
		grid.setHgap(50);
		grid.setAlignment(Pos.CENTER);
		nameScreen = new Scene(grid, screenBounds.getWidth(), screenBounds.getHeight());

		
		// select team ....................................................

		Image ch1 = new Image("captain.gif");
		BackgroundImage bImg3 = new BackgroundImage(ch1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background captainP = new Background(bImg3);

		Image ch2 = new Image("deadpool.gif");
		BackgroundImage bImg4 = new BackgroundImage(ch2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background deadpoolP = new Background(bImg4);

		Image ch3 = new Image("strange.gif");
		BackgroundImage bImg5 = new BackgroundImage(ch3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background strangeP = new Background(bImg5);
		
		Image ch4 = new Image("electro.gif");
		BackgroundImage bImg8 = new BackgroundImage(ch4, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background electroP = new Background(bImg8);
		
		Image ch5 = new Image("ghost.gif");
		BackgroundImage bImg9 = new BackgroundImage(ch5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background ghostP = new Background(bImg9);
		
		Image ch6 = new Image("hela.gif");
		BackgroundImage bImg10 = new BackgroundImage(ch6, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background helaP = new Background(bImg10);

		
		
		
		Image ch7 = new Image("hulk.gif");
		BackgroundImage bImg11 = new BackgroundImage(ch7, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background hulkP = new Background(bImg11);
		
		Image ch8 = new Image("iceman.gif");
		BackgroundImage bImg12 = new BackgroundImage(ch8, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background icemanP = new Background(bImg12);
		
		
		Image ch9 = new Image("ironman.gif");
		BackgroundImage bImg13= new BackgroundImage(ch9, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background ironmanP = new Background(bImg13);
		
		Image ch10 = new Image("loki.gif");
		BackgroundImage bImg14 = new BackgroundImage(ch10, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background lokiP = new Background(bImg14);
		
		Image ch11 = new Image("quicksilver.gif");
		BackgroundImage bImg15 = new BackgroundImage(ch11, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background quicksilverP = new Background(bImg15);
		
		Image ch12 = new Image("spiderman.gif");
		BackgroundImage bImg16 = new BackgroundImage(ch12, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background spidermanP = new Background(bImg16);

		Image ch13 = new Image("thor.gif");
		BackgroundImage bImg17 = new BackgroundImage(ch13, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background thorP = new Background(bImg17);
		
		Image ch14 = new Image("venom.gif");
		BackgroundImage bImg18 = new BackgroundImage(ch14, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background venomP = new Background(bImg18);
		
		Image ch15 = new Image("yellow.gif");
		BackgroundImage bImg19 = new BackgroundImage(ch15, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background yellowjacketP = new Background(bImg19);
		
		
		
		
		
//		VBox fp = new VBox(50);
		fp.setPadding(new Insets(1, 2, 1, 2));
		fp.setPrefWidth(300);
		fp.setAlignment(Pos.CENTER);
	
//		fp.getChildren().add(n1);
		
		Label sTeam = new Label("'s Team");
		

		
		sp.setPadding(new Insets(1, 2, 1, 2));
		sp.setPrefWidth(300);
		sp.setAlignment(Pos.CENTER);
		Label n1 = new Label();
		n1.textProperty().bind(name1.textProperty());
		n1.setId("labelSides");
		Label n2 = new Label();
		n2.textProperty().bind(name2.textProperty());
		n2.setId("labelSides");
		
		
		
		
		Button continueButton = new Button("Continue");
		continueButton.setId("buttonCont");
		continueButton.setDisable(true);
		FlowPane flow = new FlowPane();
		
	
		captainB.setBackground(captainP);
		captainB.setPrefSize(150, 150);
		
		captainB.setOnAction(e -> {
			//System.out.println(info("Captain America"));
			Label lab1 = new Label();
			ImageView tmp = new ImageView(ch1);
			lab1.setGraphic(tmp);
			if (firstSel % 2 == 0)
				champSelect(captainB, sp, lab1, findChamp("Captain America") , heading , continueButton ,flow );
			else
				champSelect(captainB, fp, lab1, findChamp("Captain America"), heading, continueButton,flow );
			firstSel++;
		});

		
		deadpoolB.setBackground(deadpoolP);
		deadpoolB.setPrefSize(150 ,150);
		deadpoolB.setOnAction(e -> {
			Label lab2 = new Label();
			ImageView tmp = new ImageView(ch2);
			lab2.setGraphic(tmp);
			if (firstSel % 2 == 0)
				champSelect(deadpoolB, sp, lab2, findChamp("Deadpool"), heading, continueButton,flow );
			else
				champSelect(deadpoolB, fp, lab2, findChamp("Deadpool"), heading, continueButton,flow );
			firstSel++;
		});
	
		doctorB.setBackground(strangeP);
		doctorB.setPrefSize(150 ,150);
		doctorB.setOnAction(e -> {
			Label lab = new Label();
			ImageView tmp = new ImageView(ch3);
			lab.setGraphic(tmp);
			if (firstSel % 2 == 0)
				champSelect(doctorB, sp, lab, findChamp("Dr Strange"), heading, continueButton,flow );
			else
				champSelect(doctorB, fp, lab, findChamp("Dr Strange"), heading, continueButton,flow );
			firstSel++;
		});
		
		electroB.setBackground(electroP);
		electroB.setPrefSize(150 ,150);
		electroB.setOnAction(e -> {
			Label lab = new Label();
			ImageView tmp = new ImageView(ch4);
			lab.setGraphic(tmp);
			if (firstSel % 2 == 0)
				champSelect(electroB, sp, lab, findChamp("Electro"), heading, continueButton,flow );
			else
				champSelect(electroB, fp, lab, findChamp("Electro"), heading, continueButton,flow );
			firstSel++;
		});
		
		ghostB.setBackground(ghostP);
		ghostB.setPrefSize(150 ,150);
		ghostB.setOnAction(e -> {
			Label lab = new Label();
			ImageView tmp = new ImageView(ch5);
			lab.setGraphic(tmp);
			if (firstSel % 2 == 0)
				champSelect(ghostB, sp, lab, findChamp("Ghost Rider"), heading, continueButton,flow );
			else
				champSelect(ghostB, fp, lab, findChamp("Ghost Rider"), heading, continueButton,flow );
			firstSel++;
		});
		
		helaB.setBackground(helaP);
		helaB.setPrefSize(150 ,150);
		helaB.setOnAction(e -> {
			Label lab = new Label();
			ImageView tmp = new ImageView(ch6);
			lab.setGraphic(tmp);
			if (firstSel % 2 == 0)
				champSelect(helaB, sp, lab, findChamp("Hela"), heading, continueButton,flow );
			else
				champSelect(helaB, fp, lab, findChamp("Hela"), heading, continueButton,flow );
			firstSel++;
		});
		
		//Hulk
	
				hulkB.setBackground(hulkP);
				hulkB.setPrefSize(150 ,150);
				hulkB.setOnAction(e -> {
					Label lab = new Label();
					ImageView tmp = new ImageView(ch7);
					lab.setGraphic(tmp);
					if (firstSel % 2 == 0)
						champSelect(hulkB, sp, lab, findChamp("Hulk"), heading, continueButton,flow );
					else
						champSelect(hulkB, fp, lab, findChamp("Hulk"), heading, continueButton,flow );
					firstSel++;
				});
				
				//ICEMAN
			
				icemanB.setBackground(icemanP);
				icemanB.setPrefSize(150 ,150);
				icemanB.setOnAction(e -> {
					Label lab = new Label();
					ImageView tmp = new ImageView(ch8);
					lab.setGraphic(tmp);
					if (firstSel % 2 == 0)
						champSelect(icemanB, sp, lab, findChamp("Iceman"), heading, continueButton,flow );
					else
						champSelect(icemanB, fp, lab, findChamp("Iceman"), heading, continueButton,flow );
					firstSel++;
				});
				
				//IRONman
				
				ironmanB.setBackground(ironmanP);
				ironmanB.setPrefSize(150 ,150);
				ironmanB.setOnAction(e -> {
					Label lab = new Label();
					ImageView tmp = new ImageView(ch9);
					lab.setGraphic(tmp);
					if (firstSel % 2 == 0)
						champSelect(ironmanB, sp, lab, findChamp("Ironman"), heading, continueButton,flow );
					else
						champSelect(ironmanB, fp, lab, findChamp("Ironman"), heading, continueButton,flow );
					firstSel++;
				});
				
				//Loki
				
				lokiB.setBackground(lokiP);
				lokiB.setPrefSize(150 ,150);
				lokiB.setOnAction(e -> {
					Label lab = new Label();
					ImageView tmp = new ImageView(ch10);
					lab.setGraphic(tmp);
					if (firstSel % 2 == 0)
						champSelect(lokiB, sp, lab, findChamp("Loki"), heading, continueButton,flow );
					else
						champSelect(lokiB, fp, lab, findChamp("Loki"), heading, continueButton,flow );
					firstSel++;
				});
				
				//QuickSilver
				
				quicksilverB.setBackground(quicksilverP);
				quicksilverB.setPrefSize(150 ,150);
				quicksilverB.setOnAction(e -> {
					Label lab = new Label();
					ImageView tmp = new ImageView(ch11);
					lab.setGraphic(tmp);
					if (firstSel % 2 == 0)
						champSelect(quicksilverB, sp, lab, findChamp("Quicksilver"), heading, continueButton,flow );
					else
						champSelect(quicksilverB, fp, lab, findChamp("Quicksilver"), heading, continueButton,flow );
					firstSel++;
				});
				
				//SPIDEMAN
			
				spidermanB.setBackground(spidermanP);
				spidermanB.setPrefSize(150 ,150);
				spidermanB.setOnAction(e -> {
					Label lab = new Label();
					ImageView tmp = new ImageView(ch12);
					lab.setGraphic(tmp);
					if (firstSel % 2 == 0)
						champSelect(spidermanB, sp, lab, findChamp("Spiderman"), heading, continueButton,flow );
					else
						champSelect(spidermanB, fp, lab, findChamp("Spiderman"), heading, continueButton,flow );
					firstSel++;
				});
				
				//THOR
			
				thorB.setBackground(thorP);
				thorB.setPrefSize(150 ,150);
				thorB.setOnAction(e -> {
					Label lab = new Label();
					ImageView tmp = new ImageView(ch13);
					lab.setGraphic(tmp);
					if (firstSel % 2 == 0)
						champSelect(thorB, sp, lab, findChamp("Thor"), heading, continueButton,flow );
					else
						champSelect(thorB, fp, lab, findChamp("Thor"), heading, continueButton,flow );
					firstSel++;
				});
				
				//VENOM
				
				venomB.setBackground(venomP);
				venomB.setPrefSize(150 ,150);
				venomB.setOnAction(e -> {
					Label lab = new Label();
					ImageView tmp = new ImageView(ch14);
					lab.setGraphic(tmp);
					if (firstSel % 2 == 0)
						champSelect(venomB, sp, lab, findChamp("Venom"), heading, continueButton,flow );
					else
						champSelect(venomB, fp, lab, findChamp("Venom"), heading, continueButton,flow );
					firstSel++;
				});
				
				//YELLOW JACKET
				
				yellowjacketB.setBackground(yellowjacketP);
				yellowjacketB.setPrefSize(150 ,150);
				yellowjacketB.setOnAction(e -> {
					Label lab = new Label();
					ImageView tmp = new ImageView(ch15);
					lab.setGraphic(tmp);
					if (firstSel % 2 == 0)
						champSelect(yellowjacketB, sp, lab, findChamp("Yellow Jacket"), heading, continueButton,flow );
					else
						champSelect(yellowjacketB, fp, lab, findChamp("Yellow Jacket"), heading, continueButton,flow );
					firstSel++;
				});
		
	
		
		
		
		
		BorderPane pane = new BorderPane();
		HBox trn = new HBox (10);
		trn.setAlignment(Pos.CENTER);
		pane.setTop(trn);
		HBox t1  = new HBox(20); 
		t1.setAlignment(Pos.CENTER);
		HBox t2  = new HBox(20); 
		t2.setAlignment(Pos.CENTER);
		
		GridPane boardGrid = new GridPane();
		VBox subFirstP = new VBox(5);
		VBox subSecondP = new VBox(5);
		
		VBox direction = new VBox(5);

		VBox firstP = new VBox(20);
		VBox secondP = new VBox(20);
		//continueButton.setDisable(true);
		continueButton.setOnAction(e -> 
		{
			music4();
			game.prepareChampionTurns2();
			game.placeChampions();
			printTurn(trn);
			printTeams(t1 , t2);
			if (game.getFirstPlayer().getTeam().contains( game.getCurrentChampion()) )
			{
				currTurnInfo(subFirstP , boardGrid ,direction);
				secondP.setDisable(true);
			}	
			else 
			{
				currTurnInfo(subSecondP, boardGrid , direction);
				firstP.setDisable(true);
			}
				
			
			takePosition(boardGrid);
			putCovers(boardGrid);  
			window.setScene(playScreen);
		});

	
		flow.setPadding(new Insets(1, 1, 1, 1));
		flow.setVgap(2);
		flow.setHgap(2);
		flow.getChildren().addAll(captainB, deadpoolB, doctorB , electroB , ghostB , 
				helaB, hulkB ,icemanB ,ironmanB,lokiB ,quicksilverB,spidermanB , thorB, venomB, yellowjacketB  );
		//flow.setPrefWrapLength(10);
		flow.setAlignment(Pos.CENTER);

		BorderPane selection = new BorderPane();
		selection.setPadding(new Insets(15, 15, 15, 15));
		selection.setLeft(fp);
		selection.setRight(sp);
		selection.setCenter(flow);
		selection.setBackground(blankBack);
		heading.setAlignment(Pos.CENTER);
		selection.setTop(heading);
		//selection.setBottom(done);
		BorderPane.setAlignment(continueButton, Pos.CENTER);
		selection.setBottom(continueButton);
		selectTeamScreen = new Scene(selection, screenBounds.getWidth(), screenBounds.getHeight());
		
		

		// play screen ..........................................................

		Image aren = new Image("arenaFinal.png");
		BackgroundImage bImg6 = new BackgroundImage(aren, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background arena = new Background(bImg6);
		
		int rowCount = 5;
		int columnCount = 5;
		RowConstraints rc = new RowConstraints();
		rc.setPercentHeight(100d / rowCount);
		for (int i = 0; i < rowCount; i++) {
			boardGrid.getRowConstraints().add(rc);
		}
		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(100d / columnCount);
		for (int i = 0; i < columnCount; i++) {
			boardGrid.getColumnConstraints().add(cc);
		}

		boardGrid.setPrefSize(650, 650);
		boardGrid.setAlignment(Pos.CENTER);
		
		
		firstP.setPadding(new Insets(1, 2, 1, 2));
		firstP.setPrefWidth(300);
		firstP.setAlignment(Pos.TOP_CENTER);
		
		Button attack = new Button("Attack");
		attack.setId("buttonSides");
		Label chooseDirection = new Label("Choose A Direction"+"\n" );
		chooseDirection.setFont(new Font("Arial", 50));
		direction.setAlignment(Pos.CENTER);
		
		Image img = new Image("WASD.png");
		ImageView arrows = new ImageView(img);
		direction.getChildren().addAll(chooseDirection , arrows );
		direction.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		direction.setOpacity(0.5);
		direction.setVisible(false);
		attack.setOnAction(e -> {
			
			direction.setVisible(true);
			if (game.getFirstPlayer().getTeam().contains( game.getCurrentChampion()) )
				attackHelp(subFirstP ,direction , boardGrid);
			else 
				attackHelp(subSecondP ,direction , boardGrid);

		});
	
		
		secondP.setPadding(new Insets(1, 2, 1, 2));
		secondP.setPrefWidth(300);
		secondP.setAlignment(Pos.TOP_CENTER);
		
		Button attack2 = new Button("Attack");
		
		attack2.setId("buttonSides");
		attack2.setOnAction(e -> {
			direction.setVisible(true);
			
			if (game.getFirstPlayer().getTeam().contains( game.getCurrentChampion()) )
				attackHelp(subFirstP ,direction , boardGrid);
			else 
				attackHelp(subSecondP ,direction , boardGrid);
		});
		
		Button move = new Button("Move");
		move.setId("buttonSides");
		move.setOnAction(e -> {
			direction.setVisible(true);
			if (game.getFirstPlayer().getTeam().contains( game.getCurrentChampion()) )
				moveHelp(subFirstP , direction , boardGrid);
			else 
				moveHelp(subSecondP, direction , boardGrid);
			//attackHelp(chooseDirection , boardGrid);
		});
		
		Button move2 = new Button("Move");
		move2.setId("buttonSides");
		move2.setOnAction(e -> {
			direction.setVisible(true);
			if (game.getFirstPlayer().getTeam().contains( game.getCurrentChampion()) )
				moveHelp(subFirstP , direction , boardGrid);
			else 
				moveHelp(subSecondP, direction , boardGrid);
		});
		
		firstP.getChildren().addAll(n1,t1 , move, attack);
		secondP.getChildren().addAll(n2, t2,move2,   attack2);

		StackPane stack = new StackPane();
		//arrowsP.translateYProperty();
		stack.getChildren().addAll(boardGrid , direction );
		
		pane.setPadding(new Insets(2, 20, 10, 20));
		pane.setCenter(stack);
		pane.setLeft(firstP);
		pane.setRight(secondP);
		pane.setBackground(arena);
		
		playScreen = new Scene(pane, screenBounds.getWidth(), screenBounds.getHeight());


		Button endButton = new Button ("End Turn");
		endButton.setId("buttonSides");
		endButton.setOnAction(e -> {
			endTurnHelper();
			highlightTurn(firstP, secondP);
			printTurn(trn);
			if (game.getFirstPlayer().getTeam().contains( game.getCurrentChampion()) )
				currTurnInfo(subFirstP , boardGrid , direction);
			else 
				currTurnInfo(subSecondP, boardGrid , direction);
		});
		
		Button endButton2 = new Button ("End Turn");
		endButton2.setId("buttonSides");
		endButton2.setOnAction(e -> {
			endTurnHelper();
			highlightTurn(firstP, secondP);
			printTurn(trn);
			if (game.getFirstPlayer().getTeam().contains( game.getCurrentChampion()) )
				currTurnInfo(subFirstP, boardGrid , direction);
			else 
				currTurnInfo(subSecondP, boardGrid , direction);
		});
		
		firstP.getChildren().addAll( subFirstP, endButton);
		secondP.getChildren().addAll(subSecondP, endButton2);
		 
		
		// winning Scene.................................................

		Image w = new Image("winScene2.png");
		BackgroundImage bImage = new BackgroundImage(w, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background winBG = new Background(bImage);
		
		winPane = new Pane();
		winScene = new Scene(winPane,  screenBounds.getWidth(), screenBounds.getHeight());
		winPane.setBackground(winBG);
		
		

		//////////////////////////////////////////////////////////////////
		String cssC = this.getClass().getResource("cool.css").toExternalForm();
		String cssS = this.getClass().getResource("selection.css").toExternalForm();
		String cssP = this.getClass().getResource("playStyle.css").toExternalForm();
		startScreen.getStylesheets().add(cssC);
		nameScreen.getStylesheets().add(cssC);
		selectTeamScreen.getStylesheets().add(cssS);
		playScreen.getStylesheets().add(cssP);
		
		winScene.getStylesheets().add(cssS);
		//firstP.setStyle("labelFirst");
		
		window.setScene(startScreen);
		window.show();

	}

	public void checkGameOver() 
	{
		Player p = game.checkGameOver();
		if (p==null)
		{
			return;
		}
		else 
		{
			Label n = new Label();
			n.setAlignment(Pos.CENTER);
			n.setId("labelWin");
			
			n.setLayoutY(200);
			window.setScene(winScene);
			if (p.getName().equals(game.getFirstPlayer().getName()))
			{
				n.setText(game.getFirstPlayer().getName() + " YOU WON!");
				Label t1 = generateLabel(team1.get(0).getName());
				Label t2 = generateLabel(team1.get(1).getName());
				Label t3 = generateLabel(team1.get(2).getName());
				winPane.getChildren().addAll(n,t1,t2,t3);
				t1.setLayoutX(500);
				t2.setLayoutX(750);
				t3.setLayoutX(950);
				
				t1.setLayoutY(565);
				t2.setLayoutY(455);
				t3.setLayoutY(630);
				
				Button end = new Button("End Game");
				end.setId("buttonEnd");
				end.setOnAction(e -> window.close());
				winPane.getChildren().add(end);
				end.setLayoutX(1200);
				end.setLayoutY(700);
			}
			else 
			{
				n.setText(game.getSecondPlayer().getName() + " YOU WON!");
				Label t1 = generateLabel(team2.get(0).getName());
				Label t2 = generateLabel(team2.get(1).getName());
				Label t3 = generateLabel(team2.get(2).getName());
				winPane.getChildren().addAll(n,t1,t2,t3);
				
				t1.setLayoutX(500);
				t2.setLayoutX(750);
				t3.setLayoutX(950);
				
				t1.setLayoutY(565);
				t2.setLayoutY(455);
				t3.setLayoutY(630);
				
				Button end = new Button("End Game");
				end.setId("buttonEnd");
				end.setOnAction(e -> window.close());
				winPane.getChildren().add(end);
				end.setLayoutX(1200);
				end.setLayoutY(700);
			}
		}
		
	
	}


	public String allAbilities(String champ)
	{
		String abs ="";
		for (int i=0 ; i<Game.getAvailableChampions().size() ; i++)
		{
			if (Game.getAvailableChampions().get(i).getName().equals(champ))
			{
				for (int j=0 ; j<Game.getAvailableChampions().get(i).getAbilities().size() ;j++)
				{
					int abN =j+1;
					abs = abs + "\n"+"\n"+ "Ability " + abN + "\n" +abilityInfo(Game.getAvailableChampions().get(i).getAbilities().get(j).getName());
				}
			}
		}
		return abs;
	}
	
	
	public void moveHelp(VBox sub ,VBox direction, GridPane boardGrid) {
		playScreen.setOnKeyPressed(e -> 
		{

			try {
				switch (e.getCode())
				{
					
					case W : game.move(Direction.DOWN) ; break;
					case S : game.move(Direction.UP); break;
					case A : game.move(Direction.LEFT); break;
					case D : game.move(Direction.RIGHT); break;
					
				}
				}
			catch (NotEnoughResourcesException e1) {
				Popup pop = new Popup();
				pop.display("Alert", "No Enough Recources");
				System.out.println("no enough resources");
			} catch (UnallowedMovementException e1) {
				Popup pop = new Popup();
				pop.display("Alert", "Unallowed Movement");
				System.out.println("unallowed move");
			}
				direction.setVisible(false);
				putChamps(boardGrid);
			currTurnInfo(sub, boardGrid, direction);

		});
		
	}

	public void currTurnInfo(VBox h , GridPane boardGrid , VBox direction)
	{
		h.getChildren().clear();
		
		h.setAlignment(Pos.CENTER);
		Champion curr = game.getCurrentChampion();
		Label card = generateCard(curr);
		card.setTooltip(new Tooltip(info(curr.getName())));
		h.getChildren().add(card);
		h.getChildren().add(healthBar(curr));
		Label manaNum  = new Label(curr.getMana()+ "");
		manaNum.setId("labelSides");
		Image m1 = new Image("glove.png");
		ImageView man = new ImageView(m1);
		HBox mana = new HBox (5);
		mana.getChildren().addAll(man , manaNum);
		mana.setAlignment(Pos.CENTER);
		//mana.setBackground(Color.GOLD);
		
		
		Label actionPts = new Label(curr.getCurrentActionPoints()+"");
		actionPts.setId("labelSides");
		Image m2 = new Image("action.png");
		ImageView act = new ImageView(m2);
		HBox action = new HBox (5);
		action.getChildren().addAll(act , actionPts);
		action.setAlignment(Pos.CENTER);
		
		
		
		
		h.getChildren().addAll(mana , action);
		h.getChildren().add(generateAbilities(boardGrid ,direction , h));
		h.getChildren().add(generateAppliedEff());
		 
		if (game.getCurrentChampion().getName() == game.getFirstPlayer().getLeader().getName() )		
		{
			Button leadAb = generateLeaderAb(h, boardGrid, direction);
			h.getChildren().add(leadAb);
			if (game.isFirstLeaderAbilityUsed())
				leadAb.setDisable(true);
		}
		else if (game.getCurrentChampion().getName() == game.getSecondPlayer().getLeader().getName() )		
		{
			Button leadAb = generateLeaderAb(h, boardGrid, direction);

			h.getChildren().add(leadAb);
			if (game.isSecondLeaderAbilityUsed())
				leadAb.setDisable(true);
			
			
		}
		

	}
	
	
	
	public Button generateLeaderAb(VBox h, GridPane boardGrid, VBox direction) {
			
			Button b = new Button();

			b.setOnAction(e ->{
				try {
					game.useLeaderAbility();
				} catch (LeaderNotCurrentException e1) {
					Popup pop = new Popup();
					pop.display("Alert", "Not Leader Champion's Turn");
					System.out.println("LeaderNotCurrentException");
				} catch (LeaderAbilityAlreadyUsedException e1) {
					Popup pop = new Popup();
					pop.display("Alert", "Ability Already Used");
					System.out.println("Ability Already Used");
				}
				currTurnInfo(h, boardGrid, direction);
				putChamps(boardGrid);
				
			});
			b.setId("buttonSides");
			if (game.getCurrentChampion() instanceof Villain)
			{
				b.setText("Villain Leader Ability");
				b.setTooltip(new Tooltip("Knocks Out All Enemies" + "\n" + "Less than 30% of HP"));
			}
			else if (game.getCurrentChampion() instanceof Hero)
			{
				b.setText("Hero Leader Ability");
				b.setTooltip(new Tooltip("Add Embrace to Team" + "\n" + "Removes all negative Effects"));
			}
			else
			{
				b.setText("Anti-Hero Leader Ability");
				b.setTooltip(new Tooltip("Stun all Non-Leader Champions"));
			}
				
			return b;

	}

	public HBox generateAppliedEff() {
		HBox h = new HBox(5);
		
		
		for (int i = 0 ; i< game.getCurrentChampion().getAppliedEffects().size() ; i++)
		{
			h.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, null , null)));
			Label tmp = new Label (game.getCurrentChampion().getAppliedEffects().get(i).getName() + " ("+ game.getCurrentChampion().getAppliedEffects().get(i).getDuration() +")");
			h.getChildren().add(tmp);
		}
		return h;
		
	}

	public HBox generateAbilities(GridPane boardGrid , VBox direction , VBox sub ) 
	{
		HBox h = new HBox(5);
		for (int i =0 ; i<game.getCurrentChampion().getAbilities().size() ; i++)
		{
			
			h.getChildren().add(abilityButton(game.getCurrentChampion().getAbilities().get(i) ,  boardGrid , direction , sub));
			h.setAlignment(Pos.CENTER);
		}
		return h;
	}

	public Button abilityButton(Ability a , GridPane boardGrid , VBox direction, VBox sub) {
		Image a1 = new Image("damageIcon.png");
		BackgroundImage bImg = new BackgroundImage(a1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background ab1 = new Background(bImg);

		Image a2 = new Image("healIcon.png");
		BackgroundImage bImg2 = new BackgroundImage(a2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background ab2 = new Background(bImg2);
		
		Image a3 = new Image("crowdIcon.png");
		BackgroundImage bImg3 = new BackgroundImage(a3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background ab3 = new Background(bImg3);
	
		Button ability =  new Button ();
		
		if (a instanceof DamagingAbility)
			ability.setBackground(ab1);
		else if (a instanceof HealingAbility)
			ability.setBackground(ab2);
		else 
			ability.setBackground(ab3);
		
		
		ability.setId("buttonAb");
		
		ability.setPrefSize(50, 50);
		ability.setTooltip(new Tooltip(abilityInfo(a.getName())));
		ability.setOnAction(e-> {
			if (a.getCastArea() == AreaOfEffect.SELFTARGET || a.getCastArea() == AreaOfEffect.TEAMTARGET
					|| a.getCastArea() == AreaOfEffect.SURROUND)
			{
				try {
					game.castAbility(a);
				} catch (NotEnoughResourcesException e1) {
					Popup pop = new Popup();
					pop.display("Alert", "No Enough Recources");
					System.out.println("no enough resources");
				} catch (AbilityUseException e1) {
					Popup pop = new Popup();
					pop.display("Alert", "Can't Cast Ability");
					System.out.println("Ability use Exception");
					
				} catch (CloneNotSupportedException e1) {
					Popup pop = new Popup();
					pop.display("Alert", "Clone");
					System.out.println("Clone Exception");
				}
				putChamps(boardGrid);
				currTurnInfo(sub, boardGrid, direction);
			}
			
			else if (a.getCastArea() == AreaOfEffect.DIRECTIONAL)
			{
				direction.setVisible(true);
				playScreen.setOnKeyPressed(e1 -> 
				{
					switch (e1.getCode())
					{
					
						case W : try {
							game.castAbility(  a ,Direction.DOWN);
							if (game.getFirstPlayer().getTeam().contains( game.getCurrentChampion()) )
								currTurnInfo(sub , boardGrid , direction);
							else 
								currTurnInfo(sub, boardGrid , direction);
						} catch (NotEnoughResourcesException e2) {
							Popup pop = new Popup();
							pop.display("Alert", "No Enough Recources");
							System.out.println("no enough resources");
						} catch (AbilityUseException e2) {
							Popup pop = new Popup();
							pop.display("Alert", "Can't Cast Ability");
							System.out.println("Ability use Exception");
						} catch (CloneNotSupportedException e2) {
							Popup pop = new Popup();
							pop.display("Alert", "Clone");
							System.out.println("Clone Exception");
						}  break;
						case S : try {
							game.castAbility(a , Direction.UP);
						} catch (NotEnoughResourcesException e2) {
							Popup pop = new Popup();
							pop.display("Alert", "No Enough Recources");
							System.out.println("no enough resources");
						
						} catch (AbilityUseException e2) {
							Popup pop = new Popup();
							pop.display("Alert", "Can't Cast Ability");
							System.out.println("Ability use Exception");
						} catch (CloneNotSupportedException e2) {
							Popup pop = new Popup();
							pop.display("Alert", "Clone");
							System.out.println("Clone Exception");
						}break;
						case A : try {
							game.castAbility(a , Direction.LEFT);
						} catch (NotEnoughResourcesException e2) {
							Popup pop = new Popup();
							pop.display("Alert", "No Enough Recources");
							System.out.println("no enough resources");
						
						} catch (AbilityUseException e2) {
							Popup pop = new Popup();
							pop.display("Alert", "Can't Cast Ability");
							System.out.println("Ability use Exception");
						} catch (CloneNotSupportedException e2) {
							Popup pop = new Popup();
							pop.display("Alert", "Clone");
							System.out.println("Clone Exception");
						}break;
						case D : try {
							game.castAbility(a, Direction.RIGHT);
						} catch (NotEnoughResourcesException e2) {
							Popup pop = new Popup();
							pop.display("Alert", "No Enough Recources");
							System.out.println("no enough resources");
						
						} catch (AbilityUseException e2) {
							Popup pop = new Popup();
							pop.display("Alert", "Can't Cast Ability");
							System.out.println("Ability use Exception");
						} catch (CloneNotSupportedException e2) {
							Popup pop = new Popup();
							pop.display("Alert", "Clone");
							System.out.println("Clone Exception");
						}break;
					}
					direction.setVisible(false);
					currTurnInfo(sub , boardGrid , direction);
					putChamps(boardGrid);
//					if (game.getFirstPlayer().getTeam().contains( game.getCurrentChampion()) )
//						updateVals(sub, boardGrid, direction);
//					else 
//						updateVals(sub, boardGrid, direction);
				});
				
				
			}
			else if (a.getCastArea() == AreaOfEffect.SINGLETARGET)
			{
				sigleTargetButtons(a , boardGrid , sub , direction);
			}
			
			
		});
		checkGameOver();
		
		return ability;
	}
	
	public void updateVals(VBox sub , GridPane boardGrid , VBox direction)
	{
		currTurnInfo(sub , boardGrid , direction);
	}

	public void sigleTargetButtons(Ability a , GridPane boardGrid , VBox sub , VBox direction) {
		
		for(int i=0 ; i< 5 ; i++)
		{
			for (int j=0 ; j<5 ; j++)
			{
				int in =i;
				int jn = j;
				Button single =new Button();
				single.setId("buttonSides");
				single.setOpacity(0.5);
				boardGrid.add(single, i, j);
				boardGrid.setHalignment(single,HPos.CENTER);
				single.setPrefSize(100, 100);
				single.setOnAction(e -> {
					try {
						game.castAbility(a, jn, in);
					} catch (NotEnoughResourcesException e1) {
						Popup pop = new Popup();
						pop.display("Alert", "No Enough Recources");
						System.out.println("no enough resources");
					} catch (AbilityUseException e1) {
						Popup pop = new Popup();
						pop.display("Alert", "Can't Cast Ability");
						System.out.println("out of range");
					
					} catch (InvalidTargetException e1) {
						Popup pop = new Popup();
						pop.display("Alert", "Invalid Target");
						System.out.println("invalid target");
					} catch (CloneNotSupportedException e1) {
						Popup pop = new Popup();
						pop.display("Alert", "Clone");
						System.out.println("Clone Exception");
					}
					
					putChamps(boardGrid);
					if (game.getFirstPlayer().getTeam().contains( game.getCurrentChampion()) )
						currTurnInfo(sub , boardGrid , direction);
					else 
						currTurnInfo(sub, boardGrid , direction);
				});
				
			}
		}
		
	}

	public Label generateCard(Champion currentChampion) {
		Image ch1 = new Image("captainCard.png");
		Image ch2 = new Image("deadCard.png");
		Image ch3 = new Image("elecCard.png");
		Image ch4 = new Image("ghostCard.png");
		Image ch5 = new Image("helaCard.png");
		Image ch6 = new Image("hulkCard.png");
		Image ch7 = new Image("iceCard.png");
		Image ch8 = new Image("ironCard.png");
		Image ch9 = new Image("lokiCard.png");
		Image ch10 = new Image("quickCard.png");
		Image ch11 = new Image("spiderCard.png");
		Image ch12 = new Image("strangeCard.png");
		Image ch13 = new Image("thorCard.png");
		Image ch14 = new Image("venomCard.png");
		Image ch15 = new Image("yellowCard.png");

		Label tmp = new Label();	
		ImageView i  = new ImageView();
		switch(currentChampion.getName())
		{
			case "Captain America" : i= new ImageView(ch1);  break;
			case "Deadpool" : 	i= new ImageView(ch2);	     break;
			case "Dr Strange" : i= new ImageView(ch12);		 break;
			case "Electro" :i= new ImageView(ch3); 			 break;
			case "Ghost Rider" :	i= new ImageView(ch4);   break;
			case "Hela" : 		i= new ImageView(ch5);		 break;
			case "Hulk" : i= new ImageView(ch6);			 break;
			case "Iceman" : i= new ImageView(ch7);			 break;
			case "Ironman" : i= new ImageView(ch8);			 break;
			case "Loki" : i= new ImageView(ch9);			 break;
			case "Quicksilver" : i= new ImageView(ch10);	 break;
			case "Spiderman" : i= new ImageView(ch11);		 break;
			case "Thor" : i= new ImageView(ch13);			 break;
			case "Venom" : i= new ImageView(ch14);			 break;
			case "Yellow Jacket" : i= new ImageView(ch15);	 break;
		}
		tmp.setGraphic(i);
		tmp.setPrefSize(200, 119);
		return tmp;
	}

	
	public HBox healthBar(Damageable x)
	{
		HBox health = new HBox(5);
		Label hpInt  = new Label(x.getCurrentHP()+"");
		
		if (x instanceof Cover)
		{
			ProgressBar hp = new ProgressBar(x.getCurrentHP()*1.0/100);
			hp.setStyle("-fx-accent: purple");
			health.getChildren().addAll(hpInt , hp);
		
		}
		else 
		{
			ProgressBar hp = new ProgressBar(x.getCurrentHP()*1.0/((Champion)x).getMaxHP());
			health.getChildren().addAll(hpInt , hp);
			if (x instanceof Champion && game.getFirstPlayer().getTeam().contains(x))
			{
				hp.setStyle("-fx-accent: green");
			}
			else if (x instanceof Champion && game.getSecondPlayer().getTeam().contains(x))
			{
				hp.setStyle("-fx-accent: red");
			}
		}
		health.setAlignment(Pos.CENTER);
		return health;

	}
	public void printTeams(HBox h1 , HBox h2 ) 
	{
		for (int i=0 ; i< game.getFirstPlayer().getTeam().size() ; i++)
		{
			Label p = generateIconLabel(game.getFirstPlayer().getTeam().get(i).getName());
			h1.getChildren().add(p);
		}
		for (int i=0 ; i< game.getSecondPlayer().getTeam().size() ; i++)
		{
			Label p = generateIconLabel(game.getSecondPlayer().getTeam().get(i).getName());
			h2.getChildren().add(p);
		}
	}

	public void printTurn(HBox h)
	{
		h.getChildren().clear();
		Label l = new Label("Turn Order: ");
		h.getChildren().add(l);
		turn = new ArrayList<>();
		while (!turn.isEmpty())
		{
			turn.remove(0);
		}
		while(!game.getTurnOrder().isEmpty())
		{
			h.getChildren().add(generateIconLabel(((Champion)game.getTurnOrder().peekMin()).getName()));
			turn.add((Champion) game.getTurnOrder().remove());
		}
		while(!turn.isEmpty())
		{
			game.getTurnOrder().insert(turn.remove(0));
		}
		
		
	}
	
	
	public void highlightTurn (VBox firstP , VBox SecondP )
	{
		if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion()))
		{
			firstP.setDisable(false);
			SecondP.setDisable(true);
		}
		else 
		{
			SecondP.setDisable(false);
			firstP.setDisable(true);
		}
	}
	

	public void attackHelp( VBox sub, VBox direction , GridPane boardGrid)
	{
		
		playScreen.setOnKeyPressed(e -> 
		{	music3();
			
			//System.out.println(game.getTurnOrder().isEmpty());
			try
			{
				
				switch (e.getCode())
				{
				
					case W : game.attack(Direction.DOWN);  break;
					case S : game.attack(Direction.UP);break;
					case A : game.attack(Direction.LEFT);break;
					case D : game.attack(Direction.RIGHT);break;
				}
			}
			catch (NotEnoughResourcesException e1) {
				Popup pop = new Popup();
				pop.display("Alert", "No Enough Recources");
				System.out.println("no resources");
			} catch (ChampionDisarmedException e1) {
				Popup pop = new Popup();
				pop.display("Alert", "Can't Attack... This Champion is Disarmed");
				System.out.println("disarmed");
			} catch (InvalidTargetException e1) {
				Popup pop = new Popup();
				pop.display("Alert", "Invalid Target");
				System.out.println("invalid target");
			}

			direction.setVisible(false);
			putChamps(boardGrid);
			putCovers(boardGrid);
			currTurnInfo(sub , boardGrid , direction);
			checkGameOver();
			
		});
	}



	public void champSelect(Button b, VBox layout, Label l, Champion champ , HBox h , Button cont , FlowPane flow) // throws IOException
	{
		b.setDisable(true);
		layout.getChildren().add(l);
		h.getChildren().remove(0);
		
		if (firstSel % 2 == 0) 
		{
			Label title = new Label(p1 + " SELECT A CHAMPION");
			h.getChildren().add(title);
			game.getSecondPlayer().getTeam().add(champ);
			team2.add(champ);
			if (firstSel == 2)
			{
				game.getSecondPlayer().setLeader(champ);
			}
			else if (firstSel == 4)
			{
				
			}
			else if (firstSel == 6)
			{
				cont.setDisable(false);
				music2();
				flow.setDisable(true);
			}
		}
		else 
		{
			
			team1.add(champ);
			game.getFirstPlayer().getTeam().add(champ);
			if (firstSel == 1)
			{
				Label title = new Label(p2 + " SELECT YOUR LEADER CHAMPION");
				h.getChildren().add(title);
				game.getFirstPlayer().setLeader(champ);
			}
			else 
			{
				Label title = new Label(p2 + " SELECT A CHAMPION");
				h.getChildren().add(title);
			}
			
		}
		
	}
	
	
	public void takePosition(GridPane grid)
	{
		
		String h1 ;
		String h2 ;
		for (int i = 0 ; i<3 ; i++)
		{
			h1 = team1.get(i).getName();
			h2 = team2.get(i).getName();
			
			Label tmp1 =generateLabel(h1);
			Label tmp2= generateLabel(h2);
			
			GridPane.setHalignment(tmp1, HPos.CENTER);
			GridPane.setHalignment(tmp2, HPos.CENTER);
			grid.add(tmp1,1+i , 0);
			grid.add(tmp2,1+i , 4);
		}
	
	}
	
	
	public void putChamps(GridPane grid)
	{
		//System.out.println(game.getCurrentChampion().getLocation().x + " " + game.getCurrentChampion().getLocation().y);
		grid.getChildren().clear();
		
		Object [][] board = game.getBoard();

		for (int i=0 ; i<5 ; i++)
		{
			for (int j = 0 ; j<5  ; j++)
			{
				if (board[i][j] instanceof Champion)
				{
					String s = ((Champion)board[i][j]).getName();
					Label tmp = generateLabel(s);
					HBox health = healthBar((Damageable) board[i][j]);
					VBox v = new VBox (5);
					v.getChildren().addAll(tmp , health );
					
					v.setAlignment(Pos.CENTER);
					grid.add(v , j, i);
					grid.setHalignment(v, HPos.CENTER);
					grid.setValignment(v, VPos.CENTER);
					grid.setHalignment(health, HPos.CENTER);

				}
			}
			
		}
		
		putCovers(grid);
	}
	public void putCovers(GridPane pane)
	{
		Image c1 = new Image("sat.png");
		BackgroundImage bImg1 = new BackgroundImage(c1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background cP = new Background(bImg1);
		Object [][] board = game.getBoard();

		
		for (int i=0 ; i<5 ; i++)
		{
			for (int j = 0 ; j<5  ; j++)
			{
				if (board[i][j] instanceof Cover)
				{
					ImageView coverP = new ImageView(c1);
					Label l = new Label();
					
					VBox v = new VBox (5);
					v.getChildren().addAll(l , healthBar((Damageable) board[i][j]));
					v.setAlignment(Pos.CENTER);
					
					pane.setHalignment(v, HPos.CENTER);
					l.setGraphic(coverP);
					pane.add(v, j, i);
				}
			}
		}
	}
	
	
	public Label generateLabel(String champName)
	{
		Image ch1 = new Image("captain2.gif");
		BackgroundImage bImg3 = new BackgroundImage(ch1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background captainP = new Background(bImg3);
		
		Image ch2 = new Image("deadpool.gif");
		BackgroundImage bImg4 = new BackgroundImage(ch2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background deadpoolP = new Background(bImg4);

		Image ch3 = new Image("strange.gif");
		BackgroundImage bImg5 = new BackgroundImage(ch3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background strangeP = new Background(bImg5);
		
		Image ch4 = new Image("electro.gif");
		BackgroundImage bImg8 = new BackgroundImage(ch4, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background electroP = new Background(bImg8);
		
		Image ch5 = new Image("ghost.gif");
		BackgroundImage bImg9 = new BackgroundImage(ch5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background ghostP = new Background(bImg9);
		
		Image ch6 = new Image("hela.gif");
		BackgroundImage bImg10 = new BackgroundImage(ch6, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background helaP = new Background(bImg10);
		
		Image ch7 = new Image("hulk.gif");
		BackgroundImage bImg11 = new BackgroundImage(ch7, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background hulkP = new Background(bImg11);
		
		Image ch8 = new Image("iceman.gif");
		BackgroundImage bImg12 = new BackgroundImage(ch8, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background icemanP = new Background(bImg12);
		
		
		Image ch9 = new Image("ironman.gif");
		BackgroundImage bImg13= new BackgroundImage(ch9, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background ironmanP = new Background(bImg13);
		
		Image ch10 = new Image("loki.gif");
		BackgroundImage bImg14 = new BackgroundImage(ch10, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background lokiP = new Background(bImg14);
		
		Image ch11 = new Image("quicksilver.gif");
		BackgroundImage bImg15 = new BackgroundImage(ch11, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background quicksilverP = new Background(bImg15);
		
		Image ch12 = new Image("spiderman.gif");
		BackgroundImage bImg16 = new BackgroundImage(ch12, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background spidermanP = new Background(bImg16);

		Image ch13 = new Image("thor.gif");
		BackgroundImage bImg17 = new BackgroundImage(ch13, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background thorP = new Background(bImg17);
		
		Image ch14 = new Image("venom.gif");
		BackgroundImage bImg18 = new BackgroundImage(ch14, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background venomP = new Background(bImg18);
		
		Image ch15 = new Image("yellow.gif");
		BackgroundImage bImg19 = new BackgroundImage(ch15, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background yellowjacketP = new Background(bImg19);
		
		
		Label tmp = new Label();
		
		ImageView i  = new ImageView(ch1);
		switch(champName)
		{
			case "Captain America" : i= new ImageView(ch1); tmp.setGraphic(i); break;
			case "Deadpool" : 	i= new ImageView(ch2);	    tmp.setGraphic(i); break;
			case "Dr Strange" : i= new ImageView(ch3);		tmp.setGraphic(i); break;
			case "Electro" :i= new ImageView(ch4); 			tmp.setGraphic(i); break;
			case "Ghost Rider" :	i= new ImageView(ch5);  tmp.setGraphic(i); break;
			case "Hela" : 		i= new ImageView(ch6);		tmp.setGraphic(i); break;
			case "Hulk" : i= new ImageView(ch7);			tmp.setGraphic(i); break;
			case "Iceman" : i= new ImageView(ch8);			tmp.setGraphic(i); break;
			case "Ironman" : i= new ImageView(ch9);			tmp.setGraphic(i); break;
			case "Loki" : i= new ImageView(ch10);			tmp.setGraphic(i); break;
			case "Quicksilver" : i= new ImageView(ch11);	tmp.setGraphic(i); break;
			case "Spiderman" : i= new ImageView(ch12);		tmp.setGraphic(i); break;
			case "Thor" : i= new ImageView(ch13);			tmp.setGraphic(i); break;
			case "Venom" : i= new ImageView(ch14);			tmp.setGraphic(i); break;
			case "Yellow Jacket" : i= new ImageView(ch15);	tmp.setGraphic(i); break;
		}
		tmp.setPrefSize(50, 50);
		return tmp;
	}
	
	
	
	public Label generateIconLabel(String champName)
	{
		Image ch1 = new Image("captainIcon.png");
		BackgroundImage bImg3 = new BackgroundImage(ch1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background captainP = new Background(bImg3);
		
		Image ch2 = new Image("deadpoolIcon.png");
		BackgroundImage bImg4 = new BackgroundImage(ch2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background deadpoolP = new Background(bImg4);

		Image ch3 = new Image("strangeIcon.png");
		BackgroundImage bImg5 = new BackgroundImage(ch3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background strangeP = new Background(bImg5);
		
		Image ch4 = new Image("electroIcon.png");
		BackgroundImage bImg8 = new BackgroundImage(ch4, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background electroP = new Background(bImg8);
		
		Image ch5 = new Image("ghostIcon.png");
		BackgroundImage bImg9 = new BackgroundImage(ch5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background ghostP = new Background(bImg9);
		
		Image ch6 = new Image("helaIcon.png");
		BackgroundImage bImg10 = new BackgroundImage(ch6, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background helaP = new Background(bImg10);
		
		Image ch7 = new Image("hulkIcon.png");
		BackgroundImage bImg11 = new BackgroundImage(ch7, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background hulkP = new Background(bImg11);
		
		Image ch8 = new Image("icemanIcon.png");
		BackgroundImage bImg12 = new BackgroundImage(ch8, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background icemanP = new Background(bImg12);
		
		
		Image ch9 = new Image("ironmanIcon.png");
		BackgroundImage bImg13= new BackgroundImage(ch9, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background ironmanP = new Background(bImg13);
		
		Image ch10 = new Image("lokiIcon.png");
		BackgroundImage bImg14 = new BackgroundImage(ch10, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background lokiP = new Background(bImg14);
		
		Image ch11 = new Image("quicksilverIcon.png");
		BackgroundImage bImg15 = new BackgroundImage(ch11, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background quicksilverP = new Background(bImg15);
		
		Image ch12 = new Image("spidermanIcon.png");
		BackgroundImage bImg16 = new BackgroundImage(ch12, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background spidermanP = new Background(bImg16);

		Image ch13 = new Image("thorIcon.png");
		BackgroundImage bImg17 = new BackgroundImage(ch13, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background thorP = new Background(bImg17);
		
		Image ch14 = new Image("venomIcon.png");
		BackgroundImage bImg18 = new BackgroundImage(ch14, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background venomP = new Background(bImg18);
		
		Image ch15 = new Image("yellowIcon.png");
		BackgroundImage bImg19 = new BackgroundImage(ch15, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background yellowjacketP = new Background(bImg19);
		
		
		Label tmp = new Label();
		ImageView i  = new ImageView(ch1);
		switch(champName)
		{
			case "Captain America" : i= new ImageView(ch1); tmp.setGraphic(i); break;
			case "Deadpool" : 	i= new ImageView(ch2);	    tmp.setGraphic(i); break;
			case "Dr Strange" : i= new ImageView(ch3);		tmp.setGraphic(i); break;
			case "Electro" :i= new ImageView(ch4); 			tmp.setGraphic(i); break;
			case "Ghost Rider" :	i= new ImageView(ch5);  tmp.setGraphic(i); break;
			case "Hela" : 		i= new ImageView(ch6);		tmp.setGraphic(i); break;
			case "Hulk" : i= new ImageView(ch7);			tmp.setGraphic(i); break;
			case "Iceman" : i= new ImageView(ch8);			tmp.setGraphic(i); break;
			case "Ironman" : i= new ImageView(ch9);			tmp.setGraphic(i); break;
			case "Loki" : i= new ImageView(ch10);			tmp.setGraphic(i); break;
			case "Quicksilver" : i= new ImageView(ch11);	tmp.setGraphic(i); break;
			case "Spiderman" : i= new ImageView(ch12);		tmp.setGraphic(i); break;
			case "Thor" : i= new ImageView(ch13);			tmp.setGraphic(i); break;
			case "Venom" : i= new ImageView(ch14);			tmp.setGraphic(i); break;
			case "Yellow Jacket" : i= new ImageView(ch15);	tmp.setGraphic(i); break;
		}
		return tmp;
	}
	public Champion findChamp(String n) {
		
		for (int i = 0; i < game.getAvailableChampions().size(); i++) {
			if (game.getAvailableChampions().get(i).getName().equals(n)) {
				return game.getAvailableChampions().get(i);
			}
			
		}
		return null;
	}
	
	
	public void endTurnHelper()
	{
		
		game.endTurn();

	}
	
	public String toString (Champion champ)
	   {
		   String c="";
		   
		    if (champ instanceof Hero )
			   c+= "Name :" + champ.getName() + "\n"+
		          "Type :" + "Hero" + "\n"+
		          "Health point :" +champ.getCurrentHP() +"\n"+ 
				  "Mana :"+champ.getMana()+"\n"+ 
				  "Action point :"+champ.getCurrentActionPoints()+"\n"+
				  "Speed :" + champ.getSpeed() +"\n"+
	              "Attack damage :" +champ.getAttackDamage() +"\n"+ 
	              "Attack Range :" + champ.getAttackRange() +"\n";
		   else if (champ instanceof Villain )
				  c+= "Name :" + champ.getName() + "\n"+
			      "Type :" + "Villain :" + "\n"+
			      "Health point :" + champ.getCurrentHP() +"\n"+ 
			      "Mana cost :"+ champ.getMana()+"\n"+ 
			      "Action point :"+ champ.getCurrentActionPoints()+"\n"+
				  "Speed :" +champ.getSpeed() +"\n"+
				  "Attack damage :" + champ.getAttackDamage() +"\n"+ 
		          "Attack Range :" +champ.getAttackRange() +"\n";
		   else if (champ instanceof AntiHero )
			   c+= "Name :" + champ.getName() + "\n"+
		          "Type :" + "AntiHero" + "\n"+
		          "Health point :" + champ.getCurrentHP() +"\n"+ 
				  "Mana cost :"+ champ.getMana()+"\n"+ 
				  "Action point :"+ champ.getCurrentActionPoints()+"\n"+
				  "Speed :" + champ.getSpeed() +"\n"+
	              "Attack damage :" + champ.getAttackDamage() +"\n"+ 
	              "Attack Range :" + champ.getAttackRange() +"\n";
		   
		   return c;
	   }
	   public String info(String s )
		{
			Champion c= null ;
			for (int i=0 ;i<game.getAvailableChampions().size();i++)
			{
				if (game.getAvailableChampions().get(i).getName().equals(s))
					c= game.getAvailableChampions().get(i);
			}
			return toString(c);
		}
	  
	   
	   public String abilityInfo(String n)
	   {
		   String info = "";
		   for (int i=0 ; i<game.getAvailableAbilities().size() ; i++)
		   {
			   if (game.getAvailableAbilities().get(i).getName().equals(n))
			   {
				   Ability ab= game.getAvailableAbilities().get(i);
				   
				   info+= ab.getName() + "\n"+ 
				   			"Type: "+ ab.getCastArea() + "\n" + 
				   			"Required Action Pts: " + ab.getRequiredActionPoints()+"\n"+
				   			"Mana Cost: "+ ab.getManaCost() + "\n" +
				   			"Range: "+ab.getCastRange() + "\n" + 
				   			"Base Cooldown: " + ab.getBaseCooldown() + "\n" +
				   			"Current Cooldown: " + ab.getCurrentCooldown() + "\n";
				   			
				   if (ab instanceof DamagingAbility)
				   {
					   info+= "Ability Type: Damaging Ability" + "\n"+
							  "Damage Amount: " + ((DamagingAbility)ab).getDamageAmount() ;
					   break;
				   }
				   else if( ab instanceof HealingAbility)
				   {
					   info+= "Ability Type: Healing Ability" + "\n"+
							  "Healing Amount: " + ((HealingAbility)ab).getHealAmount(); 
					   break;
				   }
				   else
				   {
					   info+= "Ability Type: Crowd Control Ability" + "\n"+
							  "Effect Type: " + ((CrowdControlAbility)ab).getEffect().getName() + " --Duration: " +
							  ((CrowdControlAbility)ab).getEffect().getDuration(); 
					   break;
				   }
			   }
		   }
		   
		   if (n.equals("Punch"))
		   {
			   info+= "Name: Punch" + "\n " + "Type: Damaging Ability" + "\n" + "Damage Amount: 50" + 
					   	"\n" + "Range: 1" + "\n" + "Required Action Pts: 1";
		   }
		   return info;
		   
	   }
	   
	   public void music() {
			String s = "Song.mp4";
			Media h = new Media(Paths.get(s).toUri().toString());
			mediaplayer = new MediaPlayer(h);
			mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
			mediaplayer.setVolume(0.7);
			
			mediaplayer.play();
		}
	   
	   public void music2() {
			String s = "assemble.mp3";
			Media h = new Media(Paths.get(s).toUri().toString());
			mediaplayer = new MediaPlayer(h);
			mediaplayer.play();
		}
	   
	   public void music3() {
			String s = "attack.mp3";
			Media h = new Media(Paths.get(s).toUri().toString());
			mediaplayer = new MediaPlayer(h);
			mediaplayer.play();
		}
	   
	   public void music4() {
			String s = "snap.mp3";
			Media h = new Media(Paths.get(s).toUri().toString());
			mediaplayer = new MediaPlayer(h);
			mediaplayer.setVolume(0.2);
			mediaplayer.play();
		}
	   
	   
	public static void main(String[] args) throws IOException {
		
//		String uriString = new File("D:\\GUC\\Semester 4\\CS 401\\Game\\Images\\temp\\Avengers.mp3").toURI().toString();
//	    MediaPlayer player = new MediaPlayer( new Media(uriString));
//	    player.play();
		launch(args);
		
	}

}
