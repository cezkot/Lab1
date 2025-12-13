import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private Player player;
    Player computer = new Player("Komputer");
    List<Player> playerList;
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public Game(Player player) {
        this.player = player;
    }

    public Game(List<Player> playerList){
        this.playerList = playerList;
    }

    public void start(int gameType, int gameDiff) {
        switch (gameType) {
            case 1:
                gameNormal(gameDiff);
                break;
            case 2:
                gameReverse(gameDiff);
                break;
            case 3:
                gameMixed(gameDiff);
                break;
            case 4:
                multiplayerGame(gameDiff);
        }
    }

    //Przyjmuje poziom trudnosci i zwraca liczbe która jest koncem zakresu zgadywania
    private int gameNumberRange(int gameDiff) {
        int numberRange;

        switch (gameDiff) {
            case 1:
                numberRange = 100;
                break;
            case 2:
                numberRange = 10000;
                break;
            case 3:
                numberRange = 1000000;
                break;
            default:
                numberRange = 100;
                break;
        }

        return numberRange;
    }

    //Komputer losuje gracz zgaduje
    private void gameNormal(int gameDiff) {

        int numberRange = gameNumberRange(gameDiff);

        System.out.println("Zgadnij liczbe od 0-" + numberRange);

        int number = random.nextInt(numberRange + 1);
        int guess = -1;
        int attempts = 0;

        while (number != guess) {
            guess = gamePlayerGuessing(number);
            attempts++;
        }

        System.out.println("Udalo ci sie w " + attempts + " podejsciach");
        player.savePlayerScore(attempts,gameDiff);
        System.out.println("Najlepszy wynik: " + player.getBestScore());

    }

    //Gracz losuje komputer zgaduje
    private void gameReverse(int gameDiff) {
        int numberRange = gameNumberRange(gameDiff);
        System.out.println("Podaj liczbe z zakresu 0-" + numberRange);
        int number = -1;

        while (number <= 0 || number >= numberRange) {
            number = scanner.nextInt();
            if (number < 0 || number > numberRange) {
                System.out.println("Podana liczba jest poza zakresem");
            }
        }
        int computerGuees = -1;
        int bound = numberRange;
        int origin = 0;
        int attempts = 0;
        while (computerGuees != number) {
            computerGuees = random.nextInt(origin, bound + 1);
            if (computerGuees > number) {
                bound = computerGuees;
                System.out.println("Komputer wybrał:" + computerGuees + " Jest to za duzo teraz szuka w zakresie: " + origin + "-" + bound);
            } else if (computerGuees < number) {
                origin = computerGuees;
                System.out.println("Komputer wybrał:" + computerGuees + " Jest to za malo teraz szuka w zakresie: " + origin + "-" + bound);
            }
            attempts++;

        }
        System.out.println("Komputer zgadl w " + attempts + " probach");
        computer.savePlayerScore(attempts,gameDiff);
    }

    //Komputer losuje gracz i koputer zgaduja na zmiane
    private void gameMixed(int gameDiff) {
        boolean turn = random.nextBoolean();// True = gracz False = komputer

        int numberRange = gameNumberRange(gameDiff);
        int number = random.nextInt(numberRange + 1);
        int playerGuess;
        int computerGuess;
        int attempsPlayer = 0;
        int attempsComputer = 0;
        int computerBound = numberRange;
        int computerOrigin = 0;
        boolean gameFlag = true;
        while (gameFlag) {
            if (turn) {
                System.out.println("Podaj liczbe");
                playerGuess = gamePlayerGuessing(number);
               if(playerGuess == number)
               {
                   gameFlag = false;
               }
                attempsPlayer++;
                turn = !turn;
            }
            else {
                computerGuess = random.nextInt(computerOrigin, computerBound + 1);
                if (computerGuess > number) {
                    computerBound = computerGuess;
                    System.out.println("Komputer wybrał:" + computerGuess + " Jest to za duzo teraz szuka w zakresie: " + computerOrigin + "-" + computerBound);
                } else if (computerGuess < number) {
                    computerOrigin = computerGuess;
                    System.out.println("Komputer wybrał:" + computerGuess + " Jest to za malo teraz szuka w zakresie: " + computerOrigin + "-" + computerBound);
                }
                else {
                    System.out.println("Komputer zgadł");
                    gameFlag = false;
                }
                attempsComputer++;
                turn = !turn;
            }
        }
        if (!turn){
            System.out.println("Gracz zgadł pierwszy w " + attempsPlayer + " probach");
            player.savePlayerScore(attempsPlayer,4);
            computer.savePlayerScore(attempsComputer,5);
        }else {
            System.out.println("komputer zgadł pierwszy w " + attempsPlayer + " probach");
            computer.savePlayerScore(attempsComputer,4);
            player.savePlayerScore(attempsPlayer,5);


        }
    }

    public void multiplayerGame(int gameDiff) {
        boolean nextGame =true;
                while(nextGame) {
                    int numberRange = gameNumberRange(gameDiff);
                    int number = random.nextInt(numberRange + 1);

                    boolean win = false;
                    while (!win) {
                        for (Player p : playerList) {
                            System.out.println(p.getNickname() + " zgaduje:");
                            int guess = gamePlayerGuessing(number);
                            if (guess == number) {
                                System.out.println("Zwycięzca: " + p.getNickname());

                                win = true;
                                Player winner = p;
                                for (Player playerSave : playerList) {
                                    if (playerSave.equals(winner)) {
                                        playerSave.savePlayerScore(0, 4);
                                    } else {
                                        playerSave.savePlayerScore(0, 5);
                                    }
                                }
                                System.out.println("Kolejna gra ?\n 1.tak\n 2.nie");
                                nextGame = switch (scanner.nextInt()) {
                                    case 1 -> true;
                                    case 2 -> false;
                                    default -> false;
                                };
                                break;
                            }
                        }

                    }
                }
    }

    //Wyodrebniona funkcja która pokazuje czy liczba jest mniejsza czy wieksza itd.
    private int gamePlayerGuessing(int number) {
        System.out.println("Podaj liczbe: ");
        System.out.println(number);
        int guess = scanner.nextInt();

        if (guess > number) {
            System.out.println("Za duzo!");
        } else if (guess < number) {
            System.out.println("Za malo!");
        } else {
            System.out.println("Brawo! Zgadłeś liczbę!");
        }

        return guess;
    }
}
