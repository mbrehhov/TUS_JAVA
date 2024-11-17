package data;

import output.ConsoleMenu;

public class Timing implements Runnable {
    private volatile boolean looping = true;
    private volatile ConsoleMenu cm;
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
                Thread.sleep(1000);
                time++;
                if (looping)
                    cm.moveTime(String.valueOf(time));

                if (time >= 8) {
                    time = 0;
                    var currentLives = Tools.getInstance().getGameStat().getLives();
                    if (currentLives == 0)
                {
                    Thread.currentThread().interrupt(); 
                    cm.leaveGameToMainMenu();
                }
                    if (currentLives > 0) {
                        Tools.getInstance().getGameStat().setLives(currentLives - 1);
                        cm.getQuestionOption(new Quiz());
                    }

                }
            }
        } catch (InterruptedException e) {
            cm.leaveGameToMainMenu();
        }

    }

}
