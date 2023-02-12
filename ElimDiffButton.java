import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ElimDiffButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ElimDiffButton extends Button
{
    private boolean elimOver = false;
    private boolean elimOn = false;

    /**
     * Act 
     */
    public void act() 
    {
        mouseDetect(this);

        if(Greenfoot.mouseMoved(null)) {
            elimOver = Greenfoot.mouseMoved(this);
        }

        if(elimOver && !elimOn && Greenfoot.mouseClicked(this)) {
            setImage(new GreenfootImage("ElimDiffButtonON.png"));
            setDiff(5);
            elimOn = true;
        } else if(elimOver && elimOn && Greenfoot.mouseClicked(this)) {
            setImage(new GreenfootImage("ElimDiffButton.png"));
            elimOn = false;
        }
    }    
}
