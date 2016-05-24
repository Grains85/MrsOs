
public class Analys {

	private String kod;
	private String namn;
	private String resultat;
	private String enhet;
	private String referensintervall;
	private String flagga;
	private String kommentar;
	
	public Analys(String kod, String namn, String resultat, String enhet, String referensintervall, String flagga, String kommentar){
		this.kod = kod;
		this.namn = namn;
		this.resultat = resultat;
		this.enhet = enhet;
		this.referensintervall = referensintervall;
		this.flagga = flagga;
		this.kommentar = kommentar;
	}
	
	public String getKod() {
		return kod;
	}
	public void setKod(String kod) {
		this.kod = kod;
	}
	public String getNamn() {
		return namn;
	}
	public void setNamn(String namn) {
		this.namn = namn;
	}
	public String getResultat() {
		return resultat;
	}
	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	public String getEnhet() {
		return enhet;
	}
	public void setEnhet(String enhet) {
		this.enhet = enhet;
	}
	public String getReferensintervall() {
		return referensintervall;
	}
	public void setReferensintervall(String referensintervall) {
		this.referensintervall = referensintervall;
	}
	public String getFalgga() {
		return flagga;
	}
	public void setFalgga(String falgga) {
		this.flagga = falgga;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
}
