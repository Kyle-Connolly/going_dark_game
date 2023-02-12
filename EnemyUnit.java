import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyUnit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyUnit extends Unit
{
    /**
     * Constructor for EnemyUnit
     */
    public EnemyUnit(Node node, int hitPoints) {
        setEnemyNode(node, this);
        setEnemyHP(hitPoints, this);
        this.unitID = "Enemy";
    }
    
    /**
     * Act - do whatever the EnemyUnit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
