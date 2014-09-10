package remap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class gets a orderer list of maps, where each map represents a single 
 * step in a multiple-map conversion, and returns a new map that goes
 * from the domain values of the first map to the codomain values of the last 
 * map.
 * @author javier
 *
 */
public class MapCombiner {

	/**
	 * Combine maps.
	 * The default value is used when 
	 * @param maps
	 * @return
	 */
	public Map<String,String> combineMaps (List<Map<String,String>> maps, String defaultValue) {
		
		if (maps.size()>0) {
		
			Set<String> domain = maps.get(0).keySet();
			
			LinkedHashMap<String,String> combinedMap = new LinkedHashMap<String, String>();

			String mappedValue=null;
			
			for (String key : domain) {
				
				mappedValue = this.mapTroughAllMaps(maps, key);
				
				if (mappedValue == null) {
					
					if (defaultValue!=null) {
						
						combinedMap.put(key, defaultValue);
						
					} 
					
				} else {
					
					combinedMap.put(key, mappedValue);
					
				}
				
			}
			
			return combinedMap;
				
		} else {
			
			return new HashMap<String, String>();
			
		}
		
	}

	private String mapTroughAllMaps(List<Map<String, String>> maps, String mappedValue) {
		
		for (int i = 0 ; i< maps.size() && mappedValue!=null; i++) {

			Map<String, String> currentMap = maps.get(i);
			
			if (currentMap.containsKey(mappedValue)) {
				
				mappedValue = currentMap.get(mappedValue);
				
			} else{
				
				mappedValue = null;
				
			}
			
		}
		return mappedValue;
	}
	
}
