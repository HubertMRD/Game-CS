package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Interface extends JFrame implements ActionListener {

    static JTextArea textArea = new JTextArea(); 
    private JPanel panel = new JPanel(); 
    static TextField textF = new TextField(); 
    private JScrollPane scroller = new JScrollPane(textArea);

    public Interface() {
        buildWindow();
    }

    private void buildWindow() {
        setTitle("Riddler Dungeon"); 
        setLayout(new BorderLayout()); 

        
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setEditable(false); 
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scroller, BorderLayout.CENTER);

        
        panel.setLayout(new GridLayout(2, 1)); 
        panel.setBackground(Color.BLACK);

        
        JLabel prompt = new JLabel("What would you like to do?");
        prompt.setOpaque(true);
        prompt.setFont(new Font("SansSerif", Font.BOLD, 12));
        prompt.setForeground(Color.WHITE);
        prompt.setBackground(Color.BLACK);
        panel.add(prompt);

        
        textF.setFont(new Font("SansSerif", Font.PLAIN, 12));
        textF.setBackground(Color.BLACK);
        textF.setForeground(Color.WHITE);
        panel.add(textF);

        
        JButton execute = new JButton("Execute");
        execute.addActionListener(this); 
        execute.setFont(new Font("SansSerif", Font.BOLD, 12));
        execute.setForeground(Color.WHITE);
        execute.setBackground(Color.BLACK);
        panel.add(execute);

        
        add(panel, BorderLayout.SOUTH);

       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = textF.getText().trim();
        if (!command.isEmpty()) {
            processCommand(command);
            textF.setText(""); 
        }
    }

    private void processCommand(String command) {
       
        textArea.append("You entered: " + command + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength()); 

        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}
