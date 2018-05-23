package com.scx.dropdownmenu;

import java.util.List;

/**
 * Created by Stephen Sun on 2017/8/7 0007.
 * Email:suncunx@qq.com
 */

public class ForumFilterBean {
    private String header;

    private String[] indexes;
    private List<ForumFilterItem> data; //最外层对应每个header，中间对应左边索引与右边值的列表

    private boolean simple;

    public ForumFilterBean(String header, String[] indexes, List<ForumFilterItem> data, boolean simple) {
        this.header = header;
        this.indexes = indexes;
        this.data = data;
        this.simple = simple;
    }

    public String[] getIndexes() {
        return indexes;
    }

    public void setIndexes(String[] indexes) {
        this.indexes = indexes;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<ForumFilterItem> getData() {
        return data;
    }

    public void setData(List<ForumFilterItem> data) {
        this.data = data;
    }

    public boolean isSimple() {
        return simple;
    }

    public void setSimple(boolean simple) {
        this.simple = simple;
    }
}
