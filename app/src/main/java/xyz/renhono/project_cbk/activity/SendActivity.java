package xyz.renhono.project_cbk.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import xyz.renhono.project_cbk.MainActivity;
import xyz.renhono.project_cbk.R;

public class SendActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageView ivbackhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        imageView = (ImageView) findViewById(R.id.ivback);
        ivbackhome = (ImageView) findViewById(R.id.ivbackhome);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        ivbackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction("xyz.renhono.project_cbk.mainhome");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

    }
}
