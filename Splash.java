package in.discreetmusings.popcola;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        drawDarkRedOverlay();

    }

    protected void drawDarkRedOverlay() {

        TextView tv_message = (TextView) findViewById(R.id.tv_helloWorld);
        int OVERLAY_PERMISSION_REQ_CODE = 1234;

        String permission = "android.permission.SYSTEM_ALERT_WINDOW";
        int res = ContextCompat.checkSelfPermission(this, permission);
        if (res == PackageManager.PERMISSION_GRANTED) {
            tv_message.setText("API < 23 method!");
            startService(new Intent(this, darkOverlayService.class));
        }
        else {

            tv_message.setText("Permission not available!");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
                }
                else {
                    startService(new Intent(this, darkOverlayService.class));
                    tv_message.setText("API >= 23 method!");
                }
            }
        }
    }
}
