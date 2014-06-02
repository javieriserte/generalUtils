package io.resources;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResourceContentAsStringTest extends ResourceContentAsString {

	@Test
	public void testReadContents() {
		
		ResourceContentAsString r = new ResourceContentAsString ();
		
		String a = r.readContents("testresource");

		a = a.trim();
		
		assertEquals(a, "hello");
		
	}

}
