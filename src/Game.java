
import java.util.Random;
import java.util.Scanner;

public class Game {

    private Player player;
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public Game(Player player){
        this.player = player;
    }


    public void start(){
        System.out.println("Zgadnij liczbe od 0 -100");

        int number = random.nextInt(101);
        int guess = -1;
        int attempts = 0;

        while(number != guess)
        {
            System.out.println("Podaj liczbe: ");
            System.out.println(number);
            guess = scanner.nextInt();
            attempts++
            ;
            if (guess > number) {
                System.out.println("Za duzo!");
            } else if (guess < number) {
                System.out.println("Za malo!");
            } else {
                System.out.println("Brawo! Zgadłeś liczbę!");
            }
        }

        System.out.println("Udalo ci sie w " + attempts + " podejsciach");
        player.savePlayerScore(attempts);
        System.out.println("Najlepszy wynik: " + player.getBestScore());
    }
}
