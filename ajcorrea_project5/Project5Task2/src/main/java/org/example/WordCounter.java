package org.example;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Scanner;

public class WordCounter {

    private static void wordCount(String fileName) {

        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("Text Analyst");

        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> inputFile = sparkContext.textFile(fileName);

        //  Task 0: Number of lines in text file
        long numberOfLines = inputFile.count();
        System.out.println("Number of lines: " + numberOfLines);

        JavaRDD<String> wordsFromFile = inputFile.flatMap(content -> Arrays.asList(content.split(" ")));

        // Task 1: Number of words in text file
        //long numberOfWords = wordsFromFile.count();
        //System.out.println("Number of words: " + numberOfWords);

        // Task 1: Number of words in text file
        long numberOfWords = wordsFromFile.filter(word -> !word.isEmpty()).count(); // Filter out empty strings
        System.out.println("Number of words: " + numberOfWords);

        // Task 2: Number of distinct words in text file
        long numberOfDistinctWords = wordsFromFile.map(word -> word.replaceAll("[^a-zA-Z]", ""))
                // Remove non-alphabetic characters
                .filter(word -> !word.isEmpty()) // Filter out empty strings
                .distinct()
                .count();
        System.out.println("Number of distinct words: " + numberOfDistinctWords);

        // Task 3: Number of symbols (i.e., characters) in text file
        long numberOfSymbols = inputFile.mapToPair(content -> new Tuple2<>(content, 1L))
                .reduceByKey((x, y) -> x + y)
                .map(tuple -> tuple._1.length() * tuple._2)
                .reduce((x, y) -> x + y);
        System.out.println("Number of symbols: " + numberOfSymbols);


        // Task 4: Number of distinct symbols (i.e., characters) in text file
        long numberOfDistinctSymbols = inputFile.flatMap(content -> Arrays.asList(content.split("")))
                .distinct()
                .count();
        System.out.println("Number of distinct symbols: " + numberOfDistinctSymbols);

        // Task 5: Number of distinct letters (i.e., alphabets only) in text file
         long numberOfDistinctLetters = inputFile.flatMap(content -> Arrays.asList(content.replaceAll("[^a-zA-Z]",
                         "").split("")))
                .distinct()
                .count();
        System.out.println("Number of distinct letters: " + numberOfDistinctLetters);

        // Task 6: Search for lines containing a specific word
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word to search for: ");
        String searchWord = scanner.next();

        // Filter and print lines containing the word
        System.out.println("Lines containing the word '" + searchWord + "':");
        inputFile.filter(line -> line.contains(searchWord)).foreach(line -> System.out.println(line));


        // Stop the SparkContext
        sparkContext.stop();
    }


    public static void main(String[] args) {

        // String fileName = "ShortTextFile";
        String fileName = "AllsWellThatEndsWell.txt";

        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("Text Analyst");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);


        JavaRDD<String> inputFile = sparkContext.textFile(fileName);

        // Task 0 to Task 6
        wordCount(fileName);

        // Stop the SparkContext
        sparkContext.stop();
    }

}
