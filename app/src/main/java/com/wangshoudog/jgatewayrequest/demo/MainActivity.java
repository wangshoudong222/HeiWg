package com.wangshoudog.jgatewayrequest.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wangshoudog.jgatewayrequest.R;
import com.wangshoudog.jgatewayrequest.annotation.Service;
import com.wangshoudog.jgatewayrequest.bean.BusinessBean;
import com.wangshoudog.jgatewayrequest.network.WgSubscriber;

public class MainActivity extends AppCompatActivity {

    @Service
    private AppDataApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //发送请求
        api.getData().subscribe(new WgSubscriber<BusinessBean>(this) {
            @Override
            public void onSuccess(BusinessBean businessBean) {

            }
        });

    }

}
