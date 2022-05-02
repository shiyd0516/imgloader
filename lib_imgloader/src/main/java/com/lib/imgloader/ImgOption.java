package com.lib.imgloader;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 图片加载配置信息.
 */
public class ImgOption {
  /**
   * format  rgb565 .
   */
  public static final int FORMAT_RGB_565 = 0;
  /**
   * format argb8888.
   */
  public static final int FORMAT_ARGB_8888 = 1;

  @IntDef({ FORMAT_RGB_565, FORMAT_ARGB_8888 })
  @Retention(RetentionPolicy.SOURCE)
  public @interface Format {
  }

  private int placeholderImgResId;
  private int errorImgResId;
  private int fallbackImgResId;
  private int width;
  private int height;
  private String key;
  private @Format int format;
  private boolean isSkipMemory;
  private boolean isSkipDisk;
  private boolean isCircle;
  private int roundingRadius;
  private long frameTimeMicros;

  /**
   * 设置请求占位图片.
   *
   * @param placeholderImgResId 图片resId.
   * @return {@link ImgOption}.
   */
  public ImgOption setPlaceholderImgResId(int placeholderImgResId) {
    this.placeholderImgResId = placeholderImgResId;
    return this;
  }

  /**
   * 设置请求错误图片
   *
   * @param errorImgResId 图片resId.
   * @return {@link ImgOption}.
   */
  public ImgOption setErrorImg(int errorImgResId) {
    this.errorImgResId = errorImgResId;
    return this;
  }

  /**
   * 设置请求错误地址是null时的图片
   *
   * @param fallbackImgResId 图片resId.
   * @return {@link ImgOption}.
   */
  public ImgOption setFallbackImgResId(int fallbackImgResId) {
    this.fallbackImgResId = fallbackImgResId;
    return this;
  }

  /**
   * 重置图片大小 .
   *
   * @param width 图片宽度.
   * @param height 图片高度.
   * @return {@link ImgOption}.
   */
  public ImgOption setSize(int width, int height) {
    this.width = width;
    this.height = height;
    return this;
  }

  /**
   * 设置图片签名.
   *
   * @param key 图片签名.
   * @return {@link ImgOption}.
   */
  public ImgOption setKey(String key) {
    this.key = key.trim();
    return this;
  }

  /**
   * 设置图片格式.
   *
   * @param format {@value  #FORMAT_RGB_565},  {@value   #FORMAT_ARGB_8888}.
   * @return {@link ImgOption}.
   */
  public ImgOption setFormat(@Format int format) {
    this.format = format;
    return this;
  }

  /**
   * 是否跳过内存缓存.
   *
   * @param skipMemory {@code true} 跳过内存缓存.
   * @return {@link ImgOption}.
   */
  public ImgOption setSkipMemory(boolean skipMemory) {
    isSkipMemory = skipMemory;
    return this;
  }

  /**
   * 是否跳过硬盘缓存.
   *
   * @param skipDisk {@code true} 跳过硬盘缓存.
   * @return {@link ImgOption}.
   */
  public ImgOption setSkipDisk(boolean skipDisk) {
    isSkipDisk = skipDisk;
    return this;
  }

  /**
   * 剪裁图片为圆形.
   *
   * @param circle {@code   true}表示圆形，false表示非圆形.
   * @return {@link ImgOption}.
   */
  public ImgOption setCircle(boolean circle) {
    isCircle = circle;
    return this;
  }

  /**
   * 设置图片四个角的剪裁角度.
   *
   * @param roundingRadius 四个角的剪裁角度 .
   * @return {@link ImgOption}.
   */
  public ImgOption setRoundingRadius(int roundingRadius) {
    this.roundingRadius = roundingRadius;
    return this;
  }

  /**
   * 特定用于视频帧的事件位置.
   *
   * @param frameTimeMicros 时间位置，单位微秒.
   * @return {@link ImgOption}.
   */
  public ImgOption setFrameTimeMicros(long frameTimeMicros) {
    this.frameTimeMicros = frameTimeMicros;
    return this;
  }

  @NonNull RequestOptions getRequestOptions() {
    RequestOptions requestOptions = RequestOptions.placeholderOf(placeholderImgResId)
        .error(errorImgResId)
        .fallback(fallbackImgResId);
    if (frameTimeMicros > 0) {
      requestOptions = requestOptions.frame(frameTimeMicros);
    }
    if (width > 0 || height > 0) {
      requestOptions = requestOptions.override(width, height);
    }
    if (null != key && !key.isEmpty()) {
      requestOptions = requestOptions.signature(new ObjectKey(key));
    }
    if (ImgOption.FORMAT_RGB_565 == format) {
      requestOptions = requestOptions.format(DecodeFormat.PREFER_RGB_565);
    } else if (ImgOption.FORMAT_ARGB_8888 == format) {
      requestOptions = requestOptions.format(DecodeFormat.PREFER_ARGB_8888);
    }
    if (isSkipMemory) {
      requestOptions = requestOptions.skipMemoryCache(true);
    }
    if (isSkipDisk) {
      requestOptions = requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
    }
    if (isCircle) {
      requestOptions = requestOptions.optionalCircleCrop();
    } else if (roundingRadius > 0) {
      requestOptions = requestOptions.optionalTransform(new RoundedCorners(roundingRadius));
    }
    return requestOptions.dontAnimate();
  }
}
