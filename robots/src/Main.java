import isel.leic.pg.Console;
import model.Actor;
import model.Game;
import model.Villain;
import view.GameView;

public class Main {

    private static final int LINES = 20;
    private static final int COLS = 40;

    public static void main(String[] args) {

        Console.open("Robots", LINES, COLS);

        final GameView gameView = new GameView(new Game(COLS, LINES));

        char key;

        boolean gameOver = false;
        while ((key = Console.waitChar(0)) != ' ' && !gameOver) {
            switch (key) {
                case 'q':
                    gameOver = gameView.moveStuff(-1, -1);
                    break;
                case 'w':
                    gameOver = gameView.moveStuff( 0, -1);
                    break;
                case 'e':
                    gameOver = gameView.moveStuff(1, -1);
                    break;
                case 'a':
                    gameOver = gameView.moveStuff(-1, 0);
                    break;
                case 'd':
                    gameOver = gameView.moveStuff(1, 0);
                    break;
                case 'z':
                    gameOver = gameView.moveStuff(-1, 1);
                    break;
                case 'x':
                    gameOver = gameView.moveStuff(0, 1);
                    break;
                case 'c':
                    gameOver = gameView.moveStuff(1, 1);
                    break;
            }
        }

        Console.close();
    }
}