package com.rudenkoInc.stringrandomizerupd.app;


import android.os.Environment;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ContainerReader {

    public static final String LOG_TAG = "my logs";
    public static final String CONTAINER_SEPARATOR = ",";

    protected String[] readFromRandomStringsContainer(){
        String dir = Environment.getExternalStorageDirectory() + File.separator + ContainerCreator.DIR_NAME;
        File container = new File(dir, ContainerCreator.CONTAINER_NAME);

        StringBuilder sb = new StringBuilder();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(container));
            String line;

            while ((line = reader.readLine()) != null){
                sb.append(line);
                Log.d(LOG_TAG, "readerLine: " + line);
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        String line = String.valueOf(sb);
        Log.d(LOG_TAG, line);

        return line.trim().split(CONTAINER_SEPARATOR);
    }
}
