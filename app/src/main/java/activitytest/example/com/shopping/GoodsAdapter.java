package activitytest.example.com.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 19254 on 2018/4/7.
 */

public class GoodsAdapter extends ArrayAdapter<Goods> {
    private int resourceID;
    public GoodsAdapter(Context context, int textViewResourceID, List<Goods>objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    public View getView(int position, View converView, ViewGroup parent){
        Goods good = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);

        ImageView head  =  (ImageView)view.findViewById(R.id.user_img);
        TextView user_name = (TextView)view.findViewById(R.id.user_name);
        TextView price = (TextView)view.findViewById(R.id.price);
        ImageView goods_img = (ImageView)view.findViewById(R.id.goods_img);

        head.setImageResource(good.head);
        user_name.setText(good.user_name);
        price.setText(String.valueOf(good.price));
        goods_img.setImageResource(good.imageId);
        return view;
    }
}
