package us.xingkong.xingcard.moudle.login;

import us.xingkong.xingcard.base.BasePresenterImpl;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public class LoginPresenter extends BasePresenterImpl implements LoginContract.Presenter {

    private final LoginContract.View mView;
    public LoginPresenter(LoginContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }


}
