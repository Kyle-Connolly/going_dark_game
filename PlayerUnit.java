import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class PlayerUnit here.
 * 
 * @Kyle Connolly
 * @version (a version number or a date)
 */
public class PlayerUnit extends Unit
{
    /**
     * Constructor for PlayerUnit
     */
    public PlayerUnit(Node node, int hitPoints) {
        setPlayerNode(node, this);
        this.unitID = "Player";
    }
    
    /**
     * Act - do whatever the PlayerUnit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
