package com.myz.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipeRecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRv = findViewById(R.id.rv);
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        mRv.setLayoutManager(new LinearLayoutManager(this));
        // 设置监听器。

// 创建菜单：
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {

                SwipeMenuItem deleteItem = new SwipeMenuItem(MainActivity.this).setText("wocao").setImage(R.mipmap.ic_launcher);// 各种文字和图标属性设置。
                SwipeMenuItem deleteItem2 = new SwipeMenuItem(MainActivity.this).setImage(R.mipmap.ic_launcher);// 各种文字和图标属性设置。
                rightMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。
                rightMenu.addMenuItem(deleteItem2);
                // 注意：哪边不想要菜单，那么不要添加即可。
            }
        };
        mRv.setSwipeMenuCreator(mSwipeMenuCreator);



        OnItemMenuClickListener mItemMenuClickListener = new OnItemMenuClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();

                // 左侧还是右侧菜单：
                int direction = menuBridge.getDirection();
                // 菜单在Item中的Position：
                int menuPosition = menuBridge.getPosition();
                Toast.makeText(MainActivity.this,"click",Toast.LENGTH_SHORT).show();
            }
        };
        mRv.setOnItemMenuClickListener(mItemMenuClickListener);
        mRv.setAdapter(new RecyclerviewAdapter(this,list));

    }
    class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

        private Context context;
        private List<String> data;

        public RecyclerviewAdapter(Context context, List<String> data){
            this.context = context;
            this.data = data;

        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

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

}

