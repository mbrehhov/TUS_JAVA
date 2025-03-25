package output;

import data.Core;
import data.Score;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainFrame extends JPanel implements ActionListener {
    // public static JFrame JF;
    public static GamePanel GP;
    public static IntroPanel IP;
    public static JFrame JF;

    
    JFrame mainFrame = null;
    Core cm = null;
    GamePanel gp = null;   
    IntroPanel ip = null;
    HSPanel ipp = null;
    public void refresh()
    {

        mainFrame.revalidate();
        mainFrame.repaint();
    }
    public void guip() {
        gp = new GamePanel(this); // add to list
        ip = new IntroPanel(this);
        ipp = new HSPanel(this);

       GP = gp;
       IP = ip;
       // Stats.JF = mainFrame;
       // Stats.MF = this;
        mainFrame = new JFrame();
        mainFrame.setSize(800, 600);
        JF = mainFrame;
        mainFrame.setResizable(false);
        //mainFrame.add(gp.getMainp());
        JF.add(ip.getIntroPanel());
        GuessGameWindAdaptor gAdp = new GuessGameWindAdaptor(this);
        mainFrame.addWindowListener(gAdp);

      // mainFrame.pack();
        mainFrame.setVisible(true);

    }

    class GuessGameWindAdaptor extends WindowAdapter {
        MainFrame f;

        public GuessGameWindAdaptor(MainFrame f) {
            this.f = f;
        }

        @Override
        public void windowClosing(WindowEvent we) {
            f.setVisible(false);
            System.exit(0);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("new game")) {
            JF.remove(ip.getIntroPanel());
            
            JF.repaint();
            JF.revalidate();
             JF.add(gp.getMainp());
             //JF.repaint();
            JF.revalidate();
             //change panel
            // start new game
             cm = new Core();
             cm.newGame(gp.getjTextAreaQuestions(), gp.getOptions(),gp.getHearts(),gp.getTimeLabel());

        } else
        if (str.equals("High Score")) {
            JF.remove(ip.getIntroPanel());
           // JF.add(hp.getHSPanel());
            //JF.add(gp.getMainp());

            JF.repaint();
            JF.revalidate();
            
            ipp.setData(new Score().getTopFive());
            JF.add(ipp.getHSPanel());
             //JF.repaint();
            JF.revalidate();   

        } else
        if (str.equals("Submit")) {

            for (JRadioButton jRadioButton : gp.getOptions()) {
                if (jRadioButton.isSelected()) {
                    //System.out.println(jRadioButton.getName());
                    //System.out.println(Tools.getInstance().getGameStat().getCurrentQuestionAnswer());
                    
                    cm.answerToQuestion(jRadioButton.getName().charAt(0));

                   
                    // gp.getTimeLabel().setVisible(false);

                    //System.out.println("current lives"+ Tools.getInstance().getGameStat().getLives());

                    break;
                }
            }

            // mainp.setBackground(Color.green);
        }
        
        
        else
        if (str.equals("back")) {
            JF.remove(ipp.getHSPanel());
            // JF.add(hp.getHSPanel());
             //JF.add(gp.getMainp());
 
             JF.repaint();
             JF.revalidate();
             
             JF.add(ip.getIntroPanel());
                        
        }
        
        else if (str.equals("Quit"))
            mainFrame.setBackground(Color.blue);
    }

}
