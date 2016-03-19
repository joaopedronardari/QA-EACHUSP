import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WSTT {

	private String dataInicialString;
	private String dataFinalString;
	private Date dataInicial;
	private Date dataFinal;
	private float duracaoChamadaNoPeriodoSemDesconto;
	private float duracaoChamadaNoPeriodoComDesconto;
	
	public float calculaTarifa(int diai, int mesi, int anoi, int horai, int minutoi, int segundoi, int diaf, int mesf, int anof, int horaf, int minutof, int segundof) {
		
		float duracaoLigacao = calculaDuracaoLigacao(diai, mesi, anoi, horai, minutoi, segundoi, diaf, mesf, anof, horaf, minutof, segundof);
		float precoBruto = calculaPrecoBruto(duracaoLigacao);
		float porcentagemDesconto = calculaDescontos();
		float tarifaFinal = aplicaDescontoNoPrecoBruto(precoBruto, porcentagemDesconto);
		return tarifaFinal;
	}
	
	private float calculaPrecoBruto(float duracaoLigacao) {
		return (float) (duracaoLigacao * 0.4);
	}
	
	private float calculaDuracaoLigacao(int diai, int mesi, int anoi, int horai, int minutoi, int segundoi, int diaf, int mesf, int anof, int horaf, int minutof, int segundof) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
		dataInicialString = diai + "-" + mesi + "-" + anoi + " " + horai + ":" + minutoi + ":" + segundoi;
		dataFinalString = diaf + "-" + mesf + "-" + anof  + " " + horaf + ":" + minutof + ":" + segundof;

		try {

			dataInicial = formatter.parse(dataInicialString);
			dataFinal = formatter.parse(dataFinalString);
			System.out.println(dataInicial);
			System.out.println(formatter.format(dataInicial));
			
			System.out.println(dataFinal);
			System.out.println(formatter.format(dataFinal));
			
		} catch (ParseException e) {
			System.out.println("Data(s) Inválida(s)");
		}
		
		return 0;
	}
	
	private void calculaDuracaoNosIntervalos() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
        cal.setTime(dataInicial);
		String inicioPeriodoComDescontoString = cal.get(Calendar.DAY_OF_MONTH) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR) + " " + "18:00:00";
		cal.setTime(dataFinal);
		String fimPeriodoComDescontoString = cal.get(Calendar.DAY_OF_MONTH) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR) + " " + "7:59:59";

		Date inicioPeriodoComDesconto = null;
		Date fimPeriodoComDesconto = null;
		
		try {

			inicioPeriodoComDesconto = formatter.parse(inicioPeriodoComDescontoString);
			fimPeriodoComDesconto = formatter.parse(fimPeriodoComDescontoString);
			System.out.println(inicioPeriodoComDesconto);
			System.out.println(formatter.format(inicioPeriodoComDesconto));
			
			System.out.println(fimPeriodoComDesconto);
			System.out.println(formatter.format(fimPeriodoComDesconto));
			
		} catch (ParseException e) {
			System.out.println("Período(s) Inválido(s)");
		}
		
		
		cal.setTime(dataInicial);
		if(cal.get(Calendar.HOUR) >= 8 && cal.get(Calendar.HOUR) < 18) {
			duracaoChamadaNoPeriodoSemDesconto = dataFinal.getTime() - dataInicial.getTime();
		} else if(cal.get(Calendar.HOUR) >= 0 && cal.get(Calendar.HOUR) < 8) {
			duracaoChamadaNoPeriodoComDesconto = dataFinal.getTime() - dataInicial.getTime();
		} else if(cal.get(Calendar.HOUR) >= 18 && cal.get(Calendar.HOUR) < 24) {
			duracaoChamadaNoPeriodoComDesconto = dataFinal.getTime() - dataInicial.getTime();
		} else if(cal.get(Calendar.HOUR) >= 0 && cal.get(Calendar.HOUR) < 8) {
			duracaoChamadaNoPeriodoComDesconto = dataFinal.getTime() - dataInicial.getTime();
		} else if(cal.get(Calendar.HOUR) >= 18 && cal.get(Calendar.HOUR) < 8) {
			duracaoChamadaNoPeriodoComDesconto = dataFinal.getTime() - dataInicial.getTime();
		} 
		
		duracaoChamadaNoPeriodoComDesconto = dataInicial.getTime() - inicioPeriodoComDesconto.getTime();
		System.out.println(fimPeriodoComDesconto);
		System.out.println(formatter.format(fimPeriodoComDesconto));
		
		duracaoChamadaNoPeriodoSemDesconto = dataFinal.getTime() - fimPeriodoComDesconto.getTime();
	}
	
	private float calculaDescontos() {
		return 0;
	}

	private float aplicaDescontoNoPrecoBruto(float precoBruto, float porcentagemDesconto) {
		return precoBruto * (1 - porcentagemDesconto);
	}
	
}
