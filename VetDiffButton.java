import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class VetDiffButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VetDiffButton extends Button
{
    private boolean vetOver = false;
    private boolean vetOn = false;

    /**
     * Act - do whatever the NormalDiffButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        mouseDetect(this);

        if(Greenfoot.mouseMoved(null)) {
            vetOver = Greenfoot.mouseMoved(this);
        }

        if(vetOver && !vetOn && Greenfoot.mouseClicked(this)) {
            setImage(new GreenfootImage("VetDiffButtonON.png"));
            setDiff(3);
            vetOn = true;
        } else if(vetOver && vetOn && Greenfoot.mouseClicked(this)) {
            setImage(new GreenfootImage("VetDiffButton.png"));
            vetOn = false;
        }
    }    
}
