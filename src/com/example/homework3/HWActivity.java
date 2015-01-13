package com.example.homework3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import android.graphics.PorterDuff;

@SuppressLint("NewApi")
public class HWActivity extends Activity implements OnClickListener {
	public static final int GREEN = 0xff00ff00;
	WebView webView;
	Button site1, site2, site3, share, menu;
	String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hw);
		site1 = (Button) findViewById(R.id.site1);
		site2 = (Button) findViewById(R.id.site2);
		site3 = (Button) findViewById(R.id.site3);
		share = (Button) findViewById(R.id.share);
		menu = (Button) findViewById(R.id.menu);
		
		site1.getBackground().setColorFilter(GREEN,PorterDuff.Mode.MULTIPLY);
		site2.getBackground().setColorFilter(GREEN,PorterDuff.Mode.MULTIPLY);
		site3.getBackground().setColorFilter(GREEN,PorterDuff.Mode.MULTIPLY);
		share.getBackground().setColorFilter(GREEN,PorterDuff.Mode.MULTIPLY);
		menu.getBackground().setColorFilter(GREEN,PorterDuff.Mode.MULTIPLY);

		webView = (WebView) findViewById( R.id.webView ); 
		webView.getSettings().setJavaScriptEnabled(true);   
		webView.getSettings().setSupportZoom(true);      
		webView.getSettings().setBuiltInZoomControls(true); 
		
		site1.setOnClickListener(this);
		site2.setOnClickListener(this);
		site3.setOnClickListener(this);
		share.setOnClickListener(this);
		menu.setOnClickListener(this);		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.activity_hw, menu);
		return true;
	}
	@Override
	public void onClick(View v){
		switch(v.getId()){
		case R.id.site1:
			Toast.makeText(getApplicationContext(), "Site 1", Toast.LENGTH_LONG).show();
			webView.setWebViewClient(yourWebClient);

			url = PreferenceConnector.readString(this,PreferenceConnector.Web1, null).toString();
			if(url==null||url.isEmpty()){
				webView.loadUrl("http://www.google.com/");
			}
			else{
				webView.loadUrl("http://"+url+"/");	
			}
			break;

		case R.id.site2:

			Toast.makeText(getApplicationContext(), "Site 2", Toast.LENGTH_LONG).show();
			webView.setWebViewClient(yourWebClient);
			
			url = PreferenceConnector.readString(this,PreferenceConnector.Web2, null).toString();
			if(url==null||url.isEmpty()){
				webView.loadUrl("https://sites.google.com/a/ucsc.edu/luca/classes/cmps-121-winter-2013-mobile-applications/");	
			}
			else{
				webView.loadUrl("http://"+url+"/");	
			}
			break;
		case R.id.site3:
			Toast.makeText(getApplicationContext(), "Site 3", Toast.LENGTH_LONG).show();
			webView.setWebViewClient(yourWebClient);
			url = PreferenceConnector.readString(this,PreferenceConnector.Web3, null).toString();
			if(url==null||url.isEmpty()){
				webView.loadUrl("http://www.ucsc.edu/");	
			}
			else{
				webView.loadUrl("http://"+url+"/");	
			}
			break;
		case R.id.share:
			try {
				URL url = new URL(webView.getUrl());
				String tmp = url.toString();
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("text/plain");
				i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
				i.putExtra(Intent.EXTRA_TEXT, tmp);
				startActivity(i);

			} catch (MalformedURLException e) {
				Toast.makeText(getApplicationContext(), "Choose a site", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
			break;
		case R.id.menu:
			Intent i = new Intent("com.example.homework3.Menu");
			startActivity(i);
		}

	}
	WebViewClient yourWebClient = new WebViewClient(){
		@Override
		public boolean shouldOverrideUrlLoading(WebView  view, String  url){
			return false;
		}
	};
}
