package com.example.velo77.obj;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.velo77.PanierActivity;
import com.example.velo77.R;
import com.example.velo77.request.LoadImages;

import java.util.List;

public class PanierAdapter extends BaseAdapter {
    private Context context;
    private List<Item> data;

    public PanierAdapter(Context c, List<Item> d) {
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
            convertView = inflater.inflate(R.layout.card_panier, null);

        }
        Item current = (Item) getItem(position);


        TextView tv_name = convertView.findViewById(R.id.card_name);
        TextView tv_price = convertView.findViewById(R.id.card_price);
        TextView tv_description = convertView.findViewById(R.id.card_description);
        TextView tv_number = convertView.findViewById(R.id.card_number);
        TextView tv_delete = convertView.findViewById(R.id.card_delete);

        LinearLayout LL = convertView.findViewById(R.id.base_id_card);

        ImageView iv = convertView.findViewById(R.id.imgCard);



        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder( context )
                        .setTitle("Supprimer du panier")
                        .setMessage("voulez-vous supprimer cet article au panier ?")
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ((PanierActivity)context).deletePanier(current.getId());

                            }
                        })

                        .setCancelable(false)
                        .show();

            }
        });


        tv_name.setText(current.getName());
        tv_price.setText(current.getPrice() + " €");
        tv_description.setText(current.getDescription());
        tv_number.setText("x" + current.getNumber());
        updateImage(iv, current.getId());

        return convertView;
    }


    public void updateImage(ImageView iv, String item) {


        String url = "http://10.0.2.2/velo77/backend/img-item/item_" + item + ".png";
        new LoadImages(iv).execute(url);
    }
}

