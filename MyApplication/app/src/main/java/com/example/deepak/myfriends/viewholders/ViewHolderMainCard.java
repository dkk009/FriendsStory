package com.example.deepak.myfriends.viewholders;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.deepak.myfriends.R;
import com.example.deepak.myfriends.Utilities.CommonUtility;
import com.example.deepak.myfriends.models.FriendStory;
import com.example.deepak.myfriends.networks.ImageDownloader;

/**
 *
 */
public class ViewHolderMainCard extends ViewHolderBaseCard {

    private ImageView mImageView;
    private View mView;
    public ViewHolderMainCard(View itemView) {
        super(itemView);
        mView = itemView;
        mImageView = (ImageView) itemView.findViewById(R.id.img_view);
    }

    @Override
    public void binData(Object object) {
        FriendStory friendStory = (FriendStory) object;
        downloadImage(friendStory.getPhoto());
    }

    private void downloadImage(String imageUrl) {
        mImageView.setImageResource(R.drawable.placeholder);
        if(TextUtils.isEmpty(imageUrl)) {
            return;
        }
       int width = CommonUtility.getScreenWidthInPixels(mView.getContext());
        ImageDownloader.getInstance().getImageLoader().get(imageUrl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if(null != mImageView && null != response.getBitmap()) {
                    mImageView.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("test", "Volley Error:" + error);
            }
        },width,width);
    }
}
