package com.zjdt.dtsjwb.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zjdt.dtsjwb.R;

public class CAssetsActivity extends BaseActivity {
    /**
     * 总之打开这个activity 确实就是打开资产列表  设置一个true或者false 来开关 listener
     * @param savedInstanceState
     */
    private boolean isOpen=false;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cassets);
        isOpen=getIntent().getBooleanExtra("isopen",false);
    }
    public void initview(){
        recyclerView=f(R.id.asset_recyclerview);
    }

    public class AssetAdapter extends RecyclerView.Adapter<AssetAdapter.Vh>{


        @Override
        public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(Vh holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class Vh extends RecyclerView.ViewHolder{

            public Vh(View itemView) {
                super(itemView);
            }
        }

        public AssetAdapter() {
        }
    }



}
