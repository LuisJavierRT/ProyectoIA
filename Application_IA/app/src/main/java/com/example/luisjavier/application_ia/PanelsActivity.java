package com.example.luisjavier.application_ia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PanelsActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    private String[] damagedPanels = {
            "Section 1 - Structure 1 - Panel 4",
            "Section 1 - Structure 3 - Panel 2",
            "Section 2 - Structure 2 - Panel 3",
            "Section 3 - Structure 1 - Panel 1",
            "Section 3 - Structure 2 - Panel 4"
    };
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panels);
        getSupportActionBar().hide();
        adapter = new ArrayAdapter<String>(this,R.layout.activity_listview,damagedPanels);
        listView = (ListView) findViewById(R.id.panels_list);
        listView.setAdapter(adapter);
    }
}
