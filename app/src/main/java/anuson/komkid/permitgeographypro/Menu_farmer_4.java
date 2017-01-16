package anuson.komkid.permitgeographypro;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;


public class Menu_farmer_4 extends Activity {

    private String[] urlStrings;
    private int post = 0;
    Response response;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_farmer_4);

        urlStrings = new String[]{"http://swiftcodingthai.com/gam/php_get_post.php"};

        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlStrings[post]).build();
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ArrayList<String> listdata = new ArrayList<String>();
        String[] jArray = new String[Integer.parseInt(response.body().toString())];
        if (jArray != null) {
            for (int i = 0; i < urlStrings.length; i++) {
                listdata.add(urlStrings[i].toString());
            }
        }

        Log.d("Data : ",listdata.toString());

    }
}//Main Method
