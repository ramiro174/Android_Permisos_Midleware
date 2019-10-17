package com.example.permisos;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import java.util.concurrent.Callable;

public class MidlewarePermiso {

   private static Callable<Integer> t;

    public static void handle(Activity ac, int requestCode, Callable<Integer> next) {
        try {
            t=next;
            if (ac.checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ac.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, requestCode);
                return;
            }
            t.call();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
