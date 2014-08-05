package net.liaohuqiu.cube.sample.ui.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import net.liaohuqiu.cube.sample.R;
import net.liaohuqiu.cube.sample.activity.SampleTabsStyled;
import net.liaohuqiu.cube.sample.activity.TitleBaseFragment;
import net.liaohuqiu.cube.sample.data.SampleRequest;
import net.liaohuqiu.cube.sample.ui.fragment.imagelist.BigListViewFragment;
import net.liaohuqiu.cube.sample.ui.fragment.imagelist.GridListViewFragment;
import net.liaohuqiu.cube.sample.ui.fragment.imagelist.SmallListViewFragment;
import net.liaohuqiu.cube.util.LocalDisplay;
import net.liaohuqiu.cube.views.block.BlockListAdapter;
import net.liaohuqiu.cube.views.block.BlockListView;
import net.liaohuqiu.cube.views.block.BlockListView.OnItemClickListener;

public class HomeFragment extends TitleBaseFragment {

    private BlockListView mBlockListView;
    private ArrayList<ItemInfo> mItemInfos = new ArrayList<HomeFragment.ItemInfo>();

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        mBlockListView = (BlockListView) view.findViewById(R.id.ly_home_container);

        setHeaderTitle("Cube Demo");

        mItemInfos.add(new ItemInfo("Big Image List", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(BigListViewFragment.class, null);
            }
        }));

        mItemInfos.add(new ItemInfo("Grid Image List", "#b8ebf7", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(GridListViewFragment.class, null);
            }
        }));

        mItemInfos.add(new ItemInfo("Small Image List", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(SmallListViewFragment.class, null);
            }
        }));

        mItemInfos.add(new ItemInfo("API Request", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(RequestDemoFragment.class, null);
            }
        }));

        mItemInfos.add(new ItemInfo("Dot View", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(DotViewFragment.class, null);
            }
        }));

        mItemInfos.add(new ItemInfo("More Action", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(MoreActionViewFragment.class, null);
            }
        }));
        mItemInfos.add(new ItemInfo("Tab", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(MoreActionViewFragment.class, null);
                Intent intent = new Intent();
                Class<?> dstClassName = SampleTabsStyled.class;
                intent.setClass(getActivity(), dstClassName);
                startActivity(intent);
            }
        }));

        setupList();
        return view;
    }

    private int mSize = 0;

    public void setupList() {

        mSize = (LocalDisplay.SCREEN_WIDTH_PIXELS - LocalDisplay.dp2px(25 + 5 + 5)) / 3;

        int horizontalSpacing = LocalDisplay.dp2px(5);
        int verticalSpacing = LocalDisplay.dp2px(10.5f);

        mBlockListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                mBlockListAdapter.getItem(position).mOnClickListener.onClick(v);
            }
        });

        mBlockListAdapter.setSpace(horizontalSpacing, verticalSpacing);
        mBlockListAdapter.setBlockSize(mSize, mSize);
        mBlockListAdapter.setColumnNum(3);
        mBlockListView.setAdapter(mBlockListAdapter);
        mBlockListAdapter.displayBlocks(mItemInfos);
    }

    private BlockListAdapter<ItemInfo> mBlockListAdapter = new BlockListAdapter<HomeFragment.ItemInfo>() {

        @Override
        public View getView(LayoutInflater layoutInflater, int position) {
            ItemInfo itemInfo = mBlockListAdapter.getItem(position);

            ViewGroup view = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.item_home, null);

            TextView textView = ((TextView) view.findViewById(R.id.tv_item_home_title));
            textView.setText(itemInfo.mTitle);
            view.setBackgroundColor(itemInfo.getColor());
            return view;
        }
    };

    @Override
    protected boolean enableDefaultBack() {
        return false;
    }

    private static class ItemInfo {
        private String mColor;
        private String mTitle;
        private OnClickListener mOnClickListener;

        public ItemInfo(String title, String color, OnClickListener onClickListener) {
            mTitle = title;
            mColor = color;
            mOnClickListener = onClickListener;
        }

        private int getColor() {
            return Color.parseColor(mColor);
        }
    }
}
