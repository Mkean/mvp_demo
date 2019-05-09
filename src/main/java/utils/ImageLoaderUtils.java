package utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bwie.mvp_demo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * 作者：王庆
 * 时间：2017/12/7
 */

public class ImageLoaderUtils {
    private static ImageLoaderUtils mImageLoaderUtils = null;

    public static ImageLoaderUtils getInstance() {
        synchronized (ImageLoaderUtils.class) {
            if (mImageLoaderUtils == null) {
                mImageLoaderUtils = new ImageLoaderUtils();
            }
        }
        return mImageLoaderUtils;
    }

    public void initImageLoader(String uri, ImageView imageview) {
        ImageLoader instance = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .build();//构建完成
        instance.displayImage(uri, imageview, options);
    }
}
