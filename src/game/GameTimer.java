package game;

public class GameTimer {

    private long startTime;

    public GameTimer() {

    }

    //When the GameTimer is started it gets System.currentTimeMillis() to get the startTime
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    //Calculates elapsed time
    public long getElapsedTime() {
        long elapsedTime = (System.currentTimeMillis() - this.startTime);
        return elapsedTime / 1000;
    }

}