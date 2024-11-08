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
        Game.print("You can't open that!");
    }

    public void use() {
        Game.print("You can't use that!");
    }

    
    public static class Combination extends Item {
        public Combination(String name, String description) {
            super(name, description);
        }

        @Override
        public void use() {
            Game.print("If you find a safe, try opening it!");
        }
    }

  
    public static class Safe extends Item {
        public Safe(String name, String description) {
            super(name, description);
        }

        @Override
        public void open() {
            Item combination = Dungeon.getInventoryItem("combination");
            if (combination != null) {
                Game.print("Using the combination, you open the safe and find a diamond inside! Naturally, you pocket the diamond.");
                Item diamond = new Item("Diamond", "A shiny, precious diamond.");
                Dungeon.addToInventory(diamond);
            } else {
                Game.print("The safe is locked and you don't have the combination.");
            }
        }
    }
}


