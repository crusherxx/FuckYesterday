package com.example.liulanqi;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private String url = null;
    private WebView webView;
    private ProgressDialog dialog;
    private EditText text;
    private Button b1,b2,b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "欢迎使用简易浏览器_by sorgs", Toast.LENGTH_SHORT).show(); //弹出欢迎
        webView = findViewById(R.id.webview);
        webView.loadUrl("http://218.21.35.46:8990/meol/");
        webView.setWebViewClient(new WebViewClient());
        Button b1=(Button)findViewById(R.id.button);
        Button b2=(Button)findViewById(R.id.button2);
        Button b3=(Button)findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回
                webView.goBack();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新加载
                webView.reload();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //前进
                webView.goForward();
            }
        });
    }


}
