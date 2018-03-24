package com.system.mrqin.commonutil.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.system.mrqin.commonutil.logs.KLog;
import com.system.mrqin.commonutil.net.http.Https;
import com.system.mrqin.commonutil.net.http.OkHttpUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;


/**
 * 基础Application
 *
 * @author Lemon
 * @use extends BaseApplication 或 在你的Application的onCreate方法中BaseApplication.init(this);
 * @see #init
 */
public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";

    private static Application instance;
    private static Context mContext;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        init(this);
    }

    /**
     * 初始化方法
     *
     * @param application
     * @must 调用init方法且只能调用一次，如果extends BaseApplication会自动调用
     */
    private void init(Application application) {
        instance = application;
        initOkHttp();
    }

    /**
     * @Description 初始化OkHttp
     */
    private void initOkHttp() {
        File cache = getExternalCacheDir();
        int cacheSize = 50 * 1024 * 1024;

        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(mContext));
        Https.SSLParams sslParams = Https.getSslSocketFactory(null, null, null);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .pingInterval(20, TimeUnit.SECONDS)
                .cache(new Cache(cache.getAbsoluteFile(), cacheSize))
                .cookieJar(cookieJar)
                .retryOnConnectionFailure(true)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                //https配置
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(sLoggingInterceptor)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * 打印返回的json数据拦截器
     */
    private static final Interceptor sLoggingInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            Buffer requestBuffer = new Buffer();
            if (request.body() != null) {
                request.body().writeTo(requestBuffer);
            } else {
                KLog.d("LogTAG", "request.body() == null");
            }
            //打印url信息
            KLog.w(request.url() + (request.body() != null ? "?" + parseParams(request.body(), requestBuffer) : ""));
            final Response response = chain.proceed(request);

            return response;
        }
    };

    @NonNull
    private static String parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
    }

}
