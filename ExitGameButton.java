import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Scanner; //To take the player's inputs
/**
 * Write a description of class ExitGameButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExitGameButton extends Button
{
    boolean exitTryCatch;

    /**
     * Act - if the mouse is over change the image transparency, if clicked set the menu terminate the program
     */
    public void act() 
    {
        mouseDetect(this);

        int exitInput = 0;
        Scanner exitScan = new Scanner(System.in);

        if(Greenfoot.mouseClicked(this)) {
            do{
                System.out.println("Are you sure you want to exit? Enter 1 to exit. Enter 2 to cancel");
                if(exitScan.hasNextInt()){
                    exitInput = exitScan.nextInt();
                    if(exitInput == 1 || exitInput == 2) {
                        exitTryCatch = true;
                    }
                }else{
                    exitScan.nextLine();
                    System.out.println("Integer value between 1 & 2 required");
                }
            }while(!exitTryCatch);

            if(exitInput == 1) {
                System.exit(0);
            } 
            if(exitInput == 2) {
                exitScan.close();
            }
            exitScan.close();
        }
    }    
}
