package game;

public class Item {
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }

    public void open() {
        System.out.println("You can't open that!");
    }

    public void use() {
        switch (name.toLowerCase()) {
            case "magic scroll":
                revealHiddenMessage();
                break;
            case "teleportation crystal":
                teleportPlayer();
                break;
            case "ancient book":
                createSpecialKey();
                break;
            default:
                System.out.println("You can't use this item.");
        }
    }

    private void revealHiddenMessage() {
        System.out.println("The Magic Scroll reveals: 'Seek the crystal to find a hidden passage.'");
    }

    private void teleportPlayer() {
        System.out.println("The Teleportation Crystal glows and transports you to a different room!");
        Game.teleportToRoom("East"); 
    }

    private void createSpecialKey() {
        System.out.println("The Ancient Book opens, and a mysterious key materializes!");
        Game.addToInventory(new Item("Mysterious Key", "A key that unlocks a secret chamber."));
    }

   
    public static class Combination extends Item {
        public Combination(String name, String description) {
            super(name, description);
        }

        @Override
        public void use() {
            System.out.println("If you find a safe, try opening it!");
        }
    }

   
    public static class Safe extends Item {
        public Safe(String name, String description) {
            super(name, description);
        }

        @Override
        public void open() {
            Item combination = Game.getInventoryItem("combination");
            if (combination != null) {
                System.out.println("Using the combination, you open the safe and find a diamond inside! Naturally, you pocket the diamond.");
                Item diamond = new Item("Diamond", "A shiny, precious diamond.");
                Game.addToInventory(diamond);
            } else {
                System.out.println("The safe is locked, and you don't have the combination.");
            }
        }
    }
}


