package com.system.mrqin.superutils.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.system.mrqin.commonutil.BitmapUtil;
import com.system.mrqin.commonutil.FileProvider7;
import com.system.mrqin.commonutil.MPermissionUtils;
import com.system.mrqin.commonutil.MyCameraUtil;
import com.system.mrqin.commonutil.logs.KLog;
import com.system.mrqin.superutils.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by mrqin on 2018/1/29 15.
 * E-Mail Address：mrqinjie@qq.com
 */
public class CameraActivity extends BaseActivity {

    private final String TAG = getClass().getSimpleName();
    private final int ACT_GALLERY = 0;
    private final int ACT_CAMERA = 1;
    private final int ACT_CROP = 2;
    private final int ACT_PERMISSION = 3;
    private boolean CROP = false;
    private String filePath = Environment.getExternalStorageDirectory() + File.separator + "DICM" + File.separator;
    private ImageView imgCamera;
    private ImageView imgGallery;
    private ImageView imgCrop;
    private ImageView imgTest1;
    private ImageView imgTest2;
    private ImageView imgTest3;

    private Uri uriForFile;
    private String path;

    private ArrayList<Bitmap> bitmaps = new ArrayList<>();

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, CameraActivity.class));
    }

    @Override
    int getViewLayoutId() {
        return R.layout.activity_camera;
    }

    @Override
    void initViews(Bundle savedInstanceState) {
        imgCamera = (ImageView) findViewById(R.id.img_camera);
        imgGallery = (ImageView) findViewById(R.id.img_gallery);
        imgCrop = (ImageView) findViewById(R.id.img_crop);
        imgTest1 = (ImageView) findViewById(R.id.img_test1);
        imgTest2 = (ImageView) findViewById(R.id.img_test2);
        imgTest3 = (ImageView) findViewById(R.id.img_test3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(getString(R.string.camera_util), toolbar, true);
        requestBasicPermission();
    }

    @Override
    void initEvent() {

    }

    @Override
    void initData() {

    }

    @Override
    void updateViews(boolean isRefresh) {

    }

    /**
     * 基本权限管理
     */
    private final String[] BASIC_PERMISSIONS = new String[]{
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
        CROP = false;
        openCamera();
    }

    public void startGallery(View view) {
        CROP = false;
        openGallery();
    }

    public void startCameraCrop(View view) {
        CROP = true;
        openCamera();
    }

    public void startGalleryCrop(View view) {
        CROP = true;
        openGallery();
    }

    /**
     * 打开相机
     */
    private void openCamera() {
        //创建图片的名称
        String fileName = MyCameraUtil.createFileName(".png");
        //创建图片的文件夹
        MyCameraUtil.createDir(filePath);
        //创建图片的文件
        File file = new File(filePath, fileName);
        path = file.getAbsolutePath();
        uriForFile = FileProvider7.getUriForFile(mContext, file);
        Intent intent = MyCameraUtil.openCamera(uriForFile);
        startActivityForResult(intent, ACT_CAMERA);
    }

    /**
     * 打开相册
     */
    private void openGallery() {
        Intent intent = MyCameraUtil.openGallery();
        startActivityForResult(intent, ACT_GALLERY);
    }

    /**
     * 开始裁剪
     */
    private void startCrop(Uri inUri) {
        //创建图片的名称
        String fileName = MyCameraUtil.createFileName(".png");
        //创建图片的文件夹
        MyCameraUtil.createDir(filePath);
        //创建图片的文件
        File file = new File(filePath, fileName);
        Intent intent = MyCameraUtil.cropPicture(inUri, 1, 1, 180, 180);
//        path = file.getAbsolutePath();
//        outUri = FileProvider7.getUriForFile(mContext, file);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, ACT_CROP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case ACT_GALLERY:
                if (null != data) {
                    if (CROP) {
                        startCrop(data.getData());
                    } else {

                        String imgPath = getRealPathFromURI(data.getData());

                        Bitmap bitmap2 = BitmapUtil.readBitmapFromFileDescriptor(imgPath, 720, 1280);
                        logBitmapSize(bitmap2);
                        imgTest2.setImageBitmap(bitmap2);
                        bitmaps.add(bitmap2);

                        //创建图片的名称
                        String fileName = MyCameraUtil.createFileName(".png");
                        //创建图片的文件夹
                        MyCameraUtil.createDir(filePath);
                        //创建图片的文件
                        File file = new File(filePath, fileName);
                        FileOutputStream out = null;
                        try {
                            out = new FileOutputStream(file);
                            bitmap2.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.flush();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Bitmap bitmap3 = BitmapUtil.readBitmapFromFileDescriptor(file.getAbsolutePath(), 720, 1280);
                        logBitmapSize(bitmap3);
                        imgTest3.setImageBitmap(bitmap3);
                        bitmaps.add(bitmap3);


                    }
                }
                break;
            case ACT_CAMERA:
                if (null != data) {
                    if (CROP) {
                        startCrop(data.getData());
                    } else {
                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        logBitmapSize(bitmap);
                        imgCamera.setImageBitmap(bitmap);
                    }
                } else {
                    if (CROP) {
                        startCrop(uriForFile);
                    } else {
                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        logBitmapSize(bitmap);
                        imgCamera.setImageBitmap(bitmap);
                    }
                }
                break;
            case ACT_CROP:
                if (null != data) {
                    Bitmap bitmap = getBitmapFromUri(data.getData());
                    logBitmapSize(bitmap);
                    imgCrop.setImageBitmap(bitmap);
                } else {

                }
//                Bitmap bitmap = BitmapFactory.decodeFile(path);
//                logBitmapSize(bitmap);
//                imgCrop.setImageBitmap(bitmap);
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 获取contentUri的File路径
     *
     * @param contentUri
     * @return
     */
    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    /**
     * 从uri 中获取bitmap
     *
     * @param uri
     * @return
     */
    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            // 读取uri所在的图片
            return MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取bitmap的大小
    public void logBitmapSize(Bitmap bitmap) {
        KLog.i(TAG, bitmap.getRowBytes() * bitmap.getHeight() / 1024 + "/" + bitmap.getWidth() + "/" + bitmap.getHeight());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
