package cn.bocweb.visainterview.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * 权限
 * Created by fcp on 2016/8/8.
 */
public class PermissionUtils {
    public static final int EXTERNAL_STORAGE_REQ_CODE = 10 ;
    public static final int PERMISSIONS_REQUEST_CAMERA_FOR_PHOTO =11;
    public static final int PERMISSIONS_REQUEST_CAMERA_FOR_VIDEO =12;
    public static final int PERMISSIONS_REQUEST_LOCATION =13;
    public static final int PERMISSIONS_REQUEST_PHONE=14;

    public static boolean requestPermissionWriteExternalStorage(Activity mActivity ){
        //判断当前Activity是否已经获得了该权限
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //如果App的权限申请曾经被用户拒绝过，就需要在这里跟用户做出解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(mActivity,"please give me the permission",Toast.LENGTH_SHORT).show();
            } else {
                //进行权限请求
                ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_REQ_CODE);
            }
            return false;
        }
        return true;
    }

    public static boolean requestPermissionCameraForPhoto(Fragment mFragment){
        if (ContextCompat.checkSelfPermission(mFragment.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            mFragment.requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA_FOR_PHOTO);
            //判断是否需要 向用户解释，为什么要申请该权限
            //mFragment.shouldShowRequestPermissionRationale( Manifest.permission.READ_CONTACTS);
            return false;
        }
        return true;
    }

    public static boolean requestPermissionCameraForVideo(Fragment mFragment){
        if (ContextCompat.checkSelfPermission(mFragment.getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(mFragment.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            mFragment.requestPermissions( new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, PERMISSIONS_REQUEST_CAMERA_FOR_VIDEO);
            //判断是否需要 向用户解释，为什么要申请该权限
            //mFragment.shouldShowRequestPermissionRationale( Manifest.permission.READ_CONTACTS);
            return false;
        }
        return true;
    }

    public static boolean requestPermissionLocation(Activity mActivity) {
        // Verify that all required contact permissions have been granted.
        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // Contacts permissions have not been granted.
            //请求权限
            ActivityCompat.requestPermissions(mActivity, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE
            }, PERMISSIONS_REQUEST_LOCATION);
            return false;
        } else {
            // Contact permissions have been granted. Show the contacts fragment.
            return true;
        }
    }

    public static boolean requestPermissionPhone(Activity mActivity){
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSIONS_REQUEST_PHONE);
            //判断是否需要 向用户解释，为什么要申请该权限
            ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.READ_PHONE_STATE);
            return false;
        }
        return true;
    }

    public static boolean verifyPermissions(int[] grantResults) {
        // At least one result must be checked.
        if(grantResults == null || grantResults.length < 1){
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


}
