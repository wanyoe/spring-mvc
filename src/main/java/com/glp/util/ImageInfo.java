package com.glp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.core.InfoException;
import org.im4java.process.ArrayListOutputConsumer;

/**
 * 
 * @author Administrator
 *
 */
public class  ImageInfo {


  private Map<String,Object> iAttributes = null;



  public ImageInfo(String pImage) throws InfoException {
	  getBaseInfo(pImage);
  }
  


  /**
     Query basic image-information.
 
     @param pImage  Source image
     @param pInput  InputStream (must not be null for pImage=="-")

     @since 1.2.0
  */

  private void getBaseInfo(String pImage) throws InfoException {
    // create operation
    IMOperation op = new IMOperation();
    op.ping();
    //op.format("%m\n%w\n%h\n%g\n%W\n%H\n%G\n%z\n%r");
    op.format("%m\n%w\n%h\n");
    op.addImage(pImage);

    try {
      // execute ...
      IdentifyCmd identify = new IdentifyCmd(true);
      ArrayListOutputConsumer output = new ArrayListOutputConsumer();
      identify.setOutputConsumer(output);
      identify.run(op);

      // ... and parse result
      ArrayList<String> cmdOutput = output.getOutput();
      Iterator<String> iter = cmdOutput.iterator();

      iAttributes = new HashMap<String,Object>();
      iAttributes.put("Format",iter.next());
      iAttributes.put("Width",Integer.parseInt(iter.next()));
      iAttributes.put("Height",Integer.parseInt(iter.next()));
    } catch (Exception ex) {
      throw new InfoException(ex);
    }
  }

  public String getImageFormat() {
    return iAttributes.get("Format").toString();
  }

  public int getImageWidth() throws InfoException {
	 return (Integer)iAttributes.get("Width");
  }

  public int getImageHeight() throws InfoException {
	 return (Integer)iAttributes.get("Height");
  }

 
}
