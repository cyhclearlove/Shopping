package activitytest.example.com.shopping;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 19254 on 2018/4/8.
 */

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.ViewHolder> {

    private List<Sort> mSortList;
    static  class ViewHolder extends RecyclerView.ViewHolder{

        ImageView sortImg;
        TextView sortName;
        public ViewHolder(View view){
            super(view);
            sortImg = (ImageView)view.findViewById(R.id.sort_img);
            sortName = view.findViewById(R.id.sort_name);
        }
    }
    public SortAdapter(List<Sort> sortList){
           mSortList = sortList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sort_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(ViewHolder holder,int position){
        Sort sort = mSortList.get(position);
        holder.sortImg.setImageResource(sort.img);
        holder.sortName.setText(sort.name);
    }
    public int getItemCount(){
        return mSortList.size();
    }
}
