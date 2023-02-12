import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OpenHDocButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OpenHDocButton extends Button
{
    /**
     * Act - do whatever the OpenHDocButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        mouseDetect(this);
        
        setMenu(new HelpDoc1());
    }    
}
