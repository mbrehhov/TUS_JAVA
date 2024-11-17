package data;

//this object helps to keep track of state of certain variables during game, like lives, enableQuestions..etc 

// object itelf should be kept within singleton class and called to update stats

public class Stats {
    private float score;
    private String currentQuestionAnswer;
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

}
