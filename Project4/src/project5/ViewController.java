package project5;
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

public class ViewController extends Application {

//controls the animation speed - how many frames to be displayed
	double framesPerSec = 1.0;
	int numOfWorldSteps = 1;
	Duration duration = new Duration(framesPerSec);
//will position the top-left corner of the canvas on the window
	int startRowGame = 25;
	int startColGame = 300;
	static final int p = 5; //number of pixels for each coordinate in the game
	static final int q = 20; //number of pixels in a row for each critter shape
//	public static void main(String[] args) {
//        launch(args);
//    }

	@Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("CRITTERS");
 //create a root which is attached to the scene. this root will then get more children attached to it
    	Group root = new Group();
//create a gridpane which will include the buttons
//the default location is the top-left corner of the window/scene
    	GridPane grid = new GridPane();
//sets the columns width of desired nunmber of pixels
    	
    	grid.getColumnConstraints().add(new ColumnConstraints(55)); //the 0th columns
    	grid.getColumnConstraints().add(new ColumnConstraints(80)); //the first column
    	grid.getColumnConstraints().add(new ColumnConstraints(50)); //the second column
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

    	Label lbAnim = new Label("Animation Speed");
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
    	GridPane.setConstraints(lbAnim,0,4);
    	GridPane.setConstraints(rb1,0,5);
    	GridPane.setConstraints(rb2,0,6);
    	GridPane.setConstraints(rb3,0,7);
    	GridPane.setConstraints(rb4,0,8);
    	GridPane.setConstraints(rb5,0,9);
    	GridPane.setConstraints(rb6,0,10);
    	GridPane.setConstraints(rb7,0,11);
    	GridPane.setConstraints(rb8,0,12);
    	GridPane.setConstraints(rb9,0,13);
    	GridPane.setConstraints(rb10,0,14);
    	GridPane.setConstraints(rb11,0,15);
    	GridPane.setConstraints(rb12,0,16);
    	GridPane.setConstraints(rb13,0,17);
    	grid.getChildren().add(lbAnim);
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
//      			System.out.println("make");
      			try {
      				String input = num2.getText();
      				int j = getNumber(input);
      				String str = critterMakeCombo.getValue();
      				str = "project5." + str;
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
      			framesPerSec =  100.0;
      			duration = new Duration(framesPerSec);
//      			System.out.println("rb1 framesPerSec:" + framesPerSec);
      		}
        });
     	rb2.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			framesPerSec = 50.0;
      			duration = new Duration(framesPerSec);
//      			System.out.println("rb2 framesPerSec:" + framesPerSec);
      		}
        });
    	rb12.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			framesPerSec = 1.0/50.0;
      			duration = new Duration(framesPerSec);
 //     			System.out.println("rb12 framesPerSec:" + framesPerSec);
      		}
        });
    	rb13.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			framesPerSec = 1.0/100.0;
      			duration = new Duration(framesPerSec);
  //    			System.out.println("rb13 framesPerSec:" + framesPerSec);
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
//event to handle the key frame.
//here we will make changes to the frame after every interruption
//the interruption is based on the time in Duration controlling how many frames to be dispplayed per sec
/*
        KeyFrame kf = new KeyFrame(
        		 Duration.seconds(framesPerSec),                //1 frame per sec
        		 
                 new EventHandler<ActionEvent>(){
                     public void handle(ActionEvent ae)
                 {
                    	 animate(gc,lb,critterStatsCombo.getValue());
                }
             }
        );
		            
*/
/* 
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
            	System.out.println("timer;" + System.currentTimeMillis());
                for (int i = 0; i < numOfWorldSteps; i++){
                    Critter.worldTimeStep();
                }

            }
        };
*/
        Duration duration = Duration.seconds(framesPerSec);
        
        System.out.println("framespersecFirst:" + framesPerSec);
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Duration duration = Duration.seconds(framesPerSec);
                System.out.println("framespersec:" + framesPerSec);
            	System.out.println("animate;" + System.currentTimeMillis());
            	System.out.println("numOfWorldSteps;" + numOfWorldSteps);
                for (int i = 0; i < numOfWorldSteps; i++){
                    Critter.worldTimeStep();
                }
              	 animate(gc,lb,critterStatsCombo.getValue());
//                 timeline.setCycleCount(Timeline.INDEFINITE); 
//                 kf = new KeyFrame(duration, onFinished);
            }
        };
        //        KeyFrame kf = new KeyFrame(duration);
        KeyFrame kf = new KeyFrame(duration, onFinished);

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
*/
        
        
//adds the keyframe to the timline. multiple time frames could ahve been added
        timeline.getKeyFrames().add(kf);
        timeline.play();       


        //shows the stage on the computer screen
        primaryStage.show();
//      primaryStage.close();
    }
	private void animate(GraphicsContext gc,Label lb, String str){
        // Clear the canvas, by painting an empty rectangle at the starting corner
        gc.clearRect(0, 0, Params.world_width * 5, Params.world_height * 5);
 		 gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, Params.world_width * 5, Params.world_height * 5);
      //this method will find which critters at what location and write  a string.image at that location on the canvas
        drawShapes(gc);
			String str2 = "project5." + str;
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
	
    private void drawShapes(GraphicsContext gc) {
		gc.setFont(new Font(STYLESHEET_CASPIAN,5));
		for (int i = 1; i <= Params.world_height;i++){
			for (int j = 1; j <= Params.world_width;j++){
				for (int k = 0; k < Critter.getPopulation().size(); k++){
					if (((Critter.getPopulation().get(k)).getX_coord() == j) && ((Critter.getPopulation().get(k)).getY_coord() == i)){ 
						gc.setFill(Critter.getPopulation().get(k).viewFillColor());
//trying to experiment with images instead of the string
//						Image img = new Image(getClass().getResourceAsStream("Dumbo.jpg"));
//						Image img = new Image("Beetle.png");
//						gc.drawImage(img, 10 + j * 5, i * 5, 5, 5);
						String str = Critter.getPopulation().get(k).viewShape().toString();
						if (str.equals("CIRCLE")){
							gc.fillOval(j * p, i * p, q/2,q/2);
//							gc.fillText(str,j * 5, i * 5);
						}
						if (str.equals("SQUARE")){
							gc.fillRect(j * p, i * p, q, q);
						}
						if (str.equals("TRIANGLE")){
							 gc.fillPolygon(new double[]{j*p,j*p+q/2,j*p+q},
				                       		new double[]{i*p+q,i*p,i*p+q}, 
				                       		3);
						}
						if (str.equals("DIAMOND")){
							 gc.fillPolygon(new double[]{j*p,j*p+q/2,j*p+q/2,j*p+q},
			                       		new double[]{i*p+q/2,i*p,i*p+q,i*p+q/2}, 
			                       		4);
						}
						if (str.equals("STAR")){
							 gc.fillPolygon(new double[]{j*p,j*p+q/2,j*p+q},
			                       		new double[]{i*p+3*q/4,i*p,i*p+3*q/4}, 
			                       		3);
							 gc.fillPolygon(new double[]{j*p,j*p+q/2,j*p+q},
			                       		new double[]{i*p+1*q/4,i*p+p,i*p+1*q/4}, 
			                       		3);
						}
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

