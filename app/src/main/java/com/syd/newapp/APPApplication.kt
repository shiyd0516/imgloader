package com.syd.newapp

import android.app.Application
import com.lib.imgloader.ImgOption
import com.lib.imgloader.ImgOptionGlobal
import java.io.File

class APPApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    //全局配置参数
    val option = ImgOption()
      .setPlaceholderImgResId(androidx.appcompat.R.drawable.abc_btn_check_to_on_mtrl_015)//默认图片
      .setErrorImg(androidx.appcompat.R.drawable.abc_btn_check_to_on_mtrl_015)//错误图片
      .setFallbackImgResId(androidx.appcompat.R.drawable.abc_btn_check_to_on_mtrl_015)//图片url时null时的图片
      .setFormat(ImgOption.FORMAT_ARGB_8888)//图片格式argb8888
    //初始化全局配置
    ImgOptionGlobal()
      .setDiskCache(File(this.externalCacheDir, "img"), 250 * 1024 * 1024)//设置硬盘缓存位置和大小
      .setImgOption(option)
      .init(true)
  }
}