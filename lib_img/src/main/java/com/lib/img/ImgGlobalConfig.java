package com.lib.img;

import java.io.File;

/**
 * 图片加载全局配置.
 */
public class ImgGlobalConfig {
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
   * 是不是Debug模式.
   *
   * @param debug {@code true}表示Debug模式.
   * @return {@link ImgGlobalConfig}.
   */
  public ImgGlobalConfig setDebug(boolean debug) {
    isDebug = debug;
    return this;
  }

  /**
   * 自定义缓存目录和大小.
   *
   * @param diskCacheDir 缓存目录.
   * @param diskCacheDirSize 缓存大小,单位byte.
   * @return {@link ImgGlobalConfig}.
   */
  public ImgGlobalConfig setDiskCache(File diskCacheDir, long diskCacheDirSize) {
    this.diskCacheDir = diskCacheDir;
    this.diskCacheDirSize = diskCacheDirSize;
    return this;
  }

  /**
   * @param imgOption 设置全局统一配置
   * @return {@link ImgGlobalConfig}.
   */
  public ImgGlobalConfig setImgOption(ImgOption imgOption) {
    this.imgOption = imgOption;
    return this;
  }

  /**
   * 全局统一的初始化.
   */
  public void init() {
    ImgAppGlideModule.setImgGlobalOption(this);
  }
}
