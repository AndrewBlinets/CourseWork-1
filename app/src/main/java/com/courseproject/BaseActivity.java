package com.courseproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BaseActivity extends Activity implements View.OnClickListener {

    private final int LAYOUT = R.layout.activity_base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showInformation:
                Intent intent = new Intent(this, MainPageUser.class);
                this.startActivity(intent);
                break;
        }
    }
}
