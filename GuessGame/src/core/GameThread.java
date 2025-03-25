package core;

import data.Quiz;
import data.Tools;
import java.util.List;
import javax.swing.JLabel;
/**
 * # Timing class Example
 *
 * This is a **childThread** .
 *
 * - counts Time
 * - prints new questions
 */
public class GameThread implements Runnable {
    private volatile boolean looping = true;
    private volatile GameManager cm;
    private volatile boolean newQuestion;
    private volatile boolean wrongAnswer;
    private int time;

    public int getTime() {
        return time;
    }

    public GameThread(GameManager cm) {
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

                    if(Tools.getInstance().getGameStat().isAnswerSubmited())
                    {
                        Tools.getInstance().getGameStat().setAnswerSubmited(false);
                    }

                    //Tools.getInstance().getGameStat().getTimeLabel().setVisible(true);
                    setNewQuestion(false); // reset
                    cm.getQuestionOption(new Quiz());

                }
                if (isWrongAnswer()) {
                    time = 0;
                    if(Tools.getInstance().getGameStat().isAnswerSubmited())
                    {
                        Tools.getInstance().getGameStat().setAnswerSubmited(false);
                    }

                    //Tools.getInstance().getGameStat().getTimeLabel().setVisible(true);

                    setWrongAnswer(false); // reset
                    deductLive();
                    cm.getQuestionOption(new Quiz());

                }
                Thread.sleep(1000);
                time++;
                
                if(Tools.getInstance().getGameStat()!=null && !Tools.getInstance().getGameStat().isAnswerSubmited())
                {
                Tools.getInstance().getGameStat().getTimeLabel().setText(String.valueOf(time));    
                }
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
        List<JLabel> hearts = Tools.getInstance().getGameStat().getHearts();        ;
        
        
        if (currentLives == 0) {

            for (JLabel heart : hearts) {
                if(heart.isVisible())
                {   
                    heart.setVisible(false);
                    break;
                }

            }

            Thread.currentThread().interrupt();
           cm.leaveGameToMainMenu();
        }
        if (currentLives > 0) {

            Tools.getInstance().getGameStat().setLives(currentLives - 1);
            cm.getQuestionOption(new Quiz());

            for (JLabel heart : hearts) {
                if(heart.isVisible())
                {
                    heart.setVisible(false);
                    break;
                }

            }

         
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
