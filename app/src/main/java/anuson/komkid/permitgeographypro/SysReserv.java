package anuson.komkid.permitgeographypro;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class SysReserv extends AsyncTask<Void,Void,String>{

    private Context context;
    private static final String urlJSON = "http://swiftcodingthai.com/gam/php_get_reserv.php";

    public SysReserv (Context context) {
        this.context = context;
    }
    @Override
    protected String doInBackground(Void... voids) {
        try{
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlJSON).build();
            Response response = okHttpClient.newCall(request).execute();

            return  response.body().string();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
