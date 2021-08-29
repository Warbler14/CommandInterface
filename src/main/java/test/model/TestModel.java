package test.model;

public class TestModel {

	//id INTEGER, name VARCHAR(255), address VARCHAR(255), city VARCHAR(255), state CHAR(2), zip CHAR(10)
	private Integer id;
	
	private String name;
	
	private String addrss;
	
	private String city;
	
	private String state;
	
	private String zip;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddrss() {
		return addrss;
	}

	public void setAddrss(String addrss) {
		this.addrss = addrss;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TestModel [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", addrss=");
		builder.append(addrss);
		builder.append(", city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append(", zip=");
		builder.append(zip);
		builder.append("]");
		return builder.toString();
	}
	
	
}
