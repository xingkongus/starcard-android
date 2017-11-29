package us.xingkong.xingcard.data;

import rx.Observable;
import us.xingkong.xingcard.bean.Contacts;
import us.xingkong.xingcard.net.ApiClient;
import us.xingkong.xingcard.net.ContactsService;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class ContactsRepository implements ContactsSource {
    private final ContactsService mContactsService;

    public ContactsRepository() {
        mContactsService = ApiClient.getInstance().getRealSService();
    }

    @Override
    public Observable<Contacts> getDataResults(String key, String name) {
        return mContactsService.getDataResults(key, name);
    }
}
