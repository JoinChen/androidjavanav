package com.bksx.android_java_nav.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.bksx.android_java_nav.R;

/**
 * Created by Administrator on 2018\7\7 0007.
 * 自定义基类dialog
 */

public abstract class BaseDialog extends Dialog {
    protected Context context;
    private int layoutId;
    protected View view;
    protected CaseGrivate mGrivate;
    protected float screenPercent;
    protected float heightSp;


    public BaseDialog(Context context) {
        this(context, R.style.dialog);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context=context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        listen();
    }

    protected abstract void listen();

    //设置布局的id
    public abstract int setLayoutId();

    //获取当前的布局view
    public View getCurrentView(){
        if(view!=null){
            return view;
        }else{
            throw new IllegalArgumentException("view do not null");
        }
    }
    //设置dialog在界面中的位置
    public BaseDialog setmGrivate(CaseGrivate mGrivate) {
        this.mGrivate = mGrivate;
        return this;
    }

    public CaseGrivate getmGrivate() {

        return mGrivate;
    }
    //设置dialog的屏占比
    public BaseDialog setScreenPercent(float screenPercent) {
        this.screenPercent = screenPercent;
        return this;
    }

    public BaseDialog setScreenPercent(float screenPercent,float heightSp) {
        this.screenPercent = screenPercent;
        this.heightSp=heightSp;
        return this;
    }
    public float getScreenPercent() {

        return screenPercent;
    }

    public float getHeightSp() {
        return heightSp;
    }

    public void myShow(){
        show();

    }

    public void myDismiss(){

        dismiss();
    }

    //初始化dialog的参数
    protected void init() {
        view =  LayoutInflater.from(context).inflate(setLayoutId(), null);
        setContentView(view);
        //设置dialog大小
        Window dialogWindow = getWindow();
        WindowManager manager = ((AppCompatActivity) context).getWindowManager();
        //获取dialog的当前参数
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        //设置dialog在屏幕中的位置
        if(getmGrivate()!=null){
            switch (mGrivate){
                case BOTTOM:
                    dialogWindow.setGravity(Gravity.BOTTOM);
                    break;
                case CENTER:
                    dialogWindow.setGravity(Gravity.CENTER);
                    break;
                case TOP:
                    dialogWindow.setGravity(Gravity.TOP);
                    break;
                default:
                    break;
            }
        }else{
            throw new IllegalArgumentException("dialog的位置还没有设置");
        }
        Display d = manager.getDefaultDisplay();
        //设置dialog的宽度
        if(getScreenPercent()!=0.0f){
            params.width = (int) (d.getWidth() * getScreenPercent());
        }else{
            throw new IllegalArgumentException("dialog的屏占比不可为0");
        }
        if(getHeightSp()!=0.0f){
            params.height = (int) (d.getHeight() * getHeightSp());
        }else{
            throw new IllegalArgumentException("dialog的屏占比不可为0");
        }

        dialogWindow.setAttributes(params);
    }
}
