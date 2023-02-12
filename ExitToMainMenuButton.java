import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ExitToMainMenuButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExitToMainMenuButton extends Button
{
    /**
     * Act - if the mouse is over change the image transparency, if clicked set the menu (world) to the Main Menu
     */
    public void act() 
    {
        mouseDetect(this);
        
        setMenu(new MainMenu());
    }    
}
