package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;


public class Menu_user_2 extends Activity {

    private String[] userLoginStrings, idReservStrings, mem_idStrings;
    private ListView listView;
    private String[] columnReservStrings = new String[]{
            "reserv_id",
            "post_id",
            "mem_u_id"};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_user_2);

        listView = (ListView) findViewById(R.id.livReserv_by_user);

        userLoginStrings = getIntent().getStringArrayExtra("Login");
        for (int i = 0; i < userLoginStrings.length; i++) {
            Log.d("18JanV1", "userLogin" + i + "]=" + userLoginStrings[i]);
        }//for

        try {
            SysReserv sysReserv = new SysReserv(Menu_user_2.this, userLoginStrings[0]);
            sysReserv.execute();
            String stringreserv = sysReserv.get();
            Log.d("18JanV5", "JSON==> " + stringreserv);

            idReservStrings = new String[columnReservStrings.length];

            JSONArray jsonArray = new JSONArray(stringreserv);
            for (int a = 0; a < jsonArray.length(); a += 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(a);

                for (int j = 0; j < columnReservStrings.length; j += 1) {
                    idReservStrings[j] = jsonObject.getString(columnReservStrings[j]);
                    Log.d("19JanV1", "colum ==>" + idReservStrings[j]);
                }

                try {
                    SynPost_post_id synPost_post_id = new SynPost_post_id(Menu_user_2.this, idReservStrings[1]);
                    synPost_post_id.execute();
                    String strJSON_postid = synPost_post_id.get();
                    Log.d("18JanV2", "JSON==> " + strJSON_postid);

                    JSONArray jsonArray1 = new JSONArray(strJSON_postid);

                    final String[] titleStrings = new String[jsonArray.length()];
                    final String[] endStrings = new String[jsonArray.length()];
                    final String[] statusShowStrings = new String[jsonArray.length()];
                    final String[] statusStrings = new String[jsonArray.length()];
                    final String[] textStrings = new String[jsonArray.length()];
                    final String[] startStrings = new String[jsonArray.length()];
                    final String[] pic1Strings = new String[jsonArray.length()];
                    final String[] pic2Strings = new String[jsonArray.length()];

                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(i);

                        titleStrings[i] = jsonObject1.getString("post_tiltle");
                        endStrings[i] = jsonObject1.getString("post_data_end");
                        statusStrings[i] = jsonObject1.getString("status_reserv_id");
                        statusShowStrings[i] = showStatus(statusStrings[i]);
                        textStrings[i] = jsonObject1.getString("post_text");
                        startStrings[i] = jsonObject1.getString("post_data_ster");
                        pic1Strings[i] = jsonObject1.getString("post_pic");
                        pic2Strings[i] = jsonObject1.getString("post_pic_two");
                        Log.d("testtest", titleStrings[i].toString() + " เว้น " + String.valueOf(i));

                    }//for
                    MyReservListview myReservListview = new MyReservListview(Menu_user_2.this,
                            titleStrings, endStrings, statusStrings);
                    listView.setAdapter(myReservListview);

                } catch (Exception e) {
                    Log.d("27novV3", "e menu3 ==> " + e.toString());
                }

            }//for


        } catch (Exception e) {
            Log.d("18JanV5", "e menu3 ==> " + e.toString());
        }//try

    }//onCreate

    private String showStatus(String statusString) {

        String[] strings = new String[]{"กำลังขาย", "จอง", "สิ้นสุด"};
        int i = Integer.parseInt(statusString);

        return strings[i];
    }
}
