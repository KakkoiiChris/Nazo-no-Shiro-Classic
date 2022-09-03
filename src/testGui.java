import javax.swing.JFrame;

public class testGui
{
   public static void main(String[] args)
   {
      OptionsMenu a = new OptionsMenu();
      a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      a.setSize(300, 200);
      a.setVisible(true);
   }
}