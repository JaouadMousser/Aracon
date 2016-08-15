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
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import aracon.Concode;
public class MorphPatternAnalyser {
	private String verb;
	private Map<String, String> EillaMap= new HashMap<String, String>();
	private Map<String, String> naAqiS_map= new HashMap<String, String>();	
	private String F = "[O|b|t|v|j|H|x|d|\\*|r|z|T|S|D|Z|s|l|m|n|h|E|g|\\$|y|w|f|q|k|W|I|\\}|']{1}";//Y was delte
	private String E = "[O|b|t|v|j|H|x|d|\\*|r|z|T|S|D|Z|s|l|m|n|h|E|g|\\$|y|w|f|q|k|W|I|\\}|']{1}";
	private String L = "[O|b|t|v|j|H|x|d|\\*|r|z|T|S|D|Z|s|l|m|n|h|E|g|\\$|y|w|f|q|k|W|I|\\}|']{1}";
	private String vow = "[aiuo\\^e]"; 
	private String IifofaEala = "(Ii)(" + F + ")(\\^)(" + F + ")(a)("+ E + ")(a)("+ L +")(a)";//إتَّقى إتَّعض
	private String IifoTaEala = "(Ii)("+ F + ")(o)" + "(Ta)(" +E + ")(a)(" +L +")(a)";//IiSoTadama
	private String IifodaEala = "(Ii)("+ F + ")(o)" + "(da)(" +E + ")(a)(" +L +")(a)";//Iizodahara
	private String faEoEa = "(" + F + ")(a)(" + E +")(\\^)(" +E + ")(a)"; //  سدَّ مدَّ
	private String OafaEoEa = "(O)+(a)(" + F + ")(a)(" + E +")(\\^)(" +E + ")(a)"; //أمدَّ
	private String faEala = "("+F+")"+"(a)"+"("+E+")"+"(a)"+"(" + L+")" +"(a)"; // فعل
	private String faAla = "(" + F + ")(a)(A)("+L +")(a)"; //قال سال
	private String IisotafaAla = "(Iisota)(" + F + ")(a)(A)("+L +")(a)";//إستقال
	private String OafaAla = "(Oa)(" + F + ")(a)(A)("+L +")(a)";
	private String faAEala = "("+F+")(a)(A)("+ E +")(a)(" + L +")(a)"; //  PROBLEM فاعل
	private String faEila = "("+F+")(a)"+"("+E+")(i)"+"("+L+")(a)";// فعِل
	private String faEula = "("+ F +")(a)"+ "(" +E +")(u)" + "(" + L +")(a)";// فعُل
	private String faEolala = "("+ F + ")(a)"+ "(" + E +")(o)" + "(" + L +")(a)" + "(" +L+")(a)"; // فعلل
	private String tafaEolala = "(t)" + "(a)"+ "(" + F + ")(a)"+ "(" + E+")(o)" + "(" + L +")(a)" + "(" + L+")(a)"; // تفاعل
	private String tafaEEala = "(ta)" + "(" + F+")(a)"+"(" + E+")(\\^)("+ E +")(a)"+ "(" + L +")(a)";// تفعَّل
	private String tafaAEala = "(ta)"+"("+F+")(a)(A)("+ E +")(a)(" + L +")(a)"; // تفاعل
	private String IisotafoEala = "(Iisota)" + "(" + F +")(o)"+ "(" + E +")(a)"+ "(" + L +")(a)";//استحمَّّ
	private String faEEala =  "(" + F+")(a)"+ "(" + E+")(\\^)("+E + ")(a)("+ L+ ")(a)";//فعَّل
	private String IinofaEala = "(Ii)" + "(n)" +"(o)("+ F+")(a)("+E+")(a)("+L+")(a)";// إنفعل
	private String IifotaEala =  "(Ii)("+ F +")(o)"+"(t)" + "(a)(" +E+")(a)(" + L+")(a)"; //
	private String OafoEala = "(Oa)(" + F +")(o)(" + E +")(a)(" + L +")(a)"; //أفعل 
	private String IifoEalola = "(Ii)(" + F +")(o)(" + E +")(a)(" + L +")(\\^)(" + L +")(a)"; //إفعلَّ
	private String IifoEaAlola = "(Ii)(" + F +")(o)(" + E +")(aA)(" + L +")(\\^)(" + L +")(a)";// إفعال
	private String IifoEalalola = "(Ii)(" + F +")(o)(" + E +")(a)(" + L +")(a)(" + L +")(\\^)(" + L +")(a)"; //إفعللَّفعنل
	private String IifoEawoEala = "(Ii)(" + F + ")(o)(" + E + ")(a)" + "(w)" + "(o)(" + E + ")(a)(" + L +")(a)";  //إعشوشب
	private String IifoEaAla  = "(Ii)(" + F + ")(o)(" + E + ")(a)(A)(" + L +")(a)"; //إغتاب
    private String faEoEaY = "("+F + ")(a)("+ E+")(\\^)("+E+")(a)([YA])";//سلَّى
	private String tafaEoEaY = "ta"+faEoEaY;
	private String IisotafoEaY ="(Iisota)(" + F +")(o)("+L +")(a)([YA])" ; //إستحلى 
	private String faEaY = "(" +F + ")(a)(" + E + ")(a)([YA])" ; // علا على قلى
	private String OafoEaY = "(Oa)("+ F +")(o)(" + E+ ")(a)([YA])";
	private String IinofaEaY = "(Iino)" + faEaY;//انقضى
    private String IifotaEaY = "(Ii)(" + F +")(ota)(" +E + ")(a)([YA])";//اعتلى
    private String tafaAEaY = "(ta)(" + F + ")(aA)(" + E +")(a)([YA])";//تمادى
	private String faAEoEa = "(" + F +")(aA)(" + E + ")(\\^)(" + E + ")(a)"; //حاجَّ
    private String tafaAEoEa = "ta"+ faAEoEa;//َّتحاج
	private String IifofaEaY = "(Ii)(" + F +")(\\^)("+ F +")(a)(" + E +")(a)[YA]"; //اتَّقى
    private String wazn = null;
    private String faA =null;
    private String faA2 = null;
    private  String Eayn = null;
    private  String Eayn2 = null;
    private String laAm = null;
    private String laAm2 = null;
    private String laAm3 = null;
    private String root =null;
	
	
	public MorphPatternAnalyser(String VERB){
		this.verb = normalize(VERB);
	}
	
	
	// Normalize the transcription of Arabic character for the next analyse steps
	 public String normalize(String word){
    	 String char1="";
		char[] charArr = word.toCharArray();
        for(int i = 0; i<charArr.length; i++){
        	 if(charArr[i]=='~'){
  				char1+= "^" + Character.toString(charArr[i-1]);
         	 }
        	 else if(charArr[i]=='|'){
        		 char1+= "OaA";
        	 }
//        	else if(charArr[i]=='Y'){ //replace alif with alif+a vocal
//        		 char1 +="ya";
//        	 }
        	 else{
        	char1 += Character.toString(charArr[i]);
        	 }
        	 
        }
        return char1;
     }
		
public String  guessVerbForm(String input){
char[] uv = dediacret(input).toCharArray();
String guessed = null;
	if(uv.length<=1){
			throw new IllegalArgumentException("Input must have at least 2 consonants!");
	}
else if (uv.length==2){
if(Character.toString(uv[1]).matches("[A|'}W]")){
	throw new IllegalArgumentException("Illegal input!");
}
else if(uv[0]=='|'){
	guessed = uv[0]+uv[1]+"a"; //OaAla
}
else {
	guessed = uv[0]+"a"+uv[1]+"~a";//mad
}
}
else if(uv.length==3){
	if(uv[1]=='A'){
		guessed = uv[0]+"a"+uv[1]+uv[2]+"a";//maAl
	}
	else if(uv[2]=='~'){//mad~a
		guessed = uv[0]+"a"+uv[1]+uv[2]+"a";
	}
	else if(Character.toString(uv[2]).matches("[Y|A]")){//qlY
		guessed = uv[0]+"a"+uv[1]+"aY";
	}
	else if(uv[1]==uv[2]){
		guessed = uv[0]+"a"+uv[1]+"a"+uv[2]+"a";//qAl
	}
	else{
 //need refinement to differenciate faEila faEala faEula on the basis of what the use have put
			guessed = uv[0]+"a"+uv[1]+"a"+uv[2]+"a";//faEla	
	}
}
else if(uv.length==4){
	if(uv[2]=='~'){
		if(Character.toString(uv[3]).matches("[YA]")){
		guessed = uv[0]+"a"+uv[1]+uv[2]+"a"+uv[3];//sal~aY
		}
		else{
			guessed = uv[0]+"a"+uv[1]+uv[2]+"a"+uv[3]+"a";//sal~aHa
		}
	}
	else if(uv[1]=='A'){
		guessed = uv[0]+"a"+uv[1]+uv[2]+"a"+uv[3]+"a"; //faAEala
	}
	else if(uv[3]=='~'){
		guessed = uv[0]+"a"+uv[1]+"a"+uv[2]+uv[3]+"a";//Oamal~a
	}
	else if(Character.toString(uv[3]).matches("[YA]")){
		guessed = uv[0]+"a"+uv[1]+"o"+uv[2]+"a"+uv[3];
	}
	else {
		guessed = uv[0]+"a"+uv[1]+"o"+uv[2]+"a"+uv[3]+"a";//OafoEala
	}
}
else if(uv.length==5){
	if(Character.toString(uv[0]).matches("[IAO]")){
		if(uv[4]=='Y'){
			guessed = "Ii"+uv[1]+"o"+uv[2]+"a"+uv[3]+"aY";//IiHotamaY
		}
		else if(uv[4]=='~'){
			guessed = "Ii"+uv[1]+"o"+uv[2]+"a"+uv[3]+uv[4]+"a";//IiHotal~a
	    }
		else if(uv[2]=='~'){
			guessed = "Ii"+uv[1]+uv[2]+"a"+uv[3]+"a"+uv[4]+"a"; //Iid~xara
		}
		else if(uv[3]=='A'){
			guessed = "Ii"+uv[1]+"o"+uv[2]+"a"+uv[3]+uv[4]+"a";//IirotaAha
		}
		else{
		    guessed = "Ii"+uv[1]+"o"+uv[2]+"a"+uv[3]+"a"+uv[4]+"a";//IiHotalama
		}	
	}
	else if(uv[0]=='t'){
		if(uv[2]=='A'){
			if(Character.toString(uv[4]).matches("[YA]")){//tamaAdaY
			guessed = uv[0]+"a"+uv[1]+"a"+uv[2]+uv[3]+"a"+uv[4];
		   }
			else if(uv[4]=='~'){
				guessed = uv[0]+"a"+uv[1]+"a"+uv[2]+uv[3]+uv[4]+"a";//taHaAb~a
			}
			else{
				guessed = uv[0]+"a"+uv[1]+"a"+uv[2]+uv[3]+"a"+uv[4]+"a";//taEaAmala
			}
		}
		else if(uv[3]=='~'){
		    if(Character.toString(uv[4]).matches("[YA]")){
			guessed = uv[0]+"a"+uv[1]+"a"+uv[2]+uv[3]+"a"+uv[4];//tasal~aY
		    }
		    else{
		    	guessed = uv[0]+"a"+uv[1]+"a"+uv[2]+uv[3]+"a"+uv[4]+"a";//tasal~ama
		    }
		}
		else{
			guessed = uv[0]+"a"+uv[1]+"a"+uv[2]+"o"+uv[3]+"a"+uv[4]+"a";//tadaHoraja
		}
		
		}
}
else if(uv.length==6){
	if(Character.toString(uv[0]).matches("[AOI]")&&uv[1]=='s'&uv[2]=='t'){
		if(Character.toString(uv[5]).matches("[AY]")){
			guessed = "Ii"+uv[1]+"o"+uv[2]+"a"+uv[3]+"o"+uv[4]+"a"+uv[5];//IisotaloqaY
		}
		else if(uv[5]=='~'){
			guessed = "Ii"+uv[1]+"o"+uv[2]+"a"+uv[3]+"a"+uv[4]+uv[5]+"a";//Iisotamad~a
		}
		else if(uv[4]=='A'){
			guessed = "Ii"+uv[1]+"o"+uv[2]+"a"+uv[3]+"a"+uv[4]+uv[5]+"a"; //IisotaqaAla
		}
		else{
			guessed = "Ii"+uv[1]+"o"+uv[2]+"a"+uv[3]+"o"+uv[4]+"a"+uv[5]+"a";//IisotaEobada
		}
	}
	else if(Character.toString(uv[0]).matches("[AOI]")&&uv[1]!='s'&uv[2]!='t'){
		if(uv[5]=='~'){
			if(uv[3]=='A'){
				guessed = "Ii"+uv[1]+"o"+uv[2]+"a"+uv[3]+uv[4]+uv[5]+"a";//IifoEaAl~a
			}
			else{
				guessed = "Ii"+uv[1]+"o"+uv[2]+"a"+uv[3]+"a"+uv[4]+uv[5]+"a"; //IifoEalal~a
			}
		}
		else{
			guessed = "Ii"+uv[1]+"o"+uv[2]+"a"+uv[3]+"o"+uv[4]+"a"+uv[5]+"a";//IiEo$awo$aba
		}
		
	}
}
else{
	throw new IllegalArgumentException("Your input don't matches any of the standard Arabic Pattern! Please try again!")	;
}
return guessed;
 }
 
 public String dediacret(String str){
	 return str.replaceAll(vow, "");	
 }
	 public void get_pattern(){
		    Matcher m = Pattern.compile(faEala).matcher(verb);
			if(m.matches()){
				wazn = "FaE0La";
				faA  = m.group(1);
				Eayn = m.group(3);
				laAm = m.group(5);
				root = faA+Eayn+laAm;
			}
			else{
				m = Pattern.compile(faAEala).matcher(verb); 
				if(m.matches()){ 
					wazn = "FaAEaLa";
					faA = m.group(1);
					Eayn = m.group(4);
					laAm = m.group(6);
					root = faA+Eayn+laAm;
					
				}
				else{
					m = Pattern.compile(tafaAEala).matcher(verb);
					if(m.matches()){
						wazn = "taFaAEaLa";
						faA = m.group(2);
						Eayn = m.group(5);
						laAm = m.group(7);
						root = faA+Eayn+laAm;
					}
				
				else{
					m = Pattern.compile(faEila).matcher(verb);
						if(m.matches()){
							wazn = "faE0la";
							faA = m.group(1);
							Eayn = m.group(3);
							laAm = m.group(5);
							root = faA+Eayn+laAm;
							
						}
						else{
							m = Pattern.compile(faEula).matcher(verb);
							if(m.matches()){
								wazn = "faE0la";
								faA = m.group(1);
								Eayn = m.group(3);
								laAm = m.group(5);
								root = faA+Eayn+laAm;
							}
								else{
									m = Pattern.compile(faEolala).matcher(verb);
									if(m.matches()){
										faA = m.group(1);
										Eayn = m.group(3);
										laAm = m.group(5);
										laAm2 = m.group(7);
										if(faA.equals(laAm)&&!Eayn.equals(laAm2)){
											faA2  = laAm; 
											laAm = laAm2;
											wazn = "FaEoFala"; //salosaEa
											root = faA+Eayn+faA2+laAm;		
										}
										else if(faA.equals(laAm)&&Eayn.equals(laAm2)){
											faA2  = laAm;
											laAm = laAm2;
											Eayn2 = laAm2;
											wazn = "FaEoFaEa"; //salosala
											root = faA+Eayn+laAm;
										}
										else if(laAm.equals("w")&&!laAm2.equals(Eayn)){
											laAm = laAm2;
											laAm2 = null;
											wazn = "FaEowaLa";
											root = faA+Eayn+laAm;
											
										}
										else if(faA.equals("O")&&!Eayn.equals(laAm)){
											faA = Eayn;
											Eayn = laAm;
											laAm = laAm2;
											wazn  = "OaFoEaLa";
											root = faA+Eayn+laAm;
										}
										else if(faA.equals("O")&&Eayn.equals(laAm)){
											laAm = laAm2;
											wazn = "faEoEala";
											root = faA+Eayn+laAm;
										}
										else if (!faA.equals("m")&&!laAm.equals(faA)&&laAm2.equals("n")){
											laAm2 = null;
											wazn = "FaEoLana"; //Eaqolana
											root = faA+Eayn+laAm;
										}
										else if(faA.equals("m")&&!laAm.equals(faA)){
											faA = Eayn;
											Eayn = laAm;
											laAm = laAm2;
											wazn = "maFoEaLa";
											root = faA+Eayn+laAm;
										}
										else if(!laAm.equals(faA)&&Eayn.equals("w")){
											Eayn = laAm;
											laAm = laAm2;
											wazn = "FawoEaLa";
											root = faA+Eayn+laAm;
										
										}
										else if(!laAm.equals(faA)&&Eayn.equals("y")){
											Eayn = laAm;
											laAm = laAm2;
											wazn = "FayoEala";
											root = faA+Eayn+laAm;
										}
										else if(!laAm.equals(faA)&&laAm.equals("y")){
											laAm = laAm2;
											laAm2 = null;
											wazn = "FaEoyala";
											root = faA+Eayn+laAm;
										}
										else if(!laAm.equals(faA)&&laAm.equals("w")){
											laAm = laAm2;
											laAm2 = null;
											wazn = "FaEowala";
											root = faA+Eayn+laAm;
										}
										else if(!laAm2.equals(faA)&&laAm.equals("n")){
											laAm = laAm2;
											laAm2 = null;
											wazn = "FaEonaLa"; //qalonasa
											root = faA+Eayn+laAm;
											
										}
										else if(Eayn.equals(laAm)){
											Eayn2 = laAm;
											laAm = laAm2;
											wazn = "faEoEala";
											root = faA+Eayn+laAm;
										}
										
										else{
											wazn = "FaEoLaLa"; //daHoraja
											root = faA+Eayn+laAm+laAm2;
										}
									}
									
									else {
										m = Pattern.compile(faEEala).matcher(verb);
										if(m.matches()){
										faA = m.group(1);
										Eayn = m.group(3);
										Eayn2 = m.group(5);
										laAm = m.group(7);
										wazn = "FaEoEaLa";
										root = faA+Eayn+laAm;
									}
										
									else {
										m = Pattern.compile(tafaEolala).matcher(verb);
										if(m.matches()){
										faA = m.group(3);
										Eayn = m.group(5);
										laAm = m.group(7);
										laAm2 = m.group(9);
										wazn = "taFaEoLaLa";
										root = faA+Eayn+laAm;
								
//										if(faA.equals(laAm)){
//											faA2 = laAm;
//											laAm = laAm2;
//											wazn = "taFaEoFaLa";
//											root = faA+Eayn+laAm;
//										}
										if(faA.equals("O")&&!laAm.equals(faA)){
											faA = Eayn;
											Eayn = laAm;
											laAm = laAm2;
											wazn = "taOaFoEaLa";
											root = faA+Eayn+laAm;
										}
										else if(!faA.equals("m")&&laAm2.equals("n")&&!laAm.equals(faA)){
											laAm2 = null;
											wazn = "taFaEoLana";
											root = faA+Eayn+laAm;
										}
										else if(faA.equals("m")&&!laAm.equals(faA)){
											faA = Eayn;
											Eayn = laAm;
											laAm = laAm2;
											laAm2 = null;
											wazn = "tamaFoEaLa";
											root = faA+Eayn+laAm;
										
										}
										else if(!laAm.equals("faA")&&laAm.equals("n")){
											laAm= laAm2;
											laAm2 = null;
											wazn = "taFaEonala";
											root = faA+Eayn+laAm;
										}
										else if(!laAm.equals("faA")&&Eayn.equals("w")){
											Eayn = laAm;
											laAm = laAm2;
											laAm2 = null;
											wazn = "taFawoEala";
											root = faA+Eayn+laAm;
										}
										else if(!laAm.equals("faA")&&Eayn.equals("y")){
											Eayn = laAm;
											laAm = laAm2;
											laAm2 = null;
											wazn = "taFayoEala";
											root = faA+Eayn+laAm;
											
										}
										else if(!laAm.equals("faA")&&laAm.equals("w")){
											laAm = laAm2;
											laAm2 = null;
											wazn = "taFaEowala";
											root = faA+Eayn+laAm;
										}
										else if(!laAm.equals("faA")&&laAm.equals("y")){
											laAm = laAm2;
											laAm2 = null;
											wazn = "taFaEoyala";
											root = faA+Eayn+laAm;
										}
										else if(!laAm2.equals(faA)&&laAm.equals("n")){
											laAm = laAm2;
											laAm2 = null;
											wazn = "taFaEonaLa"; //taqalonasa
											root = faA+Eayn+laAm;
										}
										else if(faA.equals("m")&&!laAm.equals("faA")){
											faA = Eayn;
											Eayn = laAm;
											laAm = laAm2;
											laAm2 = null;
											wazn = "tamaFoEaLa";
											
										}
										else if(!laAm.equals(faA)&&laAm.equals("n")){
											laAm = laAm2;
											laAm2 = null;
											wazn = "taFaEonaLa";
											root = faA+Eayn+laAm;
											
										}
										else if(faA.equals(laAm)&&Eayn.equals(laAm2)){
											faA2  = laAm;
											laAm = null;
											Eayn2 = laAm2;
											wazn = "taFaEoFaEa"; //tasalosala
											root = faA+Eayn+Eayn2;
										
										}
										else if(faA.equals(laAm)&&!Eayn.equals(laAm2)){
											faA2  = laAm; 
											laAm = laAm2;
											wazn = "taFaEoFala"; //salosaEa
											root = faA+Eayn+faA2+laAm;	
										}
										else{
											
											wazn = "taFaEoLaLa";
											root = faA+Eayn+laAm+laAm2;
										}
										}
										else{
											m = Pattern.compile(tafaEEala).matcher(verb);
										if(m.matches()){
											faA = m.group(2);
											Eayn = m.group(4);
											Eayn2 = m.group(6);
											laAm = m.group(8);
											wazn = "taFaEoEala";
											root = faA+Eayn+laAm;
											}
											else{
												m = Pattern.compile(IisotafoEala).matcher(verb);
												if(m.matches()){
													faA = m.group(2);
													Eayn = m.group(4);
													laAm = m.group(6);
													wazn = "IisotaFoEaLa";
													root = faA+Eayn+laAm;
												}
										else{
											m = Pattern.compile(faEEala).matcher(verb);;
											if(m.matches()){
												faA = m.group(1);
												Eayn = m.group(3);
												Eayn2 = m.group(5);
												laAm = m.group(7);
												wazn = "faEoEala";
												root = faA+Eayn+laAm;
											}
											else{
												m = Pattern.compile(IinofaEala).matcher(verb);
												if(m.matches()){
													faA = m.group(4);
													Eayn = m.group(6);
													laAm = m.group(8);
													if(faA.equals("t")){
														faA = m.group(2);
														wazn = "IiFotaEaLa"; //IinotaEa$a
														root = faA+Eayn+laAm;
													}
													else{
														wazn = "IinoFaEaLa"; // IinoqaraDa
														root = faA+Eayn+laAm;
													}
													
												}
												else{
													m = Pattern.compile(IifotaEala).matcher(verb);
													if(m.matches()){
														faA = m.group(2);
														Eayn = m.group(6);
														laAm = m.group(8);
														root = faA+Eayn+laAm;
														if(laAm.equals(Eayn)){
															wazn = "IiFotaEoLa"; //إستلَّ
															root = faA+Eayn+laAm;
														}
														else{
														wazn = "IiFotaEaLa";
														}
													}
				
														m = Pattern.compile(OafoEala).matcher(verb);
														if(m.matches()){
															faA = m.group(3);
															Eayn = m.group(5);
															laAm = m.group(7);
															wazn = "OaFoEaLa";
															root = faA+Eayn+laAm;
															if(faA.equals(Eayn)){
																faA = m.group(1);
																Eayn = m.group(3);
																Eayn2= m.group(5);
																laAm = m.group(7);
																wazn = "faEoEala";
																root = faA+Eayn+laAm;
															}
														}
														else{
															m = Pattern.compile(IifoEalola).matcher(verb);
															if(m.matches()){
																faA = m.group(2);
																Eayn = m.group(4);
																laAm = m.group(6);
																laAm2 = m.group(8);
																if(Eayn.equals("t")){
																	Eayn = laAm;
																	laAm = laAm2;
																	laAm2 = null;
																	wazn = "IifotaEola"; //Iistal~a
																	root = faA+Eayn+laAm;
																}
																else if(faA.matches("S|D")&&Eayn.equals("T")){
																	Eayn = laAm;
																	laAm = laAm2;
																	laAm2 = null;
																	wazn = "IiFoTaEaLa";
																	root = faA+Eayn+laAm;
																}
																
																else{
																wazn = "IiFoEaLoLa";
																root = faA+Eayn+laAm;
																}
															}
															else{
																m = Pattern.compile(IifoEaAlola).matcher(verb);
																if(m.matches()){
																	
																	faA = m.group(2);
																	Eayn = m.group(4);
																	laAm = m.group(6);
																	laAm2 = m.group(8);
																	wazn = "IiFoEaAloLa";
																	root = faA+Eayn+laAm;
																}
																else{
																	m = Pattern.compile(IifoEalalola).matcher(verb);
																	if(m.matches()){
																		faA = m.group(2);
																		Eayn = m.group(4);
																		laAm = m.group(6);
																		laAm2 = m.group(8);
																		laAm3 = m.group(10);
																		String firstfive = m.group(1) + faA + m.group(3)+Eayn+m.group(5);
//																		if(firstfive.equals("Iisota")&!laAm2.equals(laAm3)){
//																			faA = laAm;
//																			Eayn = laAm2;
//																			laAm = laAm3;
//																			wazn = "IisotaFoEaLa";
//																			root = faA+Eayn+laAm;
//																		}
																		if(firstfive.equals("Iisota")&laAm2.equals(laAm3)){
																				faA = laAm;
																				Eayn = laAm2;
																				Eayn2= laAm3;
																				wazn = "IisotaFaEoEa";
																				root = faA+Eayn+Eayn2;
																			}
																			
																		
																		else if(m.group(6).equals("w")){
																			laAm = laAm2;
																			laAm2 = laAm3;
																			laAm3 = null;
																			wazn = "IiFoEawaLoLa";
																			root = faA+Eayn+laAm;
																		}
																		else{
																		wazn = "IiFoEaLaLoLa";
																		root = faA+Eayn+laAm+laAm2;
																		}
																		
																	}
													
														
																											else{
																												m = Pattern.compile(IifoEawoEala).matcher(verb);
																												if(m.matches()){
																													 faA = m.group(2);
																													 Eayn = m.group(4);
																													 Eayn2 = m.group(8);
																													 laAm = m.group(10);
																													 wazn = "IiFoEawoEala";
																													 root = faA+Eayn+laAm;
																													  
																												}
																												else{
																													 m = Pattern.compile(OafoEala).matcher(verb);
																													if(m.matches()){
																														faA = m.group(3);
																														Eayn = m.group(5);
																														laAm = m.group(7);
																														wazn = "OaFoEala";
																														root = faA+Eayn+laAm;
																														if(faA.equals(Eayn)){
																															faA = m.group(1);
																															Eayn = m.group(3);
																															Eayn2= m.group(5);
																															laAm = m.group(7);
																															wazn = "faEoEala";
																															root = faA+Eayn+laAm;
																														}
																														
																												}
																													else{
																														m = Pattern.compile(faEoEa).matcher(verb);
																														if(m.matches()){//mad~a
																														faA = 	m.group(1);
																														Eayn = 	m.group(3);
																														Eayn2 = m.group(5);
																														wazn = "FaEoEa";
																														root = faA+Eayn+Eayn2;
																														}
																														else{
																															m = Pattern.compile(OafaEoEa).matcher(verb);
																															if(m.matches()){
																																faA = m.group(2);
																																Eayn = m.group(4);
																																laAm = m.group(6);
																																wazn = "OaFaEoEa";
																																root = faA+Eayn+laAm;
																														}
																														else{
																															m = Pattern.compile(faAla).matcher(verb);
																															fillEillaMap();
																														 if(m.matches()){//qaAla
																															 
																															 faA = m.group(1);
																															 laAm = m.group(4);
																															 if(EillaMap.containsKey(verb)){
																															 if(EillaMap.get(verb).equals("w")){
																																 Eayn = "w";
																																 wazn = "faAla1";
																															 }
																															 else if(EillaMap.get(verb).equals("y")){
																																 Eayn = "y";
																																 wazn = "faAla2"; 
																															 }
																															 else  if(EillaMap.get(verb).equals("A")){
																																 Eayn = "A";
																																 wazn = "faAla3"; 
																															 }}
																															 else{
																																 Eayn = "w";
																																 wazn = "faAla1";
																															 }
																															 root = faA+Eayn+laAm;
																														 }
																															else{
																																m = Pattern.compile(OafaAla).matcher(verb);
																															 if(m.matches()){//qaAla
																																 
																																 faA = m.group(2);
																																 Eayn = m.group(4);
																																 laAm = m.group(5);
																																 wazn = "OafaAla";
																																 root = faA+Eayn+laAm;
																															 }
																															 else {
																																 m = Pattern.compile(IifoEaAla).matcher(verb);
																																 if(m.matches()){
																																	 faA = m.group(2);
																																	 Eayn = m.group(4);
																																	 laAm = m.group(7);
																																	 wazn = "IiFoEaALa";
																																	 root = faA+Eayn+laAm;
																																 }
																																 else{
																																	 m = Pattern.compile(IifoTaEala).matcher(verb);
																																	 if(m.matches()){
																																		 faA = m.group(2);
																																		 Eayn = m.group(5);
																																		 laAm = m.group(7);
																																		 if(faA.matches("S|D")){
																																			 wazn = "IiFoTaEaLa";
																																		 }
																																		 root = faA+Eayn+laAm;
																																	 }
																																	 else{
																																		 m = Pattern.compile(IifodaEala).matcher(verb);
																																		 if(m.matches()){
																																			 faA = m.group(2);
																																			 Eayn = m.group(5);
																																			 laAm = m.group(7);
																																			 if(faA.matches("d|z")){
																																				 wazn = "IiFodaEaLa";
																																			 }
																																			 root = faA+Eayn+laAm;
																																		 }
																																		 else {
																																			 m = Pattern.compile(IifofaEala).matcher(verb);
																																			 if(m.matches()){
																																				 faA = m.group(2);
																																				 faA2 = m.group(4);
																																				 Eayn = m.group(6);
																																				 laAm = m.group(8);
																																				 wazn = "IifofaEala";
																																				 root  =faA+Eayn+laAm;
																																				 if(!faA.equals("t")&&faA2.equals("t")){
																																					 faA = faA.replace("t", "w");
																																					 wazn = "IiFoTaEaLa";
																																					 root = faA+Eayn+laAm;
																																				 }
																																				 else if(faA.equals("t")&&faA2.equals("t")){
																																					 root = "w" + Eayn+laAm;
																																				 }
																																			 }
																																			 else{
																																				 m = Pattern.compile(IisotafaAla).matcher(verb);
																																				 if(m.matches()){
																																					 faA = m.group(2);
																																					 Eayn = m.group(4);
																																					 laAm = m.group(5);
																																					 wazn = "IisotaFaAla";
																																					 root = faA+Eayn+laAm;
																																						 
																					
																																				 }
																																				 else{
																																					 m = Pattern.compile(faEoEaY).matcher(verb);
																																					 if(m.matches()){
																																						 faA = m.group(1);
																																						 Eayn = m.group(3);
																																						 Eayn2 = m.group(5);
																																						 wazn = "faEoEaY";
																																						 root = faA+Eayn+"y";
																																					 }
																																					 
																																					 else{
																																						 m = Pattern.compile(tafaEoEaY).matcher(verb);
																																						 if(m.matches()){
																																							 faA = m.group(1);
																																							 Eayn = m.group(3);
																																							 Eayn2 = m.group(5);
																																							 wazn = "tafaEoEaY";
																																							 root = faA+Eayn+"y";
																																						 }
																																						 else{
																																							 m = Pattern.compile(IisotafoEaY).matcher(verb);
																																							 if(m.matches()){
																																								 faA = m.group(2);
																																								 Eayn = m.group(4);
																																								 wazn = "IisotafoEaY";
																																								 root = faA+Eayn+"y";
																																							 }
																																							 else{
																																								
																																									 m = Pattern.compile(faEaY).matcher(verb); 
																																									 if(m.matches()){
																																										 faA = m.group(1);
																																										 Eayn = m.group(3);
																																										 fillNaqiSMap();
																																										 if(naAqiS_map.containsKey(verb)){
																																										 if(naAqiS_map.get(verb).equals("y")){
																																											 laAm ="y";
																																										 wazn = "faEaY1";
																																										 }
																																										 else if(naAqiS_map.get(verb).equals("w")){
																																											laAm = "w";
																																											 wazn = "faEaY2";
																																											 }
																																										 else if(naAqiS_map.get(verb).equals("A")){
																																											 laAm ="A";
																																											 wazn = "faEaY3";
																																										 }}
																																										 else{
																																											 laAm = "y";
																																											 wazn = "faEaY1";
																																										 }
																																										 root = faA+Eayn+laAm;
																																									 
																																									 }
																																									 else{
																																										 m = Pattern.compile(OafoEaY).matcher(verb);
																																										 if(m.matches()){
																																										 faA = m.group(2);
																																											 Eayn = m.group(4);
																																											 wazn = "OafoEaY";
																																											 root = faA+Eayn+"y/w";
																																										 }
																																										 else{
																																											 m = Pattern.compile(IinofaEaY).matcher(verb);
																																											 if(m.matches()){
																																												 faA = m.group(2);
																																												 Eayn = m.group(4);
//																																												 laAm = "y";
																																												 wazn = "IinofaEaY";
																																												 root = faA+Eayn+"y/w";
																																												 
																																											 }
																																											 else{
																																												 m = Pattern.compile(IifotaEaY).matcher(verb);
																																												 if(m.matches()){
																																													 faA = m.group(2);
																																													 Eayn = m.group(4);
																																													 wazn = "IifotaEaY";
																																													 root = faA+Eayn +"y/w";
																								
																																												 }
																																												 else{
																																													 m = Pattern.compile(tafaAEaY).matcher(verb);
																																													 if(m.matches()){
																																														 faA = m.group(2);
																																														 Eayn = m.group(4);
																																														 wazn = "tafaAEaY";
																																														 root = faA+Eayn+"y/w";
																																													 }
																																													 else{
																																														 m  = Pattern.compile(faAEoEa).matcher(verb);
																																														 if(m.matches()){
																																															 faA = m.group(1);
																																															 Eayn = m.group(3);
																																															 Eayn2 = m.group(5);
																																															 wazn = "faAEoEa";
																																															 root = faA+Eayn+Eayn2;
																																														 }
																																														 else{
																																															 m  = Pattern.compile(tafaAEoEa).matcher(verb);
																																															 if(m.matches()){
																																																 faA = m.group(1);
																																																 Eayn = m.group(3);
																																																 Eayn2 = m.group(5);
																																																 wazn = "tafaAEoEa";
																																																 root = faA+Eayn+Eayn2;
																																															 }
																																															 else{
																																																 m  = Pattern.compile(IifofaEaY).matcher(verb);
																																																 if(m.matches()){
																												
																																																	 faA = m.group(2);
																																																	 faA2 = m.group(4);
																																																	 Eayn = m.group(6);
																																																	 wazn = "IifofaEaY";
																																																	 if(faA.equals("t")&&faA2.equals("t")){
																			
																																																	 root = "w"+faA2+Eayn;
																																																	 }
																																																	 else{
																																																		 root = faA2+Eayn+"y/w";
																																																	 }
																																																 }
																																																 
																																																	else{
																																																		m = Pattern.compile(faEala).matcher(verb);
																																																		if(m.matches()){
																																																			wazn = "faE0la";
																																																			faA = m.group(1);
																																																			Eayn = m.group(3);
																																																			laAm = m.group(5);
																																																			root = faA+Eayn+laAm;
																																																		}
																																																		
                                                                                                                                                                                                 else{
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
	 
	//Replace stem verb with C (consonant) and V (vowel)     
	public String get_CVCV(String ENTR){	
		String verb_patt ="";
		char[] charArr = ENTR.toCharArray();
		String char1 =null;
	
        for(int i = 0; i<charArr.length; i++){
        	
        	char1 = Character.toString(charArr[i]);
        	 
        	if(char1.matches(F))
        	{verb_patt += char1.replace(char1, "C");
        	
        	}
        	else if(char1.matches(vow)){
        		verb_patt += char1.replace(char1, "V");
        	}
      
        	
        }
        return verb_patt;
	}

	
	public String get_VVV(String ENTR){
		char[] charArr = ENTR.toCharArray();
		String  verb_vocal ="";
        for(int i = 0; i<charArr.length; i++){
        	String char1 = Character.toString(charArr[i]);
        	if(char1.matches(vow)){
        		verb_vocal += char1;
        	}
        	else{
        		verb_vocal += "_";
        	}
        } 
        return verb_vocal;
	}
	
	public String get_wazn(){
		return wazn;
	}
	public String get_FaA1(){
		return faA;
	}
	public String get_FaA2(){
		return faA2;
	}
	public String get_Eayn1(){
		return Eayn;
	}
	public String get_Eayn2(){
		return Eayn2;
	}
	public String get_LaAm1(){
		return laAm;
	}
	public String get_LaAm2(){
		return laAm2;
	}
	public String get_LaAm3(){
		return laAm3;
	}
	public String get_root(){
		return root;
	}
	
	public void fillEillaMap(){
		EillaMap.put("OaAba","w");
		EillaMap.put("OaAta","y");
		EillaMap.put("OaAva","y");
		EillaMap.put("OaAja","y");
		EillaMap.put("OaAHa","w");
		EillaMap.put("OaAxa","y");
		EillaMap.put("OaAda","w");
		EillaMap.put("OaA*a","w");
		EillaMap.put("OaAra","w");
		EillaMap.put("OaAza","y");
		EillaMap.put("OaATa","y");
		EillaMap.put("OaASa","y");
		EillaMap.put("OaADa","y");
		EillaMap.put("OaAZa","y");
		EillaMap.put("OaAsa","w");
		EillaMap.put("OaAla","w");
		EillaMap.put("OaAma","w");
		EillaMap.put("OaAna","w");
		EillaMap.put("OaAha","w");
		EillaMap.put("OaAEa","y");
		EillaMap.put("OaAga","y");
		EillaMap.put("OaA$a","w");
		EillaMap.put("OaAya","y");
		EillaMap.put("OaAwa","w");
		EillaMap.put("OaAfa","y");
		EillaMap.put("OaAqa","y");
		EillaMap.put("OaAka","y");
		EillaMap.put("baA'a","w");
		EillaMap.put("baAta","y");
		EillaMap.put("baAva","y");
		EillaMap.put("baAja","y");
		EillaMap.put("baAHa","w");
		EillaMap.put("baAxa","w");
		EillaMap.put("baAda","y");
		EillaMap.put("baA*a","y");
		EillaMap.put("baAra","w");
		EillaMap.put("baAza","y");
		EillaMap.put("baATa","y");
		EillaMap.put("baASa","w");
		EillaMap.put("baADa","y");
		EillaMap.put("baAZa","y");
		EillaMap.put("baAsa","w");
		EillaMap.put("baAla","w");
		EillaMap.put("baAma","y");
		EillaMap.put("baAna","y");
		EillaMap.put("baAha","A");
		EillaMap.put("baAEa","y");
		EillaMap.put("baAga","y");
		EillaMap.put("baA$a","w");
		EillaMap.put("baAya","A");
		EillaMap.put("baAwa","A");
		EillaMap.put("baAfa","y");
		EillaMap.put("baAqa","w");
		EillaMap.put("baAka","w");
		EillaMap.put("taA'a","w");
		EillaMap.put("taAba","w");
		EillaMap.put("taAva","w");
		EillaMap.put("taAja","w");
		EillaMap.put("taAHa","w");
		EillaMap.put("taAxa","w");
		EillaMap.put("taAda","w");
		EillaMap.put("taA*a","w");
		EillaMap.put("taAra","w");
		EillaMap.put("taAza","w");
		EillaMap.put("taATa","y");
		EillaMap.put("taASa","w");
		EillaMap.put("taADa","y");
		EillaMap.put("taAZa","y");
		EillaMap.put("taAsa","w");
		EillaMap.put("taAla","w");
		EillaMap.put("taAma","w");
		EillaMap.put("taAna","y");
		EillaMap.put("taAha","y");
		EillaMap.put("taAEa","y");
		EillaMap.put("taAga","w");
		EillaMap.put("taA$a","w");
		EillaMap.put("taAya","w");
		EillaMap.put("taAwa","A");
		EillaMap.put("taAfa","w");
		EillaMap.put("taAqa","w");
		EillaMap.put("taAka","w");
		EillaMap.put("vaA'a","w");
		EillaMap.put("vaAba","w");
		EillaMap.put("vaAva","w");
		EillaMap.put("vaAja","w");
		EillaMap.put("vaAHa","w");
		EillaMap.put("vaAxa","w");
		EillaMap.put("vaAda","w");
		EillaMap.put("vaA*a","w");
		EillaMap.put("vaAra","w");
		EillaMap.put("vaAza","w");
		EillaMap.put("vaATa","y");
		EillaMap.put("vaASa","w");
		EillaMap.put("vaADa","y");
		EillaMap.put("vaAZa","y");
		EillaMap.put("vaAsa","w");
		EillaMap.put("vaAla","w");
		EillaMap.put("vaAma","w");
		EillaMap.put("vaAna","y");
		EillaMap.put("vaAha","y");
		EillaMap.put("vaAEa","y");
		EillaMap.put("vaAga","w");
		EillaMap.put("vaA$a","w");
		EillaMap.put("vaAya","w");
		EillaMap.put("vaAwa","A");
		EillaMap.put("vaAfa","w");
		EillaMap.put("vaAqa","w");
		EillaMap.put("vaAka","w");
		EillaMap.put("jaA'a","y");
		EillaMap.put("jaAba","w");
		EillaMap.put("jaAta","y");
		EillaMap.put("jaAva","y");
		EillaMap.put("jaAHa","y");
		EillaMap.put("jaAxa","y");
		EillaMap.put("jaAda","y");
		EillaMap.put("jaA*a","y");
		EillaMap.put("jaAra","y");
		EillaMap.put("jaAza","y");
		EillaMap.put("jaATa","y");
		EillaMap.put("jaASa","y");
		EillaMap.put("jaADa","y");
		EillaMap.put("jaAZa","y");
		EillaMap.put("jaAsa","w");
		EillaMap.put("jaAla","w");
		EillaMap.put("jaAma","w");
		EillaMap.put("jaAna","y");
		EillaMap.put("jaAha","w");
		EillaMap.put("jaAEa","w");
		EillaMap.put("jaAga","w");
		EillaMap.put("jaA$a","y");
		EillaMap.put("jaAya","A");
		EillaMap.put("jaAwa","A");
		EillaMap.put("jaAfa","y");
		EillaMap.put("jaAqa","w");
		EillaMap.put("jaAka","w");
		EillaMap.put("HaA'a","y");
		EillaMap.put("HaAba","y");
		EillaMap.put("HaAta","y");
		EillaMap.put("HaAva","y");
		EillaMap.put("HaAja","y");
		EillaMap.put("HaAxa","y");
		EillaMap.put("HaAda","y");
		EillaMap.put("HaA*a","y");
		EillaMap.put("HaAra","A");
		EillaMap.put("HaAza","w");
		EillaMap.put("HaATa","y");
		EillaMap.put("HaASa","y");
		EillaMap.put("HaADa","y");
		EillaMap.put("HaAZa","y");
		EillaMap.put("HaAsa","y");
		EillaMap.put("HaAla","w");
		EillaMap.put("HaAma","w");
		EillaMap.put("HaAna","y");
		EillaMap.put("HaAha","y");
		EillaMap.put("HaAEa","w");
		EillaMap.put("HaAga","y");
		EillaMap.put("HaA$a","w");
		EillaMap.put("HaAya","A");
		EillaMap.put("HaAwa","A");
		EillaMap.put("HaAfa","w");
		EillaMap.put("HaAqa","y");
		EillaMap.put("HaAka","y");
		EillaMap.put("xaA'a","y");
		EillaMap.put("xaAba","y");
		EillaMap.put("xaAta","y");
		EillaMap.put("xaAva","y");
		EillaMap.put("xaAja","y");
		EillaMap.put("xaAHa","w");
		EillaMap.put("xaAda","y");
		EillaMap.put("xaA*a","y");
		EillaMap.put("xaAra","w");
		EillaMap.put("xaAza","w");
		EillaMap.put("xaATa","y");
		EillaMap.put("xaASa","y");
		EillaMap.put("xaADa","w");
		EillaMap.put("xaAZa","w");
		EillaMap.put("xaAsa","y");
		EillaMap.put("xaAla","A");
		EillaMap.put("xaAma","y");
		EillaMap.put("xaAna","w");
		EillaMap.put("xaAha","w");
		EillaMap.put("xaAEa","w");
		EillaMap.put("xaAga","w");
		EillaMap.put("xaA$a","w");
		EillaMap.put("xaAya","A");
		EillaMap.put("xaAwa","A");
		EillaMap.put("xaAfa","A");
		EillaMap.put("xaAqa","y");
		EillaMap.put("xaAka","y");
		EillaMap.put("daA'a","A");
		EillaMap.put("daAba","w");
		EillaMap.put("daAta","w");
		EillaMap.put("daAva","w");
		EillaMap.put("daAja","y");
		EillaMap.put("daAHa","w");
		EillaMap.put("daAxa","w");
		EillaMap.put("daA*a","w");
		EillaMap.put("daAra","w");
		EillaMap.put("daAza","w");
		EillaMap.put("daATa","y");
		EillaMap.put("daASa","y");
		EillaMap.put("daADa","y");
		EillaMap.put("daAZa","y");
		EillaMap.put("daAsa","w");
		EillaMap.put("daAla","y");
		EillaMap.put("daAma","w");
		EillaMap.put("daAna","y");
		EillaMap.put("daAha","w");
		EillaMap.put("daAEa","y");
		EillaMap.put("daAga","w");
		EillaMap.put("daA$a","w");
		EillaMap.put("daAya","A");
		EillaMap.put("daAwa","A");
		EillaMap.put("daAfa","w");
		EillaMap.put("daAqa","w");
		EillaMap.put("daAka","w");
		EillaMap.put("daAda", "w");
		EillaMap.put("*aA'a","A");
		EillaMap.put("*aAba","w");
		EillaMap.put("*aAta","w");
		EillaMap.put("*aAva","w");
		EillaMap.put("*aAja","y");
		EillaMap.put("*aAHa","w");
		EillaMap.put("*aAxa","w");
		EillaMap.put("*aA*a","w");
		EillaMap.put("*aAra","w");
		EillaMap.put("*aAza","w");
		EillaMap.put("*aATa","y");
		EillaMap.put("*aASa","y");
		EillaMap.put("*aADa","y");
		EillaMap.put("*aAZa","y");
		EillaMap.put("*aAsa","w");
		EillaMap.put("*aAla","y");
		EillaMap.put("*aAma","w");
		EillaMap.put("*aAna","y");
		EillaMap.put("*aAha","w");
		EillaMap.put("*aAEa","y");
		EillaMap.put("*aAga","w");
		EillaMap.put("*aA$a","w");
		EillaMap.put("*aAya","A");
		EillaMap.put("*aAwa","A");
		EillaMap.put("*aAfa","w");
		EillaMap.put("*aAqa","w");
		EillaMap.put("*aAka","w");
		EillaMap.put("*aAda", "w");
		EillaMap.put("raA'a","w");
		EillaMap.put("raAba","w");
		EillaMap.put("raAta","w");
		EillaMap.put("raAva","w");
		EillaMap.put("raAja","w");
		EillaMap.put("raAHa","w");
		EillaMap.put("raAxa","w");
		EillaMap.put("raAda","y");
		EillaMap.put("raA*a","y");
		EillaMap.put("raAza","w");
		EillaMap.put("raATa","w");
		EillaMap.put("raASa","w");
		EillaMap.put("raADa","w");
		EillaMap.put("raAZa","w");
		EillaMap.put("raAsa","w");
		EillaMap.put("raAla","w");
		EillaMap.put("raAma","w");
		EillaMap.put("raAna","w");
		EillaMap.put("raAha","y");
		EillaMap.put("raAEa","w");
		EillaMap.put("raAga","w");
		EillaMap.put("raA$a","y");
		EillaMap.put("raAya","A");
		EillaMap.put("raAwa","A");
		EillaMap.put("raAfa","w");
		EillaMap.put("raAqa","w");
		EillaMap.put("raAka","w");
		EillaMap.put("zaA'a","y");
		EillaMap.put("zaAba","y");
		EillaMap.put("zaAta","y");
		EillaMap.put("zaAva","y");
		EillaMap.put("zaAja","y");
		EillaMap.put("zaAHa","y");
		EillaMap.put("zaAxa","y");
		EillaMap.put("zaAda","y");
		EillaMap.put("zaA*a","y");
		EillaMap.put("zaAra","w");
		EillaMap.put("zaATa","y");
		EillaMap.put("zaASa","y");
		EillaMap.put("zaADa","y");
		EillaMap.put("zaAZa","y");
		EillaMap.put("zaAsa","y");
		EillaMap.put("zaAla","w");
		EillaMap.put("zaAma","w");
		EillaMap.put("zaAna","y");
		EillaMap.put("zaAha","y");
		EillaMap.put("zaAEa","y");
		EillaMap.put("zaAga","y");
		EillaMap.put("zaA$a","y");
		EillaMap.put("zaAya","w");
		EillaMap.put("zaAwa","A");
		EillaMap.put("zaAfa","y");
		EillaMap.put("zaAqa","w");
		EillaMap.put("zaAka","w");
		EillaMap.put("TaA'a","y");
		EillaMap.put("TaAba","y");
		EillaMap.put("TaAta","y");
		EillaMap.put("TaAva","y");
		EillaMap.put("TaAja","y");
		EillaMap.put("TaAHa","y");
		EillaMap.put("TaAxa","w");
		EillaMap.put("TaAda","w");
		EillaMap.put("TaA*a","y");
		EillaMap.put("TaAra","y");
		EillaMap.put("TaAza","y");
		EillaMap.put("TaASa","y");
		EillaMap.put("TaADa","y");
		EillaMap.put("TaAZa","y");
		EillaMap.put("TaAsa","y");
		EillaMap.put("TaAla","w");
		EillaMap.put("TaAma","w");
		EillaMap.put("TaAna","y");
		EillaMap.put("TaAha","y");
		EillaMap.put("TaAEa","y");
		EillaMap.put("TaAga","y");
		EillaMap.put("TaA$a","y");
		EillaMap.put("TaAya","A");
		EillaMap.put("TaAwa","A");
		EillaMap.put("TaAfa","w");
		EillaMap.put("TaAqa","y");
		EillaMap.put("TaAka","y");
		EillaMap.put("SaA'a","w");
		EillaMap.put("SaAba","y");
		EillaMap.put("SaAta","w");
		EillaMap.put("SaAva","w");
		EillaMap.put("SaAja","y");
		EillaMap.put("SaAHa","y");
		EillaMap.put("SaAxa","y");
		EillaMap.put("SaAda","y");
		EillaMap.put("SaA*a","y");
		EillaMap.put("SaAra","y");
		EillaMap.put("SaAza","w");
		EillaMap.put("SaATa","w");
		EillaMap.put("SaADa","y");
		EillaMap.put("SaAZa","y");
		EillaMap.put("SaAsa","y");
		EillaMap.put("SaAla","w");
		EillaMap.put("SaAma","w");
		EillaMap.put("SaAna","w");
		EillaMap.put("SaAha","w");
		EillaMap.put("SaAEa","w");
		EillaMap.put("SaAga","w");
		EillaMap.put("SaA$a","w");
		EillaMap.put("SaAya","A");
		EillaMap.put("SaAwa","A");
		EillaMap.put("SaAfa","y");
		EillaMap.put("SaAqa","w");
		EillaMap.put("SaAka","w");
		EillaMap.put("DaA'a","y");
		EillaMap.put("DaAba","w");
		EillaMap.put("DaAta","y");
		EillaMap.put("DaAva","y");
		EillaMap.put("DaAja","y");
		EillaMap.put("DaAHa","y");
		EillaMap.put("DaAxa","w");
		EillaMap.put("DaAda","w");
		EillaMap.put("DaA*a","w");
		EillaMap.put("DaAra","w");
		EillaMap.put("DaAza","w");
		EillaMap.put("DaATa","w");
		EillaMap.put("DaASa","w");
		EillaMap.put("DaAZa","w");
		EillaMap.put("DaAsa","w");
		EillaMap.put("DaAla","y");
		EillaMap.put("DaAma","y");
		EillaMap.put("DaAna","w");
		EillaMap.put("DaAha","y");
		EillaMap.put("DaAEa","y");
		EillaMap.put("DaAga","y");
		EillaMap.put("DaA$a","w");
		EillaMap.put("DaAya","A");
		EillaMap.put("DaAwa","A");
		EillaMap.put("DaAfa","y");
		EillaMap.put("DaAqa","y");
		EillaMap.put("DaAka","w");
		EillaMap.put("ZaA'a","y");
		EillaMap.put("ZaAba","w");
		EillaMap.put("ZaAta","y");
		EillaMap.put("ZaAva","y");
		EillaMap.put("ZaAja","y");
		EillaMap.put("ZaAHa","y");
		EillaMap.put("ZaAxa","w");
		EillaMap.put("ZaAda","w");
		EillaMap.put("ZaA*a","w");
		EillaMap.put("ZaAra","w");
		EillaMap.put("ZaAza","w");
		EillaMap.put("ZaATa","w");
		EillaMap.put("ZaASa","w");
		EillaMap.put("ZaAZa","w");
		EillaMap.put("ZaAsa","w");
		EillaMap.put("ZaAla","y");
		EillaMap.put("ZaAma","y");
		EillaMap.put("ZaAna","w");
		EillaMap.put("ZaAha","y");
		EillaMap.put("ZaAEa","y");
		EillaMap.put("ZaAga","y");
		EillaMap.put("ZaA$a","w");
		EillaMap.put("ZaAya","A");
		EillaMap.put("ZaAwa","A");
		EillaMap.put("ZaAfa","y");
		EillaMap.put("ZaAqa","y");
		EillaMap.put("ZaAka","w");
		EillaMap.put("saA'a","y");
		EillaMap.put("saAba","y");
		EillaMap.put("saAta","y");
		EillaMap.put("saAva","y");
		EillaMap.put("saAja","y");
		EillaMap.put("saAHa","y");
		EillaMap.put("saAxa","y");
		EillaMap.put("saAda","w");
		EillaMap.put("saA*a","w");
		EillaMap.put("saAra","y");
		EillaMap.put("saAza","y");
		EillaMap.put("saATa","w");
		EillaMap.put("saASa","y");
		EillaMap.put("saADa","y");
		EillaMap.put("saAZa","y");
		EillaMap.put("saAla","y");
		EillaMap.put("saAma","w");
		EillaMap.put("saAna","y");
		EillaMap.put("saAha","w");
		EillaMap.put("saAEa","w");
		EillaMap.put("saAga","w");
		EillaMap.put("saA$a","y");
		EillaMap.put("saAya","A");
		EillaMap.put("saAwa","A");
		EillaMap.put("saAfa","y");
		EillaMap.put("saAqa","w");
		EillaMap.put("saAka","w");
		EillaMap.put("laA'a","w");
		EillaMap.put("laAba","w");
		EillaMap.put("laAta","y");
		EillaMap.put("laAva","y");
		EillaMap.put("laAja","w");
		EillaMap.put("laAHa","w");
		EillaMap.put("laAxa","w");
		EillaMap.put("laAda","w");
		EillaMap.put("laA*a","w");
		EillaMap.put("laAra","w");
		EillaMap.put("laAza","w");
		EillaMap.put("laASa","y");
		EillaMap.put("laADa","w");
		EillaMap.put("laAZa","w");
		EillaMap.put("laAsa","w");
		EillaMap.put("laAma","w");
		EillaMap.put("laAna","y");
		EillaMap.put("laAha","y");
		EillaMap.put("laAEa","w");
		EillaMap.put("laAga","w");
		EillaMap.put("laA$a","y");
		EillaMap.put("laAya","A");
		EillaMap.put("laAwa","A");
		EillaMap.put("laAfa","w");
		EillaMap.put("laAqa","y");
		EillaMap.put("laAka","w");
		EillaMap.put("maA'a","w");
		EillaMap.put("maAba","w");
		EillaMap.put("maAta","w");
		EillaMap.put("maAva","w");
		EillaMap.put("maAja","w");
		EillaMap.put("maAHa","y");
		EillaMap.put("maAxa","y");
		EillaMap.put("maAda","y");
		EillaMap.put("maA*a","y");
		EillaMap.put("maAra","w");
		EillaMap.put("maAza","y");
		EillaMap.put("maATa","y");
		EillaMap.put("maASa","w");
		EillaMap.put("maADa","y");
		EillaMap.put("maAZa","y");
		EillaMap.put("maAsa","y");
		EillaMap.put("maAla","y");
		EillaMap.put("maAna","w");
		EillaMap.put("maAha","y");
		EillaMap.put("maAEa","y");
		EillaMap.put("maAga","w");
		EillaMap.put("maA$a","y");
		EillaMap.put("maAya","A");
		EillaMap.put("maAwa","A");
		EillaMap.put("maAfa","y");
		EillaMap.put("maAqa","y");
		EillaMap.put("maAka","y");
		EillaMap.put("naA'a","w");
		EillaMap.put("naAba","w");
		EillaMap.put("naAta","y");
		EillaMap.put("naAva","y");
		EillaMap.put("naAja","y");
		EillaMap.put("naAHa","w");
		EillaMap.put("naAxa","w");
		EillaMap.put("naAda","w");
		EillaMap.put("naA*a","w");
		EillaMap.put("naAra","w");
		EillaMap.put("naAza","y");
		EillaMap.put("naATa","w");
		EillaMap.put("naASa","w");
		EillaMap.put("naADa","w");
		EillaMap.put("naAZa","w");
		EillaMap.put("naAsa","y");
		EillaMap.put("naAla","A");
		EillaMap.put("naAma","A");
		EillaMap.put("naAha","y");
		EillaMap.put("naAEa","y");
		EillaMap.put("naAga","w");
		EillaMap.put("naA$a","y");
		EillaMap.put("naAya","A");
		EillaMap.put("naAwa","A");
		EillaMap.put("naAfa","y");
		EillaMap.put("naAqa","w");
		EillaMap.put("naAka","y");
		EillaMap.put("haA'a","y");
		EillaMap.put("haAba","A");
		EillaMap.put("haAta","y");
		EillaMap.put("haAva","y");
		EillaMap.put("haAja","y");
		EillaMap.put("haAHa","A");
		EillaMap.put("haAxa","A");
		EillaMap.put("haAda","y");
		EillaMap.put("haA*a","y");
		EillaMap.put("haAra","y");
		EillaMap.put("haAza","y");
		EillaMap.put("haATa","y");
		EillaMap.put("haASa","y");
		EillaMap.put("haADa","y");
		EillaMap.put("haAZa","y");
		EillaMap.put("haAsa","w");
		EillaMap.put("haAla","y");
		EillaMap.put("haAma","y");
		EillaMap.put("haAna","w");
		EillaMap.put("haAEa","w");
		EillaMap.put("haAga","y");
		EillaMap.put("haA$a","y");
		EillaMap.put("haAya","A");
		EillaMap.put("haAwa","A");
		EillaMap.put("haAfa","y");
		EillaMap.put("haAqa","y");
		EillaMap.put("haAka","y");
		EillaMap.put("EaA'a","y");
		EillaMap.put("EaAba","y");
		EillaMap.put("EaAta","y");
		EillaMap.put("EaAva","y");
		EillaMap.put("EaAja","w");
		EillaMap.put("EaAHa","w");
		EillaMap.put("EaAxa","w");
		EillaMap.put("EaAda","w");
		EillaMap.put("EaA*a","w");
		EillaMap.put("EaAra","w");
		EillaMap.put("EaAza","w");
		EillaMap.put("EaATa","w");
		EillaMap.put("EaASa","w");
		EillaMap.put("EaADa","w");
		EillaMap.put("EaAZa","w");
		EillaMap.put("EaAsa","y");
		EillaMap.put("EaAla","y");
		EillaMap.put("EaAma","w");
		EillaMap.put("EaAna","y");
		EillaMap.put("EaAha","y");
		EillaMap.put("EaAga","y");
		EillaMap.put("EaA$a","y");
		EillaMap.put("EaAya","A");
		EillaMap.put("EaAwa","A");
		EillaMap.put("EaAfa","y");
		EillaMap.put("EaAqa","y");
		EillaMap.put("EaAka","y");
		EillaMap.put("gaA'a","y");
		EillaMap.put("gaAba","y");
		EillaMap.put("gaAta","y");
		EillaMap.put("gaAva","y");
		EillaMap.put("gaAja","w");
		EillaMap.put("gaAHa","w");
		EillaMap.put("gaAxa","w");
		EillaMap.put("gaAda","y");
		EillaMap.put("gaA*a","y");
		EillaMap.put("gaAra","y");
		EillaMap.put("gaAza","y");
		EillaMap.put("gaATa","y");
		EillaMap.put("gaASa","w");
		EillaMap.put("gaADa","y");
		EillaMap.put("gaAZa","y");
		EillaMap.put("gaAsa","y");
		EillaMap.put("gaAla","w");
		EillaMap.put("gaAma","y");
		EillaMap.put("gaAna","y");
		EillaMap.put("gaAha","y");
		EillaMap.put("gaAEa","y");
		EillaMap.put("gaA$a","y");
		EillaMap.put("gaAya","A");
		EillaMap.put("gaAwa","A");
		EillaMap.put("gaAfa","y");
		EillaMap.put("gaAqa","A");
		EillaMap.put("gaAka","y");
		EillaMap.put("$aA'a","A");
		EillaMap.put("$aAba","y");
		EillaMap.put("$aAta","y");
		EillaMap.put("$aAva","y");
		EillaMap.put("$aAja","w");
		EillaMap.put("$aAHa","y");
		EillaMap.put("$aAxa","y");
		EillaMap.put("$aAda","y");
		EillaMap.put("$aA*a","y");
		EillaMap.put("$aAra","w");
		EillaMap.put("$aAza","y");
		EillaMap.put("$aATa","y");
		EillaMap.put("$aASa","y");
		EillaMap.put("$aADa","w");
		EillaMap.put("$aAZa","w");
		EillaMap.put("$aAsa","y");
		EillaMap.put("$aAla","y");
		EillaMap.put("$aAma","w");
		EillaMap.put("$aAna","y");
		EillaMap.put("$aAha","w");
		EillaMap.put("$aAEa","y");
		EillaMap.put("$aAga","y");
		EillaMap.put("$aAya","A");
		EillaMap.put("$aAwa","A");
		EillaMap.put("$aAfa","w");
		EillaMap.put("$aAqa","w");
		EillaMap.put("$aAka","w");
		EillaMap.put("yaA'a","w");
		EillaMap.put("yaAba","w");
		EillaMap.put("yaAta","w");
		EillaMap.put("yaAva","w");
		EillaMap.put("yaAja","w");
		EillaMap.put("yaAHa","w");
		EillaMap.put("yaAxa","w");
		EillaMap.put("yaAda","w");
		EillaMap.put("yaA*a","w");
		EillaMap.put("yaAra","w");
		EillaMap.put("yaAza","w");
		EillaMap.put("yaATa","w");
		EillaMap.put("yaASa","w");
		EillaMap.put("yaADa","w");
		EillaMap.put("yaAZa","w");
		EillaMap.put("yaAsa","w");
		EillaMap.put("yaAla","w");
		EillaMap.put("yaAma","w");
		EillaMap.put("yaAna","w");
		EillaMap.put("yaAha","w");
		EillaMap.put("yaAEa","w");
		EillaMap.put("yaAga","w");
		EillaMap.put("yaA$a","w");
		EillaMap.put("yaAwa","w");
		EillaMap.put("yaAfa","w");
		EillaMap.put("yaAqa","w");
		EillaMap.put("yaAka","w");
		EillaMap.put("waA'a","w");
		EillaMap.put("waAba","w");
		EillaMap.put("waAta","w");
		EillaMap.put("waAva","w");
		EillaMap.put("waAja","w");
		EillaMap.put("waAHa","w");
		EillaMap.put("waAxa","y");
		EillaMap.put("waAda","y");
		EillaMap.put("waA*a","y");
		EillaMap.put("waAra","w");
		EillaMap.put("waAza","y");
		EillaMap.put("waATa","y");
		EillaMap.put("waASa","w");
		EillaMap.put("waADa","y");
		EillaMap.put("waAZa","y");
		EillaMap.put("waAsa","y");
		EillaMap.put("waAla","w");
		EillaMap.put("waAma","y");
		EillaMap.put("waAna","y");
		EillaMap.put("waAha","y");
		EillaMap.put("waAEa","y");
		EillaMap.put("waAga","y");
		EillaMap.put("waA$a","y");
		EillaMap.put("waAya","y");
		EillaMap.put("waAfa","w");
		EillaMap.put("waAqa","w");
		EillaMap.put("waAka","y");
		EillaMap.put("faA'a","y");
		EillaMap.put("faAba","y");
		EillaMap.put("faAta","w");
		EillaMap.put("faAva","w");
		EillaMap.put("faAja","w");
		EillaMap.put("faAHa","w");
		EillaMap.put("faAxa","w");
		EillaMap.put("faAda","y");
		EillaMap.put("faA*a","y");
		EillaMap.put("faAra","w");
		EillaMap.put("faAza","w");
		EillaMap.put("faATa","w");
		EillaMap.put("faASa","w");
		EillaMap.put("faADa","y");
		EillaMap.put("faAZa","y");
		EillaMap.put("faAsa","w");
		EillaMap.put("faAla","y");
		EillaMap.put("faAma","w");
		EillaMap.put("faAna","y");
		EillaMap.put("faAha","w");
		EillaMap.put("faAEa","w");
		EillaMap.put("faAga","w");
		EillaMap.put("faA$a","y");
		EillaMap.put("faAya","A");
		EillaMap.put("faAwa","A");
		EillaMap.put("faAqa","w");
		EillaMap.put("faAka","y");
		EillaMap.put("qaA'a","y");
		EillaMap.put("qaAba","w");
		EillaMap.put("qaAta","w");
		EillaMap.put("qaAva","w");
		EillaMap.put("qaAja","y");
		EillaMap.put("qaAHa","y");
		EillaMap.put("qaAxa","w");
		EillaMap.put("qaAda","w");
		EillaMap.put("qaA*a","w");
		EillaMap.put("qaAra","y");
		EillaMap.put("qaAza","y");
		EillaMap.put("qaATa","y");
		EillaMap.put("qaASa","y");
		EillaMap.put("qaADa","y");
		EillaMap.put("qaAZa","y");
		EillaMap.put("qaAsa","y");
		EillaMap.put("qaAla","w");
		EillaMap.put("qaAma","w");
		EillaMap.put("qaAna","y");
		EillaMap.put("qaAha","y");
		EillaMap.put("qaAEa","w");
		EillaMap.put("qaAga","w");
		EillaMap.put("qaA$a","y");
		EillaMap.put("qaAya","A");
		EillaMap.put("qaAwa","A");
		EillaMap.put("qaAfa","w");
		EillaMap.put("qaAka","y");
		EillaMap.put("kaA'a","y");
		EillaMap.put("kaAba","w");
		EillaMap.put("kaAta","y");
		EillaMap.put("kaAva","y");
		EillaMap.put("kaAja","y");
		EillaMap.put("kaAHa","w");
		EillaMap.put("kaAxa","w");
		EillaMap.put("kaAda","A");
		EillaMap.put("kaA*a","A");
		EillaMap.put("kaAra","w");
		EillaMap.put("kaAza","y");
		EillaMap.put("kaATa","w");
		EillaMap.put("kaASa","w");
		EillaMap.put("kaADa","y");
		EillaMap.put("kaAZa","y");
		EillaMap.put("kaAsa","y");
		EillaMap.put("kaAla","y");
		EillaMap.put("kaAma","w");
		EillaMap.put("kaAna","w");
		EillaMap.put("kaAha","w");
		EillaMap.put("kaAEa","w");
		EillaMap.put("kaAga","y");
		EillaMap.put("kaA$a","w");
		EillaMap.put("kaAya","A");
		EillaMap.put("kaAwa","A");
		EillaMap.put("kaAfa","y");
		EillaMap.put("kaAqa","y");
	}
	public void fillNaqiSMap(){
		naAqiS_map.put("OaOaY", "w");
		naAqiS_map.put("OabaY", "A");
		naAqiS_map.put("OataY", "y");
		naAqiS_map.put("OavaY", "y");
		naAqiS_map.put("OajaY", "w");
		naAqiS_map.put("OaHaY", "w");
		naAqiS_map.put("OaxaY", "y");
		naAqiS_map.put("OadaY", "y");
		naAqiS_map.put("Oa*aY", "y");
		naAqiS_map.put("OaraY", "y");
		naAqiS_map.put("OazaY", "w");
		naAqiS_map.put("OaTaY", "y");
		naAqiS_map.put("OaSaY", "w");
		naAqiS_map.put("OaDaY", "y");
		naAqiS_map.put("OaZaY", "y");
		naAqiS_map.put("OasaY", "w");
		naAqiS_map.put("OalaY", "w");
		naAqiS_map.put("OamaY", "y");
		naAqiS_map.put("OanaY", "y");
		naAqiS_map.put("OahaY", "y");
		naAqiS_map.put("OaEaY", "A");
		naAqiS_map.put("OagaY", "A");
		naAqiS_map.put("Oa$aY", "w");
		naAqiS_map.put("OayaY", "A");
		naAqiS_map.put("OawaY", "y");
		naAqiS_map.put("OafaY", "w");
		naAqiS_map.put("OaqaY", "y");
		naAqiS_map.put("OakaY", "w");
		naAqiS_map.put("baOaY", "w");
		naAqiS_map.put("bataY", "w");
		naAqiS_map.put("bavaY", "w");
		naAqiS_map.put("bajaY", "w");
		naAqiS_map.put("baHaY", "w");
		naAqiS_map.put("baxaY", "y");
		naAqiS_map.put("badaY", "w");
		naAqiS_map.put("ba*aY", "w");
		naAqiS_map.put("baraY", "y");
		naAqiS_map.put("bazaY", "w");
		naAqiS_map.put("baTaY", "y");
		naAqiS_map.put("baSaY", "y");
		naAqiS_map.put("baDaY", "y");
		naAqiS_map.put("baZaY", "y");
		naAqiS_map.put("basaY", "w");
		naAqiS_map.put("balaY", "A");
		naAqiS_map.put("bamaY", "y");
		naAqiS_map.put("banaY", "y");
		naAqiS_map.put("bahaY", "w");
		naAqiS_map.put("baEaY", "w");
		naAqiS_map.put("bagaY", "y");
		naAqiS_map.put("ba$aY", "w");
		naAqiS_map.put("bayaY", "A");
		naAqiS_map.put("bawaY", "A");
		naAqiS_map.put("bafaY", "w");
		naAqiS_map.put("baqaY", "A");
		naAqiS_map.put("bakaY", "y");
		naAqiS_map.put("taOaY", "A");
		naAqiS_map.put("tabaY", "w");
		naAqiS_map.put("tavaY", "w");
		naAqiS_map.put("tajaY", "w");
		naAqiS_map.put("taHaY", "w");
		naAqiS_map.put("taxaY", "A");
		naAqiS_map.put("tadaY", "w");
		naAqiS_map.put("ta*aY", "w");
		naAqiS_map.put("taraY", "y");
		naAqiS_map.put("tazaY","y");
		naAqiS_map.put("taTaY", "w");
		naAqiS_map.put("taSaY", "w");
		naAqiS_map.put("taDaY", "y");
		naAqiS_map.put("taZaY", "y");
		naAqiS_map.put("tasaY", "w");
		naAqiS_map.put("talaY", "w");
		naAqiS_map.put("tamaY", "w");
		naAqiS_map.put("tanaY", "y");
		naAqiS_map.put("tahaY", "w");
		naAqiS_map.put("taEaY", "w");
		naAqiS_map.put("tagaY", "w");
		naAqiS_map.put("ta$aY", "y");
		naAqiS_map.put("tayaY", "A");
		naAqiS_map.put("tawaY", "y");
		naAqiS_map.put("tafaY", "w");
		naAqiS_map.put("taqaY", "A");
		naAqiS_map.put("takaY", "w");
		naAqiS_map.put("vaOaY", "A");
		naAqiS_map.put("vabaY", "w");
		naAqiS_map.put("vavaY", "w");
		naAqiS_map.put("vajaY", "w");
		naAqiS_map.put("vaHaY", "w");
		naAqiS_map.put("vaxaY", "A");
		naAqiS_map.put("vadaY", "w");
		naAqiS_map.put("va*aY", "w");
		naAqiS_map.put("varaY", "y");
		naAqiS_map.put("vazaY","y");
		naAqiS_map.put("vataY", "w");
		naAqiS_map.put("vaSaY", "w");
		naAqiS_map.put("vaDaY", "y");
		naAqiS_map.put("vaZaY", "y");
		naAqiS_map.put("vasaY", "w");
		naAqiS_map.put("valaY", "w");
		naAqiS_map.put("vamaY", "w");
		naAqiS_map.put("vanaY", "y");
		naAqiS_map.put("vahaY", "w");
		naAqiS_map.put("vaEaY", "w");
		naAqiS_map.put("vagaY", "w");
		naAqiS_map.put("va$aY", "y");
		naAqiS_map.put("vayaY", "A");
		naAqiS_map.put("vawaY", "y");
		naAqiS_map.put("vafaY", "w");
		naAqiS_map.put("vaqaY", "A");
		naAqiS_map.put("vakaY", "w");
		naAqiS_map.put("jaOaY", "w");
		naAqiS_map.put("jabaY", "y");
		naAqiS_map.put("jataY", "w");
		naAqiS_map.put("javaY", "w");
		naAqiS_map.put("jaHaY", "w");
		naAqiS_map.put("jaxaY", "w");
		naAqiS_map.put("jadaY", "y");
		naAqiS_map.put("ja*aY", "y");
		naAqiS_map.put("jaraY", "y");
		naAqiS_map.put("jazaY", "y");
		naAqiS_map.put("jaTaY", "w");
		naAqiS_map.put("jaSaY", "w");
		naAqiS_map.put("jaDaY", "y");
		naAqiS_map.put("jaZaY", "y");
		naAqiS_map.put("jasaY", "w");
		naAqiS_map.put("jalaY", "y");
		naAqiS_map.put("jamaY", "y");
		naAqiS_map.put("janaY", "y");
		naAqiS_map.put("jahaY", "w");
		naAqiS_map.put("jaEaY", "w");
		naAqiS_map.put("jagaY", "w");
		naAqiS_map.put("ja$aY", "w");
		naAqiS_map.put("jayaY", "A");
		naAqiS_map.put("jawaY", "y");
		naAqiS_map.put("jafaY", "y");
		naAqiS_map.put("jaqaY", "w");
		naAqiS_map.put("jakaY",  "w");
		naAqiS_map.put("HaOaY", "w");
		naAqiS_map.put("HabaY", "w");
		naAqiS_map.put("HataY", "w");
		naAqiS_map.put("HavaY", "w");
		naAqiS_map.put("HajaY", "w");
		naAqiS_map.put("HaxaY", "w");
		naAqiS_map.put("HadaY", "w");
		naAqiS_map.put("Ha*aY", "w");
		naAqiS_map.put("HaraY", "w");
		naAqiS_map.put("HazaY", "y");
		naAqiS_map.put("HaTaY", "w");
		naAqiS_map.put("HaSaY", "w");
		naAqiS_map.put("HaDaY", "y");
		naAqiS_map.put("HaZaY", "y");
		naAqiS_map.put("HasaY", "w");
		naAqiS_map.put("HalaY", "w");
		naAqiS_map.put("HamaY", "y");
		naAqiS_map.put("HanaY", "y");
		naAqiS_map.put("HahaY", "w");
		naAqiS_map.put("HaEaY", "w");
		naAqiS_map.put("HagaY", "w");
		naAqiS_map.put("Ha$aY", "w");
		naAqiS_map.put("HayaY", "A");
		naAqiS_map.put("HawaY", "y");
		naAqiS_map.put("HafaY", "A");
		naAqiS_map.put("HaqaY", "y");
		naAqiS_map.put("HakaY", "y");
		naAqiS_map.put("xaOaY", "y");
		naAqiS_map.put("xabaY", "w");
		naAqiS_map.put("xataY", "w");
		naAqiS_map.put("xavaY", "w");
		naAqiS_map.put("xajaY", "w");
		naAqiS_map.put("xaHaY", "w");
		naAqiS_map.put("xadaY", "y");
		naAqiS_map.put("xa*aY", "y");
		naAqiS_map.put("xaraY",  "A");
		naAqiS_map.put("xazaY", "w");
		naAqiS_map.put("xaTaY", "w");
		naAqiS_map.put("xaSaY", "y");
		naAqiS_map.put("xaDaY", "w");
		naAqiS_map.put("xaZaY", "w");
		naAqiS_map.put("xasaY", "y");
		naAqiS_map.put("xalaY", "w");
		naAqiS_map.put("xamaY", "w");
		naAqiS_map.put("xanaY", "w");
		naAqiS_map.put("xahaY", "w");
		naAqiS_map.put("xaEaY", "w");
		naAqiS_map.put("xagaY", "w");
		naAqiS_map.put("xa$aY", "y");
		naAqiS_map.put("xayaY", "A");
		naAqiS_map.put("xawaY", "y");
		naAqiS_map.put("xafaY", "y");
		naAqiS_map.put("xaqaY", "w");
		naAqiS_map.put("xakaY", "y");
		naAqiS_map.put("daOaY", "w");
		naAqiS_map.put("dabaY", "w");
		naAqiS_map.put("dataY", "w");
		naAqiS_map.put("davaY", "w");
		naAqiS_map.put("dajaY", "w");
		naAqiS_map.put("daHaY", "w");
		naAqiS_map.put("daxaY", "y");
		naAqiS_map.put("da*aY", "w");
		naAqiS_map.put("daraY", "y");
		naAqiS_map.put("dazaY", "w");
		naAqiS_map.put("daTaY", "w");
		naAqiS_map.put("daSaY", "w");
		naAqiS_map.put("daDaY", "w");
		naAqiS_map.put("daZaY", "w");
		naAqiS_map.put("dasaY", "A");
		naAqiS_map.put("dalaY", "w");
		naAqiS_map.put("damaY", "y");
		naAqiS_map.put("danaY", "w");
		naAqiS_map.put("dahaY", "w");
		naAqiS_map.put("daEaY", "w");
		naAqiS_map.put("dagaY", "w");
		naAqiS_map.put("da$aY", "w");
		naAqiS_map.put("dayaY", "A");
		naAqiS_map.put("dawaY", "y");
		naAqiS_map.put("dafaY", "A");
		naAqiS_map.put("daqaY", "w");
		naAqiS_map.put("dakaY", "y");
		naAqiS_map.put("*aOaY", "w");
		naAqiS_map.put("*abaY", "w");
		naAqiS_map.put("*ataY", "w");
		naAqiS_map.put("*avaY", "w");
		naAqiS_map.put("*ajaY", "w");
		naAqiS_map.put("*aHaY", "w");
		naAqiS_map.put("*axaY", "y");
		naAqiS_map.put("*a*aY", "w");
		naAqiS_map.put("*araY", "y");
		naAqiS_map.put("*azaY", "w");
		naAqiS_map.put("*aTaY", "w");
		naAqiS_map.put("*aSaY", "w");
		naAqiS_map.put("*a*aY", "w");
		naAqiS_map.put("*aZaY", "w");
		naAqiS_map.put("*asaY", "A");
		naAqiS_map.put("*alaY", "w");
		naAqiS_map.put("*amaY", "y");
		naAqiS_map.put("*anaY", "w");
		naAqiS_map.put("*ahaY", "w");
		naAqiS_map.put("*aEaY", "w");
		naAqiS_map.put("*agaY", "w");
		naAqiS_map.put("*a$aY", "w");
		naAqiS_map.put("*ayaY", "A");
		naAqiS_map.put("*awaY", "y");
		naAqiS_map.put("*afaY", "A");
		naAqiS_map.put("*aqaY", "w");
		naAqiS_map.put("*akaY", "y");
		naAqiS_map.put("raOaY", "A");
		naAqiS_map.put("rabaY", "w");
		naAqiS_map.put("rataY", "y");
		naAqiS_map.put("ravaY", "w");
		naAqiS_map.put("rajaY","w");
		naAqiS_map.put("raHaY", "y");
		naAqiS_map.put("raxaY", "y");
		naAqiS_map.put("radaY", "y");
		naAqiS_map.put("ra*aY", "y");
		naAqiS_map.put("razaY", "y");
		naAqiS_map.put("raTaY", "w");
		naAqiS_map.put("raSaY", "A");
		naAqiS_map.put("raDaY", "y");
		naAqiS_map.put("raZaY", "y");
		naAqiS_map.put("rasaY", "w");
		naAqiS_map.put("ralaY", "w");
		naAqiS_map.put("ramaY", "y");
		naAqiS_map.put("ranaY", "w");
		naAqiS_map.put("rahaY", "w");
		naAqiS_map.put("raEaY", "A");
		naAqiS_map.put("ragaY", "w");
		naAqiS_map.put("ra$aY", "w");
		naAqiS_map.put("rayaY", "A");
		naAqiS_map.put("rawaY", "y");
		naAqiS_map.put("rafaY", "w");
		naAqiS_map.put("raqaY", "y");
		naAqiS_map.put("rakaY", "y");
		naAqiS_map.put("zaOaY", "w");
		naAqiS_map.put("zabaY", "y");
		naAqiS_map.put("zataY", "w");
		naAqiS_map.put("zavaY", "w");
		naAqiS_map.put("zajaY", "y");
		naAqiS_map.put("zaHaY", "w");
		naAqiS_map.put("zaxaY", "w");
		naAqiS_map.put("zadaY", "w");
		naAqiS_map.put("za*aY", "w");
		naAqiS_map.put("zaraY", "y");
		naAqiS_map.put("zaTaY", "w");
		naAqiS_map.put("zaSaY", "y");
		naAqiS_map.put("zaDaY", "w");
		naAqiS_map.put("zaZaY", "w");
		naAqiS_map.put("zasaY", "w");
		naAqiS_map.put("zalaY", "w");
		naAqiS_map.put("zamaY", "y");
		naAqiS_map.put("zanaY", "y");
		naAqiS_map.put("zahaY", "w");
		naAqiS_map.put("zaEaY", "w");
		naAqiS_map.put("zagaY", "w");
		naAqiS_map.put("za$aY", "y");
		naAqiS_map.put("zayaY", "A");
		naAqiS_map.put("zawaY", "y");
		naAqiS_map.put("zafaY", "w");
		naAqiS_map.put("zaqaY", "w");
		naAqiS_map.put("zakaY", "w");
		naAqiS_map.put("TaOaY", "w");
		naAqiS_map.put("TabaY", "w");
		naAqiS_map.put("TataY", "w");
		naAqiS_map.put("TavaY", "w");
		naAqiS_map.put("TajaY", "A");
		naAqiS_map.put("TaHaY", "w");
		naAqiS_map.put("TaxaY", "w");
		naAqiS_map.put("TadaY", "w");
		naAqiS_map.put("Ta*aY", "w");
		naAqiS_map.put("TaraY", "w");
		naAqiS_map.put("TazaY", "A");
		naAqiS_map.put("TaSaY", "w");
		naAqiS_map.put("TaDaY", "w");
		naAqiS_map.put("TaZaY", "w");
		naAqiS_map.put("TasaY", "w");
		naAqiS_map.put("TalaY", "y");
		naAqiS_map.put("TamaY", "y");
		naAqiS_map.put("TanaY", "w");
		naAqiS_map.put("TahaY", "w");
		naAqiS_map.put("TaEaY", "A");
		naAqiS_map.put("TagaY", "A");
		naAqiS_map.put("Ta$aY", "A");
		naAqiS_map.put("TayaY", "A");
		naAqiS_map.put("TawaY", "y");
		naAqiS_map.put("TafaY", "w");
		naAqiS_map.put("TaqaY", "w");
		naAqiS_map.put("TakaY", "A");
		naAqiS_map.put("SaOaY", "A");
		naAqiS_map.put("SabaY", "w");
		naAqiS_map.put("SataY", "w");
		naAqiS_map.put("SavaY", "w");
		naAqiS_map.put("SajaY", "w");
		naAqiS_map.put("SaHaY", "w");
		naAqiS_map.put("SaxaY", "A");
		naAqiS_map.put("SadaY", "A");
		naAqiS_map.put("Sa*aY", "A");
		naAqiS_map.put("SaraY", "w");
		naAqiS_map.put("SazaY", "A");
		naAqiS_map.put("SaTaY", "w");
		naAqiS_map.put("SaDaY", "w");
		naAqiS_map.put("SaZaY", "w");
		naAqiS_map.put("SasaY", "w");
		naAqiS_map.put("SalaY", "y");
		naAqiS_map.put("SamaY", "w");
		naAqiS_map.put("SanaY", "w");
		naAqiS_map.put("SahaY", "w");
		naAqiS_map.put("SaEaY", "w");
		naAqiS_map.put("SagaY", "A");
		naAqiS_map.put("Sa$aY", "w");
		naAqiS_map.put("SayaY", "A");
		naAqiS_map.put("SawaY", "y");
		naAqiS_map.put("SafaY", "A");
		naAqiS_map.put("SaqaY", "y");
		naAqiS_map.put("SakaY", "w");
		naAqiS_map.put("DaOaY", "w");
		naAqiS_map.put("DabaY", "w");
		naAqiS_map.put("DataY", "A");
		naAqiS_map.put("DavaY", "A");
		naAqiS_map.put("DajaY", "w");
		naAqiS_map.put("DaHaY", "w");
		naAqiS_map.put("DaxaY", "w");
		naAqiS_map.put("DadaY", "w");
		naAqiS_map.put("Da*aY", "w");
		naAqiS_map.put("DaraY", "w");
		naAqiS_map.put("DazaY", "A");
		naAqiS_map.put("DaTaY", "w");
		naAqiS_map.put("DaSaY", "w");
		naAqiS_map.put("DasaY", "w");
		naAqiS_map.put("DalaY", "w");
		naAqiS_map.put("DamaY", "A");
		naAqiS_map.put("DanaY", "w");
		naAqiS_map.put("DahaY", "y");
		naAqiS_map.put("DaEaY", "w");
		naAqiS_map.put("DagaY", "w");
		naAqiS_map.put("Da$aY", "A");
		naAqiS_map.put("DayaY", "A");
		naAqiS_map.put("DawaY", "w");
		naAqiS_map.put("DafaY", "A");
		naAqiS_map.put("DaqaY", "A");
		naAqiS_map.put("DakaY", "A");
		naAqiS_map.put("ZaOaY", "w");
		naAqiS_map.put("ZabaY", "w");
		naAqiS_map.put("ZataY", "A");
		naAqiS_map.put("ZavaY", "A");
		naAqiS_map.put("ZajaY", "w");
		naAqiS_map.put("ZaHaY", "w");
		naAqiS_map.put("ZaxaY", "w");
		naAqiS_map.put("ZaZaY", "w");
		naAqiS_map.put("Za*aY", "w");
		naAqiS_map.put("ZaraY", "w");
		naAqiS_map.put("ZazaY", "A");
		naAqiS_map.put("ZaTaY", "w");
		naAqiS_map.put("ZaSaY", "w");
		naAqiS_map.put("ZasaY", "w");
		naAqiS_map.put("ZalaY", "w");
		naAqiS_map.put("ZamaY", "A");
		naAqiS_map.put("ZanaY", "w");
		naAqiS_map.put("ZahaY", "y");
		naAqiS_map.put("ZaEaY", "w");
		naAqiS_map.put("ZagaY", "w");
		naAqiS_map.put("Za$aY", "A");
		naAqiS_map.put("ZayaY", "A");
		naAqiS_map.put("ZawaY", "w");
		naAqiS_map.put("ZafaY", "A");
		naAqiS_map.put("ZaqaY", "A");
		naAqiS_map.put("ZakaY", "A");
		naAqiS_map.put("saOaY", "w");
		naAqiS_map.put("sabaY", "y");
		naAqiS_map.put("sataY", "y");
		naAqiS_map.put("savaY", "y");
		naAqiS_map.put("sajaY", "A");
		naAqiS_map.put("saHaY", "w");
		naAqiS_map.put("saxaY", "w");
		naAqiS_map.put("sadaY", "w");
		naAqiS_map.put("sa*aY", "w");
		naAqiS_map.put("saraY", "y");
		naAqiS_map.put("sazaY", "w");
		naAqiS_map.put("saTaY", "w");
		naAqiS_map.put("saDaY", "A");
		naAqiS_map.put("saZaY", "A");
		naAqiS_map.put("saSaY", "w");
		naAqiS_map.put("salaY", "w");
		naAqiS_map.put("samaY", "w");
		naAqiS_map.put("sanaY", "w");
		naAqiS_map.put("sahaY", "w");
		naAqiS_map.put("saEaY", "A");
		naAqiS_map.put("sagaY", "A");
		naAqiS_map.put("sa$aY", "w");
		naAqiS_map.put("sayaY", "A");
		naAqiS_map.put("sawaY", "A");
		naAqiS_map.put("safaY", "A");
		naAqiS_map.put("saqaY", "y");
		naAqiS_map.put("sakaY", "w");
		naAqiS_map.put("laOaY", "A");
		naAqiS_map.put("labaY", "A");
		naAqiS_map.put("lataY", "w");
		naAqiS_map.put("lavaY", "w");
		naAqiS_map.put("lajaY", "w");
		naAqiS_map.put("laHaY", "w");
		naAqiS_map.put("laxaY", "A");
		naAqiS_map.put("ladaY", "A");
		naAqiS_map.put("la*aY", "A");
		naAqiS_map.put("laraY", "A");
		naAqiS_map.put("lazaY", "A");
		naAqiS_map.put("laTaY", "A");
		naAqiS_map.put("laSaY", "w");
		naAqiS_map.put("laDaY", "A");
		naAqiS_map.put("laZaY", "y");
		naAqiS_map.put("lasaY", "y");
		naAqiS_map.put("lamaY", "w");
		naAqiS_map.put("lanaY", "A");
		naAqiS_map.put("lahaY", "w");
		naAqiS_map.put("laEaY", "w");
		naAqiS_map.put("lagaY", "w");
		naAqiS_map.put("la$aY", "A");
		naAqiS_map.put("layaY", "A");
		naAqiS_map.put("lawaY", "y");
		naAqiS_map.put("lafaY", "A");
		naAqiS_map.put("laqaY", "A");
		naAqiS_map.put("lakaY", "w");
		naAqiS_map.put("maOaY", "A");
		naAqiS_map.put("mabaY", "w");
		naAqiS_map.put("mataY", "w");
		naAqiS_map.put("mavaY", "w");
		naAqiS_map.put("majaY", "w");
		naAqiS_map.put("maHaY", "w");
		naAqiS_map.put("maxaY", "y");
		naAqiS_map.put("madaY", "A");
		naAqiS_map.put("ma*aY", "A");
		naAqiS_map.put("maraY", "y");
		naAqiS_map.put("mazaY", "y");
		naAqiS_map.put("maTaY", "A");
		naAqiS_map.put("maSaY", "y");
		naAqiS_map.put("maDaY", "y");
		naAqiS_map.put("maZaY", "y");
		naAqiS_map.put("masaY", "y");
		naAqiS_map.put("malaY", "y");
		naAqiS_map.put("manaY", "y");
		naAqiS_map.put("mahaY", "y");
		naAqiS_map.put("maEaY", "w");
		naAqiS_map.put("magaY", "y");
		naAqiS_map.put("ma$aY", "y");
		naAqiS_map.put("mayaY", "A");
		naAqiS_map.put("mawaY", "y");
		naAqiS_map.put("mafaY", "A");
		naAqiS_map.put("maqaY", "y");
		naAqiS_map.put("makaY", "A");
		naAqiS_map.put("naOaY", "w");
		naAqiS_map.put("nabaY", "w");
		naAqiS_map.put("nataY", "w");
		naAqiS_map.put("navaY", "w");
		naAqiS_map.put("najaY", "w");
		naAqiS_map.put("naHaY", "w");
		naAqiS_map.put("naxaY", "w");
		naAqiS_map.put("nadaY", "w");
		naAqiS_map.put("na*aY", "w");
		naAqiS_map.put("naraY", "w");
		naAqiS_map.put("nazaY", "w");
		naAqiS_map.put("naTaY", "w");
		naAqiS_map.put("naSaY", "w");
		naAqiS_map.put("naDaY", "w");
		naAqiS_map.put("naZaY", "w");
		naAqiS_map.put("nasaY", "A");
		naAqiS_map.put("nalaY", "A");
		naAqiS_map.put("namaY", "w");
		naAqiS_map.put("nahaY", "A");
		naAqiS_map.put("naEaY", "A");
		naAqiS_map.put("nagaY", "A");
		naAqiS_map.put("na$aY", "w");
		naAqiS_map.put("nayaY", "A");
		naAqiS_map.put("nawaY", "y");
		naAqiS_map.put("nafaY", "y");
		naAqiS_map.put("naqaY", "A");
		naAqiS_map.put("nakaY", "A");
		naAqiS_map.put("haOaY", "A");
		naAqiS_map.put("habaY", "A");
		naAqiS_map.put("hataY", "w");
		naAqiS_map.put("havaY", "w");
		naAqiS_map.put("hajaY", "w");
		naAqiS_map.put("haHaY", "A");
		naAqiS_map.put("haxaY", "w");
		naAqiS_map.put("hadaY", "y");
		naAqiS_map.put("ha*aY", "y");
		naAqiS_map.put("haraY", "w");
		naAqiS_map.put("hazaY", "w");
		naAqiS_map.put("haTaY", "w");
		naAqiS_map.put("haSaY", "w");
		naAqiS_map.put("haDaY", "y");
		naAqiS_map.put("haZaY", "y");
		naAqiS_map.put("hasaY", "w");
		naAqiS_map.put("halaY", "w");
		naAqiS_map.put("hamaY", "w");
		naAqiS_map.put("hanaY", "w");
		naAqiS_map.put("haEaY", "A");
		naAqiS_map.put("hagaY", "w");
		naAqiS_map.put("ha$aY", "w");
		naAqiS_map.put("hayaY", "A");
		naAqiS_map.put("hawaY", "A");
		naAqiS_map.put("hafaY", "w");
		naAqiS_map.put("haqaY", "y");
		naAqiS_map.put("hakaY", "y");
		naAqiS_map.put("EaOaY", "w");
		naAqiS_map.put("EabaY", "w");
		naAqiS_map.put("EataY", "w");
		naAqiS_map.put("EavaY", "w");
		naAqiS_map.put("EajaY", "w");
		naAqiS_map.put("EaHaY", "A");
		naAqiS_map.put("EaxaY", "w");
		naAqiS_map.put("EadaY", "w");
		naAqiS_map.put("Ea*aY", "w");
		naAqiS_map.put("EaraY", "w");
		naAqiS_map.put("EazaY", "w");
		naAqiS_map.put("EaTaY", "y");
		naAqiS_map.put("EaSaY", "y");
		naAqiS_map.put("EaDaY", "y");
		naAqiS_map.put("EaZaY", "y");
		naAqiS_map.put("EasaY", "w");
		naAqiS_map.put("EalaY", "w");
		naAqiS_map.put("EamaY", "A");
		naAqiS_map.put("EanaY", "A");
		naAqiS_map.put("EahaY", "w");
		naAqiS_map.put("EagaY", "A");
		naAqiS_map.put("Ea$aY", "A");
		naAqiS_map.put("EayaY", "A");
		naAqiS_map.put("EawaY", "y");
		naAqiS_map.put("EafaY", "w");
		naAqiS_map.put("EaqaY", "w");
		naAqiS_map.put("EakaY", "w");
		naAqiS_map.put("gaOaY", "A");
		naAqiS_map.put("gabaY", "w");
		naAqiS_map.put("gataY", "w");
		naAqiS_map.put("gavaY", "w");
		naAqiS_map.put("gajaY", "w");
		naAqiS_map.put("gaHaY", "A");
		naAqiS_map.put("gaxaY", "w");
		naAqiS_map.put("gadaY", "w");
		naAqiS_map.put("ga*aY", "w");
		naAqiS_map.put("garaY", "w");
		naAqiS_map.put("gazaY", "w");
		naAqiS_map.put("gaTaY", "w");
		naAqiS_map.put("gaSaY", "A");
		naAqiS_map.put("gaDaY", "w");
		naAqiS_map.put("gaZaY", "w");
		naAqiS_map.put("gasaY", "w");
		naAqiS_map.put("galaY", "A");
		naAqiS_map.put("gamaY", "w");
		naAqiS_map.put("ganaY", "A");
		naAqiS_map.put("gahaY", "w");
		naAqiS_map.put("gaEaY", "A");
		naAqiS_map.put("ga$aY", "A");
		naAqiS_map.put("gayaY", "A");
		naAqiS_map.put("gawaY", "y");
		naAqiS_map.put("gafaY", "w");
		naAqiS_map.put("gaqaY", "A");
		naAqiS_map.put("gakaY", "w");
		naAqiS_map.put("$aOaY", "w");
		naAqiS_map.put("$abaY", "w");
		naAqiS_map.put("$ataY", "w");
		naAqiS_map.put("$avaY", "w");
		naAqiS_map.put("$ajaY", "w");
		naAqiS_map.put("$aHaY", "w");
		naAqiS_map.put("$axaY", "A");
		naAqiS_map.put("$adaY", "w");
		naAqiS_map.put("$a*aY", "w");
		naAqiS_map.put("$araY", "y");
		naAqiS_map.put("$azaY", "w");
		naAqiS_map.put("$aTaY", "w");
		naAqiS_map.put("$aSaY", "w");
		naAqiS_map.put("$aDaY", "w");
		naAqiS_map.put("$aZaY", "w");
		naAqiS_map.put("$asaY", "w");
		naAqiS_map.put("$alaY", "w");
		naAqiS_map.put("$amaY", "w");
		naAqiS_map.put("$anaY", "w");
		naAqiS_map.put("$ahaY", "A");
		naAqiS_map.put("$aEaY", "A");
		naAqiS_map.put("$agaY", "w");
		naAqiS_map.put("$ayaY", "A");
		naAqiS_map.put("$awaY", "y");
		naAqiS_map.put("$afaY", "A");
		naAqiS_map.put("$aqaY", "A");
		naAqiS_map.put("$akaY", "w");
		naAqiS_map.put("yaOaY", "A");
		naAqiS_map.put("yabaY", "A");
		naAqiS_map.put("yataY", "A");
		naAqiS_map.put("yavaY", "A");
		naAqiS_map.put("yajaY", "w");
		naAqiS_map.put("yaHaY", "A");
		naAqiS_map.put("yaxaY", "w");
		naAqiS_map.put("yadaY", "A");
		naAqiS_map.put("ya*aY", "A");
		naAqiS_map.put("yaraY", "A");
		naAqiS_map.put("yazaY", "A");
		naAqiS_map.put("yaTaY", "A");
		naAqiS_map.put("yaSaY", "A");
		naAqiS_map.put("yaDaY", "A");
		naAqiS_map.put("yaZaY", "A");
		naAqiS_map.put("yasaY", "A");
		naAqiS_map.put("yalaY", "A");
		naAqiS_map.put("yamaY", "A");
		naAqiS_map.put("yanaY", "A");
		naAqiS_map.put("yahaY", "A");
		naAqiS_map.put("yaEaY", "A");
		naAqiS_map.put("yagaY", "A");
		naAqiS_map.put("ya$aY", "A");
		naAqiS_map.put("yayaY", "A");
		naAqiS_map.put("yawaY", "A");
		naAqiS_map.put("yafaY", "A");
		naAqiS_map.put("yaqaY", "A");
		naAqiS_map.put("yakaY", "A");
		naAqiS_map.put("waOaY", "A");
		naAqiS_map.put("wabaY", "A");
		naAqiS_map.put("wataY", "A");
		naAqiS_map.put("wavaY", "A");
		naAqiS_map.put("wajaY", "w");
		naAqiS_map.put("waHaY", "A");
		naAqiS_map.put("waxaY", "A");
		naAqiS_map.put("wadaY", "y");
		naAqiS_map.put("wa*aY", "y");
		naAqiS_map.put("waraY", "y");
		naAqiS_map.put("wazaY", "y");
		naAqiS_map.put("waTaY", "A");
		naAqiS_map.put("waSaY", "A");
		naAqiS_map.put("waDaY", "y");
		naAqiS_map.put("waZaY", "y");
		naAqiS_map.put("wasaY", "y");
		naAqiS_map.put("walaY", "y");
		naAqiS_map.put("wamaY", "y");
		naAqiS_map.put("wanaY", "y");
		naAqiS_map.put("wahaY", "y");
		naAqiS_map.put("waEaY", "A");
		naAqiS_map.put("wagaY", "y");
		naAqiS_map.put("wa$aY", "y");
		naAqiS_map.put("wayaY", "y");
		naAqiS_map.put("wafaY", "y");
		naAqiS_map.put("waqaY", "y");
		naAqiS_map.put("wakaY", "y");
		naAqiS_map.put("faOaY", "w");
		naAqiS_map.put("fabaY", "w");
		naAqiS_map.put("fataY", "A");
		naAqiS_map.put("favaY", "A");
		naAqiS_map.put("fajaY", "w");
		naAqiS_map.put("faHaY", "w");
		naAqiS_map.put("faxaY", "w");
		naAqiS_map.put("fadaY", "y");
		naAqiS_map.put("fa*aY", "y");
		naAqiS_map.put("faraY", "y");
		naAqiS_map.put("fazaY", "w");
		naAqiS_map.put("faTaY", "w");
		naAqiS_map.put("faSaY", "y");
		naAqiS_map.put("faDaY", "w");
		naAqiS_map.put("faZaY", "w");
		naAqiS_map.put("fasaY", "w");
		naAqiS_map.put("falaY", "y");
		naAqiS_map.put("famaY", "w");
		naAqiS_map.put("fanaY", "A");
		naAqiS_map.put("fahaY", "w");
		naAqiS_map.put("faEaY", "w");
		naAqiS_map.put("fagaY", "w");
		naAqiS_map.put("fa$aY", "w");
		naAqiS_map.put("fayaY", "A");
		naAqiS_map.put("fawaY", "y");
		naAqiS_map.put("faqaY", "w");
		naAqiS_map.put("fakaY", "w");
		naAqiS_map.put("qaOaY", "A");
		naAqiS_map.put("qabaY", "w");
		naAqiS_map.put("qataY", "w");
		naAqiS_map.put("qavaY", "w");
		naAqiS_map.put("qajaY", "w");
		naAqiS_map.put("qaHaY", "w");
		naAqiS_map.put("qaxaY", "w");
		naAqiS_map.put("qadaY", "w");
		naAqiS_map.put("qa*aY", "w");
		naAqiS_map.put("qaraY", "w");
		naAqiS_map.put("qazaY", "Y");
		naAqiS_map.put("qaTaY", "w");
		naAqiS_map.put("qaSaY", "w");
		naAqiS_map.put("qaDaY", "y");
		naAqiS_map.put("qaZaY", "y");
		naAqiS_map.put("qasaY", "w");
		naAqiS_map.put("qalaY", "y");
		naAqiS_map.put("qamaY", "y");
		naAqiS_map.put("qanaY", "y");
		naAqiS_map.put("qahaY", "w");
		naAqiS_map.put("qaEaY", "w");
		naAqiS_map.put("qagaY", "w");
		naAqiS_map.put("qa$aY", "w");
		naAqiS_map.put("qayaY", "A");
		naAqiS_map.put("qawaY", "A");
		naAqiS_map.put("qafaY", "w");
		naAqiS_map.put("qakaY", "A");
		naAqiS_map.put("kaOaY", "w");
		naAqiS_map.put("kabaY", "w");
		naAqiS_map.put("kataY", "w");
		naAqiS_map.put("kavaY", "w");
		naAqiS_map.put("kajaY", "w");
		naAqiS_map.put("kaHaY", "w");
		naAqiS_map.put("kaxaY", "w");
		naAqiS_map.put("kadaY", "w");
		naAqiS_map.put("ka*aY", "w");
		naAqiS_map.put("karaY", "y");
		naAqiS_map.put("kazaY", "w");
		naAqiS_map.put("kaTaY", "A");
		naAqiS_map.put("kaSaY", "y");
		naAqiS_map.put("kaDaY", "A");
		naAqiS_map.put("kaZaY", "A");
		naAqiS_map.put("kasaY", "y");
		naAqiS_map.put("kalaY", "A");
		naAqiS_map.put("kamaY", "w");
		naAqiS_map.put("kanaY", "A");
		naAqiS_map.put("kahaY", "w");
		naAqiS_map.put("kaEaY", "A");
		naAqiS_map.put("kagaY", "A");
		naAqiS_map.put("ka$aY", "w");
		naAqiS_map.put("kayaY", "A");
		naAqiS_map.put("kawaY", "y");
		naAqiS_map.put("kafaY", "y");
		naAqiS_map.put("kaqaY", "A");
}
}