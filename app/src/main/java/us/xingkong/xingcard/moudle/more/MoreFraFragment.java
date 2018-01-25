package us.xingkong.xingcard.moudle.more;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import us.xingkong.baymax.Baymax;
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

    @BindView(R.id.tv_name)
    TextView mNameTextView;
    @BindView(R.id.rl_robot)
    RelativeLayout mRobotRL;
    @BindView(R.id.rl_exit)
    RelativeLayout mExitRL;

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

    @Override
    public void setData(String string) {
        mNameTextView.setText("组织：" + string);
    }

    @OnClick(R.id.rl_exit)
    public void rlExitOnClick(View view) {
        mPresenter.clearData();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.rl_robot)
    public void rlRobotOnClick(View view) {
        //TODO: 星大白目前与星名片基础功能处在独立模块，后期可以考虑用组件化路由去跳转，而不选择intent
        Intent intent = new Intent(getActivity(), Baymax.class);
        startActivity(intent);
    }
}
