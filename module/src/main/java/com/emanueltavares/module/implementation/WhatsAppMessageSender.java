package com.emanueltavares.module.implementation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.emanueltavares.module.IMessageSender;
import com.emanueltavares.module.MessageSenderUtils;
import com.emanueltavares.module.exception.InvalidMessageException;
import com.emanueltavares.module.exception.InvalidUserIdException;
import com.emanueltavares.module.exception.PackageNotInstalledException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WhatsAppMessageSender implements IMessageSender {

    private static final String WHATS_APP_SEND_URL = "https://api.whatsapp.com/send?phone=%s&text=%s";

    @Override
    public void sendMessageTo(String receiverId, String message, Context context) throws PackageNotInstalledException, InvalidUserIdException, InvalidMessageException {

        PackageManager packageManager = context.getPackageManager();
        if (!MessageSenderUtils.isPackageInstalled(getSenderPackageName(), packageManager)) {
            throw new PackageNotInstalledException(getSenderPackageName());
        }

        // This regular expression removes all characters from the receiver Id, turning it into a phone number
        String phoneNumber = receiverId.replaceAll("[^\\d]", "");
        if (phoneNumber.trim().isEmpty()) {
            throw new InvalidUserIdException(receiverId);
        }

        String url;
        try {
            url = String.format(WHATS_APP_SEND_URL, phoneNumber, URLEncoder.encode(message, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new InvalidMessageException(message);
        }

        Intent sendMessageIntent = new Intent(Intent.ACTION_VIEW);
        sendMessageIntent.setPackage(getSenderPackageName());
        sendMessageIntent.setData(Uri.parse(url));
        context.startActivity(Intent.createChooser(sendMessageIntent, "Share with:"));
    }

    @Override
    public String getSenderPackageName() {
        return "com.whatsapp";
    }
}
