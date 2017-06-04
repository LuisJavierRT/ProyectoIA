package com.example.luisjavier.application_ia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_panel);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String[] panelInfo = intent.getStringArrayExtra("panelInfo");

        TextView panel = (TextView) findViewById(R.id.panelText);
        panel.setText(panelInfo[0]);

        TextView usage = (TextView) findViewById(R.id.usageText);
        usage.setText(panelInfo[1]);

        TextView eGenerated = (TextView) findViewById(R.id.eGeneratedText);
        eGenerated.setText(panelInfo[2]);

        ImageView greenButton = (ImageView) findViewById(R.id.greenButton);
        ImageView redButton = (ImageView) findViewById(R.id.redButton);
        if(panelInfo[3].equals("verde")){
            redButton.setVisibility(View.INVISIBLE);
            greenButton.setVisibility(View.VISIBLE);

        }
        else{

            greenButton.setVisibility(View.INVISIBLE);
            redButton.setVisibility(View.VISIBLE);
        }


    }
}
