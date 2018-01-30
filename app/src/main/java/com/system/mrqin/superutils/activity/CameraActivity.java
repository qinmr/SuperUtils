package com.system.mrqin.superutils.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.system.mrqin.commonutil.MPermissionUtils;
import com.system.mrqin.commonutil.MyCameraUtil;
import com.system.mrqin.superutils.R;

import java.io.File;

/**
 * Created by mrqin on 2018/1/29 15.
 * E-Mail Address：mrqinjie@qq.com
 */
public class CameraActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private final int ACT_GALLERY = 0;
    private final int ACT_CAMERA = 1;
    private final int ACT_CROP = 2;
    private final int ACT_PERMISSION = 3;
    private Context mContext;
    private Uri pictureUri = null;
    private String filePath = Environment.getExternalStorageDirectory() + File.separator + "DICM" + File.separator;
    private ImageView imgCamera;
    private ImageView imgGallery;
    private ImageView imgCrop;

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, CameraActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mContext = this;
        imgCamera = (ImageView) findViewById(R.id.img_camera);
        imgGallery = (ImageView) findViewById(R.id.img_gallery);
        imgCrop = (ImageView) findViewById(R.id.img_crop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.camera_util);
        //设置返回键可用
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        requestBasicPermission();
    }

    /**
     * 基本权限管理
     */
    private final String[] BASIC_PERMISSIONS = new String[]{
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    private void requestBasicPermission() {
        //功能归类分区方法，必须调用>>>>>>>>>>
        MPermissionUtils.requestPermissionsResult(this, ACT_PERMISSION, BASIC_PERMISSIONS, new MPermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied() {
                MPermissionUtils.showTipsDialog(mContext);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void startCamera(View view) {
        startCamera();
    }

    private void startCamera() {
        //创建图片的名称
        String fileName = MyCameraUtil.createFileType(".jpg");
        //创建图片的文件
        MyCameraUtil.createFile(filePath, fileName);
        File file = new File(filePath, fileName);
        pictureUri = Uri.fromFile(file);
        MyCameraUtil.openCamera(pictureUri);
        Intent intent = MyCameraUtil.openCamera(pictureUri);
        startActivityForResult(intent, ACT_CAMERA);
    }

    public void startGallery(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case ACT_GALLERY:
//                galleryBack(data.getData());
                MyCameraUtil.updateSystemMedia(mContext);
                break;
            case ACT_CAMERA:
//                startCrop(pictureUri);
                showImg(pictureUri);
                MyCameraUtil.updateSystemMedia(mContext);
                break;
            case ACT_CROP:
                cropBack(pictureUri);
                MyCameraUtil.updateSystemMedia(mContext);
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startCrop(Uri inUri) {
        Intent intent = MyCameraUtil.cropPicture(inUri, pictureUri);
        startActivityForResult(intent, ACT_CROP);
    }

    private void showImg(Uri inUri) {
        if (inUri == null) {
            return;
        }
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(inUri));
            imgCrop.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), inUri);
//            imgCrop.setImageBitmap(bitmap1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void cropBack(Uri inUri) {
        if (inUri == null) {
            return;
        }
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(inUri));
            imgCrop.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
