package game;

// Here is the start-point of our project. The launcher does nothing else but start the game.
public class Launcher {

    public static void main(String[] args) {

        Game game = new Game("Brick breaker", 800, 600);
        game.start();
    }
}
