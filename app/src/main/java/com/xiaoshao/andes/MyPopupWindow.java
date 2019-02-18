package com.xiaoshao.andes;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: liuxiao
 * Created: 2019/1/14 18:21
 * Description:
 */
public class MyPopupWindow extends PopupWindow implements ExpandableLayout.OnExpansionUpdateListener {

    private static final long DURATION = 300L;
    private ExpandableLayout mContent;
    private View mIcon;
    private View mShadeView;
    private RecyclerView mContentView;

    public MyPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
        mContent = getContentView().findViewById(R.id.ly_expandable);
        mIcon = getContentView().findViewById(R.id.icon);
        mShadeView = getContentView().findViewById(R.id.shade);
        mContentView = getContentView().findViewById(R.id.recycler_view);
        mContent.setOnExpansionUpdateListener(this);
        mContent.setDuration((int) DURATION);

        mContentView.setAdapter(new IndexAdapter());
        mContentView.setLayoutManager(new GridLayoutManager(contentView.getContext(), 2));
        mContentView.addItemDecoration(new IndexItemDecoration(20));
    }

    public void show(View anchor, int xoff, int yoff, int index) {
        showAsDropDown(anchor, xoff, yoff);
        mContent.expand();
        Animation iconAnimation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        iconAnimation.setDuration(DURATION);
        iconAnimation.setFillAfter(true);
        mIcon.startAnimation(iconAnimation);

        Animation shadeAnimation = new AlphaAnimation(0.0f, 0.6f);
        shadeAnimation.setDuration(DURATION);
        shadeAnimation.setFillAfter(true);
        mShadeView.startAnimation(shadeAnimation);

        mContentView.setAdapter(new IndexAdapter());
        mContentView.setLayoutManager(new GridLayoutManager(getContentView().getContext(), 2));
        mContentView.scrollToPosition(index);


    }
//
//    @Override
//    public void showAtLocation(View parent, int gravity, int x, int y) {
//        super.showAtLocation(parent, gravity, x, y);
//        mContent.expand();
//    }

    @Override
    public void dismiss() {
        mContent.collapse(true);

        Animation iconAnimation = new RotateAnimation(180, 0,
                Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        iconAnimation.setDuration(DURATION);
        iconAnimation.setFillAfter(true);
        mIcon.startAnimation(iconAnimation);

        Animation shadeAnimation = new AlphaAnimation(0.6f, 0.0f);
        shadeAnimation.setDuration(DURATION);
        shadeAnimation.setFillAfter(true);
        mShadeView.startAnimation(shadeAnimation);
    }

    @Override
    public void onExpansionUpdate(float expansionFraction) {
        Log.i("lxlx", "" + expansionFraction);
        if (expansionFraction == 0.0f) {
            super.dismiss();
        }
    }

    private class IndexAdapter extends RecyclerView.Adapter<IndexHolder> {

        private List<String> mIndexList;

        public IndexAdapter() {
            mIndexList = new ArrayList<>();
            mIndexList.add("0金州勇士");
            mIndexList.add("1日天模式");
            mIndexList.add("2死神来了");
            mIndexList.add("3佛光普照");
            mIndexList.add("4波士顿凯尔特人");
            mIndexList.add("5费城76人");
            mIndexList.add("6波特兰开拓者");
            mIndexList.add("7圣安东尼奥马刺");
            mIndexList.add("8菲尼克斯太阳");
            mIndexList.add("9俄克拉荷马雷霆");
            mIndexList.add("10休斯顿火箭");
            mIndexList.add("11洛杉矶快船");
            mIndexList.add("12犹他爵士");
            mIndexList.add("13萨克拉门托国王");
            mIndexList.add("14新奥尔良鹈鹕");
            mIndexList.add("15新奥尔良鹈鹕");
        }

        @NonNull
        @Override
        public IndexHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.item_index, viewGroup, false);
            return new IndexHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull IndexHolder indexHolder, int i) {
            indexHolder.mIndexTv.setText(mIndexList.get(i));
            indexHolder.mIndexTv.setBackground(getContentView().getContext().getDrawable(R.drawable.expand_index_bg));
            indexHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        @Override
        public void onBindViewHolder(@NonNull IndexHolder holder, int position, @NonNull List<Object> payloads) {
            super.onBindViewHolder(holder, position, payloads);
//                holder.mIndexTv.setTextColor(getContentView().getContext().getResources().getColor(R.color.red));
        }

        @Override
        public int getItemCount() {
            return mIndexList.size();
        }
    }


    private class IndexHolder extends RecyclerView.ViewHolder {

        private TextView mIndexTv;

        public IndexHolder(@NonNull View itemView) {
            super(itemView);
            mIndexTv = itemView.findViewById(R.id.tv_index);
        }

    }

    private class IndexItemDecoration extends RecyclerView.ItemDecoration {

        private int padding;

        public IndexItemDecoration(int space) {
            padding = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            int position = parent.getChildAdapterPosition(view);
//            if (position == 0) {
//                outRect.left = padding;
//            } else if (parent.getAdapter() != null && position == parent.getAdapter().getItemCount() - 1) {
//                outRect.right = padding;
//            }
            outRect.left = padding;
            outRect.right = padding;
            outRect.top = padding;
            outRect.bottom = padding;
            if (parent.getAdapter() != null && parent.getAdapter().getItemCount()-parent.getChildAdapterPosition(view) <= 2) {
                outRect.bottom = padding * 3;
            }
        }

    }


}
