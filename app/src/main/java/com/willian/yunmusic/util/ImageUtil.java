package com.willian.yunmusic.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

import com.willian.yunmusic.R;
import com.willian.yunmusic.constant.Constant;

/**
 * 图片操作的工具类
 */

public class ImageUtil {

    /**
     * 得到唱盘背后半透明的圆形背景
     *
     * @param context
     * @return
     */
    public static Drawable getDiscBackgroundDrawable(Context context) {
        int screenWidth = DisplayUtils.getScreenWidthPixels(context);
        int discSize = (int) (screenWidth * Constant.SCALE_DISC_SIZE);
        Bitmap discBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.play_disc_halo), discSize, discSize, false);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), discBitmap);
        return roundedBitmapDrawable;
    }

    /**
     * 得到唱盘图片
     * 唱盘图片由空心圆盘及音乐专辑图片“合成”得到
     */
    public static Drawable getDiscDrawable(Context context, int musicPicRes) {
        int screenWidth = DisplayUtils.getScreenWidthPixels(context);
        int discSize = (int) (screenWidth * Constant.SCALE_DISC_SIZE);
        int musicSize = (int) (screenWidth * Constant.SCALE_MUSIC_SIZE);
        Bitmap discBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.play_disc), discSize, discSize, false);
        Bitmap musicBitmap = getMusicPicBitmap(context, musicSize, musicPicRes);

        BitmapDrawable discDrawable = new BitmapDrawable(discBitmap);

        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), musicBitmap);

        discDrawable.setAntiAlias(true);
        roundedBitmapDrawable.setAntiAlias(true);

        Drawable[] drawables = new Drawable[2];
        drawables[0] = roundedBitmapDrawable;
        drawables[1] = discDrawable;

        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        int musicPicMargin = (int) ((Constant.SCALE_DISC_SIZE - Constant.SCALE_MUSIC_SIZE) * screenWidth / 2);
        //调整专辑图片的四周边距，让其显示在正中
        layerDrawable.setLayerInset(0, musicPicMargin, musicPicMargin, musicPicMargin, musicPicMargin);
        return layerDrawable;
    }

    public static Bitmap getMusicPicBitmap(Context context, int musicPicSize, int musicPicRes) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(context.getResources(), musicPicRes, options);
        // 图片实际的宽度
        int imageWidth = options.outWidth;

        int sample = imageWidth / musicPicSize;

        int dstSample = 1;

        if (sample > dstSample) {
            dstSample = sample;
        }

        options.inJustDecodeBounds = false;
        //设置图片采样率
        options.inSampleSize = dstSample;
        // 设置图片解码格式
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), musicPicRes, options), musicPicSize, musicPicSize, true);
    }
}
