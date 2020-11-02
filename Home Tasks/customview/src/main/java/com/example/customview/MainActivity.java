package com.example.customview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private NotificationManager notificationManager;
    private NOTIFICATION_TYPE notificationType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        notificationManager = NotificationManager.getNotificationManager(this);
        notificationType = NOTIFICATION_TYPE.valueOf(notificationManager.loadChoice());

        ((CustomView) findViewById(R.id.customView)).setTouchActionListener(new CustomView.TouchActionListener() {
            @Override
            public void onTouchDown(int x, int y) {
                switch (notificationType) {
                    case TOAST:
                        Toast.makeText(MainActivity.this, "Нажаты координаты [" + x + " : " + y + "]", Toast.LENGTH_SHORT).show();
                        break;
                    case SNACKBAR:
                        Snackbar.make(findViewById(R.id.parentContainer), "Нажаты координаты [" + x + " : " + y + "]", BaseTransientBottomBar.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();
        if (itemID == R.id.chooseNotificationType) {
            startNotificationTypeActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void startNotificationTypeActivity() {
        startActivity(NotificationTypeActivity.newIntent(MainActivity.this));
    }
}
