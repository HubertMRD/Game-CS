package game;

public class Riddler extends NPC {

    private final Item[] riddles;
    private String[] answers;
    private int currentRiddle;

    public Riddler() {
        super("Riddler", "A mysterious figure who loves riddles.");
        this.riddles = new Item[] {
            new Item("Riddle #1", "I go on red, but stop for green. What am I doing?"),
            new Item("Riddle #2", "What time is it when an elephant sits on a fence?"),
            new Item("Riddle #3", "A man in a car saw a golden door, a silver door, and a bronze door."),
            new Item("Riddle #4", "What goes up but never comes down?"),
            new Item("Riddle #5", "What is there one of in every corner and two of in every room?"),
            new Item("Riddle #6", "What is stronger than steel but can’t handle the sun?"),
            new Item("Riddle #7", "What is it that no one wants, but no one wants to lose?"),
            new Item("Riddle #8", "The more there is, the less you see. What is it?"),
            new Item("Riddle #9", "I can fly but have no wings. I can cry but I have no eyes. Wherever I go, darkness follows me. What am I?"),
            new Item("Riddle #10","The more you take, the more you leave behind. What are they?")
        };
        this.answers = new String[] {
            "driving", "time to get a new fence", "use the door handle", "age", "o", "Ice", "A Lawsuit", "Darkness", "Clouds", "Footsteps"
        };
        this.currentRiddle = 0;
    }

    @Override
    public void talk() {
        if (currentRiddle < riddles.length) {
            say(riddles[currentRiddle].getDesc());
        } else {
            say("You have solved all my riddles! You may proceed.");
        }
    }

    public void answer(String playerAnswer) {
        if (currentRiddle < riddles.length) {
            if (playerAnswer.equalsIgnoreCase(answers[currentRiddle])) {
                say("Correct! You may move to the next riddle.");
                currentRiddle++;
                if (currentRiddle < riddles.length) {
                    talk();
                } else {
                    say("Congratulations! You have solved all my riddles.");
                }
            } else {
                say("Wrong answer. Try again.");
            }
        } else {
            say("You have already solved all my riddles.");
        }
    }
}
