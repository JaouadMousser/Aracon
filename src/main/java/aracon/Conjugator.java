package aracon;
//This file is part of the  ARACON Java library.
//Copyright (C) 2011 Jaouad Mousser
//				  Jaouad.Mousser@uni-konstanz.de
//                 Univeristy of Konstanz
//                 Department of Linguistics
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import aracon.MorphPatternAnalyser;
import aracon.Concode;

public class Conjugator {
	private String verb = null;// The input verb
	private String wazn = null;
	private String root = null;
	private String F1 = null;
	private String F2 = null;
	private String E1 = null;
	private String E2 = null;
	private String L1 = null;
	private String L2 = null;
	private String L3 = null;
	private String conson = "[O|b|t|v|j|H|x|d|\\*|r|z|T|S|D|Z|s|l|m|n|h|E|g|\\$|y|w|f|q|k|W|I|\\}|']{1}";
	private String vowel = "[a|u|i]";

	private File fd_patt_modell = new File("src/main/resources/conj_tres");// Folder of the pattern
	// modell
	private File[] files = fd_patt_modell.listFiles();// files of the pattern
	// modelln
	private Map<String, String> map_perfect = new LinkedHashMap<String, String>();
	private Map<String, String> map_muDaAriE1 = new LinkedHashMap<String, String>();
	private Map<String, String> map_muDaAriE2 = new LinkedHashMap<String, String>();
	private Map<String, String> map_muDaAriE3 = new LinkedHashMap<String, String>();
	private Map<String, String> map_muDaAriE4 = new LinkedHashMap<String, String>();
	private Map<String, String> map_imperative1 = new LinkedHashMap<String, String>();
	private Map<String, String> map_imperative2 = new LinkedHashMap<String, String>();
	private Map<String, String> map_future1 = new LinkedHashMap<String, String>();
	private Map<String, String> map_future2 = new LinkedHashMap<String, String>();
	private Map<String, String> map_past_cont = new LinkedHashMap<String, String>();// kaAna
	// yaquulu
	private Map<String, String> map_past_perf = new LinkedHashMap<String, String>();// kaAna
	// qad
	private Map<String, String> map_subjunctive = new LinkedHashMap<String, String>();// kaAna
	// sayaOotiy
	private Map<String, String> map_future_perf = new LinkedHashMap<String, String>();

	MorphPatternAnalyser mpa = null;
	ArrayList<Document> list_rel_doc = new ArrayList<Document>();

	Concode conc = null;
//
	public enum Mode {
		PASSIVE, ACTIVE
	}

	//
	// Mode mode1;

	public Conjugator(String Verb) {
		this.verb = Verb;
	}

	public void SelectConjModel() throws ParserConfigurationException,
			SAXException, IOException {
		conc  = new Concode(verb);
		mpa = new MorphPatternAnalyser(conc.Arabic2Buckwalter());// Pattern analyser
		mpa.get_pattern();
		wazn = mpa.get_wazn();
		root = mpa.get_root();
		F1 = mpa.get_FaA1();
		F2 = mpa.get_FaA2();
		E1 = mpa.get_Eayn1();
		E2 = mpa.get_Eayn2();
		L1 = mpa.get_LaAm1();
		L2 = mpa.get_LaAm2();
		L3 = mpa.get_LaAm3();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setIgnoringElementContentWhitespace(true);

		DocumentBuilder builder = factory.newDocumentBuilder();

		for (File file : files) {
			if (file.getName().endsWith("xml")
					&& file.getName()
							.substring(0, file.getName().length() - 12)
							.equalsIgnoreCase(wazn)) {
				list_rel_doc.add(builder.parse(file));
				//System.out.println(file.getName());
			}
		}
	}

	public String map_fEl(String verb3) {
		String mapped = null;
		if (verb3.contains("2") && F2 != null) {
			mapped = verb3.replace("1", F1).replace("2", F2);
		} else {
			mapped = verb3.replace("1", F1);
		}
		if (mapped.contains("4") && E2 != null) {
			mapped = mapped.replace("3", E1).replace("4", E2);
		} else {
			mapped = mapped.replace("3", E1);
		}
		if (mapped.contains("6") && L2 != null) {
			mapped = mapped.replace("5", L1).replace("6", L2);
		} else if (mapped.contains("5") && L1 != null) {
			mapped = mapped.replace("5", L1);
		}
		if (mapped.contains("7") && L3 != null) {
			mapped = mapped.replace("7", L3);
		}
		return mapped;
	}

	public void buildSimpleTenses(Mode mode) {
		String pers_pro = null;
		String verb1 = null;
		for (int e = 0; e < list_rel_doc.size(); e++) {
			Element root = list_rel_doc.get(e).getDocumentElement();
			// System.out.println(root.getAttribute("ID"));
			String modeAttr = root.getAttribute("mode");
			// System.out.println(modeAttr);
			if (modeAttr.equalsIgnoreCase(mode.name())) {
				NodeList conj_list = root.getElementsByTagName("Conjugation");
				for (int in = 0; in < conj_list.getLength(); in++) {
					Element conj_el = (Element) conj_list.item(in);
					String temp = conj_el.getAttribute("TEMP");
					if (conj_el.getAttribute("TEMP")
							.equalsIgnoreCase("perfect")) {
						pers_pro = conj_el.getAttribute("PRS_PRN");
						verb1 = map_fEl(conj_el.getAttribute("VERB"));
						
						conc = new Concode(verb1);
						
						//System.out.println(pers_pro+"-"+temp+"-"+modeAttr+
						//" ==>"+normalize(verb1));
						map_perfect.put(pers_pro + "-" + temp + "-" + modeAttr,
								normalize(verb1));
					}

					else if (conj_el.getAttribute("TEMP").equals("muDaAriE1")) {
						verb1 = map_fEl(conj_el.getAttribute("VERB"));
						// System.out.println(pers_pro+"-"+"Futur1-"+modeAttr
						// +"==>"+normalize(verb1));
						map_muDaAriE1.put(pers_pro + "-" + temp + "-"
								+ modeAttr, normalize(verb1));
						map_future1.put(pers_pro + "-" + "Futur1-" + modeAttr,
								"sa" + normalize(verb1));
						map_future2.put(pers_pro + "-" + "Futur2-" + modeAttr,
								"sawofa " + normalize(verb1));
						 //System.out.println(pers_pro+"-"+"Futur2-"+modeAttr
						 //+"==>"+ "sawofa "+normalize(verb1));
					} else if (conj_el.getAttribute("TEMP").equals("muDaAriE2")) {
						verb1 = map_fEl(conj_el.getAttribute("VERB"));
						map_muDaAriE2.put(pers_pro + "-" + temp + "-"
								+ modeAttr, normalize(verb1));
					} else if (conj_el.getAttribute("TEMP").equals("muDaAriE3")) {
						verb1 = map_fEl(conj_el.getAttribute("VERB"));
						map_muDaAriE3.put(pers_pro + "-" + temp + "-"
								+ modeAttr, normalize(verb1));
					} else if (conj_el.getAttribute("TEMP").equals("muDaAriE4")) {
						verb1 = map_fEl(conj_el.getAttribute("VERB"));
						map_muDaAriE4.put(pers_pro + "-" + temp + "-"
								+ modeAttr, normalize(verb1));
					} else if (conj_el.getAttribute("TEMP").equals(
							"Imperative1")) {
						verb1 = map_fEl(conj_el.getAttribute("VERB"));
						if (verb1.length() > 2) {
							map_imperative1.put(pers_pro + "-" + temp + "-"
									+ modeAttr, normalize(verb1));
						} else {
							map_imperative1.put(pers_pro + "-" + temp + "-"
									+ modeAttr, "0");
						}
					} else if (conj_el.getAttribute("TEMP").equals(
							"Imperative2")) {
						verb1 = map_fEl(conj_el.getAttribute("VERB"));
						if (verb1.length() > 2) {
							map_imperative2.put(pers_pro + "-" + temp + "-"
									+ modeAttr, normalize(verb1));
						} else {
							map_imperative2.put(pers_pro + "-" + temp + "-"
									+ modeAttr, "0");
						}
					}

				}
			}
			
		}

	}

	

	// Chedda
	public String shedda(String verb) {
		String regex = "(" + conson + ")(o)(" + conson + ")(" + vowel + ")";
		Matcher m = Pattern.compile(regex).matcher(verb);
		if (m.find()) {
			if (m.group(1).equals(m.group(3))) {
				verb = verb.replace(m.group(1) + m.group(2) + m.group(3), m
						.group(1)
						+ "~");
			}
		}
		return verb;
	}

	public String normalize(String cjV) {
		String normalized = null;
		if (cjV.contains("iyo")) {
			normalized = cjV.replace("iyo", "iy");
		} else if (cjV.contains("uwo")) {
			normalized = cjV.replace("uwo", "uw");
		} else if (cjV.contains("uwOi")) {
			normalized = cjV.replace("uwO", "uw}i");
		} else if (cjV.contains("AOa")) {
			normalized = cjV.replace("AOa", "A'a");
		} else if (cjV.contains("u'o")) {
			normalized = cjV.replace("u'o", "uWo");
		} else if (cjV.contains("'uw")) {
			normalized = cjV.replace("'uw", "Wuw");
		} else if (cjV.contains("'iy")) {
			normalized = cjV.replace("'iy", "}iy");
		} else if (cjV.contains("i'")) {
			normalized = cjV.replace("i'", "i}");
		} else if (cjV.contains("iy'at")) {
			normalized = cjV.replace("iy'at", "iy}at");
		} else if (cjV.contains("a'o")) {
			normalized = cjV.replace("a'o", "aOo");
		} else if (cjV.contains("uOa")) {
			normalized = cjV.replace("uOa", "uWa");
		} else if (cjV.contains("OaA")) {
			normalized = cjV.replace("OaA", "|");
		} else if (cjV.contains("OaOo")) {
			normalized = cjV.replace("OaOo", "|");
		} else if (cjV.contains("IiOo")) {
			normalized = cjV.replace("IiOo", "Ii}o");
		}

		else {
			normalized = cjV;
		}
		return shedda(normalized);

	}

	public Map<String, String> get_perfect() {
		return map_perfect;
	}

	public Map<String, String> get_muDaAriE1() {
		return map_muDaAriE1;
	}

	public Map<String, String> get_muDaAriE2() {
		return map_muDaAriE2;
	}

	public Map<String, String> get_muDaAriE3() {
		return map_muDaAriE3;
	}

	public Map<String, String> get_muDaAriE4() {
		return map_muDaAriE4;
	}

	public Map<String, String> get_future1() {
		return map_future1;
	}

	public Map<String, String> get_future2() {
		return map_future2;
	}

	public Map<String, String> get_past_continuous() {
		return map_past_cont;
	}

	public Map<String, String> get_past_perfect() {
		return map_past_perf;
	}

	public Map<String, String> get_subjunctive() {
		return map_subjunctive;
	}

	public Map<String, String> get_future_perfect() {
		return map_future_perf;
	}

	public Map<String, String> get_imperative1() {
		return map_imperative1;
	}

	public Map<String, String> get_imperative2() {
		return map_imperative2;
	}
  
	public String getPattern(){
		return wazn;
	}
	public String getRoot(){
		return root;
	}
	public void buildComplexTenses() throws ParserConfigurationException,
			SAXException, IOException {
		Conjugator conj = new Conjugator("kaAna");
		conj.SelectConjModel();
		conj.buildSimpleTenses(Mode.ACTIVE);
		
		Map<String, String> muD1 = conj.get_perfect();
		
		Map<String, String> fut = conj.get_future1();
	
		List Pentries = new LinkedList(map_muDaAriE2.entrySet());
	    
		
		//System.out.println(Pentries);
		List PTentries = new LinkedList(map_perfect.entrySet());
		List Fentries = new LinkedList(fut.entrySet());
		List Xentries = new LinkedList(muD1.entrySet());
		Map.Entry entryX = null;
		Map.Entry entryV = null;
		Map.Entry entryPt = null;
		Map.Entry entryXf = null;
		for (int l = 0; l < Xentries.size(); l++) {
			entryV = (Map.Entry) Pentries.get(l);
			entryX = (Map.Entry) Xentries.get(l);
			entryPt = (Map.Entry) PTentries.get(l);
			entryXf = (Map.Entry) Fentries.get(l);
			map_past_cont.put((String) entryX.getKey().toString().replace(
					"Perfect", "PastContinuous"), entryX.getValue() + " "
					+ entryV.getValue());
			map_subjunctive.put((String) entryX.getKey().toString().replace(
					"Perfect", "Subjunctive"), entryX.getValue() + " sa"
					+ entryV.getValue());
			map_past_perf.put((String) entryX.getKey().toString().replace(
					"Perfect", "PastPerfect"), entryX.getValue() + " (qado) "
					+ entryPt.getValue());
			map_future_perf.put((String) entryX.getKey().toString().replace(
					"Perfect", "FuturePerfect"), entryXf.getValue()
					+ " (qado) " + entryPt.getValue());
		}
	}

	// The following verb are irregular an require a particular treatement. They
	// matche the pattern: OafoEala but have different conjugation:
	// Oaqolama, Oamoraka, Oasolama, Oaro$afa

}
