package com.rudenkoInc.stringrandomizerupd.app;


import android.os.Environment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FilesCreator {
    public static final int MAX_RANDOM_SIZE = 5;
    public static final int MIN_SIZE = 1;
    public static final String DIR_NAME = "RandomStringsDir2";
    public static final String CONTAINER_NAME = "randomStringsContainer2.txt";
    public static final String CONTAINER_SEPARATOR = System.getProperty("line.separator");

    private String[] strings;

    public FilesCreator(){}

    public FilesCreator(String[] strings){
        this.strings = strings;
    }

    protected void createStringsContainer(){
        String[] randomStrings = createRandomTable();


        try {
            File outputFile = new File(getContainerDirectory(), CONTAINER_NAME);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            for(String randStr: randomStrings){
                String trimmedStr = randStr.trim();
                writer.write(trimmedStr);
                writer.write(CONTAINER_SEPARATOR);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] createRandomTable(){
        Random rand = new Random();

        String[] randomStrings = new String[rand.nextInt(MAX_RANDOM_SIZE) + MIN_SIZE];

        for(int i = 0; i < randomStrings.length; i++){
            randomStrings[i] = strings[rand.nextInt(strings.length)];
        }

        return randomStrings;
    }

    protected String createRandomWord(){

        Random rand = new Random();

        String randomWord = strings[rand.nextInt(strings.length)];
        try {
            File outputFile = new File(getContainerDirectory(), CONTAINER_NAME);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));

            writer.write(randomWord.trim());
            writer.write(CONTAINER_SEPARATOR);

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return randomWord;
    }

    protected File getContainerDirectory(){
        File root = Environment.getExternalStorageDirectory();
        File outDir = new File(root.getAbsolutePath() + File.separator + DIR_NAME);

        if(!outDir.isDirectory()){outDir.mkdir();}
        return outDir;
    }

}
