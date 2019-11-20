package com.Aditya.tkp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DialogueLoader {
	private String path;
	private String dialogue = "";
	int currentNumber;
	public DialogueLoader(String path) {
		this.path = path;
	}
	public String getDialogue(int dialogNumber) {
		InputStream is = this.getClass().getResourceAsStream(path);
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		System.out.println("Trying to load: "+path+"... succeeded!");
		try {
			while(currentNumber != dialogNumber) {
				
				String line = br.readLine();
				if(!line.isEmpty())
					if(line.charAt(0) == '#') {
						String[] tokens = line.split("#");
						currentNumber = Integer.parseInt(tokens[1]);
					}
			}
			System.out.println(currentNumber);
			if(currentNumber == dialogNumber) {
				String line = br.readLine();
				dialogue+=line;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dialogue;
	}
}
