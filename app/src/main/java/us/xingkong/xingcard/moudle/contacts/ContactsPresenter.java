package us.xingkong.xingcard.moudle.contacts;

import android.util.Log;

import rx.Subscription;
import us.xingkong.xingcard.base.BasePresenterImpl;

import us.xingkong.xingcard.bean.Contacts;
import us.xingkong.xingcard.data.ContactsRepository;
import us.xingkong.xingcard.listener.NetSubscriber;
import us.xingkong.xingcard.utils.RxUtils;

import static us.xingkong.xingcard.base.Constants.CONTACTS_KEY;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public class ContactsPresenter extends BasePresenterImpl implements ContactsContract.Presenter {

    private final ContactsContract.View mView;
    private ContactsRepository mContactsRepository;

    public ContactsPresenter(ContactsContract.View view, ContactsRepository contactsRepository) {
        mView = view;
        this.mView.setPresenter(this);
        mContactsRepository = contactsRepository;
    }


    @Override
    public void getContactsList(String name) {
        Subscription subscription =
                mContactsRepository.getDataResults(CONTACTS_KEY, name)
                        .compose(RxUtils.<Contacts>defaultSchedulers())
                        .subscribe(new NetSubscriber<Contacts>() {
                            @Override
                            public void onSuccess(Contacts data) {
//                                if (page == 1) {
//                                    mView.showRefresh(false);
//                                }
//                                mView.loadSuccess(page);
//                                mView.showRealSList(page, data.getResults());

                                Log.i("hugeterry",data.toString());
                            }

                            @Override
                            public void onFailure(String failMsg) {
//                                if (page == 1) {
//                                    mView.showRefresh(false);
//                                }
//                                mView.loadFailure(page);
//                                mView.showToast(failMsg);

                                Log.i("hugeterry",failMsg);
                            }
                        });
        mSubscriptions.add(subscription);
    }
}
