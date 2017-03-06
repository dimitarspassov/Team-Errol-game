package game;


import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {

    private static long startTime;
    private static long endTime;
    private static long elapsedTime;



    public GameTimer() {

      //this.setStartTime(startTime);
    }
    
    //When the GameTimer is started it gets System.currentTimeMillis() to get the startTime
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    
    //Calculates elapsed time
    public long SetElapsedTime(){
        long elapsedTime = (System.currentTimeMillis() - getStartTime());
        long elapsedSeconds = elapsedTime / 1000;

        long finalMinutes = elapsedSeconds/60;
        long finalSeconds = elapsedSeconds%60;
       /* System.out.println(finalSeconds);
        System.out.println(finalMinutes);*/
        return elapsedSeconds;
    }
    public long GetElapsedTime(){
        return this.elapsedTime;
    }



    public long getFinalMinutes(){
        long finalMinutes = GetElapsedTime()/60;
        return finalMinutes;
    }
    public long getFinalSeconds(){
         long finalSeconds = GetElapsedTime()%60;
        return finalSeconds;
    }

    public void setEndTime(long endTime){
        long endTimeLevel = endTime;

    }
    public long getStartTime(){
        return this.startTime;
    }

    public long getEndTime(){
        return this.endTime;
    }




    /*public void initializeTimer() {
        thread = new Thread();
        this.seconds = 60;

        for (int i = 60; i >= 1; i--) {
            try {
                thread.sleep(1000);
                seconds = i;
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }


        }
}*/
    /*public void initializeTimer() {

        this.seconds = 60;

        this.timer.schedule(
                new TimerTask() {

                    @Override
                    public void run() {
                        if (seconds == 0) {
                            return;
                        }
                        seconds--;
                    }
                }, 1000, 1000);

        //this.start(task);
    }*/

      /*private void start(TimerTask task) {
         this.timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public int getSeconds() {
        return this.seconds;
    }*/
}
