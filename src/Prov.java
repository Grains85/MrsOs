import java.util.List;


public class Prov {

	private String kommentar;
	private String lid;
	private List<Analys> analysList;
	
	public Prov(String kommentar, String lid, List<Analys> analysList){
		this.kommentar = kommentar;
		this.lid = lid;
		this.analysList = analysList;
	}
	
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public List<Analys> getAnalysList() {
		return analysList;
	}
	public void setAnalysList(List<Analys> analysList) {
		this.analysList = analysList;
	}
}
