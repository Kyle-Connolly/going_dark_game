import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*; //import the java input/output library required for file access
/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    //to store the transparency value of the button as a solid image (static as it does not change and so it is the same for all buttons)
    private static int transVal = 255;
    //to store if the mouse is over the button object
    private boolean mouseOn = false;
    //to sotre the difficulty level reflected in the integer it holds (static as it does not change and so it is the same for all buttons)
    private static int diff;

    File difficultyFile = new File("playerFile.txt");

    File tempDiffFile = new File("playerFileTemp.txt");

    /**
     * Act - if the mouse is over the button, set the mouseOn to true, if it's is true half the transparency of its image, else set it back to full transparency
     * "this" keyword is used so that the instance of the button is used
     * 
     * File fileName = new File("phonebook.txt");
     */
    public void mouseDetect(Button button) 
    {
        if(Greenfoot.mouseMoved(null)) {
            mouseOn = Greenfoot.mouseMoved(this);
        }

        if(mouseOn) {
            changeTrans(button, transVal/2);
        } else {
            changeTrans(button, transVal);
        }
    }
    //if the button is clicked then set the world to the one in the parameter. "this" keyword is used so that the instance of the button is used
    public void setMenu(World world) {
        if(Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(world);
        }
    }

    /**
     * This method sets the difficulty by taking the difficulty selected from the relavant 
     * subclasses. A temporary file is then created and the difficulty (int) is written to it 
     * The orginal text file is then deleted and the temporary file is renamed to the orginal file
     */
    public void setDiff(int diffID) {
        diff = diffID;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempDiffFile, true));

            writer.write(String.valueOf(diff));

            writer.close();

            difficultyFile.delete();

            tempDiffFile.renameTo(difficultyFile);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            diff = 1;
        }   
    }

    //getter method for getting the difficulty
    public int getDiff() {
        return diff;
    }

    //method for setting the transparency of by getting the image and assinging it to a temporary GreenfootImage variable, setting transparency to the value in the parameters and setting that as that buttons image
    public void changeTrans(Button button, int adjVal) {
        GreenfootImage tempImage = button.getImage();
        tempImage.setTransparency(adjVal);
        button.setImage(tempImage);        
    }
}
