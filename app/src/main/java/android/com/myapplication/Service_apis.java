package android.com.myapplication;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
//
//import io.reactivex.Observable;
//import retrofit2.http.GET;
//import com.google.gson.JsonObject;

/**
 * Created by Sona-11 on 16/12/21.
 */
public interface Service_apis {
    @GET("api/users?page=2")
    Observable<JsonObject> userList();
}
