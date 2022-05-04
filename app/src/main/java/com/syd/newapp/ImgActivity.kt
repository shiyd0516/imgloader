package com.syd.newapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.lib.imgloader.ImgLoader
import com.lib.imgloader.ImgOption

class ImgActivity : AppCompatActivity() {

  private lateinit var imgView: ImageView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_img)
    imgView = findViewById<ImageView>(R.id.iv_img)

    //全局配置，没有单独配置
    findViewById<MaterialButton>(R.id.btn).setOnClickListener { globalOptionImage() }
    //测试局部配置.
    findViewById<MaterialButton>(R.id.btn2).setOnClickListener { signOptionImage() }
    //清空缓存
    findViewById<MaterialButton>(R.id.btn3).setOnClickListener { ImgLoader.clear(applicationContext) }
  }

  private fun globalOptionImage() {
    ImgLoader.load(
      this,
      "https://upload-images.jianshu.io/upload_images/24398822-9e96db34c98c2dbd.png?imageMogr2/auto-orient/strip|imageView2/2/w/581/format/webp",
      imgView
    )
  }

  private fun signOptionImage() {
    val option = ImgOption()
      .setPlaceholderImgResId(com.google.android.material.R.drawable.abc_ic_ab_back_material)
      .setErrorImg(com.google.android.material.R.drawable.abc_ic_ab_back_material)
      .setFallbackImgResId(com.google.android.material.R.drawable.abc_ic_ab_back_material)
      .setCircle(true)
    ImgLoader.load(
      this,
      "https://upload.jianshu.io/users/upload_avatars/5439590/a247253e-f59b-4cbe-a7ad-c5b44354165a.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240",
      imgView,
      option
    )
  }
}