package edu.ucsb.cs.cs185.gaucho.zzhu.tagmyfile;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.nfc.Tag;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.annotations.NonNull;

class TagList {
    Set<String> tagList;
    public TagList(Set<String> tagList){
        this.tagList = tagList;
    }
}

@Entity(primaryKeys = {"name"})
class MyFile implements Parcelable {
    @android.support.annotation.NonNull
    String name;
    String description;
    String path;
    @TypeConverters(Converter.class)
    TagList tagList;
    Integer photoId;

    MyFile(){
        this.name = "";
    }

    MyFile(String name, String description, String path, Set<String> tagList, Integer photoId) {
        this.name = name;
        this.description = description;
        this.path = path;
        this.tagList = new TagList(tagList);
        this.photoId = photoId;
    }

    MyFile(Parcel in) {
        this.tagList =new TagList( new HashSet<>(Arrays.asList(in.createStringArray())));
        this.name = in.readString();
        this.description = in.readString();
        this.path = in.readString();
        this.photoId = in.readInt();
    }

    public static final Creator<MyFile> CREATOR = new Creator<MyFile>() {
        @Override
        public MyFile createFromParcel(Parcel in) {
            return new MyFile(in);
        }

        @Override
        public MyFile[] newArray(int size) {
            return new MyFile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(tagList.tagList.toArray(new String[tagList.tagList.size()]));
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(path);
        parcel.writeInt(photoId);

    }

}
