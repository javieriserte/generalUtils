package remap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pair.Pair;

public class MapTable {

	List<List<String>> map (List<List<String>> data, List<Pair<Integer,Map<String,String>>> maps) {
		
		List<Integer> columnIndexes = new ArrayList<Integer>();
		
		for (Pair<Integer,Map<String,String>> pair : maps) {
			columnIndexes.add(pair.getFirst());
		}
		
		List<List<String>> result = new ArrayList<List<String>>();
		
		for (List<String> list : data) {
			
			int counter = 0;
			
			List<String> line = new ArrayList<String>();			

			for (String fieldValue : list) {
	
				if (columnIndexes.contains(counter)) {
					
					int index = columnIndexes.indexOf(counter);
					
					line.add(maps.get(index).getSecond().get(fieldValue));
					
				} else {
					
					line.add(fieldValue);
					
				}

				counter++;
				
			}
			
			result.add(line);
			
		}
		
		return result;
		
	}
	
}
