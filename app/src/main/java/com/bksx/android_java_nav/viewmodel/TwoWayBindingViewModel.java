package com.bksx.android_java_nav.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.databinding.library.baseAdapters.BR;

import com.bksx.android_java_nav.model.NameModel;

/**
 * @Author JoneChen
 * @Date 2020\7\31 0031-16:33
 */
public class TwoWayBindingViewModel extends BaseObservable {
//    private NameModel nameModel;
    //使用observableField包装
    private ObservableField<NameModel> nameModel;

    public TwoWayBindingViewModel() {
        NameModel model = new NameModel();
        model.name = "TestName";
        nameModel = new ObservableField<>();
        nameModel.set(model);

    }

//    @Bindable
//    public String getName() {
//        return nameModel.name;
//    }
//
//    public void setName(String name) {
//        if (name != null && !name.equals(nameModel.name)){
//            nameModel.name = name;
//            notifyPropertyChanged(BR.name);
//        }
//    }

    public String getName(){
        return nameModel.get().name;
    }

    public void setName(String name){
        nameModel.get().name = name;
    }
}
