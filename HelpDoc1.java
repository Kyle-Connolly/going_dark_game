import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HelpDoc1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HelpDoc1 extends World
{
    /**
     * Constructor for objects of class HelpDoc1.
     * 
     */
    public HelpDoc1()
    {
        super(540, 540, 1);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        ExitDocButton exitDocButton = new ExitDocButton();
        addObject(exitDocButton,31,30);
        NextPageOne nextPageOne = new NextPageOne();
        addObject(nextPageOne,31,80);
    }
}
