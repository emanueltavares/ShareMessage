package com.emanueltavares.sharemessage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.emanueltavares.module.IMessageSender;
import com.emanueltavares.module.WhatsAppMessageSender;

public class MainActivity extends AppCompatActivity {

    private EditText inputUserId;
    private EditText inputMessage;
    private Button buttonSendMessage;
    private RadioGroup senderProviderRadioGroup;
    private IMessageSender messageSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUserId = findViewById(R.id.inputUserId);
        inputMessage = findViewById(R.id.inputMessage);
        senderProviderRadioGroup = findViewById(R.id.senderProviderRadioGroup);
        buttonSendMessage = findViewById(R.id.buttonSendMessage);
        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        buttonSendMessage.setOnClickListener(null);
//    }

    private void sendMessage() {

        String userId = inputUserId.getText().toString();
        String message = inputMessage.getText().toString();
        switch (senderProviderRadioGroup.getCheckedRadioButtonId()) {
            case R.id.whatsAppRadioButton: // Whats App
                messageSender = new WhatsAppMessageSender();
                messageSender.sendMessageTo(userId, message, this);
                break;
            case R.id.telegramRadionButton: // Telegram
                break;
            case R.id.facebookMessengerRadioButton: // Facebook Messenger
                break;
            case R.id.skypeRadioButton: // Skype
                break;
        }
    }
}
