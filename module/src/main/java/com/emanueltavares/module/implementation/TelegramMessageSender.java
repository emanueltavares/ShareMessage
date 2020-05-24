package com.emanueltavares.module.implementation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.emanueltavares.module.IMessageSender;
import com.emanueltavares.module.MessageSenderUtils;
import com.emanueltavares.module.exception.InvalidUserIdException;
import com.emanueltavares.module.exception.PackageNotInstalledException;

public class TelegramMessageSender implements IMessageSender {

    private static final String TELEGRAM_MESSENGER_URL = "http://t.me/";

    @Override
    public void sendMessageTo(String receiverId, String message, Context context) throws PackageNotInstalledException, InvalidUserIdException {
        PackageManager packageManager = context.getPackageManager();
        if (!MessageSenderUtils.isPackageInstalled(getSenderPackageName(), packageManager)) {
            throw new PackageNotInstalledException(getSenderPackageName());
        }

        if (receiverId.trim().isEmpty()) {
            throw new InvalidUserIdException(receiverId);
        }

        Intent sendMessageIntent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(TELEGRAM_MESSENGER_URL + receiverId);
        sendMessageIntent.setData(uri);
        context.startActivity(sendMessageIntent);
    }

    @Override
    public String getSenderPackageName() {
        return "org.telegram.messenger";
    }
}
