package com.example.xiangnanzhang.freetaxcar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTv_zqcy, mTv_zqz;
    private ImageView iv_onfire;
//    private Button btn_refresh;
    private String MY_TAG = "image1";
    private Bitmap mBitmap;
    /*侧拉菜单*/
    private ListView mLv_filter;
    private Context mContext = this;
    private String[] filteritems = new String[]{"北京奔驰","华晨宝马","一汽奥迪","一汽大众","上海大众"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        setListener();
        doRequest();
    }



    public void onClick(View view){
        int vid = view.getId();
        Intent intent = new Intent(this, WebViewActivity.class);
        switch (vid) {
            case R.id.tv_zqcy:
                intent.putExtra("url", "http://api.firstp2p.com/help/faq/?id=768");
                startActivity(intent);
                break;
            case R.id.tv_zqz:
                intent.putExtra("url","http://www.autodutyfree.cn");
                startActivity(intent);
                break;
            case R.id.iv_onfire:
                iv_onfire.setImageResource(R.drawable.pushsettings);
                doRequest();
                break;
        }
    }

    @Override
    public void findViewById(){
        mTv_zqcy = (TextView)findViewById(R.id.tv_zqcy);
        mTv_zqz = (TextView)findViewById(R.id.tv_zqz);
        iv_onfire = (ImageView)findViewById(R.id.iv_onfire);
        mLv_filter = (ListView)findViewById(R.id.filter_list);
//        btn_refresh = (Button)findViewById(R.id.btn_refresh);
    }

    @Override
    public void setListener(){
        mTv_zqz.setOnClickListener(this);
        mTv_zqcy.setOnClickListener(this);
        iv_onfire.setOnClickListener(this);
//        btn_refresh.setOnClickListener(this);
    }

    public void doRequest(){
        if(mBitmap != null)
            mBitmap.recycle();
        String url = "http://i.imgur.com/7spzG.png";
        ImageRequest request = new ImageRequest(url,listener,0,0, Bitmap.Config.ARGB_8888,errorListener);
//        request.setShouldCache(false);
        request.setTag(MY_TAG);
        VolleySingleton.getVolleySingleton(this).getRequestQueue().cancelAll(MY_TAG);//getCache().clear();
        VolleySingleton.getVolleySingleton(this).addToRequestQueue(request);

    }

    Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
        @Override
        public void onResponse(Bitmap bitmap) {
            mBitmap = bitmap;
            iv_onfire.setImageBitmap(mBitmap);
            mBitmap.recycle();

        }
    };

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            iv_onfire.setImageResource(R.drawable.redgift_head_close);
        }
    };

    BaseAdapter myAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView != null) {
                viewHolder = (ViewHolder)convertView.getTag();
            }else {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.filter_item,parent);
                viewHolder = new ViewHolder();
            }
            viewHolder.item.setText(filteritems[position]);
            convertView.setTag(viewHolder);
            return convertView;
        }
    };

    class ViewHolder {
        private TextView item;
    }

}