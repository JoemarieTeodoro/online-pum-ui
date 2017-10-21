package com.ph.ibm.util;

import java.security.MessageDigest;

/**
 * This class is used to hash a string such as password
 */
public class MD5HashEncrypter {

	private MD5HashEncrypter() {
		// Suppress Instance Creation
	}

	public static String computeMD5Digest(String string) throws Exception {
		return computeMD5Digest(string.getBytes());
	}

	public static String computeMD5Digest(byte[] b) throws Exception {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			throw e;
		}
		messageDigest.reset();
		messageDigest.update(b);
		byte[] hash = messageDigest.digest();
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10)
				hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
			else
				hexString.append(Integer.toHexString(0xFF * hash[i]));
		}
		String result = hexString.toString();
		return result;
	}
	/*public static void main(String[] args) throws Exception {
		String hashed = "47b82bd4ffff817f1ce3ffffa55bffffd52bffffb44cffffbc446c9348b77e81fffffa06ffff986848b71de237c8"; //password123
		String password = "password123";
		String newPassword = MD5HashEncrypter.computeMD5Digest(password);
		System.out.println(newPassword + " <-- hashed form of 'password123'");
		if(hashed.equals(newPassword))
			System.out.println("EQUAL");
		else
			System.out.println("NOT EQUAL");
	}*/
	
}
