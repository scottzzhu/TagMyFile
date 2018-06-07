package edu.ucsb.cs.cs185.gaucho.zzhu.tagmyfile;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recView = (RecyclerView) findViewById(R.id.recView);
        ArrayList<MyFile> files = new ArrayList<>();
        files.add(new MyFile("CS 185 HW1",
                "Just another homework",
                "Sample Location",
                new HashSet<String>(Arrays.asList("pdf", "cs185", "hci", "nofile")),
                R.drawable.ic_insert_drive_file_black_24dp));
        files.add(new MyFile("CS 185 HW2",
                "Just another homework",
                "Sample Location",
                new HashSet<String>(Arrays.asList("folder", "cs185", "hci", "nofile")),
                R.drawable.photo));

        ViewAdapter adapter = new ViewAdapter(files,this);
        recView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(null);
        recView.setLayoutManager(llm);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
