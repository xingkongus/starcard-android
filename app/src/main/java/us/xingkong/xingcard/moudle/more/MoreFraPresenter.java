package us.xingkong.xingcard.moudle.more;

import us.xingkong.xingcard.base.BasePresenterImpl;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class MoreFraPresenter extends BasePresenterImpl implements MoreFraContract.Presenter {

    private final MoreFraContract.View mView;
    public MoreFraPresenter(MoreFraContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }

}
