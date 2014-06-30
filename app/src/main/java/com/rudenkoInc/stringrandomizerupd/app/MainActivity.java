package com.rudenkoInc.stringrandomizerupd.app;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import java.io.File;


public class MainActivity extends ActionBarActivity {


    public static final String[] STRINGS = {"one", "two", "three", "four", "five", "six"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Initialize();
    }

    private void Initialize(){
        FilesCreator creator = new FilesCreator(STRINGS);

        if(!isInitialized()){
            creator.createStringsContainer();
            startViewActivity();
        } else
            startViewActivity();
    }

    private boolean isInitialized(){
        File sdDir = Environment.getExternalStorageDirectory();
        File file = new File(sdDir, FilesCreator.DIR_NAME
                + File.separator + FilesCreator.CONTAINER_NAME);

        return file.isFile();
    }

    private void startViewActivity(){
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }


}
