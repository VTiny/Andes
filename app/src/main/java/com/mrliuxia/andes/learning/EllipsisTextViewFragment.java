package com.mrliuxia.andes.learning;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.mrliuxia.andes.R;
import com.mrliuxia.andes.base.BaseFragment;
import com.mrliuxia.andes.view.MyEllipsisTextView;

/**
 * Description:
 * Author: liuxiao
 * Data: 2019-09-17
 *
 * @blame: liuxiao
 */
public class EllipsisTextViewFragment extends BaseFragment {

    @Override
    protected int getContentViewLayout() {
        return R.layout.working_ellipsis_text_view_layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final MyEllipsisTextView autoSizeTv = findViewById(R.id.tv_content_auto);
//        MyEllipsisTextView fixedSizeTv = findViewById(R.id.tv_content_fixed);

        Log.i(TAG, "onViewCreated: ");
//        autoSizeTv.setText("asdf");
        autoSizeTv.post(new Runnable() {
            @Override
            public void run() {
                autoSizeTv.setText("asdf");
            }
        });
    }
}
