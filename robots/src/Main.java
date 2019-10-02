import isel.leic.pg.Console;
import model.Game;
import model.Hero;
import model.Villain;

public class Main {

    public static final int LINES = 20;
    public static final int COLS = 40;

    private static boolean moveStuff(GameView gameView, int dx, int dy) {
        gameView.clear();
        gameView.game.hero.moveBy(dx, dy);
        gameView.game.villain.moveTowards(gameView.game.hero.getPosition());
        gameView.draw();
        return gameView.game.isOver();
    }

    public static void main(String[] args) {
        Console.open("Robots", LINES, COLS);

        final GameView gameView = new GameView(new Game(COLS, LINES));
        gameView.draw();

        char key = 0;

        boolean gameOver = false;
        while ((key = Console.waitChar(0)) != ' ' && !gameOver) {
            switch (key) {
                case 'q':
                    gameOver = moveStuff(gameView,-1, -1);
                    break;
                case 'w':
                    gameOver = moveStuff(gameView, 0, -1);
                    break;
                case 'e':
                    gameOver = moveStuff(gameView, 1, -1);
                    break;
                case 'a':
                    gameOver = moveStuff(gameView, -1, 0);
                    break;
                case 'd':
                    gameOver = moveStuff(gameView, 1, 0);
                    break;
                case 'z':
                    gameOver = moveStuff(gameView, -1, 1);
                    break;
                case 'x':
                    gameOver = moveStuff(gameView, 0, 1);
                    break;
                case 'c':
                    gameOver = moveStuff(gameView, 1, 1);
                    break;
            }
        }

        Console.close();
    }
}