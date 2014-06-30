package com.rudenkoInc.stringrandomizerupd.app;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class DeleteActivity extends Activity implements View.OnClickListener{

    public static final String LOG_TAG = "my logs";

    Button btnDelete;
    EditText etDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        etDelete = (EditText) findViewById(R.id.etDelete);

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnDelete){

            String strToDelete;
            if(etDelete.getText() != null) {
              strToDelete = etDelete.getText().toString().trim();
            } else {
                Toast.makeText(this, "Row is empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            String dir = Environment.getExternalStorageDirectory() + File.separator + FilesCreator.DIR_NAME;
            File container = new File(dir, FilesCreator.CONTAINER_NAME);

            File tempFile = new File(dir, "myTempFile.txt");

            try {
                BufferedReader reader = new BufferedReader(new FileReader(container));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    // trim newline when comparing with lineToDelete
                    String trimmedLine = currentLine.trim();
                    Log.d(LOG_TAG, "Inside OnClickBtnDelete: " + trimmedLine);

                        if (trimmedLine.equals(strToDelete))
                            continue;
                        writer.write(trimmedLine);
                        writer.write(FilesCreator.CONTAINER_SEPARATOR);
                }
                writer.close();
                reader.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }

            boolean successful = tempFile.renameTo(container);
            Log.d(LOG_TAG, "Inside btnDelete. File's renamed successful: " + successful);

            Intent intent = new Intent();
            intent.putExtra("strToDelete", strToDelete);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
