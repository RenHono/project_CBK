package xyz.renhono.project_cbk;

import android.app.ActionBar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import xyz.renhono.project_cbk.adapter.FragPagerAD;
import xyz.renhono.project_cbk.adapter.TabSildePagerAD;
import xyz.renhono.project_cbk.fragment.List_Fragment;
import xyz.renhono.project_cbk.fragment.Main_Fragment;
import xyz.renhono.project_cbk.fragment.SlideFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private String[] tabs = {"头条", "百科", "资讯", "经营", "数据"};

    private ViewPager viewPager;
    private TabSildePagerAD tabSildePagerAD;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragPagerAD fragPagerAD;
    private List<String> titleList = new ArrayList<>();


    private String slideShowUrl = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getSlideshow";

    private String urlHead = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getHeadlines";
    private String urlBaike = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getListByType&type=16";
    private String urlZixun = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getListByType&type=52";
    private String urlJingying = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getListByType&type=53";
    private String urlShuju = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getListByType&type=54";
    private ImageView ivmore;

    private View fragmainslide;
    private DrawerLayout drawerLayout;
    private Fragment fragmentmainslide;
    private ImageView ivbackxx;
    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {

                    //如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出茶百科", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    //更新firstTime
                    return true;
                } else {
                    //两次按键小于2秒时，退出应用
                    System.exit(0);
                }
                break;
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivmore = (ImageView) findViewById(R.id.ivmore);
        tabLayout = (TabLayout) findViewById(R.id.tlxx);
        fragmainslide = findViewById(R.id.fragmainslide);
        viewPager = (ViewPager) findViewById(R.id.vpxx);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);


        ivbackxx = (ImageView) getSupportFragmentManager().findFragmentById(R.id.fragmainslide).getView().findViewById(R.id.ivback);

        ivbackxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                drawerLayout.closeDrawers();
            }
        });


        ivmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(fragmainslide);

            }
        });

        for (int i = 0; i < tabs.length; i++) {


            titleList.add(tabs[i]);
            //           tabLayout.addTab(tabLayout.newTab().setText(tabs[i]));

        }

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        Bundle bundle = new Bundle();

        bundle.putString("slide", slideShowUrl);
        bundle.putString("url1", urlHead);
        bundle.putString("url2", urlBaike);
        bundle.putString("url3", urlZixun);
        bundle.putString("url4", urlJingying);
        bundle.putString("url5", urlShuju);


        Main_Fragment main_fragment = new Main_Fragment();


        List_Fragment list_fragment2 = new List_Fragment();
        List_Fragment list_fragment3 = new List_Fragment();
        List_Fragment list_fragment4 = new List_Fragment();
        List_Fragment list_fragment5 = new List_Fragment();

        main_fragment.setArguments(bundle);

        Bundle bundle2 = new Bundle();
        Bundle bundle3 = new Bundle();
        Bundle bundle4 = new Bundle();
        Bundle bundle5 = new Bundle();
        bundle2.putString("url", urlBaike);
        bundle3.putString("url", urlZixun);
        bundle4.putString("url", urlJingying);
        bundle5.putString("url", urlShuju);

        list_fragment2.setArguments(bundle2);
        list_fragment3.setArguments(bundle3);
        list_fragment4.setArguments(bundle4);
        list_fragment5.setArguments(bundle5);


        fragmentList.add(main_fragment);
        fragmentList.add(list_fragment2);
        fragmentList.add(list_fragment3);
        fragmentList.add(list_fragment4);
        fragmentList.add(list_fragment5);

//        SlideFragment slideFragment = new SlideFragment();
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//
//        fragmentTransaction.replace(R.id.fragmainslide,slideFragment).commit();
//


        fragPagerAD = new FragPagerAD(getSupportFragmentManager(), fragmentList, titleList);

        viewPager.setAdapter(fragPagerAD);
        tabLayout.setupWithViewPager(viewPager);

    }


}
