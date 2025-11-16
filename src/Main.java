import java.util.Scanner;


void main() {

    Scanner scanner = new Scanner(System.in);

//inicjalizacja gracza
    System.out.println("Podaj nazwe");
    String nickname = scanner.nextLine();
    Player player = new Player(nickname);

// Zmienne i obiekty związane z gra
    Game game = new Game(player);
    boolean gameFlag = true;
    int gameType;
    int gameDiff;

    while (gameFlag) {

        System.out.println("1: Zacznij gre");
        System.out.println("2:Zresetuj wynik");
        System.out.println("3: Wyjscie");
        int GameFlag = scanner.nextInt();

        switch (GameFlag) {
            case 1:
                System.out.println("Podaj rodzaj rozgrywki");
                System.out.println("1: normalna");
                System.out.println("2: odwrotna");
                System.out.println("3: mieszana");
                gameType = scanner.nextInt();
                System.out.println("Podaj poziom trudnosci");
                System.out.println("1: 0-100");
                System.out.println("2: 0-10000");
                System.out.println("3: 0-1000000");
                gameDiff = scanner.nextInt();
                game.start(gameType,gameDiff);
                break;
            case 2:
                player.resetPlayerScore();
                break;
            case 3:
                System.out.println("Koniec gry.");
                gameFlag = false;
                break;
            default:
                System.out.println("Zla opcja");
        }
    }


}
