package com.mrliuxia.andes.working.motion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.mrliuxia.andes.base.BaseFragment;
import com.mrliuxia.andes.util.SingleFragmentHelper;

/**
 * Description:
 * Author: liuxiao
 * Data: 2020/8/20
 *
 * @blame: liuxiao
 */
public class MotionLayoutDemoFragment extends BaseFragment {

    private static final String ARGS_LAYOUT_ID = "ARGS_LAYOUT_ID";

    static void showDemo(Context context, @LayoutRes int layoutResID) {
        Bundle args = new Bundle();
        args.putInt(ARGS_LAYOUT_ID, layoutResID);
        Intent startIntent = SingleFragmentHelper.getStartIntent(context, MotionLayoutDemoFragment.class, args);
        context.startActivity(startIntent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewLayout() {
        int layoutID = getArguments() == null ? 0 : getArguments().getInt(ARGS_LAYOUT_ID, 0);
        return layoutID == 0 ? super.getContentViewLayout() : layoutID;
    }
}
