import isel.leic.pg.Console;
import model.Game;
import view.TiledGameView;
import view.GameView;

public class Main {

    private static final int WINDOW_LINES = 30;
    private static final int WINDOW_COLS = 50;

    private static void runWithConsoleView() {
        final Game model = new Game(WINDOW_COLS, WINDOW_LINES);
        final GameView view = new GameView(model);

        char key;
        while ((key = Console.waitChar(0)) != ' ' && !model.isOver()) {
            switch (key) {
                case 'q':
                    view.moveHero(-1, -1);
                    break;
                case 'w':
                    view.moveHero(0, -1);
                    break;
                case 'e':
                    view.moveHero(1, -1);
                    break;
                case 'a':
                    view.moveHero(-1, 0);
                    break;
                case 'd':
                    view.moveHero(1, 0);
                    break;
                case 'z':
                    view.moveHero(-1, 1);
                    break;
                case 'x':
                    view.moveHero(0, 1);
                    break;
                case 'c':
                    view.moveHero(1, 1);
                    break;
            }
        }
    }

    private static void runWithTiledView() {
        final int SIDE = 2;
        final int BOARD_LINES = WINDOW_LINES / SIDE;
        final int BOARD_COLS = WINDOW_COLS / SIDE;

        final Game model = new Game(BOARD_COLS, BOARD_LINES);
        final TiledGameView view = new TiledGameView(model, BOARD_COLS, BOARD_LINES, SIDE);
        char key;

        while ((key = Console.waitChar(0)) != ' ' && !model.isOver()) {
            switch (key) {
                case 'q':
                    model.moveHeroBy(-1, -1);
                    break;
                case 'w':
                    model.moveHeroBy(0, -1);
                    break;
                case 'e':
                    model.moveHeroBy(1, -1);
                    break;
                case 'a':
                    model.moveHeroBy(-1, 0);
                    break;
                case 'd':
                    model.moveHeroBy(1, 0);
                    break;
                case 'z':
                    model.moveHeroBy(-1, 1);
                    break;
                case 'x':
                    model.moveHeroBy(0, 1);
                    break;
                case 'c':
                    model.moveHeroBy(1, 1);
                    break;
            }
        }
    }

    public static void main(String[] args) {

        Console.open("Robots", WINDOW_LINES, WINDOW_COLS);

        if (args.length != 0 && args[0].equals("tiled"))
            runWithTiledView();
        else
            runWithConsoleView();

        Console.close();
    }
}