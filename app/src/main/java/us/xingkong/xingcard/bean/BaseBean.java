package us.xingkong.xingcard.bean;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class BaseBean {

    protected boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "BaseRealSBean{" +
                "error=" + error +
                '}';
    }
}
