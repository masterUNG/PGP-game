package anuson.komkid.permitgeographypro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ShowDetailByUser extends AppCompatActivity {

    //Explicit
    private String titleString, textString, startString, endString,
            statusString, urlPic1String, urlPic2String,
            post_idString, mem_u_idString;

    private TextView titleTextView, textView, startTextView,
            endTextView, statusTextView;

    private ImageView pic1ImageView, pic2ImageView;

    private Button button, orderButton;

    private String[] loginStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_by_user);
        //Bind Widget
        bindWidget();

        //Setup
        loginStrings = getIntent().getStringArrayExtra("Login");

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

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmOrder();

            }   // onClick
        });


    }   // Main Method

    private void confirmOrder() {

        int i = Integer.parseInt(statusString);
        if (i != 0) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(ShowDetailByUser.this, "ไม่สามารถจองได้",
                    "สินค้าอยู่ในสภาวะ " + showStatus(statusString));
        } else {

            sureOrder();

        }

    }   // confirmOrder

    private void sureOrder() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ShowDetailByUser.this);
        builder.setCancelable(false);
        builder.setTitle("การจองสินค้า");
        builder.setMessage("เงื่อนไขการจอง");
        builder.setIcon(R.drawable.dule_icon);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                saveValueToServer();
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }

    private void saveValueToServer() {

        post_idString = getIntent().getStringExtra("idPost");
        mem_u_idString = loginStrings[0];

        Log.d("21decV3", "post_idString ==> " + post_idString);
        Log.d("21decV3", "mem_u_idString ==> " + loginStrings[0]);

        try {

            AddServer addServer = new AddServer(ShowDetailByUser.this,
                    post_idString, mem_u_idString);
            addServer.execute();

            Log.d("21decV3", "Result addServer ==> " + addServer.get());

            EditStatus editStatus = new EditStatus(ShowDetailByUser.this,
                    post_idString, "1");
            editStatus.execute();

            Log.d("21decV3", "Result editStatus ==> " + editStatus.get());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }   // saveValueToServer

    private void showView() {

        titleTextView.setText(titleString);
        textView.setText(textString);
        startTextView.setText(startString);
        endTextView.setText(endString);
        statusTextView.setText(showStatus(statusString));

        Picasso.with(ShowDetailByUser.this)
                .load(urlPic1String).into(pic1ImageView);
        Picasso.with(ShowDetailByUser.this)
                .load(urlPic2String).into(pic2ImageView);

    }

    private String showStatus(String statusString) {

        String[] strings = new String[]{"กำลังขาย", "จอง", "สิ้นสุด"};
        int i = Integer.parseInt(statusString);

        return strings[i];
    }

    private void bindWidget() {

        titleTextView = (TextView) findViewById(R.id.textView30);
        textView = (TextView) findViewById(R.id.textView31);
        startTextView = (TextView) findViewById(R.id.textView32);
        endTextView = (TextView) findViewById(R.id.textView33);
        statusTextView = (TextView) findViewById(R.id.textView34);
        pic1ImageView = (ImageView) findViewById(R.id.imageView10);
        pic2ImageView = (ImageView) findViewById(R.id.imageView11);
        button = (Button) findViewById(R.id.button10);
        orderButton = (Button) findViewById(R.id.button6);


    }

}   // Main Class
