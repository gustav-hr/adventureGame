import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {

    public void start() {

        Adventure adventure = new Adventure();
        System.out.println("Hello and welcome to the text adventure game! Please begin the game by giving your character a name:");
        Scanner scanner = new Scanner(System.in);
        String playerName;
        String name = scanner.nextLine();
        playerName = name;
        System.out.println("Your characters name is " + playerName + ". The objective for " + playerName + " is to reach room 5 without losing 100 HP.");
        System.out.println("To move around, use commands: 'go north', 'go east', go west', 'go south'.");
        System.out.println("You can at all times write 'help', 'look', 'exit', or 'backpack'.");
        System.out.println("If there are any items or consumables in the room your character is in, you can always pick them up and drop them whereever you like. For items, simply write: 'take <item>' or 'drop <item>'. For consumables, write 'eat <consumable> ' or 'drop <consumable>");
        System.out.println("After you have taken a weapon, you need to write 'equip <weapon name>' to equip it to your character.");
        System.out.println(playerName + " starts in room 1. Tip: Take a look around to see if there is anything useful for " + playerName + " to begin the game.");

        Scanner input = new Scanner(System.in);
        String useraction = "";

        while (!useraction.equalsIgnoreCase("exit")) {
            useraction = input.nextLine();
            String[] word = useraction.split(" ");
            String action = word[0];

            switch (action) {

                case "health", "hp", "HP" -> {
                    System.out.println(adventure.getPlayer().toString());
                }
                // Her gør jeg så man kan "consume" hvad end food man har.
                case "consume" -> {
                    if (word.length < 2) {
                        System.out.println("Consume what?");
                    } else {
                        String foodName = word[1].trim();
                        Food foodToConsume = null;

                        // Dene kode nedenunder sørger for at tjekke backpack og se om der er noget food i, som man kan consume.
                        for (Item item : adventure.getPlayer().getItemArrayList()) {
                            if (item instanceof Food && item.getItemName().equalsIgnoreCase((foodName))) {
                                foodToConsume = (Food) item;
                                break;
                            }
                        }
                        if (foodToConsume != null) {
                            adventure.getPlayer().addHealthPoints((int) foodToConsume.getHealthPoints()); // Denne linje sørger for at plusse det man indtager med ens HP.
                            adventure.getPlayer().getItemArrayList().remove(foodToConsume); // Denne linje fjerner den mad man har indtaget fra ens inventory, hvis man vælger at sige "consume".
                            System.out.println(playerName + " consumed " + foodName + ".");
                            System.out.println(playerName + " is now at " + adventure.getPlayer().getHealthPoints() + " HP");
                        } else if ((adventure.getPlayer().getHealthPoints() <= 0)) {
                            System.out.println("You died - game over.");
                            System.exit(0);
                        } else {
                            System.out.println("You don't have that consumable.");
                        }
                    }
                }
                // Her laver jeg hvordan man bruger sit weapon til at angribe mulige fjender.
                case "attack" -> {
                    if (word.length < 2) {
                        System.out.println("Attack what?");
                    } if (adventure.getPlayer().getEquippedWeapon().isEmpty()) {
                        System.out.println("You are not able to attack without a weapon.");
                    }
                    else {
                        Weapon currentWeapon = adventure.getPlayer().getCurrentweapon();
                        if (currentWeapon != null && currentWeapon.getRemainingUses() > 0) {
                            currentWeapon.useWeapon(); // Denne kode går tilbage til weapon-klassen hvor man siger UseWeapon, og den går derfor automatisk ned med én pr gang man affyrer noget ranged.
                            System.out.println(playerName + " attacked with " + currentWeapon.getItemName() + ". There are currently: " + currentWeapon.getRemainingUses() + " ammunition left on " + currentWeapon);
                        } else {
                            System.out.println("No remaining ammunition for " + currentWeapon.getItemName() + " or no ranged weapon is equipped.");
                        }
                    }
                }
                case "ammunition", "ammo" -> {
                    System.out.println("There is: " + adventure.getPlayer().getCurrentweapon().getRemainingUses() + " ammo left in " + adventure.getPlayer().getCurrentweapon());
                }

                // Her laver jeg at man kan equippe et våben. Når man skriver "attack"(ovenover) så bruger man det våben man har equippet.

                case "equip" -> {
                    if (word.length < 2) {
                        System.out.println("Equip what?");
                    } else {
                        String itemName = word[1].trim();
                        Item itemToEquip = null;

                        // Tjekker om varen findes i inventory
                        for (Item item : adventure.getPlayer().getItemArrayList()) {
                            if (item.getItemName().equalsIgnoreCase(itemName)) {
                                itemToEquip = item;
                                break;
                            }
                        }
                        // Denne kode tjekker om jeg har item i mit inventory. Hvis ikke, får jeg outprint herunder:
                        if (itemToEquip == null) {
                            System.out.println("You don't have that item in your inventory.");
                        }
                        // Her tjekker jeg om det jeg prøver at equippe er et våben, og outpriner hvis det ikke er et våben.
                        else if (!(itemToEquip instanceof Weapon)) {
                            System.out.println(itemToEquip.getItemName() + " is not a weapon.");
                        }
                        // Her tjekker min kode igen om det er et våben jeg forsøger at equippe, og hvis det er et våben, equipper den det som våben.
                        else {
                            Weapon weaponChosen = (Weapon) itemToEquip;
                            if (!adventure.getPlayer().getEquippedWeapon().isEmpty()) {
                                adventure.getPlayer().getEquippedWeapon().remove(0);
                                adventure.getPlayer().addWeaponToEquipped(weaponChosen);
                                System.out.println(playerName + " equipped " + weaponChosen.getItemName());
                            } else {
                                adventure.getPlayer().addWeaponToEquipped(weaponChosen);
                                System.out.println(playerName + " equipped " + weaponChosen.getItemName());
                            }
                        }
                    }
                }
                case "equipped" -> {
                    System.out.println(adventure.getPlayer().getEquippedWeapon() + " is curently equipped.");
                }

                case "take", "Take", "tAke", "TAKE" -> {
                    if (word.length < 2) {
                        System.out.println("Take what?"); // Hvis man bare skriver "take" uden noget efterfølgende, genkender koden at man vil samle noget op, men ikke hvad man vil samle op.
                    } else {
                        String itemName = word[1].trim(); // dette fjerner eventuelle unødvendige mellemrum.
                        Item item = adventure.getPlayer().getCurrentRoom().takeItem(itemName); // Her finder vi item og fjerner det igen fra Listen, hvis man tager det op.
                        if (item != null) {
                            System.out.println(playerName + " took " + item.getItemName());
                            adventure.getPlayer().addItemToInventory(item);
                        } else {
                            System.out.println("No such item in this room. ");
                        }
                    }
                }
                case "drop" -> {
                    if (word.length < 2) { // Ligesom før med at skulle "take", så er det bare drop.
                        System.out.println("Drop what?");
                    } else {
                        String itemName = word[1].trim();
                        Item itemToDrop = null;
                        for (Item item : adventure.getPlayer().getItemArrayList()) {
                            if (item.getItemName().equalsIgnoreCase(itemName)) {
                                itemToDrop = item;
                                break;
                            }
                        }
                        if (itemToDrop != null) {
                            adventure.getPlayer().getCurrentRoom().dropItem(itemToDrop);
                            adventure.getPlayer().getItemArrayList().remove(itemToDrop);
                            adventure.getPlayer().getEquippedWeapon().remove(0);
                            System.out.println("You dropped " + itemName + " in room " + adventure.getPlayer().getCurrentRoom().getRoomNumber() + ".");
                        } else {
                            System.out.println("You don't have that item.");
                        }
                    }
                }
                case "go" -> {
                    if (word.length < 2) {
                        System.out.println("Go where?");
                    } else {

                        if (word[1].equalsIgnoreCase("north") || word[1].equalsIgnoreCase("n")) {
                            adventure.getPlayer().getN();
                            System.out.println(playerName + " is now in room " + adventure.getPlayer().getCurrentRoom().getRoomNumber() + ".");
                            System.out.println(adventure.getPlayer().getCurrentRoom().getRoomDescription());
                        } else if (word[1].equalsIgnoreCase("south") || word[1].equalsIgnoreCase("s")) {
                            adventure.getPlayer().getS();
                            System.out.println(playerName + " is now in room " + adventure.getPlayer().getCurrentRoom().getRoomNumber() + ".");
                            System.out.println(adventure.getPlayer().getCurrentRoom().getRoomDescription());
                        } else if (word[1].equalsIgnoreCase("east") || word[1].equalsIgnoreCase("e")) {
                            adventure.getPlayer().getE();
                            System.out.println(playerName + " is now in room " + adventure.getPlayer().getCurrentRoom().getRoomNumber() + ".");
                            System.out.println(adventure.getPlayer().getCurrentRoom().getRoomDescription());
                        } else if (word[1].equalsIgnoreCase("west") || word[1].equalsIgnoreCase("w")) {
                            adventure.getPlayer().getW();
                            System.out.println(playerName + " is now in room " + adventure.getPlayer().getCurrentRoom().getRoomNumber() + ".");
                            System.out.println(adventure.getPlayer().getCurrentRoom().getRoomDescription());
                        }
                    }
                }

                case "inventory", "i", "backpack", "b", "inv" -> {
                    System.out.println(playerName + " has " + adventure.getPlayer().getItemArrayList() + " items in the backpack.");
                }


                case "look", "l", "Look" -> {
                    List<Item> items = adventure.getPlayer().getCurrentRoom().getItems();
                    if (items.isEmpty()) {
                        System.out.println("There is nothing useful for " + playerName + " to pick up in this room.");
                    } else {
                        System.out.println("You see the following items in the current room:");
                        for (Item item : items) {
                            System.out.println(item);
                        }
                    }
                }

                case "help" -> {
                    System.out.println("To move around, write either 'go north', 'go east', go west', 'go south'.");
                    System.out.println("You can at all times write 'help', 'look' or 'exit'.");
                }

                default -> {
                    System.out.println("Unknown command");
                }

            }


        }
    }

}
