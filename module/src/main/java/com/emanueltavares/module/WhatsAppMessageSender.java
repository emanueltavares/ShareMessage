package com.emanueltavares.module;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WhatsAppMessageSender implements IMessageSender {

    // TESTNUMBER:+559591742026
    private static final String WHATS_APP_PACKAGE_NAME = "com.whatsapp"; // Whats App Package Name
    private static final String WHATS_APP_SEND_URL = "https://api.whatsapp.com/send?phone=%s&text=%s";

    @Override
    public void sendMessageTo(String receiverId, String message, Context context) {

        PackageManager packageManager = context.getPackageManager();
        if (!MessageSenderUtils.isPackageInstalled(WHATS_APP_PACKAGE_NAME, packageManager)) {
            Toast.makeText(context, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
            return;
        }

        // This regular expression removes all characters from the receiver Id, turning it into a phone number
        String phoneNumber = receiverId.replaceAll("[^\\d]", "");

        String url = "";
        try {
            url = String.format(WHATS_APP_SEND_URL, phoneNumber, URLEncoder.encode(message, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(context, "Could not encode message to UTF-8", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent sendMessageIntent = new Intent(Intent.ACTION_VIEW);
        sendMessageIntent.setPackage(WHATS_APP_PACKAGE_NAME);
        sendMessageIntent.setData(Uri.parse(url));
        context.startActivity(Intent.createChooser(sendMessageIntent, "Share with:"));
    }
}
