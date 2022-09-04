package kakkoiichris.nazonoshiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class OptionsMenu extends JFrame {
    private static final String[] mapNames = { "-Choose One-", "Original Castle", "Double Classic", "The Ziggurat" };
    
    private final JComboBox<String> box = new JComboBox<>(mapNames);
    
    private String map = "null";
    
    public OptionsMenu() {
        super("Options Menu");
        
        setLayout(new FlowLayout());
        
        box.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                map = mapNames[box.getSelectedIndex()];
                
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        
        var subtitle = new JLabel("Generate Castle:");
        subtitle.setToolTipText("The map you will play on in a new game.");
        
        add(subtitle);
        add(box);
    }
    
    public String getMap() {
        return map;
    }
}