package com.dynamichub.yash.whatsappstatussaver;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class prefconfig {

    private static final String LIST_KEY = "com.dynamichub.yash.whatsappstatussaver";

    public static void writelistinpref(Context context, ArrayList<String> list){

        Gson gson=new Gson();
        String jsonString=gson.toJson(list);

        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(LIST_KEY,jsonString);
        editor.apply();


    }

    public static ArrayList<String> readlistfrompref(Context context){
        SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString= pref.getString(LIST_KEY,"");

        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> list=gson.fromJson(jsonString,type);

        return list;
    }

}
