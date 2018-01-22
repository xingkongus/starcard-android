package us.xingkong.xingcard.adapter.expandableRecyclerviewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import us.xingkong.xingcard.bean.Employee;
import us.xingkong.xingcard.bean.ExpandGroup;
import xingkong.us.expandablerecycleradapter.adapter.BaseExpandableAdapter;
import xingkong.us.expandablerecycleradapter.viewholder.AbstractAdapterItem;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public class ImpExpandableAdapter extends BaseExpandableAdapter {

    private final int ITEM_TYPE_EXPANDGROUP = 1;
    private final int ITEM_TYPE_EMPLOYEE = 2;
    private Context context;

    public ImpExpandableAdapter(Context context, List data) {
        super(data);
        this.context = context;
    }

    @NonNull
    @Override
    public AbstractAdapterItem<Object> getItemView(Object type) {
        int itemType = (int) type;
        switch (itemType) {
            case ITEM_TYPE_EXPANDGROUP:
                return new ExpandGroupItem();
            case ITEM_TYPE_EMPLOYEE:
                return new EmployeeItem(context);
        }
        return null;
    }

    @Override
    public Object getItemViewType(Object t) {
        if (t instanceof ExpandGroup) {
            return ITEM_TYPE_EXPANDGROUP;
        } else if (t instanceof Employee)
            return ITEM_TYPE_EMPLOYEE;
        return -1;
    }
}
