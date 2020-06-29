package br.com.caelum.leilao.testesgerais;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.mockito.Answers;

class Bar{
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String encrypt(String text) throws NoSuchAlgorithmException {
		//simula criptografia, mas na verdade apenas retorna o MD5
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(text.getBytes(), 0, text.length());
		return new BigInteger(1,m.digest()).toString(16);
	}
}

class Foo{
	
	Bar bar;

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}
	
}

public class TestesGerais {
	
	@Test
	public void mockComChamadasDeMetodosEncadeadas() {
		Foo mock = mock(Foo.class, Answers.RETURNS_DEEP_STUBS);

		// note that we're stubbing a chain of methods here: getBar().getName()
		when(mock.getBar().getName()).thenReturn("deep");

		// note that we're chaining method calls: getBar().getName()
		assertEquals("deep", mock.getBar().getName());
	}
	
	@Test
	public void mockQueRetornaMuitosValores() {
		
		Bar bar = mock(Bar.class);
		
		when(bar.getName()).thenReturn("Bruno", "Diciane", "Enzo", "Iris");
		
		assertEquals("Bruno", bar.getName());
		assertEquals("Diciane", bar.getName());
		assertEquals("Enzo", bar.getName());
		assertEquals("Iris", bar.getName());
		
	}
	
	@Test
	public void comoConfigurarOMockParaReceberQualquerString() throws NoSuchAlgorithmException {
		
//		System.out.println(new Bar().encrypt("Bruno"));
//		System.out.println(new Bar().encrypt("Diciane"));
//		System.out.println(new Bar().encrypt("Enzo"));
//		System.out.println(new Bar().encrypt("Iris"));
		
		//Teste mockado
		
		Bar bar = mock(Bar.class);		
		
		when(bar.encrypt(anyString())). //O mock eh configurado para receber qualquer String
			thenReturn("9b2b78033ecf0401a2feab5b4ba7462e", "c105c051a5bdda3c086702538ee3e535", 
				"bca236048835bcf1f95e761e2e442393", "d25c7286a46dbb6eeecc5cfddacd5818");
		
		assertEquals("9b2b78033ecf0401a2feab5b4ba7462e", bar.encrypt("Bruno"));
		assertEquals("c105c051a5bdda3c086702538ee3e535", bar.encrypt("Diciane"));
		assertEquals("bca236048835bcf1f95e761e2e442393", bar.encrypt("Enzo"));
		assertEquals("d25c7286a46dbb6eeecc5cfddacd5818", bar.encrypt("Iris"));
		
		//Teste chamando o metodo de verdade
		
		Bar bar2 = new Bar();
		
		assertEquals("9b2b78033ecf0401a2feab5b4ba7462e", bar2.encrypt("Bruno"));
		assertEquals("c105c051a5bdda3c086702538ee3e535", bar2.encrypt("Diciane"));
		assertEquals("bca236048835bcf1f95e761e2e442393", bar2.encrypt("Enzo"));
		assertEquals("d25c7286a46dbb6eeecc5cfddacd5818", bar2.encrypt("Iris"));
	}

}
