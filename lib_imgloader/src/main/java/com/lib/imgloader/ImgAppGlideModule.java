package com.lib.imgloader;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.module.AppGlideModule;
import java.io.File;

public class ImgAppGlideModule extends AppGlideModule {

  private static ImgGlobalConfig imgGlobalOption;
  private DiskCache diskCache;//硬盘缓存目录.

  public static ImgGlobalConfig getImgGlobalOption() {
    return imgGlobalOption;
  }

  public static void setImgGlobalOption(ImgGlobalConfig imgGlobalOption) {
    ImgAppGlideModule.imgGlobalOption = imgGlobalOption;
  }

  @Override public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
    super.applyOptions(context, builder);
    if (null != imgGlobalOption) {
      if (imgGlobalOption.isDebug()) {
        builder.setLogLevel(Log.DEBUG);
      }
      if (null != imgGlobalOption.getImgOption()) {
        builder.setDefaultRequestOptions(imgGlobalOption.getImgOption().getRequestOptions());
      }
      if (null != imgGlobalOption.getDiskCacheDir()) {
        builder.setDiskCache(() ->
            getDiskCache(imgGlobalOption.getDiskCacheDir(), imgGlobalOption.getDiskCacheDirSize()));
      }
    }
  }

  /** 自定义硬盘缓存目录和大小 */
  @Nullable private DiskCache getDiskCache(@NonNull File cacheDir, long size) {
    if (!cacheDir.exists()) {
      if (!cacheDir.mkdirs()) {
        return null;
      }
    }
    if (null == diskCache) {
      diskCache = DiskLruCacheWrapper.create(cacheDir, size);
    }
    return diskCache;
  }
}
