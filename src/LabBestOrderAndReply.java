
public class LabBestOrderAndReply {

	private Meddelande meddelande;
	private Kund kund;
	private Patient patient;
	private Remiss remiss;
	private Prov prov;
	
	public LabBestOrderAndReply(Meddelande meddelande, Kund kund, Patient patient, Remiss remiss, Prov prov){
		this.meddelande = meddelande;
		this.kund = kund;
		this.patient = patient;
		this.remiss = remiss;
		this.prov = prov;
	}
	
	public Meddelande getMeddelande() {
		return meddelande;
	}
	public void setMeddelande(Meddelande meddelande) {
		this.meddelande = meddelande;
	}
	public Kund getKund() {
		return kund;
	}
	public void setKund(Kund kund) {
		this.kund = kund;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Remiss getRemiss() {
		return remiss;
	}
	public void setRemiss(Remiss remiss) {
		this.remiss = remiss;
	}
	public Prov getProv() {
		return prov;
	}
	public void setProv(Prov prov) {
		this.prov = prov;
	}
}
