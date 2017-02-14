package game;


import java.io.*;
import java.util.*;


public class Highscores {


    private TreeMap<String, Integer> results;
    private ArrayList<String> sortedResults;


    public Highscores() {

        this.results = makeNewScoreTable();
        this.sortedResults = sortScores();
    }

    public Integer getMinResult() {

        ArrayList<Integer> minScore = new ArrayList<>();
        results.values().stream().mapToInt(Integer::valueOf).forEach(i -> minScore.add(i));

        if (minScore.isEmpty()) {
            return 0;
        } else {
            return Collections.min(minScore);
        }
    }

    //applicable if player's score is higher than the minimal result
    public void insertPlayer(String playerName, int score) {

        if (results.size() == 10) {
            int minScore = this.getMinResult();
            String playerToRemove = "";
            for (String s : results.keySet()) {

                if (results.get(s) == minScore) {
                    playerToRemove = s;
                }
            }

            results.remove(playerToRemove);
            results.put(playerName, score);
        } else {
            results.put(playerName, score);
        }

        updateSaveData();

    }

    private void updateSaveData() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./save/highscores.ser"))) {

            ScoreTable st = new ScoreTable();
            st.results = results;
            oos.writeObject(st);

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
                    sortedResults.add(pair.getKey() + ":" + pair.getValue());
                });
        return sortedResults;

    }


    public static TreeMap<String, Integer> makeNewScoreTable() {


        File file = new File("./save/highscores.ser");


        TreeMap<String, Integer> result = new TreeMap<>();
        if (!file.exists()) {

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.getCanonicalPath()))) {

                ScoreTable st = new ScoreTable();
                oos.writeObject(st);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {

            try (ObjectInputStream oip = new ObjectInputStream(new FileInputStream(file.getCanonicalPath()))) {

                ScoreTable loadedResults = (ScoreTable) oip.readObject();
                result = loadedResults.results;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        return result;
    }
}





