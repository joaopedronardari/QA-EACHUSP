package x;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class WSTTtest extends TestCase {

	private WSTT wstt;

	@Before
	public void setUp() {
		wstt = new WSTT();
	}

	@Test
	public void testCalculaTarifaEmPeriodoBruto(){ // Caso 1
		Float tarifa = wstt.calculaTarifa(4,11,2015,15,0,0,4,11,2015,15,20,0);
		assertEquals(8F, tarifa);
	}
	
	@Test
	public void testCalculaTarifaEmPeriodoDesconto(){ // Caso 2
		Float tarifa = wstt.calculaTarifa(4,11,2015,18,0,0,4,11,2015,18,20,0);
		assertEquals(8F, tarifa);
	}
	
	@Test
	public void testCalculaTarifaEmPeriodoBrutoAcima60Minutos(){ // Caso 3
		Float tarifa = wstt.calculaTarifa(4,11,2015,15,0,0,4,11,2015,16,0,1);
		assertEquals(20.74F, tarifa);
	}
	
	@Test
	public void testCalculaTarifaComecaComDescontoTerminaSemDesconto(){ // Caso 4
		Float tarifa = wstt.calculaTarifa(4,11,2015,7,40,0,4,11,2015,8,10,0);
		assertEquals(8F, tarifa);
	}
	
	@Test
	public void testCalculaTarifaComecaSemDescontoTerminaComDesconto(){ // Caso 5
		Float tarifa = wstt.calculaTarifa(4,11,2015,17,40,0,4,11,2015,18,10,0);
		assertEquals(10F, tarifa);
	}
	
	@Test
	public void testCalculaTarifaEmPeriodoDescontoAcima60Minutos(){ // Caso 6
		Float tarifa = wstt.calculaTarifa(10, 1, 2016, 18, 0, 0,10, 1, 2016, 20, 30, 0);
		assertEquals(25.5F, tarifa);
	}
	
	@Test
	public void testCalculaTarifaEmComESemDescontoAcima60Minutos(){ // Caso 7
		Float tarifa = wstt.calculaTarifa(10, 1, 2016, 17, 30, 0, 10, 1, 2016, 19, 30, 0);
		assertEquals(25.5F, tarifa);
	}
	
	@Test
	public void testCalculaComAnoInicialBissexto(){ // Caso 8
		Float tarifa = wstt.calculaTarifa(29,02,2016,15,0,0,29,02,2016,15,20,0);
		assertEquals(8F, tarifa);
	}
	
	@Test
	public void testCalculaComAnoFinalBissexto(){ // Caso 9
		Float tarifa = wstt.calculaTarifa(28,02,2016,23,50,0,29,02,2016,0,10,0);
		assertEquals(4F, tarifa);
	}
	
	@Test
	public void testCalculaComAnoInicialBissextoInvalido(){ // Caso 10
		Float tarifa = wstt.calculaTarifa(29,02,2015,15,0,0,29,02,2015,15,20,0);
		assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testCalculaComAnoFinalBissextoInvalido(){ // Caso 11
		Float tarifa = wstt.calculaTarifa(28,02,2015,23,50,0,29,02,2015,0,10,0);
		assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testCalculaDiaInicialZero(){ // Caso 12
		  Float tarifa = wstt.calculaTarifa(0,2,2015,0,0,0,1,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testCalculaMesInicialZero(){ // Caso 13
		  Float tarifa = wstt.calculaTarifa(1,0,2015,0,0,0,1,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testCalculaAnoInicialZero(){ // Caso 14
		  Float tarifa = wstt.calculaTarifa(1,2,0,0,0,0,1,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testCalculaHoraInicialNegativa(){ // Caso 15
		  Float tarifa = wstt.calculaTarifa(1,2,2015,-1,0,0,1,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testCalculaMinutoInicialNegativa(){ // Caso 16
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,-1,0,1,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testCalculaSegundoInicialNegativa(){ // Caso 17
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,-1,1,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testCalculaDiaFinalZero(){ // Caso 18
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,0,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testCalculaMesFinalZero(){ // Caso 19
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,0,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testCalculaAnoFinalZero(){ // Caso 20
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,2,0,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testCalculaHoraFinalNegativa(){ // Caso 21
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,2,2015,-1,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testCalculaMinutoFinalNegativa(){ // Caso 22
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,2,2015,0,-1,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testCalculaSegundoFinalNegativa(){ // Caso 23
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,2,2015,0,0,-1);
		  assertEquals(-1F, tarifa);
	}
	
	
	
	
	
	// VITOR TESTES
	@Test
	public void testCalculaDuracaoLigacao(){
		Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 1, 2016, 10, 0, 0, 
				10, 1, 2016, 12, 30, 0);
		assertEquals(150F, duracaoLigacao);
	}
	
	
	
	@Test
	public void testCalculaDataMaiorQue31(){
		Float duracaoLigacao = wstt.calculaDuracaoLigacao(32, 1, 2016, 17, 30, 0, 
				32, 1, 2016, 19, 30, 0);
		assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaDataNegativa(){
		Float duracaoLigacao = wstt.calculaDuracaoLigacao(-1, 1, 2016, 17, 30, 0, 
				-1, 1, 2016, 19, 30, 0);
		assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaMesZero(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 0, 2016, 10, 0, 0, 
						10, 0, 2016, 12, 30, 0);

		  assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaMesMaiorQue12(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 20, 2016, 10, 0, 0, 
					10, 10, 2016, 12, 30, 0);
		assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaMesNegativo(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, -3, 2016, 10, 0, 0, 
					10, -1, 2016, 12, 30, 0);
		assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaAnosNegativo(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, -2016, 10, 0, 0, 
					10, 1, 2016, 12, 30, 0);
		assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaAnosZero(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 0, 10, 0, 0, 
					10, 2, 2016, 12, 30, 0);
		assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaHoraNegativo(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 2016, -10, 0, 0, 
					10, 1, 2016, 12, 30, 0);
		assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaHoraAcimade24(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 0, 26, 0, 0, 
					17, 2, 2016, 12, 30, 0);
		assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaMinutoNegativo(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 2016, 10, -3, 0, 
					10, 1, 2016, 12, 30, 0);
		assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaMinutoAcimade59(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 0, 10, 60, 0, 
					10, 2, 2016, 12, 30, 0);
		assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaSegundoNegativo(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 2016, 10, 0, -6, 
					10, 1, 2016, 12, 30, 0);
		assertEquals(-1F, duracaoLigacao);
	}
	
	@Test
	public void testCalculaSegundoAcimade59(){
		  Float duracaoLigacao = wstt.calculaDuracaoLigacao(10, 3, 0, 10, 0, 0, 
					10, 2, 2016, 12, 30, 80);
		assertEquals(-1F, duracaoLigacao);
	}

}
