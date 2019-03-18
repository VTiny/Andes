package com.xiaoshao.andes.learning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.xiaoshao.andes.R;
import com.xiaoshao.andes.base.BaseFragment;
import com.xiaoshao.andes.util.SystemUtil;
import com.xiaoshao.andes.util.TouchDelegateHelper;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/3/4
 */
public class TouchDelegateLearningFragment extends BaseFragment {

    @Override
    protected int getContentViewLayout() {
        return R.layout.learning_touch_delegate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View btn = view.findViewById(R.id.btn);
        View father = ((View) btn.getParent());
        View grandfather = ((View) father.getParent());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Button clicked.", Toast.LENGTH_SHORT).show();
            }
        });

        TouchDelegateHelper.expandViewClickArea(btn,
                (int) SystemUtil.dp2px(500),
                (int) SystemUtil.dp2px(500),
                (int) SystemUtil.dp2px(500),
                (int) SystemUtil.dp2px(500));

//        TouchDelegateHelper.expandClickAreaByAddForkView(btn,
//                (int) SystemUtil.dp2px(40),
//                (int) SystemUtil.dp2px(40),
//                (int) SystemUtil.dp2px(40),
//                (int) SystemUtil.dp2px(40));


        father.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Father clicked.", Toast.LENGTH_SHORT).show();
            }
        });
//        TouchDelegateHelper.expandViewClickArea(father,
//                (int) SystemUtil.dp2px(0),
//                (int) SystemUtil.dp2px(15),
//                (int) SystemUtil.dp2px(0),
//                (int) SystemUtil.dp2px(15));

//        TouchDelegateHelper.expandViewClickArea(father,
//                (int) SystemUtil.dp2px(100),
//                (int) SystemUtil.dp2px(50),
//                (int) SystemUtil.dp2px(100),
//                (int) SystemUtil.dp2px(50));


    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
