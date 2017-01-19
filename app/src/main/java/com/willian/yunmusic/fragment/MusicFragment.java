package com.willian.yunmusic.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.willian.yunmusic.R;
import com.willian.yunmusic.activity.LocalMusicActivity;
import com.willian.yunmusic.adapter.SongListAdapter;
import com.willian.yunmusic.bean.CommItem;
import com.willian.yunmusic.widget.WrapHeightListView;

import java.util.ArrayList;
import java.util.List;

/**
 * MusicFragment
 */
public class MusicFragment extends BaseFragment implements View.OnClickListener {

    private View mView;

    private List<CommItem> mItemList = new ArrayList<CommItem>();
    // 高度自适应的ListView
    private WrapHeightListView mListView;

    private SongListAdapter mItemAdapter;

    private RelativeLayout mHeadLayout;

    private ImageView mHeadIcon;

    private ImageView mMoreIcon;

    private PopupWindow mPopupWindow;

    private LinearLayout mRootLayout;

    private LinearLayout mCreateLayout;

    private LinearLayout mManageLayout;

    private TextView listCount;

    private LinearLayout mLocalMusicLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_music, container, false);
        // 初始化View
        initView();
        // 设置初始数据
        setData();
        // 事件处理
        handleEvent();

        return mView;
    }

    private void handleEvent() {
        mHeadLayout.setOnClickListener(this);
        mMoreIcon.setOnClickListener(this);
        mLocalMusicLayout.setOnClickListener(this);
    }

    private void initView() {
        mRootLayout = (LinearLayout) mView.findViewById(R.id.layout_root);
        mHeadLayout = (RelativeLayout) mView.findViewById(R.id.layout_list_header);
        listCount = (TextView) mView.findViewById(R.id.tv_list_count);
        mHeadIcon = (ImageView) mView.findViewById(R.id.icon_list_head);
        mMoreIcon = (ImageView) mView.findViewById(R.id.icon_list_more);

        mListView = (WrapHeightListView) mView.findViewById(R.id.lv_song_list);
        mItemAdapter = new SongListAdapter(getActivity(), mItemList);
        mListView.setAdapter(mItemAdapter);

        mLocalMusicLayout = (LinearLayout) mView.findViewById(R.id.layout_local_music);
    }

    private void setData() {
        mItemList.add(new CommItem(R.mipmap.placeholder_disk_play_fm, getActivity().getResources().getString(R.string.like_music), getActivity().getResources().getString(R.string.zero_count), "", R.mipmap.list_icn_more));
        mItemAdapter.changeData(mItemList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_list_header:
                if (mListView.getVisibility() == View.VISIBLE) {
                    mListView.setVisibility(View.GONE);
                    mHeadIcon.setImageResource(R.mipmap.list_head_icn_fold);
                } else {
                    mListView.setVisibility(View.VISIBLE);
                    mHeadIcon.setImageResource(R.mipmap.list_head_icn_open);
                }
                break;
            case R.id.icon_list_more:
                initPopupWindow();
                break;
            case R.id.layout_local_music:
                Intent mIntent = new Intent(getActivity(), LocalMusicActivity.class);
                startActivity(mIntent);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化PopupWindow
     */
    private void initPopupWindow() {
        if (mPopupWindow == null) {
            View popupView = View.inflate(getActivity(), R.layout.popup_song_list, null);
            mCreateLayout = (LinearLayout) popupView.findViewById(R.id.layout_create_list);
            mManageLayout = (LinearLayout) popupView.findViewById(R.id.layout_manage_list);
            mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        //点击外部，自动消失
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        // 设置弹出动画
        mPopupWindow.setAnimationStyle(R.style.popup_window_style);
        //设置弹出框的背景
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x90000000));
        // 位于某个View的正下方
        mPopupWindow.showAtLocation(mRootLayout, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        backgroundAlpha(0.3f);
        // 创建歌单
        mCreateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                View dialogView = View.inflate(getActivity(), R.layout.alert_dialog, null);
                final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setView(dialogView);
                alertDialog.show();
                final Button submitBtn = (Button) dialogView.findViewById(R.id.btn_submit);
                Button cancelBtn = (Button) dialogView.findViewById(R.id.btn_cancel);
                final EditText editText = (EditText) dialogView.findViewById(R.id.et_list_title);
                final TextView textCount = (TextView) dialogView.findViewById(R.id.tv_text_count);
                // 监听EditText内容变化
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        int len = s.length();
                        if (len > 0) {
                            if (len > 40) {
                                editText.setEnabled(false);
                            } else {
                                editText.setEnabled(true);
                            }
                            submitBtn.setEnabled(true);
                            textCount.setText(len + "");
                        } else {
                            submitBtn.setEnabled(false);
                            textCount.setText("0");
                        }
                    }
                });
                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CommItem itemInfo = new CommItem(R.mipmap.placeholder_disk_play_fm, editText.getText().toString(), getActivity().getResources().getString(R.string.zero_count), "", R.mipmap.list_icn_more);
                        mItemList.add(itemInfo);
                        mItemAdapter.changeData(mItemList);
                        alertDialog.dismiss();
                        listCount.setText("(" + mItemList.size() + ")");
                    }
                });
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
        // 管理歌单
        mManageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }
}
