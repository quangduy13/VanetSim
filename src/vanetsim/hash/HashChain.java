package vanetsim.hash;

import java.util.ArrayList;
import java.util.List;

import vanetsim.dto.CyclicDTO;
import vanetsim.ecc.EllipticCurve;
import vanetsim.ecc.Point;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;

public class HashChain {
	EllipticCurve eclip = new EllipticCurve(7,12,103);
	
	//Hash to choose 
	public CyclicDTO H1(long secret, long prime){
		int s = (int)(secret% prime);
		CyclicDTO cyclicDTO = new CyclicDTO();
		cyclicDTO = eclip.listMust1(new Point(13, 2, 1)).get(s);
		return cyclicDTO;
	}
	
	public int H2(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		String arr[] = {"a","b","c","d","e","f","g","i","j","k","l",
				"m"," ","n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z","0","1","2","3","4","5","6","7","8","9","(",";",")"};
		
		MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(str.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        
        System.out.println(hashtext);
		int a = 0;
		for(int i =0;i<hashtext.length();i++){
			for(int j=0;j<arr.length;j++){
				if(hashtext.substring(i,i+1).equals(arr[j]))
					a +=j;
			}
		}
		return a%11;
	}
}
