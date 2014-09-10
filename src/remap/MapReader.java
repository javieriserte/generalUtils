package remap;

import io.onelinelister.OneLineListReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapReader {

	/**
	 * Reads an input file a creates a Map.
	 * 
	 * The input file must has two columns separated by a tab char.
	 * The first column has the key values and the second the mapped values 
	 * 
	 * @param mapfile
	 * @return
	 */
	public Map<String, String> createMap(File mapfile, boolean inverse) {
		
		Map<String,String> map = new HashMap<String, String>();
		
		List<String> tmpMapRep = OneLineListReader.createOneLineListReaderForString().read(mapfile);

		for (String string : tmpMapRep) {
			
			String[] data = string.split("\t");
			
			int index_domain = (inverse)?1:0;
			   // If reading an inverse map, then
			   // the first element read is the 
			   // codomain, and the second is the 
			   // domain.

			int index_codomain = 1 - index_domain;

			map.put(data[index_domain].trim(), data[index_codomain].trim());
			
		}
		
		return map;
	}
	
}
