//package com.system.mrqin.superutils.viewmodel;
//
//import android.arch.lifecycle.LiveData;
//import android.arch.lifecycle.ViewModel;
//
//import com.system.mrqin.superutils.bean.User;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2018/3/23.
// */
//
//public class UserProfileViewModel extends ViewModel {
//
//    private LiveData<List<User>> user;
//    private UserRepository userRepo;
//
//    public UserProfileViewModel(UserRepository userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    public void init(String userId) {
//        if (this.user != null) {
//            // ViewModel is created per Fragment so
//            // we know the userId won't change
//            return;
//        }
//        user = userRepo.getUser(userId);
//    }
//
//    public LiveData<List<User>> getUser() {
//        return this.user;
//    }
//
//}
