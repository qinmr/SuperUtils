
CommonUtil:
    LogUtil：
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




