package activitytest.example.com.shopping;

import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * Created by 19254 on 2018/4/22.
 */

public class HorizonListviewAdapter extends BaseAdapter {
    private LayoutInflater inflater = null;
    private List<Img> imgs = new ArrayList<>();

    public HorizonListviewAdapter(Context context,List<Img> imgs){
        this.inflater = LayoutInflater.from(context);
        this.imgs = imgs;
    }
    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public Object getItem(int position) {
        return imgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position,View conterView,ViewGroup parent){
        Img img = imgs.get(position);
        conterView = inflater.inflate(R.layout.goods_listview_img, null);
        ImageView imageView= (ImageView)conterView.findViewById(R.id.goods_detail_img_item);

        imageView.setImageResource(img.img_item);
        return conterView;
    }
}
