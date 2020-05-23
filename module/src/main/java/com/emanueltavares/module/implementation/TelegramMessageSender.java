package com.emanueltavares.module.implementation;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.emanueltavares.module.IMessageSender;
import com.emanueltavares.module.MessageSenderUtils;

public class TelegramMessageSender implements IMessageSender {

    private static final String TELEGRAM_MESSENGER_PACKAGE_NAME = "org.telegram.messenger"; // Facebook Package Name
    private static final String TELEGRAM_MESSENGER_URL = "http://t.me/";

    @Override
    public void sendMessageTo(String receiverId, String message, Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (!MessageSenderUtils.isPackageInstalled(TELEGRAM_MESSENGER_PACKAGE_NAME, packageManager)) {
            Toast.makeText(context, "Telegram is not installed!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (receiverId.trim().isEmpty()) {
            Toast.makeText(context, "Invalid telegram user id!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent sendMessageIntent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(TELEGRAM_MESSENGER_URL + receiverId);
        sendMessageIntent.setData(uri);
        context.startActivity(sendMessageIntent);
    }
}
