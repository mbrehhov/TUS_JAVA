package data;

import output.ConsoleMenu;
/**
 * # Timing class Example
 *
 * This is a **childThread** .
 *
 * - counts Time
 * - prints new questions
 */
public class Timing implements Runnable {
    private volatile boolean looping = true;
    private volatile ConsoleMenu cm;
    private volatile boolean newQuestion;
    private volatile boolean wrongAnswer;
    private int time;

    public int getTime() {
        return time;
    }

    public Timing(ConsoleMenu cm) {
        super();
        this.cm = cm;
    }

    public void stopLoop() {
        looping = false;
    }

    public void startLoop() {
        time = 0;
        looping = true;
    }

    @Override
    public void run() {
        try {
            while (looping) {

                // check if new question was requested (ex. user entered correctly answer)
                if (isNewQuestion()) {
                    time = 0;
                    setNewQuestion(false); // reset
                    cm.getQuestionOption(new Quiz());

                }
                if (isWrongAnswer()) {
                    time = 0;
                    setWrongAnswer(false); // reset
                    deductLive();
                    cm.getQuestionOption(new Quiz());

                }
                Thread.sleep(1000);
                time++;
                if (looping)
                    {
                        Tools.getInstance().getGameStat().setCurrentTime(String.valueOf(time));
                        //cm.output(cm.getQuestionPage());
                    }
                    

                if (time >= 14) {
                    time = 0;

                    deductLive();

                }
            }
        } catch (InterruptedException e) {
            cm.leaveGameToMainMenu();
        }

    }

    private void deductLive() {
        var currentLives = Tools.getInstance().getGameStat().getLives();
        if (currentLives == 0) {
            Thread.currentThread().interrupt();
           // cm.leaveGameToMainMenu();
        }
        if (currentLives > 0) {
            Tools.getInstance().getGameStat().setLives(currentLives - 1);
            cm.getQuestionOption(new Quiz());

        }

    }

    public boolean isNewQuestion() {
        return newQuestion;
    }

    public void setNewQuestion(boolean newQuestion) {
        this.newQuestion = newQuestion;
    }

    public boolean isWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(boolean wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }

}
