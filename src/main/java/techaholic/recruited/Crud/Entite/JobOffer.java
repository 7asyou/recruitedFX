package techaholic.recruited.Crud.Entite;

import java.sql.Date;

public class JobOffer {
	private int id;
	private String positionTitle;
	private int availablePositions;
	private String location;
	private String tags;
	private Date deadline;
	private int duration;
	private String description;

	public JobOffer(int id, String positionTitle, int availablePositions, String location, String tags, Date deadline,
			int duration, String description) {
		this.tags = tags;
		this.availablePositions = availablePositions;
		this.deadline = deadline;
		this.duration = duration;
		this.id = id;
		this.location = location;
		this.description = description;
		this.positionTitle = positionTitle;

	}

	public JobOffer(String positionTitle, int availablePositions, String location, String tags, Date deadline,
			int duration, String description) {
		this.tags = tags;
		this.availablePositions = availablePositions;
		this.deadline = deadline;
		this.duration = duration;
		this.location = location;
		this.description = description;
		this.positionTitle = positionTitle;

	}

	public int getAvailablePositions() {
		return availablePositions;
	}

	public Date getDeadline() {
		return deadline;
	}

	public String getDescription() {
		return description;
	}

	public int getDuration() {
		return duration;
	}

	public int getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public String getPositionTitle() {
		return positionTitle;
	}

	public String getTags() {
		return tags;
	}

	public void setAvailablePositions(int availablePositions) {
		this.availablePositions = availablePositions;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "jobOffer{" +
				"Id=" + id +
				", availablePositions='" + availablePositions + '\'' +
				", deadline='" + deadline + '\'' +
				", duration=" + duration +
				", description=" + description +
				", positionTitle='" + positionTitle + '\'' +
				", tags='" + tags + '\'' +
				", location='" + location + '\'' +
				'}';
	}

}
