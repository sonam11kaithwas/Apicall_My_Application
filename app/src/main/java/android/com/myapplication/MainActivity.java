package android.com.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        internetConnection();
    }

    private void internetConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        if (connected) {
            getUserListFromServer();
        } else {
            Toast.makeText(this, "Check internet connection.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getUserListFromServer() {

        ApicallCtrl.getservices().userList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext( JsonObject responseBody) {
                        Log.e("", "");
                        TextView txt=findViewById(R.id.txt);
                        txt.setText(responseBody.get("data").toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("", "");
                        TextView txt=findViewById(R.id.txt);
                        txt.setText(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}