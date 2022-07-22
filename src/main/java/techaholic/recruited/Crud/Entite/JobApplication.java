package techaholic.recruited.CRUD.Entite;

import java.sql.Timestamp;

public class JobApplication {
	private int id;
	private String status;
	private String description;
	private String addJoints;
	private Timestamp date;
	private int evaluation;
	private User user;
	private JobOffer jobOffer;

	public JobApplication(int id, String status, String description, String addJoints, Timestamp date, int evaluation,
			User user, JobOffer jobOffer) {
		this.id = id;
		this.status = status;
		this.description = description;
		this.addJoints = addJoints;
		this.date = date;
		this.evaluation = evaluation;
		this.user = user;
		this.jobOffer = jobOffer;
	}

	public JobApplication(String status, String description, String addJoints, Timestamp date, int evaluation,
			User user, JobOffer jobOffer) {
		this.status = status;
		this.description = description;
		this.addJoints = addJoints;
		this.date = date;
		this.evaluation = evaluation;
		this.user = user;
		this.jobOffer = jobOffer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddJoints() {
		return addJoints;
	}

	public void setAddJoints(String addJoints) {
		this.addJoints = addJoints;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JobOffer getJobOffer() {
		return jobOffer;
	}

	public void setJobOffer(JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}

	@Override
	public String toString() {
		return "JobApplication{" +
				"id=" + id +
				", status='" + status + '\'' +
				", description='" + description + '\'' +
				", addJoints='" + addJoints + '\'' +
				", date=" + date +
				", evaluation=" + evaluation +
				", user=" + user +
				", jobOffer=" + jobOffer +
				'}';
	}
}
