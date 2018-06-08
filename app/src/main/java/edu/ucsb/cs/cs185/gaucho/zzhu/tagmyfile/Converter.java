package edu.ucsb.cs.cs185.gaucho.zzhu.tagmyfile;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Converter {

        @TypeConverter
        public String fromSet(TagList tagList) {
            String ret = "";
            if(tagList.tagList.size()==0)return ret;
            ArrayList<String> tmp = new ArrayList<>(tagList.tagList);
            for(int i = 0; i < tmp.size(); i++)
                ret += tmp.get(i) + ",";
            return ret;
        }
        @TypeConverter
        public TagList toSet(String value) {
            HashSet<String> ret = new HashSet<>();
            if(!value.equals(""))ret.addAll(Arrays.asList(value.split("\\s*,\\s*")));
            return new TagList(ret);
        }
}
