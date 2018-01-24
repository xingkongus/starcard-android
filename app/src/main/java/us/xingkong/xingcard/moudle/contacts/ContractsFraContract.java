package us.xingkong.xingcard.moudle.contacts;

import java.util.List;

import us.xingkong.xingcard.base.BasePresenter;
import us.xingkong.xingcard.base.BaseView;
import us.xingkong.xingcard.bean.Contacts;
import us.xingkong.xingcard.bean.ExpandGroup;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public interface ContractsFraContract {
    interface View extends BaseView<Presenter> {
        void updateContactsUI(Contacts contacts);

    }

    interface Presenter extends BasePresenter {
        void getContactsList(String name);
    }
}
