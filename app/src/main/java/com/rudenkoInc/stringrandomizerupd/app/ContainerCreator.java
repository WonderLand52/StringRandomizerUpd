package com.rudenkoInc.stringrandomizerupd.app;


import android.os.Environment;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

public class ContainerCreator {
    public static final String LOG_TAG = "my logs";
    public static final int MAX_RANDOM_SIZE = 5;
    public static final int MIN_SIZE = 1;
    public static final String DIR_NAME = "RandomStringsDir2";
    public static final String CONTAINER_NAME = "randomStringsContainer2.txt";

    private String[] strings;

    public ContainerCreator(String[] strings){
        this.strings = strings;
    }

    protected void createRandomStringsContainer(){
        String [] randomStrings = createRandomTable();

        File root = Environment.getExternalStorageDirectory();
        File outDir = new File(root.getAbsolutePath() + File.separator + DIR_NAME);

        if(!outDir.isDirectory()){outDir.mkdir();}

        try {
            File outputFile = new File(outDir, CONTAINER_NAME);
            Writer writer = new BufferedWriter(new FileWriter(outputFile));

            for(String randStr: randomStrings){
                writer.write(randStr);
                writer.write(", ");
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
            Log.d(LOG_TAG, randomStrings[i]);
        }
        Log.d(LOG_TAG, String.valueOf(randomStrings.length));
        return randomStrings;
    }

    protected String createRandomWord(){
        Random rand = new Random();
        return strings[rand.nextInt(strings.length)];
    }
}
