package com.glp.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.GMOperation;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.ImageCommand;
import org.im4java.core.InfoException;
import org.im4java.process.Pipe;

public class ImageProcessor {

		
	String defaultFont = "msyh"; //系统没有的时候无法找到
	
	double defaultQuality = 85.0;
	
	//String textColor = "white";
	
	int pointsize = 12;
	
	//public void setTextColor(String textColor) {
	//	this.textColor = textColor;
	//}
	public void setPointsize(int pointsize) {
		this.pointsize = pointsize;
	}
	public void setDefaultFont(String defaultFont) {
		this.defaultFont = defaultFont;
	}
	public void setDefaultQuality(double defaultQuality) {
		this.defaultQuality = defaultQuality;
	}
	public void addStringWater(InputStream src, OutputStream out, String water , int[] px , int position, String font) throws IOException{
		ConvertCmd cmd = new ConvertCmd(true);//使用gm 
		GMOperation op = new GMOperation();
		op.gravity(positionMagick(position));
		op.colorspace("RGB");		//颜色范围
		op.quality(defaultQuality);
		
		if(px != null &&  px.length == 2){
			//缩放操作
			//op.resize(px[0], px[1]); 
			  op.scale(px[0],  px[1]); //性能比resize 快2倍以上，质量差不多
		}
		
		//linux下注意字体是否存在,最好拷贝windows的字体，避免字符无法显示
		op.font( font == null ? defaultFont : font );
		
		op.pointsize(pointsize);
		
		//gm的命令行只支持unicode的字符串输入,在windows下由于命令行是gbk的输入,会导致中文字乱码,
		//linux下的命令行如果是gbk也会出问题， 但是由java输出的不会?
		//FIXME 部署完最好先检查下中文是否正常 
		//写两次，第一次偏移1个像素，形成阴影效果
		op.fill("#333333");
		op.draw("text 19,19 '"+water+"'");//  
		op.fill("white");
		op.draw("text 20,20 '"+water+"'");//
				
		
		Pipe pipeIn  = new Pipe(src,null);//  pipe 管道输出
		Pipe pipeOut = new Pipe(null,out);
		
		op.addImage("-"); 
		op.addImage("jpg:-"); //输出jpeg 

		cmd.setInputProvider(pipeIn);
		cmd.setOutputConsumer(pipeOut);
		try {
			cmd.run(op);
		} catch (InterruptedException e) {
			throw new IOException(e);
		} catch (IM4JavaException e) {
			throw new IOException(e);
		}		
	}
	
	public void addStringWater(String src, String dest,String water,String xy) throws IOException{
		FileInputStream in = new FileInputStream(src);
		FileOutputStream out = new FileOutputStream(dest);	
		try{
			//long start = System.currentTimeMillis();
			int[] px = getResize(src, xy);	//FIXME 长宽自适应还没有搞	
			//System.out.println("use "+ (System.currentTimeMillis() - start) );
			addStringWater(in , out, water, px, 9, defaultFont);	
		} finally{
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}
	
	
	public static String positionMagick(int position) {
		String postin="SouthEast";
		switch (position) {
		case 1:
			postin="NorthWest";
			break;
		case 2:
			postin="North";
			break;
		case 3:
			postin="NorthEast";
			break;
		case 4:
			postin="West";
			break;
		case 5:
			postin="Center";
			break;
		case 6:
			postin="East";
			break;
		case 7:
			postin="SouthWest";
			break;
		case 8:
			postin="South";
			break;
		case 9:
			postin="SouthEast";
			break;
		}
		return postin;
	}
	
	/**
	 * 使用ImageInfo 读取照片信息
	 * @param src
	 * @param xy
	 * @return
	 * @throws IOException 
	 */
	public int[] getResize(String src, String xy) throws  IOException{
		if(StringUtils.isBlank(xy)) return null;
		try {
			ImageInfo info = new ImageInfo(src);
			String[] sp = xy.split("x|\\*",2);
			int w = Integer.parseInt(sp[0]);
			int h = Integer.parseInt(sp[1]);
			int wight = info.getImageWidth();
			int height = info.getImageHeight();
			return getResize(wight, height, w, h);
		} catch (InfoException e) {
			throw new IOException("Image meta info parse error:",e);
		} 
	}
	
	
	/**
	 * 缩放尺寸计算
	 * 
	 * @param ow
	 * @param oh
	 * @param w
	 * @param h
	 * @return
	 */
	public static int[] getResize(int ow, int oh, int w, int h) {
		int[] w_h = new int[2];
		int toWidth = ow;
		int toHeight = oh;
		double sw = (double) ow / w;
		double sh = (double) oh / h;
		if (sw > sh) {
			toWidth = w;
			toHeight = (int) (oh / sw);
		} else {
			toHeight = h;
			toWidth = (int) (ow / sh);
		}
		w_h[0] = toWidth;
		w_h[1] = toHeight;
		return w_h;
	}
	
	/**
	 * 处理图片
	 * 
	 * @param in 输入文件
	 * @param out 输出文件
	 * @param inWidth 输入文件的宽
	 * @param inHeight 输入文件的高
	 * @param width 输出宽度
	 * @param hight 输出高度
	 * @param mode 输出模式
	 * @param water 水印类型 
	 * @param waterPosition 水印位置
	 * @param angle 旋转角度
	 * @param sharp 锐化
	 * 
	 * @throws IOException 
	 */
	public void process(String in, String out,
			int inWidth, int inHeight,
			int width, int height, int mode,
			String waterFile , int waterPosition, int angle, double sharp) throws IOException {
		
		ImageCommand cmd = new ConvertCmd(true);//使用gm 
		IMOperation op = new IMOperation();
		op.colorspace("RGB");		//颜色范围
		op.quality(defaultQuality);
		
//		if(mode > 0){
//			//需要scale 
//			//-scale "500x500"  "900x"可以自适应高度
//			op.scale(width < 1 ? null : width, 
//					height < 1 ? null : height) ;
//		}
		int[] wh = null;
		if(mode == 1){
			wh = getWandH(inWidth, inHeight, width, height, 1);
			op.scale(wh[0] < 1 ? null : wh[0],
					wh[1] < 1 ? null : wh[1]);
		}else if(mode == 2){
			wh = getWandH(inWidth, inHeight, width, height, 2);
			op.scale(wh[0] < 1 ? null : wh[0],
					wh[1] < 1 ? null : wh[1]);
			op.gravity("center");
			op.extent(width, height);//固定画布
			
		}else if(mode == 3){
			wh = getResize(inWidth, inHeight, width, height);
			op.scale(wh[0] < 1 ? null : wh[0],
					wh[1] < 1 ? null : wh[1]);
			op.gravity("center");
			op.extent(width, height);
		}
		
		if (sharp > 0) {
			op.sharpen(sharp);
		} else {
			if (wh != null && wh[0] <= 200) {
				// 锐化
				op.sharpen(8.0);
			}
		}
				
		//旋转
		if(angle > 0){
			op.rotate((double) angle);
		}
		
		//先转换
		op.addImage(in , out);
				
		try {
			cmd.run(op);
			//有水印
			if(waterFile != null){
				
				// > 600 的才做水印
				if(inWidth > 600){
					op = new IMOperation();
					op.gravity(positionMagick(waterPosition));	
					op.quality(100.0);
					
					op.addImage();
					op.addImage();
					op.addImage();
					
					cmd = new CompositeCmd(true);
					cmd.run(op, waterFile, out, out);					
				}				
			}		
		} catch (InterruptedException e) {
			throw new IOException(e);
		} catch (IM4JavaException e) {
			throw new IOException(e);
		}
	}
	
	public static int[] getWandH(int ow, int oh, int w, int h, int mode) {
		int[] w_h = new int[2];
		int thumbWidth = (w == 0 ? ow : w);
		int thumbHeight = (h == 0 ? oh : h);
		int toWidth = thumbWidth;
		int toHeight = thumbHeight;
		if (ow > thumbWidth || oh > thumbHeight) {
			// 自适应时如果宽大于高则根据宽度进行缩略
			if (mode == 1 && (w == 0 || h == 0)) {
				if (w == 0)
					toWidth = ow * thumbHeight / oh;
				else if (h == 0)
					toHeight = oh * thumbWidth / ow;
			} else {
				if ((ow > thumbWidth && oh > thumbHeight)) {
					if (ow > oh) {
						toHeight = oh * thumbWidth / ow;
					} else {
						toWidth = ow * thumbHeight / oh;
					}
				} else {
					if (ow > thumbWidth) {
						toHeight = oh * thumbWidth / ow;
					} else {
						toWidth = ow * thumbHeight / oh;
					}
				}
			}
		} else {
			toHeight = oh;
			toWidth = ow;
		}

		// 自动适应一个边
		if (thumbWidth == 0) {
			thumbWidth = toWidth;
		}
		if (thumbHeight == 0) {
			thumbHeight = toHeight;
		}

		if (mode == 1) {
			thumbWidth = toWidth;
			thumbHeight = toHeight;
		}
		if (thumbWidth == 0) {
			thumbWidth = 1;
		}
		if (thumbHeight == 0) {
			thumbHeight = 1;
		}
		w_h[0] = thumbWidth;
		w_h[1] = thumbHeight;
		return w_h;
	}

}
