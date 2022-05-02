package com.syd.newapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.lib.imgloader.ImgLoader
import com.lib.imgloader.ImgOption

class ImgActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_img)
    val imgView = findViewById<ImageView>(R.id.iv_img)
    //测试局部配置.
    val option = ImgOption()
      .setPlaceholderImgResId(com.google.android.material.R.drawable.abc_ic_ab_back_material)
      .setErrorImg(com.google.android.material.R.drawable.abc_ic_ab_back_material)
      .setFallbackImgResId(com.google.android.material.R.drawable.abc_ic_ab_back_material)
    // ImgLoader.load(this,"https://upload.jianshu.io/users/upload_avatars/5439590/a247253e-f59b-4cbe-a7ad-c5b44354165a.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240",imgView)
    ImgLoader.load(
      this,
      "https://upload-images.jianshu.io/upload_images/24398822-9e96db34c98c2dbd.png?imageMogr2/auto-orient/strip|imageView2/2/w/581/format/webp",
      imgView
    )
    //测试清空缓存
    findViewById<Button>(R.id.btn).setOnClickListener {
      ImgLoader.clear(applicationContext)
    }
  }
}