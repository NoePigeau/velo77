package com.example.velo77;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private List<Item> data;

    public ItemAdapter(Context c, List<Item> d) {
        this.context = c;
        this.data = d;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.card_item, null);
        }
        Item current = (Item) getItem(position);

        TextView tv_name = convertView.findViewById(R.id.card_name);
        TextView tv_id = convertView.findViewById(R.id.card_id);

        tv_name.setText(current.getName());
        tv_id.setText(current.getId());

        return convertView;
    }
}

