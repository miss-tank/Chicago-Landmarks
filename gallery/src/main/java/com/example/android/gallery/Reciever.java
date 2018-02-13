package com.example.android.gallery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by MissTank on 10/31/17.
 */

public class Reciever extends BroadcastReceiver
{
    //Receive the broadcast
    @Override
    public void onReceive(Context context, Intent intent) {
        int index = intent.getIntExtra("GalleryIndex",0);
        Intent i = new Intent();
        i.putExtra("Position",index);
        i.setClassName("com.example.android.gallery","com.example.android.gallery.MainActivity");
        context.startActivity(i);
    }
}

