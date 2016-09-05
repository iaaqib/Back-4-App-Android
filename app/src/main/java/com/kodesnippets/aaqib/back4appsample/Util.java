package com.kodesnippets.aaqib.back4appsample;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by silen on 9/4/2016.
 */
public class Util {
    static void showToast(String message, Context context){

        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();

    }

}
