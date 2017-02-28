package game;


import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {


    private Timer timer;
    private int seconds;

    public GameTimer() {

        timer = new Timer();
    }


    public void initializeTimer() {

        this.seconds = 60;
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                if (seconds == 0) {
                    timer.cancel();
                }
                seconds--;
            }
        };

        this.start(task);
    }

    private void start(TimerTask task) {
        this.timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public int getSeconds() {
        return this.seconds;
    }
}
