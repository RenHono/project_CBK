package xyz.renhono.project_cbk.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import xyz.renhono.project_cbk.R;
import xyz.renhono.project_cbk.activity.CopyActivity;
import xyz.renhono.project_cbk.activity.FavoActivity;
import xyz.renhono.project_cbk.activity.SearchActivity;
import xyz.renhono.project_cbk.activity.SendActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlideFragment extends Fragment {

    private ImageView ivback;
    private EditText etsea;
    private ImageView ivsea;
    private DrawerLayout drawerLayout;

    private TextView tvbanquan;
    private TextView tvfankui;
    private TextView tvtea;
    private TextView tvfavo;
    private ImageView ivbackhome;


    public SlideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_slide, container, false);

        ivbackhome = (ImageView) view.findViewById(R.id.ivbackhome);

        ivbackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction("xyz.renhono.project_cbk.mainhome");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });





        tvfavo = (TextView) view.findViewById(R.id.tvmyfavo);
        tvbanquan = (TextView) view.findViewById(R.id.tvbanquan);
        tvfankui = (TextView) view.findViewById(R.id.tvfankui);
        tvtea = (TextView) view.findViewById(R.id.tvtea);

        ivback = (ImageView) view.findViewById(R.id.ivback);
        etsea = (EditText) view.findViewById(R.id.etguanjianzi);
        ivsea = (ImageView) view.findViewById(R.id.ivsearch);
        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.activity_main);


        tvfavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FavoActivity.class);


                startActivity(intent);

            }
        });

        tvtea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), SearchActivity.class);

                intent.putExtra("gjz", "茶");


                startActivity(intent);
            }
        });


        tvbanquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CopyActivity.class);
                startActivity(intent);
            }
        });

        tvfankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SendActivity.class);
                startActivity(intent);
            }
        });

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                drawerLayout.closeDrawer(Gravity.RIGHT);

//                drawerLayout.closeDrawers();
            }
        });

        ivsea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gjz = etsea.getText().toString();


//                Bundle bundle = new Bundle();
//
//                bundle.putString("guanjianzi", gjz);

                if (!gjz.isEmpty()) {

                    Intent intent = new Intent(getActivity(), SearchActivity.class);

                    intent.putExtra("gjz", gjz);

                    startActivity(intent);


                }

            }
        });


        return view;


    }

}
