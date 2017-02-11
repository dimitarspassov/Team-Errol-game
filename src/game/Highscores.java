package game;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Highscores {
    public int score;
    public String playerName;
    public long time; //still not clear how we will calculate time - Timer not ready;

    public Highscores(int score, String playerName, long time) {
        this.score = score;
        this.playerName = playerName;
        this.time = time;

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void createTextFile() { //Text file with the top 10 scores (name, score, time) is created
        String outputPath = "/Highscores.txt";

        try (PrintWriter writer = new PrintWriter(outputPath)) {

            String testLine = "Name: Test, Score: 1, Time: 1";

            writer.println(testLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //After the end of the game the score of the current user is compared to the top 10 scores
    public void getScoresFromFile() {

        int resultCurrentUser = score;
        long timeCurrentUser = time;
        String nameCurrentUser = playerName;
        String inputPath = "/Highscores.txt";

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputPath))) {

            String line = reader.readLine();
            ArrayList<String> scoresFromFile = new ArrayList<>();

            while (line != null) {

                scoresFromFile.add(line);
                line = reader.readLine();

            }
            LinkedHashMap<Integer, TreeMap<String, Long>> results = new LinkedHashMap<>();

            int minResult = Integer.MAX_VALUE;
            for (String userResult : scoresFromFile) {
                String[] userResultArgs = userResult.split(", ");
                String[] userNameArgs = userResultArgs[0].split(":");
                String userName = userNameArgs[1].trim();
                String[] scoreArgs = userResultArgs[1].split(":");
                int resultUser = Integer.parseInt(scoreArgs[1].trim());
                String[] timeArgs = userResultArgs[2].split(":");
                long timeUser = Long.parseLong(timeArgs[1].trim());

                if (!results.containsKey(resultUser)) {
                    results.put(resultUser, new TreeMap<>());

                    if (!results.get(resultUser).containsKey(userName)) {
                        results.get(resultUser).put(userName, timeUser);
                    }
                    if (minResult > resultUser) {
                        minResult = resultUser;
                    }
                } else {
                    if (!results.get(resultUser).containsKey(userName)) {
                        results.get(resultUser).put(userName, timeUser);
                    } else {
                        results.get(resultUser).put(userName, results.get(resultUser).get(userName) + timeUser);
                    }
                    if (minResult > resultUser) {
                        minResult = resultUser;
                    }
                }
            }

            if (score >= minResult || results.size() < 10) {
                if (!results.containsKey(score)) {
                    results.put(score, new TreeMap<>());
                    results.get(score).put(playerName, time);
                } else {
                    if (!results.get(score).containsKey(playerName)) {
                        results.get(score).put(playerName, time);
                    } else {
                        results.get(score).put(playerName, results.get(score).get(playerName) + time);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();

            results.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey((v1, v2) -> v2.compareTo(v1)))
                    .limit(10)
                    .forEach(c -> {
                        TreeMap<String, Long> orderedResults = c.getValue();
                        orderedResults.entrySet().stream()
                                .sorted(Map.Entry.comparingByValue((v1, v2) -> v1.compareTo(v2)))
                                .forEach(pair -> {
                                    sb.append("Name: " + pair.getKey() + ", Score: " + c.getKey() + ", Time: " + pair.getValue());
                                    sb.append("\n");

                                });
                    });


            String outputPath = "/temp.txt"; //The sorted map is saved in temp.txt to get numeration

            try (PrintWriter writer = new PrintWriter(outputPath)) {

                writer.print(sb.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
            //The final scores get numeration and are saved in a fileFinal 
            String inputPathTemp = "/temp.txt";
            String outputPathFinal = "/HighscoresFinal.txt";

            try (BufferedReader readerTemp = Files.newBufferedReader(Paths.get(inputPathTemp));
                 PrintWriter writerFinal = new PrintWriter(outputPathFinal)){

                String lineTemp = readerTemp.readLine();
                int counter = 1;

                while(line != null){
                    writerFinal.println(counter + ". " + lineTemp);

                    lineTemp = readerTemp.readLine();
                    counter++;

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            //The final sorted file replaces the original Highscores file
            File f = new File("/HighScores.txt");
            File g = new File("/HighscoresFinal.txt");
            f.delete();
            g.renameTo(f);

            //Method for printing scores is created
            printScores();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printScores(/*Graphics g*/) throws IOException {

        int h = 100;
        File fileToRead = new File("HighScores.txt");
        LineNumberReader lnr = new LineNumberReader(new FileReader(fileToRead));
        String line = lnr.readLine();
        while (line != null) {
            int rank = lnr.getLineNumber();
            // g.drawString(rank + ". " + line, getWidth()/5, h);
            h += 15;
            line = lnr.readLine();
        }
        lnr.close();
    }

}





