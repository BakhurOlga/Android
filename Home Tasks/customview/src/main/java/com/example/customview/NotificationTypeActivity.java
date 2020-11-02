package com.example.customview;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import static com.example.customview.NOTIFICATION_TYPE.SNACKBAR;
import static com.example.customview.NOTIFICATION_TYPE.TOAST;

public class NotificationTypeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SwitchCompat switchButton;
    private ImageView saveButton;

    private NotificationManager notificationManager;
    private NOTIFICATION_TYPE notificationType;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, NotificationTypeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_type);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        saveButton = findViewById(R.id.save);
        switchButton = findViewById(R.id.switchNotificationType);

        notificationManager = NotificationManager.getNotificationManager(this);
        notificationType = NOTIFICATION_TYPE.valueOf(notificationManager.loadChoice());

        implementNotificationType(notificationType);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSwitchButton();
                finish();
            }
        });
        Log.d("TAG", "onCreate");
    }

    private void setSwitchButton() {
        if (switchButton.isChecked()) {
            notificationManager.saveChoice(SNACKBAR);
        } else {
            notificationManager.saveChoice(TOAST);
        }
    }

    private void implementNotificationType(NOTIFICATION_TYPE notificationType) {
        switch (notificationType) {
            case SNACKBAR:
                switchButton.setChecked(true);
            case TOAST:
                switchButton.setChecked(false);
        }
    }
}