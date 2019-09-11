package cn.com.fyl.learn.mysoftkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.com.fyl.learn.mysoftkeyboard.view.MySoftKeyboardEditext;

public class MainActivity extends AppCompatActivity{

    private MySoftKeyboardEditext softKeyboardEditext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        softKeyboardEditext=(MySoftKeyboardEditext)findViewById(R.id.password_edit);
    }

}
