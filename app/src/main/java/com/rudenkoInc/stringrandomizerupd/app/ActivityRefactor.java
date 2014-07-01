package com.rudenkoInc.stringrandomizerupd.app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ActivityRefactor extends Activity implements View.OnClickListener {

    Button btnReplace;
    EditText et1stWordToReplace;
    EditText et2ndWordToReplace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refactor);

        btnReplace = (Button) findViewById(R.id.btnReplace);
        btnReplace.setOnClickListener(this);

        et1stWordToReplace = (EditText) findViewById(R.id.et1stWordToReplace);
        et2ndWordToReplace = (EditText) findViewById(R.id.et2ndWordToReplace);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnReplace) {

            String strToReplace1;
            String strToReplace2;
            if (et1stWordToReplace.getText() != null && et2ndWordToReplace.getText() != null) {
                strToReplace1 = et1stWordToReplace.getText().toString().trim();
                strToReplace2 = et2ndWordToReplace.getText().toString().trim();
            } else {
                Toast.makeText(this, "Row is empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            String[] arrContainerLines = new ContainerReader().readFromRandomStringsContainer();

            outerLoop:
            for(int i = 0; i < arrContainerLines.length; i++){
                if(arrContainerLines[i].trim().equalsIgnoreCase(strToReplace1)){
                    for(int j = 0; j < arrContainerLines.length; j++){
                        if(arrContainerLines[j].trim().equalsIgnoreCase(strToReplace2)){
                            String tmp = arrContainerLines[i];
                            arrContainerLines[i] = arrContainerLines[j];
                            arrContainerLines[j] = tmp;
                            break outerLoop;
                        }
                    }
                }
            }

            File containerDir = new FilesCreator().getContainerDirectory();
            File container = new File(containerDir, FilesCreator.CONTAINER_NAME);

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(container));

                for(String refactoredStr: arrContainerLines){
                    String trimmedStr = refactoredStr.trim();
                    writer.write(trimmedStr);
                    writer.write(FilesCreator.CONTAINER_SEPARATOR);
                }
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
