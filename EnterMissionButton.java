import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnterMissionButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnterMissionButton extends Button
{
    /**
     * Act - if the mouse is over change the image transparency, if clicked set the menu (world) to the Grid
     */
    public void act() 
    {
        mouseDetect(this);
        
        setMenu(new Grid());
    }    
}
