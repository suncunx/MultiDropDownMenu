package com.scx.dropdownmenu;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scx.dropdownmenu.adapter.HorizontalDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Stephen Sun on 2017/7/31 0031.
 * Email:suncunx@qq.com
 */

public class PopupHelper {

    private static final String TAG = "PopupHelper";

    public static DropMenuBean getSimpleDropMenuBean(Context context, DropItemClickListener listener,
                                                     String header, List<String> data, View contentView) {
        List<String> headers = new ArrayList<>();
        headers.add(header);

        List<List<String>> dataList = new ArrayList<>();
        dataList.add(data);
        return getSimpleDropMenuBean(context, listener, headers, dataList, contentView);
    }
    public static DropMenuBean getSimpleDropMenuBean(Context context, DropItemClickListener listener, List<String> headers, List<List<String>> data, View contentView) {
        DropMenuBean bean = new DropMenuBean();

        List<View> popupViews = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            List<String> array = data.get(i);
            initPopupViews(context, listener, popupViews, headers.get(i), array, bean, i);
        }
        bean.setTabTexts(headers);
        bean.setPopupViews(popupViews);
        bean.setContentView(contentView);
        Log.d(TAG, "" + headers);
        Log.d(TAG, "" + popupViews);
        Log.d(TAG, "" + contentView);
        return bean;
    }

    public DropMenuBean getLinkageResultBean(Context context, DropItemClickListener listener, List<ForumFilterBean> filterBeanList, View contentView, ViewGroup rootView) {
        DropMenuBean bean = new DropMenuBean();
        List<String> headers = new ArrayList<>();
        List<View> popupViews = new ArrayList<>();
        for (int i = 0; i < filterBeanList.size(); i++) {
            String header = filterBeanList.get(i).getHeader();
            headers.add(header);
            if (filterBeanList.get(i).isSimple()) { // 初始化简单列表
                initPopupViews(context,listener, popupViews, header, Arrays.asList(filterBeanList.get(i).getIndexes()), bean, i);
            } else {
                initContent(context, listener, popupViews, header,  filterBeanList.get(i).getData(), rootView, bean, i); // 初始化二级联动列表
            }
        }
        // 初始化AdapterBean
        bean.setTabTexts(headers);
        bean.setPopupViews(popupViews);
        bean.setContentView(contentView);
        return bean;
    }

    // 初始化一个header下的二级列表
    private void initContent(Context context, final DropItemClickListener listener, List<View> popupViews, final String header, final List<ForumFilterItem> maps, ViewGroup rootView, final DropMenuBean bean, final int tabPosition) {

        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.popup_gange, rootView, false);

        List<AdapterBean> adapterBeanList = new ArrayList<>();

        final PopupAdapter popupAdapter1 = new PopupAdapter(R.layout.item_list_rent_filter);
        List<PopupItem> items1 = new ArrayList<>();
        for (int i = 0; i < maps.size(); i++) {
            ForumFilterItem filterItem = maps.get(i); // 所有的key构成了左边列表， 有了左边列表的值就可以放入recyclerView中
            items1.add(new PopupItem(filterItem.getIndex(), false));
        }
        popupAdapter1.setNewData(items1);
        AdapterBean adapterBean1 = new AdapterBean(new LinearLayoutManager(context), popupAdapter1, new HorizontalDecoration(context, R.drawable.divider));

        final PopupAdapter popupAdapter2 = new PopupAdapter(R.layout.item_list_rent_filter);
        popupAdapter2.setNewData(filter2Items(maps.get(0).getValues()));
        AdapterBean adapterBean2 = new AdapterBean(new LinearLayoutManager(context), popupAdapter2, new HorizontalDecoration(context, R.drawable.divider));

        popupAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                popupAdapter1.setCheckItem(position);
                popupAdapter2.setNewData(filter2Items(maps.get(position).getValues()));
            }
        });

        popupAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                popupAdapter2.setCheckItem(position);
                bean.setTabText(position == 0 ? header : popupAdapter2.getItem(position).getText());
                bean.setClose(true);
                listener.onDropItemClick(adapter, position, tabPosition);
            }
        });

        adapterBeanList.add(adapterBean1);
        adapterBeanList.add(adapterBean2);
        dataBinding.setVariable(BR.adapterBeanList, adapterBeanList);
        popupViews.add(dataBinding.getRoot());
    }

    private List<PopupItem> filter2Items(String[] values) {
        List<PopupItem> list = new ArrayList<>();
        for (String value : values) {
            list.add(new PopupItem(value, false));
        }
        return list;
    }
    /**
     * 获取结果列表的数据
     *
     * @param headers 标题数组
     * @param data    每个标题对应的列表数据的列表，长度与标题数量一致
     * @return 整个结果列表页面的数据结构
     */
    public static void setSimpleBean(Context context, DropItemClickListener listener, String[] headers, List<List<String>> data, DropMenuBean bean) {
        List<View> popupViews = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            List<String> array = data.get(i);
            initPopupViews(context, listener, popupViews, headers[i], array, bean, i);
        }

        bean.setTabTexts(Arrays.asList(headers));
        bean.setPopupViews(popupViews);
//        bean = new DropMenuBean(Arrays.asList(headers), popupViews, R.layout.content_in_bill_save, null, true, BR.inBillSaveBean, null);
//        return bean;
    }

    private static List<PopupItem> filter2Items(List<String> values) {
        List<PopupItem> list = new ArrayList<>();
        for (String value : values) {
            list.add(new PopupItem(value, false));
        }
        return list;
    }

    // simple
    private static void initPopupViews(Context context, final DropItemClickListener listener, List<View> popupViews, final String header, final List<String> array, final DropMenuBean bean, final int tabPosition) {

        RecyclerView recyclerView = new RecyclerView(context);
        final PopupAdapter popupAdapter = new PopupAdapter(R.layout.item_list_forum_filter_simple);
        popupAdapter.setNewData(filter2Items(array));

        recyclerView.addItemDecoration(new HorizontalDecoration(context, R.drawable.divider_thin));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(popupAdapter);
        popupAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                popupAdapter.setCheckItem(position);
//                bean.getMenuBean().setTabText(position == 0 ? header : array[position]);
                bean.setTabText(array.get(position));
                bean.setClose(true);
                listener.onDropItemClick(adapter, position, tabPosition);
            }
        });

        popupViews.add(recyclerView);

    }

}
