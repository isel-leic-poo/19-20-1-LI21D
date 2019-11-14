import isel.leic.pg.Console;

import java.util.LinkedList;
import java.util.List;

public class EventLoop {

    interface KeyListener {
        void keyPressed(char key);
    }

    interface Predicate {
        boolean evaluate();
    }

    private List<KeyListener> listeners = new LinkedList<>();

    public void registerKeyListener(KeyListener listener) {
        listeners.add(listener);
    }

    private void fireKeyPressed(char key) {
        for (KeyListener listener : listeners) {
            listener.keyPressed(key);
        }
    }

    public void run(Predicate terminationCondition) {
        char key;
        while ((key = Console.waitChar(0)) != ' ' &&
                !terminationCondition.evaluate()) {
            fireKeyPressed(key);
        }
    }
}
