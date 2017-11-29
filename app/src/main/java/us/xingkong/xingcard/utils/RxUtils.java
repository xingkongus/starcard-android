package us.xingkong.xingcard.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class RxUtils {

    public static <T> Observable.Transformer<T, T> defaultSchedulers() {
        return new Observable.Transformer<T, T>() {

            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io());
            }
        };
    }

    public static <T> Observable.Transformer<T, T> allIO() {
        return new Observable.Transformer<T, T>() {

            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.observeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io());
            }
        };
    }

}
