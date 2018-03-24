package com.system.mrqin.superutils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.system.mrqin.superutils.R;
import com.system.mrqin.superutils.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */

public class EasyAdapter extends RecyclerView.Adapter<EasyAdapter.MyHolder> {

    private Context mContext;
    private List<User> users;

    public EasyAdapter(Context context) {
        mContext = context;
    }

    @Override
    public EasyAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.item_user_layout, parent));
    }

    @Override
    public void onBindViewHolder(EasyAdapter.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }

    public void clear() {
        this.users.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<User> users) {
        if (null != users) {
            this.users.addAll(users);
            notifyDataSetChanged();
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private ImageView imgHead;
        private TextView name;
        private TextView city;
        private TextView sex;
        private TextView age;
        private TextView tel;

        public MyHolder(View itemView) {
            super(itemView);
            imgHead = (ImageView) itemView.findViewById(R.id.img_head);
            name = (TextView) itemView.findViewById(R.id.name);
            city = (TextView) itemView.findViewById(R.id.city);
            sex = (TextView) itemView.findViewById(R.id.sex);
            age = (TextView) itemView.findViewById(R.id.age);
            tel = (TextView) itemView.findViewById(R.id.tel);
        }
    }
}
