package x;

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
	
	@Test
	public void testCalculaDataZero(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(0, 1, 2016, 10, 0, 0, 
						0, 1, 2016, 12, 30, 0);

		  Assert.assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaDataMaiorQue31(){
		Float duracaoLigacao = wstt.calculaDuracaoLigacao(32, 1, 2016, 17, 30, 0, 
				32, 1, 2016, 19, 30, 0);
		Assert.assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaDataNegativa(){
		Float duracaoLigacao = wstt.calculaDuracaoLigacao(-1, 1, 2016, 17, 30, 0, 
				-1, 1, 2016, 19, 30, 0);
		Assert.assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaMesZero(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 0, 2016, 10, 0, 0, 
						10, 0, 2016, 12, 30, 0);

		  Assert.assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaMesMaiorQue12(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 20, 2016, 10, 0, 0, 
					10, 10, 2016, 12, 30, 0);
		Assert.assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaMesNegativo(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, -3, 2016, 10, 0, 0, 
					10, -1, 2016, 12, 30, 0);
		Assert.assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaAnosNegativo(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, -2016, 10, 0, 0, 
					10, 1, 2016, 12, 30, 0);
		Assert.assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaAnosZero(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 0, 10, 0, 0, 
					10, 2, 2016, 12, 30, 0);
		Assert.assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaHoraNegativo(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 2016, -10, 0, 0, 
					10, 1, 2016, 12, 30, 0);
		Assert.assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaHoraAcimade24(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 0, 26, 0, 0, 
					17, 2, 2016, 12, 30, 0);
		Assert.assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaMinutoNegativo(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 2016, 10, -3, 0, 
					10, 1, 2016, 12, 30, 0);
		Assert.assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaMinutoAcimade59(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 0, 10, 60, 0, 
					10, 2, 2016, 12, 30, 0);
		Assert.assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaSegundoNegativo(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 2016, 10, 0, -6, 
					10, 1, 2016, 12, 30, 0);
		Assert.assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaSegundoAcimade59(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 0, 10, 0, 0, 
					10, 2, 2016, 12, 30, 80);
		Assert.assertEquals(-1F, duracaoLigacao);
	}

}
