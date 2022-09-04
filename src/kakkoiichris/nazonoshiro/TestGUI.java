package kakkoiichris.nazonoshiro;

import javax.swing.*;

public class TestGUI {
    public static void main(String[] args) {
        var menu = new OptionsMenu();

        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(300, 200);
        menu.setVisible(true);
    }
}