package activitytest.example.com.shopping;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.lang.String;

import activitytest.example.com.shopping.Fragment.Fragment_class;
import activitytest.example.com.shopping.Fragment.Fragment_home;
import activitytest.example.com.shopping.Fragment.Fragment_me;
import activitytest.example.com.shopping.Fragment.Fragment_mes;

/**
 * Created by 19254 on 2018/3/27.
 */

   class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String title[] = new String[]{"首页","分类","消息","我的"};
    public MyFragmentPagerAdapter(FragmentManager f){
        super(f);
    }

    public android.support.v4.app.Fragment getItem(int position){
        if (position==0){
            return new Fragment_home();
        }else if(position==1) {
            return new Fragment_class();
        }else if(position==2){
            return new Fragment_mes();
        }else if(position==3)
            return  new Fragment_me();
        return new Fragment_home();
    }
    public int getCount(){
        return title.length;
    }
    public CharSequence getPageTitle(int posion){
        return title[posion];
    }
}
