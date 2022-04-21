package com.xiaoshao.andes.working.motion;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.xiaoshao.andes.R;
import com.xiaoshao.andes.base.BaseFragment;

/**
 * Description: ConstraintLayout2.0发布beta/正式版后继续，目前测试版IDE酷炫功能不开放
 * Author: liuxiao
 * Date: 2019/2/19
 */
public class MotionLayoutFragment extends BaseFragment {

    @Override
    protected int getContentViewLayout() {
        return R.layout.learning_motion_layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindShowDemo(view.findViewById(R.id.btn_hello_world), R.layout.learning_motion_hello_layout);
    }

    private void bindShowDemo(View view, @LayoutRes int layoutID) {
        if (view == null || layoutID == 0) {
            return;
        }
        view.setOnClickListener(v -> MotionLayoutDemoFragment.showDemo(getContext(), layoutID));
    }
}
