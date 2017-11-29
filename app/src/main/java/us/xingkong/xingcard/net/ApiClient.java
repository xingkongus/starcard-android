package us.xingkong.xingcard.net;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import us.xingkong.xingcard.XingCardAPP;
import us.xingkong.xingcard.base.Constants;

/**
 * @author hugeterry(http://hugeterry.cn)
 */


public class ApiClient {

    private static OkHttpClient okHttpClient;
    private static ContactsService sContactsService;

    private static class ApiClientHolder {
        public static final ApiClient INSTANCE = new ApiClient();
    }

    public static ApiClient getInstance() {
        return ApiClientHolder.INSTANCE;
    }

    private ApiClient() {
        setOkhttpAndCache();
    }

    private void setOkhttpAndCache() {
//        File httpCacheDirectory = new File(XingCardAPP.getAppContext().getExternalCacheDir().getAbsolutePath(), "responses");
//        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
//        CacheStrategyInterceptor cacheStrategyInterceptor = new CacheStrategyInterceptor();

        okHttpClient = new OkHttpClient
                .Builder()
//                .cache(cache)
//                .addInterceptor(cacheStrategyInterceptor)
//                .addNetworkInterceptor(cacheStrategyInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(7, TimeUnit.SECONDS)
                .build();
    }

    public ContactsService getRealSService() {
        if (sContactsService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            sContactsService = retrofit.create(ContactsService.class);
        }
        return sContactsService;
    }

}
