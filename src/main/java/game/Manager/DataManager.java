package game.Manager;

import game.Engine.Annotation.GameManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

@GameManager
public class DataManager {
    private Logger logger = LogManager.getLogger(DataManager.class);

    private File scoreFile;

    public DataManager() {
        scoreFile = new File("score.data");
        if (!scoreFile.exists()) {
            try {
                boolean result = scoreFile.createNewFile();
                logger.info("create data file, result={}", result);
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    public int getHighestScore() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(scoreFile)));
            String line = bufferedReader.readLine();
            bufferedReader.close();
            return Integer.valueOf(line);
        } catch (IOException | NumberFormatException ex) {
            logger.error(ex.getMessage());
        }
        return 0;
    }

    public void setHighestScore(int score) {
        try {
            FileWriter fileWriter = new FileWriter(scoreFile, false);
            fileWriter.write(String.valueOf(score));
            fileWriter.close();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
