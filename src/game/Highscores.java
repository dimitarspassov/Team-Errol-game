package game;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Highscores {
    public int score;
    public String playerName;

    public Highscores(int score, String playerName) throws IOException {
        this.score = score;
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

//Text file with the top scores (name, score) created
    public void createTextFile() { 
        String outputPath = "/Highscores.txt";

        try (PrintWriter writer = new PrintWriter(outputPath)) {

            String testLine = "Name: Test, Score: 1, Time: 1";

            writer.println(testLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public TreeMap<String, Integer> results;
    public ArrayList<String> sortedResults;

    public int returnMinResult() {

        int resultCurrentUser = score;
        String nameCurrentUser = playerName;
        String inputPath = "/Highscores.txt";
        int minResult = Integer.MAX_VALUE;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputPath))) {

            String line = reader.readLine();
            ArrayList<String> scoresFromFile = new ArrayList<>();

            while (line != null) {

                scoresFromFile.add(line);
                line = reader.readLine();

            }
            results = new TreeMap<>();

            for (String userResult : scoresFromFile) {
                String[] userResultArgs = userResult.split(", ");
                String[] userNameArgs = userResultArgs[0].split(":");
                String userName = userNameArgs[1].trim();
                String[] scoreArgs = userResultArgs[1].split(":");
                int resultUser = Integer.parseInt(scoreArgs[1].trim());

                if (!results.containsKey(userName)) {
                    results.put(userName, resultUser);

                    if (minResult > resultUser) {
                        minResult = resultUser;
                    }
                } else {
                    if (results.get(userName) < resultUser) {
                        results.put(userName, resultUser);
                    }
                    if (minResult > resultUser) {
                        minResult = resultUser;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return minResult;
    }
    //applicable if player's score is higher than the minimal result
    public void insertPlayer(String playerName, int score) {

        if (!results.containsKey(playerName)) {
            results.put(playerName, score);
        } else {
            if (results.get(playerName) < score) {
                results.put(playerName, score);
            }
        }
    }

    // the results are sorted after the new player is added
    public ArrayList<String> sortScores() {

        sortedResults = new ArrayList<>();
        results.entrySet().stream()
                .sorted(Map.Entry.comparingByKey((v1, v2) -> v2.compareTo(v1)))
                .sorted((k1, k2) -> k1.getValue().compareTo(k2.getValue()))
                .limit(10)
                .forEach(pair -> {

                    sortedResults.add("Name: " + pair.getKey() + ", Score: " + pair.getKey() + "\n");

                });
        return sortedResults;

    }
    public void saveFinalResults(){
        String outputPathFinal = "/HighscoresFinal.txt";

        try (PrintWriter writerFinal = new PrintWriter(outputPathFinal)){

            for (String result : sortedResults) {
                writerFinal.println(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeNewScoreTable() throws IOException {
        File f = new File("HighScores.txt");
        File g = new File("temp.txt");
        f.delete();
        g.renameTo(f);
    }

}




   /* public void createTextFile() { //Text file with the top 10 scores (name, score) is created
        String outputPath = "/Highscores.ser";
        String testLine = "Name: Test, Score: 1";

        try (FileOutputStream fos = new FileOutputStream(outputPath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(testLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/




