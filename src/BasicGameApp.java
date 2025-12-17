//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image astroPic;
    public Image astroid;
    public Image astroid2;
    public Image backgroundPic;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Astronaut astro;
    private Astronaut astro2;
    private Astroid astroid1;
    private Astroid astroid22;



   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();

        //Random number structure
        //int ----- = (int)(Math.random()* range) + start
        //the range is the amount of numbers

        int randx = (int)(Math.random()*999)+1;

        randx = (int)(Math.random()*999)+1;

        int randy = (int)(Math.random()*699)+1;


      //variable and objects
      //create (construct) the objects needed for the game and load up 
		astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png");
        astroid = Toolkit.getDefaultToolkit().getImage("Astroid pic.png");//load the picture
        astroid2 = Toolkit.getDefaultToolkit().getImage("Astroid pic.png");
        backgroundPic = Toolkit.getDefaultToolkit().getImage("Starry Background.jpg");
        astro = new Astronaut(WIDTH/2,HEIGHT/2);
        astro2 = new Astronaut(randx,randy);

        astroid1 = new Astroid(100,150);
        astroid1.dx = -astroid1.dx;
        astroid22 = new Astroid(110,100);



	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
		astro.move();
        astro2.move();
        astroid1.move();
        astroid22.move();
        crashing();

	}

    public void crashing(){
        //check to see if my astro's crash into each other
        if(astro.hitbox.intersects(astro2.hitbox)&& astro2.isAlive == true){
            System.out.println("CRASH!!");
            astro.dy = -astro.dy;
            astro2.dy = -astro2.dy;
            astro2.isAlive = false;
        }

        if(astroid1.hitbox2.intersects(astroid22.hitbox2) && astroid1.isCrashing == false){
            System.out.println("HIT!!");
            astroid1.height += 10;
            //same as writing astroid1.height = astroid1.height+10
            astroid1.isCrashing = true;
        }

        if (!astroid1.hitbox2.intersects(astroid22.hitbox2)){
            System.out.println("no intersection");
            astroid1.isCrashing = false;
        }


    }
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the image of the astronaut

        g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);
        if(astro2.isAlive == true){
        g.drawImage(astroPic, astro2.xpos, astro2.ypos, astro2.width, astro2.height, null);
        }
        g.drawImage(astroid, astroid1.xpos, astroid1.ypos, astroid1.width, astroid1.height, null);
        g.drawImage(astroid2, astroid22.xpos, astroid22.ypos, astroid22.width, astroid22.height, null);


        g.drawRect(astro.hitbox.x, astro.hitbox.y, astro.hitbox.width, astro.hitbox.height);
        g.drawRect(astro2.hitbox.x, astro2.hitbox.y, astro2.hitbox.width, astro2.hitbox.height);
        /*gives a visual of how the hitbox looks */
        g.drawRect(astroid1.hitbox2.x, astroid1.hitbox2.y, astroid1.hitbox2.width, astroid1.hitbox2.height);
        g.drawRect(astroid22.hitbox2.x, astroid22.hitbox2.y, astroid22.hitbox2.width, astroid22.hitbox2.height);
        g.setColor(Color.GREEN);
        g.fillRect(100,300,200,200);

		g.dispose();

		bufferStrategy.show();
	}
}