package data;

//this object helps to keep track of state of certain variables during game, like lives, enableQuestions..etc 
// object itelf should be kept within singleton class and called to update stats


public class Stats {
    private int score;
    //private int questionCount;
    private boolean questionsEnabled;
    private int lives = 10;
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
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

}
