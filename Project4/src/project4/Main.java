/* CRITTERS <MyClass.java>
 * https://github.com/MattDaumas/assignment4.git
 * EE422C Project 4 submission by
 * Tarang Khandpur
 * tk8435
 * 76595
 * Matthew Daumas
 * md32789
 * 76595
 * Slip days used: <1>
 * Summer 2016
 */
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

//import project4.Critter.TestCritter;

public class Main extends Application {

//	static int i = 10;
//	static String input = "START";
//	static String delim = "[ ]+";
//	static String[] tokens = new String[5]; 
//	static Scanner in = new Scanner(System.in);
			 
	public static void main(String[] args) {
        launch(args);
    }

	@Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("Drawing Operations Test");
    	Group root = new Group();

		Button btnQuit = new Button("QUIT");
		btnQuit.setLayoutX(10);
		btnQuit.setLayoutY(100);
		Button btnPause = new Button("PAUSE");
		btnPause.setLayoutX(10);
		btnPause.setLayoutY(150);
		Button btnPlay = new Button("PLAY");
		btnPlay.setLayoutX(10);
		btnPlay.setLayoutY(200);
    	RadioButton rb1 = new RadioButton("1/2");
		RadioButton rb2 = new RadioButton("1/2");
		RadioButton rb3 = new RadioButton("1/2");
		ToggleGroup tg = new ToggleGroup();
		tg.getToggles().addAll(rb1, rb2, rb3);
    	ToolBar toolbar = new ToolBar(
    			new Button("SEED"),
    			new Button("MAKE"),
    			new Button("STATS"),
    			new Button("+SPEED"),
    			new Button("-SPEED")
    			);
    	toolbar.getItems().addAll(rb1, rb2, rb3, btnQuit, btnPause, btnPlay);
    	toolbar.setOrientation(Orientation.VERTICAL);
    	BorderPane pane = new BorderPane();
    	pane.setRight(toolbar);

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
        root.getChildren().add(btnPause);
        root.getChildren().add(btnPlay);
        root.getChildren().add(canvas);
    	primaryStage.setScene(new Scene(root));
    	GraphicsContext gc = canvas.getGraphicsContext2D();

    	
        timeline.setCycleCount(Timeline.INDEFINITE); 
        KeyFrame kf = new KeyFrame(
                 Duration.seconds(1),                // 60 FPS
                 new EventHandler<ActionEvent>(){
                     public void handle(ActionEvent ae)
                 {
                 // Clear the canvas
                 gc.clearRect(Params.world_width * 1, 0, Params.world_width * 5, Params.world_height * 5);
                 Critter.worldTimeStep();
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
		/*
		    	try {
					Critter.makeCritter("project4.Dumbo");
				} catch (InvalidCritterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		*/
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
//						gc.fillText(str, 10 + j * 5, i * 5);
						gc.fillRect(Params.world_width + j * 5, i * 5, 5, 5);
						break;
					}
				}
			}
		}
	}
}
    
    
//		Critter.setSeed(3);
//		
//		Craig c = new Craig();
//		c.setEnergy(Params.start_energy);
//		c.setXCoord(5); 
//		c.setYCoord(5);
//		Critter.TestCritter.setPopulation(c);
//		
//		FraidyCat f = new FraidyCat();
//		f.setEnergy(Params.start_energy);
//		f.setXCoord(5); 
//		f.setYCoord(5);
//		Critter.TestCritter.setPopulation(f);
//		
//		Tribble t0 = new Tribble();
//		t0.setEnergy(Params.start_energy);
//		t0.setXCoord(5); 
//		t0.setYCoord(5);
//		Critter.TestCritter.setPopulation(t0);
//		
//		Tribble t = new Tribble();
//		t.setEnergy(Params.start_energy);
//		t.setXCoord(3); 
//		t.setYCoord(5);
//		Critter.TestCritter.setPopulation(t);
//		
//		for (Critter a: Critter.TestCritter.getPopulation()) {
//			for (Critter b: Critter.TestCritter.getPopulation()) {
//				TestCritter a1 = (TestCritter) a;
//				TestCritter b1 = (TestCritter) b;
//				if (a1 != b1 && a1.getXCoord() == b1.getXCoord() && a1.getYCoord() == b1.getYCoord()) {
//					Critter.TestCritter.resolveEncounter(a1,b1);
//				}
//			}
//		}
//		
//		Critter.TestCritter.resolveEncounter(c, f);
//		Critter.TestCritter.cullDead();
		
/*
		while (!(input.toUpperCase().equals("QUIT"))) {
			System.out.print("Critters>");
			input = in.nextLine();
			tokens = input.trim().split(delim);
			switch (tokens[0]){
			case "quit":
				if (tokens.length > 1){
					System.out.println("Invalid Command: " + input);
					break;
				}
				input = tokens[0];
				break;
			case "show":
				if (tokens.length > 1){
					System.out.println("Invalid Command: " + input);
					break;
				}
				Critter.displayWorld();
				break;
			case "step":
				if (tokens.length > 2){
					System.out.println("Invalid Command: " + input);
					break;
				}
				i = 1;
				if (tokens.length > 1){
					i = getNumber(tokens[1]);
				}
				for (int j = 1 ; j <= i; j++){
					Critter.worldTimeStep();
				}
				break;
			case "seed":
				if (tokens.length > 2){
					System.out.println("Invalid Command: " + input);
					break;
				}
				i = 1;
				if (tokens.length > 1){
					i = getNumber(tokens[1]);
				}
				i = getNumber(tokens[1]);
				Critter.setSeed(i);
				break;
			case "make":
				if (tokens.length > 3){
					System.out.println("Invalid Command: " + input);
					break;
				}
				i = 1;
				if (tokens.length > 2){
					i = getNumber(tokens[2]);
				}
				for (int j = 1 ; j <= i; j++){
					Critter.makeCritter(tokens[1]);
				}
				break;
			case "stats":
				if (tokens.length > 2){
					System.out.println("Invalid Command: " + input);
					break;
				}
				if (!(tokens.length == 2)){
					System.out.println("Invalid Command: " + input);
					break;
				}
				List<Critter> statList = new ArrayList<Critter>();
				statList = Critter.getInstances(tokens[1]);
				try{
					if (statList.size() <= 0){
						System.out.println("No instances found");
						break;
					}
				}
				catch (NullPointerException e){
					break;
				}
				if (statList.equals(Critter.TestCritter.getPopulation())){
					Critter.runStats(statList);
					break;
				}
				Object ob = null;
				Method m1 = null; // super class to all methods of any particular class
				Class<?> cls = Class.forName(tokens[1]);
				ob = cls.newInstance();
				try {
					m1 = ob.getClass().getMethod("runStats", List.class);
				} catch (NoSuchMethodException | SecurityException e) {
					System.out.println("Invalid Command: " + input);
					return;
					//e.printStackTrace();
				}
				try {
					m1.invoke(ob, statList);
				} catch (IllegalArgumentException | InvocationTargetException e) {
					System.out.println("Invalid Command: " + input);
					return;
					//e.printStackTrace();
				}
				break;
			default:
				//if (!(input.equals("START"))){
					System.out.println("Invalid Command: " + input);
			//}
				 break;
			}
		}
		System.out.println("Bye");	
	/** converts string into an integer value, and also prints Invalid command if its not a valid integer.
	 * @param str
	 * @return
	 */
/*
	public static int getNumber(String str){
		int i = 0;
		if (str == null || str == "" || str == " "){
			return 1;
		}
		try { 
			i = Integer.parseInt(str);
		}
		 catch(NumberFormatException er) {
			 System.out.println("Invalid Command: " + input);
			 return 0;
		}
		if (i < 1){
			 System.out.println("Invalid Command:" + input);
			 return 0;
		}
		return i;
	}
*/

