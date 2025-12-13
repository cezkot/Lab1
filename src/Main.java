import java.util.Scanner;
import java.util.ArrayList;


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
        System.out.println("3: Statystyki");
        System.out.println("4: Wyjscie");
        int GameFlag = scanner.nextInt();

        switch (GameFlag) {
            case 1:
                System.out.println("Podaj rodzaj rozgrywki");
                System.out.println("1: normalna");
                System.out.println("2: odwrotna");
                System.out.println("3: mieszana");
                System.out.println("4: multiplayer");
                gameType = scanner.nextInt();
                System.out.println("Podaj poziom trudnosci");
                System.out.println("1: 0-100");
                System.out.println("2: 0-10000");
                System.out.println("3: 0-1000000");
                System.out.println("4: Wlasny zakres");
                gameDiff = scanner.nextInt();
                if(gameType == 4)
                {
                    List<Player> playerList = new ArrayList<>();
                    System.out.println("Ile ma grać graczy");
                    int playerCount = scanner.nextInt();
                    scanner.nextLine();
                    for(int i = 0; i<playerCount;i++)
                    {
                        System.out.println("Podaj nick: ");
                        String nicknameMulti = scanner.nextLine();
                        playerList.add(new Player(nicknameMulti));
                    }
                    Game gameMulti = new Game(playerList);
                    gameMulti.start(gameType,gameDiff);
                }
                else
                {
                    game.start(gameType, gameDiff);
                }

                break;
            case 2:
                player.resetPlayerScore();
                break;
            case 3:
                System.out.println("Statystyki");
                System.out.println("easy= " + player.getEasy());
                System.out.println("medium= " + player.getMedium());
                System.out.println("hard= " + player.getHard());
                System.out.println("custom= " + player.getCustom());
                System.out.println("multiplayer");
                System.out.println("win= " + player.getWin());
                System.out.println("lose= " + player.getLose());
                break;
            case 4:
                System.out.println("Koniec gry.");
                gameFlag = false;
                break;
            default:
                System.out.println("Zla opcja");
        }
    }


}
