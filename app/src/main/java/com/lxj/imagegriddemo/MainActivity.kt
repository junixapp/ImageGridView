package com.lxj.imagegriddemo

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lxj.imagegridview.ImageGridAdapter
import com.lxj.imagegridview.ImageGridView
import kotlinx.android.synthetic.main.activity_main.*
import com.bumptech.glide.request.target.Target
import com.lxj.androidktx.AndroidKtxConfig
import com.lxj.androidktx.core.*
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.XPopupImageLoader
import java.io.File
import java.util.ArrayList

data class CircleInfo(
    var msg: String = "",
    var list: ArrayList<String>
)

class MainActivity : AppCompatActivity() {

    val list = arrayListOf<CircleInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidKtxConfig.init(this)

        prepareData()

        recyclerView.vertical()
            .divider(color = Color.parseColor("#cccccc"))
            .bindData(list, R.layout.adapter_list) { holder, t, _ ->
                holder.getView<ImageGridView>(R.id.imageGrid).setAdapter(object : ImageGridAdapter(this, t.list) {
                    override fun onImageItemClick(
                        context: Context,
                        gridView: ImageGridView,
                        position: Int,
                        urls: MutableList<String>
                    ) {
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
                        imageView.load(url = url, isCrossFade = true, placeholder = R.drawable.ic_default_color,
                            isForceOriginalSize = true)
                    }
                })
            }


    }
    internal inner class ImageLoader : XPopupImageLoader {
        override fun loadImage(position: Int, url: Any, imageView: ImageView) {
            //必须指定Target.SIZE_ORIGINAL，否则无法拿到原图，就无法享用天衣无缝的动画
            imageView.load(url = url, isForceOriginalSize = true)
        }

        override fun getImageFile(context: Context, uri: Any): File? {
            try {
                return Glide.with(context).downloadOnly().load(uri).submit().get()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }
    fun prepareData(){
        list.add(CircleInfo(list = arrayListOf(
            "https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=efb47ed806fa513d4eaa6ade0d6d554c/cb8065380cd79123e9e67f70a3345982b2b780b4.jpg"
        )))
        list.add(CircleInfo(list = arrayListOf(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554227871051&di=1a4ca9d7a9b45cce4dbae052d859c5d1&imgtype=0&src=http%3A%2F%2Fimage.yy.com%2Fyywebalbumbs2bucket%2F0e912df9174b490992c2f2ccf97f26bc_1497908005893.jpeg"
        )))
        list.add(CircleInfo(list = arrayListOf(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548756837006&di=551df0dcf59d1d71673c3d46b33f0d93&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201308%2F04%2F20130804155912_wCRnE.thumb.700_0.jpeg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2279952540,2544282724&fm=26&gp=0.jpg"
        )))
        list.add(CircleInfo(list = arrayListOf(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548756837006&di=551df0dcf59d1d71673c3d46b33f0d93&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201308%2F04%2F20130804155912_wCRnE.thumb.700_0.jpeg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2279952540,2544282724&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=851052518,4050485518&fm=26&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548764579122&di=e3a46d9075ee49ecefb552a447974ddc&imgtype=0&src=http%3A%2F%2Fimg5q.duitang.com%2Fuploads%2Fitem%2F201112%2F03%2F20111203233836_3wu5E.thumb.700_0.jpg"
        )))

        list.add(CircleInfo(list = arrayListOf(
            "https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=a292f2e0af44ad3431bf8187e0a30c08/574e9258d109b3deaf33b465c2bf6c81800a4c2a.jpg"
        )))
        list.add(CircleInfo(list = arrayListOf(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548756837006&di=551df0dcf59d1d71673c3d46b33f0d93&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201308%2F04%2F20130804155912_wCRnE.thumb.700_0.jpeg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2279952540,2544282724&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=851052518,4050485518&fm=26&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548764579122&di=e3a46d9075ee49ecefb552a447974ddc&imgtype=0&src=http%3A%2F%2Fimg5q.duitang.com%2Fuploads%2Fitem%2F201112%2F03%2F20111203233836_3wu5E.thumb.700_0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=174904559,2874238085&fm=26&gp=0.jpg"
        )))
        list.add(CircleInfo(list = arrayListOf(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548756837006&di=551df0dcf59d1d71673c3d46b33f0d93&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201308%2F04%2F20130804155912_wCRnE.thumb.700_0.jpeg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2279952540,2544282724&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=851052518,4050485518&fm=26&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548764579122&di=e3a46d9075ee49ecefb552a447974ddc&imgtype=0&src=http%3A%2F%2Fimg5q.duitang.com%2Fuploads%2Fitem%2F201112%2F03%2F20111203233836_3wu5E.thumb.700_0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=174904559,2874238085&fm=26&gp=0.jpg",
            "https://user-gold-cdn.xitu.io/2019/1/25/168839e977414cc1?imageView2/2/w/800/q/100",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551692956639&di=8ee41e070c6a42addfc07522fda3b6c8&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160413%2F75659e9b05b04eb8adf5b52669394897.jpg"
        )))
    }


}
