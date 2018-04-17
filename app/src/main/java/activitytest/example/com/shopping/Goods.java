package activitytest.example.com.shopping;

/**
 * Created by 19254 on 2018/4/7.
 */

public class Goods {
    public String goods_name;
    public float price;
    public int imageId;
    public int head;
    public String user_name;
    public Goods(int head,String user_name,float price,int imageId,String goods_name){
        this.head = head;
        this.user_name = user_name;
        this.price = price;
        this.imageId = imageId;
        this.goods_name = goods_name;
    }
}
