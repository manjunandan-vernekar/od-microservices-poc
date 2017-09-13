package com.officedepot;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.apache.tomcat.util.codec.binary.Base64;

import com.officedepot.domain.price.PriceRow;

public class Utilities {
	public static PriceRow deserializeFromString(String serializedObject){
		try {
		     byte b[] = Base64.decodeBase64(serializedObject.getBytes());
		     //System.out.println("getting byte array...");
		     ByteArrayInputStream bi = new ByteArrayInputStream(b);
		     //System.out.println("2" + bi.toString());
		     ObjectInputStream si = new ObjectInputStream(bi);
		     //System.out.println("3");
		     return (PriceRow) si.readObject();
		 } catch (Exception e) {
		     System.out.println(e);
		 }
		return null;
	}
}
