package com.baiwa.framework.common.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class NumberUtils {
	
	public static BigDecimal toBigDecimal(String input) {
		if (StringUtils.isNotBlank(input)) {
			return new BigDecimal(input);
		} else {
			return BigDecimal.ZERO;
		}
	}
	
}
