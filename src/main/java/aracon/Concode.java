package aracon;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

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

public class Concode {
	String transtring;
	public Concode(String input){
		this.transtring=input;
	}
//	BidiMap Transtable = new DualHashBidiMap();
		public BidiMap firstMap(){
			BidiMap Transtable = new DualHashBidiMap();
			Transtable.put('\'', '\u0621'); 
			Transtable.put('|', '\u0622');
			Transtable.put('O', '\u0623'); 
			Transtable.put('W', '\u0624'); 
			Transtable.put('I', '\u0625');
			Transtable.put('}', '\u0626');
			Transtable.put('A', '\u0627');
			Transtable.put('b', '\u0628');
		    Transtable.put('p', '\u0629');
		    Transtable.put('t', '\u062A');
		    Transtable.put('v', '\u062B');
		    Transtable.put('j', '\u062C');
			Transtable.put('H', '\u062D');
			Transtable.put('x', '\u062E');
			Transtable.put('d', '\u062F');
			Transtable.put('*', '\u0630');
			Transtable.put('r', '\u0631');
			Transtable.put('z', '\u0632');
			Transtable.put('s', '\u0633');
			Transtable.put('$', '\u0634');
			Transtable.put('S', '\u0635'); 
			Transtable.put('D', '\u0636');
			Transtable.put('T', '\u0637');
			Transtable.put('Z', '\u0638'); 
			Transtable.put('E', '\u0639'); 
			Transtable.put('g', '\u063A');
			Transtable.put('_', '\u0640'); 
			Transtable.put('f', '\u0641'); 
			Transtable.put('q', '\u0642'); 
			Transtable.put('k', '\u0643');
			Transtable.put('l', '\u0644');
			Transtable.put('m', '\u0645');
			Transtable.put('n', '\u0646'); 
			Transtable.put('h', '\u0647'); 
			Transtable.put('w', '\u0648'); 
			Transtable.put('Y', '\u0649'); 
			Transtable.put('y', '\u064A'); 
			Transtable.put('F', '\u064B'); 
			Transtable.put('N', '\u064C'); 
			Transtable.put('K', '\u064D');
			Transtable.put('a', '\u064E');
			Transtable.put('u', '\u064F'); 
			Transtable.put('i', '\u0650'); 
			Transtable.put('~', '\u0651'); 
			Transtable.put('o',	'\u0652'); 
			Transtable.put('`', '\u0670'); 
			Transtable.put('{' , '\u0671');  
			Transtable.put('P', '\u067E'); 
			Transtable.put('J', '\u0686'); 
			Transtable.put('V', '\u06A4');
			Transtable.put('G', '\u06AF'); 
			Transtable.put(' ', ' ');
			return Transtable;
		}
		
		public BidiMap secondMap(){
			BidiMap Transtable = new DualHashBidiMap();
			Transtable.put("\'", "�"); 
			Transtable.put("|", "A�");
			Transtable.put("O", "'"); 
			Transtable.put("W", "U�"); 
			Transtable.put("I", "�i");
			Transtable.put("}", "I�");
			Transtable.put("A", "A");
			Transtable.put("b", "b");
		    Transtable.put("p", "T");
		    Transtable.put("t", "t");
		    Transtable.put("v", "_t");
		    Transtable.put("j", "^g");
			Transtable.put("H", ".h");
			Transtable.put("x", "_h");
			Transtable.put("d", "d");
			Transtable.put("*", "_d");
			Transtable.put("r", "r");
			Transtable.put("z", "z");
			Transtable.put("s", "s");
			Transtable.put("$", "^s");
			Transtable.put("S", ".s"); 
			Transtable.put("D", ".d");
			Transtable.put("T", ".t");
			Transtable.put("Z", ".z"); 
			Transtable.put("E", "`"); 
			Transtable.put("g", ".g");
			Transtable.put("f", "f"); 
			Transtable.put("q", "q"); 
			Transtable.put("k", "k");
			Transtable.put("l", "l");
			Transtable.put("m", "m");
			Transtable.put("n", "n"); 
			Transtable.put("h", "h"); 
			Transtable.put("w", "w"); 
			Transtable.put("Y", "_A"); 
			Transtable.put("y", "y"); 
			Transtable.put("F", "aN"); 
			Transtable.put("N", "uN"); 
			Transtable.put("K", "iN");
			Transtable.put("a", "a");
			Transtable.put("u", "u"); 
			Transtable.put("i", "i"); 
//			Transtable.put('~', '\u0651'); 
			Transtable.put("o",	""); 
//			Transtable.put('`', '\u0670'); 
			Transtable.put("{" , "�A");  
//			Transtable.put('P', 'p'); 
//			Transtable.put('J', '^c'); 
//			Transtable.put('V', '\u06A4');
			Transtable.put("G", "^n"); 
			Transtable.put(" ", " ");
			Transtable.put("\n", "\n");
		
			return Transtable;
		}
		
		public String Buckwalter2Arabic(){
			StringBuffer strbf = new StringBuffer();
			char[] charArr = transtring.toCharArray();
	        for(int i = 0; i<charArr.length; i++){
	        	char toReplace = charArr[i];
	        	if(firstMap().containsKey(toReplace)){
	            strbf.append(firstMap().get(toReplace).toString());
	        	}
	        	else if(Character.isUpperCase(toReplace)){
	        		
	        		char lowerCase = Character.toLowerCase(toReplace);
	        		if(firstMap().containsKey(lowerCase)){
	        		strbf.append(firstMap().get(lowerCase).toString());
	        		}
	        		else{
	        			strbf.append(toReplace);
	        		}
	        		}
	        	
	        	else if(Character.isLowerCase(toReplace)){
	        		if(Character.isLetter(toReplace)){
	        		char uppCase = Character.toUpperCase(toReplace);
	        		if(firstMap().containsKey(uppCase)){
	        		strbf.append(firstMap().get(uppCase).toString());
	        		}
	        		else{
	        			strbf.append(toReplace);
	        		}
	        		}
	        		
	        	}
	        	else{
	        		strbf.append(toReplace);
	        	}
	        }
	        return strbf.toString();
		}
		
	public String Arabic2Buckwalter(){
		StringBuffer strbf= new StringBuffer();
		BidiMap bdm = firstMap().inverseBidiMap();
		char[] charArr = transtring.toCharArray();
        for(int i = 0; i<charArr.length; i++){
        	char toReplace = charArr[i];
        	if(bdm.containsKey(toReplace)){
            strbf.append(bdm.get(toReplace).toString());
        	}
        	else{
        		strbf.append(toReplace);
        	}
        }
        return strbf.toString();
	}

	 public String Buckwalter2Lagally(){
		 String str2replace = null;
		 StringBuffer strbf = new StringBuffer();
		 BidiMap bdm = secondMap();
		 char[] charArr = transtring.toCharArray();
		 for(int i = 0; i<charArr.length; i++){
			 str2replace = Character.toString(charArr[i]);
			 char charRel  = charArr[i];
			 if(charRel=='~'){
				charArr[i] = charArr[i-1];
				strbf.append(Character.toString(charArr[i]));
			 }
			 if(bdm.containsKey(str2replace)){
				 strbf.append(bdm.get(str2replace));
			 }
		 }
	    	return strbf.toString();
	    }
	 
	 public String Arabic2Lagally(){
		 Concode trans = new Concode(transtring);
		 trans = new Concode(trans.Arabic2Buckwalter());
		 return trans.Buckwalter2Lagally();
		 
	 }
}




