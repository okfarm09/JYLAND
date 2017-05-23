package project.jyland.helper;

import java.io.UnsupportedEncodingException;

import org.bouncycastle.jcajce.provider.digest.Keccak.DigestKeccak;
import org.bouncycastle.util.encoders.Hex;

public class Sha {
	
	public static String sha3(String key) {
		DigestKeccak md = new DigestKeccak(512);
		try {
			md.update(key.getBytes("UTF-8"));
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] digest=md.digest();
		return Hex.toHexString(digest);
	}

}
