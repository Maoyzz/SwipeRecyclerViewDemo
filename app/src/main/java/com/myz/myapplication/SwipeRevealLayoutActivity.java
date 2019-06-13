package com.myz.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.ArrayList;
import java.util.List;

public class SwipeRevealLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_reveal_layout);
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        MRv adapter = new MRv(list);
        rv.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.img){
                    adapter.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

        private Context context;
        private List<String> data;
        private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

        public RecyclerviewAdapter(Context context, List<String> data){
            this.context = context;
            this.data = data;
            viewBinderHelper.setOpenOnlyOne(true);
        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview2,parent,false);
            final SwipeRevealLayout sw = view.findViewById(R.id.sw_layout);
            view.findViewById(R.id.img).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sw.close(true);
                }
            });
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            viewBinderHelper.bind((SwipeRevealLayout) (holder.itemView.findViewById(R.id.sw_layout)),position+"");
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            private TextView name;

            public ViewHolder(View itemView) {
                super(itemView);
//                name = itemView.findViewById(R.id.name);

            }
        }
    }

    class MRv extends BaseQuickAdapter<String,BaseViewHolder>{
        private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

        public MRv(@Nullable List<String> data) {
            super(R.layout.item_recyclerview2,data);
            viewBinderHelper.setOpenOnlyOne(true);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            viewBinderHelper.bind((SwipeRevealLayout)(helper.getView(R.id.sw_layout)),item);
            helper.addOnClickListener(R.id.img);
        }
    }
}
