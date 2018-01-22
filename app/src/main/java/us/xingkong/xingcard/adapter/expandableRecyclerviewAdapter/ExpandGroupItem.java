package us.xingkong.xingcard.adapter.expandableRecyclerviewAdapter;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import us.xingkong.xingcard.R;
import us.xingkong.xingcard.bean.ExpandGroup;
import xingkong.us.expandablerecycleradapter.viewholder.AbstractExpandableAdapterItem;

/**
 * @author hugeterry(http://hugeterry.cn)
 */
public class ExpandGroupItem extends AbstractExpandableAdapterItem {

    private TextView mNameTV;
    private ImageView mArrowIV;
    private ExpandGroup mExpandGroup;

    @Override
    public int getLayoutResId() {
        return R.layout.item_expand_group;
    }

    @Override
    public void onBindViews(final View root) {
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doExpandOrUnexpand();
            }
        });
        mNameTV = (TextView) root.findViewById(R.id.tv_name);
        mArrowIV = (ImageView) root.findViewById(R.id.iv_arrow);
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        float start, target;
        if (expanded) {
            start = 0f;
            target = -90f;
        } else {
            start = -90f;
            target = 0f;
        }
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mArrowIV, View.ROTATION, start, target);
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }

    @Override
    public void onSetViews() {
        mArrowIV.setImageResource(0);
        mArrowIV.setImageResource(R.drawable.ic_chevron_left_white_24dp);
    }

    @Override
    public void onUpdateViews(Object model, int position) {
        super.onUpdateViews(model, position);
        onSetViews();
        onExpansionToggled(getExpandableListItem().isExpanded());
        mExpandGroup = (ExpandGroup) model;
        mNameTV.setText(mExpandGroup.name);
    }
}
