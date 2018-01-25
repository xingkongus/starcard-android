package us.xingkong.xingcard.moudle.contacts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemSelected;
import us.xingkong.xingcard.R;
import us.xingkong.xingcard.adapter.expandableRecyclerviewAdapter.ImpExpandableAdapter;
import us.xingkong.xingcard.base.BaseFragment;
import us.xingkong.xingcard.bean.Contacts;
import us.xingkong.xingcard.bean.Employee;
import us.xingkong.xingcard.bean.ExpandGroup;
import us.xingkong.xingcard.data.TellNumController;
import us.xingkong.xingcard.moudle.login.LoginActivity;
import us.xingkong.xingcard.moudle.main.MainActivity;
import us.xingkong.xingcard.moudle.more.MoreFraFragment;
import us.xingkong.xingcard.utils.SearchUtils;
import xingkong.us.expandablerecycleradapter.adapter.BaseExpandableAdapter;

import static android.content.ContentValues.TAG;
import static us.xingkong.xingcard.base.Constants.STAR_ID;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public class ContractsFraFragment extends BaseFragment<ContractsFraContract.Presenter> implements ContractsFraContract.View {

    @BindView(R.id.sv_contracts)
    SearchView mSearchView;
    @BindView(R.id.spinner)
    Spinner mSpinner;
    @BindView(R.id.rv_contracts)
    RecyclerView mRecyclerView;

    ImpExpandableAdapter mImpExpandableAdapter;
    List<ExpandGroup> mExpandGroups = new ArrayList<>();

    private String mStarIDString;

    @Override
    protected ContractsFraContract.Presenter createPresenter() {
        return new ContractsFraPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_contacts;
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        mStarIDString = bundle.getString(STAR_ID);
        if (!TextUtils.isEmpty(mStarIDString)) {
            mPresenter.getContactsList(mStarIDString);
        }
    }

    @Override
    protected void initView(View rootView) {
        setToolbarTitle("联系人");
        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mSearchView.clearFocus();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


    @Override
    protected void initEvent() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    List<ExpandGroup> mExpandGroupsRearch = mExpandGroups;
                    List<ExpandGroup> expandGroups = SearchUtils.filterData(newText, mExpandGroupsRearch);
                    mImpExpandableAdapter.updateData(expandGroups);
                    mImpExpandableAdapter.expandAllParents();
                } else {
                    mImpExpandableAdapter.reUpdateData(mExpandGroups);
                }
                return false;
            }
        });
    }

    public void updateContactsUI(Contacts contacts) {
        mExpandGroups.addAll(contacts.getDepartments());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mImpExpandableAdapter = new ImpExpandableAdapter(getActivity(), mExpandGroups);
        mRecyclerView.setAdapter(mImpExpandableAdapter);
        mImpExpandableAdapter.setExpandCollapseListener(new BaseExpandableAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {

            }

            @Override
            public void onListItemCollapsed(int position) {

            }
        });

        MoreFraFragment moreFraFragment = ((MainActivity) getActivity()).getmMoreFraContract();
        moreFraFragment.setData(contacts.getDepartment_belong());

    }

    public void loadDataFail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.login_fail);
        builder.setMessage(R.string.re_login);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().startActivity(intent);
            }
        }).create().show();
    }

    @OnItemSelected(R.id.spinner)
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TellNumController.getController().setmTellType(position);
        if (mImpExpandableAdapter != null) {
            mImpExpandableAdapter.notifyDataSetChanged();
        }
    }
}
