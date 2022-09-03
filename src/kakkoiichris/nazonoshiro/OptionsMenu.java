package kakkoiichris.nazonoshiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class OptionsMenu extends JFrame {
    private JComboBox box;
    
    private static String[] mapNames = { "-Choose One-", "Original Castle", "Double Classic", "The Ziggurat" };
    
    private JLabel Subtitle;
    private String map = "null";
    
    public OptionsMenu() {
        super("Options Menu");
        setLayout(new FlowLayout());
        
        
        box = new JComboBox(mapNames);
        
        box.addItemListener
            (
                new ItemListener() {
                    public void itemStateChanged(ItemEvent event) {
                        if (event.getStateChange() == ItemEvent.SELECTED) {
                            map = mapNames[box.getSelectedIndex()];
                            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        }
                    }
                }
            );
        
        Subtitle = new JLabel("Generate Castle:");
        Subtitle.setToolTipText("The map you will play on in a new game.");
        
        add(Subtitle);
        add(box);
    }
    
    public String getMap() {
        return map;
    }
}