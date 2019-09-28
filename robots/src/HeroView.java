import isel.leic.pg.Console;

/**
 * Represents the hero view in the Robots game.
 */
public class HeroView {

    /**
     * The hero instance
     */
    private final Hero hero;

    /**
     * The color to be used to paint the hero
     */
    private final int color;

    /**
     * The character to be used to paint the hero
     */
    private final char face;

    /**
     * Initiates the view withe the given parameters
     * @param hero  the hero instance
     * @param color the view's color
     * @param face  the view's character
     */
    public HeroView(Hero hero, int color, char face) {
        this.hero = hero;
        this.color = color;
        this.face = face;
    }

    /**
     * Paints the hero at its current position
     */
    public void draw() {
        Console.cursor(hero.getPosition().y, hero.getPosition().x);
        Console.setForeground(color);
        Console.print(face);
    }

    /**
     * Clears the view
     */
    public void clear() {
        Console.cursor(hero.getPosition().y, hero.getPosition().x);
        Console.setForeground(color);
        Console.print(' ');
    }
}
