package com.example.l10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button haku;
    EditText url;
    String osoite;
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        haku = (Button)findViewById(R.id.button);
        url = (EditText)findViewById(R.id.editText);
        web = (WebView)findViewById(R.id.webview);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("http://google.com");
        url.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((keyEvent != null ) || (i == EditorInfo.IME_ACTION_DONE)) {
                    web.getSettings().setJavaScriptEnabled(true);
                    osoite = url.getText().toString();
                    web.loadUrl("http://"+osoite);
                    haku.performClick();
                }
                return false;
            }
        });
    }
    public void hae(View v){

        web.getSettings().setJavaScriptEnabled(true);
        osoite = url.getText().toString();
        web.loadUrl("http://"+osoite);
        url.setText("");

        //sulje näppäimistö
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) { //tarttee try...catch koska kaatuu muuuten jos ei ole näppis auki
            System.out.println("oopps");
        }
    }
    public void refresh(View v){
        web.getSettings().setJavaScriptEnabled(true);
        web.reload();


    }
}
