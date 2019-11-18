package com.yoppi.mybroadcastreceiver;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadService extends IntentService {

    public static final String TAG = "DownloadService";

    public DownloadService() {
        super("DownloadService");
    }


    //Di sini kita akan menjalankan Intent Service yang akan melakukan proses mengunduh file secara Asynchronous di background. Kelas DownloadService mengambil peran di sini. Pada kenyataanya, DownloadService ini hanya melakukan proses sleep() selama 5 detik dan kemudian mem-broadcast sebuah IntentFilter dengan Action yang telah ditentukan
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Download Service dijalankan");
        if (intent != null){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            Intent notifyFinishIntent = new Intent(MainActivity.ACTION_DOWNLOAD_STATUS);
            sendBroadcast(notifyFinishIntent);
        }
    }

}
