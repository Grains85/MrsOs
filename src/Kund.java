
public class Kund {

private String best;
private String xSvar;
private String fakt;
private String sign;

public Kund(String best, String xSvar, String fakt, String sign){
	this.best = best;
	this.xSvar = xSvar;
	this.fakt = fakt;
	this.sign = sign;	
}

public String getBest() {
	return best;
}
public void setBest(String best) {
	this.best = best;
}
public String getxSvar() {
	return xSvar;
}
public void setxSvar(String xSvar) {
	this.xSvar = xSvar;
}
public String getFakt() {
	return fakt;
}
public void setFakt(String fakt) {
	this.fakt = fakt;
}
public String getSign() {
	return sign;
}
public void setSign(String sign) {
	this.sign = sign;
}
}
