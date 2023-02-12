import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RecruitDiffButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecruitDiffButton extends Button
{
    private boolean recOver = false;
    private boolean recOn = false;

    /**
     * Act - do whatever the NormalDiffButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        mouseDetect(this);

        if(Greenfoot.mouseMoved(null)) {
            recOver = Greenfoot.mouseMoved(this);
        }
        
        if(recOver && !recOn && Greenfoot.mouseClicked(this)) {
            setImage(new GreenfootImage("RecruitDiffButtonON.png"));
            setDiff(1);
            recOn = true;
        } else if(recOver && recOn && Greenfoot.mouseClicked(this)) {
            setImage(new GreenfootImage("RecruitDiffButton.png"));
            recOn = false;
        }
    }  
}
