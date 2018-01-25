package us.xingkong.xingcard.moudle.more;

import us.xingkong.xingcard.base.BasePresenter;
import us.xingkong.xingcard.base.BaseView;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public interface MoreFraContract {
    interface View extends BaseView<Presenter> {
        void setData(String string);
    }

    interface Presenter extends BasePresenter {
        void clearData();
    }
}
