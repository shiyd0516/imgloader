# imgloader
图片加载库

##引用该库
```
repositories {
  mavenCentral()
}

dependencies {
    implementation 'io.github.shiyd0516.lib:imgloader:1.0.1'
}
```

##使用方法

如果我们需要全局配置，可以在自己的Application中添加全局配置。

```
    val option = ImgOption()
      .setPlaceholderImgResId(androidx.appcompat.R.drawable.abc_btn_check_to_on_mtrl_015)//默认图片
      .setErrorImg(androidx.appcompat.R.drawable.abc_btn_check_to_on_mtrl_015)//错误图片
      .setFallbackImgResId(androidx.appcompat.R.drawable.abc_btn_check_to_on_mtrl_015)//图片url时null时的图片
      .setFormat(ImgOption.FORMAT_ARGB_8888)//图片格式argb8888
    ImgOptionGlobal()
      .setDiskCache(File(this.externalCacheDir, "img"), 250 * 1024 * 1024)//设置硬盘缓存位置和大小
      .setImgOption(option)
      .init(true)
```

没有特殊配，使用全局配置
```
 ImgLoader.load(this,"",imgView)
```

如果我们在加载某些图片时需要单独配置参数，我们可以使用下面的方法

```
val option = ImgOption()
      .setPlaceholderImgResId(com.google.android.material.R.drawable.abc_ic_ab_back_material)
      .setErrorImg(com.google.android.material.R.drawable.abc_ic_ab_back_material)
      .setFallbackImgResId(com.google.android.material.R.drawable.abc_ic_ab_back_material)
      .setCircle(true)
    ImgLoader.load(this,"",imgView,option)
```

##常用方法
1. 不使用单独参数配置
```
ImgLoader.load(Context context, String url, ImageView view)
```

2. 单独配置参数
```
ImgLoader.load(Context context, String url, ImageView view, ImgOption imgOption)
```

3. 暂停加载图片
```
ImgLoader.pause(Context context)
```

4. 重新开始加载图片
```
ImgLoader.resume(Context context)
```

5. 清空固定ImageView的内存
```
ImgLoader.clear(Context context, View view)
```

6. 清空所有的缓存（包含内存和硬盘缓存）
```
ImgLoader.clear(Context context)
```

7. 清空所有的内存缓存
```
ImgLoader.clearMemory(Context context)
```

8. 清空所有的磁盘缓存
```
ImgLoader.clearDiskCache(Context context)
```

9.  获取磁盘缓存目录
```
ImgLoader.getCacheDir(Context context)
```
