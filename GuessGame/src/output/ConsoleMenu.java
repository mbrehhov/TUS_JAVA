package output;

import data.Quiz;
import data.Score;
import data.Stats;
import data.Timing;
import data.Tools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class ConsoleMenu {

    private Score highScore = new Score();
    private String[][] twoDim = null;
    private HashMap<String, String> twoDimValues = new HashMap<String, String>();
    private int rows = 15;
    private int columns = 50;
    Set<String> a = new HashSet<String>();

    public void execute() {

    }

    public void getQuestionOption(Quiz quiz) {
        try {
            String question = quiz.readFile(-1, Tools.getInstance().getJavaQuestions());
            int linenr = Integer.parseInt(question.substring(0, question.indexOf('.'))); // add some validations here
                                                                                         // later
            String options = quiz.readFile(linenr, Tools.getInstance().getJavaOptions());

            String correctAnswer = quiz.readFile(linenr, Tools.getInstance().getJavaAnswer());

            Tools.getInstance().getGameStat().getJTextArea().setText(question);

            splitPopulateOptions(options);

            Tools.getInstance().getGameStat()
                    .setQuestionInProcess(question);

            Tools.getInstance().getGameStat()
                    .setCurrentQuestionAnswer(correctAnswer.substring(correctAnswer.length() - 1));

            // move(question, options);

        } catch (Exception e) {
            // ignore at the moment

        }
    }

    private void returnCursorOnePostion() {
        System.out.print("\u001B[A"); // we moved next line, bringing coursor back
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
        Float f = Tools.getInstance().getGameStat().getScore();

        Tools.getInstance().getGameStat().setTiming(null);
        Tools.getInstance().getGameStat().setChildThread(null);

        Tools.getInstance().getGameStat().setScore(0);
        Tools.getInstance().setGameStat(null);

        // System.out.print("\033[H\033[2J");
        // System.out.flush();
        // clear page part
        // output(this.getEmptyPage());
        // output();
        MainFrame.JF.remove(MainFrame.GP.getMainp());
        MainFrame.JF.add(MainFrame.IP.getIntroPanel());
        MainFrame.JF.repaint();

        JOptionPane.showMessageDialog(null, "Game Finished, you collected " + f + " points");

    }

    public String[][] getTwoDim() {
        return twoDim;
    }

    public HashMap<String, String> getTwoDimValues() {
        return twoDimValues;
    }

    public void newGame(JTextArea questions, Set<JRadioButton> options, List<JLabel> hearts, JLabel timeLabel) {
        Stats stats = Tools.getInstance().getGameStat();

        if (stats == null || stats.isQuestionsEnabled()) {
            Tools.getInstance().setGameStat(new Stats());
            Tools.getInstance().getGameStat().setHearts(hearts);
            Tools.getInstance().getGameStat().setTimeLabel(timeLabel);
            Tools.getInstance().getGameStat().setQuestionsEnabled(false);
            Tools.getInstance().getGameStat().setJTextArea(questions);
            Tools.getInstance().getGameStat().setOptions(options);
            getQuestionOption(new Quiz());
            // questions.setText(Tools.getInstance().getGameStat().getQuestionInProcess());

            // enable hearts
            for (int i = 0; i <= Tools.getInstance().getGameStat().getLives(); i++) {
                hearts.get(i).setVisible(true);
            }

            var time = new Timing(this); // class that supports threading
            Tools.getInstance().getGameStat().setTiming(time);
            var childThread = new Thread(time); // child thread
            childThread.start();
            Tools.getInstance().getGameStat().setChildThread(childThread); // to access eventually

        }
    }

    public void answerToQuestion(char selection) {
        Stats stats = Tools.getInstance().getGameStat();

        if (stats != null && stats.isQuestionsEnabled() == false) {

            // tmp solution
            Tools.getInstance().getGameStat().setAnswerSubmited(true);
            Tools.getInstance().getGameStat().getTimeLabel().setText("");

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
        String[] splittingOptions = options.substring(options.indexOf("#") + 1).split("#");

        int counter = 0;
        for (JRadioButton radioBtn : Tools.getInstance().getGameStat().getOptions()) {
            if (!radioBtn.isEnabled()) {
                radioBtn.setEnabled(true);
            }
            radioBtn.setText(null);

            try {
                radioBtn.setText(splittingOptions[counter].substring(1));
                radioBtn.setName(splittingOptions[counter].substring(0, 1));

            } catch (Exception e) {
                // bad approach - change later
                if (radioBtn.isEnabled()) {
                    radioBtn.setEnabled(false);
                    radioBtn.setName(null);
                }

            }
            counter++;
        }

    }

}