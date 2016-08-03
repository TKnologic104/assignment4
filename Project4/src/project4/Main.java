package project4;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	double framesPerSec = 1;	 
	public static void main(String[] args) {
        launch(args);
    }

	@Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("Drawing Operations Test");
    	Group root = new Group();

//		Button btnQuit = new Button("QUIT");
//		btnQuit.setLayoutX(5);
//		btnQuit.setLayoutY(10);
		Button btnPause = new Button("PAUSE");
		btnPause.setLayoutX(5);
		btnPause.setLayoutY(10);
		Button btnPlay = new Button("PLAY");
		btnPlay.setLayoutX(5);
		btnPlay.setLayoutY(40);
		ComboBox<String> critterCombo = new ComboBox();
		critterCombo.getItems().addAll(
				"Dumbo",
				"Ent",
				"FraidyCat",
				"Tribble",
				"ALL"
		);
		critterCombo.setLayoutX(5);
		critterCombo.setLayoutY(70);
		Label lb1 = new Label("Seed/Make Qty");
		TextField num1 = new TextField();
		lb1.setLayoutX(5);
		lb1.setLayoutY(100);
		num1.setLayoutX(5);
		num1.setLayoutY(130);
		num1.setMaxWidth(50);
		Button btnSeed = new Button("SEED");
		btnSeed.setLayoutX(5);
		btnSeed.setLayoutY(160);
		Button btnMake = new Button("MAKE");
		btnMake.setLayoutX(5);
		btnMake.setLayoutY(190);
		Button btnStats = new Button("STATS");
		btnStats.setLayoutX(5);
		btnStats.setLayoutY(220);
		
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
		rb1.setLayoutX(5);
		rb1.setLayoutY(300);
		rb2.setLayoutX(5);
		rb2.setLayoutY(320);
		rb3.setLayoutX(5);
		rb3.setLayoutY(340);
		rb4.setLayoutX(5);
		rb4.setLayoutY(360);
		rb5.setLayoutX(5);
		rb5.setLayoutY(380);
		rb6.setLayoutX(5);
		rb6.setLayoutY(400);
		rb7.setLayoutX(5);
		rb7.setLayoutY(420);
		rb8.setLayoutX(5);
		rb8.setLayoutY(440);
		rb9.setLayoutX(5);
		rb9.setLayoutY(460);
		rb10.setLayoutX(5);
		rb10.setLayoutY(480);
		rb11.setLayoutX(5);
		rb11.setLayoutY(500);
		rb12.setLayoutX(5);
		rb12.setLayoutY(520);
		rb13.setLayoutX(5);
		rb13.setLayoutY(540);
		
    	Timeline timeline = new Timeline();

//    	root.getChildren().add(pane);
    	Canvas canvas = new Canvas(Params.world_width * 6, Params.world_height * 5);
     	btnPause.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			System.out.println("pause");
      			timeline.pause();
      		}
      	});
     	btnPlay.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			System.out.println("play");
      			timeline.play();
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
      				String input = num1.getText();
      				int j = getNumber(input);
      				String str = critterCombo.getValue();
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
     	btnStats.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			System.out.println("Stats");
  				String input = num1.getText();
  				String str = critterCombo.getValue();
  				if (str.equals("ALL")){
  					str = "project4.Critter";
  				}
  				else {
  					str = "project4." + str;
  				}
  				try {
					Critter.runStats(Critter.getInstances(str));
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
      		}
        });
    	rb13.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent e){
      			framesPerSec =  100.0;
      		}
        });
        root.getChildren().add(btnPause);
        root.getChildren().add(btnPlay);
        root.getChildren().add(critterCombo);
        root.getChildren().add(lb1);
        root.getChildren().add(num1);
        root.getChildren().add(btnSeed);
        root.getChildren().add(btnMake);
        root.getChildren().add(btnStats);
        root.getChildren().add(rb1);
        root.getChildren().add(rb2);
        root.getChildren().add(rb3);
        root.getChildren().add(rb4);
        root.getChildren().add(rb5);
        root.getChildren().add(rb6);
        root.getChildren().add(rb7);
        root.getChildren().add(rb8);
        root.getChildren().add(rb9);
        root.getChildren().add(rb10);
        root.getChildren().add(rb11);
        root.getChildren().add(rb12);
        root.getChildren().add(rb13);
        root.getChildren().add(canvas);
    	primaryStage.setScene(new Scene(root));
    	GraphicsContext gc = canvas.getGraphicsContext2D();

    	
        timeline.setCycleCount(Timeline.INDEFINITE); 
        KeyFrame kf = new KeyFrame(
        		 Duration.seconds(framesPerSec),                // 60 FPS
                 new EventHandler<ActionEvent>(){
                     public void handle(ActionEvent ae)
                 {
                 // Clear the canvas
                 gc.clearRect(Params.world_width * 1, 0, Params.world_width * 5, Params.world_height * 5);
                 int j = 1;
                 if (framesPerSec > 1.0){
                	 j = (int) (framesPerSec);
                 }
                 for (int i = 0; i < j; i++){
                     Critter.worldTimeStep();
                 }
                 drawShapes(gc);
                 }
             }
        );
		            
        // play 40s of animation
        timeline.getKeyFrames().add(kf);
        timeline.play();       

        primaryStage.show();
//      primaryStage.close();
    }

    private void drawShapes(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
		gc.setFont(new Font(STYLESHEET_CASPIAN,10));
		for (int i = 1; i <= Params.world_height;i++){
			for (int j = 1; j <= Params.world_width;j++){
				for (int k = 0; k < Critter.getPopulation().size(); k++){
					if (((Critter.getPopulation().get(k)).getX_coord() == j) && ((Critter.getPopulation().get(k)).getY_coord() == i)){ 
						String str = Critter.getPopulation().get(k).toString();
//						Image img = new Image(getClass().getResourceAsStream("Dumbo.jpg"));
//						Image img = new Image("Beetle.png");
//						gc.drawImage(img, 10 + j * 5, i * 5, 5, 5);
						gc.fillText(str, Params.world_width + j * 5, i * 5);
//						gc.fillRect(Params.world_width + j * 5, i * 5, 5, 5);
						break;
					}
				}
			}
		}
	}

    public static int getNumber(String str){
		int i = 0;
		if (str == null || str == "" || str == " "){
			return 1;
		}
		try { 
			i = Integer.parseInt(str);
		}
		 catch(NumberFormatException er) {
			 System.out.println("Invalid Number:");
			 return 0;
		}
		if (i < 1){
			 System.out.println("Invalid Number:");
			 return 0;
		}
		return i;
	}
}

