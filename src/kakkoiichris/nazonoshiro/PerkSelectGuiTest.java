package kakkoiichris.nazonoshiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PerkSelectGuiTest extends JFrame {
    protected int A = 0, B = 0, C = 0;
    
    public PerkSelectGuiTest() {
        super("Perk Selector");
        
        setLayout(new FlowLayout());
    
        var p1 = new JTextField("Perk 1", 20);
        add(p1);
    
        var p2 = new JTextField("Perk 2", 20);
        add(p2);
    
        var p3 = new JTextField("Perk 3", 20);
        add(p3);
    
        var h = new Handler();
    
        var war = new JCheckBox("Warrior");
        war.setToolTipText("Start with higher stats.");
        war.addItemListener(h);
        add(war);
    
        var car = new JCheckBox("Cartographer");
        car.setToolTipText("Create a detailed and viewable map as you explore.");
        car.addItemListener(h);
        add(car);
    
        var puz = new JCheckBox("Puzzlist");
        puz.setToolTipText("Puzzles are easier to solve.");
        puz.addItemListener(h);
        add(puz);
    
        var wei = new JCheckBox("Weightlifter");
        wei.setToolTipText("Be able carry more items.");
        wei.addItemListener(h);
        add(wei);
    
        var ana = new JCheckBox("Analyst");
        ana.setToolTipText("Descriptions are more detailed.");
        ana.addItemListener(h);
        add(ana);
    
        var bil = new JCheckBox("Bilingual");
        bil.setToolTipText("Understand any Japanese.");
        bil.addItemListener(h);
        add(bil);
    }
    
    public static class Handler implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
        
        }
    }
    
    public static void main(String[] arg) {
        PerkSelectGuiTest a = new PerkSelectGuiTest();
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setSize(300, 200);
        a.setVisible(true);
    }
}