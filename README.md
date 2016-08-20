# Aracon
Overview:
ARACON is a verb conjugator for Arabic implemented as part of a morphological Analyser and generator. It is a java API implementing a root-pattern based algorithm for analysing and generating all possible forms of Arabic verbs.
The program includes 3 classes:

1. MorphPatternAnalyser.class:
Identifies root and pattern of the input verb using a decision tree and regular expressions. It can process either Arabic characters or Buckwalter transliteration. It guesses the form of fully undiacritized verbs or partially diacritized verbs. The class can be called as follow:

    The constructor:

    MorphPatternAnalyser ma = new MorphPatternAnalyser("VERB");
    
    Instantiate variables:
    
    ma.get_pattern();
    
    Print Output:
    
    System.out.println(ma.get_wazn());
    
    System.out.println(mpa.get_root());
    ...
    Note that this class takes only Buckwalter transliteration as input.

2. Conjugator.class:
Maps verb to the appropriate conjugation model and return for each tense a map with the pronoun (and other information) as key and the conjugated verb as value. This class can be called as follow:
    The constructor:
    Conjugator conj = new Conjugator("فعل");

    Select the appropriate conjugation model:

    conj.SelectConjModel();

    Build simple tenses maps

    conj.buildSimpleTenses("Active");

    (This method take one string argument: "Active" or "Passive")
    Build complex tenses maps

    conj.buildComplexTenses();

    Note that the you cannot build complex tenses before having called the method buildSimpleTenses().
    This class take Arabic character as well as Buckwalter transliteration as input.
    
3. Concode.class:

Is a character converter from Arabic to Buckwalter and vice versa.

    The constructor:

    Concode conc = new Conconde("INPUT");

    Convert characters:

    conc.Buckwalter2Arabic();

    conc.Arabic2Buckwalter();

Sample Code:
===========
public static void main(String[] args) throws ParserConfigurationException,SAXException, IOException {

      Concode conc = null;  
      
      Map.Entry entry = null;
      
      Conjugator conj = new Conjugator("تعامل");
      
      conj.SelectConjModel();
      
      conj.buildSimpleTenses("Active"); 
      
      conj.buildComplexTenses();
      
      Map map = conj.get_past_continuous();
      
      List aux = new LinkedList(map.entrySet());
      
           for (int i = 0; i < aux.size(); i++) {
           
           entry = (Map.Entry) aux.get(i);
           
           conc = new Concode(entry.getValue().toString());
           
           System.out.println(entry.getKey() + " ==> " + conc.buck12Arabic());
           }
      }
      
Output:

1.PRS-SG-MAS-PastContinuous-Active ==> 
كُنْتُ أَتَعَامَلُ
1.PRS-PL-PastContinuous-Active ==> 
كُنَّا نَتَعَامَلُ
2.PRS-SG-MAS-PastContinuous-Active ==> 
كُنْتَ تَتَعَامَلُ
2.PRS-SG-FEM-PastContinuous-Active ==> 
كُنْتِ تَتَعَامَلِينَ
1.PRS-DUAL-MAS-PastContinuous-Active ==> 
كُنْتُمَا تَتَعَامَلَانِ
2.DUAL-FEM-PastContinuous-Active ==> 
كُنْتُمَا تَتَعَامَلَانِ
2.PRS-PL-MAS-PastContinuous-Active ==> 
كُنْتُم تَتَعَامَلُونَ
2.PRS-PL-FEM-PastContinuous-Active ==> 
كُنْتُنَّ تَتَعَامَلْنَ
3.PRS-SG-MAS-PastContinuous-Active ==> 
كَانَ يَتَعَامَلُ
3.PRS-SG-FEM-PastContinuous-Active ==> 
كَانَتْ تَتَعَامَلُ
2.PRS-DUAL-MAS-PastContinuous-Active ==> 
كَانَا يَتَعَامَلَانِ
2.PRS-DUAL-FEM-PastContinuous-Active ==> 
كَانَتَا تَتَعَامَلَانِ
3.PRS-PL-MAS-PastContinuous-Active ==> 
كَانُوا يَتَعَامَلُونَ
3.PRS-PL-FEM-PastContinuous-Active ==> 
كُنَّ يَتَعَامَلْنَ
