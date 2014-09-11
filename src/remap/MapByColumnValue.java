package remap;

import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pair.Pair;
import cmdGA2.returnvalues.ReturnValueParser;

public class MapByColumnValue extends ReturnValueParser<Pair<Integer,Map<String,String>>> {

	private static final String FORMAT_REGEX= "^(?<index>[0-9]+)(?<inverse>i*):(?<fileName>.+)$";
	@Override
	public Pair<Integer, Map<String, String>> parse(String token) {
		
		Pattern p = Pattern.compile(FORMAT_REGEX);
		
		Matcher m = p.matcher(token.trim());
		
		m.matches();
		
		Integer index = Integer.valueOf(m.group("index"))-1;
		
		String inv = m.group("inverse");
		Boolean inverse=(inv.equals("i"));
		
		File file = new File(m.group("fileName"));
		
		Map<String,String> map = (new MapReader()).createMap(file, inverse);
		
		return new Pair<Integer,Map<String,String>>(index,map);
		
	}

}
