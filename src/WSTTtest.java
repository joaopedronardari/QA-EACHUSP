import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class WSTTtest extends TestCase {

	private WSTT wstt;

	@Before
	public void setUp() {
		wstt = new WSTT();
	}

	@Test
	public void testCalculaTarifaEmPeriodoBruto(){
		Float tarifa = wstt.calculaTarifa(10, 1, 2016, 10, 0, 0, 
				10, 1, 2016, 10, 30, 0);
		Assert.assertEquals(12F, tarifa);
	}
	
	@Test
	public void testCalculaTarifaEmPeriodoBrutoAcima60Minutos(){
		Float tarifa = wstt.calculaTarifa(10, 1, 2016, 10, 0, 0, 
				10, 1, 2016, 12, 30, 0);
		Assert.assertEquals(51F, tarifa);
	}
	
	@Test
	public void testCalculaTarifaEmPeriodoDesconto(){
		Float tarifa = wstt.calculaTarifa(10, 1, 2016, 18, 0, 0, 
				10, 1, 2016, 18, 30, 0);
		Assert.assertEquals(6F, tarifa);
	}
	
	@Test
	public void testCalculaTarifaEmPeriodoDescontoAcima60Minutos(){
		Float tarifa = wstt.calculaTarifa(10, 1, 2016, 18, 0, 0, 
				10, 1, 2016, 20, 30, 0);
		Assert.assertEquals(25.5F, tarifa);
	}
	
	@Test
	public void testCalculaTarifaEmComESemDesconto(){
		Float tarifa = wstt.calculaTarifa(10, 1, 2016, 17, 50, 0, 
				10, 1, 2016, 18, 20, 0);
		Assert.assertEquals(8F, tarifa);
	}
	
	@Test
	public void testCalculaTarifaEmComESemDescontoAcima60Minutos(){
		Float tarifa = wstt.calculaTarifa(10, 1, 2016, 17, 30, 0, 
				10, 1, 2016, 19, 30, 0);
		Assert.assertEquals(25.5F, tarifa);
	}
	
	@Test
	public void testCalculaDuracaoLigacao(){
		Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 1, 2016, 10, 0, 0, 
				10, 1, 2016, 12, 30, 0);
		Assert.assertEquals(150F, duracaoLigacao);
	}

}
