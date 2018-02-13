package com.example.android.project3_atank2;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//Several Activity and Fragment lifecycle methods are instrumented to emit LogCat output
//so you can follow the class' lifecycle
public class WebsFragment extends Fragment {

	private static final String TAG = "WebFragment";

	private WebView mQuoteView = null;
	private int mCurrIdx = -1;
	private int mQuoteArrLen;
	private String[] links;


	//set the index of teh seleted item
	int getShownIndex() {
		return mCurrIdx;
	}

	// Show the Quote string at position newIndex
	void showQuoteAtIndex(int newIndex) {

		System.out.println("in the links error");
		System.out.println("in the links index " + newIndex);


		if(newIndex>=0)
		{
			mCurrIdx = newIndex;
			System.out.println("in the links " + links[newIndex]);

			mQuoteView.loadUrl(links[newIndex]);
			System.out.println(links[newIndex]);
		}
	}




	@Override
	public void onAttach(Activity activity) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
		super.onCreate(savedInstanceState);
		setRetainInstance(true);

	}

	// Called to create the content view for this Fragment
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

		// Inflate the layout defined in quote_fragment.xml
		// The last parameter is false because the returned view does not need to be attached to the container ViewGroup

		View v = inflater.inflate(R.layout.quote_fragment, container, false);
		links = getResources().getStringArray(R.array.sites);

		return v;
	}

	// Set up some information about the mQuoteView TextView
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
		super.onActivityCreated(savedInstanceState);

		mQuoteView =(WebView)getView().findViewById(R.id.quoteView);
		mQuoteView.getSettings().setJavaScriptEnabled(true);
		mQuoteView.setWebViewClient(new WebViewClient());
		showQuoteAtIndex(mCurrIdx);

	}

	@Override
	public void onStart() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
		super.onResume();
	}


	@Override
	public void onPause() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
		super.onPause();
	}

	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onStop() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
		super.onStop();
	}

	@Override
	public void onDetach() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
		super.onDetach();
	}


	@Override
	public void onDestroy() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
		super.onDestroyView();
	}

}
