package entities;

public class Converter {
	
	private double valor1;
	private String moeda1 = "";
	private String moeda2 = "";
	private double valor2;
	
	public Converter(double valor1, String moeda1, String moeda2) {
		this.valor1 = valor1;
		this.moeda1 = moeda1;
		this.moeda2 = moeda2;
	}

	public double getValor1() {
		return valor1;
	}

	public String getMoeda1() {
		return moeda1;
	}

	public String getMoeda2() {
		return moeda2;
	}

	public double getValor2() {
		return valor2;
	}

	public void setValor2(double valor2) {
		this.valor2 = valor2;
	}
	
	public double converte() {
		return valor1 * valor2;
	}
	
	public String toString() {
		return "Valor " + this.getValor1() + " [" + this.getMoeda1() + "] corresponde ao valor final de ==> " + this.converte() + " [" + this.getMoeda2() + "]";
	}
}