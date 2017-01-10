package com.xiaozhameng.common.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @history
 * @see
 * @since v1.1
 */
public class DataUtil {
	/**
	 * 返回最小区间
	 * 返回格式
	 * {min:XXX,max:XXX}
	 * -1不限制
	 * @param min1
	 * @param min2
	 * @param max1
	 * @param max2
	 * @return
	 */
	public static Map<String, BigDecimal> getMinInterval(BigDecimal min1, BigDecimal min2, BigDecimal max1,
			BigDecimal max2) {
		BigDecimal min = null;
		BigDecimal max = null;
		if (min1 != null) {
			if (min2 != null) {
				if (min1.compareTo(min2) == -1) {
					min = min2;
				} else {
					min = min1;
				}
			} else {
				min = min1;
			}
		} else {
			if (min2 != null) {
				min = min2;
			} else {
				min = new BigDecimal(-1);
			}
		}

		if (max1 != null) {
			if (max2 != null) {
				if (max1.compareTo(max2) == -1) {
					max = max1;
				} else {
					max = max2;
				}
			} else {
				max = max1;
			}
		} else {
			if (max2 != null) {
				max = max2;
			} else {
				max = new BigDecimal(-1);
			}
		}
		if (max.compareTo(new BigDecimal(-1)) != 0 && min.compareTo(new BigDecimal(-1)) != 0
				&& max.compareTo(min) == -1) {
			min = new BigDecimal(-1);
			max = new BigDecimal(-1);
		}
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("min", min);
		map.put("max", max);
		return map;
	}

	/**
	 * 判断输入amt是否在区间内
	 * @param amt
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isInInterval(BigDecimal amt, BigDecimal min, BigDecimal max) {
		if (amt == null || min == null || max == null) {
			return false;
		} else {
			if (min.compareTo(new BigDecimal(-1)) == 0 && max.compareTo(new BigDecimal(-1)) == 0) {
				return true;
			}
			if (min.compareTo(new BigDecimal(-1)) != 0 && max.compareTo(new BigDecimal(-1)) != 0) {
				if (min.compareTo(max) == 1) {
					return false;
				}
			}

			if (min.compareTo(new BigDecimal(-1)) != 0 && amt.compareTo(min) == -1) {
				return false;
			}
			if (max.compareTo(new BigDecimal(-1)) != 0 && amt.compareTo(max) == 1) {
				return false;
			}
			return true;

		}

	}

//	public static void main(String[] args) {
//		System.out.println(DataUtil.getMinInterval(new BigDecimal(100), new BigDecimal(100), new BigDecimal(1500),
//				new BigDecimal(1600)).toString());
//		System.out.println(DataUtil.getMinInterval(new BigDecimal(100), new BigDecimal(1000), new BigDecimal(1500),
//				new BigDecimal(1600)).toString());
//		System.out.println(DataUtil.getMinInterval(new BigDecimal(100), new BigDecimal(600), new BigDecimal(500),
//				new BigDecimal(1600)).toString());
//
//		System.out.println(DataUtil.getMinInterval(null, null, null, new BigDecimal(1600)).toString());
//		System.out.println(DataUtil.isInInterval(new BigDecimal(600), new BigDecimal(100), new BigDecimal(-1)));
//
//	}
}
