package com.jumeng.shop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jumeng.shop.holders.BaseViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/9.
 * ============================================================
 */
public abstract class BaseRecyclerViewAdapter<Data> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Data> mDatas;
    private Context mContext;
    private final Object mLock = new Object();
    private boolean mNotifyOnChange = true;

    public BaseRecyclerViewAdapter(Context context) {
        init(context, new ArrayList<Data>());
    }

    public BaseRecyclerViewAdapter(Context context, Data[] datas) {
        init(context, Arrays.asList(datas));
    }

    public BaseRecyclerViewAdapter(Context context, List<Data> datas) {
        init(context, datas);
    }

    private void init(Context context, List<Data> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    public void add(Data data) {
        if (data != null) {
            synchronized (mLock) {
                mDatas.add(data);
            }
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void addAll(Collection<? extends Data> collection) {
        if (collection != null && collection.size() != 0) {
            synchronized (mLock) {
                mDatas.addAll(collection);
            }
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void addAll(Data... datas) {
        if (datas != null && datas.length != 0) {
            synchronized (mLock) {
                Collections.addAll(mDatas, datas);
            }
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }


    public void insert(Data data, int index) {
        synchronized (mLock) {
            mDatas.add(index, data);
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void remove(Data data) {
        synchronized (mLock) {
            mDatas.remove(data);
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void remove(int position) {
        synchronized (mLock) {
            if (mNotifyOnChange) notifyDataSetChanged();
        }
    }

    public void clear() {
        synchronized (mLock) {
            mDatas.clear();
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void sort(Comparator<? super Data> comparator) {
        synchronized (mLock) {
            Collections.sort(mDatas, comparator);
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void setNotifyOnChange(boolean notifyOnChange) {
        mNotifyOnChange = notifyOnChange;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateCustomContentHolder(parent, viewType);
    }

    protected abstract BaseViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Data data = mDatas.get(position);
        holder.setData(data);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }
}
