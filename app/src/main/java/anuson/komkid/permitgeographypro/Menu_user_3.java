package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


public class Menu_user_3 extends Activity{

    private ListView listView;
    private String[] loginStrings;

        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_user_3);

        loginStrings = getIntent().getStringArrayExtra("Login");

    }
}
