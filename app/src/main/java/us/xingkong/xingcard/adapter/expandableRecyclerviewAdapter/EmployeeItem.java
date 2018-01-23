package us.xingkong.xingcard.adapter.expandableRecyclerviewAdapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import us.xingkong.xingcard.R;
import us.xingkong.xingcard.bean.Employee;
import us.xingkong.xingcard.data.TellNumController;
import xingkong.us.expandablerecycleradapter.viewholder.AbstractAdapterItem;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public class EmployeeItem extends AbstractAdapterItem {

    private Context context;
    private TextView mNameTV;
    private TextView mTelTV;
    private Button mCallIV;
    private Button mSmsIV;
    private Employee employee;

    public EmployeeItem(Context context) {
        this.context = context;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_employee;
    }

    @Override
    public void onBindViews(View root) {
        mNameTV = (TextView) root.findViewById(R.id.tv_name);
        mTelTV = (TextView) root.findViewById(R.id.tv_tel);
        mCallIV = (Button) root.findViewById(R.id.bt_call);
        mSmsIV = (Button) root.findViewById(R.id.bt_sms);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public void onSetViews() {
    }

    @Override
    public void onUpdateViews(Object model, int position) {
        if (model instanceof Employee) {
            employee = (Employee) model;
            mNameTV.setText(employee.username);
            if (TellNumController.getController().getmTellType() == TellNumController.SHORT_TELL_NUM) {
                mTelTV.setText(employee.tel);
            } else {
                mTelTV.setText(employee.phone);
            }
        }
    }
}
