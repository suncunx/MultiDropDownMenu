package com.scx.dropdownmenu.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.scx.dropdownmenu.R;

public class BaseViewHolder extends com.chad.library.adapter.base.BaseViewHolder {
        public BaseViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }

        public void setVariable(int variableId, Object object) {
            ViewDataBinding viewDataBinding = getBinding();
            viewDataBinding.setVariable(variableId, object);
        }
    }