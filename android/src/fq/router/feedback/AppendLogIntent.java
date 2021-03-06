package fq.router.feedback;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import fq.router.utils.LoggedBroadcastReceiver;

public class AppendLogIntent extends Intent {
    private final static String ACTION_APPEND_LOG = "AppendLog";

    public AppendLogIntent(String log) {
        setAction(ACTION_APPEND_LOG);
        putExtra("log", log);
    }

    public static void register(final Handler handler) {
        handler.getBaseContext().registerReceiver(new LoggedBroadcastReceiver() {
            @Override
            public void handle(Context context, Intent intent) {
                handler.appendLog(intent.getStringExtra("log"));
            }
        }, new IntentFilter(ACTION_APPEND_LOG));
    }

    public static interface Handler {
        void appendLog(String log);

        Context getBaseContext();
    }
}
