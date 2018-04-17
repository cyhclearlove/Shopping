package activitytest.example.com.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import activitytest.example.com.shopping.Mes;

/**
 * Created by 19254 on 2018/4/7.
 */

public class MesAdapter extends ArrayAdapter<Mes> {
    private int resourceID;
    public MesAdapter(Context context, int textViewResourceID, List<Mes> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    public View getView(int position, View converView, ViewGroup parent){
        Mes mes = (Mes) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);

        ImageView head  =  (ImageView)view.findViewById(R.id.mes_item_head);
        TextView user_name = (TextView)view.findViewById(R.id.mes_item_name);
        TextView mes_item = (TextView)view.findViewById(R.id.mes_item_mes);
        ImageView goods_img = (ImageView)view.findViewById(R.id.mes_item_img);

        head.setImageResource(mes.mes_head);
        user_name.setText(mes.mes_name);
        mes_item.setText(mes.mes_mes);
        goods_img.setImageResource(mes.mes_img);
        return view;
    }
}
