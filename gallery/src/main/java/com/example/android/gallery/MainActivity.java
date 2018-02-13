package com.example.android.gallery;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    public static final String galleryIntent = "galleryIntent";
    private static final String MY_PERMISSION ="android.permission.myPermission22";

    //load images
    private ArrayList<Integer> mThumbIdsFlowers = new ArrayList<Integer>(
            Arrays.asList(R.drawable.watertower, R.drawable.milliniumpark,
                    R.drawable.sheddqua, R.drawable.lakeshore, R.drawable.uic,
                    R.drawable.wickr, R.drawable.willis, R.drawable.john));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        IntentFilter bFilter = new IntentFilter(galleryIntent);

        // get views from activity_gallery
        ImageView imgview= (ImageView)findViewById(R.id.image);

        GridView gview=(GridView) findViewById(R.id.gridview);
        int index = this.getIntent().getIntExtra("Position", 0);
        System.out.println("in the mainactivty " + index);

        // check if the app has a selected item,
        // if it does -> open gallery
        // else open a nothing picture
        if(index==0)
        {
            imgview.setImageResource(R.drawable.nothing);

        }
        else
        {
            gview.setAdapter(new ImageAdapter(this, mThumbIdsFlowers));

        }
    }
}


