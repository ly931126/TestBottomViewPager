package com.kelin.mvvmlight.bindingadapter.image;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kelin.mvvmlight.command.ReplyCommand;

/**
 * Created by kelin on 16-3-24.
 */
public final class ViewBindingAdapter {

    @SuppressWarnings("unused")
    @BindingAdapter(value = {"uri", "placeholderImageRes", "errorImageRes", "request_width", "request_height", "onSuccessCommand", "onFailureCommand"}, requireAll = false)
    public static void loadImage(final ImageView imageView, String uri,
                                 @DrawableRes int placeholderImageRes,
                                 @DrawableRes int errorImageRes,
                                 int width, int height,
                                 final ReplyCommand<GlideDrawable> onSuccessCommand,
                                 final ReplyCommand<Exception> onFailureCommand) {

        Glide.with(imageView.getContext()).load(uri).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().placeholder(placeholderImageRes).error(errorImageRes).into(new GlideDrawableImageViewTarget(imageView) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                super.onResourceReady(resource, animation);
                if (onSuccessCommand != null)
                    onSuccessCommand.execute(resource);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                if (onFailureCommand != null)
                    onFailureCommand.execute(e);
            }
        });
    }


    @SuppressWarnings("unused")
    @BindingAdapter({"uri"})
    public static void setImageUri(SimpleDraweeView simpleDraweeView, String uri) {
        if (!TextUtils.isEmpty(uri)) {
            simpleDraweeView.setImageURI(Uri.parse(uri));
        }
    }

}

