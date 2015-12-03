package com.example.deepak.myfriends.viewholders;

import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.deepak.myfriends.R;
import com.example.deepak.myfriends.Utilities.CommonUtility;
import com.example.deepak.myfriends.models.OurStory;
import com.example.deepak.myfriends.networks.ImageDownloader;

/**
 *
 */
public class ViewHolderCheckInCard extends ViewHolderBaseCard {

    private TextView mTxtTitle;
    private ImageView mImageView;
    private TextView mTxtMoreImage;
    private View mView;
    public ViewHolderCheckInCard(View itemView) {
        super(itemView);
        mView = itemView;
        mImageView = (ImageView) itemView.findViewById(R.id.img_view);
        mTxtTitle = (TextView) itemView.findViewById(R.id.txt_title);
        mTxtMoreImage = (TextView) itemView.findViewById(R.id.txt_more_images);
    }

    @Override
    public void binData(Object object) {
        OurStory storyCard = (OurStory) object;
        mTxtTitle.setText(storyCard.getTitle());
        mTxtTitle.setClickable(true);
        String moreImageUrl = "<a href='"+storyCard.getMoreImages()+"'>"+mView.getContext().getString(R.string.more_images)+"</a>";
        mTxtMoreImage.setText(Html.fromHtml(moreImageUrl));
        Linkify.addLinks(mTxtMoreImage, Linkify.ALL);
        mTxtMoreImage.setMovementMethod(LinkMovementMethod.getInstance());
        String imageUrl = storyCard.getImageUrl();
        downloadImage(imageUrl);
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

            }
        },width,width);
    }
}
