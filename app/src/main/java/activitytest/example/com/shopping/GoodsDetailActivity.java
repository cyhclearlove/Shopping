package activitytest.example.com.shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailActivity extends AppCompatActivity {

    private List<Img> imgs = new ArrayList<>();
    private ListView listView;
    private ImgAdapter imgAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏掉整个ActionBar
        setContentView(R.layout.goods_detail);
        initImg();
        imgAdapter = new ImgAdapter(this,R.layout.goods_detail_img,imgs);
        listView = (ListView) findViewById(R.id.list_goods_detail_img);
        listView.setAdapter(imgAdapter);
        setListViewHeightBasedOnChildren(listView);
    }
    void initImg(){
        for(int i=0;i<5;i++){
            Img img  = new Img();
            img.img_item = R.drawable.good;
            imgs.add(img);
        }

    }
    public void setListViewHeightBasedOnChildren(ListView listView){
        // 获取ListView对应的Adapter   
        ListAdapter listAdapter=listView.getAdapter();
        if(listAdapter==null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0,len=listAdapter.getCount();i<len;i++) {
            // listAdapter.getCount()返回数据项的数目   
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高   
            listItem.measure(0, 0);
            // 统计所有子项的总高度   
            Log.i("ssssssss",String.valueOf(listItem.getMeasuredHeight()));
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params=listView.getLayoutParams();
        params.height=totalHeight+(listView.getDividerHeight()*(listAdapter.getCount()-1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度   
        // params.height最后得到整个ListView完整显示需要的高度   
        listView.setLayoutParams(params);
    }
}
