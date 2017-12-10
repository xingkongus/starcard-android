package us.xingkong.xingcard.moudle.contacts;

import android.util.Log;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import us.xingkong.xingcard.base.BasePresenterImpl;

import us.xingkong.xingcard.bean.Contacts;
import us.xingkong.xingcard.data.ContactsData;
import us.xingkong.xingcard.data.ContactsRepository;

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

        ContactsData.getInstance().setGroupName("xingkongus");
        ContactsData.getInstance()
                .subscribeData(new Consumer<Contacts>() {
                    @Override
                    public void accept(@NonNull Contacts datas) throws Exception {
                        Log.i("hugeterry", datas.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        Log.i("hugeterry", "fail!" +
                                "");
                    }
                });
    }
}
