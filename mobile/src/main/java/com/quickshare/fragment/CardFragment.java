package com.quickshare.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.quickshare.R;
import com.quickshare.activity.MainActivity;
import com.quickshare.adapter.CardListAdapter;
import com.quickshare.custom.AnimatedRecyclerView;
import com.quickshare.entity.ProfileData;

import java.util.List;

import butterknife.Bind;

public class CardFragment extends BaseFragment {

    @Bind(R.id.card_list)
    AnimatedRecyclerView cardList;

    private static MainActivity mainActivity;
    private static List<ProfileData> listProfileData;
    private static boolean isNewlyAdded;

    public CardFragment() {
    }

    public static CardFragment newInstance(MainActivity mainActivity, List<ProfileData> listProfileData, boolean isNewlyAdded) {
        CardFragment.mainActivity = mainActivity;
        CardFragment.listProfileData = listProfileData;
        CardFragment.isNewlyAdded = isNewlyAdded;
        return new CardFragment();
    }

    @Override
    protected int getTitleId() {
        return R.string.app_name;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_card;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        cardList.setAdapter(new CardListAdapter(listProfileData, this, mainActivity, isNewlyAdded));
    }
}
