package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

public class Menu_farmer_3 extends Activity {

    //Explicit
    private ListView listView;
    private String[] loginStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_farmer_3);

        //Bind Widget
        listView = (ListView) findViewById(R.id.livPost);

        //Get Value From Intent
        loginStrings = getIntent().getStringArrayExtra("Login");

        try {

            SynPost synPost = new SynPost(Menu_farmer_3.this, loginStrings[0]);
            synPost.execute();

            String strJSON = synPost.get();
            Log.d("27novV3", "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);

            final String[] titleStrings = new String[jsonArray.length()];
            final String[] endStrings = new String[jsonArray.length()];
            final String[] statusShowStrings = new String[jsonArray.length()];
            final String[] statusStrings = new String[jsonArray.length()];
            final String[] textStrings = new String[jsonArray.length()];
            final String[] startStrings = new String[jsonArray.length()];
            final String[] pic1Strings = new String[jsonArray.length()];
            final String[] pic2Strings = new String[jsonArray.length()];


            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                titleStrings[i] = jsonObject.getString("post_tiltle");
                endStrings[i] = jsonObject.getString("post_data_end");
                statusStrings[i] = jsonObject.getString("status_reserv_id");
                statusShowStrings[i] = showStatus(statusStrings[i]);
                textStrings[i] = jsonObject.getString("post_text");
                startStrings[i] = jsonObject.getString("post_data_ster");
                pic1Strings[i] = jsonObject.getString("post_pic");
                pic2Strings[i] = jsonObject.getString("post_pic_two");
                Log.d("testtest", titleStrings[i].toString() + " เว้น " + String.valueOf(i));

            }   // for

            MyPostAdapter myPostAdapter = new MyPostAdapter(Menu_farmer_3.this,
                    titleStrings, endStrings, statusShowStrings);
            listView.setAdapter(myPostAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(Menu_farmer_3.this, ShowDetailActivity.class);
                    intent.putExtra("post_tiltle", titleStrings[i]);
                    intent.putExtra("post_data_end", endStrings[i]);
                    intent.putExtra("status_reserv_id", statusStrings[i]);
                    intent.putExtra("post_text", textStrings[i]);
                    intent.putExtra("post_data_ster", startStrings[i]);
                    intent.putExtra("post_pic", pic1Strings[i]);
                    intent.putExtra("post_pic_two", pic2Strings[i]);
                    startActivity(intent);


                }
            });

        } catch (Exception e) {
            Log.d("27novV3", "e menu3 ==> " + e.toString());
        }

    }   // Main Method

    private String showStatus(String statusString) {

        String[] strings = new String[]{"กำลังขาย", "จอง", "สิ้นสุด"};
        int i = Integer.parseInt(statusString);

        return strings[i];
    }

}   // Main Class