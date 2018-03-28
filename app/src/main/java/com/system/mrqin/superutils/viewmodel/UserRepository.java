//package com.system.mrqin.superutils.viewmodel;
//
//import android.arch.lifecycle.LiveData;
//import android.arch.lifecycle.MutableLiveData;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.system.mrqin.commonutil.DialogUtil;
//import com.system.mrqin.commonutil.ToastUtil;
//import com.system.mrqin.commonutil.net.http.HttpCallback;
//import com.system.mrqin.commonutil.net.http.OkHttpUtils;
//import com.system.mrqin.commonutil.net.http.ResultDesc;
//import com.system.mrqin.superutils.R;
//import com.system.mrqin.superutils.bean.CityInfoBean;
//import com.system.mrqin.superutils.bean.User;
//import com.system.mrqin.superutils.util.Constant;
//
//import java.lang.reflect.Type;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Administrator on 2018/3/23.
// */
//
//public class UserRepository {
//
//    public LiveData<List<User>> getUser(String userId) {
//        final MutableLiveData<List<User>> data = new MutableLiveData<>();
//        Map<String, String> mMap = new HashMap<>();
//        mMap.put("id", userId);
//        mMap.put("pageIndex", "1");
//        OkHttpUtils.getSync(Constant.GET_LIVE_DATA, mMap, new HttpCallback() {
//            @Override
//            public void onSuccess(ResultDesc resultDesc) {
//                super.onSuccess(resultDesc);
//                if (resultDesc.getCode() == 1) {
//                    Type type = new TypeToken<List<User>>() {
//                    }.getType();
//                    List<User> mProListData = new Gson().fromJson(resultDesc.getData(), type);
//                    data.setValue(mProListData);
//                } else if (resultDesc.getCode() == 0) {
//                    ToastUtil.showText("数据为空");
//                } else {
//                    ToastUtil.showText("数据为空");
//                }
//            }
//
//            @Override
//            public void onFailure(int code, String message) {
//                super.onFailure(code, message);
//                DialogUtil.hideDialogLoading();
//                ToastUtil.showText("网络异常");
//            }
//        });
//        return data;
//    }
//
//}
