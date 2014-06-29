package com.rudenkoInc.stringrandomizerupd.app;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ViewActivity extends Activity implements View.OnClickListener{

    public static final String LOG_TAG = "my logs";
    public static final int GRAVITY = 1;
    public static final int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
    public static final int matchParent = LinearLayout.LayoutParams.MATCH_PARENT;

    private static final int idBtnAdd = 1;
    private static final int idBtnRefactor = 1;
    private static final int idBtnDelete = 1;

    private LinearLayout llMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
        Log.d(LOG_TAG, "HELLO IN ViewActivity");

        createContainerTableView();
        createButtons();
    }

    private void createContainerTableView(){

        //initializing main LinearLayout
        llMain = (LinearLayout) findViewById(R.id.llMain);

        String[] containerStrings = new ContainerReader().readFromRandomStringsContainer();

        //creating LayoutParams for Custom LinearLayout
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(matchParent, wrapContent);
        llParams.setMargins(0, 15, 0, 0);

        //creating LayoutParams for Custom TextView
        int weight = 1;
        int size = 40;
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(matchParent, wrapContent, weight);

        //creating LayoutParams for Custom ImageView
        int tvHeight = 50;
        LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(matchParent, tvHeight, weight);

        int counter = 0;

        for(String randomStr: containerStrings){

            //creating StableLinearLayout
            LinearLayout llStable = new LinearLayout(this);
            llStable.setLayoutParams(llParams);

            //creating StableImageView
            ImageView ivStable = new ImageView(this);
            ivStable.setLayoutParams(ivParams);
            Drawable abraham = getResources().getDrawable(R.drawable.abraham);
            ivStable.setImageDrawable(abraham);

            //creating CustomTextView
            TextView tvCustom = new TextView(this);
            tvCustom.setLayoutParams(tvParams);
            tvCustom.setGravity(GRAVITY);
            tvCustom.setTextSize(size);
            tvCustom.setText(randomStr);

            //adding views to the main LinearLayout
            llStable.addView(tvCustom);
            llStable.addView(ivStable);
            llMain.addView(llStable);

            Log.d(LOG_TAG, String.valueOf(counter++));
        }
    }

    private void createButtons(){

        //creating LayoutParams for Buttons LinearLayout

        LinearLayout.LayoutParams llButtonsParams = new LinearLayout.LayoutParams(matchParent, 0, 1);

        //creating Buttons LinearLayout
        LinearLayout llButtons = new LinearLayout(this);
        llButtons.setLayoutParams(llButtonsParams);
        llButtons.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        llButtons.setOrientation(LinearLayout.HORIZONTAL);

        //creating LayoutParams for each Button

        ViewGroup.LayoutParams btnParams = new ViewGroup.LayoutParams(140, wrapContent);
        //creating Buttons
        Button btnAdd = new Button(this);
        btnAdd.setLayoutParams(btnParams);
        btnAdd.setId(idBtnAdd);
        btnAdd.setText("Add");
        llButtons.addView(btnAdd);

        Button btnRefactor = new Button(this);
        btnRefactor.setLayoutParams(btnParams);
        btnAdd.setId(idBtnRefactor);
        btnRefactor.setText("Refactor");
        llButtons.addView(btnRefactor);

        Button btnDelete = new Button(this);
        btnDelete.setLayoutParams(btnParams);
        btnAdd.setId(idBtnDelete);
        btnDelete.setText("Delete");
        llButtons.addView(btnDelete);

        llMain.addView(llButtons);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case idBtnAdd:
                String randStr = new ContainerCreator(MainActivity.STRINGS).createRandomWord();

        }
    }
}
