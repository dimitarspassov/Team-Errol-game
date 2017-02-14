package game;

import java.io.Serializable;
import java.util.TreeMap;

public class ScoreTable implements Serializable {

    TreeMap<String, Integer> results;

    public ScoreTable() {
        this.results = new TreeMap<>();
    }
}
