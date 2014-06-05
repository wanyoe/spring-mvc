package com.glp.util.editor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * 处理String类型数据
 * @author springpig
 * @date 2014-04-19
 */
public class StringEditor extends PropertiesEditor{
	
	@Override  
    public void setAsText(String text) throws IllegalArgumentException {
		text = StringUtils.trim(text);
		if(StringUtils.isBlank(text)){
			text = null;
		}
		setValue(text);  
    }  
  
    @Override  
    public String getAsText() {
    	return (getValue() != null ? getValue().toString() : null);
//        return getValue().toString();  
    }

}
