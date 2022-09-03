package kakkoiichris.nazonoshiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PerkSelectGuiTest extends JFrame {
    private JTextField p1, p2, p3;
    private JCheckBox war, car, puz, wei, ana, bil;
    protected int A = 0, B = 0, C = 0;
    
    public PerkSelectGuiTest() {
        super("Perk Selector");
        setLayout(new FlowLayout());
        
        p1 = new JTextField("Perk 1", 20);
        add(p1);
        
        p2 = new JTextField("Perk 2", 20);
        add(p2);
        
        p3 = new JTextField("Perk 3", 20);
        add(p3);
        
        Handler h = new Handler();
        
        war = new JCheckBox("Warrior");
        war.setToolTipText("Start with higher stats.");
        war.addItemListener(h);
        add(war);
        
        car = new JCheckBox("Cartographer");
        car.setToolTipText("Create a detailed and viewable map as you explore.");
        car.addItemListener(h);
        add(car);
        
        puz = new JCheckBox("Puzzlist");
        puz.setToolTipText("Puzzles are easier to solve.");
        puz.addItemListener(h);
        add(puz);
        
        wei = new JCheckBox("Weightlifter");
        wei.setToolTipText("Be able carry more items.");
        wei.addItemListener(h);
        add(wei);
        
        ana = new JCheckBox("Analyst");
        ana.setToolTipText("Descriptions are more detailed.");
        ana.addItemListener(h);
        add(ana);
        
        bil = new JCheckBox("Bilinguist");
        bil.setToolTipText("Understand any Japanese.");
        bil.addItemListener(h);
        add(bil);
    }
    
    public class Handler implements ItemListener {
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