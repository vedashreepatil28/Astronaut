import java.awt.*;

public class Astroid {
    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox2;
    public boolean isCrashing;

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Astroid(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =8;
        dy =0;
        width = 85;
        height = 85;
        isAlive = false;
        hitbox2 = new Rectangle(xpos, ypos, width, height);
        isCrashing = false;
    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
   //todo: homework make another astroid
    public void move() {
        if(xpos>1000){xpos=0-width;} //wrap when hits right wall
        if(xpos<0-width){xpos=1000;} //wrap when hits left wall
        if(ypos>700){ypos=0-height;} //wrap when hits bottom wall
        if(ypos<0-height){ypos=700;} //wrap when hits top wall
       // if (xpos>1000){dx=-dx;}
        //if (ypos>700){dy=-dy;}
        //if (xpos<0){dx=-dx;}
        //if (ypos<0){dy=-dy;}
        xpos = xpos + dx;
        ypos = ypos + dy;

        hitbox2 = new Rectangle(xpos, ypos, width, height);

    }


}
