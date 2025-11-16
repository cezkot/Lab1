import java.io.*;
import java.nio.file.*;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Scanner;


public class Player {
    private String nickname;
    private int bestScore = 999999999;

    public Player(String nickname) {
        this.nickname = nickname;
        loadPlayerScore();
    }

    public String getNickname() {
        return nickname;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void savePlayerScore(int score) {
        Path path = Paths.get("./" + nickname + ".txt");
        File file = new File(path.toString());
        try {

            if (!file.exists()) {
                Files.createFile(path);
            }

            if (score < bestScore) {
                bestScore = score;
            }

            PrintWriter pw = new PrintWriter(file);
            pw.println("nick=" + nickname);
            pw.println("bestScore=" + bestScore);
            pw.close();

        } catch (FileAlreadyExistsException e) {
            System.err.format("file named %s" + " already exists%n", path);
        } catch (IOException e) {
            System.err.format("createFile error: %s%n", e);
        }

    }

    private void loadPlayerScore() {
        try {
            File file = new File("./" + nickname + ".txt");
            if (!file.exists()) {
                return;
            }

            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();

                if (line.startsWith("bestScore=")) {
                    bestScore = Integer.parseInt(line.replace("bestScore=", ""));
                }
            }

            scan.close();
            System.out.println("Wyniki gracza : " + this.nickname);
            if (bestScore == 999999999 || bestScore == -1) {
                System.out.println("Brak poprzednych wynikow");
            } else {
                System.out.println("Najlepszy wynik: " + getBestScore());
            }
        } catch (Exception e) {
            System.out.println("Błąd odczytu danych gracza!");
        }
    }

    public void resetPlayerScore() {
        Path path = Paths.get("./" + nickname + ".txt");
        File file = new File(path.toString());
        try {

            if (!file.exists()) {
                return;
            } else {

                bestScore = 999999999;
            }

            PrintWriter pw = new PrintWriter(file);
            pw.println("nick=" + nickname);
            pw.println("bestScore=" + bestScore);
            pw.close();


        } catch (IOException e) {
            System.err.format("createFile error: %s%n", e);
        }

    }
}


