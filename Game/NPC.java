public class Riddler extends NPC {
    private String riddle;
    private String answer;

    public Riddler(String riddle, String answer) {
        super("Riddler", "A mysterious figure who loves riddles");
        this.riddle = riddle;
        this.answer = answer.toLowerCase();
    }

    // ... rest of the class implementation
}
