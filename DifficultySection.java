import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DifficultySection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DifficultySection extends World
{
    
    /**
     * Constructor for objects of class DifficultySection.
     * 
     */
    public DifficultySection()
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
        RecruitDiffButton recruitDiffButton = new RecruitDiffButton();
        addObject(recruitDiffButton,388,65);
        NormalDiffButton normalDiffButton = new NormalDiffButton();
        addObject(normalDiffButton,388,125);
        VetDiffButton vetDiffButton = new VetDiffButton();
        addObject(vetDiffButton,388,185);
        EliteDiffButton eliteDiffButton = new EliteDiffButton();
        addObject(eliteDiffButton,388,245);
        ElimDiffButton elimDiffButton = new ElimDiffButton();
        addObject(elimDiffButton,388,385);
        ExitToMainMenuButton exitToMainMenuButton = new ExitToMainMenuButton();
        addObject(exitToMainMenuButton,388,500);
    }
}
