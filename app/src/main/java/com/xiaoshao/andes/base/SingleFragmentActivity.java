package com.xiaoshao.andes.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.xiaoshao.andes.R;

/**
 * Author: liuxiao
 * Created: 2019/1/15 14:02
 * Description:
 */
public class SingleFragmentActivity extends BaseActivity {

    public static final String PARAM_TAG = "PARAM_TAG";
    public static final String PARAM_NAME = "PARAM_NAME";
    public static final String PARAM_ARGS = "PARAM_ARGS";

    private Fragment mFragment;
    private int mContainerViewId;

    @Override
    protected void onCreate(Bundle arg0) {
        if (getIntent() == null) {
            finish();
            return;
        }
        super.onCreate(arg0);
        setContentView(R.layout.base_fragment_layout);
        mContainerViewId = R.id.base_fragment_content;
        ensureFragment();
    }

    /**
     * 请在onCreate执行完之后调用
     *
     * @return
     */
    protected final int getContainerViewId() {
        return mContainerViewId;
    }

    /**
     * 初始化fragment
     */
    private void ensureFragment() {
        String fragmentTag = getIntent().getStringExtra(PARAM_TAG);
        mFragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (mFragment == null) {
            // 初始化fragment
            String fragmentName = getIntent().getStringExtra(PARAM_NAME);
            if (!TextUtils.isEmpty(fragmentName)) {
                Bundle args = getIntent().getBundleExtra(PARAM_ARGS);
                mFragment = addFragmentByTag(getContainerViewId(), fragmentName, fragmentTag, args);
            }
        }
    }

    /**
     * 增加fragment
     *
     * @param container
     * @param clazz
     * @param tag
     * @param argument
     * @return
     */
    private Fragment addFragmentByTag(int container, String clazz, String tag, Bundle argument) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentByTag(tag);
        if (f == null) {
            FragmentTransaction ft = fm.beginTransaction();
            f = Fragment.instantiate(this, clazz, argument);
            ft.add(container, f, tag);
            ft.commit();
        } else if (f.isDetached()) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.attach(f);
            ft.commit();
        }
        return f;
    }
}
