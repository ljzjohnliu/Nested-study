package com.study.white;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.study.white.adapter.SimpleTestAdapter;
import com.study.white.view.MyNestedScrollView;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    MyNestedScrollView nsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        nsv = findViewById(R.id.nsv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new SimpleTestAdapter());

//        nsv.arrowScroll(View.OVER_SCROLL_ALWAYS);
        nsv.setNestedScrollingEnabled(true);

        final View rootView = findViewById(android.R.id.content);

        nsv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                nsv.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                View topView1 = findViewById(R.id.top_1);
                View topView2 = findViewById(R.id.top_2);


                nsv.setMyScrollHeight(topView1.getHeight());
                nsv.scrollTo(0, topView1.getHeight());
                int rvNewHeight = nsv.getHeight() - topView2.getHeight();

                rv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, rvNewHeight));

            }
        });
    }
}