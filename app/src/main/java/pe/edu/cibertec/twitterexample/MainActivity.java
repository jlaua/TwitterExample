package pe.edu.cibertec.twitterexample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.internal.GuestCallback;

import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity  {

    public static final String CONSUMER_KEY = "nKpYTK5thtOO5pschZzIWNJSQ";
    public static final String CONSUMER_SECRET = "C6U5Cwf8bSOBczJApnB0G9Dz0F2uDBpLswe3zAiDSfVgqhDQOL";
    private static final String TAG = "MainActivity";
    private TwitterLoginButton btnTwitterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(CONSUMER_KEY,CONSUMER_SECRET);
        Fabric.with(this,new TwitterCore(authConfig));
        TwitterLoginButton btnTwitteLoginButton = (TwitterLoginButton) findViewById(R.id.btnTwitterLogin);
        btnTwitteLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d(TAG,"Login sucess .");
                Log.d(TAG,"User Id  " + result.data.getUserId());
                Log.d(TAG,"User name  " + result.data.getUserName());

                TwitterAuthClient authClient = new TwitterAuthClient();
                authClient.requestEmail(result.data, new Callback<String>() {
                    @Override
                    public void success(Result<String> result) {
                        Log.d(TAG, "User mail: " + result.data);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        Log.e(TAG,"Request email error",e);

                    }
                });
            }

            @Override
            public void failure(TwitterException e) {
                Log.e(TAG,"Login Error ",e );

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        btnTwitterLogin.onActivityResult(requestCode, resultCode, data);
    }
}
