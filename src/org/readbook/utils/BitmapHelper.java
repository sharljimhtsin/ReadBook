package org.readbook.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * 图片处理工具类
 * 
 * @author DingMaolin
 * 
 */
public class BitmapHelper {

	/**
	 * 根据drawable 资源ID 创建 Bitmap
	 * 
	 * @param context
	 * @param id
	 *            资源ID
	 * @return Bitmap
	 */
	public static Bitmap getImage(Context context, int id) {
		/* 得到Resrouces资源对象 */
		Resources resources = context.getResources();
		/* 得到资源中的Drawable对象 */
		Drawable drawable = resources.getDrawable(id);
		Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
		resources = null;
		drawable = null;
		return bitmap;
	}

	/**
	 * 根据drawable 资源ID 创建 Drawable
	 * 
	 * @param context
	 * @param id
	 *            资源ID
	 * @return
	 */
	public static Drawable getDrawable(Context context, int id) {
		/* 得到Resrouces资源对象 */
		Resources resources = context.getResources();
		/* 得到资源中的Drawable对象 */
		Drawable drawable = resources.getDrawable(id);
		resources = null;
		return drawable;
	}

	public static byte[] bmpToByteArray(final Bitmap bmp,
			final boolean needRecycle) {
		if (bmp == null) {
			return new byte[0];
		}
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}

		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 该方法用于将bitmap转换为字节数组，png格式质量为100的。
	 * 
	 * @param bm
	 *            待转化的bitmap
	 * @return 返回btmap的字节数组
	 */
	public static byte[] bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * 该方法用于将字节数组转化为bitmap图
	 * 
	 * @param b
	 *            字节数组
	 * @return bitmap位图
	 */
	public static Bitmap bytes2Bitmap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	/**
	 * Drawable 转 Bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
		return bitmapDrawable.getBitmap();
	}

	/**
	 * Bitmap 转 Drawable
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Drawable bitmapToDrawable(Bitmap bitmap) {
		Drawable drawable = new BitmapDrawable(bitmap);
		return drawable;
	}

	/**
	 * 该方法用于任意缩放指定大小的图片
	 * 
	 * @param bitmap
	 *            待修改的图片
	 * @param newWidth
	 *            新图片的宽度
	 * @param newHeight
	 *            新图片的高度
	 * @param sameSize
	 *            是否需要等比缩放，true等比，false固定缩放
	 * @return 缩放后的新图片
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int newWidth, int newHeight,
			boolean sameSize) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) newWidth / width);
		float scaleHeight = ((float) newHeight / height);
		if (sameSize) {
			scaleWidth = Math.abs(scaleWidth);
			scaleHeight = Math.abs(scaleHeight);
			matrix.reset();
			if (scaleWidth > scaleHeight) {
				matrix.postScale(scaleWidth, scaleWidth);
			} else {
				matrix.postScale(scaleHeight, scaleHeight);
			}
		} else {
			matrix.postScale(scaleWidth, scaleHeight);
		}
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		return resizedBitmap;
	}

	/**
	 * 压缩大图片
	 * 
	 * @param filename
	 *            文件路径
	 * @param maxWidth
	 *            压缩的宽度
	 * @param maxHeight
	 *            压缩的高度
	 * @return
	 */
	public static Bitmap compressedImage(String filename, int maxWidth,
			int maxHeight) {
		Bitmap bitmap = null;
		Matrix mMatrix = new Matrix();
		try {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filename, opts);
			opts.inJustDecodeBounds = false;
			int srcWidth = opts.outWidth;
			int srcHeight = opts.outHeight;
			int desWidth = 0;
			int desHeight = 0;

			//

			// int hRatio=(int)Math.ceil(opts.outHeight/(float)maxHeight);
			// //图片是高度的几倍
			// int wRatio=(int)Math.ceil(opts.outWidth/(float)maxWidth);
			// //图片是宽度的几倍
			// System.out.println("hRatio:"+hRatio+"  wRatio:"+wRatio);
			// //缩小到 1/ratio的尺寸和 1/ratio^2的像素
			// if(hRatio>1||wRatio>1){
			// if(hRatio>wRatio){
			// opts.inSampleSize=hRatio;
			// }
			// else
			// opts.inSampleSize=wRatio;
			// }

			//

			double ratio = 0.0;
			if (srcWidth > srcHeight) {
				ratio = srcWidth / maxWidth;
				desWidth = maxWidth;
				desHeight = (int) (srcHeight / ratio);
				// mMatrix.setRotate(90);
			} else {
				ratio = srcHeight / maxHeight;
				desHeight = maxHeight;
				desWidth = (int) (srcWidth / ratio);
			}
			BitmapFactory.Options newOpts = new BitmapFactory.Options();
			if ((int) (ratio) < 1) {
				newOpts.inSampleSize = 1;
			} else {
				newOpts.inSampleSize = (int) (ratio);
			}
			newOpts.inJustDecodeBounds = false;
			newOpts.outWidth = desWidth;
			newOpts.outHeight = desHeight;

			bitmap = BitmapFactory.decodeFile(filename, newOpts);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), mMatrix, false);

			if (bitmap.getWidth() < maxWidth || bitmap.getHeight() < maxHeight) {
				bitmap = zoomBitmap(bitmap, maxWidth, maxHeight, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	// ------------------------------------------------------------------------------------------------

	/**
	 * 该方法用于将一个矩形图片的边角钝化
	 * 
	 * @param bitmap
	 *            待修改的图片
	 * @param roundPx
	 *            边角的弧度
	 * @return 返回修改过边角的新图片
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * 该方法用于生成图片的下方倒影效果
	 * 
	 * @param bitmap
	 *            代修改的图片
	 * @return 有倒影效果的新图片
	 */
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		final int reflectionGap = 4;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);
		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
				width, height / 2, matrix, false);
		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);
		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
				0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		//  Set the Transfer mode to be porter duff and destination in  
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		//  Draw a rectangle using the paint with our linear gradient  
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);
		return bitmapWithReflection;
	}

	/**
	 * 水印
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap createBitmapForWatermark(Bitmap src, Bitmap watermark) {
		if (src == null) {
			return null;
		}
		int w = src.getWidth();
		int h = src.getHeight();
		int ww = watermark.getWidth();
		int wh = watermark.getHeight();
		// create the new blank bitmap
		Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
		Canvas cv = new Canvas(newb);
		// draw src into
		cv.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入src
		// draw watermark into
		cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, null);// 在src的右下角画入水印
		// save all clip
		cv.save(Canvas.ALL_SAVE_FLAG);// 保存
		// store
		cv.restore();// 存储
		return newb;
	}

	/**
	 * 图片合成
	 * 
	 * @return
	 */
	public static Bitmap potoMix(int direction, Bitmap... bitmaps) {
		if (bitmaps.length <= 0) {
			return null;
		}
		if (bitmaps.length == 1) {
			return bitmaps[0];
		}
		Bitmap newBitmap = bitmaps[0];
		// newBitmap = createBitmapForFotoMix(bitmaps[0],bitmaps[1],direction);
		for (int i = 1; i < bitmaps.length; i++) {
			newBitmap = createBitmapForFotoMix(newBitmap, bitmaps[i], direction);
		}
		return newBitmap;
	}

	/**
	 * 处理图片的工具类.
	 */
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int TOP = 3;
	public static final int BOTTOM = 4;

	private static Bitmap createBitmapForFotoMix(Bitmap first, Bitmap second,
			int direction) {
		if (first == null) {
			return null;
		}
		if (second == null) {
			return first;
		}
		int fw = first.getWidth();
		int fh = first.getHeight();
		int sw = second.getWidth();
		int sh = second.getHeight();
		Bitmap newBitmap = null;
		if (direction == LEFT) {
			newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh,
					Config.ARGB_8888);
			Canvas canvas = new Canvas(newBitmap);
			canvas.drawBitmap(first, sw, 0, null);
			canvas.drawBitmap(second, 0, 0, null);
		} else if (direction == RIGHT) {
			newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh,
					Config.ARGB_8888);
			Canvas canvas = new Canvas(newBitmap);
			canvas.drawBitmap(first, 0, 0, null);
			canvas.drawBitmap(second, fw, 0, null);
		} else if (direction == TOP) {
			newBitmap = Bitmap.createBitmap(sw > fw ? sw : fw, fh + sh,
					Config.ARGB_8888);
			Canvas canvas = new Canvas(newBitmap);
			canvas.drawBitmap(first, 0, sh, null);
			canvas.drawBitmap(second, 0, 0, null);
		} else if (direction == BOTTOM) {
			newBitmap = Bitmap.createBitmap(sw > fw ? sw : fw, fh + sh,
					Config.ARGB_8888);
			Canvas canvas = new Canvas(newBitmap);
			canvas.drawBitmap(first, 0, 0, null);
			canvas.drawBitmap(second, 0, fh, null);
		}
		return newBitmap;
	}

	/**
	 * 根据数字创建图片
	 * 
	 * @param num
	 *            分数
	 * @param source
	 *            原图资源图片
	 * @param width
	 *            小图片数字的宽度
	 * @return 分数图片
	 */
	public static Bitmap getMarkBitmap(String num, Bitmap source) {
		if (num != null && !(num.trim().equals(""))) {
			// if(num.length() < 2)
			// num = "0" + num;
			Bitmap bitmap = Bitmap.createBitmap(
					(num.trim().length() * (source.getWidth() / 10)),
					source.getHeight(), Config.ARGB_8888);
			for (int i = 0; i < num.length(); i++) {
				// if(i == num.length())
				// break;
				String str = num.substring(i, i + 1);
				int number = Integer.parseInt(str);
				Canvas canvas = new Canvas(bitmap);
				Bitmap tempBitmap = getBitmapByNum(number, source);
				canvas.drawBitmap(tempBitmap, i * tempBitmap.getWidth(), 0,
						null);
			}
			return bitmap;
		}
		return null;
	}

	/**
	 * 根据数字创建图片
	 * 
	 * @param num
	 *            数字
	 * @param source
	 *            资源图片
	 * @return
	 */
	private static Bitmap getBitmapByNum(int num, Bitmap source) {
		Bitmap tempBitmap = null;
		if (source != null) {
			int width = source.getWidth() / 10;
			int height = source.getHeight();
			tempBitmap = Bitmap.createBitmap(source, num * width, 0, width,
					height);
		}
		return tempBitmap;
	}

	/** */
	/**
	 * 图片去色,返回灰度图片
	 * 
	 * @param bmpOriginal
	 *            传入的图片
	 * @return 去色后的图片
	 */
	public static Bitmap toGrayscale(Bitmap bmpOriginal) {
		int width, height;
		height = bmpOriginal.getHeight();
		width = bmpOriginal.getWidth();
		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		Canvas c = new Canvas(bmpGrayscale);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmpOriginal, 0, 0, paint);
		return bmpGrayscale;
	}

	/** */
	/**
	 * 去色同时加圆角
	 * 
	 * @param bmpOriginal
	 *            原图
	 * @param pixels
	 *            圆角弧度
	 * @return 修改后的图片
	 */
	public static Bitmap toGrayscale(Bitmap bmpOriginal, int pixels) {
		return toRoundCorner(toGrayscale(bmpOriginal), pixels);
	}

	/** */
	/**
	 * 把图片变成圆角
	 * 
	 * @param bitmap
	 *            需要修改的图片
	 * @param pixels
	 *            圆角的弧度
	 * @return 圆角图片
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/** */
	/**
	 * 使圆角功能支持BitampDrawable
	 * 
	 * @param bitmapDrawable
	 * @param pixels
	 * @return
	 */
	public static BitmapDrawable toRoundCorner(BitmapDrawable bitmapDrawable,
			int pixels) {
		Bitmap bitmap = bitmapDrawable.getBitmap();
		bitmapDrawable = new BitmapDrawable(toRoundCorner(bitmap, pixels));
		return bitmapDrawable;
	}
	
	/**
	 * 图片保存到sdcard
	 */
	public static void saveImage(final Bitmap bitmap, final String imageName) {
		File file = new File(SdcardManager.getRootFilePath(), imageName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(file);
			output.write(BitmapHelper.bitmap2Bytes(bitmap));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
