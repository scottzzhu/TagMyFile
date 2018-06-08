package edu.ucsb.cs.cs185.gaucho.zzhu.tagmyfile;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@android.arch.persistence.room.Dao
public interface Dao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertFile (MyFile file);

    @Delete
    void delete(MyFile file);

    @Query ("SELECT * FROM myfile")
    MyFile[] loadAllFiles();
}
