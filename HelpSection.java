import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HelpSection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HelpSection extends World
{

    /**
     * Constructor for objects of class HelpSection.
     * 
     */
    public HelpSection()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(540, 540, 1);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        OpenHDocButton openHDocButton = new OpenHDocButton();
        addObject(openHDocButton,270,158);
        ExitToMainMenuButton exitToMainMenuButton = new ExitToMainMenuButton();
        addObject(exitToMainMenuButton,270,496);
        
    }
}
