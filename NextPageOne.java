import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NextPageButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NextPageOne extends Button
{
    /**
     * Act - if the mouse is over change the image transparency, if clicked set the menu (world) to the HelpDoc2 (Next Page)
     */
    public void act() {
        mouseDetect(this);
        
        setMenu(new HelpDoc2());
    }    
}
