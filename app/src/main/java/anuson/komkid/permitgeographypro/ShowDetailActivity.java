package anuson.komkid.permitgeographypro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ShowDetailActivity extends AppCompatActivity {

    //Explicit
    private String titleString, textString, startString, endString,
            statusString, urlPic1String, urlPic2String;

    private TextView titleTextView, textView, startTextView,
            endTextView, statusTextView;

    private ImageView pic1ImageView, pic2ImageView;

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        //Bind Widget
        bindWidget();

        //Get Value From Intent
        titleString = getIntent().getStringExtra("post_tiltle");
        textString = getIntent().getStringExtra("post_text");
        startString = getIntent().getStringExtra("post_data_ster");
        endString = getIntent().getStringExtra("post_data_end");
        statusString = getIntent().getStringExtra("status_reserv_id");
        urlPic1String = getIntent().getStringExtra("post_pic");
        urlPic2String = getIntent().getStringExtra("post_pic_two");

        //Show View
        showView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }   // Main Method

    private void showView() {

        titleTextView.setText(titleString);
        textView.setText(textString);
        startTextView.setText(startString);
        endTextView.setText(endString);
        statusTextView.setText(showStatus(statusString));

        Picasso.with(ShowDetailActivity.this)
                .load(urlPic1String).into(pic1ImageView);
        Picasso.with(ShowDetailActivity.this)
                .load(urlPic2String).into(pic2ImageView);

    }

    private String showStatus(String statusString) {

        String[] strings = new String[]{"กำลังขาย", "จอง", "สิ้นสุด"};
        int i = Integer.parseInt(statusString);

        return strings[i];
    }

    private void bindWidget() {

        titleTextView = (TextView) findViewById(R.id.textView12);
        textView = (TextView) findViewById(R.id.textView15);
        startTextView = (TextView) findViewById(R.id.textView19);
        endTextView = (TextView) findViewById(R.id.textView21);
        statusTextView = (TextView) findViewById(R.id.textView22);
        pic1ImageView = (ImageView) findViewById(R.id.imageView5);
        pic2ImageView = (ImageView) findViewById(R.id.imageView7);
        button = (Button) findViewById(R.id.button5);


    }

}   // Main Class
