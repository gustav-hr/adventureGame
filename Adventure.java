import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class Adventure {

    private String useraction = "";
    private Room currentRoom;

    public Adventure() {
        lavAdventure();
    }

    public void lavAdventure() {

        Room room1 = new Room(1, "There are 2 doors. ");
        Room room2 = new Room(2, "There are 2 doors. ");
        Room room3 = new Room(3, "There are 2 doors. ");
        Room room4 = new Room(4, "There are 2 doors. ");
        Room room5 = new Room(5, "Congrats! You've reached room 5. ");
        Room room6 = new Room(6, "There are 2 doors. ");
        Room room7 = new Room(7, "There are 2 doors. ");
        Room room8 = new Room(8, "There are 3 doors. ");
        Room room9 = new Room(9, "There are 2 doors.");

        currentRoom = room1;

        room1.setN(null);
        room1.setE(room2);
        room1.setS(room4);
        room1.setW(null);

        room2.setN(null);
        room2.setE(room3);
        room2.setS(null);
        room2.setW(room1);

        room3.setN(null);
        room3.setE(null);
        room3.setS(room6);
        room3.setW(room2);

        room4.setN(room1);
        room4.setE(null);
        room4.setS(room7);
        room4.setW(null);

        room5.setN(null);
        room5.setE(null);
        room5.setS(room8);
        room5.setW(null);

        room6.setN(room3);
        room6.setE(null);
        room6.setS(room9);
        room6.setW(null);

        room7.setN(room4);
        room7.setE(room8);
        room7.setS(null);
        room7.setW(null);

        room8.setN(room5);
        room8.setE(room9);
        room8.setS(null);
        room8.setW(room7);

        room9.setN(room6);
        room9.setE(null);
        room9.setS(null);
        room9.setW(room8);


    }

    public void adventure() {
        System.out.println("Hello and welcome to the text adventure game! Please begin the game by giving your character a name:");
        Scanner scanner = new Scanner(System.in);
        String playerName;
        String name = scanner.nextLine();
        playerName = name;
        System.out.println("Your characters name is " + playerName + ". The objective for " + playerName + " is to reach room 5 without losing 100 HP.");
        System.out.println("To move around, write either 'go west', 'go east', go west', 'go south'.");
        System.out.println("You can at all times write 'help', 'look' or 'exit'.");
        System.out.println(playerName+ " starts in room 1.");

        Scanner input = new Scanner(System.in);
        String useraction = "";

        while (!useraction.equalsIgnoreCase("exit")) {
            useraction = input.nextLine();


            switch (useraction) {

                case "help" -> {
                    System.out.println("To move around, write either 'go north', 'go east', go west', 'go south'.");
                    System.out.println("You can at all times write 'help', 'look' or 'exit'.");
                }

                case "look" -> {
                    System.out.println(currentRoom);
                }

                case "go south", "s", "south" -> {
                    if (currentRoom.getS() == null) {
                        System.out.println( playerName+" can't move that way.");
                    } else {
                        currentRoom = currentRoom.getS();
                        System.out.println(currentRoom);
                    }
                }

                case "go north", "n", "north" -> {
                    if (currentRoom.getN() == null) {
                        System.out.println(playerName+" can't move that way.");
                    } else {
                        currentRoom = currentRoom.getN();
                        System.out.println(currentRoom);
                    }
                }

                case "go west", "w", "west" -> {
                    if (currentRoom.getW() == null) {
                        System.out.println(playerName+" can't move that way.");
                    } else {
                        currentRoom = currentRoom.getW();
                        System.out.println(currentRoom);
                    }
                }

                case "go east", "e", "east" -> {
                    if (currentRoom.getE() == null) {
                        System.out.println(playerName +" can't move that way.");
                    } else {
                        currentRoom = currentRoom.getE();
                        System.out.println(currentRoom);
                    }
                }
            }
        }
    }



}





