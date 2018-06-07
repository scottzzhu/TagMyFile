package edu.ucsb.cs.cs185.gaucho.zzhu.tagmyfile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recView = (RecyclerView) findViewById(R.id.recView);
        final ArrayList<MyFile> files = new ArrayList<>();

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

        Intent intent = getIntent();
        Log.d("restart", (intent!=null)+"");
        if(intent != null) {
            String initSearch = intent.getStringExtra("search");
            if(initSearch!=null)Log.d("inits",initSearch);
            SearchView sv = findViewById(R.id.searchView);
            sv.setQuery(initSearch, false);

            ArrayList<MyFile> useFile = new ArrayList<>();
            if(initSearch!=null) {
                for (int i = 0; i < files.size(); i++) {
                    Set<String> nowSet = files.get(i).tagList;
                    ArrayList<String> now = new ArrayList<>();
                    now.addAll(nowSet);
                    for (int j = 0; j < now.size(); j++) {
                        if (now.get(j).contains(initSearch)) {
                            useFile.add(files.get(i));
                            break;
                        }
                    }
                }
            }
            else {
                useFile=files;
            }
            ViewAdapter adapter = new ViewAdapter(useFile,sv.getContext());
            recView.setAdapter(adapter);

        }

        final SearchView sv = findViewById(R.id.searchView);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                ArrayList<MyFile> useFile = new ArrayList<>();
                if(s!="") {
                    Log.d("query", s);
                    for (int i = 0; i < files.size(); i++) {
                        Set<String> nowSet = files.get(i).tagList;
                        ArrayList<String> now = new ArrayList<>();
                        now.addAll(nowSet);
                        for (int j = 0; j < now.size(); j++) {
                            if (now.get(j).contains(s)) {
                                useFile.add(files.get(i));
                                break;
                            }
                        }
                        Log.d("query", s+" "+i);
                    }
                }
                else {
                    useFile=files;
                }
                ViewAdapter adapter = new ViewAdapter(useFile,sv.getContext());
                recView.setAdapter(adapter);

                return true;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<MyFile> useFile = new ArrayList<>();
                if(s!="") {
                    Log.d("query", s);
                    for (int i = 0; i < files.size(); i++) {
                        Set<String> nowSet = files.get(i).tagList;
                        ArrayList<String> now = new ArrayList<>();
                        now.addAll(nowSet);
                        for (int j = 0; j < now.size(); j++) {
                            if (now.get(j).contains(s)) {
                                useFile.add(files.get(i));
                                break;
                            }
                        }
                        Log.d("query", s+" "+i);
                    }
                }
                else {
                    useFile=files;
                }
                ViewAdapter adapter = new ViewAdapter(useFile,sv.getContext());
                recView.setAdapter(adapter);


                return true;
            }
        });

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
