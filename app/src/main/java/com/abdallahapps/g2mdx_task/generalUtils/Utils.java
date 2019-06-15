package com.abdallahapps.g2mdx_task.generalUtils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Utils {

    public static boolean checkPermission(Context context , String permission){
        if(ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED){
            return true;
        }else {
            return false;
        }
    }
    public static void permissionGrant(Activity activity, String permission, int permissionCode){
        ActivityCompat.requestPermissions(activity,new String[]{permission},permissionCode);
    }


}
