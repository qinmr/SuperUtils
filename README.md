
<h2>CommonUtil:</h2>
<a href="https://github.com/qinmr/SuperUtils/CommonUtil/com/system/mrqin/commonutil/log/LogUtil.java">LogUtil：</a>

    自定义的LogUtil的使用：在自定义的application里面初始化
    LogUtil.init(BuildConfig.LOG_DEBUG);
    让在正式版本不打印日志，在debug版本有日志，还可以打印xml和json
    在项目的gradle里面配置：

    buildTypes {
            release {
                buildConfigField  "boolean", "LOG_DEBUG", "false"
                minifyEnabled true
                proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            }

            debug {
                buildConfigField  "boolean", "LOG_DEBUG", "true"
                minifyEnabled false
                proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            }
        }

<a href="https://github.com/qinmr/SuperUtils/CommonUtil/com/system/mrqin/commonutil/MpermissionUtil.java">MpermissionUtil:</a>

    请求权限处理是无回调的，如果想要回调的结果就使用setmOnPermissionListener将回调传入，并且在
     activity（fragment）的onRequestPermissionsResult将结果传回来

     @Override
     public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
     MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
     }

     showTipsDialog弹出一个提示框，在失败后进行使用

<a href="https://github.com/qinmr/SuperUtils/CommonUtil/com/system/mrqin/commonutil/MD5.java">MD5:</a>

    MD5加密工具

<a href="https://github.com/qinmr/SuperUtils/CommonUtil/com/system/mrqin/commonutil/MD5.java">ActivityManger:</a>

    应用程序Activity管理工具类，用于Activity的管理和应用程序的退出

<a href="https://github.com/qinmr/SuperUtils/CommonUtil/com/system/mrqin/commonutil/InstallUtil.java">InstallUtil:</a>

    程序的安装检测








