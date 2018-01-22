package us.xingkong.xingcard.moudle.main;

import us.xingkong.xingcard.base.BasePresenterImpl;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public class MainPresenter extends BasePresenterImpl implements MainContract.Presenter {

    private final MainContract.View mView;
    public MainPresenter(MainContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }


}
