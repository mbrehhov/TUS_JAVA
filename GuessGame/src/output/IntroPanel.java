package output;

import entry.MainFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroPanel extends JPanel {
   
    JPanel introPanel;      
    
   
    public JPanel getIntroPanel() {
        return introPanel;
    }


    public IntroPanel(MainFrame mainFrame) {
        introPanel = new JPanel(new BorderLayout());


        
      JButton button = new JButton("new game");
      button.setBackground(Color.blue);
      button.setForeground(Color.white);
    
      button.addActionListener(mainFrame);


      JButton score = new JButton("High Score");
      button.setBackground(Color.blue);
      button.setForeground(Color.BLACK);
      score.addActionListener(mainFrame);


      JPanel bottomPanel = new JPanel(new FlowLayout());
      bottomPanel.add(score, BorderLayout.CENTER);

      bottomPanel.add(button);
      introPanel.add(bottomPanel, BorderLayout.PAGE_END);


        String imgPath = System.getProperty("user.dir") + "/img/TUS.png";
        introPanel.add(new JLabel(new ImageIcon(imgPath)));
     
     
    }

}
