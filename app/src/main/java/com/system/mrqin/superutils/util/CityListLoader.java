package com.system.mrqin.superutils.util;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.system.mrqin.superutils.bean.CityInfoBean;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * 作者：liji on 2017/5/21 15:35
 * 邮箱：lijiwork@sina.com
 * QQ ：275137657
 */

public class CityListLoader {

    public static final String BUNDATA = "bundata";

    private static List<CityInfoBean> mCityListData = new ArrayList<>();

    private static List<CityInfoBean> mProListData = new ArrayList<>();

    /**
     * 解析所有的城市数据 357个数据
     */
    public List<CityInfoBean> getCityListData() {
        return mCityListData;
    }

    /**
     * 只解析省份34个数据
     */
    public List<CityInfoBean> getProListData() {
        return mProListData;
    }

    private volatile static CityListLoader instance;

    private CityListLoader() {

    }

    /**
     * 单例模式
     *
     * @return
     */
    public static CityListLoader getInstance() {
        if (instance == null) {
            synchronized (CityListLoader.class) {
                if (instance == null) {
                    instance = new CityListLoader();
                }
            }
        }
        return instance;
    }

    /**
     * 解析34个省市直辖区数据
     *
     * @param context
     */
    public void loadProData(Context context) {
        String cityJson = utils.getJson(context, "simple_cities_pro_city_dis.json");
        Type type = new TypeToken<List<CityInfoBean>>() {
        }.getType();
        mProListData = new Gson().fromJson(cityJson, type);
    }

}
