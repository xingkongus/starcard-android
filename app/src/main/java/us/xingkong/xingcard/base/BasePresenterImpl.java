package us.xingkong.xingcard.base;

import rx.subscriptions.CompositeSubscription;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public abstract class BasePresenterImpl implements BasePresenter {
    protected CompositeSubscription mSubscriptions;

    @Override
    public void onStart() {
        if (mSubscriptions == null) {
            mSubscriptions = new CompositeSubscription();
        }
    }

    @Override
    public void onDestroy() {
        if (mSubscriptions != null && mSubscriptions.hasSubscriptions()) {
            mSubscriptions.unsubscribe();
            mSubscriptions.clear();
        }
    }
}
