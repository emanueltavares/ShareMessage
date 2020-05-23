package com.emanueltavares.module;

import android.content.Context;

public interface IMessageSender
{
    public void sendMessageTo(String receiverId, String message, Context context);
}
