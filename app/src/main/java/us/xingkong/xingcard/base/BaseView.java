package us.xingkong.xingcard.base;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public interface BaseView<P> {

    void setPresenter(P presenter);

    void showToast(CharSequence msg);

    void showToast(int msgId);

    void showLoadingDialog(CharSequence msg);

    void hideLoadingDialog();


}
