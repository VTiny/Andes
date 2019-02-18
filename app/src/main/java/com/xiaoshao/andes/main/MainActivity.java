package com.xiaoshao.andes.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;

import com.xiaoshao.andes.R;
import com.xiaoshao.andes.base.BaseActivity;
import com.xiaoshao.andes.base.BaseFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Map<Integer, BaseFragment> mTabFragmentMap;
    private BaseFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        initTabs();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        BaseFragment target = mTabFragmentMap.get(menuItem.getItemId());
        if (target != null) {
            switchFragment(mCurrentFragment, target);
            return true;
        } else {
            return false;
        }
    }

    private void initTabs() {
        mTabFragmentMap = new HashMap<>();
        mTabFragmentMap.put(R.id.navigation_home, new MainHomeTabFragment());
        mTabFragmentMap.put(R.id.navigation_working, new MainWorkingTabFragment());
        mTabFragmentMap.put(R.id.navigation_discovery, new MainDiscoveryTabFragment());
        mTabFragmentMap.put(R.id.navigation_me, new MainMeTabFragment());
        FragmentManager fm = getSupportFragmentManager();
        if (fm != null) {
            FragmentTransaction transaction = fm.beginTransaction();
            mCurrentFragment = mTabFragmentMap.get(R.id.navigation_home);
            if (mCurrentFragment == null) {
                mCurrentFragment = new MainHomeTabFragment();
            }
            transaction.replace(R.id.fragment_container, mCurrentFragment);
            transaction.commit();
        }
    }

    private void switchFragment(BaseFragment lastFragment, BaseFragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(lastFragment);
        if (!targetFragment.isAdded()) {
            transaction.add(R.id.fragment_container, targetFragment);
        }
        transaction.show(targetFragment).commitAllowingStateLoss();
        mCurrentFragment = targetFragment;
    }



}
