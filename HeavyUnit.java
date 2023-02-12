import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class AICharElimination here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HeavyUnit extends Unit
{
    /**
     * Constructor for HeavyUnit
     */
    public HeavyUnit(Node node, int hitPoints) {
        setHeavyNode(node, this);
        this.unitID = "Heavy";
    }

    /**
      * Act - do whatever the HeavyUnit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
