import java.util.Scanner;

void main() {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Podaj nazwe");
    String nickname = scanner.nextLine();

    Player player = new Player(nickname);



    Game game = new Game(player);

    boolean gameFlag = true;

    while (gameFlag) {

        System.out.println("1: Zacznij gre");
        System.out.println("2:Zresetuj wynik");
        System.out.println("3: Wyjscie");
        int GameFlag = scanner.nextInt();

        switch (GameFlag) {
            case 1:
                game.start();
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
