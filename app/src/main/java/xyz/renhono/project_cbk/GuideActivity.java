package xyz.renhono.project_cbk;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.project_cbk.adapter.SlidePagerAD;

public class GuideActivity extends AppCompatActivity {

    private ViewPager vpguide;
    private SlidePagerAD slidePagerAD;
    private int[] guipics = {R.mipmap.slide1, R.mipmap.slide2, R.mipmap.slide3};
    private List<ImageView> imageViewList;
    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        relativeLayout = (RelativeLayout) findViewById(R.id.activity_guide);

        vpguide = (ViewPager) findViewById(R.id.vpguide);

        button = new Button(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams((int) ((displayMetrics.widthPixels / 3) * 1.1), displayMetrics.heightPixels / 10);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.topMargin = (int) ((displayMetrics.heightPixels / 10) * 2.56);
        button.setEnabled(false);
        button.setAlpha(0);

        imageViewList = new ArrayList<>();

        linearLayout = new LinearLayout(this);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                , LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.bottomMargin = 200;

        for (int i = 0; i < guipics.length; i++) {

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(guipics[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViewList.add(imageView);


            ImageView ivdot = new ImageView(this);
            ivdot.setImageResource(R.drawable.dots);
            ivdot.setEnabled(true);
            linearLayout.addView(ivdot);


        }

        linearLayout.getChildAt(0).setEnabled(false);


        slidePagerAD = new SlidePagerAD(imageViewList);
        vpguide.setAdapter(slidePagerAD);

        linearLayout.setLayoutParams(layoutParams);
        button.setLayoutParams(buttonParams);


        relativeLayout.addView(linearLayout);
        relativeLayout.addView(button);

        final Intent intent = new Intent(GuideActivity.this, MainActivity.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
                finish();
            }
        });


        vpguide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < guipics.length; i++) {


                    linearLayout.getChildAt(i).setEnabled(true);

                }

                linearLayout.getChildAt(position).setEnabled(false);

                if (position == (guipics.length - 1)) {

                    button.setEnabled(true);

                } else {
                    button.setEnabled(false);
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
