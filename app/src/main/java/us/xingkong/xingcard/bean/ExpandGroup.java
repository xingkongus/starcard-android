package us.xingkong.xingcard.bean;

import java.util.List;

import xingkong.us.expandablerecycleradapter.model.ExpandableListItem;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class ExpandGroup implements ExpandableListItem {

    public String name;
    public List<Employee> members;

    public boolean mExpanded = false;

    @Override
    public List<?> getChildItemList() {
        return members;
    }

    @Override
    public boolean isExpanded() {
        return mExpanded;
    }

    @Override
    public void setExpanded(boolean isExpanded) {
        mExpanded = isExpanded;
    }

    @Override
    public String toString() {
        return "ExpandGroup{" + "name='" + name + '\'' + '}';
    }
}
