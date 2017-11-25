package us.xingkong.xingcard.base;

import android.os.Bundle;
import android.view.View;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

/**
 * 懒加载Fragment
 *
 * @param <P> 继承自BasePresenter的Presenter
 */
public abstract class BaseLazyLoadFragment<P extends BasePresenter> extends BaseFragment<P> {

    /**
     * 当前fragment是否可见
     */
    protected boolean isVisible;
    /**
     * 是否已经准备好加载数据
     */
    protected boolean isPrepared;
    /**
     * 是否已经加载过数据
     */
    protected boolean hasLoadedOnce;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInVisible();
        }
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void setPrepared(boolean prepared) {
        isPrepared = prepared;
    }

    public boolean isHasLoadedOnce() {
        return hasLoadedOnce;
    }

    public void setHasLoadedOnce(boolean hasLoadedOnce) {
        this.hasLoadedOnce = hasLoadedOnce;
    }

    /**
     * fragment不可见时执行的操作
     */
    private void onInVisible() {

    }

    /**
     * fragment可见时执行的操作
     */
    private void onVisible() {
        initData(null);
    }

    @Override
    protected final void initData(Bundle savedInstanceState) {
        if (!isVisible || !isPrepared || hasLoadedOnce) {
            // 不可见或者没有准备好或者已经加载过一次数据
            return;
        }
        lazyLoadData();
        setHasLoadedOnce(true);
    }

    @Override
    protected void initView(View rootView) {
        initViews(rootView);
        // 视图初始化完成，可以开始加载数据
        setPrepared(true);
    }

    protected abstract void initViews(View rootView);

    /**
     * 延迟加载数据
     */
    protected abstract void lazyLoadData();

}
