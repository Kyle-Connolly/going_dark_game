import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Scanner; //To take the player's inputs
/**
 * Write a description of class ExitMissionButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExitMissionButton extends Button
{
    boolean mouseOver;
    boolean exitMissionTryCatch = false;

    /**
     * Act - do whatever the ExitMissionButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        getImage().setTransparency(100);

        if(Greenfoot.mouseMoved(null)) {
            mouseOver = Greenfoot.mouseMoved(this);
        }

        if(mouseOver) {
            getImage().setTransparency(255);
        } 

        if(Greenfoot.mouseClicked(this)) {
            System.out.println("Are you sure you want to exit? Click again to exit");
            if(Greenfoot.mouseClicked(this)) {
                Greenfoot.setWorld(new MainMenu());
            }
        }
    }    
}
