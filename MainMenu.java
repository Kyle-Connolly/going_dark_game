import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainMenu extends World
{

    /**
     * Constructor for objects of class MainMenu.
     * 
     */
    public MainMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(775, 540, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        EnterMissionButton enterMissionButton = new EnterMissionButton();
        addObject(enterMissionButton,385,180);
        DifficultyOptionsButton difficultyOptionsButton = new DifficultyOptionsButton();
        addObject(difficultyOptionsButton,385,240);
        ExitGameButton exitGameButton = new ExitGameButton();
        addObject(exitGameButton,385,300);
        HelpButton helpButton = new HelpButton();
        addObject(helpButton,70,507);
    }
}
