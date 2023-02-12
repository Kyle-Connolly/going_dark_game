import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HelpDoc2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HelpDoc2 extends World
{
    /**
     * Constructor for objects of class HelpDoc2.
     * 
     */
    public HelpDoc2()
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
        LastPageTwo lastPageTwo = new LastPageTwo();
        addObject(lastPageTwo,31,130);
    }
}
