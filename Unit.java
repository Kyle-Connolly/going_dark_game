import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.LinkedList; //amongst holding various lists of items it is used to store the resuls of the A* Search, and for holding for outward neighbouring vertices/nodes
import java.util.List; //for method used to access other class methods, list for outward neighbouring vertices/nodes, and for method used to access other class' methods
import java.lang.Math; //For square rooting, used to determine the distance between two points
import java.util.ArrayList; //array list for outward neighbouring vertices/nodes
import java.util.Map; //map for predecessor vertices/nodes
import java.util.HashMap; // dictionary (hash map) for predecessor vertices/nodes
import java.util.PriorityQueue; //priority queue for A* Search algorithm
import java.util.Comparator; //comparator to order the priority queue by lowest cost
/**
 * Unit is a blueprint for both player units and enemy units
 * 
 * Author: Kyle Connolly
 * Version: 12/02/23
 */
public class Unit extends Actor
{
    private static final int nodeCount = 81;
    private static final int attackDamage = 5;

    protected String unitID = "";
    protected int health = 0;
    protected Node unitNode;

    /**
     * Act - do whatever the Unit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }

    protected Node getPlayerNode(PlayerUnit unit) {
        return unit.unitNode;
    }

    protected void setPlayerNode(Node node, PlayerUnit unit) {
        unit.unitNode = node;
    }

    protected Node getEnemyNode(EnemyUnit unit) {
        return unit.unitNode;
    }

    protected void setEnemyNode(Node node, EnemyUnit unit) {
        unit.unitNode = node;
    }

    protected Node getHeavyNode(HeavyUnit unit) {
        return unit.unitNode;
    }

    protected void setHeavyNode(Node node, HeavyUnit unit) {
        unit.unitNode = node;
    }

    protected int getPlayerHP(PlayerUnit unit) {
        return unit.health;
    }

    protected void setPlayerHP(int hitPoints, PlayerUnit unit) {
        unit.health = hitPoints;
    }

    protected int getEnemyHP(EnemyUnit unit) {
        return unit.health;
    }

    protected void setEnemyHP(int hitPoints, EnemyUnit unit) {
        unit.health = hitPoints;
    }

    protected int getHeavyHP(HeavyUnit unit) {
        return unit.health;
    }

    protected void setHeavyUnitHP(int hitPoints, HeavyUnit unit) {
        unit.health = hitPoints;
    }

    protected int getNodeCount() {
        return nodeCount;
    }

    protected Node getUnitNode(Unit unit) {
        return unit.unitNode;
    }

    

    protected LinkedList<Node> aStarSearchAlg(Node startingNode, Node endNode) {
        //to record nodes visited for later comparison of algorithms
        int nodesVisited = 0;

        //retrieve start and end nodes in the grid
        Node startNode = startingNode;
        Node destinationNode = endNode;
        if(accessGridMethods().containsNode(startNode) && accessGridMethods().containsNode(destinationNode)) {
            //dictionary to keep estimated distance from the start node to end node
            //f(n) = g + h (heuristic = actual distance + heuristic distance)
            Map<Node, Integer> estDistance = new HashMap<Node, Integer>();
            //dictionary to keep predecessor Nodes, in order for shortest path to be re-traced
            Map<Node, Node> predecessorNodes = new HashMap<Node, Node>();
            //linked list to keep every Node visited so algorithm doesn't keep repeating forever
            LinkedList<Node> closedSet = new LinkedList<Node>();
            //order the nodes in the openset by the lowest value
            PriorityQueue<Node> openSet = new PriorityQueue<Node> (getNodeCount(), new Comparator<Node>()
                    {
                        public int compare(Node firstNode, Node secondNode) {
                            if(estDistance.get(firstNode) > estDistance.get(secondNode)) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    }
                );

            //add start node to openSet for consideration
            openSet.add(startNode);

            //startNode heursitic set to (f = g + h)
            estDistance.put(startNode, 0 + manhattanDistanceHeuristic(startNode, destinationNode));

            //while priority queue isn't empty (still need to consider nodes)
            while(openSet.size() > 0) {
                //nodes at front of the queue is removed
                Node currentNode = openSet.poll();

                //end node/destination reached so retrace path and output shortest route
                if(currentNode == destinationNode) {
                    //contruct path from the destination node to the start not in LIFO order
                    return recreatePath(startNode, destinationNode, predecessorNodes);
                }
                //node is added to closedSet
                closedSet.add(currentNode);

                //create a list to store neighbour nodes of the current node 
                List<Node> neighbours = new ArrayList<>();

                //assigning neighbouring nodes to the list and check if the neighbour node is not null
                if(currentNode.getNorthNode() != null) {
                    neighbours.add(currentNode.getNorthNode());
                }
                if(currentNode.getSouthNode() != null) {
                    neighbours.add(currentNode.getSouthNode());
                }
                if(currentNode.getWestNode() != null) {
                    neighbours.add(currentNode.getWestNode());
                }
                if(currentNode.getEastNode() != null) {
                    neighbours.add(currentNode.getEastNode());
                }

                //iterate through neighbouring nodes
                for(Node neighbour : neighbours) {
                    //if neighbour is not in the closed set
                    if(!closedSet.contains(neighbour)) {
                        //calculate the estimated cost for a neighbour node by getting the tile cost 
                        int neighbourNodeDistance = neighbour.getTileCost() + manhattanDistanceHeuristic(destinationNode, neighbour);

                        //if neighbouring node's new distance from start node is shorter from current node, update distances
                        if(!openSet.contains(neighbour) || neighbourNodeDistance < estDistance.get(neighbour)){
                            //update estDistance with new estimated distance
                            estDistance.put(neighbour, neighbourNodeDistance);

                            //record current path of previous nodes
                            predecessorNodes.put(neighbour, currentNode);

                            //add neighbour node to priority queue for consideration
                            openSet.add(neighbour);
                        }
                    }
                }
            }
        } else {
            System.out.println("Error: Invalid Coordinates/Nodes");
        }
        //return an empty Linked List
        LinkedList<Node> nodePath = new LinkedList<Node>();
        return nodePath;
    }
    //work out the distance in a grid system (no cutting corners) using the Manhattan Distance Heuristic
    protected int manhattanDistanceHeuristic(Node startVertex, Node endVertex) {
        //get the x and y coordinates of the start and end nodes
        int x1 = startVertex.getX();
        int y1 = startVertex.getY();
        int x2 = endVertex.getX();
        int y2 = endVertex.getY();
        //using the heurestic equation to work out the difference, using math.abs to return the absolute value 
        int manhattanDistance = Math.abs(x1-x2) + Math.abs(y1 - y2);
        return manhattanDistance;
    }
    //reconstruct the A* Search path of nodes in the correct order (LIFO)
    protected LinkedList<Node> recreatePath(Node startNode, Node endNode, Map<Node, Node> predecessorNodes) {
        //Linked List to store the path of nodes
        LinkedList<Node> nodePath = new LinkedList<Node>();
        //get the end Node and add it it to the linked list
        nodePath.add(endNode);
        //while the end node does not equal the start node, there are still more nodes to add so the end of the list has not been reached
        while(endNode != startNode ) {
            //retrieve predecessor node
            endNode = predecessorNodes.get(endNode);

            //add the node to the front of the linked list
            nodePath.add(0, endNode);
        }

        return nodePath;
    }

    /**
     * Access methods of Grid class by getting the instance of the Grid, and returning it
     */
    protected Grid accessGridMethods() {
        Grid grid = (Grid)getWorld();
        return grid;
    }
}
