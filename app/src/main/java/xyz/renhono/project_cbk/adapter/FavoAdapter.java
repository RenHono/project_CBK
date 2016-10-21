package xyz.renhono.project_cbk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import xyz.renhono.project_cbk.R;
import xyz.renhono.project_cbk.domain.ListData;

/**
 * Created by GT70 on 2016/10/19 0019.
 */

public class FavoAdapter extends BaseAdapter {
    private List<ListData> listDataList;
    private Context context;

    public FavoAdapter(List<ListData> listDataList, Context context) {
        this.listDataList = listDataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return listDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FavoAdapter.ViewHolder holder = null;

        if (convertView == null) {

            holder = new FavoAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view, null);

            holder.textView1 = (TextView) convertView.findViewById(R.id.tvtitle);
            holder.textView2 = (TextView) convertView.findViewById(R.id.tvdes);
            holder.textView3 = (TextView) convertView.findViewById(R.id.tvsou);
            holder.textView4 = (TextView) convertView.findViewById(R.id.tvnick);
            holder.textView5 = (TextView) convertView.findViewById(R.id.tvtime);

            holder.imageView = (ImageView) convertView.findViewById(R.id.ivtuxx);

            convertView.setTag(holder);
        } else {

            holder = (FavoAdapter.ViewHolder) convertView.getTag();

        }

        holder.textView1.setText(listDataList.get(position).getTitle());
//        holder.textView3.setText(listDataList.get(position).getSource());
        holder.textView4.setText(listDataList.get(position).getNickname());
        holder.textView5.setText(listDataList.get(position).getCreate_time());

        String sou = listDataList.get(position).getSource();
        String des = listDataList.get(position).getDescription();


        if (des.isEmpty()) {
            holder.textView2.setText("      ");

        } else {

            holder.textView2.setText(listDataList.get(position).getDescription());
        }
        if (sou.isEmpty()) {
            holder.textView3.setText("      ");

        } else {

            holder.textView3.setText(listDataList.get(position).getSource());
        }

        holder.imageView.setVisibility(View.INVISIBLE);

//        Picasso.with(context).load(R.mipmap.empty).into(holder.imageView);

        return convertView;
    }

    private class ViewHolder {

        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        ImageView imageView;


    }
}

