package us.xingkong.xingcard.moudle.contacts;

import us.xingkong.xingcard.base.BasePresenter;
import us.xingkong.xingcard.base.BaseView;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public interface ContactsContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        void getContactsList(String name);
    }
}
