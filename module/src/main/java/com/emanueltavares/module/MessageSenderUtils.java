package com.emanueltavares.module;

import android.content.pm.PackageManager;

public class MessageSenderUtils {

    public  static boolean isPackageInstalled(String packageName, PackageManager packageManager) {

        try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
