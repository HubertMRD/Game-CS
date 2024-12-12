package game;

public class Item {
    private final String name;
    private final String description;

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

    public void use() {
        System.out.println("You can't use the " + name + ".");
    }

    public void open() {
        System.out.println("You can't open the " + name + ".");
    }

    @SuppressWarnings("unused")
    String getDesc() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
