package com.example.swipecardsdemo;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private ArrayList<String> al;
	private ArrayAdapter<String> arrayAdapter;
	private int i;

	SwipeFlingAdapterView flingContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);

		al = new ArrayList<String>();
		al.add("php");
		al.add("c");
		al.add("python");
		al.add("java");
		al.add("html");
		al.add("c++");
		al.add("css");
		al.add("javascript");

		arrayAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.helloText, al);
		flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
		flingContainer.setAdapter(arrayAdapter);
		flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
			@Override
			public void removeFirstObjectInAdapter() {
				// this is the simplest way to delete an object from the Adapter
				// (/AdapterView)
				Log.d("LIST", "removed object!");
				al.remove(0);
				arrayAdapter.notifyDataSetChanged();
			}

			@Override
			public void onLeftCardExit(Object dataObject) {
				// Do something on the left!
				// You also have access to the original object.
				// If you want to use it just cast it (String) dataObject
				makeToast(MainActivity.this, "Left!");
			}

			@Override
			public void onRightCardExit(Object dataObject) {
				makeToast(MainActivity.this, "Right!");
			}

			@Override
			public void onAdapterAboutToEmpty(int itemsInAdapter) {
				// Ask for more data here
				al.add("XML ".concat(String.valueOf(i)));
				arrayAdapter.notifyDataSetChanged();
				Log.d("LIST", "notified");
				i++;
			}
		});

		// Optionally add an OnItemClickListener
		flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
			@Override
			public void onItemClicked(int itemPosition, Object dataObject) {
				makeToast(MainActivity.this, "Clicked!");
			}
		});

	}

	static void makeToast(Context ctx, String s) {
		Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
	}
	//
	// @OnClick(R.id.right)
	// public void right() {
	// /**
	// * Trigger the right event manually.
	// */
	// flingContainer.getTopCardListener().selectRight();
	// }
	//
	// @OnClick(R.id.left)
	// public void left() {
	// flingContainer.getTopCardListener().selectLeft();
	// }

}
