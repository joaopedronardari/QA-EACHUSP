package x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WSTT {
	
    public static final long HOUR_IN_MILLISECONDS = TimeUnit.HOURS.toMillis(1L);
    public static final long MINUTE_IN_MILLISECONDS = TimeUnit.MINUTES.toMillis(1L);

	public float calculaTarifa(int diai, int mesi, int anoi, int horai, int minutoi, int segundoi, int diaf, int mesf, int anof, int horaf, int minutof, int segundof) {
		boolean datasValidas = verify(diai, mesi, anoi, horai, minutoi, segundoi, diaf, mesf, anof, horaf, minutof, segundof);
		if (!datasValidas) { return -1; }
		
		final Date dataInicial = converterParaData(diai, mesi, anoi, horai, minutoi, segundoi);
		final Date dataFinal = converterParaData(diaf, mesf, anof, horaf, minutof, segundof);
		if (dataInicial == null || dataFinal == null) { return -1; }
		
		long duracaoLigacao = calculaDuracaoLigacao(dataInicial, dataFinal);
		float preco = calculaPreco(dataInicial, dataFinal);
		float tarifaFinal = aplicaDescontoSuperior1Hora(preco, duracaoLigacao);
		return tarifaFinal;
	}
	
	private float calculaPreco(final Date dataInicial, final Date dataFinal) {
		// TODO
		// (float) (duracaoLigacao * 0.4)
		
		/*SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar inicio = Calendar.getInstance();
        inicio.setTime(dataInicial);
		String inicioPeriodoComDescontoString = inicio.get(Calendar.DAY_OF_MONTH) + "-" + inicio.get(Calendar.MONTH) + "-" + inicio.get(Calendar.YEAR) + " " + "18:00:00";
		String inicioPeriodoMeiaNoiteString = inicio.get(Calendar.DAY_OF_MONTH) + "-" + inicio.get(Calendar.MONTH) + "-" + inicio.get(Calendar.YEAR) + " " + "23:59:59";
		Calendar fim = Calendar.getInstance();
		fim.setTime(dataFinal);
		String fimPeriodoComDescontoString = fim.get(Calendar.DAY_OF_MONTH) + "-" + fim.get(Calendar.MONTH) + "-" + fim.get(Calendar.YEAR) + " " + "7:59:59";

		Date inicioPeriodoComDesconto = null;
		Date inicioPeriodoMeiaNoite = null;
		Date fimPeriodoComDesconto = null;
		
		try {

			inicioPeriodoComDesconto = formatter.parse(inicioPeriodoComDescontoString);
			inicioPeriodoMeiaNoite = formatter.parse(inicioPeriodoMeiaNoiteString);
			fimPeriodoComDesconto = formatter.parse(fimPeriodoComDescontoString);
			System.out.println(inicioPeriodoComDesconto);
			System.out.println(formatter.format(inicioPeriodoComDesconto));
			
			System.out.println(inicioPeriodoMeiaNoite);
			System.out.println(formatter.format(inicioPeriodoMeiaNoite));
			
			System.out.println(fimPeriodoComDesconto);
			System.out.println(formatter.format(fimPeriodoComDesconto));
			
		} catch (ParseException e) {
			System.out.println("Período(s) Inválido(s)");
		}
		
		Calendar aux = inicio;
		
		while(aux.before(fim)){
			
			if(aux.get(Calendar.HOUR_OF_DAY) >= 8 && aux.get(Calendar.HOUR_OF_DAY) < 18) {
				this.duracaoChamadaNoPeriodoSemDesconto += minutesBetween(aux.getTime(), inicioPeriodoComDesconto);
				aux.setTime(inicioPeriodoComDesconto);		
			} else if(aux.get(Calendar.HOUR_OF_DAY) >= 0 && aux.get(Calendar.HOUR_OF_DAY) < 8) {
				this.duracaoChamadaNoPeriodoComDesconto += minutesBetween(aux.getTime(), fimPeriodoComDesconto);
				aux.setTime(fimPeriodoComDesconto);	
			} else if(aux.get(Calendar.HOUR_OF_DAY) >= 18 && aux.get(Calendar.HOUR_OF_DAY) < 24) {
				this.duracaoChamadaNoPeriodoComDesconto += minutesBetween(aux.getTime(), inicioPeriodoMeiaNoite);
				aux.setTime(inicioPeriodoMeiaNoite);
			} 

			
			System.out.println("loop");
		}
		
		
		//duracaoChamadaNoPeriodoComDesconto = dataInicial.getTime() - inicioPeriodoComDesconto.getTime();
		System.out.println(fimPeriodoComDesconto);
		System.out.println(formatter.format(fimPeriodoComDesconto));
		
		//duracaoChamadaNoPeriodoSemDesconto = dataFinal.getTime() - fimPeriodoComDesconto.getTime();
		*/
		
		return -1;
	}
	
	public Date converterParaData(int dia,int mes, int ano, int hora, int minuto, int segundo) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
		String dataString = dia + "-" + mes + "-" + ano + " " + hora + ":" + minuto + ":" + segundo;

		try {
			Date data = formatter.parse(dataString);
			System.out.println(data);
			System.out.println(formatter.format(data));
			return data;
		} catch (ParseException e) {
			System.out.println("Data(s) Inválida(s)");
			return null;
		}
	}
	
	public long calculaDuracaoLigacao(final Date dataInicial, final Date dataFinal) {
		long milliseconds = Math.abs(dataFinal.getTime() - dataInicial.getTime());
        return milliseconds / MINUTE_IN_MILLISECONDS;
	}
	
	public Calendar atualizaCalendar(int dia, int mes, int ano, int hora, int min, int seg){
		
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, dia);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.HOUR_OF_DAY, hora);
        c.set(Calendar.MINUTE, min);
        c.set(Calendar.SECOND, seg);
        return c;
	}

	public float aplicaDescontoSuperior1Hora(float precoBruto, long duracaoLigacao) {
		boolean maisQueUmaHora = duracaoLigacao >= 60 ? true:false;
		return maisQueUmaHora ? precoBruto * (1 - 0.15F) : precoBruto;
	}
	
	//retorna a diferença em horas
    public static long hoursBetween(Date dateFrom, Date dateTo) {
        long milliseconds = Math.abs(dateTo.getTime() - dateFrom.getTime());
        return milliseconds / HOUR_IN_MILLISECONDS;
    }
    
    public boolean verify(int diai, int mesi, int anoi, int horai, int minutoi, int segundoi, int diaf, int mesf, int anof, int horaf, int minutof, int segundof){
    	  	
    	if(diai >= 31 || diai <= 0 || diaf >=31 || diaf <=0){
    		return false;
    	}
    	
    	if(mesi >= 12 || mesi <= 0 || mesf >=12 || mesf <=0){
    		return false;
    	}
    	
    	if(anoi <= 0 || anof <= 0){
    		return false;
    	}
    	
    	if(horai >= 24 || horai < 0 || horaf >=24 || horaf <0){
    		return false;
    	}
    	
    	if(minutoi > 59 || minutoi < 0 || minutof >59 || minutof <0){
    		return false;
    	}
    	
    	if(segundoi > 59 || segundoi < 0 || segundof >59 || segundof <0){
    		return false;
    	}
    	
    	boolean inicialBissexto = bissexto(anoi);
    	boolean finalBissexto = bissexto(anof);
    	
    	if (!inicialBissexto) {
    		if (mesi==2 && diai>28) {
    			return false;
    		}
    	}
    	
    	if (!finalBissexto) {
    		if (mesf==2 && diaf>28) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    public boolean bissexto(int ano) {
    	String yearString = String.valueOf(ano);
    	if(ano%4==0) {
    		if (!yearString.substring(yearString.length()-2).equals("00") || ano%400==0) {
    			return true;
    		}
    	}
    	
    	return false;
    }
	
}
