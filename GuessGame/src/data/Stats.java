package data;

import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

//this object helps to keep track of state of certain variables during game, like lives, enableQuestions..etc 

// object itelf should be kept within singleton class and called to update stats

public class Stats {
  
   
    private float score;
    private String questionInProcess;
    private Set<JRadioButton> options;
    private List<JLabel> hearts;
    private JLabel timeLabel;
    private boolean answerSubmited; // 
    public String getQuestionInProcess() {
        return questionInProcess;
    }

    public void setQuestionInProcess(String questionInProcess) {
        this.questionInProcess = questionInProcess;
    }
    private JTextArea jTextArea;
    private String currentQuestionAnswer;
    private String currentTime;
    private boolean questionsEnabled;
    private int lives = 3;
    private Thread childThread;
    private Timing timing;

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Timing getTiming() {
        return timing;
    }

    public void setTiming(Timing timing) {
        this.timing = timing;
    }

    public String getCurrentQuestionAnswer() {
        return currentQuestionAnswer;
    }

    public void setCurrentQuestionAnswer(String currentQuestionAnswer) {
        this.currentQuestionAnswer = currentQuestionAnswer;
    }

    public boolean isQuestionsEnabled() {
        return questionsEnabled;
    }

    public void setQuestionsEnabled(boolean questionsEnabled) {
        this.questionsEnabled = questionsEnabled;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Thread getChildThread() {
        return childThread;
    }

    public void setChildThread(Thread childThread) {
        this.childThread = childThread;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public JTextArea getJTextArea() {
        return jTextArea;
    }

    public void setJTextArea(JTextArea jTextArea) {
        this.jTextArea = jTextArea;
    }

    public Set<JRadioButton> getOptions() {
        return options;
    }

    public void setOptions(Set<JRadioButton> options) {
        this.options = options;
    }

    public List<JLabel> getHearts() {
        return hearts;
    }

    public void setHearts(List<JLabel> hearts) {
        this.hearts = hearts;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    public boolean isAnswerSubmited() {
        return answerSubmited;
    }

    public void setAnswerSubmited(boolean answerSubmited) {
        this.answerSubmited = answerSubmited;
    }

}
