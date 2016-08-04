package project4;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

//controls the animation speed - how many frames to be displayed
	double framesPerSec = 1;	 
//will position the top-left corner of the canvas on the window
	int startRowGame = 25;
	int startColGame = 300;
	public static void main(String[] args) {
        launch(args);
    }

	@Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("CRITTERS");
//create a root which is attached to the scene. this root will then get more children attached to it
    	Group root = new Group();
//create a gridpane which will include the buttons
//the default location is the top-left corner of the window/scene
    	GridPane grid = new GridPane();
//sets the columns width of desired nunmber of pixels
    	grid.getColumnConstraints().add(new ColumnConstraints(75)); //the 0th columns
    	grid.getColumnConstraints().add(new ColumnConstraints(120)); //the first column
    	grid.getColumnConstraints().add(new ColumnConstraints(75)); //the second column
    	grid.setPadding(new Insets(10, 10, 10, 10)); //create a padding around the grid
    	grid.setVgap(5); //5 pixel gap between columns on the grid
    	grid.setHgap(5); //5 pixel gap between the rows on the grid
//command to create a new button with the title
//button name is btnPause and the title is PAUSE
    	Button btnPause = new Button("PAUSE"); 
//places the button on the grid in 0 col and 0 row of the grid    	
    	GridPane.setConstraints(btnPause,0,0);
//adds the button to the grid
    	grid.getChildren().add(btnPause);
		Button btnPlay = new Button("PLAY");
    	GridPane.setConstraints(btnPlay,1,0);
    	grid.getChildren().add(btnPlay);
		Button btnQuit = new Button("QUIT");
    	GridPane.setConstraints(btnQuit,2,0);
    	grid.getChildren().add(btnQuit);
		TextField num1 = new TextField();
		num1.setMaxWidth(50);
		num1.setText("1");
    	GridPane.setConstraints(num1,0,1);
    	grid.getChildren().add(num1);
		Button btnSeed = new Button("SEED");
    	GridPane.setConstraints(btnSeed,1,1);
    	grid.getChildren().add(btnSeed);
		TextField num2 = new TextField();
		num2.setMaxWidth(50);
		num2.setText("1");
    	GridPane.setConstraints(num2,0,2);
    	grid.getChildren().add(num2);
		ComboBox<String> critterMakeCombo = new ComboBox<>();
		critterMakeCombo.getItems().addAll(
				"Dumbo",
				"Ent",
				"FraidyCat",
				"Tribble"
		);
		critterMakeCombo.setValue("Dumbo");
    	GridPane.setConstraints(critterMakeCombo,1,2);
    	grid.getChildren().add(critterMakeCombo);
		Button btnMake = new Button("MAKE");
    	GridPane.setConstraints(btnMake,2,2);
    	grid.getChildren().add(btnMake);
		ComboBox<String> critterStatsCombo = new ComboBox();
		critterStatsCombo.getItems().addAll(
				"Dumbo",
				"Ent",
				"FraidyCat",
				"Tribble",
				"Critter"
		);
		critterStatsCombo.setValue("Critter");
    	GridPane.setConstraints(critterStatsCombo,1,3);
    	grid.getChildren().add(critterStatsCombo);
		Button btnStats = new Button("STATS");
    	GridPane.setConstraints(btnStats,2,3);
    	grid.getChildren().add(btnStats);
	
    	RadioButton rb1 = new RadioButton("1/100");
    	RadioButton rb2 = new RadioButton("1/50");
    	RadioButton rb3 = new RadioButton("1/20");
    	RadioButton rb4 = new RadioButton("1/10");
    	RadioButton rb5 = new RadioButton("1/5");
    	RadioButton rb6 = new RadioButton("1/2");
		RadioButton rb7 = new RadioButton("1");
		RadioButton rb8 = new RadioButton("2");
		RadioButton rb9 = new RadioButton("5");
		RadioButton rb10 = new RadioButton("10");
		RadioButton rb11 = new RadioButton("20");
		RadioButton rb12 = new RadioButton("50");
		RadioButton rb13 = new RadioButton("100");
		ToggleGroup tg = new ToggleGroup();
		tg.getToggles().addAll(rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11, rb12, rb13);
    	GridPane.setConstraints(rb1,0,4);
    	GridPane.setConstraints(rb2,0,5);
    	GridPane.setConstraints(rb3,0,6);
    	GridPane.setConstraints(rb4,0,7);
    	GridPane.setConstraints(rb5,0,8);
    	GridPane.setConstraints(rb6,0,9);
    	GridPane.setConstraints(rb7,0,10);
    	GridPane.setConstraints(rb8,0,11);
    	GridPane.setConstraints(rb9,0,12);
    	GridPane.setConstraints(rb10,0,13);
    	GridPane.setConstraints(rb11,0,14);
    	GridPane.setConstraints(rb12,0,15);
    	GridPane.setConstraints(rb13,0,16);
    	grid.getChildren().add(rb1);
    	grid.getChildren().add(rb2);
    	grid.getChildren().add(rb3);
    	grid.getChildren().add(rb4);
    	grid.getChildren().add(rb5);
    	grid.getChildren().add(rb6);
    	grid.getChildren().add(rb7);
    	grid.getChildren().add(rb8);
    	grid.getChildren().add(rb9);
    	grid.getChildren().add(rb10);
    	grid.getChildren().add(rb11);
    	grid.getChildren().add(rb12);
    	grid.getChildren().add(rb13);
    	
//create a new grid to display the runstats message
    	GridPane gridMsg = new GridPane();
//instead of default position this grid to col startColGame
    	gridMsg.setLayoutX(startColGame);
//create a new label
    	Label lb = new Label();
//position the label at 0 col in 0 row
    	GridPane.setConstraints(lb,0,0);
//add that label to the gridMsg    	
    	gridMsg.getChildren().add(lb);
		
//controls the animation
    	Timeline timeline = new Timeline();

//create a canvas of a size 5 times the world size.
//the multiplication by 5 is so that we can see the critters with our eyes and they dont overlap each other
    	Canvas canvas = new Canvas(Params.world_width * 5, Params.world_height * 5);
//positions the canvas to col 250 and row 25 on the window
    	canvas.setLayoutX(startColGame);
    	canvas.setLayoutY(startRowGame);
    	
//below code is trap and handle the events triggered by the keyboard and mouse and animation
     	btnPause.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			timeline.pause(); //pauses the animation
      		}

      	});
     	btnPlay.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			timeline.play(); //plays the animation
      		}
      	});
     	btnQuit.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			primaryStage.close(); //close the stage and program
      		}

      	});
     	btnSeed.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
  				String input = num1.getText();
  				int j = getNumber(input);
  				if (j > 0){
  					Critter.setSeed(j);
  				}
      		}
      	});
     	btnMake.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			System.out.println("make");
      			try {
      				String input = num2.getText();
      				int j = getNumber(input);
      				String str = critterMakeCombo.getValue();
      				str = "project4." + str;
      				for (int i = 0; i < j; i++){
      					Critter.makeCritter(str);
      				}
				} catch (InvalidCritterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
      		}
      	});

     	rb1.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			framesPerSec =  1.0/100.0;
      			System.out.println("rb1 framesPerSec:" + framesPerSec);
      		}
        });
     	rb2.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			framesPerSec =  1.0/50.0;
      			System.out.println("rb2 framesPerSec:" + framesPerSec);
      		}
        });
    	rb12.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			framesPerSec =  50.0;
      			System.out.println("rb12 framesPerSec:" + framesPerSec);
      		}
        });
    	rb13.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			framesPerSec =  100.0;
      			
      			System.out.println("rb13 framesPerSec:" + framesPerSec);
      		}
        });
//adds the grid, canvas and gridMsg to the root
        root.getChildren().add(grid);
        root.getChildren().add(canvas);
        root.getChildren().add(gridMsg);
//adds the root to the scene and the scene to the stage
    	primaryStage.setScene(new Scene(root));
//adds the facility to be able to add graphic contents to the canvas, via the gc
//later we will use gc. commands to add text and graphics
    	GraphicsContext gc = canvas.getGraphicsContext2D();

//sets the timeline for the animation to infinite    	
        timeline.setCycleCount(Timeline.INDEFINITE); 
//        double k = framesPerSec;
//        if (framesPerSec > 1.0){
//        	k = 1.0;
//        }
//event to handle the key frame.
//here we will make changes to the frame after every interruption
//the interruption is based on the time in Duration controlling how many frames to be dispplayed per sec
        KeyFrame kf = new KeyFrame(
        		 Duration.seconds(1),                // 60 FPS
                 new EventHandler<ActionEvent>(){
                     public void handle(ActionEvent ae)
                 {
                 // Clear the canvas, by painting an empty rectangle at the starting corner
                 gc.clearRect(0, 0, Params.world_width * 5, Params.world_height * 5);
                 int j = 1;
  //               if (framesPerSec > 1.0){
                	 j = (int) (framesPerSec);
//                 }
                 for (int i = 0; i < j; i++){
                     Critter.worldTimeStep();
                 }
               //this method will find which critters at what location and write  a string.image at that location on the canvas
                drawShapes(gc);
   				String str = critterStatsCombo.getValue();
   				String str2 = "project4." + str;
//sets the text in the label with the runstats message returned   				
   				try {
   					if (str.equals("Critter")){
   						lb.setText(  Critter.runStatsString(Critter.getInstances(str2)));
   					}
   					if (str.equals("Dumbo")){
   						lb.setText(  Dumbo.runStatsString(Critter.getInstances(str2)));
   					}
   					if (str.equals("Ent")){
   						lb.setText(  Ent.runStatsString(Critter.getInstances(str2)));
   					}
   					if (str.equals("FraidyCat")){
   						lb.setText(  FraidyCat.runStatsString(Critter.getInstances(str2)));
   					}
   					if (str.equals("Tribble")){
   						lb.setText(  Tribble.runStatsString(Critter.getInstances(str2)));
   					}
 				} 	catch (InvalidCritterException e1) {
//this create a pop up window to display . it could have been error, warning or information box
 					Alert alert = new Alert(AlertType.ERROR);
 			    	alert.setTitle("ERROR");
 			    	alert.setContentText("Invalid Critter Name");
 			    	alert.showAndWait();
 					}
 				
                }
             }
        );
		            
//adds the keyframe to the timline. multiple time frames could ahve been added
        timeline.getKeyFrames().add(kf);
        timeline.play();       
//shows the stage on the computer screen
        primaryStage.show();
//      primaryStage.close();
    }

    private void drawShapes(GraphicsContext gc) {
		gc.setFont(new Font(STYLESHEET_CASPIAN,5));
		for (int i = 1; i <= Params.world_height;i++){
			for (int j = 1; j <= Params.world_width;j++){
				for (int k = 0; k < Critter.getPopulation().size(); k++){
					if (((Critter.getPopulation().get(k)).getX_coord() == j) && ((Critter.getPopulation().get(k)).getY_coord() == i)){ 
						gc.setFill(Color.GREEN);
						String str = Critter.getPopulation().get(k).getClass().toString();
						str = str.trim();
						if (str.equals("class project4.Dumbo")){
							gc.setFill(Color.RED);
						}
						if (str.equals("class project4.Ent")){
							gc.setFill(Color.BLUE);
						}
						if (str.equals("class project4.FraidyCat")){
							gc.setFill(Color.BLACK);
						}
						if (str.equals("class project4.Tribble")){
							gc.setFill(Color.ORANGE);
						}
						String str2 = Critter.getPopulation().get(k).toString();
//trying to experiment with images instead of the string
//						Image img = new Image(getClass().getResourceAsStream("Dumbo.jpg"));
//						Image img = new Image("Beetle.png");
//						gc.drawImage(img, 10 + j * 5, i * 5, 5, 5);
						gc.fillText(str2, j * 5, i * 5);
//						gc.fillRect(Params.world_width + j * 5, i * 5, 5, 5);
						break;
					}
				}
			}
		}
	}

    public static int getNumber(String str){
    	Alert alert = new Alert(AlertType.ERROR);
		int i = 0;
		if (str == null || str == "" || str == " "){
			return 1;
		}
		try { 
			i = Integer.parseInt(str);
		}
		 catch(NumberFormatException er) {
	    	alert.setTitle("ERROR");
	    	alert.setContentText("Invalid Number");
	    	alert.showAndWait();
			return 0;
		}
		if (i < 1){
	    	alert.setTitle("ERROR");
	    	alert.setContentText("Invalid Number");
	    	alert.showAndWait();
			return 0;
		}
		return i;
	}
}

