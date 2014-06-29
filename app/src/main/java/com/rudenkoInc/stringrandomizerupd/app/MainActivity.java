package com.rudenkoInc.stringrandomizerupd.app;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;


public class MainActivity extends ActionBarActivity {

    public static final String LOG_TAG = "my logs";
    public static final String[] STRINGS = {"one", "two", "three", "four", "five", "six"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Initialize();
    }

    private void Initialize(){
        ContainerCreator creator = new ContainerCreator(STRINGS);

        if(!isInitialized()){
            creator.createRandomStringsContainer();
            startViewActivity();
        } else
            startViewActivity();
    }

    private boolean isInitialized(){
        File file = this.getFileStreamPath(ContainerCreator.CONTAINER_NAME);

        return file.exists();
    }

    private void startViewActivity(){
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }


}
