package us.xingkong.xingcard.moudle.sample;

import us.xingkong.xingcard.base.BasePresenterImpl;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public class SamplePresenter extends BasePresenterImpl implements SampleContract.Presenter {

    private final SampleContract.View mView;
    public SamplePresenter(SampleContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }


}
