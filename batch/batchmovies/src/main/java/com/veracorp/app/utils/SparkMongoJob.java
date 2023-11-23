package com.veracorp.app.utils;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


public class SparkMongoJob {
    private String inputUri;
    private String outputUri;

    public SparkMongoJob(String inputUri, String outputUri) {
        this.inputUri = inputUri;
        this.outputUri = outputUri;
    }

    public void run() {
        SparkSession spark = SparkSession.builder()
                .appName("SparkMongoJob")
                .master("local")
                .config("spark.mongodb.input.uri", inputUri)
                .config("spark.mongodb.output.uri", outputUri)
                .getOrCreate();

        Dataset<Row> df = spark.read()
                .format("csv")
                .option("header", "true")
                .load("./data/worldcities.csv");

        df.write()
                .format("mongo")
                .mode("append")
                .save();

        spark.stop();
    }
}