package br.com.erudio;

public class Greeting {
	private final long id;
	private final String cotent;
	
	public Greeting(long id, String cotent) {
		this.id = id;
		this.cotent = cotent;
	}

	public long getId() {
		return id;
	}

	public String getCotent() {
		return cotent;
	}
	
	
	
	
}
