package com.rudenkoInc.stringrandomizerupd.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ViewActivity extends Activity implements View.OnClickListener{

    public static final int GRAVITY = 1;
    public static final int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
    public static final int matchParent = LinearLayout.LayoutParams.MATCH_PARENT;
    public static final int TV_WEIGHT = 1;
    public static final int TV_SIZE = 40;

    private static final int ID_BTN_ADD = 1;
    private static final int ID_BTN_REFACTOR = 2;
    private static final int ID_BTN_DELETE = 3;
    private static final int REQUEST_CODE_REFACTOR = 1;
    private static final int REQUEST_CODE_DELETE = 2;

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
        btnAdd.setId(ID_BTN_ADD);
        btnAdd.setText("Add");
        btnAdd.setOnClickListener(this);
        llButtons.addView(btnAdd);

        Button btnRefactor = new Button(this);
        btnRefactor.setLayoutParams(btnParams);
        btnRefactor.setId(ID_BTN_REFACTOR);
        btnRefactor.setText("Refactor");
        btnRefactor.setOnClickListener(this);
        llButtons.addView(btnRefactor);

        Button btnDelete = new Button(this);
        btnDelete.setLayoutParams(btnParams);
        btnDelete.setId(ID_BTN_DELETE);
        btnDelete.setText("Delete");
        btnDelete.setOnClickListener(this);
        llButtons.addView(btnDelete);

        llMain.addView(llButtons);
    }

    private void addLinesToLLComposing(String randomStr){
        String trimmedRandomStr = randomStr.trim();

        if(trimmedRandomStr != null && !trimmedRandomStr.equals("")) {
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
            case ID_BTN_ADD:
                String randStr = new FilesCreator(MainActivity.STRINGS).createRandomWord();
                addLinesToLLComposing(randStr);
                break;

            case ID_BTN_DELETE:
                Intent intentDelete = new Intent(this, DeleteActivity.class);
                startActivityForResult(intentDelete, REQUEST_CODE_DELETE);
                break;

            case ID_BTN_REFACTOR:
                Intent intentRefactor = new Intent(this, ActivityRefactor.class);
                startActivityForResult(intentRefactor, REQUEST_CODE_DELETE);
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
