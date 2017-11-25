package us.xingkong.xingcard.moudle.sampleFragment;

import us.xingkong.xingcard.base.BasePresenterImpl;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class SampleFraPresenter extends BasePresenterImpl implements SampleFraContract.Presenter {

    private final SampleFraContract.View mView;
    public SampleFraPresenter(SampleFraContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }

}
