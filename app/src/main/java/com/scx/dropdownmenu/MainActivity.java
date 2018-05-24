package com.scx.dropdownmenu;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DropItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        List<String> list = new ArrayList<>();
        list.add("content1");
        list.add("content2");
        list.add("content3");
        View contentView = LayoutInflater.from(this).inflate(R.layout.content, null);
        binding.setVariable(BR.dropMenuBean, PopupHelper.getSimpleDropMenuBean(this, this, "header", list, contentView));

    }

    @Override
    public void onDropItemClick(BaseQuickAdapter adapter, int position, int tabPosition) {
        Toast.makeText(this, "position = " + position, Toast.LENGTH_SHORT).show();
    }
}
