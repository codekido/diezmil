package tdd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DadoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRolaUnDado() {
		Dado dado = new Dado();
		assertTrue("El dado debería valer entre 1 y 6.", dado.rolar() > 0 && dado.rolar() < 7);
	}

}
