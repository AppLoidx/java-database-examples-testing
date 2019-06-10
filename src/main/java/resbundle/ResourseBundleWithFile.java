package resbundle;

import javax.swing.*;
import java.awt.*;

/**
 * @author Arthur Kupriyanov
 */
public class ResourseBundleWithFile {

        public static void main(String args[]) {
            JFrame f = new JFrame("JPasswordField Sample");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel p = new JPanel();
//            Box b = Box.createHorizontalBox();
            Box b = Box.createVerticalBox();
            b.add(new JLabel("box1"));
            b.add(new JLabel("box2"));
            p.setLayout(new GridLayout(3, 2));
            p.add(new JLabel("1"));
            p.add(new JLabel("2"));
            p.add(new JLabel("3"));
            p.add(new JLabel("4"));

            b.add(p);

            f.add(b);
            f.setSize(300, 200);
            f.setVisible(true);
        }
}
