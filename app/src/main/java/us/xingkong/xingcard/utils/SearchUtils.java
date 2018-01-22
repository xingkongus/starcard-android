package us.xingkong.xingcard.utils;


import java.util.ArrayList;
import java.util.List;

import us.xingkong.xingcard.bean.Employee;
import us.xingkong.xingcard.bean.ExpandGroup;

public class SearchUtils {
    public static List<ExpandGroup> filterData(String filterStr, List<ExpandGroup> mExpandGroups) {
        CharacterParser characterParser = CharacterParser.getInstance();
        List<ExpandGroup> mExpandGroupsFilterList = new ArrayList<>();
        for (int i = 0; i < mExpandGroups.size(); i++) {
            int isAddGroup = 0;
            ExpandGroup mExpandGroupFilterList = new ExpandGroup();
            Object obj = mExpandGroups.get(i);
            if (obj instanceof ExpandGroup) {
                List<Employee> employeeFilterList = new ArrayList<>();
                for (int j = 0; j < mExpandGroups.get(i).members.size(); j++) {
                    Employee employee = mExpandGroups.get(i).members.get(j);
                    String name = employee.username;
                    String tel = employee.tel;
                    if (name.indexOf(filterStr.toString()) != -1
                            || characterParser.getSelling(name).startsWith(filterStr.toString())
                            || tel.indexOf(filterStr.toString()) != -1
                            || characterParser.getSelling(tel).startsWith(filterStr.toString())) {
                        if (!employeeFilterList.contains(employee)) {
                            employeeFilterList.add(employee);
                            isAddGroup = 2;
                        }
                    }
                }
                if (mExpandGroups.get(i).name.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(mExpandGroups.get(i).name).startsWith(filterStr.toString())) {
                    if (!mExpandGroupsFilterList.contains(mExpandGroups.get(i))) {
                        isAddGroup = 1;
                    }
                }
                if (isAddGroup == 1) {
                    mExpandGroupsFilterList.add(mExpandGroups.get(i));
                } else if (isAddGroup == 2) {
                    mExpandGroupFilterList.name = mExpandGroups.get(i).name;
                    mExpandGroupFilterList.members = employeeFilterList;
                    mExpandGroupsFilterList.add(mExpandGroupFilterList);
                }
            }
        }
        return mExpandGroupsFilterList;
    }

}
