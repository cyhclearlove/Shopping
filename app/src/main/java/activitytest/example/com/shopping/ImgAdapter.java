package activitytest.example.com.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import java.util.List;

/**
 * Created by 19254 on 2018/4/10.
 */

public class ImgAdapter extends ArrayAdapter{
    private int resourceID;
    public ImgAdapter(Context context, int textViewResourceID, List<Img>objects){
           super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    public View getView(int position, View converView, ViewGroup parent){
        Img img = (Img)getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);

        ImageView img_item = view.findViewById(R.id.goods_detail_img_item);

        img_item.setImageResource(img.img_item);
        return view;
    }
}
