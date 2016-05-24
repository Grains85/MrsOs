
public class Meddelande {

	private String datum;
	private String tid;
	private String meddelandeId;
	
	public Meddelande(String datum, String tid, String meddelandeId){
		this.datum = datum;
		this.tid = tid;
		this.meddelandeId = meddelandeId;
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
	public String getMeddelandeID() {
		return meddelandeId;
	}
	public void setMeddelandeID(String meddelandeId) {
		this.meddelandeId = meddelandeId;
	}
}
