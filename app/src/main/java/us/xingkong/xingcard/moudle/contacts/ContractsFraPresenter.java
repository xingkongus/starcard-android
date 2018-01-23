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
                        mView.initRecyclerView(datas);
                        SPUtils.put(XingCardAPP.getAppContext(),
                                Constants.KEY_LOGIN_NAME,
                                name);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        Log.i("hugeterry", "fail!");
                    }
                });
    }
}
