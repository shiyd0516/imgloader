package com.lib.imgloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import java.io.File;

public class ImgLoader {

  /**
   * 加载图片.
   *
   * @see #load(Context, String, ImageView, ImgOption).
   */
  public static void load(Context context, String url, ImageView view) {
    load(context, url, view, null);
  }

  /**
   * 加载图片.
   *
   * @param context 上下文.
   * @param url 要加载图片的url.
   * @param view {@link ImageView}.
   * @param imgOption 图片加载的参选配置.
   */
  public static void load(Context context, String url, ImageView view, ImgOption imgOption) {
    RequestBuilder<Drawable> requestBuilder = Glide.with(context).load(url);
    if (null != imgOption) {
      requestBuilder = requestBuilder.apply(imgOption.getRequestOptions());
    }
    requestBuilder.into(view);
  }

  /**
   * 暂停.
   *
   * @param context {@link Context}建议使用Application.
   */
  public static void pause(Context context) {
    Glide.with(context).pauseAllRequests();
  }

  /**
   * 重新开始.
   *
   * @param context {@link Context}建议使用Application.
   */
  public static void resume(Context context) {
    Glide.with(context).resumeRequests();
  }

  /**
   * 清空单个文件的内存.
   *
   * @param view 要清空缓存的View.
   */
  public static void clear(Context context, View view) {
    Glide.with(context).clear(view);
  }

  /**
   * 清空缓存数据数据.（包含硬盘缓存和内存缓存）.
   *
   * @param context {@link Context}建议使用Application.
   */
  public static void clear(Context context) {
    clearDiskCache(context);
    clearMemory(context);
  }

  /**
   * 清空内存缓存.
   *
   * @param context {@link Context}建议使用Application.
   */
  public static void clearMemory(Context context) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      Glide.get(context).clearMemory();
    } else {
      new Handler(Looper.getMainLooper()).post(() -> Glide.get(context).clearMemory());
    }
  }

  /**
   * 清空磁盘缓存.
   *
   * @param context {@link Context}建议使用Application.
   */
  public static void clearDiskCache(Context context) {
    try {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        //如果当前时主线程，需要开启子线程清空硬盘缓存.
        new Thread(() -> Glide.get(context).clearDiskCache()).start();
      } else {
        //当前是子线程，直接清空硬盘缓存.
        Glide.get(context).clearDiskCache();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 获取图片缓存目录.
   *
   * @param context {@link Context}建议使用Application.
   * @return 缓存目录 .
   */
  public static File getCacheDir(Context context) {
    try {
      if (null == ImgAppGlideModule.getImgGlobalOption().getDiskCacheDir()) {
        return new File(context.getCacheDir(), InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR);
      } else {
        return ImgAppGlideModule.getImgGlobalOption().getDiskCacheDir();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
