package remap;

import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pair.Pair;
import cmdGA2.returnvalues.ReturnValueParser;

public class MapByColumnValue extends ReturnValueParser<Pair<Integer, Pair<String, Map<String, String>>>> {

	private static final String FORMAT_REGEX= "^(?<index>[0-9]+)(?<inverse>i*)(?<default>:.+)*:(?<fileName>.+)$";
	@Override
	public Pair<Integer, Pair<String, Map<String, String>>> parse(String token) {
		
		Pattern p = Pattern.compile(FORMAT_REGEX);
		
		Matcher m = p.matcher(token.trim());
		
		m.matches();
		
		Integer index = Integer.valueOf(m.group("index"))-1;
		
		String inv = m.group("inverse");
		Boolean inverse=(inv.equals("i"));
		
		String def = m.group("default");
		if (!(def==null) && !def.isEmpty()) {
		  def = def.substring(1);
		} else {
		  def = "-";
		}
		
		File file = new File(m.group("fileName"));
		
		Map<String,String> map = (new MapReader()).createMap(file, inverse);
		
		return new Pair<Integer,Pair<String,Map<String,String>>>(index,new Pair<String,Map<String,String>>(def, map));
//		
	}

}
