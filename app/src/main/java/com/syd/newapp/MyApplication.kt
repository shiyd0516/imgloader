package com.syd.newapp

import android.app.Application
import com.lib.imgloader.ImgGlobalConfig
import com.lib.imgloader.ImgOption
import java.io.File

class MyApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    //全局配置
    val option = ImgOption().setPlaceholderImgResId(androidx.appcompat.R.drawable.abc_btn_check_to_on_mtrl_015)
    ImgGlobalConfig()
      .setDebug(true)
      .setDiskCache(File(this.externalCacheDir, "img"), 250 * 1024 * 1024)
      .setImgOption(option)
      .init()
  }
}