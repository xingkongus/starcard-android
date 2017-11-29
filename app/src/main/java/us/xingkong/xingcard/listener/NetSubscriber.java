package us.xingkong.xingcard.listener;

import rx.Subscriber;

import us.xingkong.xingcard.XingCardAPP;
import us.xingkong.xingcard.bean.BaseBean;
import us.xingkong.xingcard.utils.LogUtils;
import us.xingkong.xingcard.utils.NetUtils;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public abstract class NetSubscriber<T extends BaseBean> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (NetUtils.isConnected(XingCardAPP.getAppContext())) {
            // 没有网络
            onFailure("网络连接失败，请检查网络设置");
        } else {
            onFailure("获取数据失败，请稍后重试");
        }
    }

    @Override
    public void onNext(final T t) {
        LogUtils.$Log(t.toString());
        if (!t.isError()) {
            onSuccess(t);
        } else {
            onFailure(t.toString());
        }
    }

    /**
     * 网络请求成功回调(isError为false)
     *
     * @param data 成功获取到的数据
     */
    public abstract void onSuccess(T data);

    /**
     * 网络请求失败回调(Status为false或onError)
     *
     * @param failMsg 失败信息
     */
    public abstract void onFailure(String failMsg);
}

