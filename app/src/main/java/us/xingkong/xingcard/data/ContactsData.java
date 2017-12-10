package us.xingkong.xingcard.data;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

import us.xingkong.xingcard.R;
import us.xingkong.xingcard.XingCardAPP;
import us.xingkong.xingcard.bean.Contacts;
import us.xingkong.xingcard.net.NetWork;

import static us.xingkong.xingcard.base.Constants.CONTACTS_KEY;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class ContactsData {
    private static ContactsData instance;
    private static final int DATA_SOURCE_MEMORY = 1;
    private static final int DATA_SOURCE_DISK = 2;
    private static final int DATA_SOURCE_NETWORK = 3;

    @IntDef({DATA_SOURCE_MEMORY, DATA_SOURCE_DISK, DATA_SOURCE_NETWORK})
    @interface DataSource {
    }

    BehaviorSubject<Contacts> cache;

    private int dataSource;
    private String groupName;

    private ContactsData() {
    }

    public static ContactsData getInstance() {
        if (instance == null) {
            instance = new ContactsData();
        }
        return instance;
    }

    public void setGroupName(String name) {
        groupName = name;
    }

    private void setDataSource(@DataSource int dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataSourceText() {
        int dataSourceTextRes;
        switch (dataSource) {
            case DATA_SOURCE_MEMORY:
                dataSourceTextRes = R.string.data_source_memory;
                break;
            case DATA_SOURCE_DISK:
                dataSourceTextRes = R.string.data_source_disk;
                break;
            case DATA_SOURCE_NETWORK:
                dataSourceTextRes = R.string.data_source_network;
                break;
            default:
                dataSourceTextRes = R.string.data_source_network;
        }
        return XingCardAPP.getAppContext().getString(dataSourceTextRes);
    }

    public void loadFromNetwork() {
        NetWork.getInstance().getDataService()
                .getDataResults(CONTACTS_KEY, groupName)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Contacts>() {
                    @Override
                    public void accept(Contacts datas) {
                        DataCache.getInstance().writeDatas(datas);
                    }
                })
                .subscribe(new Consumer<Contacts>() {
                    @Override
                    public void accept(Contacts datas) {
                        cache.onNext(datas);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                        cache.onError(throwable);
                    }
                });
    }

    public Disposable subscribeData(@NonNull Consumer<Contacts> onNext, @NonNull Consumer<Throwable> onError) {
        if (cache == null) {
            cache = BehaviorSubject.create();
            Observable.create(new ObservableOnSubscribe<Contacts>() {
                @Override
                public void subscribe(ObservableEmitter<Contacts> e) throws Exception {
                    Contacts datas = DataCache.getInstance().readDatas();
                    if (datas == null) {
                        setDataSource(DATA_SOURCE_NETWORK);
                        loadFromNetwork();
                    } else {
                        setDataSource(DATA_SOURCE_DISK);
                        e.onNext(datas);
                    }
                }
            })
                    .subscribeOn(Schedulers.io())
                    .subscribe(cache);
        } else {
            setDataSource(DATA_SOURCE_MEMORY);
        }
        return cache.doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                cache = null;
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError);
    }

    public void clearMemoryCache() {
        cache = null;
    }

    public void clearMemoryAndDiskCache() {
        clearMemoryCache();
        DataCache.getInstance().delete();
    }

}
