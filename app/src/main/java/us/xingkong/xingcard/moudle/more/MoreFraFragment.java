package us.xingkong.xingcard.moudle.more;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import us.xingkong.xingcard.R;
import us.xingkong.xingcard.adapter.expandableRecyclerviewAdapter.ImpExpandableAdapter;
import us.xingkong.xingcard.base.BaseFragment;
import us.xingkong.xingcard.bean.ExpandGroup;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public class MoreFraFragment extends BaseFragment<MoreFraContract.Presenter> implements MoreFraContract.View {

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
        setToolbarTitle("探索");

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


    @Override
    protected void initEvent() {

    }

}
