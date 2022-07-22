package techaholic.recruited.CRUD.Entite;

public class Entreprise {
	private int id;
	private String name;
	private String tags;
	private String logo;
	private String contactNumber;
	private String contactEmail;
	private String description;

	public Entreprise(

			int id,
			String name,
			String tags,
			String logo,
			String contactNumber,
			String contactEmail,
			String description

	) {

		this.id = id;
		this.name = name;
		this.tags = tags;
		this.logo = logo;
		this.contactNumber = contactNumber;
		this.contactEmail = contactEmail;
		this.description = description;
	}

	public Entreprise(
			String name,
			String tags,
			String logo,
			String contactNumber,
			String contactEmail,
			String description

	) {

		this.name = name;
		this.tags = tags;
		this.logo = logo;
		this.contactNumber = contactNumber;
		this.contactEmail = contactEmail;
		this.description = description;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public String getLogo() {
		return logo;
	}

	public String getName() {
		return name;
	}

	public String getTags() {
		return tags;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Event{" +
				"Id=" + id +
				", name='" + name + '\'' +
				", tags=" + tags + '\'' +
				", logo='" + logo + '\'' +
				", contactNumber='" + contactNumber + '\'' +
				", contactEmail='" + contactEmail + '\'' +
				", description=" + '\'' +
				'}';
	}
}
