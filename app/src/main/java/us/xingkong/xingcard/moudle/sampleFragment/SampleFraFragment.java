package us.xingkong.xingcard.moudle.sampleFragment;

import android.os.Bundle;
import android.view.View;

import us.xingkong.xingcard.R;
import us.xingkong.xingcard.base.BaseFragment;

/**
 * @author hugeterry(http://hugeterry.cn)
 * 这只是mvp的例子，做其他界面可以再这个基础上根据提示写功能
 */
public class SampleFraFragment extends BaseFragment<SampleFraContract.Presenter> implements SampleFraContract.View {


    @Override
    protected SampleFraContract.Presenter createPresenter() {
        return new SampleFraPresenter(this);
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


    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


    @Override
    protected void initEvent() {

    }

}
