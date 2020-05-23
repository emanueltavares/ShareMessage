package com.emanueltavares.module.implementation;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.emanueltavares.module.IMessageSender;
import com.emanueltavares.module.MessageSenderUtils;

public class FacebookMessengerMessageSender implements IMessageSender {

    private static final String FB_MESSENGER_PACKAGE_NAME = "com.facebook.orca"; // Facebook Package Name
    private static final String FB_MESSENGER_URL = "fb-messenger://user/";

    @Override
    public void sendMessageTo(String receiverId, String message, Context context) {

        PackageManager packageManager = context.getPackageManager();
        if (!MessageSenderUtils.isPackageInstalled(FB_MESSENGER_PACKAGE_NAME, packageManager)) {
            Toast.makeText(context, "Facebook Messenger is not installed!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (receiverId.trim().isEmpty()) {
            Toast.makeText(context, "Invalid facebook id!", Toast.LENGTH_SHORT).show();
            return;
        }

        Uri uri = Uri.parse(FB_MESSENGER_URL);
        uri = ContentUris.withAppendedId(uri,Long.valueOf(receiverId));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        context.startActivity(intent);
    }
}
