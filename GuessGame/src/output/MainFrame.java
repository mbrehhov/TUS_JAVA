package output;

import data.Tools;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainFrame extends JPanel implements ActionListener {

    JFrame mainFrame = null;
    ConsoleMenu cm = null;
    GamePanel gp = null;   
    IntroPanel ip = null;

    public void guip() {
        gp = new GamePanel(this); // add to list
        ip = new IntroPanel(this);
        mainFrame = new JFrame();
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);

        mainFrame.setResizable(false);
        //mainFrame.add(gp.getMainp());
        mainFrame.add(ip.getIntroPanel());
        GuessGameWindAdaptor gAdp = new GuessGameWindAdaptor(this);
        mainFrame.addWindowListener(gAdp);
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
            mainFrame.remove(ip.getIntroPanel());
            mainFrame.add(gp.getMainp());
            //change panel
            // start new game
            cm = new ConsoleMenu();
            cm.newGame(gp.getjTextAreaQuestions(), gp.getOptions());

        } else if (str.equals("Submit")) {

            for (JRadioButton jRadioButton : gp.getOptions()) {
                if (jRadioButton.isSelected()) {
                    System.out.println(jRadioButton.getName());
                    System.out.println(Tools.getInstance().getGameStat().getCurrentQuestionAnswer());

                    cm.answerToQuestion(jRadioButton.getName().charAt(0));

                    break;
                }
            }

            // mainp.setBackground(Color.green);
        } else if (str.equals("Quit"))
            mainFrame.setBackground(Color.blue);
    }

}
