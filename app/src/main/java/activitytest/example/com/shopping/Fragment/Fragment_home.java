package activitytest.example.com.shopping.Fragment;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import activitytest.example.com.shopping.MainActivity;
import activitytest.example.com.shopping.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import activitytest.example.com.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_home extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public Fragment_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_home.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_home newInstance(String param1, String param2) {
        Fragment_home fragment = new Fragment_home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private List<Goods> goods = new ArrayList<>();
    private  ListView listView;
    private GoodsAdapter goodsAdapter;
    private HorizonListviewAdapter horizonListviewAdapter;
    private List<Img> imgs = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        initGoods();
        initImgs();
        horizonListviewAdapter = new HorizonListviewAdapter(getActivity(),imgs);
        goodsAdapter = new GoodsAdapter(getActivity(),goods,horizonListviewAdapter);
       // horizontalListView.setAdapter(imgAdapter);
        setListAdapter(goodsAdapter);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home , container, false);
        listView = (ListView)view.findViewById(android.R.id.list);
        FloatingActionButton floatButton = (FloatingActionButton)view.findViewById(R.id.floatButton);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),GoodsPublishActivity.class);
                startActivity(intent);
            }
        });



        //initGoods();
        //listView.setAdapter(new GoodsAdapter(getActivity(),R.layout.goods_item,goods));
        return view;
    }
    private void initGoods(){
        for(int i=0;i<10;i++) {
            Goods good = new Goods(R.drawable.head1, "cyh", 100, R.drawable.good, "轮滑鞋");
            goods.add(good);
        }
    }

    private void initImgs(){
        Img img = new Img();
        img.img_item = R.drawable.good;
        for(int i=0;i<10;i++)
            imgs.add(img);
    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      /*  if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(),GoodsDetailActivity.class);
        startActivity(intent);
    }
}
