package com.bksx.android_java_nav.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bksx.android_java_nav.R;
import com.squareup.picasso.Picasso;

/**
 * @Author JoneChen
 * @Date 2020\7\31 0031-13:03
 */
public class ImageViewBindingAdapter {

    /*
    * requireAll默认是true 是否都要赋值默认是都赋值
    * */
    @BindingAdapter(value = {"image","defaultImageResourse"},requireAll = false)
    public static void setImage(ImageView imageView, String imageUrl,int imageResourse) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imageView);
        } else {
            imageView.setBackgroundResource(imageResourse);
        }

    }

    /*重载方法*/
//    @BindingAdapter("image")
//    public static void setImage(ImageView imageView, int imageResourse) {
//        imageView.setImageResource(imageResourse);
//    }
}
