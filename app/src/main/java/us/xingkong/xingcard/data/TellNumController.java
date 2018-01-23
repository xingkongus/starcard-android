package us.xingkong.xingcard.data;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class TellNumController {
    public static final int SHORT_TELL_NUM = 0;
    public static final int LONG_TELL_NUM = 1;

    private static class TellNumControllerHolder {
        public static final TellNumController helper = new TellNumController();
    }

    public static TellNumController getController() {
        return TellNumControllerHolder.helper;
    }

    private int mTellType = SHORT_TELL_NUM;

    public int getmTellType() {
        return mTellType;
    }

    public void setmTellType(int mTellType) {
        this.mTellType = mTellType;
    }
}
