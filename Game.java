package game;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private static String currentRoom;
    private static HashMap<String, Item> inventory = new HashMap<>();
    private static HashMap<String, Wing> rooms = new HashMap<>();
    public static Scanner scan = new Scanner(System.in);

    
    public static void main(String[] args) {
        runGame();
        
    }

    public static void print(Object obj) { 
        System.out.println(obj.toString()); 
    }
    
    public static Item getInventoryItem(String itemName) {
        return inventory.get(itemName.toLowerCase());
    }

    public static void addToInventory(Item item) {
        inventory.put(item.getName().toLowerCase(), item);
        print(item.getName() + " has been added to your inventory.");
    }
   
    
    public static void runGame() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Dungeon!");

        String command;
        do {
            System.out.println("\nYou are in the " + currentRoom.getDescription());
            System.out.print("What would you like to do? ");
            command = input.nextLine();
            String[] words = command.split(" ");
            String action = words[0].toLowerCase();
            String itemName = words.length > 1 ? words[1].toLowerCase() : "";

            switch (action) {
                case "n":
                case "e":
                case "s":
                case "w":
                case "u":
                case "d":
                    Wing newRoom = currentRoom.getExit(action.charAt(0));
                    if (newRoom != null) {
                        currentRoom = newRoom;
                        print("You move to the " + currentRoom.getDescription());
                    } else {
                        print("You can't move in that direction.");
                    }
                    break;

                case "take":
                    if (!itemName.isEmpty()) {
                        Item item = currentRoom.getItem(itemName);
                        if (item != null) {
                            currentRoom.removeItem(itemName);
                            inventory.put(itemName, item);
                            print("You take the " + itemName + ".");
                        } else {
                            print("There is no " + itemName + " here.");
                        }
                    } else {
                        print("Specify an item to take.");
                    }
                    break;

                case "look":
                    if (!itemName.isEmpty()) {
                        Item item = inventory.get(itemName);
                        if (item == null) {
                            item = currentRoom.getItem(itemName);
                        }
                        if (item != null) {
                            print("You look at the " + itemName + ": " + item.getDescription());
                        } else {
                            print("There is no " + itemName + " here or in your inventory.");
                        }
                    } else {
                        print("Specify an item to look at.");
                    }
                    break;

                case "use":
                    if (!itemName.isEmpty()) {
                        Item item = inventory.get(itemName);
                        if (item == null) {
                            item = currentRoom.getItem(itemName);
                        }
                        if (item != null) {
                            item.use();
                        } else {
                            print("You don't have " + itemName + " to use.");
                        }
                    } else {
                        print("Specify an item to use.");
                    }
                    break;

                case "open":
                    if (!itemName.isEmpty()) {
                        Item item = inventory.get(itemName);
                        if (item == null) {
                            item = currentRoom.getItem(itemName);
                        }
                        if (item != null) {
                            item.open();
                        } else {
                            print("You don't have " + itemName + " to open.");
                        }
                    } else {
                        print("Specify an item to open.");
                    }
                    break;

                case "x":
                    print("Thanks for playing!");
                    break;

                default:
                    print("I don't know what that means.");
            }
        } while (!command.equals("x"));

        input.close();
    }
    public static void unlockRoom(Wing room) {
        if (room.isLocked()) {
            room.setLocked(false);
            print("The " + room.getDescription() + " is now unlocked!");
        }
    }

    public static void teleportToRoom(Wing targetRoom) {
        currentRoom = targetRoom;
        print("You have been teleported to the " + currentRoom.getDescription() + "!");
    }

    public class Puppy extends NPC {

        public Puppy() {
            super("Puppy", "A hideous puppy wags his tail.");
        }

        @Override
        public void talk() {
            say("HI! I'm an adorable puppy!");
            String[] options = {
                "Yes you are! Who's a good boy?", 
                "Ew, no. You're actually kinda hideous."
            };
            getResponse(options);
        }

        @Override
        public void response(int option) {
            switch (option) {
                case 1:
                    say("Awww, thank you! You're my best friend now!");
                    break;
                case 2:
                    say("Oh... that's mean. I just wanted to be friends...");
                    break;
                default:
                    say("I don't understand what you mean...");
            }
        }
    }

}

