package game;

public class NPC {

    private String name;
    private String desc;

    public NPC(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void say(String dialog) {
        Game.print(name + ": " + dialog);
    }

    public void talk() {
        Game.print("You can't talk to " + name + ".");
    }

    public void response(int option) {
        
        Game.print(name + " doesn't have a specific response for this option.");
    }

    public void getResponse(String[] options) {
        if (options.length == 0) {
            Game.print(name + " has nothing to say.");
            return;
        }

        for (int i = 0; i < options.length; i++) {
            Game.print("Option " + (i + 1) + ": " + options[i]);
        }

        Game.print("Enter an option (1-" + options.length + "):");

        try {
            int option = Game.scan.nextInt();
            Game.scan.nextLine(); 
            if (option >= 1 && option <= options.length) {
                response(option);
            } else {
                Game.print("Invalid option. Try again.");
            }
        } catch (Exception e) {
            Game.print("Invalid input. Please enter a number.");
            Game.scan.nextLine(); 
        }
    }
}
