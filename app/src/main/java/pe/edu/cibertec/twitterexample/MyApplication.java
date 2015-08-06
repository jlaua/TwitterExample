package pe.edu.cibertec.twitterexample;

import android.app.Application;
import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

/**
 * Created by ANDROID on 05/08/2015.
 */
public class MyApplication  extends Application{
    public static final String CONSUMER_KEY = "nKpYTK5thtOO5pschZzIWNJSQ";
    public static final String CONSUMER_SECRET = "C6U5Cwf8bSOBczJApnB0G9Dz0F2uDBpLswe3zAiDSfVgqhDQOL";
    private static final String TAG = "MainActivity";
    private TwitterLoginButton btnTwitterLogin;

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(CONSUMER_KEY,CONSUMER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig));
        TwitterLoginButton btnTwitteLoginButton = (TwitterLoginButton) findViewById(R.id.btnTwitterLogin);
        btnTwitteLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d(TAG, "Login sucess");
            }

            @Override
            public void failure(TwitterException e) {
                Log.e(TAG,"Login Error ",e );

            }
        });

        )
    }
}
