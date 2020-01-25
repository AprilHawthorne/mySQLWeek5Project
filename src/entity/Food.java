package entity;

public class Food {
	
	// variables
	private int id;
	private String name;
	
	// constructor
	public Food(int id, String name) {
		this.setId(id);
		this.setName(name);
	}

	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
