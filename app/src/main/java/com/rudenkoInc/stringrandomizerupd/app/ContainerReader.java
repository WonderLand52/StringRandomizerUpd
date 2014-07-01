package com.rudenkoInc.stringrandomizerupd.app;


import android.os.Environment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ContainerReader {

    protected String[] readFromRandomStringsContainer(){
        String dir = Environment.getExternalStorageDirectory() + File.separator + FilesCreator.DIR_NAME;
        File container = new File(dir, FilesCreator.CONTAINER_NAME);

        StringBuilder sb = new StringBuilder();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(container));
            String line;

            while ((line = reader.readLine()) != null){
                sb.append(line);
                sb.append(", ");
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        String line = String.valueOf(sb);

        String[] arrContainerLines = line.trim().split(",");
        return arrContainerLines;
    }
}
