package aracon;

//This file is part of the  ARACON Java library.
//Copyright (C) 2011 Jaouad Mousser
//				  Jaouad.Mousser@uni-konstanz.de
//               Univeristy of Konstanz
//               Department of Linguistics
//
//This software and database is being provided to you, the LICENSEE, by Jaouad Mousser University of Konstanz 
//under the following license. By obtaining, using and/or copying this software and database, you agree that 
//you have read, understood, and will comply with these terms and conditions.:
//Permission to use, copy, modify and distribute this software and database and its documentation for any purpose 
//and without fee or royalty is hereby granted, provided that you agree to comply with the following copyright 
//notice and statements, including the disclaimer, and that the same appear on ALL copies of the software, database
//and documentation, including modifications that you make for internal use or for distribution.
//ARACON 1.1 Copyright 2011 by Jaouad Mousser, University of Konstanz. All rights reserved.
//THIS SOFTWARE AND DATABASE IS PROVIDED "AS IS" AND THE AUTHOR AND THE UNIVERSITY OF KONSTANZ MAKE NO 
//REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED. BY WAY OF EXAMPLE, BUT NOT LIMITATION, THE AUTHOR AND THE 
//UNIVERSITY OF KONSTANZ MAKE NO REPRESENTATIONS OR WARRANTIES OF MERCHANT- ABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT THE USE OF THE LICENSED SOFTWARE, DATABASE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
//The name of the author may not be used in advertising or publicity pertaining to distribution of the software 
//and/or database. Title to copyright in this software, database and any associated documentation shall at all 
//times remain with the author and LICENSEE agrees to preserve same. 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jaouad_mousser
 */
public class Concode {

	private String arabicword;
	private HashMap<Character, Character> artr = new HashMap<Character, Character>();
	private HashMap<Character, Character> trar = new HashMap<Character, Character>();
	private HashMap<Character, Character> artrnu = new HashMap<Character, Character>();
	private HashMap<Character, Character> trnuar = new HashMap<Character, Character>();
	private HashMap<Character, Character> buk1buk2 = new HashMap<Character, Character>();
	private HashMap<Character, Character> buk2buk1 = new HashMap<Character, Character>();

	public Concode(String arabicword) {
		BufferedReader bf = null;
		try {
			this.arabicword = arabicword;
			String line;

			bf = new BufferedReader(new FileReader(new File("src/main/resources/conj_tres/ar_to_bw.tbl")));
			while ((line = bf.readLine()) != null) {
				String ar = line.substring(0, line.indexOf("\t")).trim();
				// System.out.println(ar);
				String buck1 = line.substring(line.lastIndexOf("\t"),
						line.length()).trim();
				String buck2 = line.substring(line.indexOf("\t"),
						line.lastIndexOf("\t")).trim();
				artr.put(ar.charAt(0), buck1.charAt(0));
				trar.put(buck1.charAt(0), ar.charAt(0));
				artrnu.put(ar.charAt(0), buck2.charAt(0));
				trnuar.put(buck2.charAt(0), ar.charAt(0));
				buk1buk2.put(buck1.charAt(0), buck2.charAt(0));
				buk2buk1.put(buck2.charAt(0), buck1.charAt(0));

			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Concode.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (IOException ex) {
			Logger.getLogger(Concode.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			try {
				bf.close();
			} catch (IOException ex) {
				Logger.getLogger(Concode.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}

	}

	public String buckNu2Arabic() {
		StringBuffer strbf = new StringBuffer();

		char[] charArr = arabicword.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			char toReplace = charArr[i];
			if (trnuar.containsKey(toReplace)) {
				strbf.append(trnuar.get(toReplace).toString());
			} else {
				strbf.append(toReplace);
			}
		}
		return strbf.toString();

	}

	public String arabic2buck1() {
		StringBuffer strbf = new StringBuffer();

		char[] charArr = arabicword.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			char toReplace = charArr[i];
			if (artr.containsKey(toReplace)) {
				strbf.append(artr.get(toReplace).toString());
			} else {
				strbf.append(toReplace);
			}
		}
		return strbf.toString();

	}

	public String arabic2buckNu() {
		StringBuffer strbf = new StringBuffer();

		char[] charArr = arabicword.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			char toReplace = charArr[i];
			if (trnuar.containsKey(toReplace)) {
				strbf.append(trnuar.get(toReplace).toString());
			} else {
				strbf.append(toReplace);
			}
		}
		return strbf.toString();

	}

	public String buck12Arabic() {
		StringBuilder strbf = new StringBuilder();

		char[] charArr = arabicword.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			char toReplace = charArr[i];
			if (trar.containsKey(toReplace)) {
				strbf.append(trar.get(toReplace).toString());
			} else {
				strbf.append(toReplace);
			}
		}
		return strbf.toString();

	}

	public String buck12Nubuck() {
		StringBuilder strbf = new StringBuilder();

		char[] charArr = arabicword.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			char toReplace = charArr[i];
			if (buk1buk2.containsKey(toReplace)) {
				strbf.append(buk1buk2.get(toReplace).toString());
			} else {
				strbf.append(toReplace);
			}
		}
		return strbf.toString();
	}

	public String Nubuck2buck1() {
		StringBuilder strbf = new StringBuilder();

		char[] charArr = arabicword.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			char toReplace = charArr[i];
			if (buk2buk1.containsKey(toReplace)) {
				strbf.append(buk2buk1.get(toReplace).toString());
			} else {
				strbf.append(toReplace);
			}
		}
		return strbf.toString();
	}

	public static void main(String[] args) {
		Concode cn = new Concode("<");
		System.out.println(cn.buck12Arabic());
	}

}