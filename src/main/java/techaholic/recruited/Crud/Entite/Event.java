package techaholic.recruited.CRUD.Entite;

import java.sql.Date;

public class Event {
	private int id;
	private String name;
	private Date date;
	private String tags;
	private String images;
	private String description;

	public Event(

			int id,
			String name,
			Date date,
			String tags,
			String images,
			String description) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.tags = tags;
		this.images = images;
		this.description = description;
	}

	public Event(
			String name,
			Date date,
			String tags,
			String images,
			String description) {
		this.name = name;
		this.date = date;
		this.tags = tags;
		this.images = images;
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public String getImages() {
		return images;
	}

	public String getName() {
		return name;
	}

	public String getTags() {
		return tags;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setImages(String images) {
		this.images = images;
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
				", date='" + date + '\'' +
				", tags=" + tags + '\'' +
				", images='" + images + '\'' +
				", description=" + '\'' +
				'}';
	}
}
