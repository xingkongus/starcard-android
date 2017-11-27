package xingkong.us.expandablerecycleradapter.model;

import java.util.List;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public interface ExpandableListItem {

    /**
     * Getter for the list of this parent list item's child list items.
     *
     * @return A {@link List} of the children of this {@link ExpandableListItem}
     */
    List<?> getChildItemList();

    /**
     * @return true if expanded, false if not
     */
    boolean isExpanded();

    /**
     * set expand state
     * @param isExpanded
     */
    void setExpanded(boolean isExpanded);
}