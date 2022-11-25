package model;


public class Image {
	private int id;
	private String name;
	private String path;
	
	
	public Image() {
		// TODO Auto-generated constructor stub
	}
	
	public Image( String image,String path) {
		super();
		this.name = image;
		this.path = path;
	}



	public Image(int id,String image,String path) {
		super();
		this.id = id;
		this.name =image;
		this.path = path;
	}

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
	
	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", image=" + name + "]";
	}

	
	
	
	

}
