package com.example.permisos;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class MidlewarePermiso {

    private static Callable<Integer> t;

    public static void handle(Activity ac, ArrayList<String> Permisos, int requestCode, Callable<Integer> next) {
        try {
            t = next;
            ArrayList<String> PermisosDenegados = new ArrayList<String>();
            for (String p : Permisos) {
                if (ac.checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED) {
                    PermisosDenegados.add(p);

                }
            }
            String[] pe = PermisosDenegados.toArray(new String[PermisosDenegados.size()]);
            if (pe.length >= 1) {
                ac.requestPermissions(pe, requestCode);
            }
            t.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        t.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }


}
