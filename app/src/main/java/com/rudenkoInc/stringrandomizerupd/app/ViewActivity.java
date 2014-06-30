package com.rudenkoInc.stringrandomizerupd.app;

import android.app.Activity;
import android.content.Intent;
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
    public static final int TV_WEIGHT = 1;
    public static final int TV_SIZE = 40;

    private static final int idBtnAdd = 1;
    private static final int idBtnRefactor = 2;
    private static final int idBtnDelete = 3;

    private LinearLayout llMain;
    private LinearLayout llComposing;
    private LinearLayout.LayoutParams tvParams;
    private LinearLayout.LayoutParams llParams;
    private LinearLayout.LayoutParams ivParams;

    String[] containerStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
        Log.d(LOG_TAG, "HELLO IN ViewActivity");

        createContainerView();
        createButtons();
    }

    private void createContainerView(){

        //initializing main LinearLayout
        llMain = (LinearLayout) findViewById(R.id.llMain);

        containerStrings = new ContainerReader().readFromRandomStringsContainer();

        //creating LayoutParams for Custom LinearLayout
        llParams = new LinearLayout.LayoutParams(matchParent, wrapContent);
        llParams.setMargins(0, 15, 0, 0);

        //creating LayoutParams for Custom TextView
        tvParams = new LinearLayout.LayoutParams(matchParent, wrapContent, TV_WEIGHT);

        //creating LayoutParams for Custom ImageView
        int tvHeight = 50;
        ivParams = new LinearLayout.LayoutParams(matchParent, tvHeight, TV_WEIGHT);

        //creating LayoutParams for Composing LinearLayout
        LinearLayout.LayoutParams llParamsComposing = new LinearLayout.LayoutParams(matchParent, wrapContent);

        //creating Composing LinearLayout
        llComposing = new LinearLayout(this);
        llComposing.setLayoutParams(llParamsComposing);
        llComposing.setOrientation(LinearLayout.VERTICAL);

        int counter = 0;

        for(String containerStr: containerStrings){

            addLinesToLLComposing(containerStr);
            Log.d(LOG_TAG, "Adding lines to llComposing: " + String.valueOf(counter++) + containerStr);
        }
        llMain.addView(llComposing);
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
        btnAdd.setOnClickListener(this);
        llButtons.addView(btnAdd);

        Button btnRefactor = new Button(this);
        btnRefactor.setLayoutParams(btnParams);
        btnRefactor.setId(idBtnRefactor);
        btnRefactor.setText("Refactor");
        btnRefactor.setOnClickListener(this);
        llButtons.addView(btnRefactor);

        Button btnDelete = new Button(this);
        btnDelete.setLayoutParams(btnParams);
        btnDelete.setId(idBtnDelete);
        btnDelete.setText("Delete");
        btnDelete.setOnClickListener(this);
        llButtons.addView(btnDelete);

        llMain.addView(llButtons);
    }

    private void addLinesToLLComposing(String randomStr){
        String trimmedRandomStr = randomStr.trim();

        if(trimmedRandomStr != null && !trimmedRandomStr.equals("")) {
            Log.d(LOG_TAG, "Inside addLinesToLLComposing(): " + "(" + randomStr + ")");
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
            tvCustom.setTextSize(TV_SIZE);
            tvCustom.setText(randomStr);

            //adding views to the main LinearLayout
            llStable.addView(tvCustom);
            llStable.addView(ivStable);
            llComposing.addView(llStable);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case idBtnAdd:
                String randStr = new FilesCreator(MainActivity.STRINGS).createRandomWord();
                addLinesToLLComposing(randStr);

                break;

            case idBtnDelete:
                Intent intent = new Intent(this, DeleteActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        llMain.removeAllViews();
        createContainerView();
        createButtons();
    }
}
