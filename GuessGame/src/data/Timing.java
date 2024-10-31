package data;

import output.ConsoleMenu;

public class Timing implements Runnable {
    private volatile boolean looping = true;
    private volatile ConsoleMenu cm;
    private int time;

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
                // when new game was started this class was called and ConsoleMenu initialised
                // into that object time is sending his values.
                if (looping)
                    cm.moveTime(String.valueOf(time));

                if (time >= 15) {
                    time = 0;
                    Tools.getInstance().getGameStat().setLives(Tools.getInstance().getGameStat().getLives()-1);
                    cm.getQuestionOption(new Quiz());
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
