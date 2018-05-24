package com.scx.dropdownmenu;

import android.support.annotation.LayoutRes;

import com.scx.dropdownmenu.adapter.BaseAdapter;
import com.scx.dropdownmenu.adapter.BaseViewHolder;


/**
 * Created by Stephen Sun on 2017/8/4 0004.
 * Email:suncunx@qq.com
 */

public class PopupAdapter extends BaseAdapter<PopupItem> {

    public void setCheckItem(int position) {
        for (int i = 0; i < mData.size(); i++) {
            mData.get(i).setChecked(false);
        }
        mData.get(position).setChecked(true);
    }

    public void setCheckItem(String value) {
        for (int i = 0; i < getData().size(); i++) {
            if (getData().get(i).getText().equals(value)) {
                setCheckItem(i);
                break;
            }
        }
    }

    public PopupAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PopupItem item) {
        helper.setVariable(com.scx.dropdownmenu.BR.popupItem, item);
    }
}
