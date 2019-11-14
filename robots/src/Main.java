import isel.leic.pg.Console;
import model.Actor;
import model.Direction;
import model.Game;
import view.GameView;
import view.TiledGameView;

import java.util.HashMap;
import java.util.Map;

import static model.Direction.*;

public class Main {

    private static final int WINDOW_LINES = 30;
    private static final int WINDOW_COLS = 50;

    private static Map<Character, Direction> initializeKeyMap() {
        Map<Character, Direction> keyMap = new HashMap<>();
        keyMap.put('q', NW);
        keyMap.put('w', N);
        keyMap.put('e', NE);
        keyMap.put('a', W);
        keyMap.put('d', E);
        keyMap.put('z', SW);
        keyMap.put('x', S);
        keyMap.put('c', SE);
        return keyMap;
    }

    private static final Map<Character, Direction> keyMap = initializeKeyMap();
    private static final EventLoop eventLoop = new EventLoop();

    private static void runWithConsoleView() {
        final Game model = new Game(WINDOW_COLS, WINDOW_LINES);
        final GameView view = new GameView(model);

        eventLoop.registerKeyListener(key -> {
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
        });

        eventLoop.run(new EventLoop.Predicate() {
            @Override
            public boolean evaluate() { return model.isOver(); }
        });
    }

    private static void runWithTiledView() {
        final int SIDE = 2;
        final int BOARD_LINES = WINDOW_LINES / SIDE;
        final int BOARD_COLS = WINDOW_COLS / SIDE;

        final Game model = new Game(BOARD_COLS, BOARD_LINES);
        new TiledGameView(model, BOARD_COLS, BOARD_LINES, SIDE);

        eventLoop.registerKeyListener(key -> {
            final Direction dir = keyMap.get(key);
            model.moveHeroBy(dir);
        });

        eventLoop.run(new EventLoop.Predicate() {
            @Override
            public boolean evaluate() { return model.isOver(); }
        });
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