package com.xiaozhameng.common.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.codec.digest.DigestUtils;


public class SignUtil {
	/**
	 * 形成签名后数据map
	 * @param reqMap
	 * @param key
	 * @return
	 */
	public static Map<String, String> signMap(Map<String, String> reqMap,String key) {

		Map<String, String> maps = sortMapByKey(reqMap);
		StringBuffer reqTex = new StringBuffer();
		
		Iterator<Entry<String, String>> it = maps.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> ent = it.next();
			if (isNotBlank(ent.getValue()))
				reqTex.append(ent.getKey()).append("=").append(ent.getValue())
						.append("&");
		}
		reqTex.deleteCharAt(reqTex.length() - 1);	
		String sign = DigestUtils.sha256Hex(reqTex.toString() + key);
		maps.put("sign", sign);
		return maps;
	}
	
	
	/**
	 * 使用 Map按key进行排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortMap = new TreeMap<String, String>(
				new MapKeyComparator());
		sortMap.putAll(map);

		return sortMap;
	}
	
	// 比较器类
	private static class MapKeyComparator implements Comparator<String> {
		public int compare(String str1, String str2) {
			return str1.compareTo(str2);
		}
	}
	
	public static boolean isNotBlank(String str) {
        int length;
        if ((str == null) || ((length = str.length()) == 0))
            return false;
        for (int i = 0; i < length; ++i) {
            if (!(Character.isWhitespace(str.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

}
