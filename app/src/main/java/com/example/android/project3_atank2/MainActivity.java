package com.example.android.project3_atank2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.android.project3_atank2.TitlesFragment.ListSelectionListener;


public class MainActivity extends AppCompatActivity implements
        ListSelectionListener {

    int superIndex = -1;
    public static String[] mTitleArray;
    public static String[] mWebArray;

    private static final String PERMISSION = "android.permission.myPermission22";
    private static final String galleryIntent = "galleryIntent";

    boolean access = false;
    private boolean landscapeMode=false;
    private Bundle currentBunddle;

    private static final String WEBFRAGMENT_SHOW = "WEBFRAGMENT_SHOW";
    private static final String TILEFRAGMENT_SHOW = "TILEFRAGMENT_SHOW";

    private WebsFragment mWebFrgment;
    private TitlesFragment mTileFragment;

    private FragmentManager mFragmentManager;
    private FrameLayout mTitleFrameLayout, mWebFrameLayout;

    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("WebsFragment change");
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        currentBunddle=savedInstanceState;
        layout();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#000000"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
       // checkPermission();

    }

    private void layout() {

        // Determine whether the QuoteFragment has been added
        Bundle savedInstanceState = currentBunddle;

        // Get the string arrays with the titles and qutoes
        mTitleArray = getResources().getStringArray(R.array.Titles);
        mWebArray = getResources().getStringArray(R.array.sites);

        //Set content view

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            landscapeMode = true;
            System.out.print("itis in Landscapr mode");
        }
        else
        {
            System.out.print("itis not in Landscapr mode");

        }

        // Get references to the TitleFragment and to the QuotesFragment
        mTitleFrameLayout = (FrameLayout) findViewById(R.id.title_fragment_container);
        mWebFrameLayout = (FrameLayout) findViewById(R.id.quote_fragment_container);

        // Get a reference to the FragmentManager
        mFragmentManager = getFragmentManager();

        if(savedInstanceState==null)
        {
            System.out.println("There is nothing in the instance");
            mWebFrgment= new WebsFragment();
            Bundle bundle = new Bundle();
            bundle.putStringArray("mQuoteArray",mWebArray);
            mWebFrgment.setArguments(bundle);


            mTileFragment=new TitlesFragment();
            bundle = new Bundle();
            bundle.putStringArray("mTitleArray",mTitleArray);
            mTileFragment.setArguments(bundle);

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.title_fragment_container,mTileFragment);
            fragmentTransaction.commit();
        }
        else
        {
            System.out.print("in the esle");
            mTileFragment= (TitlesFragment)mFragmentManager.findFragmentByTag(TILEFRAGMENT_SHOW);
            mWebFrgment=(WebsFragment)mFragmentManager.findFragmentByTag(WEBFRAGMENT_SHOW);

            if(mWebFrgment==null)
            {
                mWebFrgment=new WebsFragment();
                Bundle bundle = new Bundle();
                bundle.putStringArray("mQuoteArray",mWebArray);
                mWebFrgment.setArguments(bundle);
            }


        }

        setLayout();

        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        mFragmentManager
                .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });

    }

    public void setLayout()
    {

        if (!mWebFrgment.isAdded()) {

            // Make the TitleFragment occupy the entire layout
            mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            mWebFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,LayoutParams.MATCH_PARENT));
        } else {

            if(currentBunddle!=null)
            {
                if((WebsFragment)mFragmentManager.findFragmentByTag(WEBFRAGMENT_SHOW) !=null)
                {
                    if(landscapeMode)
                    {
                        System.out.println("LANDSCAPE MODE ON!");
                        // Make the TitleLayout take 1/3 of the layout's width
                        mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                                MATCH_PARENT, 1f));

                        // Make the QuoteLayout take 2/3's of the layout's width
                        mWebFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                                MATCH_PARENT, 2f));

                    }

                }
            }



            if(!landscapeMode)
            {
                // Make the TitleLayout take 1/3 of the layout's width
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        LayoutParams.MATCH_PARENT));
                // Make the QuoteLayout take 2/3's of the layout's width
                mWebFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            }
            else if(landscapeMode)
            {
                System.out.println("LANDSCAPE MODE ON!");
                // Make the TitleLayout take 1/3 of the layout's width
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                // Make the QuoteLayout take 2/3's of the layout's width
                mWebFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));

            }



        }

    }
    // Called when the user selects an item in the TitlesFragment
    @Override
    public void onListSelection(int index) {

        superIndex = index;

        // If the QuoteFragment has not been added, add it now
        if (!mWebFrgment.isAdded()) {

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

            // Add the QuoteFragment to the layout
            fragmentTransaction.add(R.id.quote_fragment_container, mWebFrgment,WEBFRAGMENT_SHOW);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }

        if (mWebFrgment.getShownIndex() != index) {

            // Tell the mWebFrgment to show the quote string at position index
            mWebFrgment.showQuoteAtIndex(index);

        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    public void checkPermission()
    {
        System.out.println("Checking for permision :) ");
        if(ContextCompat.checkSelfPermission(this,PERMISSION)!= PackageManager.PERMISSION_GRANTED)
        {
            System.out.println("Checking fro permission :) ");
            requestPermissions(new String[]{PERMISSION}, 100);
        }
        else
        {
            System.out.println("Permission GRANTED :D");
            showGallery();
        }
    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] grantResults)
    {
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            System.out.println("Permission Resuly Granted");
            showGallery();
        }
        else{
            System.out.println("Permission denied :(");
        }
    }


    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    //options
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exitapp:
                exit();
                return true;
            case R.id.switchapp:
                checkPermission();
                return true;
            default:
                return false;
        }
    }

    /**
     * Switch  app
     */

    //switch appas
    public void showGallery() {

            System.out.println("Switching apps");
            Intent i = new Intent(galleryIntent);
            superIndex=superIndex+1;
            i.putExtra("GalleryIndex", superIndex);
            i.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            sendBroadcast(i,null);

    }

    //exit app and show the homescreen
    public void exit()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}


