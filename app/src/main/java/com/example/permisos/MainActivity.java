package com.example.permisos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnllamar).setOnClickListener(this);
    }
    public Integer llamar() {
        it = new Intent(Intent.ACTION_CALL, Uri.parse("tel:871-176-50-75"));
        startActivity(it);
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnllamar:
                ArrayList<String>  lp=new ArrayList<String>();
                lp.add(Manifest.permission.CALL_PHONE);
                MidlewarePermiso.handle(this, lp,1 ,new Callable<Integer>() {
                    public Integer call() {
                        return llamar();
                    }
                });
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MidlewarePermiso.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
