package com.glp.util.editor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * 处理Long类型数据
 * @author springpig
 * @date 2014-04-19
 */
public class LongEditor extends PropertiesEditor{
	
	@Override  
    public void setAsText(String text) throws IllegalArgumentException {
		text = StringUtils.trim(text);
		if(StringUtils.isNotBlank(text)){// check is blank or not
			setValue(Long.parseLong(text));
		}else{
			setValue(null);
		}
    }  
  
    @Override  
    public String getAsText() {  
    	return (getValue() != null ? getValue().toString() : null);
//      return getValue().toString();  
    }

}
