package output;

import data.Tools;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class MainFrame extends JPanel implements ActionListener {
   
    JTextArea questions = new JTextArea(10, 40);  // for question panel

    JFrame f;
    JButton button1, button2;
    

    Set<JRadioButton> options = new HashSet<>();
    JRadioButton birdButton =  new JRadioButton("");
    JRadioButton catButton = new JRadioButton("");
    JRadioButton dogButton = new JRadioButton("");
    JRadioButton rabbitButton = new JRadioButton("");



 
    JPanel mainp;
    
    public void guip() {
        f = new JFrame("panel");
        f.setSize(800, 600);
        f.setVisible(true);

        options.add(birdButton);
        options.add(catButton);
        options.add(dogButton);
        options.add(rabbitButton);
        

        //create a main paenl (with grid)
        mainp = new JPanel();
        mainp.setBorder(new TitledBorder("Guessing Game"));
        mainp.setLayout(new GridLayout(3,0));


        JPanel panelQuestion = new JPanel();
        panelQuestion.setBorder(new TitledBorder("question"));
        panelQuestion.setLayout(new GridLayout(1,1));

        panelQuestion.add(questions);

        mainp.add(panelQuestion);


        JPanel panelOptions = new JPanel();
        //panelQuestion.setBorder(new TitledBorder("options"));
       // panelQuestion.setLayout(new GridLayout(1,2));


        
       // panelOptions.add( new JList<>(new String[]{option1.getText(),option2.getText(),option3.getText(),option4.getText()}));
       
        birdButton.setMnemonic(KeyEvent.VK_B);
        //birdButton.setActionCommand(option1.getText());
        birdButton.setSelected(true);

        catButton.setMnemonic(KeyEvent.VK_C);
        //catButton.setActionCommand(option2.getText());
        dogButton.setMnemonic(KeyEvent.VK_D);
        //dogButton.setActionCommand(option3.getText());

        rabbitButton.setMnemonic(KeyEvent.VK_R);
        //rabbitButton.setActionCommand(option4.getText());
 
     //Group the radio buttons.
         ButtonGroup group = new ButtonGroup();
         group.add(birdButton);
         group.add(catButton);
         group.add(dogButton);
         group.add(rabbitButton);
       
        JPanel radioButnPanel = new JPanel();

        radioButnPanel.add(birdButton);
        radioButnPanel.add(catButton);
        radioButnPanel.add(dogButton);
        radioButnPanel.add(rabbitButton);
       
        radioButnPanel.setLayout(new BoxLayout(radioButnPanel, BoxLayout.Y_AXIS));
        radioButnPanel.setAlignmentX( Component.LEFT_ALIGNMENT );//0.0
        
        JPanel panelOptionsR = new JPanel();
        panelOptionsR.setLayout(new BorderLayout());
        panelOptionsR.add(radioButnPanel,BorderLayout.CENTER);
        //panelOptionsR.add(new JButton("Smit"), BorderLayout.SOUTH);

        panelOptions.add(panelOptionsR);
        mainp.add(panelOptions);

    //Register a listener for the radio buttons.
    birdButton.addActionListener(this);
    catButton.addActionListener(this);
    dogButton.addActionListener(this);
    rabbitButton.addActionListener(this);


        JPanel panel3  = new JPanel();

        button1 = new JButton("new");
        button2 = new JButton("Submit");
        button1.addActionListener(this);
        button2.addActionListener(this);
        
    
        
        // Creating a panel & add components to panel
        //p = new JPanel();

        panel3.add(button1);
        
        questions.setEditable(false);
        //p.add(questions);


      //  p.add(birdButton);
       // p.add(catButton);
       /// p.add(dogButton);
        //p.add(rabbitButton);
        panel3.add(button2);

        
        // setbackground of panel
        panel3.setBackground(Color.BLACK);

        mainp.add(panel3);

        f.add(mainp);

               GuessGameWindAdaptor gAdp = new GuessGameWindAdaptor(this);
        f.addWindowListener(gAdp);
    }

    // }
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
        // TODO Auto-generated method stub
        String str = e.getActionCommand();

        if (str.equals("new"))
        {


            //start new game
            new ConsoleMenu().newGame(questions,options);
            
            
            mainp.setBackground(Color.red);
        } else if (str.equals("Submit")) {

            
            for (JRadioButton jRadioButton : options) {
                if(jRadioButton.isSelected())
                {
                    System.out.println(jRadioButton.getName());
                    System.out.println(Tools.getInstance().getGameStat().getCurrentQuestionAnswer());
                }
            }

            mainp.setBackground(Color.green);
        } else if (str.equals("Quit"))
        mainp.setBackground(Color.blue);
    }

}
