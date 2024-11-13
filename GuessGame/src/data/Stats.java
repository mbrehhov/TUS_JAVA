package data;

//this object helps to keep track of state of certain variables during game, like lives, enableQuestions..etc 
// object itelf should be kept within singleton class and called to update stats


public class Stats {
    //score should have coeficent with time , the more time you still have (means quicker answer), the more score..
    private float  score;
    private String currentQuestionAnswer;
    private boolean questionsEnabled;
    private int lives = 10;

    public String getCurrentQuestionAnswer() {
        return currentQuestionAnswer;
    }
    public void setCurrentQuestionAnswer(String currentQuestionAnswer) {
        this.currentQuestionAnswer = currentQuestionAnswer;
    }
    public float getScore() {
        return score;
    }
    public void setScore(float score) {
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
