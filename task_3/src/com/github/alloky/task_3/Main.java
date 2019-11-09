package task_3.src.com.github.alloky.task_3;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


public class Main {
    private static Random rndGenerator = new Random(42);
    private static int buffSize = 50 * 1024 * 1024;


    public static void main(String[] args) {
        String filename = "example.txt";
        long fileSize = 1024 * 1024 * 1024L;
        createBigRandomTxtFile(filename, fileSize);
        calculateFrequences(filename);
    }

    private static void calculateFrequences(String pathStr) {
        HashMap freqs = new HashMap<Character, Long>();

        Path path = Paths.get(pathStr);
        if (!Files.exists(path)){
            System.out.println("File doesn't exists");
            return;
        }


        System.out.println("Reading file.");

        byte[] chunk = new byte[buffSize];

        BufferedInputStream reader = null;
        try {
            reader = new BufferedInputStream(new FileInputStream(new File(pathStr)), buffSize);
            long readedSize = 0;
            while ((readedSize = reader.read(chunk)) != -1) {
                for (byte currByte : chunk) {
                    Character c = (char) currByte;
                    Long cFreq = (Long) freqs.get(c);
                    if (cFreq == null) {
                        freqs.put(c, 1L);
                    } else {
                        freqs.put(c, (Long)cFreq + 1L);
                    }
                }
            }

            System.out.println("Reading succeed");

            System.out.println();
            for (Object o: freqs.keySet()) {
                Character c = (Character) o;
                Long freq = (Long) freqs.get(o);
                System.out.println(c + " : " + freq);
            }
            System.out.println();


        } catch (IOException ex) {
            System.out.println("Error while reading file. Exit.");
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (Exception ex) {}
        }
    }

    private static String createBigRandomString(int size){
        var intStream = rndGenerator.ints(size, 0, 10);
        return intStream.mapToObj(Integer::toString).collect(Collectors.joining(""));
    }

    private static void createBigRandomTxtFile(String pathStr, long size){
        Path path = Paths.get(pathStr);
        if (Files.exists(path)){
            System.out.println("File already exists");
            return;
        }

        var answer = askYesOrNo("!!! Warning. Do you really want to create 2GB file with random numbers? (yes/no)");
        if (!answer){
            System.out.println("Exiting.");
            System.exit(0);
        }


        System.out.println("Creating file, " + Long.toString(size) + " kb");

        BufferedOutputStream writer = null;
        try {
            writer = new BufferedOutputStream(new FileOutputStream(new File(pathStr), true), buffSize);

            for (long i = 0; i <= (size) / (buffSize); ++i){
                writer.write(createBigRandomString(buffSize).getBytes());
            }

        } catch (IOException ex) {
            System.out.println("Error while creating random file. Exit.");
            System.exit(-1);
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (Exception ex) {}
        }

        System.out.println("File created succeed.");
    }

    public static boolean askYesOrNo(String query) {
        String yesOrNo = null;
        boolean validInput = false;
        while (!validInput) {
            System.out.println(query);

            yesOrNo = getConsoleInput();
            validInput = yesOrNo.toLowerCase().equals("yes")
                    || yesOrNo.toLowerCase().equals("n");
            if (!validInput) {
                System.out
                        .println("Invalid input! Please enter /'yes/' or /'n/'");
            }
        }
        return yesOrNo.toUpperCase().equals("Y");
    }

    public static String getConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
