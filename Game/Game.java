package game;

import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private Wing currentRoom;
    private final HashMap<String, Item> inventory = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    private Riddler riddler;

    public Game() {
        initializeGame();
    }

    private void initializeGame() {
        currentRoom = new Wing("Entrance Hall");
        riddler = new Riddler();


        currentRoom.setNPC(riddler);
    }


    public void runGame() {
        System.out.println("Welcome to the Dungeon!");

        String command;
        do {
            System.out.println("\nYou are in the " + currentRoom.getDescription());
            currentRoom.listItems();
            
            
            private void initializeGame() {
                currentRoom = new Wing("Entrance Hall");
                riddler = new Riddler("What has keys but no locks, space but no room, and you can enter but not go in?", "keyboard");
                currentRoom.setNPC(riddler);
            }


            System.out.print("What would you like to do? ");
            command = scanner.nextLine().trim().toLowerCase();

            if (command.isEmpty()) {
                System.out.println("Please enter a command.");
                continue;
            }

            String[] words = command.split(" ");
            String action = words[0];
            String target = (words.length > 1) ? words[1] : "";

            handleCommand(action, target);

        } while (!command.equals("exit"));

        System.out.println("Thanks for playing!");
    }

    private void handleCommand(String action, String target) {
        switch (action) {
            case "n":
            case "e":
            case "s":
            case "w":
            case "u":
            case "d":
                move(action.charAt(0));
                break;

            case "take":
                if (!target.isEmpty()) takeItem(target);
                else System.out.println("Specify an item to take.");
                break;

            case "look":
                if (!target.isEmpty()) lookAtItem(target);
                else System.out.println("Specify an item to look at.");
                break;

            case "use":
                if (!target.isEmpty()) useItem(target);
                else System.out.println("Specify an item to use.");
                break;

            case "talk":
                if (!target.isEmpty()) talkToNPC(target);
                else System.out.println("Specify who to talk to.");
                break;

            case "exit":
                break;

            default:
                System.out.println("Invalid command.");
        }
    }

    private void move(char direction) {
        Wing nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("You can't move in that direction.");
            return;
        }
        if (nextRoom.isLocked()) {
            if (inventory.containsKey("key")) {
                nextRoom.setLocked(false);
                System.out.println("You used a key to unlock the " + nextRoom.getDescription());
            } else {
                System.out.println("The " + nextRoom.getDescription() + " is locked. You need a key.");
                return;
            }
        }
        currentRoom = nextRoom;
        System.out.println("You move to the " + currentRoom.getDescription());
    }

    private void takeItem(String itemName) {
        Item item = currentRoom.getItem(itemName);
        if (item != null) {
            inventory.put(itemName, item);
            currentRoom.removeItem(itemName);
            System.out.println("You take the " + itemName + ".");
        } else {
            System.out.println("There is no " + itemName + " here.");
        }
    }

    private void lookAtItem(String itemName) {
        Item item = inventory.getOrDefault(itemName, currentRoom.getItem(itemName));
        if (item != null) {
            System.out.println("You look at the " + itemName + ": " + item.getDescription());
        } else {
            System.out.println("There is no " + itemName + " here or in your inventory.");
        }
    }

    private void useItem(String itemName) {
        Item item = inventory.get(itemName);
        if (item != null) {
            item.use();
        } else {
            System.out.println("You don't have " + itemName + " in your inventory.");
        }
    }

    private void talkToNPC(String npcName) {
        NPC npc = currentRoom.getNPC();
        if (npc != null && npc.getName().equalsIgnoreCase(npcName)) {
            npc.talk();
            
            
            if (npc instanceof Riddler) {
                System.out.print("Your answer: ");
                String answer = scanner.nextLine().trim();
                ((Riddler) npc).answer(answer);
            }
        } else {
            System.out.println("There is no " + npcName + " here.");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.runGame();
    }
}
