package activitytest.example.com.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19254 on 2018/4/22.
 */

public class GoodsAdapter extends BaseAdapter {
    private LayoutInflater inflater = null;
    private List<Goods> goods = new ArrayList<>();
    private HorizonListviewAdapter adapter;
    public GoodsAdapter(Context context,List<Goods> goods, HorizonListviewAdapter adapter){
        this.inflater = LayoutInflater.from(context);
        this.goods = goods;
        this.adapter = adapter;
    }
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return goods.get(position);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return goods.size();
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    public View getView(int position, View converView, ViewGroup parent){
        converView = inflater.inflate(R.layout.goods_item, null);

        Goods good = (Goods)getItem(position);

        ImageView head  =  (ImageView)converView.findViewById(R.id.user_img);
        TextView user_name = (TextView)converView.findViewById(R.id.user_name);
        TextView price = (TextView)converView.findViewById(R.id.price);
        HorizontalListView horizontalListView = (HorizontalListView) converView.findViewById(R.id.horizontalListView);
        // ImageView goods_img = (ImageView)view.findViewById(R.id.goods_img);

        horizontalListView.setAdapter(adapter);
        head.setImageResource(good.head);
        user_name.setText(good.user_name);
        price.setText("￥"+String.valueOf(good.price));
        //goods_img.setImageResource(good.imageId);
        return converView;
    }
}
