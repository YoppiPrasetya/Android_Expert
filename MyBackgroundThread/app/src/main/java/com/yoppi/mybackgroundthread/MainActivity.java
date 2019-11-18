package com.yoppi.mybackgroundthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements MyAsyncCallback {

    private TextView tvStatus, tvDesc;
    private final static String INPUT_STRING = "Halo Ini Demo AsyncTask !!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tv_status);
        tvDesc = findViewById(R.id.tv_desc);

        DemoAsync demoAsync = new DemoAsync(this);
        demoAsync.execute(INPUT_STRING);

    }

    // Pada metode onPreExecute(), kita hanya menuliskan status onPreExecute ke dalam obyek TextView untuk menandakan bahwa metode onPreExecute telah dijalankan.
    @Override
    public void onPreExecute() {
        tvStatus.setText(R.string.status_pre);
        tvDesc.setText(INPUT_STRING);

    }

    //Terakhir, metode onPostExecute() akan menampilkan hasil proses yang dilakukan di doInBackground().
    @Override
    public void onPostExecute(String result) {
        tvStatus.setText(R.string.status_post);
        if (result != null){
            tvDesc.setText(result);
        }
    }

    private static class DemoAsync extends AsyncTask<String, Void, String> {

        //Jika Anda perhatikan ada penggunaan WeakReference di dalam AsyncTask. WeakReference disarankan untuk menghindari memory leak yang bisa terjadi dalam AsyncTask. Memory leak (kebocoran memori) ini bisa terjadi ketika aplikasi sudah ditutup, akan tetapi proses asynctask masih tetap berjalan. Ketika hal ini terjadi maka seharusnya garbage collector bisa membersihkan variable yang ada di dalam asynctask. Ketika kita tidak menggunakan weakreference maka garbage collector tidak bisa membersihkan variable di asynctask dan akan tercipta memory leak.
        //
        //
        //Pada latihan ini weakreference digunakan untuk mereferensikan obyek callback
        static final String LOG_ASYNC = "DemoAsync";
        WeakReference<MyAsyncCallback> myListener;
        DemoAsync(MyAsyncCallback myListener){
            this.myListener = new WeakReference<>(myListener);
        }

        // Mempersiapkan AsyncTask, disini masih berjalan di main thread dan bisa digunakan untuk mengakses view
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Log.d(LOG_ASYNC, "status : onPreExecute");

            MyAsyncCallback myListener = this.myListener.get();
            if (myListener != null){
                myListener.onPreExecute();
            }
        }


        // Kemudian di sini kita melakukan proses sederhana yaitu menambahkan teks "Selamat Belajar!!" ke parameter input. Proses ini memerlukan waktu selama 2 detik (2000 miliseconds). Hasil dari proses tersebut kita kembalikan dalam obyek output. Sekarang seharusnya nilai dari obyek output adalah "Halo ini demo AsyncTask. Selamat Belajar!!."
        //
        //Jika diperhatikan pada bagian Java, ada tanda ... dalam param tersebut. Ini merupakan bentuk lain dari array dan menunjukkan bahwa inputan dari sebuah AsyncTask bisa lebih dari satu.
        //
        //Sesuai dengan aturan kedua yaitu "Jangan melakukan manipulasi terhadap ui toolkit pada worker thread yang berjalan secara asynchronous", maka kita hanya bisa memantau proses yang terjadi
        @Override
        protected String doInBackground(String... params) {
            Log.d(LOG_ASYNC, "status : doInBackground");
            String output = null;
            try {
                String input = params[0];
                output = input + " Selamat Belajar!!";
                Thread.sleep(2000);
            } catch (Exception e){
                Log.d(LOG_ASYNC, e.getMessage());
            }
            return output;
        }


        //Kode di atas, yakni onPostExecute berjalan ketika proses doInBackground telah selesai dan akan dijalankan di main thread yang mana state/kondisi ini dapat mengakses view.
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            Log.d(LOG_ASYNC, "status : OnPostExecute");

            MyAsyncCallback myListener = this.myListener.get();
            if (myListener != null){
                myListener.onPostExecute(result);
            }
        }
    }
}

interface MyAsyncCallback{
    void onPreExecute();
    void onPostExecute(String text);
}
