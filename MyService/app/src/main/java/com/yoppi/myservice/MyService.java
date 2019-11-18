package com.yoppi.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.lang.ref.WeakReference;

// Langkah 2 : implement interface dan implement method preAsync dan postAsync
public class MyService extends Service implements DummyAsyncCallback{
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d(TAG, "onStartCommand: ");

        // Langkah 4 : inisialisasi dan jalankan Asynctask
        DummyAsync dummyAsync = new DummyAsync(this);
        dummyAsync.execute();

        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    // Langkah 5 : tambahkan aksi di callback
    @Override
    public void preAsync() {
        Log.d(TAG, "preAsync: Mulai.....");
    }

    @Override
    public void postAsync() {
        Log.d(TAG, "preAsync: Selesai.....");
        stopSelf();
    }

    // Langkah 3 : buat AsyncTask dan WeakReference
    private static class DummyAsync extends AsyncTask<Void,Void,Void>{

        private final WeakReference<DummyAsyncCallback> callback;

        private DummyAsync(DummyAsyncCallback callback) {
            this.callback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: ");
            callback.get().preAsync();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: ");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void  onPostExecute(Void avoid){
            super.onPostExecute(avoid);
            Log.d(TAG, "onPostExecute: ");
            callback.get().postAsync();
        }


    }
}

// Langkah 1 : buat Interface
interface  DummyAsyncCallback{
    void preAsync();
    void postAsync();
}
