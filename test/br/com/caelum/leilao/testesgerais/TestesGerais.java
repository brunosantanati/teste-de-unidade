package br.com.caelum.leilao.testesgerais;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

}
