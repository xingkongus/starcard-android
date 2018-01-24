package us.xingkong.xingcard.utils.acpUtils;

import java.util.List;

public interface AcpListener {
    /**
     *同意
     */
    void onGranted();

    /**
     * 拒绝
     */
    void onDenied(List<String> permissions);
}
