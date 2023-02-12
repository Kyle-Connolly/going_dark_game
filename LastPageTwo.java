import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LastPageButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LastPageTwo extends Button
{
    /**
     * Act - if the mouse is over change the image transparency, if clicked set the menu (world) to the HelpDoc1 (Previous Page)
     */
    public void act() 
    {
        mouseDetect(this);
        
        setMenu(new HelpDoc1());
    }    
}
