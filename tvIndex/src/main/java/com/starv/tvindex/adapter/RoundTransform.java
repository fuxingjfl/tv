package com.starv.tvindex.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;
import com.starv.tvindex.R;
import com.starv.tvindex.util.BitmapUtils;
import com.starv.tvindex.util.DensityUtil;
import com.starv.tvindex.util.view.BitmapBGUtils;

import static android.R.attr.radius;

/**
 * Created by ysq on 2019/3/5.
 */

class RoundTransform implements Transformation {

    private Context context;
    private boolean isbg;
    public RoundTransform(Context context,boolean isbg){
        this.context=context;
        this.isbg=isbg;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap bitmap=null,img_bg;
        //1.压缩处理
        Bitmap zoomBitmp = BitmapUtils.zoom(source, DensityUtil.dp2px(context,30), DensityUtil.dp2px(context,30));
//        if (isbg){
//            img_bg  = BitmapBGUtils.circleBitmap(zoomBitmp,context.getResources());
//        }else{
            //2.圆形处理
            bitmap  = BitmapUtils.circleBitmap(zoomBitmp,context.getResources());
//        }
        //必须要回收source，否则会报错
        source.recycle();
        //返回圆形的Bitmap对象


//        Bitmap bitmap1 = mergeImage(zoomBitmp);


        return bitmap;
    }

    @Override
    public String key() {
        return "round : radius = " + radius;
    }


    public Bitmap mergeImage(Bitmap img){

        //获取Bitmap的宽度
        int width = img.getWidth();

        //返回一个正方形的Bitmap对象
        Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);


        //提供指定宽高的canvas
        Canvas canvas = new Canvas(bitmap);
        // 提供画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(context.getResources().getColor(R.color.white));
        //背景：在画布上绘制一个圆
        canvas.drawCircle(width / 2, width / 2, width / 2, paint);
//        canvas.drawColor(resources.getColor(R.color.white));
        //设置图片相交情况下的处理方式 //setXfermode：设置当绘制的图像出现相交情况时候的处理方式的,它包含的常用模式有哪几种 //PorterDuff.Mode.SRC_IN 取两层图像交集部门,只显示上层图像,注意这里是指取相交叉的部分,然后显示上层图像 //PorterDuff.Mode.DST_IN 取两层图像交集部门,只显示下层图像
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        //前景：在画布上绘制一个bitmap
        canvas.drawBitmap(img, 0, 0, paint);
        return bitmap;


    }

}
