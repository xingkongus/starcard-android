package us.xingkong.xingcard.moudle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import us.xingkong.xingcard.R;
import us.xingkong.xingcard.XingCardAPP;
import us.xingkong.xingcard.base.Constants;
import us.xingkong.xingcard.data.DataCache;
import us.xingkong.xingcard.moudle.login.LoginActivity;
import us.xingkong.xingcard.moudle.main.MainActivity;
import us.xingkong.xingcard.utils.SPUtils;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final String isLogin = (String) SPUtils.get(XingCardAPP.getAppContext(),
                Constants.KEY_LOGIN_NAME,
                "");
        final boolean hasDataCache = DataCache.getInstance().hasDataCache();
        Log.i("hugeterry", "hasDataCache:" + hasDataCache + " isLogin:" + isLogin+" "+isLogin.length());

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //双重保险
                if (!TextUtils.isEmpty(isLogin) && hasDataCache) {
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                } else {
                    Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            }

        }, 1000);
    }
}
