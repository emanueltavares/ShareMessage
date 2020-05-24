package com.emanueltavares.module;

import android.content.Context;

import com.emanueltavares.module.exception.InvalidMessageException;
import com.emanueltavares.module.exception.InvalidUserIdException;
import com.emanueltavares.module.exception.PackageNotInstalledException;

public interface IMessageSender
{
    public void sendMessageTo(String receiverId, String message, Context context) throws PackageNotInstalledException, InvalidUserIdException, InvalidMessageException;

    public String getSenderPackageName();
}
