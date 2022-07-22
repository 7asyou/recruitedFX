package techaholic.recruited.CRUD.Entite;

public class Article {
	private int id;
	private String category;
	private String title;
	private String description;
	private String image;

	public Article(int id, String category, String title, String description, String image) {
		this.id = id;
		this.category = category;
		this.title = title;
		this.description = description;
		this.image = image;
	}

	public Article(String category, String title, String description, String image) {
		this.category = category;
		this.title = title;
		this.description = description;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Article{" +
				"id=" + id +
				", category='" + category + '\'' +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", image='" + image + '\'' +
				'}';
	}
}
