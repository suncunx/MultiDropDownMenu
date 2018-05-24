package com.scx.dropdownmenu;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.File;
import java.util.List;

import skin.support.widget.SkinCompatTextView;

/**
 * Created by Stephen Sun on 2017/5/8 0008.
 * Email:1243357168@qq.com
 */

public class BindUtil {

    @BindingAdapter({"image"})
    public static void loadImage(ImageView imageView, @IdRes int resId) {
        Glide.with(imageView.getContext()).load(resId).into(imageView);
    }

    //这里必须是Drawable，因为在app:placeHolder中使用不了@mipmap
    @BindingAdapter({"image", "placeHolder"})
    public static void loadImage(ImageView imageView, String url, Drawable placeHolder) {
        //        .error(R.mipmap.load_failed)
        if (url != null && url.length() > 0) {
            File file = new File(url);
            if (file.exists())
                Glide.with(imageView.getContext()).load(file).placeholder(placeHolder).into(imageView);
        }

        Glide.with(imageView.getContext()).load(url).placeholder(placeHolder).into(imageView);
    }

    @BindingAdapter({"image", "placeHolder", "error"})
    public static void loadImage(ImageView imageView, String url, Drawable placeHolder, Drawable error) {
        //        .error(R.mipmap.load_failed)
        Glide.with(imageView.getContext()).load(url).placeholder(placeHolder).error(error).into(imageView);
    }

    @BindingAdapter({"layoutManager", "itemDecoration", "adapter"})
    public static void setLayoutManager(RecyclerView recyclerView, RecyclerView.LayoutManager manager, RecyclerView.ItemDecoration itemDecoration,
                                        RecyclerView.Adapter adapter) {
        recyclerView.setLayoutManager(manager != null ? manager : new LinearLayoutManager(recyclerView.getContext()));
        if (itemDecoration != null) {
            recyclerView.addItemDecoration(itemDecoration);
        }
        if (adapter == null) {
            throw new NullPointerException("the adapter of recyclerView is null");
        }
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"fixSize"})
    public static void setHasFixSize(RecyclerView recyclerView, boolean fixSize) {
        recyclerView.setHasFixedSize(fixSize);
    }

    @BindingAdapter({"background"})
    public static void setTextBackground(TextView textView, int back) {
        textView.setBackgroundResource(back);
    }

    // 设置密码可见性
    @BindingAdapter({"transMethod"})
    public static void setTransMethod(EditText editText, TransformationMethod transMethod) {
        if (transMethod == null) {
            return;
        }
        editText.setTransformationMethod(transMethod);
    }

    // 可设置可不设置
    @BindingAdapter({"adapter", "itemClick", "loadMore"})
    public static void setRecyclerView(RecyclerView recyclerView, BaseQuickAdapter adapter, BaseQuickAdapter.OnItemClickListener itemClickListener,
                                       BaseQuickAdapter.RequestLoadMoreListener loadMoreListener) {

        adapter.setOnItemClickListener(itemClickListener);
        adapter.setOnLoadMoreListener(loadMoreListener, recyclerView);
    }

    @BindingAdapter({"adapterBean"})
    public static void setRecyclerView(RecyclerView recyclerView, AdapterBean adapterBean) {
        if (adapterBean == null) {
            throw new NullPointerException("adapterBean is null");
        }

        if (adapterBean.getLayoutManager() != null) {
            recyclerView.setLayoutManager(adapterBean.getLayoutManager());
        }

        if (adapterBean.getItemDecoration() != null) {
            recyclerView.addItemDecoration(adapterBean.getItemDecoration());
        }

        if (adapterBean.getAdapter() == null) {
            throw new NullPointerException("RecyclerView.Adapter in adapterBean is null");
        }
        recyclerView.setAdapter(adapterBean.getAdapter());
    }

    @BindingAdapter({"adapterBeanNull"})
    public static void setRecyclerViewNull(RecyclerView recyclerView, AdapterBean adapterBean) {
        if (adapterBean == null) {
            return;
        }

        if (adapterBean.getLayoutManager() != null) {
            recyclerView.setLayoutManager(adapterBean.getLayoutManager());
        }

        if (adapterBean.getItemDecoration() != null) {
            recyclerView.addItemDecoration(adapterBean.getItemDecoration());
        }

        if (adapterBean.getAdapter() == null) {
            return;
        }
        recyclerView.setAdapter(adapterBean.getAdapter());
    }

    @BindingAdapter({"viewPager"})
    public static void setViewPager(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager, false);
    }

    @BindingAdapter({"viewPagerAutoRefresh"})
    public static void setViewPagerAutoRefresh(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @BindingAdapter({"fragmentPagerAdapter"})
    public static void setFragmentPagerAdapter(ViewPager viewPager, FragmentPagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
    }

    @BindingAdapter({"clickListener"})
    public static void setClickListener(View view, View.OnClickListener clickListener) {
        if (clickListener != null) {
            view.setOnClickListener(clickListener);
        }
    }

    @BindingAdapter({"tabTexts", "popupViews", "contentView"})
    public static void setDropDownMenu(DropDownMenu downMenu, List<String> tabTexts, List<View> popupViews, View contentView) {
        downMenu.setDropDownMenu(tabTexts, popupViews, contentView);
    }

    @BindingAdapter({"tabText"})
    public static void setDropDownMenu(DropDownMenu downMenu, String tabText) {
        downMenu.setTabText(tabText);
    }

    @BindingAdapter({"dropMenuClose"})
    public static void setDropDownMenu(DropDownMenu downMenu, boolean close) {
        if (downMenu.isShowing() && close) {
            downMenu.closeMenu();
        }
    }
    @BindingAdapter({"rentPopupChecked"})
    public static void setTextView(TextView textView, boolean checked) {
        Context context = textView.getContext();
        if (checked) {
            //            textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
            ((SkinCompatTextView) textView).setTextAppearance(textView.getContext(), R.style.SkinCompatTextAppearance);
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.mipmap.drop_down_checked), null);
        } else {
            textView.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    @BindingAdapter({"rentPopupCheckedSimple"})
    public static void setTextViewCheckSimple(TextView textView, boolean checked) {
        Context context = textView.getContext();
        Log.d("BindUtil", "setTextViewCheckSimple: textView = " + textView);
        if (checked) {
            textView.setTextColor(context.getResources().getColor(R.color.drop_down_selected));
        } else {
            textView.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));
        }
    }
}
