package com.note.apple.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

/**
 * @author Liu Ke
 *
 */

public class NoteUtil {
	
	/**
	 * 随机生成id
	 * @return
	 */
	public static String createId() {
		UUID uuid=UUID.randomUUID();
		//随机生成id并将其转换成字符串,将-替换为空
		return uuid.toString().replace("-", "");
	}
	
	/**
	 * 将传入的密码加密处理
	 * @param src	明文字符串
	 * @return		加密后字符串的结果
	 * @throws NoSuchAlgorithmException 
	 */
	public static String md5(String src) throws NoSuchAlgorithmException {
		MessageDigest md=MessageDigest.getInstance("MD5");
		byte[] output=md.digest(src.getBytes());//将字符串类型转换成字节数组
		//将MD5处理结果转换成字符串
		String s=Base64.encodeBase64String(output);
		return s;
	}
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(createId());
		System.out.println(md5("123456"));
		System.out.println(md5("14514445774244777"));
	}
	
}
