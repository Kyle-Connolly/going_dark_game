import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NormalDiffButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NormalDiffButton extends Button
{
    private boolean normOver = false;
    private boolean normOn = false;
    
    /**
     * Act - do whatever the NormalDiffButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        mouseDetect(this);

        if(Greenfoot.mouseMoved(null)) {
            normOver = Greenfoot.mouseMoved(this);
        }

        if(normOver && !normOn && Greenfoot.mouseClicked(this)) {
            setImage(new GreenfootImage("NormalDiffButtonON.png"));
            setDiff(2);
            normOn = true;
        } else if(normOver && normOn && Greenfoot.mouseClicked(this)) {
            setImage(new GreenfootImage("NormalDiffButton.png"));
            normOn = false;
        }

    }

   //private int normID = 2;

    /*
 public void normDiffOn() {
        //check if button is already on (check with super class) and turn it off
        difficultyOff(normID);
        setImage(new GreenfootImage("RecruitDiffButtonON.png"));
        //updateButtonID();
    }

    public void normDiffOff() {
        setImage(new GreenfootImage("NormalDiffButton.png"));
    }
     */

    /*
    if(normOver && Greenfoot.mouseClicked(this)) {
    normDiffOn();
    }

    if(normOver && Greenfoot.mouseClicked(this)) {
    normDiffOff();

    }
     */

}
