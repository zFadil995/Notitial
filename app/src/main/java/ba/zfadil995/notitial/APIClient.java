package ba.zfadil995.notitial;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public enum APIClient {
    Instance;
    private RequestQueue requestQueue;
    private final String baseUrl = "https://mobile.notitial.com/api/";

    public void InitAPIClient(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    public boolean LogIn(final String studentID, final String password){
        final String url = baseUrl.concat("login");

        RequestFuture<String> future = RequestFuture.newFuture();
        StringRequest request = new StringRequest(Request.Method.POST, url, future, future) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("StudentID", studentID);
                params.put("Password", password);
                return params;
            }
        };
        requestQueue.add(request);

        try {
            String response = future.get(30, TimeUnit.SECONDS);
            return true;
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            return false;
        }
    }
    public boolean GetGrades(){
        final String url = baseUrl.concat("grades");

        RequestFuture<String> future = RequestFuture.newFuture();
        StringRequest request = new StringRequest(Request.Method.GET, url, future, future);
        requestQueue.add(request);

        try {
            String response = future.get(30, TimeUnit.SECONDS);
            return true;
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            return false;
        }
    }
}