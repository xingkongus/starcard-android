package us.xingkong.xingcard.moudle.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.Log;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import us.xingkong.xingcard.R;
import us.xingkong.xingcard.XingCardAPP;
import us.xingkong.xingcard.adapter.MainPagerAdapter;
import us.xingkong.xingcard.base.BaseActivity;
import us.xingkong.xingcard.base.Constants;
import us.xingkong.xingcard.moudle.contacts.ContractsFraFragment;
import us.xingkong.xingcard.moudle.more.MoreFraFragment;
import us.xingkong.xingcard.utils.SPUtils;
import us.xingkong.xingcard.utils.StatusBarUtils;

import static us.xingkong.xingcard.base.Constants.STAR_ID;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @BindArray(R.array.tab_title)
    String[] mTitles;
    @BindView(R.id.view_pager)
    AHBottomNavigationViewPager mViewPager;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation mBottomNavigation;

    private ArrayList<AHBottomNavigationItem> mBottomNavigationItems = new ArrayList<>();

    private ContractsFraFragment mContractsFraFragment;
    private MoreFraFragment mMoreFraContract;
    private MainPagerAdapter pagerAdapter;

    private String mStarIDString;

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void prepareData() {
        Intent intent = getIntent();
        mStarIDString = intent.getStringExtra(STAR_ID);
        if (TextUtils.isEmpty(mStarIDString)) {
            mStarIDString = (String) SPUtils.get(XingCardAPP.getAppContext(),
                    Constants.KEY_LOGIN_NAME,
                    "");
        }

    }

    @Override
    protected void initView() {
        List<Fragment> fragments = new ArrayList<>();
        addFragmentList(fragments);

        mViewPager.setOffscreenPageLimit(2);
        pagerAdapter = new MainPagerAdapter(
                getSupportFragmentManager(),
                fragments,
                this.mTitles);
        mViewPager.setAdapter(pagerAdapter);

        initBottomBar();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void initEvent() {
        mBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Log.i("hugeterry", "onTabSelected: " + position);
                if (position == 0) {
                    StatusBarUtils.setWindowStatusBarColor(MainActivity.this,
                            R.color.colorPrimaryDark);
                } else if (position == 1) {
                    StatusBarUtils.setWindowStatusBarColor(MainActivity.this,
                            R.color.colorGreenDrak);
                }
                mViewPager.setCurrentItem(position, false);
                return true;
            }
        });
    }

    private void addFragmentList(List<Fragment> fragments) {
        mContractsFraFragment = new ContractsFraFragment();
        Bundle bundle = new Bundle();
        bundle.putString(STAR_ID, mStarIDString);
        mContractsFraFragment.setArguments(bundle);
        fragments.add(mContractsFraFragment);

        mMoreFraContract = new MoreFraFragment();
        fragments.add(mMoreFraContract);
    }

    private void initBottomBar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(mTitles[0],
                ContextCompat.getDrawable(this, R.drawable.ic_style_white_24dp),
                ContextCompat.getColor(this, R.color.colorBlue));
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(mTitles[1],
                ContextCompat.getDrawable(this, R.drawable.ic_widgets_white_24dp),
                ContextCompat.getColor(this, R.color.colorGreen));
        mBottomNavigationItems.add(item1);
        mBottomNavigationItems.add(item2);

        mBottomNavigation.addItems(mBottomNavigationItems);
        mBottomNavigation.setColored(true);
        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
    }
}
