package us.xingkong.xingcard.moudle.more;

import us.xingkong.xingcard.XingCardAPP;
import us.xingkong.xingcard.base.BasePresenterImpl;
import us.xingkong.xingcard.base.Constants;
import us.xingkong.xingcard.data.ContactsData;
import us.xingkong.xingcard.utils.SPUtils;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class MoreFraPresenter extends BasePresenterImpl implements MoreFraContract.Presenter {

    private final MoreFraContract.View mView;

    public MoreFraPresenter(MoreFraContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void clearData() {
        ContactsData.getInstance().clearMemoryAndDiskCache();
        SPUtils.remove(XingCardAPP.getAppContext(), Constants.KEY_LOGIN_NAME);
    }
}
