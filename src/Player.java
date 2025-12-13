import java.io.*;
import java.nio.file.*;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Scanner;


public class Player {
    private String nickname;
    private int bestScore = 999999999;
    int easy = 999999999;
    int medium = 999999999;
    int hard = 999999999;
    int custom = 999999999;
    int win = 0;
    int lose = 0;

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
    public int getEasy(){
        return easy;
    }
    public int getMedium(){
        return medium;
    }
    public int getHard(){
        return hard;
    }
    public int getCustom() {
        return custom;
    }
    public int getWin(){
        return win;
    }
    public int getLose(){
        return lose;
    }

    public void savePlayerScore(int score, int mode) {
        Path path = Paths.get("./" + nickname + ".txt");
        File file = new File(path.toString());

        int bestScore = 999999999;

        try {
            // Jeśli pliku nie ma – utwórz
            if (!file.exists()) {
                Files.createFile(path);
            }

            // Aktualizacja odpowiedniej kategorii
            switch (mode) {
                case 1: // EASY
                    if (score < easy) {
                        easy = score;
                    }
                    break;

                case 2: // MEDIUM
                    if (score < medium) {
                        medium = score;
                    }
                    break;

                case 3: // HARD
                    if (score < hard) {
                        hard = score;
                    }
                    break;
                case 4:
                    if (score < custom) {
                        custom = score;
                    }
                    break;
                case 5:
                    win = win + 1;
                    break;
                case 6:
                    lose = lose + 1;
                    break;

                default:
                    System.out.println("Nieznany tryb gry!");
            }

            // Best score (najlepszy z wszystkich)
            bestScore = Math.min(bestScore, score);

            // Zapis do pliku
            PrintWriter pw = new PrintWriter(file);
            pw.println("nick=" + nickname);
            pw.println("bestScore=" + bestScore);

            pw.println("single");
            pw.println("easy=" + (easy == 999999999 ? 999999999 : easy));
            pw.println("medium=" + (medium == 999999999 ? 999999999 : medium));
            pw.println("hard=" + (hard == 999999999 ? 999999999 : hard));
            pw.println("custom=" + (custom == 999999999 ? 999999999 : custom));

            pw.println("multiplayer");
            pw.println("win=" + win);
            pw.println("lose=" + lose);

            pw.close();

        } catch (IOException e) {
            System.err.println("Błąd: " + e.getMessage());
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
                if (line.startsWith("easy=")) {
                    easy = Integer.parseInt(line.replace("easy=", ""));
                }
                if (line.startsWith("medium=")) {
                    medium = Integer.parseInt(line.replace("medium=", ""));
                }
                if (line.startsWith("hard=")) {
                    hard = Integer.parseInt(line.replace("hard=", ""));
                }
                if (line.startsWith("custom=")) {
                    custom = Integer.parseInt(line.replace("custom", ""));
                }
                if (line.startsWith("win=")) {
                    win = Integer.parseInt(line.replace("win=", ""));
                }
                if (line.startsWith("lose=")) {
                    lose = Integer.parseInt(line.replace("lose=", ""));
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


