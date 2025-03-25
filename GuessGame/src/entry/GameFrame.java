package entry;

import core.GameManager;
import data.Score;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import output.GamePanel;
import output.HSPanel;
import output.IntroPanel;

public class GameFrame extends JPanel implements ActionListener {
  
    private GameManager cm = null;
    private JFrame mainFrame = null;
    private GamePanel gp = null;
    private IntroPanel ip = null;
    private HSPanel hsp = null;

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public IntroPanel getIp() {
        return ip;
    }

    public void setIp(IntroPanel ip) {
        this.ip = ip;
    }

    public HSPanel getHsp() {
        return hsp;
    }

    public void setHsp(HSPanel hsp) {
        this.hsp = hsp;
    }

    public void refresh() {
        
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void guip() {
        gp = new GamePanel(this); // add to list
        ip = new IntroPanel(this);
        hsp = new HSPanel(this);

        mainFrame = new JFrame();
        mainFrame.setSize(800, 600);
        mainFrame.setResizable(false);
        mainFrame.add(ip.getIntroPanel());
        
        GuessGameWindAdaptor gAdp = new GuessGameWindAdaptor(this);
        mainFrame.addWindowListener(gAdp);
        mainFrame.setVisible(true);
    }

    class GuessGameWindAdaptor extends WindowAdapter {
        GameFrame f;
        public GuessGameWindAdaptor(GameFrame f) {
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

            mainFrame.repaint();
            mainFrame.revalidate();
            mainFrame.add(gp.getMainp());
            // JF.repaint();
            mainFrame.revalidate();
            cm = new GameManager(this);
            cm.newGame(gp.getjTextAreaQuestions(), gp.getOptions(), gp.getHearts(), gp.getTimeLabel());

        } else if (str.equals("High Score")) {
            mainFrame.remove(ip.getIntroPanel());
            mainFrame.repaint();
            mainFrame.revalidate();
            hsp.setData(new Score().getTopFive());
            mainFrame.add(hsp.getHSPanel());
            mainFrame.revalidate();

        } else if (str.equals("Submit")) {

            for (JRadioButton jRadioButton : gp.getOptions()) {
                if (jRadioButton.isSelected()) {

                    cm.answerToQuestion(jRadioButton.getName().charAt(0));

                    break;
                }
            }

        } else if (str.equals("back")) {
            mainFrame.remove(hsp.getHSPanel());
            mainFrame.repaint();
            mainFrame.revalidate();
            mainFrame.add(ip.getIntroPanel());

        }

        else if (str.equals("Quit"))
            mainFrame.setBackground(Color.blue);
    }
}