import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {

    private int healthPoints;
    private Room currentRoom;
    private ArrayList<Item> itemArrayList;


    // Herunder står alt som har noget med HP at gøre.
    @Override
    public String toString() {
        return "Health " + healthPoints;
    }

    public void addHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }


    public Player(Room firstRoom, int healthPoints) {
        this.healthPoints = healthPoints;
        this.currentRoom = firstRoom;
        this.itemArrayList = new ArrayList<>();
    }

    // Herunder står alt der har noget med spillerens lokation at gøre, og om det er muligt at bevæge sig den vej.

    public void getS() {
        if (currentRoom.getS() != null) {
            currentRoom = currentRoom.getS();
        } else {
            System.out.println("Unfortunately, a wall is blocking the way.");
        }
    }

    public void getN() {
        if (currentRoom.getN() != null) {
            currentRoom = currentRoom.getN();
        } else {
            System.out.println("Unfortunately, a wall is blocking the way.");
        }
    }


    public void getE() {
        if (currentRoom.getE() != null) {
            currentRoom = currentRoom.getE();
        } else {
            System.out.println("Unfortunately, a wall is blocking the way.");
        }

    }

    public void getW() {
        if (currentRoom.getW() != null) {
            currentRoom = currentRoom.getW();
        } else {
            System.out.println("Unfortunately, a wall is blocking the way.");

        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void addItemToInventory(Item item) {
        itemArrayList.add(item);
    }

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }
}


//
//    case "go south", "s", "south", "go s" -> {
//        if (currentRoom.getS() == null) {
//            System.out.println(playerName + " can't move that way.");
//        } else {
//            currentRoom = currentRoom.getS();
//            System.out.println(currentRoom);
//        }
//    }
//
//                case "go north", "n", "north", "go n" -> {
//        if (currentRoom.getN() == null) {
//            System.out.println(playerName + " can't move that way.");
//        } else {
//            currentRoom = currentRoom.getN();
//            System.out.println(currentRoom);
//        }
//    }
//
//                case "go west", "w", "west", "go w" -> {
//        if (currentRoom.getW() == null) {
//            System.out.println(playerName + " can't move that way.");
//        } else {
//            currentRoom = currentRoom.getW();
//            System.out.println(currentRoom);
//        }
//    }
//
//                case "go east", "e", "east", "go e" -> {
//        if (currentRoom.getE() == null) {
//            System.out.println(playerName + " can't move that way.");
//        } else {
//            currentRoom = currentRoom.getE();
//            System.out.println(currentRoom);
//        }
//    }


//    Room placement;
//
//    public Player(Room placement) {
//        this.placement = placement;
//    }
//
//
//}
//


//    public Room move(String movement) {
//    if (movement.equalsIgnoreCase("go north") && placement.getN() != null) {
//        placement = placement.getN();
//    } else if (movement.equalsIgnoreCase("go west") && placement.getS() != null) {
//        placement = placement.getS();
//        }
//    else if (movement.equalsIgnoreCase("go south") && placement.getW() != null) {
//        placement = placement.getW();
//    } else if (movement.equalsIgnoreCase("go east") && placement.getE() != null) {
//        placement = placement.getE();
//    } else {
//        return null;
//    }
//return placement;
//
//
//
//    }
//
//
//    }

