package paymentSystem.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class SecurityService {
	private static SecurityService instance=null;

	public static SecurityService getInstance(){
		if(instance==null)
			instance=new SecurityService();
		return instance;
	}
	public  String encrypt(String plaintext, String key) throws Exception {
		byte[] keyData = key.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] cyphertext = cipher.doFinal(plaintext.getBytes());
		return new Base64().encodeAsString(cyphertext);
	}

	public String decrypt(String cyphertext, String key) throws Exception {
		byte[] keyData = key.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] cyphertext2 = cipher.doFinal(new Base64().decode(cyphertext));
		return  new String(cyphertext2);
	}

	public static void main(String[] args){
		try{
			String key="admin";
			String plaintext="admin";
			System.out.println("Initial text="+plaintext);
			String cyphertext=SecurityService.getInstance().encrypt(plaintext, key);
			System.out.println("Cypher text="+cyphertext);
			System.out.println("Plain text="+SecurityService.getInstance().decrypt(cyphertext, key));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
