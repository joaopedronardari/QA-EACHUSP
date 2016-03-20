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

	private String dataInicialString;
	private String dataFinalString;
	private Date dataInicial;
	private Date dataFinal;
	private boolean maisQueUmaHora;
	private float duracaoChamadaNoPeriodoSemDesconto;
	private float duracaoChamadaNoPeriodoComDesconto;
	
	public float calculaTarifa(int diai, int mesi, int anoi, int horai, int minutoi, int segundoi, int diaf, int mesf, int anof, int horaf, int minutof, int segundof) {
		
		float duracaoLigacao = calculaDuracaoLigacao(diai, mesi, anoi, horai, minutoi, segundoi, diaf, mesf, anof, horaf, minutof, segundof);
		if (duracaoLigacao == -1F) { return -1; }
		float precoBruto = calculaPrecoBruto(duracaoLigacao);
		float porcentagemDesconto = calculaDescontos();
		float tarifaFinal = aplicaDescontoNoPrecoBruto(precoBruto, porcentagemDesconto);
		return tarifaFinal;
	}
	
	private float calculaPrecoBruto(float duracaoLigacao) {
		return (float) (duracaoLigacao * 0.4);
	}
	
	public float calculaDuracaoLigacao(int diai, int mesi, int anoi, int horai, int minutoi, int segundoi, int diaf, int mesf, int anof, int horaf, int minutof, int segundof) {
		
		Float verifica = verify(diai, mesi, anoi, horai, minutoi, segundoi, diaf, mesf, anof, horaf, minutof, segundof);
		if(verifica == -1F){
			return verifica;
		}
		
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
			return -1F;
		}
		
		Long duracaoLigacao = minutesBetween(dataInicial,dataFinal); //retorna a diferença em minutos
		
		if(duracaoLigacao >= 60){
			maisQueUmaHora = true;
		}else{
			maisQueUmaHora = false;
		}
		
		return duracaoLigacao;
	}
	
	public void calculaDuracaoNosIntervalos() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar inicio = Calendar.getInstance();
        inicio.setTime(dataInicial);
		String inicioPeriodoComDescontoString = inicio.get(Calendar.DAY_OF_MONTH) + "-" + inicio.get(Calendar.MONTH) + "-" + inicio.get(Calendar.YEAR) + " " + "18:00:00";
		Calendar fim = Calendar.getInstance();
		fim.setTime(dataFinal);
		String fimPeriodoComDescontoString = fim.get(Calendar.DAY_OF_MONTH) + "-" + fim.get(Calendar.MONTH) + "-" + fim.get(Calendar.YEAR) + " " + "7:59:59";

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
		
		Calendar aux = Calendar.getInstance();
		
		while(inicio.before(fim)){
			
			if(inicio.get(Calendar.HOUR_OF_DAY) >= 8 && inicio.get(Calendar.HOUR_OF_DAY) < 18) {
//				aux = atualizaCalendar(inicio.get(Calendar.DAY_OF_MONTH), inicio.get(Calendar.MONTH), 
//						inicio.get(Calendar.YEAR), hora, min, seg);
				duracaoChamadaNoPeriodoSemDesconto += dataFinal.getTime() - dataInicial.getTime();
			} else if(inicio.get(Calendar.HOUR_OF_DAY) >= 0 && inicio.get(Calendar.HOUR_OF_DAY) < 8) {
				duracaoChamadaNoPeriodoComDesconto += dataFinal.getTime() - dataInicial.getTime();
			} else if(inicio.get(Calendar.HOUR_OF_DAY) >= 18 && inicio.get(Calendar.HOUR_OF_DAY) < 24) {
				duracaoChamadaNoPeriodoComDesconto += dataFinal.getTime() - dataInicial.getTime();
			} else if(inicio.get(Calendar.HOUR_OF_DAY) >= 18 && inicio.get(Calendar.HOUR_OF_DAY) < 8) {
				duracaoChamadaNoPeriodoComDesconto += dataFinal.getTime() - dataInicial.getTime();
			} 
		}
		
		
		//duracaoChamadaNoPeriodoComDesconto = dataInicial.getTime() - inicioPeriodoComDesconto.getTime();
		System.out.println(fimPeriodoComDesconto);
		System.out.println(formatter.format(fimPeriodoComDesconto));
		
		//duracaoChamadaNoPeriodoSemDesconto = dataFinal.getTime() - fimPeriodoComDesconto.getTime();
		
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
	
	public float calculaDescontos() {
		
		
		return 0; 
	}

	public float aplicaDescontoNoPrecoBruto(float precoBruto, float porcentagemDesconto) {
		
		Float precoBrutoAux = precoBruto * (1 - porcentagemDesconto);
		if(maisQueUmaHora == true){
			precoBrutoAux = precoBrutoAux * (1 - 0.15F);
		}
		return precoBrutoAux;
	}
	
	//retorna a diferença em horas
    public static long hoursBetween(Date dateFrom, Date dateTo) {
        long milliseconds = Math.abs(dateTo.getTime() - dateFrom.getTime());
        return milliseconds / HOUR_IN_MILLISECONDS;
    }
    
    //retorna a diferença em minutos
    public static long minutesBetween(Date dateFrom, Date dateTo) {
        long milliseconds = Math.abs(dateTo.getTime() - dateFrom.getTime());
        return milliseconds / MINUTE_IN_MILLISECONDS;
    }
    
    public Float verify(int diai, int mesi, int anoi, int horai, int minutoi, int segundoi, int diaf, int mesf, int anof, int horaf, int minutof, int segundof){
    	  	
    	if(diai >= 31 || diai <= 0 || diaf >=31 || diaf <=0){
    		return -1F;
    	}
    	
    	if(mesi >= 12 || mesi <= 0 || mesf >=12 || mesf <=0){
    		return -1F;
    	}
    	
    	if(anoi <= 0 || anof <= 0){
    		return -1F;
    	}
    	
    	if(horai >= 24 || horai < 0 || horaf >=24 || horaf <0){
    		return -1F;
    	}
    	
    	if(minutoi > 59 || minutoi < 0 || minutof >59 || minutof <0){
    		return -1F;
    	}
    	
    	if(segundoi > 59 || segundoi < 0 || segundof >59 || segundof <0){
    		return -1F;
    	}
    	
    	boolean inicialBissexto = bissexto(anoi);
    	boolean finalBissexto = bissexto(anof);
    	
    	if (!inicialBissexto) {
    		if (mesi==2 && diai>28) {
    			return -1F;
    		}
    	}
    	
    	if (!finalBissexto) {
    		if (mesf==2 && diaf>28) {
    			return -1F;
    		}
    	}
    	
    	return 0F;
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
