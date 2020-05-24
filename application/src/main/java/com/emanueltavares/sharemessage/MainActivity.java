package com.emanueltavares.sharemessage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.emanueltavares.module.IMessageSender;
import com.emanueltavares.module.exception.InvalidMessageException;
import com.emanueltavares.module.exception.InvalidUserIdException;
import com.emanueltavares.module.exception.PackageNotInstalledException;
import com.emanueltavares.module.implementation.FacebookMessengerMessageSender;
import com.emanueltavares.module.implementation.SkypeMessageSender;
import com.emanueltavares.module.implementation.TelegramMessageSender;
import com.emanueltavares.module.implementation.WhatsAppMessageSender;

public class MainActivity extends AppCompatActivity {

    private EditText inputUserId;
    private EditText inputMessage;
    private RadioGroup senderProviderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUserId = findViewById(R.id.inputUserId);
        inputMessage = findViewById(R.id.inputMessage);

        // Configure Sender Provider Radio Group
        senderProviderRadioGroup = findViewById(R.id.senderProviderRadioGroup);
        senderProviderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // Hide Input Message View if Whats App is not selected
                switch (senderProviderRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.whatsAppRadioButton: // Whats App
                        inputMessage.setVisibility(View.VISIBLE);
                        break;
                    case R.id.telegramRadionButton: // Telegram
                    case R.id.facebookMessengerRadioButton: // Facebook Messenger
                    case R.id.skypeRadioButton: // Skype
                        inputMessage.setVisibility(View.GONE);
                        break;
                }
            }
        });

        // Configure Button Send Message
        Button buttonSendMessage = findViewById(R.id.buttonSendMessage);
        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {

        String userId = inputUserId.getText().toString();
        String message = inputMessage.getText().toString();
        IMessageSender messageSender = null;

        switch (senderProviderRadioGroup.getCheckedRadioButtonId()) {
            case R.id.whatsAppRadioButton: // Whats App
                messageSender = new WhatsAppMessageSender();
                break;
            case R.id.telegramRadionButton: // Telegram
                messageSender = new TelegramMessageSender();
                break;
            case R.id.facebookMessengerRadioButton: // Facebook Messenger
                messageSender = new FacebookMessengerMessageSender();
                break;
            case R.id.skypeRadioButton: // Skype
                messageSender = new SkypeMessageSender();
                break;
        }

        try {
            messageSender.sendMessageTo(userId, message, this);
        } catch (PackageNotInstalledException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

            // Open google play to install package
        } catch (InvalidUserIdException | InvalidMessageException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
