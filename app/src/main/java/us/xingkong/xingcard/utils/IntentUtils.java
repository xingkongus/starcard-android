package us.xingkong.xingcard.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import java.util.List;

import us.xingkong.xingcard.utils.acpUtils.Acp;
import us.xingkong.xingcard.utils.acpUtils.AcpListener;
import us.xingkong.xingcard.utils.acpUtils.AcpOptions;

/**
 * @author hugeterry(http://hugeterry.cn)
 */

public class IntentUtils {
    /**
     * 打电话
     *
     * @param context
     * @param phoneNumber
     */
    public static void call(Context context, String phoneNumber) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel://" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 调起系统发短信功能
     *
     * @param context
     * @param phoneNumber
     */
    public static void send(Context context, String phoneNumber) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto://" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean intentToCall(final Context context, final String phoneNumber) {
        if (context == null || TextUtils.isEmpty(phoneNumber)) {
            return false;
        }
        //6.0权限处理
        Acp.getInstance(context).request(new AcpOptions.Builder().setPermissions(
                Manifest.permission.CALL_PHONE).build(), new AcpListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onGranted() {
                Uri u = Uri.parse("tel:" + phoneNumber);
                Intent it = new Intent(Intent.ACTION_CALL, u);
                context.startActivity(it);
            }

            @Override
            public void onDenied(List<String> permissions) {

            }
        });

        return true;
    }
}
