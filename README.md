# ImageGridView
微博，微信九宫格图片布局，配上目前可能是Android最好的大图浏览效果。

## Demo
![](/screenshot/demo.gif)

## 使用
 ![Download](https://api.bintray.com/packages/li-xiaojun/jrepo/imagegridview/images/download.svg)
 添加依赖
 ```groovy
 implementation 'com.lxj:imagegridview:最新版本'
 ```


使用：
```kotlin
imageGrid.setAdapter(object : ImageGridAdapter(this, t.list) {
            override fun onImageItemClick(
                context: Context,
                gridView: ImageGridView,
                position: Int,
                urls: MutableList<String>
            ) {
               //使用XPopup展示大图
                val urls2 = ArrayList<Any>()
                urls.forEach { urls2.add(it) }
                XPopup.Builder(context).asImageViewer(
                    gridView.getChildAt(position) as ImageView, position, urls2,
                    { popupView, position ->
                        popupView.updateSrcView(gridView.getChildAt(position) as ImageView)
                    }, ImageLoader()
                ).show()
            }

            override fun loadImage(imageView: ImageView, position: Int, url: String) {
                //只需要实现这一个方法
                imageView.load(url = url, isCrossFade = true, placeholder = R.drawable.ic_default_color,
                    isForceOriginalSize = true)
            }
        })
    }
```