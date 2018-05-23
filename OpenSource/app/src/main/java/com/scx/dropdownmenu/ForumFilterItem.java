package com.scx.dropdownmenu;

/**
 * Created by Stephen Sun on 2017/8/7 0007.
 * Email:suncunx@qq.com
 */

public class ForumFilterItem {
    private String index;
    private String[] values;

    public ForumFilterItem(String index, String[] values) {
        this.index = index;
        this.values = values;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }
}
