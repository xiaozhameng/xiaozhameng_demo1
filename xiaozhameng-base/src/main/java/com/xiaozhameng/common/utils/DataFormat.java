package com.xiaozhameng.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 数据格式化工具类
 */
public class DataFormat {

	/**
	 * 格式化邮箱为加密样式：类似
	 * 		aaa***@163.com
	 * @param email
	 * @return
	 */
	public static String emailFormat(String email) {
		if (email != null && !email.equals("")) {
			int sig = email.indexOf("@");
			email = email.substring(0, 3) + "***" + email.substring(sig);
			return email;
		} else {
			return null;
		}

	}

	/**
	 * 处理手机号码加密显示
	 * @param mobile
	 * @return
	 */
	public static String mobilephoneFormat(String mobile) {
		if (mobile == null || mobile.length() != 11) {
			return mobile;
		}
		mobile = mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
		return mobile;
	}

	public static String idCard(String card) {
		//modify by zl 2014-04-20  避免空指针  card==null.
		if (card == null || card.length() == 0 || card.length() < 10) {
			return card;
		}
		card = "**************" + card.substring(card.length() - 4, card.length());
		return card;
	}

	public static String bankCard(String card) {
		if (card == null || card.length() < 10) {
			return card;
		}
		card = card.substring(0, 3) + "***" + card.substring(card.length() - 4, card.length());
		return card;
	}

	public static String shorName(String name) {
		if (name == null || name.equals("")) {
			return name;
		}
		name = name.substring(0, name.length() - 1) + "*";
		return name;
	}

	/**
	 * 格式化金额		
	 * @param s
	 * @param len
	 * @return
	 */
	public static String formatMoney(String s, int len) {
		if (s == null || s.length() < 1) {
			return "";
		}
		NumberFormat formater = null;
		double num = Double.parseDouble(s);
		if (len == 0) {
			formater = new DecimalFormat("###,###");

		} else {
			StringBuffer buff = new StringBuffer();
			buff.append("###,###.");
			for (int i = 0; i < len; i++) {
				buff.append("#");
			}
			formater = new DecimalFormat(buff.toString());
		}
		String result = formater.format(num);
		if (result.indexOf(".") == -1) {
			result = result + ".00";
		} else {
			result = result;
		}
		return result;
	}

	/**
	 * 格式化金额	大于1万显示万元 	
	 * @param s
	 * @param len
	 * @return
	 */
	public static String formatMoneyFroW(BigDecimal investAmount) {
		String temp = "";
		if (investAmount.compareTo(new BigDecimal(10000)) == -1) {
			temp = "元";
			return DataFormat.formatMoney(investAmount.toString(), 2) + temp;
		} else {
			investAmount = investAmount.divide(new BigDecimal(10000));
			temp = "万";
			return DataFormat.formatMoney(investAmount.toString(), 2) + temp;
		}
	}
	public static String formatMoneyFroW2(BigDecimal investAmount) {
		String temp = "";
		if (investAmount.compareTo(new BigDecimal(10000)) == -1) {
			temp = "元";
			return DataFormat.formatMoney(investAmount.toString(), 2) + temp;
		}else if(investAmount.doubleValue()<100000000){
			double amount=investAmount.doubleValue()/10000;
			temp = "万";
			return reject1Amount(amount) + temp;
		}else{
			double amount=investAmount.doubleValue()/100000000d;
			temp = "亿";
			return amount + temp;
		}
	}
	public static String formatMoneyFroW2New(BigDecimal investAmount) {
		String temp = "";
		if (investAmount.compareTo(new BigDecimal(10000)) == -1) {
			double amount=investAmount.doubleValue()/10000;
			temp = "万";
			return reject1Amount(amount) + temp;
		}else if(investAmount.doubleValue()<100000000){
			double amount=investAmount.doubleValue()/10000;
			temp = "万";
			return reject1Amount(amount) + temp;
		}else{
			double amount=investAmount.doubleValue()/100000000d;
			temp = "亿";
			return amount + temp;
		}
	}
	/**
	 * 舍去 小数点后仅1/2位且等于0/00的数据
	 * @param amount
	 * @return
	 */
	private static String reject1Amount(double amount){
		String mAmount = String.valueOf(amount);
		try{
			int i = mAmount.indexOf(".");
			if(i == -1){
				return mAmount;
			}
			String mStr = mAmount.substring(i + 1);
			if((mStr.length() == 1 && "0".equals(mStr)) || (mStr.length() == 2 && "00".equals(mStr))){
				mAmount = mAmount.substring(0,i);
			}
		}catch(Exception e){
		}
		return mAmount;
	}
	public static String formatBankName(String bankName) {
		if (bankName != null) {
			if (!"中国银行".equals(bankName) && bankName.indexOf("中国") != -1) {
				bankName = bankName.substring(2);
			}
		} else {
			bankName = "";
		}
		return bankName;
	}

	public static String formatBankCardNo(String bankCardNo) {
		if (bankCardNo != null) {
			if (bankCardNo.length() > 5) {
				bankCardNo = "尾号" + bankCardNo.substring(bankCardNo.length() - 4);
			}
		} else {
			bankCardNo = "";
		}
		return bankCardNo;
	}
	public static String moneyFormat(BigDecimal money) {
		if(money == null){
			return "";
		}
		NumberFormat nf = new DecimalFormat("#,##0.00#元");
		String moneyStr = nf.format(money);
		return moneyStr;
	}
	/**
	 * 后缀不要元
	 * @param money
	 * @return
	 */
	public static String moneyFormat2(BigDecimal money) {
		if(money == null){
			return "";
		}
		NumberFormat nf = new DecimalFormat("#,##0.00#");
		String moneyStr = nf.format(money);
		return moneyStr;
	}
	
	/**
	 * 隐藏手机
	 * @param phone
	 * @return
	 */
	public static String hidePhone(String phone) {
		if (phone == null || "".equals(phone))
			return "";
		phone = phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
		return phone;
	}

	/**
	 * 隐藏真实姓名
	 * @param realName
	 * @return
	 */
	public static String hideRealName(String realName) {
		if (realName == null || "".equals(realName))
			return "";
		realName = realName.substring(0, 1) + "**";
		return realName;
	}

	/**
	 * 根据用户身份证号格式化用户的真实姓名
	 * @param realName
	 * @return "x先生，x女士"
	 */
	public static String FormatRealName(String realName,String idNo) {
		if (realName == null || "".equals(realName))
			return "";
		realName = realName.substring(0, 1) + CheckIdCardUtils.getGenderByIdCard(idNo);

		return realName;
	}

	/**
	 * 隐藏身份证号
	 * @param cardNo
	 * @return
	 */
	public static String hideCardNo(String cardNo) {
		if (cardNo == null || "".equals(cardNo))
			return "";
		cardNo = cardNo.substring(0, 4) + "*****" + cardNo.substring(cardNo.length() - 3);
		return cardNo;
	}

	/**
	 * 隐藏EMAIL
	 * @param email
	 * @return
	 */
	public static String hideEmail(String email) {
		if (email == null || "".equals(email))
			return "";
		email = email.substring(0, 3) + "***" + email.substring(email.indexOf("@"));
		return email;
	}

	/**
	 * 隐藏呢称
	 * @param email
	 * @return
	 */
	public static String hideNickName(String nickName) {
		if (nickName == null || "".equals(nickName))
			return "";
		if(nickName.length() < 3){
			nickName = nickName.substring(0, nickName.length() - 1) + "*";
		}else{
			nickName = nickName.substring(0, 3) + "**";
		}
		return nickName;
	}

	/**
	 * 格式化银行卡号，如：6226*****2621
	 * @param bankCardNo
	 * @return
	 */
	public static String hideBankCardNo(String bankCardNo) {
		if (bankCardNo == null || bankCardNo.trim().length() == 0)
			return "";

		String sRet = bankCardNo.trim();
		if (sRet.length() > 4)
			sRet = sRet.substring(0, 4) + "*****" + sRet.substring(sRet.length() - 4);
		else
			sRet = sRet.substring(0, 4) + "*****" + sRet;
		return sRet;
	}
	/**
	 * 获取真实姓名
	 * 
	 * @param realName
	 * @param idCardNo
	 * @return
	 */
	public static String getRealName(String realName,String idCardNo){
		if (realName == null || "".equals(realName))
			return "";
		String sex = idCardNo.substring(16, 17);
		if(Integer.parseInt(sex)%2==0){
			sex = "女士";
		}else{
			sex ="先生";
		}
		realName = realName.substring(0, 1) + sex;
		return realName;
	}	
	
	/**
	 * 保留2位小数
	 * @param money
	 * @return
	 */
	public static String moneyFormatStr2(BigDecimal money) {
		NumberFormat nf = new DecimalFormat("0.00");
		String moneyStr = nf.format(money);
		return moneyStr;
	}
	
	
	
	public static void main(String[] args) {
//		System.out.println(DataFormat.formatMoney("0", 2));
//		BigDecimal b=new BigDecimal(9900000000d);
//		System.out.println(b.doubleValue()/100000000d);
	}
}
