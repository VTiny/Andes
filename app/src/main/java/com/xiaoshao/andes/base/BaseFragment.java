package com.xiaoshao.andes.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.xiaoshao.andes.R;

/**
 * Author: liuxiao
 * Created: 2019/1/12 18:31
 * Description:
 */
public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int contentViewLayout = getContentViewLayout();
        if (contentViewLayout == 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            ViewGroup root = (LinearLayout) inflater.inflate(R.layout.base_fragment_layout, container, false);
            FrameLayout contentContainer = root.findViewById(R.id.base_fragment_content);
            View contentView = inflater.inflate(contentViewLayout, container, false);
            contentContainer.addView(contentView);
            return root;
        }
    }

    protected int getContentViewLayout() {
        return 0;
    }

}
