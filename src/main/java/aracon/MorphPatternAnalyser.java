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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aracon.Concode;

public class MorphPatternAnalyser {
	private BufferedReader bf = null;
	private String verb;
	private Map<String, String> Eilla_Map = new HashMap<String, String>();
	private Map<String, String> naAqiS_map = new HashMap<String, String>();
	private String F = "[O|b|t|v|j|H|x|d|\\*|r|z|T|S|D|Z|s|l|m|n|h|E|g|\\$|y|w|f|q|k|W|I|\\}|']{1}";// Y
																									// was
																									// delte
	private String E = "[O|b|t|v|j|H|x|d|\\*|r|z|T|S|D|Z|s|l|m|n|h|E|g|\\$|y|w|f|q|k|W|I|\\}|']{1}";
	private String L = "[O|b|t|v|j|H|x|d|\\*|r|z|T|S|D|Z|s|l|m|n|h|E|g|\\$|y|w|f|q|k|W|I|\\}|']{1}";
	private String vow = "[aiuo\\^e]";
	private String IifofaEala = "(Ii)(" + F + ")(\\^)(" + F + ")(a)(" + E
			+ ")(a)(" + L + ")(a)";// إتَّقى إتَّعض
	private String IifoTaEala = "(Ii)(" + F + ")(o)" + "(Ta)(" + E + ")(a)("
			+ L + ")(a)";// IiSoTadama
	private String IifodaEala = "(Ii)(" + F + ")(o)" + "(da)(" + E + ")(a)("
			+ L + ")(a)";// Iizodahara
	private String faEoEa = "(" + F + ")(a)(" + E + ")(\\^)(" + E + ")(a)"; // سدَّ
																			// مدَّ
	private String OafaEoEa = "(O)+(a)(" + F + ")(a)(" + E + ")(\\^)(" + E
			+ ")(a)"; // أمدَّ
	private String faEala = "(" + F + ")" + "(a)" + "(" + E + ")" + "(a)" + "("
			+ L + ")" + "(a)"; // فعل
	private String waEala = "(w"   + ")" + "(a)" + "(" + E + ")" + "(a)" + "("
			+ L + ")" + "(a)"; // waqafa, walaqa, warada
	private String faAla = "(" + F + ")(a)(A)(" + L + ")(a)"; // قال سال
	private String IisotafaAla = "(Iisota)(" + F + ")(a)(A)(" + L + ")(a)";// إستقال
	private String OafaAla = "(Oa)(" + F + ")(a)(A)(" + L + ")(a)";
	private String faAEala = "(" + F + ")(a)(A)(" + E + ")(a)(" + L + ")(a)"; // PROBLEM
																				// فاعل
	private String faEila = "(" + F + ")(a)" + "(" + E + ")(i)" + "(" + L
			+ ")(a)";// فعِل
	private String faEula = "(" + F + ")(a)" + "(" + E + ")(u)" + "(" + L
			+ ")(a)";// فعُل
	private String faEolala = "(" + F + ")(a)" + "(" + E + ")(o)" + "(" + L
			+ ")(a)" + "(" + L + ")(a)"; // فعلل
	private String tafaEolala = "(t)" + "(a)" + "(" + F + ")(a)" + "(" + E
			+ ")(o)" + "(" + L + ")(a)" + "(" + L + ")(a)"; // تفاعل
	private String tafaEEala = "(ta)" + "(" + F + ")(a)" + "(" + E + ")(\\^)("
			+ E + ")(a)" + "(" + L + ")(a)";// تفعَّل
	private String tafaAEala = "(ta)" + "(" + F + ")(a)(A)(" + E + ")(a)(" + L
			+ ")(a)"; // تفاعل
	private String IisotafoEala = "(Iisota)" + "(" + F + ")(o)" + "(" + E
			+ ")(a)" + "(" + L + ")(a)";// استحمَّّ
	private String faEEala = "(" + F + ")(a)" + "(" + E + ")(\\^)(" + E
			+ ")(a)(" + L + ")(a)";// فعَّل
	private String IinofaEala = "(Ii)" + "(n)" + "(o)(" + F + ")(a)(" + E
			+ ")(a)(" + L + ")(a)";// إنفعل
	private String IifotaEala = "(Ii)(" + F + ")(o)" + "(t)" + "(a)(" + E
			+ ")(a)(" + L + ")(a)"; //
	private String OafoEala = "(Oa)(" + F + ")(o)(" + E + ")(a)(" + L + ")(a)"; // أفعل
	private String IifoEalola = "(Ii)(" + F + ")(o)(" + E + ")(a)(" + L
			+ ")(\\^)(" + L + ")(a)"; // إفعلَّ
	private String IifoEaAlola = "(Ii)(" + F + ")(o)(" + E + ")(aA)(" + L
			+ ")(\\^)(" + L + ")(a)";// إفعال
	private String IifoEalalola = "(Ii)(" + F + ")(o)(" + E + ")(a)(" + L
			+ ")(a)(" + L + ")(\\^)(" + L + ")(a)"; // إفعللَّفعنل
	private String IifoEawoEala = "(Ii)(" + F + ")(o)(" + E + ")(a)" + "(w)"
			+ "(o)(" + E + ")(a)(" + L + ")(a)"; // إعشوشب
	private String IifoEaAla = "(Ii)(" + F + ")(o)(" + E + ")(a)(A)(" + L
			+ ")(a)"; // إغتاب
	private String faEoEaY = "(" + F + ")(a)(" + E + ")(\\^)(" + E
			+ ")(a)([YA])";// سلَّى
	private String tafaEoEaY = "ta" + faEoEaY;
	private String IisotafoEaY = "(Iisota)(" + F + ")(o)(" + L + ")(a)([YA])"; // إستحلى
	private String faEaY = "(" + F + ")(a)(" + E + ")(a)([YA])"; // علا على قلى
	private String OafoEaY = "(Oa)(" + F + ")(o)(" + E + ")(a)([YA])";
	private String IinofaEaY = "(Iino)" + faEaY;// انقضى
	private String IifotaEaY = "(Ii)(" + F + ")(ota)(" + E + ")(a)([YA])";// اعتلى
	private String tafaAEaY = "(ta)(" + F + ")(aA)(" + E + ")(a)([YA])";// تمادى
	private String faAEoEa = "(" + F + ")(aA)(" + E + ")(\\^)(" + E + ")(a)"; // حاجَّ
	private String tafaAEoEa = "ta" + faAEoEa;// َّتحاج
	private String IifofaEaY = "(Ii)(" + F + ")(\\^)(" + F + ")(a)(" + E
			+ ")(a)[YA]"; // اتَّقى
	private String wazn = null;
	private String faA = null;
	private String faA2 = null;
	private String Eayn = null;
	private String Eayn2 = null;
	private String laAm = null;
	private String laAm2 = null;
	private String laAm3 = null;
	private String root = null;

	public MorphPatternAnalyser(String VERB) {
		this.verb = normalize(VERB);
	}

	// Normalize the transcription of Arabic character for the next analyse
	// steps
	public String normalize(String word) {
		String char1 = "";
		char[] charArr = word.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			if (charArr[i] == '~') {
				char1 += "^" + Character.toString(charArr[i - 1]);
			} else if (charArr[i] == '|') {
				char1 += "OaA";
			}
			// else if(charArr[i]=='Y'){ //replace alif with alif+a vocal
			// char1 +="ya";
			// }
			else {
				char1 += Character.toString(charArr[i]);
			}

		}
		return char1;
	}

	public String guessVerbForm(String input) {
		char[] uv = dediacret(input).toCharArray();
		String guessed = null;
		if (uv.length <= 1) {
			throw new IllegalArgumentException(
					"Input must have at least 2 consonants!");
		} else if (uv.length == 2) {
			if (Character.toString(uv[1]).matches("[A|'}W]")) {
				throw new IllegalArgumentException("Illegal input!");
			} else if (uv[0] == '|') {
				guessed = uv[0] + uv[1] + "a"; // OaAla
			} else {
				guessed = uv[0] + "a" + uv[1] + "~a";// mad
			}
		} else if (uv.length == 3) {
			if (uv[1] == 'A') {
				guessed = uv[0] + "a" + uv[1] + uv[2] + "a";// maAl
			} else if (uv[2] == '~') {// mad~a
				guessed = uv[0] + "a" + uv[1] + uv[2] + "a";
			} else if (Character.toString(uv[2]).matches("[Y|A]")) {// qlY
				guessed = uv[0] + "a" + uv[1] + "aY";
			} else if (uv[1] == uv[2]) {
				guessed = uv[0] + "a" + uv[1] + "a" + uv[2] + "a";// qAl
			} else {
				// need refinement to differenciate faEila faEala faEula on the
				// basis of what the use have put
				guessed = uv[0] + "a" + uv[1] + "a" + uv[2] + "a";// faEla
			}
		} else if (uv.length == 4) {
			if (uv[2] == '~') {
				if (Character.toString(uv[3]).matches("[YA]")) {
					guessed = uv[0] + "a" + uv[1] + uv[2] + "a" + uv[3];// sal~aY
				} else {
					guessed = uv[0] + "a" + uv[1] + uv[2] + "a" + uv[3] + "a";// sal~aHa
				}
			} else if (uv[1] == 'A') {
				guessed = uv[0] + "a" + uv[1] + uv[2] + "a" + uv[3] + "a"; // faAEala
			} else if (uv[3] == '~') {
				guessed = uv[0] + "a" + uv[1] + "a" + uv[2] + uv[3] + "a";// Oamal~a
			} else if (Character.toString(uv[3]).matches("[YA]")) {
				guessed = uv[0] + "a" + uv[1] + "o" + uv[2] + "a" + uv[3];
			} else {
				guessed = uv[0] + "a" + uv[1] + "o" + uv[2] + "a" + uv[3] + "a";// OafoEala
			}
		} else if (uv.length == 5) {
			if (Character.toString(uv[0]).matches("[IAO]")) {
				if (uv[4] == 'Y') {
					guessed = "Ii" + uv[1] + "o" + uv[2] + "a" + uv[3] + "aY";// IiHotamaY
				} else if (uv[4] == '~') {
					guessed = "Ii" + uv[1] + "o" + uv[2] + "a" + uv[3] + uv[4]
							+ "a";// IiHotal~a
				} else if (uv[2] == '~') {
					guessed = "Ii" + uv[1] + uv[2] + "a" + uv[3] + "a" + uv[4]
							+ "a"; // Iid~xara
				} else if (uv[3] == 'A') {
					guessed = "Ii" + uv[1] + "o" + uv[2] + "a" + uv[3] + uv[4]
							+ "a";// IirotaAha
				} else {
					guessed = "Ii" + uv[1] + "o" + uv[2] + "a" + uv[3] + "a"
							+ uv[4] + "a";// IiHotalama
				}
			} else if (uv[0] == 't') {
				if (uv[2] == 'A') {
					if (Character.toString(uv[4]).matches("[YA]")) {// tamaAdaY
						guessed = uv[0] + "a" + uv[1] + "a" + uv[2] + uv[3]
								+ "a" + uv[4];
					} else if (uv[4] == '~') {
						guessed = uv[0] + "a" + uv[1] + "a" + uv[2] + uv[3]
								+ uv[4] + "a";// taHaAb~a
					} else {
						guessed = uv[0] + "a" + uv[1] + "a" + uv[2] + uv[3]
								+ "a" + uv[4] + "a";// taEaAmala
					}
				} else if (uv[3] == '~') {
					if (Character.toString(uv[4]).matches("[YA]")) {
						guessed = uv[0] + "a" + uv[1] + "a" + uv[2] + uv[3]
								+ "a" + uv[4];// tasal~aY
					} else {
						guessed = uv[0] + "a" + uv[1] + "a" + uv[2] + uv[3]
								+ "a" + uv[4] + "a";// tasal~ama
					}
				} else {
					guessed = uv[0] + "a" + uv[1] + "a" + uv[2] + "o" + uv[3]
							+ "a" + uv[4] + "a";// tadaHoraja
				}

			}
		} else if (uv.length == 6) {
			if (Character.toString(uv[0]).matches("[AOI]") && uv[1] == 's'
					& uv[2] == 't') {
				if (Character.toString(uv[5]).matches("[AY]")) {
					guessed = "Ii" + uv[1] + "o" + uv[2] + "a" + uv[3] + "o"
							+ uv[4] + "a" + uv[5];// IisotaloqaY
				} else if (uv[5] == '~') {
					guessed = "Ii" + uv[1] + "o" + uv[2] + "a" + uv[3] + "a"
							+ uv[4] + uv[5] + "a";// Iisotamad~a
				} else if (uv[4] == 'A') {
					guessed = "Ii" + uv[1] + "o" + uv[2] + "a" + uv[3] + "a"
							+ uv[4] + uv[5] + "a"; // IisotaqaAla
				} else {
					guessed = "Ii" + uv[1] + "o" + uv[2] + "a" + uv[3] + "o"
							+ uv[4] + "a" + uv[5] + "a";// IisotaEobada
				}
			} else if (Character.toString(uv[0]).matches("[AOI]")
					&& uv[1] != 's' & uv[2] != 't') {
				if (uv[5] == '~') {
					if (uv[3] == 'A') {
						guessed = "Ii" + uv[1] + "o" + uv[2] + "a" + uv[3]
								+ uv[4] + uv[5] + "a";// IifoEaAl~a
					} else {
						guessed = "Ii" + uv[1] + "o" + uv[2] + "a" + uv[3]
								+ "a" + uv[4] + uv[5] + "a"; // IifoEalal~a
					}
				} else {
					guessed = "Ii" + uv[1] + "o" + uv[2] + "a" + uv[3] + "o"
							+ uv[4] + "a" + uv[5] + "a";// IiEo$awo$aba
				}

			}
		} else {
			throw new IllegalArgumentException(
					"Your input don't matches any of the standard Arabic Pattern! Please try again!");
		}
		return guessed;
	}

	public String dediacret(String str) {
		return str.replaceAll(vow, "");
	}

	public void get_pattern() {
		Matcher m = Pattern.compile(waEala).matcher(verb);
		if (m.matches()) {
			wazn = "waEaLa";
			faA = m.group(1);
			Eayn = m.group(3);
			laAm = m.group(5);
			root = faA + Eayn + laAm;
		}
		else {	
		m = Pattern.compile(faEala).matcher(verb);
		if (m.matches()) {
			wazn = "FaE0La";
			faA = m.group(1);
			Eayn = m.group(3);
			laAm = m.group(5);
			root = faA + Eayn + laAm;
		} 
		else {
			m = Pattern.compile(faAEala).matcher(verb);
			if (m.matches()) {
				wazn = "FaAEaLa";
				faA = m.group(1);
				Eayn = m.group(4);
				laAm = m.group(6);
				root = faA + Eayn + laAm;

			} else {
				m = Pattern.compile(tafaAEala).matcher(verb);
				if (m.matches()) {
					wazn = "taFaAEaLa";
					faA = m.group(2);
					Eayn = m.group(5);
					laAm = m.group(7);
					root = faA + Eayn + laAm;
				}

				else {
					m = Pattern.compile(faEila).matcher(verb);
					if (m.matches()) {
						wazn = "faE0la";
						faA = m.group(1);
						Eayn = m.group(3);
						laAm = m.group(5);
						root = faA + Eayn + laAm;

					} else {
						m = Pattern.compile(faEula).matcher(verb);
						if (m.matches()) {
							wazn = "faE0la";
							faA = m.group(1);
							Eayn = m.group(3);
							laAm = m.group(5);
							root = faA + Eayn + laAm;
						} else {
							m = Pattern.compile(faEolala).matcher(verb);
							if (m.matches()) {
								faA = m.group(1);
								Eayn = m.group(3);
								laAm = m.group(5);
								laAm2 = m.group(7);
								if (faA.equals(laAm) && !Eayn.equals(laAm2)) {
									faA2 = laAm;
									laAm = laAm2;
									wazn = "FaEoFala"; // salosaEa
									root = faA + Eayn + faA2 + laAm;
								} else if (faA.equals(laAm)
										&& Eayn.equals(laAm2)) {
									faA2 = laAm;
									laAm = laAm2;
									Eayn2 = laAm2;
									wazn = "FaEoFaEa"; // salosala
									root = faA + Eayn + laAm;
								} else if (laAm.equals("w")
										&& !laAm2.equals(Eayn)) {
									laAm = laAm2;
									laAm2 = null;
									wazn = "FaEowaLa";
									root = faA + Eayn + laAm;

								} else if (faA.equals("O")
										&& !Eayn.equals(laAm)) {
									faA = Eayn;
									Eayn = laAm;
									laAm = laAm2;
									wazn = "OaFoEaLa";
									root = faA + Eayn + laAm;
								} else if (faA.equals("O") && Eayn.equals(laAm)) {
									laAm = laAm2;
									wazn = "faEoEala";
									root = faA + Eayn + laAm;
								} else if (!faA.equals("m")
										&& !laAm.equals(faA)
										&& laAm2.equals("n")) {
									laAm2 = null;
									wazn = "FaEoLana"; // Eaqolana
									root = faA + Eayn + laAm;
								} else if (faA.equals("m") && !laAm.equals(faA)) {
									faA = Eayn;
									Eayn = laAm;
									laAm = laAm2;
									wazn = "maFoEaLa";
									root = faA + Eayn + laAm;
								} else if (!laAm.equals(faA)
										&& Eayn.equals("w")) {
									Eayn = laAm;
									laAm = laAm2;
									wazn = "FawoEaLa";
									root = faA + Eayn + laAm;

								} else if (!laAm.equals(faA)
										&& Eayn.equals("y")) {
									Eayn = laAm;
									laAm = laAm2;
									wazn = "FayoEala";
									root = faA + Eayn + laAm;
								} else if (!laAm.equals(faA)
										&& laAm.equals("y")) {
									laAm = laAm2;
									laAm2 = null;
									wazn = "FaEoyala";
									root = faA + Eayn + laAm;
								} else if (!laAm.equals(faA)
										&& laAm.equals("w")) {
									laAm = laAm2;
									laAm2 = null;
									wazn = "FaEowala";
									root = faA + Eayn + laAm;
								} else if (!laAm2.equals(faA)
										&& laAm.equals("n")) {
									laAm = laAm2;
									laAm2 = null;
									wazn = "FaEonaLa"; // qalonasa
									root = faA + Eayn + laAm;

								} else if (Eayn.equals(laAm)) {
									Eayn2 = laAm;
									laAm = laAm2;
									wazn = "faEoEala";
									root = faA + Eayn + laAm;
								}

								else {
									wazn = "FaEoLaLa"; // daHoraja
									root = faA + Eayn + laAm + laAm2;
								}
							}

							else {
								m = Pattern.compile(faEEala).matcher(verb);
								if (m.matches()) {
									faA = m.group(1);
									Eayn = m.group(3);
									Eayn2 = m.group(5);
									laAm = m.group(7);
									wazn = "FaEoEaLa";
									root = faA + Eayn + laAm;
								}

								else {
									m = Pattern.compile(tafaEolala).matcher(
											verb);
									if (m.matches()) {
										faA = m.group(3);
										Eayn = m.group(5);
										laAm = m.group(7);
										laAm2 = m.group(9);
										wazn = "taFaEoLaLa";
										root = faA + Eayn + laAm;

										// if(faA.equals(laAm)){
										// faA2 = laAm;
										// laAm = laAm2;
										// wazn = "taFaEoFaLa";
										// root = faA+Eayn+laAm;
										// }
										if (faA.equals("O")
												&& !laAm.equals(faA)) {
											faA = Eayn;
											Eayn = laAm;
											laAm = laAm2;
											wazn = "taOaFoEaLa";
											root = faA + Eayn + laAm;
										} else if (!faA.equals("m")
												&& laAm2.equals("n")
												&& !laAm.equals(faA)) {
											laAm2 = null;
											wazn = "taFaEoLana";
											root = faA + Eayn + laAm;
										} else if (faA.equals("m")
												&& !laAm.equals(faA)) {
											faA = Eayn;
											Eayn = laAm;
											laAm = laAm2;
											laAm2 = null;
											wazn = "tamaFoEaLa";
											root = faA + Eayn + laAm;

										} else if (!laAm.equals("faA")
												&& laAm.equals("n")) {
											laAm = laAm2;
											laAm2 = null;
											wazn = "taFaEonala";
											root = faA + Eayn + laAm;
										} else if (!laAm.equals("faA")
												&& Eayn.equals("w")) {
											Eayn = laAm;
											laAm = laAm2;
											laAm2 = null;
											wazn = "taFawoEala";
											root = faA + Eayn + laAm;
										} else if (!laAm.equals("faA")
												&& Eayn.equals("y")) {
											Eayn = laAm;
											laAm = laAm2;
											laAm2 = null;
											wazn = "taFayoEala";
											root = faA + Eayn + laAm;

										} else if (!laAm.equals("faA")
												&& laAm.equals("w")) {
											laAm = laAm2;
											laAm2 = null;
											wazn = "taFaEowala";
											root = faA + Eayn + laAm;
										} else if (!laAm.equals("faA")
												&& laAm.equals("y")) {
											laAm = laAm2;
											laAm2 = null;
											wazn = "taFaEoyala";
											root = faA + Eayn + laAm;
										} else if (!laAm2.equals(faA)
												&& laAm.equals("n")) {
											laAm = laAm2;
											laAm2 = null;
											wazn = "taFaEonaLa"; // taqalonasa
											root = faA + Eayn + laAm;
										} else if (faA.equals("m")
												&& !laAm.equals("faA")) {
											faA = Eayn;
											Eayn = laAm;
											laAm = laAm2;
											laAm2 = null;
											wazn = "tamaFoEaLa";

										} else if (!laAm.equals(faA)
												&& laAm.equals("n")) {
											laAm = laAm2;
											laAm2 = null;
											wazn = "taFaEonaLa";
											root = faA + Eayn + laAm;

										} else if (faA.equals(laAm)
												&& Eayn.equals(laAm2)) {
											faA2 = laAm;
											laAm = null;
											Eayn2 = laAm2;
											wazn = "taFaEoFaEa"; // tasalosala
											root = faA + Eayn + Eayn2;

										} else if (faA.equals(laAm)
												&& !Eayn.equals(laAm2)) {
											faA2 = laAm;
											laAm = laAm2;
											wazn = "taFaEoFala"; // salosaEa
											root = faA + Eayn + faA2 + laAm;
										} else {

											wazn = "taFaEoLaLa";
											root = faA + Eayn + laAm + laAm2;
										}
									} else {
										m = Pattern.compile(tafaEEala).matcher(
												verb);
										if (m.matches()) {
											faA = m.group(2);
											Eayn = m.group(4);
											Eayn2 = m.group(6);
											laAm = m.group(8);
											wazn = "taFaEoEala";
											root = faA + Eayn + laAm;
										} else {
											m = Pattern.compile(IisotafoEala)
													.matcher(verb);
											if (m.matches()) {
												faA = m.group(2);
												Eayn = m.group(4);
												laAm = m.group(6);
												wazn = "IisotaFoEaLa";
												root = faA + Eayn + laAm;
											} else {
												m = Pattern.compile(faEEala)
														.matcher(verb);
												;
												if (m.matches()) {
													faA = m.group(1);
													Eayn = m.group(3);
													Eayn2 = m.group(5);
													laAm = m.group(7);
													wazn = "faEoEala";
													root = faA + Eayn + laAm;
												} else {
													m = Pattern.compile(
															IinofaEala)
															.matcher(verb);
													if (m.matches()) {
														faA = m.group(4);
														Eayn = m.group(6);
														laAm = m.group(8);
														if (faA.equals("t")) {
															faA = m.group(2);
															wazn = "IiFotaEaLa"; // IinotaEa$a
															root = faA + Eayn
																	+ laAm;
														} else {
															wazn = "IinoFaEaLa"; // IinoqaraDa
															root = faA + Eayn
																	+ laAm;
														}

													} else {
														m = Pattern.compile(
																IifotaEala)
																.matcher(verb);
														if (m.matches()) {
															faA = m.group(2);
															Eayn = m.group(6);
															laAm = m.group(8);
															root = faA + Eayn
																	+ laAm;
															if (laAm.equals(Eayn)) {
																wazn = "IiFotaEoLa"; // إستلَّ
																root = faA
																		+ Eayn
																		+ laAm;
															} else {
																wazn = "IiFotaEaLa";
															}
														}

														m = Pattern.compile(
																OafoEala)
																.matcher(verb);
														if (m.matches()) {
															faA = m.group(3);
															Eayn = m.group(5);
															laAm = m.group(7);
															wazn = "OaFoEaLa";
															root = faA + Eayn
																	+ laAm;
															if (faA.equals(Eayn)) {
																faA = m.group(1);
																Eayn = m.group(3);
																Eayn2 = m
																		.group(5);
																laAm = m.group(7);
																wazn = "faEoEala";
																root = faA
																		+ Eayn
																		+ laAm;
															}
														} else {
															m = Pattern
																	.compile(
																			IifoEalola)
																	.matcher(
																			verb);
															if (m.matches()) {
																faA = m.group(2);
																Eayn = m.group(4);
																laAm = m.group(6);
																laAm2 = m
																		.group(8);
																if (Eayn.equals("t")) {
																	Eayn = laAm;
																	laAm = laAm2;
																	laAm2 = null;
																	wazn = "IifotaEola"; // Iistal~a
																	root = faA
																			+ Eayn
																			+ laAm;
																} else if (faA
																		.matches("S|D")
																		&& Eayn.equals("T")) {
																	Eayn = laAm;
																	laAm = laAm2;
																	laAm2 = null;
																	wazn = "IiFoTaEaLa";
																	root = faA
																			+ Eayn
																			+ laAm;
																}

																else {
																	wazn = "IiFoEaLoLa";
																	root = faA
																			+ Eayn
																			+ laAm;
																}
															} else {
																m = Pattern
																		.compile(
																				IifoEaAlola)
																		.matcher(
																				verb);
																if (m.matches()) {

																	faA = m.group(2);
																	Eayn = m.group(4);
																	laAm = m.group(6);
																	laAm2 = m
																			.group(8);
																	wazn = "IiFoEaAloLa";
																	root = faA
																			+ Eayn
																			+ laAm;
																} else {
																	m = Pattern
																			.compile(
																					IifoEalalola)
																			.matcher(
																					verb);
																	if (m.matches()) {
																		faA = m.group(2);
																		Eayn = m.group(4);
																		laAm = m.group(6);
																		laAm2 = m
																				.group(8);
																		laAm3 = m
																				.group(10);
																		String firstfive = m
																				.group(1)
																				+ faA
																				+ m.group(3)
																				+ Eayn
																				+ m.group(5);
																		// if(firstfive.equals("Iisota")&!laAm2.equals(laAm3)){
																		// faA =
																		// laAm;
																		// Eayn
																		// =
																		// laAm2;
																		// laAm
																		// =
																		// laAm3;
																		// wazn
																		// =
																		// "IisotaFoEaLa";
																		// root
																		// =
																		// faA+Eayn+laAm;
																		// }
																		if (firstfive
																				.equals("Iisota")
																				& laAm2.equals(laAm3)) {
																			faA = laAm;
																			Eayn = laAm2;
																			Eayn2 = laAm3;
																			wazn = "IisotaFaEoEa";
																			root = faA
																					+ Eayn
																					+ Eayn2;
																		}

																		else if (m
																				.group(6)
																				.equals("w")) {
																			laAm = laAm2;
																			laAm2 = laAm3;
																			laAm3 = null;
																			wazn = "IiFoEawaLoLa";
																			root = faA
																					+ Eayn
																					+ laAm;
																		} else {
																			wazn = "IiFoEaLaLoLa";
																			root = faA
																					+ Eayn
																					+ laAm
																					+ laAm2;
																		}

																	}

																	else {
																		m = Pattern
																				.compile(
																						IifoEawoEala)
																				.matcher(
																						verb);
																		if (m.matches()) {
																			faA = m.group(2);
																			Eayn = m.group(4);
																			Eayn2 = m
																					.group(8);
																			laAm = m.group(10);
																			wazn = "IiFoEawoEala";
																			root = faA
																					+ Eayn
																					+ laAm;

																		} else {
																			m = Pattern
																					.compile(
																							OafoEala)
																					.matcher(
																							verb);
																			if (m.matches()) {
																				faA = m.group(3);
																				Eayn = m.group(5);
																				laAm = m.group(7);
																				wazn = "OaFoEala";
																				root = faA
																						+ Eayn
																						+ laAm;
																				if (faA.equals(Eayn)) {
																					faA = m.group(1);
																					Eayn = m.group(3);
																					Eayn2 = m
																							.group(5);
																					laAm = m.group(7);
																					wazn = "faEoEala";
																					root = faA
																							+ Eayn
																							+ laAm;
																				}

																			} else {
																				m = Pattern
																						.compile(
																								faEoEa)
																						.matcher(
																								verb);
																				if (m.matches()) {// mad~a
																					faA = m.group(1);
																					Eayn = m.group(3);
																					Eayn2 = m
																							.group(5);
																					wazn = "FaEoEa";
																					root = faA
																							+ Eayn
																							+ Eayn2;
																				} else {
																					m = Pattern
																							.compile(
																									OafaEoEa)
																							.matcher(
																									verb);
																					if (m.matches()) {
																						faA = m.group(2);
																						Eayn = m.group(4);
																						laAm = m.group(6);
																						wazn = "OaFaEoEa";
																						root = faA
																								+ Eayn
																								+ laAm;
																					} else {
																						m = Pattern
																								.compile(
																										faAla)
																								.matcher(
																										verb);
																						fillEilla_Map();
																						if (m.matches()) {// qaAla

																							faA = m.group(1);
																							laAm = m.group(4);
																							if (Eilla_Map
																									.containsKey(verb)) {
																								if (Eilla_Map
																										.get(verb)
																										.equals("w")) {
																									Eayn = "w";
																									wazn = "faAla1";
																								} else if (Eilla_Map
																										.get(verb)
																										.equals("y")) {
																									Eayn = "y";
																									wazn = "faAla2";
																								} else if (Eilla_Map
																										.get(verb)
																										.equals("A")) {
																									Eayn = "A";
																									wazn = "faAla3";
																								}
																							} else {
																								Eayn = "w";
																								wazn = "faAla1";
																							}
																							root = faA
																									+ Eayn
																									+ laAm;
																						} else {
																							m = Pattern
																									.compile(
																											OafaAla)
																									.matcher(
																											verb);
																							if (m.matches()) {// qaAla

																								faA = m.group(2);
																								Eayn = m.group(4);
																								laAm = m.group(5);
																								wazn = "OafaAla";
																								root = faA
																										+ Eayn
																										+ laAm;
																							} else {
																								m = Pattern
																										.compile(
																												IifoEaAla)
																										.matcher(
																												verb);
																								if (m.matches()) {
																									faA = m.group(2);
																									Eayn = m.group(4);
																									laAm = m.group(7);
																									wazn = "IiFoEaALa";
																									root = faA
																											+ Eayn
																											+ laAm;
																								} else {
																									m = Pattern
																											.compile(
																													IifoTaEala)
																											.matcher(
																													verb);
																									if (m.matches()) {
																										faA = m.group(2);
																										Eayn = m.group(5);
																										laAm = m.group(7);
																										if (faA.matches("S|D")) {
																											wazn = "IiFoTaEaLa";
																										}
																										root = faA
																												+ Eayn
																												+ laAm;
																									} else {
																										m = Pattern
																												.compile(
																														IifodaEala)
																												.matcher(
																														verb);
																										if (m.matches()) {
																											faA = m.group(2);
																											Eayn = m.group(5);
																											laAm = m.group(7);
																											if (faA.matches("d|z")) {
																												wazn = "IiFodaEaLa";
																											}
																											root = faA
																													+ Eayn
																													+ laAm;
																										} else {
																											m = Pattern
																													.compile(
																															IifofaEala)
																													.matcher(
																															verb);
																											if (m.matches()) {
																												faA = m.group(2);
																												faA2 = m.group(4);
																												Eayn = m.group(6);
																												laAm = m.group(8);
																												wazn = "IifofaEala";
																												root = faA
																														+ Eayn
																														+ laAm;
																												if (!faA.equals("t")
																														&& faA2.equals("t")) {
																													faA = faA
																															.replace(
																																	"t",
																																	"w");
																													wazn = "IiFoTaEaLa";
																													root = faA
																															+ Eayn
																															+ laAm;
																												} else if (faA
																														.equals("t")
																														&& faA2.equals("t")) {
																													root = "w"
																															+ Eayn
																															+ laAm;
																												}
																											} else {
																												m = Pattern
																														.compile(
																																IisotafaAla)
																														.matcher(
																																verb);
																												if (m.matches()) {
																													faA = m.group(2);
																													Eayn = m.group(4);
																													laAm = m.group(5);
																													wazn = "IisotaFaAla";
																													root = faA
																															+ Eayn
																															+ laAm;

																												} else {
																													m = Pattern
																															.compile(
																																	faEoEaY)
																															.matcher(
																																	verb);
																													if (m.matches()) {
																														faA = m.group(1);
																														Eayn = m.group(3);
																														Eayn2 = m
																																.group(5);
																														wazn = "faEoEaY";
																														root = faA
																																+ Eayn
																																+ "y";
																													}

																													else {
																														m = Pattern
																																.compile(
																																		tafaEoEaY)
																																.matcher(
																																		verb);
																														if (m.matches()) {
																															faA = m.group(1);
																															Eayn = m.group(3);
																															Eayn2 = m
																																	.group(5);
																															wazn = "tafaEoEaY";
																															root = faA
																																	+ Eayn
																																	+ "y";
																														} else {
																															m = Pattern
																																	.compile(
																																			IisotafoEaY)
																																	.matcher(
																																			verb);
																															if (m.matches()) {
																																faA = m.group(2);
																																Eayn = m.group(4);
																																wazn = "IisotafoEaY";
																																root = faA
																																		+ Eayn
																																		+ "y";
																															} else {

																																m = Pattern
																																		.compile(
																																				faEaY)
																																		.matcher(
																																				verb);
																																if (m.matches()) {
																																	faA = m.group(1);
																																	Eayn = m.group(3);
																																	fillNaqiSMap();
																																	if (naAqiS_map
																																			.containsKey(verb)) {
																																		if (naAqiS_map
																																				.get(verb)
																																				.equals("y")) {
																																			laAm = "y";
																																			wazn = "faEaY1";
																																		} else if (naAqiS_map
																																				.get(verb)
																																				.equals("w")) {
																																			laAm = "w";
																																			wazn = "faEaY2";
																																		} else if (naAqiS_map
																																				.get(verb)
																																				.equals("A")) {
																																			laAm = "A";
																																			wazn = "faEaY3";
																																		}
																																	} else {
																																		laAm = "y";
																																		wazn = "faEaY1";
																																	}
																																	root = faA
																																			+ Eayn
																																			+ laAm;

																																} else {
																																	m = Pattern
																																			.compile(
																																					OafoEaY)
																																			.matcher(
																																					verb);
																																	if (m.matches()) {
																																		faA = m.group(2);
																																		Eayn = m.group(4);
																																		wazn = "OafoEaY";
																																		root = faA
																																				+ Eayn
																																				+ "y/w";
																																	} else {
																																		m = Pattern
																																				.compile(
																																						IinofaEaY)
																																				.matcher(
																																						verb);
																																		if (m.matches()) {
																																			faA = m.group(2);
																																			Eayn = m.group(4);
																																			// laAm
																																			// =
																																			// "y";
																																			wazn = "IinofaEaY";
																																			root = faA
																																					+ Eayn
																																					+ "y/w";

																																		} else {
																																			m = Pattern
																																					.compile(
																																							IifotaEaY)
																																					.matcher(
																																							verb);
																																			if (m.matches()) {
																																				faA = m.group(2);
																																				Eayn = m.group(4);
																																				wazn = "IifotaEaY";
																																				root = faA
																																						+ Eayn
																																						+ "y/w";

																																			} else {
																																				m = Pattern
																																						.compile(
																																								tafaAEaY)
																																						.matcher(
																																								verb);
																																				if (m.matches()) {
																																					faA = m.group(2);
																																					Eayn = m.group(4);
																																					wazn = "tafaAEaY";
																																					root = faA
																																							+ Eayn
																																							+ "y/w";
																																				} else {
																																					m = Pattern
																																							.compile(
																																									faAEoEa)
																																							.matcher(
																																									verb);
																																					if (m.matches()) {
																																						faA = m.group(1);
																																						Eayn = m.group(3);
																																						Eayn2 = m
																																								.group(5);
																																						wazn = "faAEoEa";
																																						root = faA
																																								+ Eayn
																																								+ Eayn2;
																																					} else {
																																						m = Pattern
																																								.compile(
																																										tafaAEoEa)
																																								.matcher(
																																										verb);
																																						if (m.matches()) {
																																							faA = m.group(1);
																																							Eayn = m.group(3);
																																							Eayn2 = m
																																									.group(5);
																																							wazn = "tafaAEoEa";
																																							root = faA
																																									+ Eayn
																																									+ Eayn2;
																																						} else {
																																							m = Pattern
																																									.compile(
																																											IifofaEaY)
																																									.matcher(
																																											verb);
																																							if (m.matches()) {

																																								faA = m.group(2);
																																								faA2 = m.group(4);
																																								Eayn = m.group(6);
																																								wazn = "IifofaEaY";
																																								if (faA.equals("t")
																																										&& faA2.equals("t")) {

																																									root = "w"
																																											+ faA2
																																											+ Eayn;
																																								} else {
																																									root = faA2
																																											+ Eayn
																																											+ "y/w";
																																								}
																																							}

																																							else {
																																								m = Pattern
																																										.compile(
																																												faEala)
																																										.matcher(
																																												verb);
																																								if (m.matches()) {
																																									wazn = "faE0la";
																																									faA = m.group(1);
																																									Eayn = m.group(3);
																																									laAm = m.group(5);
																																									root = faA
																																											+ Eayn
																																											+ laAm;
																																								}

																																								else {
																																									verb = guessVerbForm(verb);
																																									get_pattern();
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}

																												}

																											}
																										}
																									}

																								}

																							}
																						}
																					}
																				}
																			}

																		}
																	}
																}

															}

														}

													}

												}
											}
										}
									}
								}

							}
						}
						}
					}
				}
			}
		}
	}

	// Replace stem verb with C (consonant) and V (vowel)
	public String get_CVCV(String ENTR) {
		String verb_patt = "";
		char[] charArr = ENTR.toCharArray();
		String char1 = null;

		for (int i = 0; i < charArr.length; i++) {

			char1 = Character.toString(charArr[i]);

			if (char1.matches(F)) {
				verb_patt += char1.replace(char1, "C");

			} else if (char1.matches(vow)) {
				verb_patt += char1.replace(char1, "V");
			}

		}
		return verb_patt;
	}

	public String get_VVV(String ENTR) {
		char[] charArr = ENTR.toCharArray();
		String verb_vocal = "";
		for (int i = 0; i < charArr.length; i++) {
			String char1 = Character.toString(charArr[i]);
			if (char1.matches(vow)) {
				verb_vocal += char1;
			} else {
				verb_vocal += "_";
			}
		}
		return verb_vocal;
	}

	public String get_wazn() {
		return wazn;
	}

	public String get_FaA1() {
		return faA;
	}

	public String get_FaA2() {
		return faA2;
	}

	public String get_Eayn1() {
		return Eayn;
	}

	public String get_Eayn2() {
		return Eayn2;
	}

	public String get_LaAm1() {
		return laAm;
	}

	public String get_LaAm2() {
		return laAm2;
	}

	public String get_LaAm3() {
		return laAm3;
	}

	public String get_root() {
		return root;
	}

	public void fillEilla_Map() {
		try {

			String line;

			bf = new BufferedReader(new FileReader(new File(
					"src/main/resources/conj_tres/Eilla_Verbs.tbl")));
			while ((line = bf.readLine()) != null) {
				String[] kv = line.split("\t");
				Eilla_Map.put(kv[0], kv[1]);
				}
			}
		catch (FileNotFoundException ex) {
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

	public void fillNaqiSMap() {
		try {

			String line;

			bf = new BufferedReader(new FileReader(new File(
					"src/main/resources/conj_tres/NaAqiS_Verbs.tbl")));
			while ((line = bf.readLine()) != null) {
				String[] kv = line.split("\t");
				naAqiS_map.put(kv[0], kv[1]);
				}
			}
		catch (FileNotFoundException ex) {
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
	
}