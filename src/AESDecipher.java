import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

public class AESDecipher {
	private static HexToString conversor;
	
	public static String toHexString(byte[] array) {
		return DatatypeConverter.printHexBinary(array);
	}
	
	public static byte[] toByteArray(String s) {
		return DatatypeConverter.parseHexBinary(s);
	}
	
	public static SecretKeySpec getSecretKey (String passwd) throws Exception{
		byte[] dataBytes = passwd.getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(dataBytes,0,passwd.length());
		byte[] mdbytes = md.digest();
		
		return new SecretKeySpec(Arrays.copyOfRange(mdbytes, 0, 16), "AES");
	}
	
	public static void main(String[] args) throws Exception{
		conversor = new HexToString();
		SecretKeySpec skeySpec = getSecretKey(args[0]);
		
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
						
		String hexString = args[1];
		byte[] stringbizarra = conversor.hexStringToByteArray(hexString);
		
		byte[] deciphered = cipher.doFinal(stringbizarra);
		
		
		
		System.out.println("encrypted string: "+(new String(deciphered)));
	}
}
