package in.discreetmusings.popcola;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class darkOverlayService extends Service {

    private WindowManager windowManager;
    private ImageView mask;

    public darkOverlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mask = new ImageView(this);

        mask.setBackgroundColor(Color.parseColor("#88880000"));
        mask.setImageDrawable(null);

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);

        windowManager.addView(mask, params);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mask != null) windowManager.removeView(mask);
    }
}
