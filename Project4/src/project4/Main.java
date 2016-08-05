package project4;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
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

//controls the animation speed - how many frames to be displayed in seconds
	int countWithout = 1;
	int countWithoutShow = 1;
	int numOfWorldSteps = 1;
//will position the top-left corner of the canvas on the window
	int startRowGame = 25;
	int startColGame = 300;
//	public static void main(String[] args) {
//        launch(args);
//    }

	@Override
    public void start(Stage primaryStage) {
//sets the stage
    	primaryStage.setTitle("CRITTERS");
 //create a root which is attached to the scene. this root will then get more children attached to it
    	Group root = new Group();
//create a gridpane which will include the buttons
//the default location is the top-left corner of the window/scene
    	GridPane grid = new GridPane();
//sets the columns width of desired nunmber of pixels
    	
    	grid.getColumnConstraints().add(new ColumnConstraints(80)); //the 0th columns
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

   
//***************************************************************************************************//    	
  /*** below code just creates new buttons, labels, radiobuttons etc and adds them to pane ***/
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
		
    	ComboBox<String> critterStatsCombo = new ComboBox<String>();
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

    	Label lbAnim = new Label("Animation");
    	Label lbAnim2 = new Label("Speed");
    	
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
		tg.selectToggle(rb7);
    	
		GridPane.setConstraints(lbAnim,0,4);
    	GridPane.setConstraints(lbAnim2,0,5);
    	GridPane.setConstraints(rb1,0,6);
    	GridPane.setConstraints(rb2,0,7);
    	GridPane.setConstraints(rb3,0,8);
    	GridPane.setConstraints(rb4,0,9);
    	GridPane.setConstraints(rb5,0,10);
    	GridPane.setConstraints(rb6,0,11);
    	GridPane.setConstraints(rb7,0,12);
    	GridPane.setConstraints(rb8,0,13);
    	GridPane.setConstraints(rb9,0,14);
    	GridPane.setConstraints(rb10,0,15);
    	GridPane.setConstraints(rb11,0,16);
    	GridPane.setConstraints(rb12,0,17);
    	GridPane.setConstraints(rb13,0,18);
    	
    	grid.getChildren().add(lbAnim);
    	grid.getChildren().add(lbAnim2);
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
    	
    	Label lbSteps = new Label("World Steps");
		RadioButton rb14 = new RadioButton("1");
		RadioButton rb15 = new RadioButton("2");
		RadioButton rb16 = new RadioButton("5");
		RadioButton rb17 = new RadioButton("10");
		RadioButton rb18 = new RadioButton("20");
		RadioButton rb19 = new RadioButton("50");
		RadioButton rb20 = new RadioButton("100");
		RadioButton rb21 = new RadioButton("1000");
		RadioButton rb22 = new RadioButton("10000");
		
		ToggleGroup tg2 = new ToggleGroup();
		tg2.getToggles().addAll(rb14, rb15, rb16, rb17, rb18, rb19, rb20, rb21, rb22);
		tg2.selectToggle(rb14);
    	
		GridPane.setConstraints(lbSteps,1,4);
    	GridPane.setConstraints(rb14,1,5);
    	GridPane.setConstraints(rb15,1,6);
    	GridPane.setConstraints(rb16,1,7);
    	GridPane.setConstraints(rb17,1,8);
    	GridPane.setConstraints(rb18,1,9);
    	GridPane.setConstraints(rb19,1,10);
    	GridPane.setConstraints(rb20,1,11);
    	GridPane.setConstraints(rb21,1,12);
    	GridPane.setConstraints(rb22,1,13);
    	grid.getChildren().add(lbSteps);
    	grid.getChildren().add(rb14);
    	grid.getChildren().add(rb15);
    	grid.getChildren().add(rb16);
    	grid.getChildren().add(rb17);
    	grid.getChildren().add(rb18);
    	grid.getChildren().add(rb19);
    	grid.getChildren().add(rb20);
    	grid.getChildren().add(rb21);
    	grid.getChildren().add(rb22);
    	
//********************************************************************************************//
    	//						STATS GRID
    	
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
		
//***********************************************************************************************//    	
    	//implements the button functionality
    	
    	
    	
    	//controls the animation
    	Timeline timeline = new Timeline();

 	
/***below code is trap and handle the events triggered by the keyboard and mouse and animation***/
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
      			try {
      				String input = num2.getText();
      				int j = getNumber(input);
      				String str = critterMakeCombo.getValue();
      				str = "project4." + str;
      				for (int i = 0; i < j; i++){
      					Critter.makeCritter(str);
      				}
				} catch (InvalidCritterException e1) {
					e1.printStackTrace();
				}
      		}
      	});

     	rb1.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			countWithoutShow =  100;
      		}
        });
     	rb2.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			countWithoutShow = 50;
      		}
        });
     	rb3.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			countWithoutShow = 20;
      		}
        });
     	rb4.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			countWithoutShow = 10;
      		}
        });
     	rb5.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			countWithoutShow = 5;
      		}
        });
     	rb6.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			countWithoutShow = 2;
      		}
        });
     	rb7.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			countWithoutShow = 1;
      		}
        });
     	rb8.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			countWithoutShow = -2;
      			timeline.setRate(2.0);
      		}
        });
     	rb9.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			countWithoutShow = -5;
      			timeline.setRate(5.0);
      		}
        });
     	rb10.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			countWithoutShow = -10;
      			timeline.setRate(10.0);
      		}
        });
     	rb11.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			countWithoutShow = -20;
      			timeline.setRate(20.0);
      		}
        });
    	rb12.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			countWithoutShow = -50;
      			timeline.setRate(50.0);

      		}
        });
    	rb13.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			countWithoutShow = -100;
      			timeline.setRate(100.0);
      		}
        });

    	rb18.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			numOfWorldSteps = 20;
      		}
        });
    	rb19.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			numOfWorldSteps = 50;
      		}
        });
    	rb20.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			numOfWorldSteps = 100;
      		}
        });
    	rb21.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			numOfWorldSteps = 1000;
      		}
        });
    	rb22.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			numOfWorldSteps = 10000;
      		}
        });
//***************************************************************************************************
    			//Creating Canvas//
    	
    	//create a canvas of a size 5 times the world size.
    	//the multiplication by 5 is so that we can see the critters with our eyes and they dont overlap each other
    	    	Canvas canvas = new Canvas(Params.world_width * Params.pixel_scale, Params.world_height * Params.pixel_scale);
    	//positions the canvas to col 250 and row 25 on the window
    	    	canvas.setLayoutX(startColGame);
    	    	canvas.setLayoutY(startRowGame);
    	    	
//*************************************************************************************************** 
    	    	//adding Nodes to Root & Scene
    	    	
    	//adds the grid, canvas and gridMsg to the root
        root.getChildren().add(grid);
        root.getChildren().add(canvas);
        root.getChildren().add(gridMsg);
        //adds the root to the scene and the scene to the stage
    	primaryStage.setScene(new Scene(root));

    	//sets the time line for the animation to infinite    	
        timeline.setCycleCount(Timeline.INDEFINITE); 

        Duration duration = Duration.seconds(0.5);
        
        
        countWithout = countWithoutShow;
            
        EventHandler<ActionEvent> endEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                timeline.pause();       

                //MANUAL DELAY
            	if (countWithout > 1){
            		countWithout--;
            	}
            	
            	else {
            		countWithout = countWithoutShow;

            		for (int i = 0; i < numOfWorldSteps; i++){
            			Critter.worldTimeStep();
            		}
                	Critter.displayWorld(canvas,lb,critterStatsCombo.getValue());
                }
                timeline.play();       
            }
        };

        KeyFrame kf2 = new KeyFrame(duration, endEvent);
        timeline.getKeyFrames().addAll(kf2);

/* WORKS
        Duration duration = Duration.seconds(framesPerSec);
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Duration duration = Duration.seconds(framesPerSec);
                for (int i = 0; i < numOfWorldSteps; i++){
                    Critter.worldTimeStep();
                }
              	 animate(gc,lb,critterStatsCombo.getValue());
            }
        };
        KeyFrame kf = new KeyFrame(duration, onFinished);
//adds the keyframe to the timline. multiple time frames could ahve been added
        timeline.getKeyFrames().add(kf);
*/

        
        timeline.play();       


        //shows the stage on the computer screen
        primaryStage.show();
//      primaryStage.close();
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

