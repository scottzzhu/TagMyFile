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

    Context context = this;
    final ArrayList<MyFile> files = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recView = (RecyclerView) findViewById(R.id.recView);

        Intent intent = getIntent();

        if(intent.getExtras() == null) {
            AppDatabase.getAppDatabase(this).Dao().insertFile(new MyFile("CS 185 HW1",
                    "Just another homework",
                    "Sample Location",
                    new HashSet<String>(Arrays.asList("pdf", "cs185", "hci", "nofile", "asdhuawdbashdas")),
                    R.drawable.ic_insert_drive_file_black_24dp));
            AppDatabase.getAppDatabase(this).Dao().insertFile(new MyFile("CS 185 HW2",
                    "Just another homework",
                    "Sample Location",
                    new HashSet<String>(Arrays.asList("folder", "cs185", "hci", "nofile")),
                    R.drawable.photo));
        }

        files.addAll(Arrays.asList(AppDatabase.getAppDatabase(this).Dao().loadAllFiles()));

        Log.d("restart", (intent != null) + "");
        if (intent != null) {
            String initSearch = intent.getStringExtra("search");
            if(intent.getExtras() != null) {
                MyFile newFile = intent.getExtras().getParcelable("newFile");
                if(newFile != null)files.add(newFile);
            }
            if (initSearch != null) Log.d("inits", initSearch);
            SearchView sv = findViewById(R.id.searchView);
            sv.setQuery(initSearch, false);

            ArrayList<MyFile> useFile = new ArrayList<>();
            if (initSearch != null) {
                for (int i = 0; i < files.size(); i++) {
                    Set<String> nowSet = files.get(i).tagList.tagList;
                    ArrayList<String> now = new ArrayList<>();
                    now.addAll(nowSet);
                    for (int j = 0; j < now.size(); j++) {
                        if (now.get(j).contains(initSearch)) {
                            useFile.add(files.get(i));
                            break;
                        }
                    }
                }
            } else {
                Log.d("","No Tag");
                useFile = files;
            }
            ViewAdapter adapter = new ViewAdapter(useFile, sv.getContext());
            recView.setAdapter(adapter);
        }

        final SearchView sv = findViewById(R.id.searchView);
        sv.setIconifiedByDefault(false);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ArrayList<MyFile> useFile = new ArrayList<>();
                if (!s.equals("")) {
                    Log.d("query", s);
                    for (int i = 0; i < files.size(); i++) {
                        Set<String> nowSet = files.get(i).tagList.tagList;
                        ArrayList<String> now = new ArrayList<>();
                        now.addAll(nowSet);
                        for (int j = 0; j < now.size(); j++) {
                            if (now.get(j).contains(s)) {
                                useFile.add(files.get(i));
                                break;
                            }
                        }
                        Log.d("query", s + " " + i);
                    }
                } else {
                    useFile = files;
                }
                ViewAdapter adapter = new ViewAdapter(useFile, sv.getContext());
                recView.setAdapter(adapter);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<MyFile> useFile = new ArrayList<>();
                if (!s.equals("")) {
                    Log.d("query", s);
                    for (int i = 0; i < files.size(); i++) {
                        Set<String> nowSet = files.get(i).tagList.tagList;
                        ArrayList<String> now = new ArrayList<>();
                        now.addAll(nowSet);
                        for (int j = 0; j < now.size(); j++) {
                            if (now.get(j).contains(s)) {
                                useFile.add(files.get(i));
                                break;
                            }
                        }
                        Log.d("query", s + " " + i);
                    }
                } else {
                    useFile = files;
                }
                ViewAdapter adapter = new ViewAdapter(useFile, sv.getContext());
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
                Intent intent = new Intent(context,AddActivity.class);
                context.startActivity(intent);
            }
        });
    }

}
