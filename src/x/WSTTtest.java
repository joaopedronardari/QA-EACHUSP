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
	public void testAnoInicialBissexto(){ // Caso 8
		Float tarifa = wstt.calculaTarifa(29,02,2016,15,0,0,29,02,2016,15,20,0);
		assertEquals(8F, tarifa);
	}
	
	@Test
	public void testAnoFinalBissexto(){ // Caso 9
		Float tarifa = wstt.calculaTarifa(28,02,2016,23,50,0,29,02,2016,0,10,0);
		assertEquals(4F, tarifa);
	}
	
	@Test
	public void testAnoInicialBissextoInvalido(){ // Caso 10
		Float tarifa = wstt.calculaTarifa(29,02,2015,15,0,0,29,02,2015,15,20,0);
		assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testAnoFinalBissextoInvalido(){ // Caso 11
		Float tarifa = wstt.calculaTarifa(28,02,2015,23,50,0,29,02,2015,0,10,0);
		assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testDiaInicialZero(){ // Caso 12
		  Float tarifa = wstt.calculaTarifa(0,2,2015,0,0,0,1,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testMesInicialZero(){ // Caso 13
		  Float tarifa = wstt.calculaTarifa(1,0,2015,0,0,0,1,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testAnoInicialZero(){ // Caso 14
		  Float tarifa = wstt.calculaTarifa(1,2,0,0,0,0,1,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testHoraInicialNegativa(){ // Caso 15
		  Float tarifa = wstt.calculaTarifa(1,2,2015,-1,0,0,1,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testMinutoInicialNegativo(){ // Caso 16
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,-1,0,1,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testSegundoInicialNegativo(){ // Caso 17
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,-1,1,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testDiaFinalZero(){ // Caso 18
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,0,2,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testMesFinalZero(){ // Caso 19
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,0,2015,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testAnoFinalZero(){ // Caso 20
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,2,0,0,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testHoraFinalNegativa(){ // Caso 21
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,2,2015,-1,0,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testMinutoFinalNegativa(){ // Caso 22
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,2,2015,0,-1,0);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testSegundoFinalNegativa(){ // Caso 23
		  Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,2,2015,0,0,-1);
		  assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testDiaInicialMaiorLimite() { // Caso 24
		Float tarifa = wstt.calculaTarifa(32,1,2015,0,0,0,1,1,2015,0,0,0);
		assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testMesInicialMaiorLimite() { // Caso 25
		Float tarifa = wstt.calculaTarifa(1,13,2015,0,0,0,1,2,2015,0,0,0);
		assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testHoraInicialMaiorLimite() { // Caso 26
		Float tarifa = wstt.calculaTarifa(1,2,2015,24,0,0,1,2,2015,0,0,0);
		assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testMinutoInicialMaiorLimite() { // Caso 27
		Float tarifa = wstt.calculaTarifa(1,2,2015,0,60,0,1,2,2015,0,0,0);
		assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testSegundoInicialMaiorLimite() { // Caso 28
		Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,60,1,2,2015,0,0,0);
		assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testDiaFinalMaiorLimite() { // Caso 29
		Float tarifa = wstt.calculaTarifa(1,1,2015,0,0,0,32,1,2015,0,0,0);
		assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testMesFinalMaiorLimite() { // Caso 30
		Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,13,2015,0,0,0);
		assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testHoraFinalMaiorLimite() { // Caso 31
		Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,2,2015,24,0,0);
		assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testMinutoFinalMaiorLimite() { // Caso 32
		Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,2,2015,0,60,0);
		assertEquals(-1F, tarifa);
	}
	
	@Test
	public void testSegundoFinalMaiorLimite() { // Caso 33
		Float tarifa = wstt.calculaTarifa(1,2,2015,0,0,0,1,2,2015,0,0,60);
		assertEquals(-1F, tarifa);
	}

}
