package us.xingkong.xingcard.data;


import io.reactivex.Observable;
import us.xingkong.xingcard.bean.Contacts;
import us.xingkong.xingcard.net.NetWork;
import us.xingkong.xingcard.net.ContactsApi;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class ContactsRepository implements ContactsSource {
    private final ContactsApi mContactsApi;

    public ContactsRepository() {
        mContactsApi = NetWork.getInstance().getDataService();
    }

    @Override
    public Observable<Contacts> getDataResults(String key, String name) {
        return mContactsApi.getDataResults(key, name);
    }
}
