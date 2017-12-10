package us.xingkong.xingcard.net;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import us.xingkong.xingcard.base.Constants;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class NetWork {
    private static ContactsApi sContactsApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    private static class ApiClientHolder {
        public static final NetWork INSTANCE = new NetWork();
    }

    public static NetWork getInstance() {
        return ApiClientHolder.INSTANCE;
    }

    public ContactsApi getDataService() {
        if (sContactsApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            sContactsApi = retrofit.create(ContactsApi.class);
        }
        return sContactsApi;
    }

}
