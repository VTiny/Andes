package com.xiaoshao.andes.working;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoshao.andes.view.MyPopupWindow;
import com.xiaoshao.andes.R;
import com.xiaoshao.andes.base.BaseFragment;
import com.xiaoshao.andes.main.MainMeTabFragment;
import com.xiaoshao.andes.util.SingleFragmentHelper;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/2/19
 */
public class ExpandableIndexFragment extends BaseFragment {

    private MyPopupWindow mPopupWindow;

    @Override
    protected int getContentViewLayout() {
        return R.layout.working_expandable_index_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.popup_menu, null);
        mPopupWindow = new MyPopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(0);
        mPopupWindow.getContentView().findViewById(R.id.shade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText indexEditTv = view.findViewById(R.id.edit_target);
        final View target = view.findViewById(R.id.btn_target);
        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.show(target, 0, -(int) target.getHeight(),
                        TextUtils.isEmpty(indexEditTv.getText().toString()) ? 0 : Integer.parseInt(indexEditTv.getText().toString()));
            }
        });
        view.findViewById(R.id.btn_outside).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "clicked", Toast.LENGTH_SHORT).show();
                getContext().startActivity(SingleFragmentHelper.getStartIntent(getContext(), MainMeTabFragment.class, null));

            }
        });
    }
}
