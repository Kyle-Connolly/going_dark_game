import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Scanner; //To take the player's inputs
import java.io.*; //import the java input/output library required for file access
/**
 * MissionLogic handles all logic in regards to handling turns and intiating player input and AI algorithms. Also handles score and writing/reading to player score file
 * 
 * Author: Kyle Connolly
 * Version: 12/02/23
 */
public class MissionLogic extends Actor
{
    private boolean diffSet = false;
    private int logicDiff = 0;

    private boolean actBlock = false;
    private boolean intro = false;

    private boolean gameOver = false;
    private boolean results = false;
    private boolean draw = false;

    private int score = 0;
    private int recordScore = 0;
    private int missionScore = 0;

    private boolean playerTurn = true;

    /**
     * Constructor for MissionLogic
     */
    public MissionLogic() {
        setDiff();
        getImage().setTransparency(0);
    }

    /**
     * Act - do whatever the MissionLogic wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!actBlock) {
            actBlock = true;
            turnSystem();
        }
    }

    protected void setDiff() {
        Grid tempGrid = accessGridMethods(); 
        logicDiff = tempGrid.getDiff();
        diffSet = true;
    }

    protected void turnSystem() {
        if(!intro) {
            System.out.println("Starting Watcher V System");
            System.out.println("Difficulty level set to: " + logicDiff);
        }
        if(!gameOver) {
            //if player's turn, run player code, else: run AI code
            if(playerTurn && !gameOver) {
                //playerSystem();
                playerTurn = false; 
                checkGameOver();
            }
            if(!playerTurn && !gameOver) {
                enemySystem(logicDiff);
                playerTurn = true;
                checkGameOver();
            }
        }
        //cycle completed so unblock act method to refresh key presses
        actBlock = false;
    }
    
    /**
     * WIP - AI (enemy) system
     * - choose which player unit to focus on
     * - attack player or move enemy unit closer to player unit/into attack range
     */
    protected void enemySystem(int lvlDiff) {
        //random decision
        //choose attacks
        //choose movement
    }
    
    /**
     * 
     * WIP - Player system
     * Listens for "r" key press to relinquish turn
     * If player clicks on a unit they can move or attack with that unit
     * - keep "m" down to show movement options; while key held down click available tiles and the unit will move there - ends the player's turn
     * - keep "a" down to show attack options; while key held down click available tiles and the unit will attack in that direction, with appropriate damage modifiers applied - ends player's turn
     */
    protected void playerSystem()   {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        Grid tempGrid = accessGridMethods();
        
        while(playerTurn) {
            String key = Greenfoot.getKey();
            while(!("r".equals(key))) {
                //if player chooses unit
                if(Greenfoot.mouseClicked(tempGrid.getPlayerOne())){
                    if(Greenfoot.isKeyDown("m")) {
                        //
                        //break
                    }
                    if(Greenfoot.isKeyDown("a")) {
                        //
                        //break
                    }
                }
            }
        }
    }

    protected int teamStatus(int teamID) {
        int teamTotalHP = 0;
        Grid tempGrid = accessGridMethods();
        if(teamID != 3) {
            int unitOneHP = 0;
            int unitTwoHP = 0;
            int unitThreeHP = 0;
            if(teamID == 1) {
                unitOneHP = (tempGrid.getPlayerOne()).getPlayerHP(tempGrid.getPlayerOne());
                unitTwoHP = (tempGrid.getPlayerTwo()).getPlayerHP(tempGrid.getPlayerTwo());
                unitThreeHP = (tempGrid.getPlayerThree()).getPlayerHP(tempGrid.getPlayerThree());
            }
            if(teamID == 2) {
                unitOneHP = (tempGrid.getEnemyOne()).getEnemyHP(tempGrid.getEnemyOne());
                unitTwoHP = (tempGrid.getEnemyTwo()).getEnemyHP(tempGrid.getEnemyTwo());
                unitThreeHP = (tempGrid.getEnemyThree()).getEnemyHP(tempGrid.getEnemyThree());
            }
            teamTotalHP = unitOneHP + unitTwoHP + unitThreeHP;
            return teamTotalHP;
        }
        teamTotalHP = (tempGrid.getHeavyUnit()).getHeavyHP(tempGrid.getHeavyUnit());

        return teamTotalHP;
    }

    public void checkGameOver() {
        if(teamStatus(1) <= 0 && teamStatus(2) <= 0) {
            gameOver = true;
            draw = true;
        }
        if(teamStatus(1) <= 0 && teamStatus(2) > 0) {
            gameOver = true;
        }
        if(teamStatus(1) > 0 && teamStatus(2) <= 0) {
            gameOver = true;
            results = true;
        }
        //end the game
        if(gameOver) {
            endOfMission();
        }
    }

    public void endOfMission() {
        if(results && draw) {
            System.out.println("> Draw. Fireteam Echo terminated...");
            score = score + 15;
        }
        if(results && !draw) {
            System.out.println("> Mission Success. Hostiles eliminated.");
            score = score + 20;
        }
        if(!results && !draw) {
            System.out.println("> Mission failure. Fireteam Echo terminated...");
            score = score + 10;
        }
        writeToFile();
        scoreHandling();
        Greenfoot.setWorld(new MainMenu());
    }

    protected void writeToFile() {
        File scoreFile = new File("scoreFile.txt");

        //if file exists, read the last recorded score from that file and store in local variable, clear file, then write to that file with mission score + last score
        if(scoreFile.exists() && !scoreFile.isDirectory()) { 
            //file isn't empty so need to read last recorded score
            if(scoreFile.length() != 0) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(scoreFile));

                    String lineOut;
                    while ((lineOut = reader.readLine()) != null) {
                        recordScore = Integer.parseInt(lineOut);
                    }
                    missionScore = score;
                    score = recordScore + score;

                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }   
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(scoreFile, true));

            writer.write(Integer.toString(score));

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }

    protected void scoreHandling() {
        System.out.println("---------------------------------------------------");
        System.out.println("Mission Score: " + missionScore + "\n" + "Previous Score: " + recordScore + "\n" + "New Lifetime Score: " + score + "\n");
        if(score <= 49) {
            System.out.println("Rank: PRIVATE");
        }
        if(score >= 50 && recordScore <= 74) {
            System.out.println("Rank: LANCE CORPORAL");
        }
        if(score >= 75 && recordScore <= 124) {
            System.out.println("Rank: CORPORAL");
        }
        if(score >= 125  && recordScore <= 199) {
            System.out.println("Rank: SERGEANT");
        }
        if(score >= 200  && recordScore <= 299) {
            System.out.println("Rank: LIEUTENANT");
        }
        if(score >= 300 && recordScore <= 499) {
            System.out.println("Rank: CAPTAIN");
        }
        if(score >= 500 && recordScore <= 599) {
            System.out.println("Rank: DELTA FORCE");
        }
        if(score >= 500) {
            System.out.println("Rank: ECHO");
        }
        System.out.println("---------------------------------------------------\n");
    }

    /**
     * Access methods of Grid class by getting the instance of the Grid, and returning it
     */
    protected Grid accessGridMethods() {
        Grid grid = (Grid)getWorld();
        return grid;
    }
}
