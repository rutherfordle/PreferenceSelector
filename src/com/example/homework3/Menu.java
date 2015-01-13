package com.example.homework3;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Menu extends Activity {
	EditText site1, site2, site3;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		init();
	}
	private void init() {
		site1 = (EditText) findViewById(R.id.site1);
		site2 = (EditText) findViewById(R.id.site2);
	    site3 = (EditText) findViewById(R.id.site3);
		read();
	}

	public void save(View view) {
		String s1 = site1.getText().toString();
		String s2 = site2.getText().toString();
		String s3 = site3.getText().toString();
		
		if (s1 != null)
			PreferenceConnector.writeString(this, PreferenceConnector.Web1,
					s1);
		if (s2 != null)
			PreferenceConnector.writeString(this, PreferenceConnector.Web2,
					s2);
		if (s3 != null)
			PreferenceConnector.writeString(this, PreferenceConnector.Web3,
					s3);
		finish();
	}

	public void reset(View view) {
		PreferenceConnector.getEditor(this).remove(PreferenceConnector.Web1)
				.commit();
		PreferenceConnector.getEditor(this).remove(PreferenceConnector.Web2)
				.commit();
		PreferenceConnector.getEditor(this).remove(PreferenceConnector.Web3)
				.commit();
		read();
	}

	private void read() {
		site1.setText(PreferenceConnector.readString(this,
				PreferenceConnector.Web1, null));
		site2.setText(PreferenceConnector.readString(this,
				PreferenceConnector.Web2, null));
		site3.setText(PreferenceConnector.readString(this,
				PreferenceConnector.Web3, null));
	}
}