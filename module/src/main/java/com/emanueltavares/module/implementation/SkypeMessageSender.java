package com.emanueltavares.module.implementation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.emanueltavares.module.IMessageSender;
import com.emanueltavares.module.MessageSenderUtils;

public class SkypeMessageSender implements IMessageSender {

    private static final String SKYPE_PACKAGE_NAME = "com.skype.raider"; // Skype Package Name
    private static final String SKYPE_URL_SCHEMA = "skype:%s?chat";

    @Override
    public void sendMessageTo(String receiverId, String message, Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (!MessageSenderUtils.isPackageInstalled(SKYPE_PACKAGE_NAME, packageManager)) {
            Toast.makeText(context, "Skype is not installed!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (receiverId.trim().isEmpty()) {
            Toast.makeText(context, "Invalid skype user id!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent sendMessageIntent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(String.format(SKYPE_URL_SCHEMA, receiverId));
        sendMessageIntent.setData(uri);
        context.startActivity(sendMessageIntent);
    }
}
