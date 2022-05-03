package com.lib.imgloader;

import java.io.File;

/**
 * 图片加载全局配置.
 */
public class ImgOptionGlobal {
  private boolean isDebug;
  private File diskCacheDir;
  private long diskCacheDirSize;
  private ImgOption imgOption;

  public boolean isDebug() {
    return isDebug;
  }

  public File getDiskCacheDir() {
    return diskCacheDir;
  }

  public long getDiskCacheDirSize() {
    return diskCacheDirSize;
  }

  public ImgOption getImgOption() {
    return imgOption;
  }

  /**
   * 自定义缓存目录和大小.
   *
   * @param diskCacheDir 缓存目录.
   * @param diskCacheDirSize 缓存大小,单位byte.
   * @return {@link ImgOptionGlobal}.
   */
  public ImgOptionGlobal setDiskCache(File diskCacheDir, long diskCacheDirSize) {
    this.diskCacheDir = diskCacheDir;
    this.diskCacheDirSize = diskCacheDirSize;
    return this;
  }

  /**
   * @param imgOption 设置全局统一配置
   * @return {@link ImgOptionGlobal}.
   */
  public ImgOptionGlobal setImgOption(ImgOption imgOption) {
    this.imgOption = imgOption;
    return this;
  }

  /**
   * 全局统一的初始化.
   *
   * @param isDebug {@code true}表示Debug模式.
   */
  public void init(boolean isDebug) {
    this.isDebug = isDebug;
    ImgAppGlideModule.setImgGlobalOption(this);
  }
}
