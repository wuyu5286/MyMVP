package com.jumeng.shop.interfaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ============================================================
 * 描 述 : 视图层代理的接口协议
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/4.
 * ============================================================
 */
public interface IDelegate {

    void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    View getView();
}
