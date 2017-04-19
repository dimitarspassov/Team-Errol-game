package game;

import display.Display;
import enumerations.State;
import graphics.BackgroundLoader;
import graphics.ImageLoader;
import sound.SoundLoader;
import units.bonuses.Bonus;
import units.bricks.Brick;
import units.bricks.Stone;
import units.platform.SimplePlatform;
import utilities.ScoreCounter;
import utilities.StaticData;
import utilities.UnitLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

//By far the most complex component of our project. This is the game itself.
public class Game extends JFrame implements Runnable {

    private static final byte MAX_LEVEL = 10;

    private String name;
    private int width, height;
    private Display display;
    private static boolean isGamePaused;
    private static boolean isSoundMuted;
    private Brick[] bricks;
    private Stone[] stones;
    private ArrayList<Bonus> bonuses;
    private int bricksRemaining;
    private boolean unitsInitialized;
    private static byte currentLevel = 1;
    private boolean levelSwitched;
    private Highscores highScores;
    private SoundLoader soundLoader;
    private BufferStrategy bs;
    private Graphics graphics;
    private Thread thread;
    private boolean isRunning;
    private GameTimer gameTimer;
    private StringBuilder playerName;
    private Menu menu;
    private State state;
    private boolean isTimerRunning;
    private boolean hasPauseBeenPressed;
    private int bonusPoints;
    private Player player;
    private ScoreCounter scoreCounter;

    public Game(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.state = State.MENU;
        this.scoreCounter = new ScoreCounter();
    }

    private void initialization() {
        this.display = new Display(name, width, height);
        this.addKeyListener(new InputHandler(this.display.getCanvas(), this));
        this.menu = new Menu(this);
        this.addMouseListener(new MouseInput(this.display.getCanvas(), this));
        currentLevel = 1;
        levelSwitched = true;
        this.bricks = new Brick[0];
        this.stones = null;
        this.bonuses = new ArrayList<>();
        playerName = new StringBuilder("");
        highScores = new Highscores();
        this.gameTimer = new GameTimer();
        this.soundLoader = new SoundLoader(this);
        soundLoader.playBackgroundMusic(false);
        this.player = new Player(new SimplePlatform(350, 550, 100, 20, 12),new ScoreCounter());
    }

    private void thick() {

        if (this.state == State.GAME && unitsInitialized) {
            this.player.getPlatform().thick();
            this.player.getBalls().forEach(b -> b.move(this));
            this.player.getBullets().forEach(b -> b.move(this));
        }
    }

    private void render() {

        //This is the buffered strategy. We get it from the canvas. If it is null, we set it with 2 buffers.
        //We can change it later.
        this.bs = this.display.getCanvas().getBufferStrategy();

        if (this.bs == null) {

            this.display.getCanvas().createBufferStrategy(2);
            this.bs = this.display.getCanvas().getBufferStrategy();
        }
        this.graphics = this.bs.getDrawGraphics();

        if (levelSwitched) {
            levelSwitched = false;
            this.bricks = UnitLoader.getBricks(currentLevel);
            this.bricksRemaining = this.bricks.length;
            this.stones = UnitLoader.getStones(currentLevel);
            //this.player = new Player(new SimplePlatform(350, 550, 100, 20, 12), scoreCounter);
            this.player.init(bricks, stones);
            this.player.getScoreCounter().resetLevelScore();
            unitsInitialized = true;
        }

        soundLoader.playBackgroundMusic(isSoundMuted);
        if (this.state == State.GAME) {


            BackgroundLoader.setBackgroundForLevel(currentLevel, graphics);

            //Creating the platform
            this.player.getPlatform().render(graphics);
            UnitLoader.prepareUnitForDrawing(this.graphics, this.player.getPlatform());
            UnitLoader.renderMovableObjects(this.player.getBalls(), graphics);
            UnitLoader.renderMovableObjects(this.player.getBullets(), graphics);

            this.bricksRemaining = this.player.getScoreCounter().getRemainingBricks(bricks, graphics);
            if (stones != null) {
                for (Stone stone : this.stones) {
                    UnitLoader.prepareUnitForDrawing(this.graphics, stone);
                }
            }

            //Bonuses
            if (bonuses != null) {
                UnitLoader.renderBonuses(this.bonuses, this.player.getBalls(), bricks, stones, this.player.getPlatform(), this.graphics, this);
            }

            this.player.getScoreCounter().setLastResult(this.player.getScoreCounter().getScore());
            this.menu.renderWidgets(graphics, gameTimer, this.player.getScoreCounter().getScore(), currentLevel, isSoundMuted);

        } else if (this.state == State.PAUSE) {
            // Draw buttons when user is paused the game
            if (isGamePaused) {
                this.graphics.drawImage(ImageLoader.loadImage(StaticData.BUTTON_RESUME_GAME), 300, 250, 200, 50, null);
                this.graphics.drawImage(ImageLoader.loadImage(StaticData.BUTTON_EXIT), 300, 350, 200, 50, null);
            }
        } else {

            this.graphics.drawImage(ImageLoader.loadImage(StaticData.BACKGROUND_PIC), 0, 0, 800, 600, null);
            this.menu.render(graphics, currentLevel);
        }

        // Whatever we draw, it finally goes through dispose and the it is shown.
        this.graphics.dispose();
        this.bs.show();
    }

    void displayBonusPointsUsingTimer() {
        if (bonusPoints >= 0) {
            if (!isGamePaused) {
                if (!hasPauseBeenPressed) {
                    this.graphics.drawString((String.format("Bonus Points: %d", bonusPoints)), 30, 30);
                } else {
                    if (!isTimerRunning) {
                        gameTimer.startTimer();
                        isTimerRunning = true;
                    }
                    this.graphics.drawString((String.format("Bonus Points: %d", bonusPoints)), 30, 30);
                }

            } else {
                gameTimer.pauseTimer();

                this.graphics.drawString((String.format("Bonus Points: %d",
                        bonusPoints)), 30, 30);

                isTimerRunning = false;
                hasPauseBeenPressed = true;
            }
        }
    }

    @Override
    public void run() {

        // Here we initialize the game loop.
        this.initialization();

        int fps = 60;
        double timePerTick = 1_000_000_000 / fps;

        double delta = 0;

        long lasTimeTicked = System.nanoTime();

        while (isRunning) {

            if (isGamePaused) {
                pause();
            }

            long now = System.nanoTime();

            delta += (now - lasTimeTicked) / timePerTick;
            lasTimeTicked = now;

            if (delta >= 1) {
                thick();
                delta--;

            }
            render();

            if (this.bricksRemaining == 0 && this.state == State.GAME && unitsInitialized) {
                //todo: AVOID DUPLICATED CODE!
                //If a player passes level 1 or level 2 under 1 minute - gets bonus points 60 minus one's points
                //Example - player passes level one for 50 seconds - one gets 60 - 50 = 10 points bonus
                if (currentLevel == 1 || currentLevel == 2) {
                    if (this.gameTimer.getCounter() / 60 < 1) {
                        int bonusPointsFromTimer = 60 - (this.gameTimer.getCounter() % 60);
                        this.player.getScoreCounter().setScore(this.player.getScoreCounter().getScore() + bonusPointsFromTimer);
                    }

                    //Other levels should be passed for less than 2 minutes to get bonus points
                } else {
                    if (this.gameTimer.getCounter() / 60 < 2) {
                        int bonusPointsFromTimer = 60 - (this.gameTimer.getCounter() % 60);
                        this.player.getScoreCounter().setScore(this.player.getScoreCounter().getScore() + bonusPointsFromTimer);
                    }
                }

                currentLevel++;
                levelSwitched = true;
                if (currentLevel > MAX_LEVEL) {
                    this.state = State.WIN;
                    soundLoader.playSound(StaticData.SOUND_LEVEL_COMPLETE);

                } else {
                    soundLoader.playSound(StaticData.SOUND_LEVEL_COMPLETE);
                    this.state = State.PAUSE_BETWEEN_LEVELS;
                    this.initLevel();
                }
            }
            //Bonus clear
            ArrayList<Bonus> newBonuses = new ArrayList<>();
            for (Bonus bonus : this.bonuses) {
                if (bonus.getStatus() && bonus.getY() < 570) {
                    newBonuses.add(bonus);
                }
            }
            this.bonuses = newBonuses;

            if (this.state == State.GAME && unitsInitialized) {

                this.player.removeFallenBalls();

                // Stop the game when all ballsAndBullets exit game field
                if (this.player.getBalls().size() == 0) {

                    this.player.decreaseLives();
                    if (this.player.getLives() == 0) {
                        this.state = State.GAME_OVER;
                        levelSwitched = true;
                        this.initLevel();
                        currentLevel = 1;
                    } else {
                        this.player.init(this.bricks, this.stones);
                        this.pressSpace(false);
                    }
                }
            }
        }

        this.stop();
    }

    private void initLevel() {
        this.player.init(this.bricks, this.stones);
        this.bonuses = new ArrayList<>();
        gameTimer.stopTimer();
        if (isTimerRunning) {
            isTimerRunning = false;
        }
        hasPauseBeenPressed = false;
    }

    private synchronized void pause() {

        while (isGamePaused) {
            render();
            Thread.yield();
        }
    }

    public static boolean isSoundMuted() {
        return isSoundMuted;
    }

    static boolean isPaused() {
        return isGamePaused;
    }

    void turnPauseOnOff(boolean state) {
        isGamePaused = state;

        if (isGamePaused) {
            this.state = State.PAUSE;
        } else {
            this.state = State.GAME;
        }
    }

    static void turnSoundOnOff() {
        isSoundMuted = !isSoundMuted;
    }

    synchronized void start() {

        this.isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {

        try {
            this.isRunning = false;
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    static byte getCurrentLevel() {
        return currentLevel;
    }

    static void setCurrentLevel(byte level) {
        currentLevel = level;
    }

    public State getGameState() {
        return this.state;
    }

    void setState(State state) {
        this.state = state;
    }

    public void playSound(String fileName) {
        this.soundLoader.playSound(fileName);
    }

    void pressSpace(boolean command) {
        this.player.getBalls().forEach(b -> b.pressSpace(command));
        if (!isTimerRunning) {
            gameTimer.startTimer();
            isTimerRunning = true;
        }
    }

    StringBuilder getPlayerName() {
        return this.playerName;
    }

    void pressFire() {
        this.player.fireFromPlatform(this.bricks, this.stones);
    }

    Player getPlayer() {
        return this.player;
    }

    void setLastBonusPoints() {
        this.player.getScoreCounter().setLastBonusPoints(this.bonusPoints);
    }

    public void addBonus(Bonus bonus) {
        this.bonuses.add(bonus);
    }

    void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    Highscores getHighScores() {
        return this.highScores;
    }

    void switchLevel(boolean state) {
        this.levelSwitched = state;
    }
    public void liveUp() {
        this.player.increaseLives();
    }

}