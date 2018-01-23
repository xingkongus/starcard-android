package us.xingkong.xingcard.moudle.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import us.xingkong.xingcard.R;
import us.xingkong.xingcard.XingCardAPP;
import us.xingkong.xingcard.base.BaseFragment;
import us.xingkong.xingcard.base.Constants;
import us.xingkong.xingcard.data.ContactsData;
import us.xingkong.xingcard.moudle.login.LoginActivity;
import us.xingkong.xingcard.utils.SPUtils;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public class MoreFraFragment extends BaseFragment<MoreFraContract.Presenter> implements MoreFraContract.View {

    @BindView(R.id.bt_exit)
    Button mExitButton;

    @Override
    protected MoreFraContract.Presenter createPresenter() {
        return new MoreFraPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_more;
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View rootView) {
        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        setToolbarTitle("探索");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


    @Override
    protected void initEvent() {

    }

    @OnClick(R.id.bt_exit)
    public void onClick(View view) {
        ContactsData.getInstance().clearMemoryAndDiskCache();
        SPUtils.remove(XingCardAPP.getAppContext(), Constants.KEY_LOGIN_NAME);

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
