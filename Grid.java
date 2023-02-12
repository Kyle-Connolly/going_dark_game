import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Scanner; //To take the player's inputted difficulty
import java.io.*; //import the java input/output library required for file access
/**
 * Grid class is the playable level, contains a 9 by 9 Grid of Nodes. Sets up the level at the start of the match/mission
 * 
 * Author: Kyle Connolly
 * Version: 12/02/23
 */
public class Grid extends World
{
    //Store the grid as a 2D array of nodes (as the grid is 2 dimensional)
    private Node[][] grid;
    //Creating new actors to be added into the world where appropriate
    private PlayerUnit playerOne;
    private PlayerUnit playerTwo;
    private PlayerUnit playerThree;
    private EnemyUnit enemyOne;
    private EnemyUnit enemyTwo;
    private EnemyUnit enemyThree;
    private HeavyUnit heavy;
    
    //to store the amount of nodes in the Grid for confirmation of created nodes
    private int nodeCount;
    //String array of images storing the images of each tile (which are in the correct order) to be assigned to each tile
    private String tileTypes[] = {"SpecialTerrain.png", "SpecialTerrain.png","NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", 
            "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png","Coords.png", "SpecialTerrain.png", "NormalTerrain.png", "NormalTerrain.png", 
            "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png","NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png",
            "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "ShockPanelOn.png", "BarricadeOn.png",
            "SpecialTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "SpecialTerrain.png",
            "NormalTerrain.png", "NormalTerrain.png", "SpecialTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png",
            "SpecialTerrain.png", "BarricadeOn.png", "SpecialTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png",
            "NormalTerrain.png", "SpecialTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "SpecialTerrain.png", "NormalTerrain.png", 
            "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "SpecialTerrain.png","BarricadeOn.png", "ShockPanelOn.png", 
            "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png","NormalTerrain.png", 
            "NormalTerrain.png", "NormalTerrain.png","NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png",
            "SpecialTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png", "NormalTerrain.png",
            "NormalTerrain.png", "SpecialTerrain.png", "SpecialTerrain.png"};
    //to store the difficulty level which is used to place the enemy (AI) depending on the difficulty
    private int diff = 0;
    private int enemyHealth = 30;
    //to store true or false wether or not the MissionLogic Actor has been placed into the world (acting as a key listener) and to make sure it is only placed once
    private boolean missionLogicSet = false;

    File difficultyFile = new File("playerFile.txt");

    /**
     * Constructor for objects of class Grid.
     * 
     */
    public Grid()
    {    
        // Create a new world with 9x9 cells with a cell size of 60x60 pixels.
        super(9, 9, 60);
        
        grid = new Node[9][9];

        //assining nodes to the grid indexes, and assigning the corresponding coordinates
        //y = 0 == top left, x = 0 == bottom left
        outputTiles();

        //assigning neighbouring nodes by iterating over the rows and columns
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(j > 0) {
                    grid[i][j].setNorthNode(grid[i][j-1]);
                }
                if(j < (grid[i].length-1)) {
                    grid[i][j].setSouthNode(grid[i][j+1]);
                }
                if(i > 0) {
                    grid[i][j].setWestNode(grid[i-1][j]);
                }
                if(i < (grid.length-1)) {
                    grid[i][j].setEastNode(grid[i+1][j]);
                }
            }
        }

        //setting the tile cost for special/rocky terrain to two (an if statement for each cluster of tiles)
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if((i == 0 && j == 0) || (i == 1 && j == 0) || (i == 0 && j == 1)) {
                    grid[i][j].setTileCost(grid[i][j]);
                }
                if((i == 6 && j == 0) || (i == 5 && j == 1)) {
                    grid[i][j].setTileCost(grid[i][j]);
                }
                if((i == 4 && j == 3) || (i == 5 && j == 4) || (i == 3 && j == 4) || (i == 5 && j == 5)) {
                    grid[i][j].setTileCost(grid[i][j]);
                }
                if((i == 2 && j == 8) || (i == 3 && j == 7)) {
                    grid[i][j].setTileCost(grid[i][j]);
                }
                if((i == 8 && j == 8) || (i == 8 && j == 7) || (i == 7 && j == 8)) {
                    grid[i][j].setTileCost(grid[i][j]);
                }
            }
        }

        //turn on all Barricades
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if((i == 6 && j == 1) || (i == 2 && j == 7) || (i == 4 && j == 4)) {
                    grid[i][j].activateBarricade();
                }
            }
        }

        //turn on all ShockPanels
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if((i == 6 && j == 2) || (i == 2 && j == 6)) {
                    grid[i][j].activateShockPanel();
                }
            }
        }

        setGridDiff();
        
        addObject(new ExitMissionButton(), 4, 4);
        //2, 0, 1, 1, 0, 2)
        //(8, 6, 7, 7, 6, 8)
        //add players into the world, and enemies where appropriate based on difficulty level (1 - 4 = Recruit - Elite Difficulty, 5 = Elimination Mode)
        addPlayers(0, 3, 0, 4, 0, 5);
        if(diff == 1 || diff == 2 || diff == 3 || diff == 4) {
            addEnemies(8, 3, 8, 4, 8, 5);
        }
        if(diff == 5) {
            addElimChar(8, 4);
        }
    }
    //act mehtod of Grid
    public void act() {
        //if the player presses the "s" key to start the mission and the CharacterSuper Actor is not already placed, add the object into the world
        if(!missionLogicSet) {
            MissionLogic missionLogic = new MissionLogic();
            addObject(missionLogic,4,4);
            
            //set the value to true to make sure it only happens once
            missionLogicSet = true;
        }
    }   

    //get method for returning the playerOne object
    protected PlayerUnit getPlayerOne() {
        return playerOne;
    }
    //get method for returning the playerTwo object
    protected PlayerUnit getPlayerTwo() {
        return playerTwo;
    }
    //get method for returning the playerThree object
    protected PlayerUnit getPlayerThree() {
        return playerThree;
    }
    //get method for returning the enemyOne object
    protected EnemyUnit getEnemyOne() {
        return enemyOne;
    }
    //get method for returning the enemyTwo object
    protected EnemyUnit getEnemyTwo() {
        return enemyTwo;
    }
    //get method for returning the enemyThree object
    protected EnemyUnit getEnemyThree() {
        return enemyThree;
    }
    //get method for returning the heavy object
    protected HeavyUnit getHeavyUnit() {
        return heavy;
    }    
    
    public void setGridDiff() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(difficultyFile));
            String lineOut = new String();
            lineOut = reader.readLine();
            diff = Integer.parseInt(lineOut);
            
            //depending on the difficulty chosen, the enemy HP will be adjusted
            if(diff == 1) {
                enemyHealth = 20;
            }
            if(diff == 3) {
                enemyHealth = 35;
            }
            if(diff == 4) {
                enemyHealth = 40;
            }
            if(diff == 5) {
                enemyHealth = 60;
            }
                        
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            //no file found or error so set default difficulty to 1
            diff = 1;
        }
    }
    
    //add the player characters into the world by taking in int parameters for coordinates, getting the nodes corresponding to them and using them to instantiate one of each, then add them into the world
    private void addPlayers(int x, int y, int n, int f, int j, int k) {
        Node node = getNode(x, y);
        Node nodeTwo = getNode(n, f);
        Node nodeThree = getNode(j, k);
        playerOne = new PlayerUnit(node, 30);
        playerTwo = new PlayerUnit(nodeTwo, 30);
        playerThree = new PlayerUnit(nodeThree, 30);
        addObject(playerOne, x, y);
        addObject(playerTwo, n, f);
        addObject(playerThree, j, k);
    }
    
    //add the AI/enemy characters into the world by taking in int parameters for coordinates, getting the nodes corresponding to them and using them to instantiate one of each, then add them into the world
    private void addEnemies(int x, int y, int n, int f, int j, int k) {
        Node node = getNode(x, y);
        Node nodeTwo = getNode(n, f);
        Node nodeThree = getNode(j, k);
        enemyOne = new EnemyUnit(node, enemyHealth);
        enemyTwo = new EnemyUnit(nodeTwo, enemyHealth);
        enemyThree = new EnemyUnit(nodeThree, enemyHealth);
        addObject(enemyOne, x, y);
        addObject(enemyTwo, n, f);
        addObject(enemyThree, j, k);
    }
    
    //using the same process for adding in the other characters, this time adding only the elimnaion mode character
    private void addElimChar(int x, int y) {
        Node node = getNode(x, y);
        heavy = new HeavyUnit(node, enemyHealth);
        addObject(heavy, x, y);
    }
    
    //get method for getting the node corresponding to coordinates
    public Node getNode(int x, int y) {
        return grid[x][y];
    }
    //get method for getting the 2D array "grid" which holds the nodes 
    public Node[][] getGrid() {
        return this.grid;
    }
    //get the number of nodes
    public int getNumOfNodes() {
        return nodeCount;
    }
    //get the difficulty level
    public int getDiff() {
        return diff;
    }

    //check whether or not a node is in the grid (as a form of validation)
    public boolean containsNode(Node node) {
        boolean confirm = false;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == node) {
                    confirm = true;
                    break;
                }
            }
        }
        return confirm;
    }
    
    //check whether the coordinates are in the grid (as a form of validation)
    public boolean containsCoordinates(int x,int y) {
        boolean confirmCoords = false;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(i == x && j == y) {
                    confirmCoords = true;
                    break;
                }
            }
        }
        return confirmCoords;
    }
    
    //creates the tiles and adds them into the world
    public void outputTiles() {
        //to store the current column the nested for loop is on, to be used as the y coordinate
        int column = 0;
        //to store the current row the nested for loop is on, to be used as the x coordinate
        int row = 0;
        //iterator to iterate through the array of images
        int iterator = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                Node node = new Node(tileTypes[iterator], i,j);
                grid[i][j] = node;

                //add the tile (node object) into the world
                addObject(node, row, column);
                //increment the number of nodes as a new one has been created and added
                nodeCount++;

                //increment column
                column++;

                iterator++;

                //9 tiles per row, if column reaches max increment row
                if(column == 9) {
                    column = 0;
                    row++;
                }
            }
        }
    }
}