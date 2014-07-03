package org.tools.life.service.common.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;
import org.tools.life.service.common.SafeService;
import org.tools.life.support.CommonConstants;
import org.tools.util.encrypt.AESCoder;
@Service
public class SafeServiceImpl implements SafeService {

	public  String encodeToHexString(String data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		return Hex.toHexString(AESCoder.encrypt(data.getBytes(), getKey()));
	}
	
	public  String decode(String enHexStr) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		return new String(AESCoder.descrypt(Hex.decode(enHexStr), getKey()));
	}
	
	private static byte[] getKey(){
		return Hex.decode(CommonConstants.getProperty("key.aes"));
	}
}
