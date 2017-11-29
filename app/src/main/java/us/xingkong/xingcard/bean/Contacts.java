package us.xingkong.xingcard.bean;

import java.util.List;

import static us.xingkong.xingcard.base.Constants.STATE_CODE;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class Contacts extends BaseBean{

    private String result;
    private String department_belong;
    private List<ExpandGroup> departments;

    public boolean isError() {
        if (result == STATE_CODE) return true;
        else return false;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDepartment_belong() {
        return department_belong;
    }

    public void setDepartment_belong(String department_belong) {
        this.department_belong = department_belong;
    }

    public List<ExpandGroup> getDepartments() {
        return departments;
    }

    public void setDepartments(List<ExpandGroup> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "result='" + result + '\'' +
                ", department_belong='" + department_belong + '\'' +
                ", departments=" + departments +
                '}';
    }
}
