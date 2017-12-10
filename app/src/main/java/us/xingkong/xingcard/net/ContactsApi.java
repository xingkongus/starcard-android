package us.xingkong.xingcard.net;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import us.xingkong.xingcard.bean.Contacts;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public interface ContactsApi {
    @FormUrlEncoded
    @POST("index.php/User/loginAPP")
    Observable<Contacts> getDataResults(
            @Field("key") String key,
            @Field("name") String name
    );
}