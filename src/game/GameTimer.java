package game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {

    private Timer timer;
    private int counter;
    private long maxCounter;
    private TimerTask timerTask;

    private long startTime;
    private static long tempTime;

    public GameTimer() {
        this.counter = 0;

    }

    public long startTimer(){

        timer = new Timer();
        timerTask = new TimerTask() {

            @Override
            public void run() {
               // System.out.println("Timer: " + counter);
                counter++;

        }
        };

        this.timer.scheduleAtFixedRate(timerTask, 1000, 1000);
        return counter;
    }

    public void pauseTimer() {
        if (timerTask != null) {
           // System.out.println("Timer cancelled");
            timer.cancel();
            timer.purge();
            timerTask.cancel();
            timerTask = null;
            timer = null;
        }
    }

    public void stopTimer() {
        if (timerTask != null) {
            //System.out.println("Timer cancelled");
            timer.cancel();
            timer.purge();
            timerTask.cancel();
            timerTask = null;
            timer = null;
            counter = 0;
        }
    }

    public int getCounter() {
        return this.counter;
    }

}