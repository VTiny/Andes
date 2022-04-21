package com.mrliuxia.andes.base;

import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mrliuxia.andes.R;

/**
 * Author: liuxiao
 * Created: 2019/1/12 18:31
 * Description:
 */
public class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        afterViewCreated();
    }

    protected int getContentViewLayout() {
        return 0;
    }

    protected void initView(View rootView) {

    }

    protected void afterViewCreated() {

    }

    protected <V extends View> V findViewById(@IdRes int viewId) {
        if (getView() == null) {
            return null;
        }
        return getView().findViewById(viewId);
    }

}
