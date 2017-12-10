package us.xingkong.xingcard.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public abstract class BasePresenterImpl implements BasePresenter {
    protected CompositeDisposable mSubscriptions;

    @Override
    public void onStart() {
        if (mSubscriptions == null) {
            mSubscriptions = new CompositeDisposable();
        }
    }

    @Override
    public void onDestroy() {
        if (mSubscriptions != null) {
            mSubscriptions.dispose();
            mSubscriptions.clear();
        }
    }
}
