
public class Patient {

	private String patientID;
	private String namn;
	private String kon;
	
	public Patient(String patientID, String namn, String kon){
		this.patientID = patientID;
		this.namn = namn;
		this.kon = kon;
	}
	
	public String getPatientID() {
		return patientID;
	}
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	public String getNamn() {
		return namn;
	}
	public void setNamn(String namn) {
		this.namn = namn;
	}
	public String getKon() {
		return kon;
	}
	public void setKon(String kon) {
		this.kon = kon;
	}
}
