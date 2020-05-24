package com.emanueltavares.module.implementation;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.emanueltavares.module.IMessageSender;
import com.emanueltavares.module.MessageSenderUtils;
import com.emanueltavares.module.exception.InvalidUserIdException;
import com.emanueltavares.module.exception.PackageNotInstalledException;

public class FacebookMessengerMessageSender implements IMessageSender {

    private static final String FB_MESSENGER_URL = "fb-messenger://user/";

    @Override
    public void sendMessageTo(String receiverId, String message, Context context) throws PackageNotInstalledException, InvalidUserIdException {

        PackageManager packageManager = context.getPackageManager();
        if (!MessageSenderUtils.isPackageInstalled(getSenderPackageName(), packageManager)) {
            throw new PackageNotInstalledException(getSenderPackageName());
        }

        if (receiverId.trim().isEmpty()) {
            throw new InvalidUserIdException(receiverId);
        }

        Uri uri = Uri.parse(FB_MESSENGER_URL);
        uri = ContentUris.withAppendedId(uri,Long.parseLong(receiverId));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        context.startActivity(intent);
    }

    @Override
    public String getSenderPackageName() {
        return "com.facebook.orca";
    }
}
