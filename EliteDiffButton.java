import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EliteDiffButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EliteDiffButton extends Button
{
    private boolean eliteOver = false;
    private boolean eliteOn = false;
    
    /**
     * Act - do whatever the NormalDiffButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        mouseDetect(this);

        if(Greenfoot.mouseMoved(null)) {
            eliteOver = Greenfoot.mouseMoved(this);
        }

        if(eliteOver && !eliteOn && Greenfoot.mouseClicked(this)) {
            setImage(new GreenfootImage("EliteDiffButtonON.png"));
            setDiff(4);
            eliteOn = true;
        } else if(eliteOver && eliteOn && Greenfoot.mouseClicked(this)) {
            setImage(new GreenfootImage("EliteDiffButton.png"));
            eliteOn = false;
        }

    }   
}
