package myplugin.generator.fmmodel;

public class FMLinkedProperty extends FMProperty {

	private CascadeType cascade;
	private FetchType fetch;
	private String mappedBy;
	private Boolean orphanRemoval;
	private Boolean optional;
	
	
	private FMLinkedProperty oppositeEnd;
	
	public FMLinkedProperty() {
		super();
		
	}
	public FMLinkedProperty(FMProperty fm) {
		super(fm.getType(), fm.getVisibility(), fm.getLower(), fm.getUpper());
		super.setColName(fm.getColName());
	}

	public CascadeType getCascade() {
		return cascade;
	}

	public void setCascade(CascadeType cascade) {
		this.cascade = cascade;
	}

	public FetchType getFetch() {
		return fetch;
	}

	public void setFetch(FetchType fetch) {
		this.fetch = fetch;
	}

	public String getMappedBy() {
		return mappedBy;
	}

	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}

	public Boolean getOrphanRemoval() {
		return orphanRemoval;
	}

	public void setOrphanRemoval(Boolean orphanRemoval) {
		this.orphanRemoval = orphanRemoval;
	}

	public Boolean getOptional() {
		return optional;
	}

	public void setOptional(Boolean optional) {
		this.optional = optional;
	}



	public FMLinkedProperty getOppositeEnd() {
		return oppositeEnd;
	}

	public void setOppositeEnd(FMLinkedProperty oppositeEnd) {
		this.oppositeEnd = oppositeEnd;
	}



}
