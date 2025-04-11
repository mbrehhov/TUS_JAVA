package output;

import entry.GameFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class GamePanel {

    JPanel mainp;
    JTextArea jTextAreaQuestions;
    Set<JRadioButton> options;
    List<JLabel> hearts = new ArrayList<>();
    JLabel timeLabel;

    public List<JLabel> getHearts() {
        return hearts;
    }

    public GamePanel(GameFrame mainFrame) {
        // create a main paenl (with grid)
        mainp = new JPanel();
        mainp.setBorder(new TitledBorder("Guessing Game"));
        mainp.setLayout(new GridLayout(3, 0));

        mainp.setBackground(Color.red);

        jTextAreaQuestions = new JTextArea(10, 40); // for question panel

        // JButton button1, button2;
        JButton button2;

        options = new HashSet<>();
        JRadioButton option1 = new JRadioButton("");
        JRadioButton option2 = new JRadioButton("");
        JRadioButton option3 = new JRadioButton("");
        JRadioButton option4 = new JRadioButton("");

        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);

        JPanel panelQuestion = new JPanel();
        panelQuestion.setBorder(new TitledBorder("question"));
        panelQuestion.setLayout(new GridLayout(1, 1));

        panelQuestion.add(jTextAreaQuestions);

        mainp.add(panelQuestion);

        JPanel panelOptions = new JPanel();

        // Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);

        JPanel radioButnPanel = new JPanel();

        radioButnPanel.add(option1);
        radioButnPanel.add(option2);
        radioButnPanel.add(option3);
        radioButnPanel.add(option4);

        radioButnPanel.setLayout(new BoxLayout(radioButnPanel, BoxLayout.Y_AXIS));
        radioButnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);// 0.0

        JPanel panelOptionsR = new JPanel();
        panelOptionsR.setLayout(new BorderLayout());
        panelOptionsR.add(radioButnPanel, BorderLayout.CENTER);
        // panelOptionsR.add(new JButton("Smit"), BorderLayout.SOUTH);

        panelOptions.add(panelOptionsR);
        mainp.add(panelOptions);

        // Register a listener for the radio buttons.
        option1.addActionListener(mainFrame);
        option2.addActionListener(mainFrame);
        option3.addActionListener(mainFrame);
        option4.addActionListener(mainFrame);

        JPanel panel3 = new JPanel();

        timeLabel = new JLabel("", JLabel.RIGHT);
        timeLabel.setVerticalAlignment(JLabel.TOP);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        timeLabel.setPreferredSize(new Dimension(250, 100));
        timeLabel.setForeground(new Color(70, 90, 40));
        timeLabel.setBackground(new Color(100, 20, 70));

        // button1 = new JButton("new");
        button2 = new JButton("Submit");
        // button1.addActionListener(mainFrame);
        button2.addActionListener(mainFrame);

        // panel3.add(button1);
        jTextAreaQuestions.setEditable(false);
        panel3.add(timeLabel);

        panel3.add(button2);

        // setbackground of panel
        panel3.setBackground(Color.BLACK);
        String imgPath = System.getProperty("user.dir") + "/img/Hearth.png";

        for (int i = 0; i < 4; i++) {
            JLabel heart = new JLabel(new ImageIcon(imgPath));
            heart.setVisible(false);
            hearts.add(heart);
            panel3.add(heart);
        }

        mainp.add(panel3);

    }

    public JPanel getMainp() {
        return mainp;
    }

    public JTextArea getjTextAreaQuestions() {
        return jTextAreaQuestions;
    }

    public Set<JRadioButton> getOptions() {
        return options;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

}
