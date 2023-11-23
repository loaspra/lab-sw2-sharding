package batchmovies.src.main.java.com.veracorp.app;

import com.veracorp.app.utils.ConfigReader;
import com.veracorp.app.utils.SparkMongoJob;

public class Transformer {
    public static void main(String[] args) {
        try {
            String configFilePath = "./deployment-config.json";
            ConfigReader configReader = new ConfigReader(configFilePath);
            String[] uris = configReader.getUris();

            SparkMongoJob sparkMongoJob = new SparkMongoJob(uris[0], uris[1]);
            sparkMongoJob.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
