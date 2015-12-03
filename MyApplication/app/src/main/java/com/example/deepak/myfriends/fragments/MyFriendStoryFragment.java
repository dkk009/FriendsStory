package com.example.deepak.myfriends.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.deepak.myfriends.R;
import com.example.deepak.myfriends.managers.FileReader;
import com.example.deepak.myfriends.models.FriendStory;
import com.example.deepak.myfriends.models.OurStory;
import com.example.deepak.myfriends.viewholders.ViewHolderBaseCard;
import com.example.deepak.myfriends.viewholders.ViewHolderCheckInCard;
import com.example.deepak.myfriends.viewholders.ViewHolderMainCard;
import com.example.deepak.myfriends.viewholders.ViewHolderSimpleCard;

import java.util.ArrayList;

/**
 *
 */
public class MyFriendStoryFragment  extends Fragment{

    private RecyclerView mRecyclerFriendList;
    private ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_friend_story,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerFriendList = (RecyclerView)view.findViewById(R.id.recycler_friend_story);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        FileReader fileReader  = new FileReader();
        mProgressBar.setVisibility(View.VISIBLE);
        fileReader.readFromAsset("sample_response.json", new FileReader.IntrFileReader() {
            @Override
            public void onCompleted(Object fileData) {
                handleResponse((FriendStory) fileData);
            }

            @Override
            public void onError(Exception e) {
                mProgressBar.setVisibility(View.GONE);
            }
        }, FriendStory.class);
    }

    private void handleResponse(FriendStory fileData) {
        mProgressBar.setVisibility(View.GONE);
        ArrayList<Object> friendStoryList = new ArrayList<>();
        friendStoryList.add(fileData);
        friendStoryList.addAll(fileData.getOurStories());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerFriendList.setLayoutManager(layoutManager);
        FriendsStoryAdapter friendsStoryAdapter = new FriendsStoryAdapter(friendStoryList);
        mRecyclerFriendList.setAdapter(friendsStoryAdapter);
    }


    private class FriendsStoryAdapter extends RecyclerView.Adapter<ViewHolderBaseCard> {

        private ArrayList<Object> mStoryList;
        private static final int FRIEND_STORY = 1;
        private static final int SIMPLE_CARD = 2;
        private static final int CHECKIN_CARD = 3;
        public FriendsStoryAdapter(ArrayList storyList) {
            mStoryList = storyList;
        }

        @Override
        public ViewHolderBaseCard onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolderBaseCard baseCard = null;
            View view = null;
            switch (viewType) {
                case FRIEND_STORY:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_main_card,parent,false);
                    baseCard = new ViewHolderMainCard(view);
                    return baseCard;
                case SIMPLE_CARD:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_simple_card,parent,false);
                    baseCard = new ViewHolderSimpleCard(view);
                    return baseCard;
                case CHECKIN_CARD:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_check_in_card,parent,false);
                    baseCard = new ViewHolderCheckInCard(view);
                    return baseCard;
            }
            return baseCard;
        }

        @Override
        public void onBindViewHolder(ViewHolderBaseCard holder, int position) {
                holder.binData(mStoryList.get(position));
        }

        @Override
        public int getItemViewType(int position) {
            int viewType = 0;
            Object object = mStoryList.get(position);
            if(object instanceof FriendStory) {
                viewType = FRIEND_STORY;
            }else if(object instanceof OurStory) {
                OurStory story = (OurStory) object;
                if(TextUtils.equals(story.getType(),"simple_card")) {
                    viewType = SIMPLE_CARD;
                }else if(TextUtils.equals(story.getType(),"checkin_card")) {
                    viewType = CHECKIN_CARD;
                }
            }
            return viewType;
        }

        @Override
        public int getItemCount() {
            return mStoryList == null ? 0 : mStoryList.size();
        }
    }
}
