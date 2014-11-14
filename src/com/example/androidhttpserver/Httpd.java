package com.example.androidhttpserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import com.example.androidhttpserver.NanoHTTPD.Method;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Httpd extends Activity {
	
	private WebServer server;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpd);
        server = new WebServer();
        try {
            server.start();
        } catch(IOException ioe) {
            Log.w("Httpd", "The server could not start.");
        }
        Log.w("Httpd", "Web server initialized.");
        
        
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (server != null)
            server.stop();
    }
    private class WebServer extends NanoHTTPD {

        public WebServer()
        {
            super(8080);
        }

        @Override
        public Response serve(IHTTPSession session) {
        	/*Node server take game_id and user_id and do proper response.*/
        	Log.d(session.getMethod().toString(),"http method shown here");
        	Log.d(session.getQueryParameterString(), "get query parameter string ");
        	Log.d(session.getUri(),"so you know client's uri is...");
        	
        
        	for(String str : session.getHeaders().keySet()){
        		Log.d(str ,"tell me headers");
        		Log.d(session.getHeaders().get(str) ,"tell me header value");
        	}
        	
        	//StringBuilder stringBuilder = new StringBuilder();
        
        	for(String str : session.getParms().keySet()){
        		Log.d(str ,"tell me parms");
        		Log.d(session.getParms().get(str) ,"tell me parms value");
        		/* 11142014 @Chris: should implement passing game_id and user_id to 
        		 * file system in execute method before serve*/
        	}
        
            return new NanoHTTPD.Response("");
        }
    }

}
