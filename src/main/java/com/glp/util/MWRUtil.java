package com.glp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MWRUtil {

	private static Logger logger = LoggerFactory.getLogger(MWRUtil.class);

	public static List<String> getCategoryIdList(String categoryIds) {
		logger.info("MWRUtil: getCategoryIdList(): categoryIds: " + categoryIds);
		List<String> catIdsList = new ArrayList<String>();
		if (StringUtils.isEmpty(categoryIds)) {
			return catIdsList;
		}
		String[] ids = categoryIds.split(",");
		String catIds = "";
		if (ids != null && ids.length > 0) {
			if (ids.length == 1) {
				catIds = ids[0];
			} else if (ids.length > 1) {
				catIds = ids[ids.length - 1];
			}
		}
		getMultiCatIdList(catIdsList, catIds);
		return catIdsList;
	}

	public static HashMap<String, List<String>> getTopAndFinalCategoryIdList(
			String categoryIds) {
		logger.info("MWRUtil: getTopAndFinalCategoryIdList(): categoryIds: "
				+ categoryIds);
		HashMap<String, List<String>> idsMap = new HashMap<String, List<String>>();
		List<String> topIdsList = new ArrayList<String>();
		List<String> finalIdsList = new ArrayList<String>();
		if (StringUtils.isEmpty(categoryIds)) {
			return idsMap;
		}
		String[] ids = categoryIds.split(",");
		String topCatId = "";
		String finalCatIds = "";
		if (ids != null && ids.length > 0) {
			if (ids.length == 1) {
				topCatId = ids[0];
				finalCatIds = topCatId;
			} else if (ids.length > 1) {
				topCatId = ids[0];
				finalCatIds = ids[ids.length - 1];
			}
		}
		getMultiCatIdList(finalIdsList, finalCatIds);

		topIdsList.add(topCatId);
		idsMap.put("TopCatIds", topIdsList);
		idsMap.put("FinalCatIds", finalIdsList);
		return idsMap;
	}

	public static void generateCatStrList(List<String> strList, String strs) {
		if (!StringUtils.isEmpty(strs)) {
			strs = strs.trim();
			int indexPar = strs.indexOf("[");
			int indexComma = strs.indexOf(",");
			int indexSemiColon = strs.indexOf(";");
			if (indexComma < 0 && indexSemiColon < 0) {
				if (indexPar >= 0) {
					String subStr = strs.substring(1, (strs.length() - 1));
					if (!StringUtils.isEmpty(subStr)) {
						strList.add(subStr);
					}
				} else {
					if (!StringUtils.isEmpty(strs)) {
						strList.add(strs);
					}
				}
			} else {
				String[] strsArray = null;
				if (indexComma >= 0) {
					strsArray = strs.split(",");
				} else if (indexSemiColon >= 0) {
					strsArray = strs.split(";");
				}
				if (strsArray != null && strsArray.length > 0) {
					for (String str : strsArray) {
						if (!StringUtils.isEmpty(str)) {
							strList.add(str);
						}
					}
				}
			}
		}
	}

	

	private static void getMultiCatIdList(List<String> catIdsList, String catIds) {
		if (!StringUtils.isEmpty(catIds)) {
			if (catIds.trim().startsWith("[") && catIds.trim().endsWith("]")) {
				String[] multiIdsArray = catIds
						.substring(1, (catIds.length() - 1)).trim().split(";");
				if (multiIdsArray != null && multiIdsArray.length > 0) {
					for (String multiId : multiIdsArray) {
						if (!StringUtils.isEmpty(multiId)) {
							catIdsList.add(multiId.trim());
						}
					}
				}
			} else {
				catIdsList.add(catIds.trim());
			}
		}
	}

	

}
