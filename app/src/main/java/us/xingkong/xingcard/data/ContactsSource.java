package us.xingkong.xingcard.data;

import io.reactivex.Observable;
import us.xingkong.xingcard.bean.Contacts;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public interface ContactsSource {
    Observable<Contacts> getDataResults(String key, String name);
}
