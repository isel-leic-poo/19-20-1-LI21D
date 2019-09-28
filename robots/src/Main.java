import isel.leic.pg.Console;

public class Main {

    private static void moveHero(HeroView view, Hero hero, int dx, int dy) {
        view.clear();
        hero.moveBy(dx, dy);
        view.draw();
    }

    public static void main(String[] args) {
        Console.open("Robots",20,40);
        final Hero hero = new Hero(20, 10);
        final HeroView heroView = new HeroView(hero, Console.RED, '@');

        char key = 0;

        heroView.draw();

        while ((key = Console.waitChar(0)) != ' ') {
            switch (key) {
                case 'q':
                    moveHero(heroView, hero, -1, -1);
                    break;
                case 'w':
                    moveHero(heroView, hero, 0, -1);
                    break;
                case 'e':
                    moveHero(heroView, hero, 1, -1);
                    break;
                case 'a':
                    moveHero(heroView, hero, -1, 0);
                    break;
                case 'd':
                    moveHero(heroView, hero, 1, 0);
                    break;
                case 'z':
                    moveHero(heroView, hero, -1, 1);
                    break;
                case 'x':
                    moveHero(heroView, hero, 0, 1);
                    break;
                case 'c':
                    moveHero(heroView, hero, 1, 1);
                    break;
            }
        }

        Console.close();
    }
}