package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WSTT {
	
    public static final long HOUR_IN_MILLISECONDS = TimeUnit.HOURS.toMillis(1L);
    public static final long MINUTE_IN_MILLISECONDS = TimeUnit.MINUTES.toMillis(1L);

	public float calculaTarifa(int diai, int mesi, int anoi, int horai, int minutoi, int segundoi, int diaf, int mesf, int anof, int horaf, int minutof, int segundof) {
		boolean datasValidas = verify(diai, mesi, anoi, horai, minutoi, segundoi, diaf, mesf, anof, horaf, minutof, segundof);
		if (!datasValidas) { return -1; }
		
		// Converte datas
		final Date dataInicial = converterParaData(diai, mesi, anoi, horai, minutoi, segundoi);
		final Date dataFinal = converterParaData(diaf, mesf, anof, horaf, minutof, segundof);
		if (dataInicial == null || dataFinal == null) { return -1; }
		
		// Calc duracao
		long duracaoLigacao = calculaDuracaoLigacao(dataInicial, dataFinal);
		// Preco ainda sem considerar duracao + de 60
		float preco = calculaPreco(dataInicial, dataFinal);
		float tarifaFinal = aplicaDescontoSuperior1Hora(preco, duracaoLigacao);
		return tarifaFinal;
	}
	
	private float calculaPreco(final Date dataInicial, final Date dataFinal) {
		long duracao = calculaDuracaoLigacao(dataInicial, dataFinal);
		Calendar inicio = Calendar.getInstance();
        inicio.setTime(dataInicial);
		Calendar fim = Calendar.getInstance();
		fim.setTime(dataFinal);

		// Checa se esta na faixa de desconto ou nao
		if (inicio.get(Calendar.HOUR_OF_DAY) >= 8 && inicio.get(Calendar.HOUR_OF_DAY) < 18 &&
				fim.get(Calendar.HOUR_OF_DAY) >= 8 && fim.get(Calendar.HOUR_OF_DAY) < 18) {
			return calculoValorBruto(duracao);
		} else {
			return calculoValorBruto(duracao)*0.5F;
		}
	}
	
	// Calcula valor bruto - sem desconto
	private float calculoValorBruto(long minutos) {
		return (float) (minutos * 0.4);
	}
	
	// Pega variaveis e transforma em data
	public Date converterParaData(int dia,int mes, int ano, int hora, int minuto, int segundo) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
		String dataString = dia + "-" + mes + "-" + ano + " " + hora + ":" + minuto + ":" + segundo;

		try {
			Date data = formatter.parse(dataString);
			return data;
		} catch (ParseException e) {
			return null;
		}
	}
	
	// Da a duracao em minutos
	public long calculaDuracaoLigacao(final Date dataInicial, final Date dataFinal) {
		long milliseconds = Math.abs(dataFinal.getTime() - dataInicial.getTime());
        return milliseconds / MINUTE_IN_MILLISECONDS;
	}

	// aplica desconto de superior a uma hora
	public float aplicaDescontoSuperior1Hora(float precoBruto, long duracaoLigacao) {
		boolean maisQueUmaHora = duracaoLigacao >= 60 ? true:false;
		return maisQueUmaHora ? precoBruto * (1 - 0.15F) : precoBruto;
	}
	
	//retorna a diferenca em horas
    public static long hoursBetween(Date dateFrom, Date dateTo) {
        long milliseconds = Math.abs(dateTo.getTime() - dateFrom.getTime());
        return milliseconds / HOUR_IN_MILLISECONDS;
    }
    
    // valida data
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
    
    // checa se eh ano bissexto
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
