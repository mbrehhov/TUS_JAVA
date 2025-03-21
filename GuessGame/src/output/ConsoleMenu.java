package output;

import data.Quiz;
import data.Score;
import data.Stats;
import data.Timing;
import data.Tools;
import interfaces.Imenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class ConsoleMenu {

    StringBuilder listHighScore = null;
    private Score highScore = new Score();
    private String[][] twoDim = null;
    private HashMap<String, String> twoDimValues = new HashMap<String, String>();
    private int rows = 15;
    private int columns = 50;
    private boolean showTitle = false;
    private boolean showScore = true;
    private ArrayList<Imenu> pages = new ArrayList();
    private TitlePage titlePage = new TitlePage();
    private HScorePage hScorePage = new HScorePage(highScore);
    private QuestionPage questionPage = new QuestionPage();
    private EmptyPage emptyPage = new EmptyPage();
                Set<String> a = new HashSet<String>();

    public void execute() {
          

        pages.add(titlePage);
        pages.add(hScorePage);
        pages.add(questionPage);
        pages.add(emptyPage);

        output();

        // Scanner saveInput = null;
        char selection;
        boolean exit = false;
        while (exit != true) {

            try {

                selection = (new String(System.console().readPassword()).toLowerCase()).charAt(0);

                switch (selection) {
                    case 'p' -> {
                    
                        Tools.getInstance().setFilePostfix("python");
                        Tools.getInstance().reAssign();
                        returnCursorOnePostion();

                    }

                    case 'j' -> {
                        Tools.getInstance().setFilePostfix("java");
                        Tools.getInstance().reAssign();
                        returnCursorOnePostion();

                    }
                    case 'm' -> {
                        Tools.getInstance().setFilePostfix("dm");
                        Tools.getInstance().reAssign();
                        returnCursorOnePostion();

                    }
                    case 'e' -> {
                        Tools.getInstance().setFilePostfix("ens");
                        Tools.getInstance().reAssign();
                        returnCursorOnePostion();

                    }

                    case '1' -> {
                        // starting new game
                        Stats stats = Tools.getInstance().getGameStat();

                        if (stats == null || stats.isQuestionsEnabled()) {
                            Tools.getInstance().setGameStat(new Stats());
                            Tools.getInstance().getGameStat().setQuestionsEnabled(false);
                            getQuestionOption(new Quiz());
                            var time = new Timing(this); // class that supports threading
                            Tools.getInstance().getGameStat().setTiming(time);
                            var childThread = new Thread(time); // child thread
                            childThread.start();
                            Tools.getInstance().getGameStat().setChildThread(childThread); // to access eventually

                        }
                    }
                    case '4' -> {
                        showTitle = false;
                        showScore = true;
                        leaveGameToMainMenu();

                    }

                    case '3' -> {
                        exit = true;
                        leaveGameToMainMenu();

                        // on exit we can clear console.
                        // https://stackoverflow.com/questions/10241217/how-to-clear-console-in-java
                        Tools.getInstance().closeAll();
                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                    }
                    case '2' -> {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        if (showScore) {
                            showScore = false;
                            showTitle = true;

                            output(this.getEmptyPage());
                            output(this.gethScorePage());

                        } else if (showTitle) {
                            showScore = true;

                            showTitle = false;
                            output(this.getEmptyPage());
                            output();

                        }
                    }

                    // answers for quiz
                    case 'a', 'b', 'c', 'd' -> {
                        answerToQuestion(selection);
                        returnCursorOnePostion();
                    }

                    default ->
                        returnCursorOnePostion();
                }

            } catch (Exception e) {
                returnCursorOnePostion();
            }
        }

    }

    public void getQuestionOption(Quiz quiz) {
        try {
            String question = quiz.readFile(-1, Tools.getInstance().getJavaQuestions());
            int linenr = Integer.parseInt(question.substring(0, question.indexOf('.'))); // add some validations here later
            String options = quiz.readFile(linenr, Tools.getInstance().getJavaOptions());


            String correctAnswer = quiz.readFile(linenr, Tools.getInstance().getJavaAnswer());
            
            Tools.getInstance().getGameStat().getJTextArea().setText(question);

            splitPopulateOptions(options);

            Tools.getInstance().getGameStat()
                    .setQuestionInProcess(question);
            
            Tools.getInstance().getGameStat()
                    .setCurrentQuestionAnswer(correctAnswer.substring(correctAnswer.length() - 1));
            
            //move(question, options);

        } catch (Exception e) {
            // ignore at the moment

        }
    }

    private void returnCursorOnePostion() {
        System.out.print("\u001B[A"); // we moved next line, bringing coursor back
    }

    public void output() {
        // output(true);

        if (twoDim == null)
            drawArray();
        moveCursorBeginning();

        for (Imenu imenu : pages) {

            if (imenu instanceof TitlePage) {
                imenu.createPage(twoDim);
                drawArray();

            }

        }
    }

    public void output(Imenu page) {
        moveCursorBeginning();

        page.createPage(twoDim);
        drawArray();
    }

    private void drawArray() {

        if (twoDim == null) {
            twoDim = new String[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    twoDim[row][column] = "";
                }

            }
        } else {
            // draw scores

            if (Tools.getInstance().getGameStat() != null && Tools.getInstance().getGameStat().getScore() != 0) {
                twoDim[0][3] = "";
                twoDim[0][3] = "Scores:" + Tools.getInstance().getGameStat().getScore();

            }

            // draw hearts
            if (Tools.getInstance().getGameStat() != null
                    && Tools.getInstance().getGameStat().isQuestionsEnabled() == false) {
                String hearts = "";
                for (int i = 0; i < Tools.getInstance().getGameStat().getLives(); i++) {
                    hearts += "\u2665" + "  ";
                }
                twoDim[1][3] = hearts;
            }
            if (Tools.getInstance().getGameStat() != null) {
                twoDim[2][1] = "Time:";
                twoDim[2][2] = Tools.getInstance().getGameStat().getCurrentTime();
            }

        }

        // final output
        // attempt is to save two dim values into hashmap and check if someting changes
        // in array..
        // and only draw in cases changes are detected.
        // Funcionality is not finished. At the moment it has not achived that goal.
        {
            // String[][] prevstate = Tools.getInstance().getSnapshot();
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {

                    if (twoDimValues.get(String.valueOf(row) + String.valueOf(column)) == null || !twoDimValues
                            .get(String.valueOf(row) + String.valueOf(column)).equals(twoDim[row][column])) {
                        twoDimValues.put((String.valueOf(row) + String.valueOf(column)), twoDim[row][column]);
                        System.out.print(twoDimValues.get(String.valueOf(row) + String.valueOf(column)));

                    } else
                        System.out.print(twoDimValues.get(String.valueOf(row) + String.valueOf(column)));

                }
                // System.out.print("\u001B[A");

                System.out.println("");
            }

        }

    }

    private void move(String... strData) {
        if (strData.length == 1) {
            output(this.getEmptyPage());
            twoDim[3][1] = strData[0];
         
            output(this.getQuestionPage());

        } else {
            output(this.getEmptyPage());

            twoDim[3][1] = strData[0];
            if (strData[1] != null) {
                String[] splittingOptions = strData[1].substring(strData[1].indexOf("#")+1).split("#");
                for (int k = 10; k <= 40; k++) {
                    twoDim[4][k] = "_";

                    twoDim[9][k] = "_";
                }
                a.clear();
                    for (String string : splittingOptions) {
                        a.add(string);
                    }
                    int index = 0;
                    //hasset iteration - gives you a bit randomness
                  
    
                    for (String qo : a) {
                        // Print HashSet data
                        twoDim[5+index][13] = qo;

                        index++;
                    }


/*
                twoDim[5][13] = splittingOptions[1];
                twoDim[6][13] = splittingOptions[2];
                twoDim[7][13] = splittingOptions.length > 3 ? splittingOptions[3] : "";
                twoDim[8][13] = splittingOptions.length > 4 ? splittingOptions[4] : "";
 */
            }

            output(this.getQuestionPage());

        }
    }

    // for reference A cursor up, B down, C and D right/left
    // we are using 15 lines + 1 our input
    synchronized private void moveCursorBeginning() {
        for (int k = 0; k < 15; k++) {
            System.out.print("\u001B[A");
        }
    }

    public void leaveGameToMainMenu() {

        try {

            // if(Tools.getInstance().getGameStat().getScore()!=0)
            highScore.evalueteTop(Tools.getInstance().getGameStat().getScore());
            Tools.getInstance().getGameStat().getTiming().stopLoop();

            Tools.getInstance().getGameStat().getChildThread().join();

        } catch (InterruptedException e) {
            // System.out.println( e);
        }

        Tools.getInstance().getGameStat().setTiming(null);
        Tools.getInstance().getGameStat().setChildThread(null);

        Tools.getInstance().getGameStat().setScore(0);
        Tools.getInstance().setGameStat(null);

        //System.out.print("\033[H\033[2J");
        //System.out.flush();
        // clear page part
       // output(this.getEmptyPage());
       // output();

    }

    public String[][] getTwoDim() {
        return twoDim;
    }

    public HashMap<String, String> getTwoDimValues() {
        return twoDimValues;
    }

    public TitlePage getTitlePage() {
        return titlePage;
    }

    public HScorePage gethScorePage() {
        return hScorePage;
    }

    public QuestionPage getQuestionPage() {
        return questionPage;
    }

    public EmptyPage getEmptyPage() {
        return emptyPage;
    }
    public void newGame(JTextArea questions, Set<JRadioButton> options)
    {
        Stats stats = Tools.getInstance().getGameStat();

                        if (stats == null || stats.isQuestionsEnabled()) {
                            Tools.getInstance().setGameStat(new Stats());
                            Tools.getInstance().getGameStat().setQuestionsEnabled(false);
                            Tools.getInstance().getGameStat().setJTextArea(questions);
                            Tools.getInstance().getGameStat().setOptions(options);
                            getQuestionOption(new Quiz());
                            //questions.setText(Tools.getInstance().getGameStat().getQuestionInProcess()); 

                            var time = new Timing(this); // class that supports threading
                            Tools.getInstance().getGameStat().setTiming(time);
                            var childThread = new Thread(time); // child thread
                            childThread.start();
                            Tools.getInstance().getGameStat().setChildThread(childThread); // to access eventually

                        }
    }
    public void answerToQuestion(char selection) {
        Stats stats = Tools.getInstance().getGameStat();

        if (stats != null || stats.isQuestionsEnabled() == false) {
            if (Tools.getInstance().getGameStat().getCurrentQuestionAnswer()
                    .equalsIgnoreCase(String.valueOf(selection))) {
                Tools.getInstance().getGameStat().setScore(Tools.getInstance().getGameStat().getScore()
                        + (float) Tools.getInstance().getGameStat().getTiming().getTime());
                Tools.getInstance().getGameStat().getTiming().setNewQuestion(true);

            } else {

                Tools.getInstance().getGameStat().getTiming().setWrongAnswer(true);
            }

        }
    }

    private void splitPopulateOptions(String options) {
        String[] splittingOptions = options.substring(options.indexOf("#")+1).split("#");
      
        int counter = 0;
        for (JRadioButton radioBtn : Tools.getInstance().getGameStat().getOptions()) {
            if (!radioBtn.isEnabled()) {
                radioBtn.setEnabled(true);
              }
              radioBtn.setText(null);

            try {
                radioBtn.setText(splittingOptions[counter].substring(1));
                radioBtn.setName(splittingOptions[counter].substring(0,1));


            } catch (Exception e) {
                        //bad approach - change later
                        if (radioBtn.isEnabled()) {
                            radioBtn.setEnabled(false);
                            radioBtn.setName(null);
                          }
                           
            }
            counter++;
        }


    }

}