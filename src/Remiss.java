
public class Remiss {

	private String datum;
	private String tid;
	private String kommentar;
	private String prioritet;
	private String rid;
	private String svarsstatus;
	
	public Remiss(String datum, String tid, String kommentar, String prioritet, String rid, String svarsstatus){
		this.datum = datum;
		this.tid = tid;
		this.kommentar = kommentar;
		this.prioritet = prioritet;
		this.rid = rid;
		this.svarsstatus = svarsstatus;
	}
	
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
	public String getPrioritet() {
		return prioritet;
	}
	public void setPrioritet(String prioritet) {
		this.prioritet = prioritet;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getSvarsstatus() {
		return svarsstatus;
	}
	public void setSvarsstatus(String svarsstatus) {
		this.svarsstatus = svarsstatus;
	}
	
}
