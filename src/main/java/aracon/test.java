package aracon;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import aracon.Conjugator.Mode;

public class test {
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {

		Conjugator conj = new Conjugator("OlY");

		conj.SelectConjModel();
		// System.out.println(conj.getPattern());
		conj.buildSimpleTenses(Mode.ACTIVE);

		conj.buildComplexTenses();

		Map<String, String> map = conj.get_muDaAriE1();

		List aux = new LinkedList(map.entrySet());
		Concode conc = null;
		Map.Entry entry = null;
		for (int l = 0; l < aux.size(); l++) {

			entry = (Map.Entry) aux.get(l);

			conc = new Concode(entry.getValue().toString());

			System.out.println(entry.getKey() + " ==> " + conc.buck12Arabic());

		}
	}
}