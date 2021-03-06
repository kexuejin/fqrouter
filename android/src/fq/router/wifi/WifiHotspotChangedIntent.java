package fq.router.wifi;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import fq.router.utils.LoggedBroadcastReceiver;

public class WifiHotspotChangedIntent extends Intent {

    private final static String ACTION_WIFI_HOTSPOT_CHECKED = "WifiHotspotChecked";

    public WifiHotspotChangedIntent(boolean isStarted) {
        setAction(ACTION_WIFI_HOTSPOT_CHECKED);
        putExtra("isStarted", isStarted);
        putExtra("wasStartingWifiRepeater", false);
    }

    public WifiHotspotChangedIntent(boolean isStarted, boolean wasStartingWifiRepeater) {
        setAction(ACTION_WIFI_HOTSPOT_CHECKED);
        putExtra("isStarted", isStarted);
        putExtra("wasStartingWifiRepeater", wasStartingWifiRepeater);
    }

    public static void register(final Handler handler) {
        handler.getBaseContext().registerReceiver(new LoggedBroadcastReceiver() {
            @Override
            public void handle(Context context, Intent intent) {
                handler.onWifiHotspotChanged(
                        intent.getBooleanExtra("isStarted", false),
                        intent.getBooleanExtra("wasStartingWifiRepeater", false));
            }
        }, new IntentFilter(ACTION_WIFI_HOTSPOT_CHECKED));
    }

    public static interface Handler {
        void onWifiHotspotChanged(boolean isStarted, boolean wasStartingWifiRepeater);

        Context getBaseContext();
    }
}
