import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Node class contains all information about each tile in the Grid world, such as x and y, cost, neighbouring tiles, and any special environmental interactions
 * 
 * Author: Kyle Connolly
 * Version: 12/02/23
 */
public class Node extends Actor 
{
    // instance variables
    private Node northTile;

    private Node southTile;

    private Node eastTile;

    private Node westTile;

    private int tileCost;

    private int gridCoordinateX;

    private int gridCoordinateY;

    //to store if the tile has an active Barricade
    private boolean barricadeOn;

    private boolean shockPanelOn;

    private String tileImage;

    /**
     * Constructor for objects of class Node
     */
    public Node(String image, int x, int y)
    {
        this.tileImage = image;

        setImage(tileImage);

        this.northTile = null;

        this.southTile = null;

        this.eastTile = null;

        this.westTile = null;

        this.tileCost = 1;

        this.gridCoordinateX = x;

        this.gridCoordinateY = y;

        this.barricadeOn = false;

        this.shockPanelOn = false;
    }

    public int getX() {
        return this.gridCoordinateX;
    }

    public int getY() {
        return this.gridCoordinateY;
    }
    
    public void setX(int x) {
        this.gridCoordinateX = x;
    }
    
    public void setY(int y) {
        this.gridCoordinateY = y;
    }

    public int getTileCost() {
        return this.tileCost;
    }

    public void setTileCost(Node node) {
        this.tileCost = 2;
    }

    public Node getNode(Node node) {
        return node;
    }

    public void setNorthNode(Node node) {
        this.northTile = node;
    }

    public Node getNorthNode() {
        return northTile;
    }

    public void setSouthNode(Node node) {
        this.southTile = node;
    }

    public Node getSouthNode() {
        return southTile;
    }

    public void setWestNode(Node node) {
        this.westTile = node;
    }

    public Node getWestNode() {
        return westTile;
    }

    public void setEastNode(Node node) {
        this.eastTile = node;
    }

    public Node getEastNode() {
        return eastTile;
    }

    
    public boolean getBarricade() {
        return barricadeOn;
    }

    public void activateBarricade() {
        this.barricadeOn = true;
    }

    public void deactivateBarricade() {
        this.barricadeOn = false;
    }

    
    public boolean getShockPanel() {
        return shockPanelOn;
    }

    public void activateShockPanel() {
        this.shockPanelOn = true;
    }

    public void deactivateShockPanel() {
        this.shockPanelOn = false;
    }

    public void changeActionImage(int imageNum) {
        if(imageNum == 1) {
            this.setImage(new GreenfootImage("BarricadeOn.png"));
        }
        if(imageNum == 2) {
            this.setImage(new GreenfootImage("ShockPanelOn.png"));
        }
    }
}