package us.xingkong.xingcard.moudle.contacts;

import android.util.Log;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import us.xingkong.xingcard.XingCardAPP;
import us.xingkong.xingcard.base.BasePresenterImpl;
import us.xingkong.xingcard.base.Constants;
import us.xingkong.xingcard.bean.Contacts;
import us.xingkong.xingcard.data.ContactsData;
import us.xingkong.xingcard.utils.SPUtils;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class ContractsFraPresenter extends BasePresenterImpl implements ContractsFraContract.Presenter {

    private final ContractsFraContract.View mView;

    public ContractsFraPresenter(ContractsFraContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void getContactsList(final String name) {
        ContactsData.getInstance().setGroupName(name);
        ContactsData.getInstance()
                .subscribeData(new Consumer<Contacts>() {
                    @Override
                    public void accept(@NonNull Contacts datas) throws Exception {
                        mView.updateContactsUI(datas);
                        SPUtils.put(XingCardAPP.getAppContext(),
                                Constants.KEY_LOGIN_NAME,
                                name);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.i(Constants.TAG, "fail load data");
                        mView.loadDataFail();
                        clearData();
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void clearData() {
        ContactsData.getInstance().clearMemoryAndDiskCache();
        SPUtils.remove(XingCardAPP.getAppContext(), Constants.KEY_LOGIN_NAME);
    }
}
