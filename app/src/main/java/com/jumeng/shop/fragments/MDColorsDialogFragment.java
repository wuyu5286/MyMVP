package com.jumeng.shop.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.jumeng.shop.R;
import com.jumeng.shop.activities.BaseActivity;
import com.jumeng.shop.constants.PreferencesKey;
import com.jumeng.shop.utils.PreferencesUtils;
import com.jumeng.shop.utils.ThemeUtils;
import com.jumeng.shop.utils.ToastUtils;
import com.jumeng.shop.views.CircleImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * 描 述 : 界面配色设置
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/11.
 * ============================================================
 */
public class MDColorsDialogFragment extends DialogFragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {


    public static void launch(Activity activity) {
        Fragment fragment = activity.getFragmentManager().findFragmentByTag("MDColorsDialogFragment");
        if (fragment != null) {
            activity.getFragmentManager().beginTransaction().remove(fragment).commit();
        }

        MDColorsDialogFragment dialogFragment = new MDColorsDialogFragment();
        dialogFragment.show(activity.getFragmentManager(), "MDColorsDialogFragment");
    }

    private Map<String, ColorDrawable> mColorDrawableMap = new HashMap<>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(true);

        View view = View.inflate(getActivity(), R.layout.md_colors_dialog, null);
        GridView gridView = (GridView) view.findViewById(R.id.md_colors_dialog_grid);
        gridView.setAdapter(new MDColorsAdapter());
        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);
        return new AlertDialog.Builder(getActivity()).setView(view).setPositiveButton("确定", null).create();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PreferencesUtils instance = PreferencesUtils.instance();
        int index = instance.getInt(PreferencesKey.THEME_INDEX);
        if (position == index) {
            dismiss();
            return;
        }

        instance.putInt(PreferencesKey.THEME_INDEX, position);
        dismiss();

        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).reLoad();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtils.show(getResources().getStringArray(R.array.mdColorNames)[position]);
        return true;
    }


    private class MDColorsAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return ThemeUtils.themeColorArr.length;
        }

        @Override
        public Object getItem(int position) {
            return ThemeUtils.themeColorArr[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = View.inflate(getActivity(), R.layout.item_md_colors_dialog, null);

            if (!mColorDrawableMap.containsKey(String.valueOf(position)))
                mColorDrawableMap.put(String.valueOf(position), new ColorDrawable(getResources().getColor(ThemeUtils.themeColorArr[position][0])));

            CircleImageView imgColor = (CircleImageView) convertView.findViewById(R.id.imgColor);
            ColorDrawable colorDrawable = mColorDrawableMap.get(String.valueOf(position));
            imgColor.setImageDrawable(colorDrawable);

            View imgSelected = convertView.findViewById(R.id.imgSelected);
            int anInt = PreferencesUtils.instance().getInt(PreferencesKey.THEME_INDEX);
            imgSelected.setVisibility(anInt == position ? View.VISIBLE : View.GONE);

            return convertView;
        }
    }
}
