package techaholic.recruited.CRUD.Entite;

public class User {
	private int Id;
	private String FirstName;
	private String LastName;
	private int Role;
	private int PhoneNumber;
	private String Email;
	private String PasswordHash;

	public User(int id, String firstName, String lastName, int role, int phoneNumber, String email, String passwordHash) {
		Id = id;
		FirstName = firstName;
		LastName = lastName;
		Role = role;
		PhoneNumber = phoneNumber;
		Email = email;
		PasswordHash = passwordHash;
	}

	public User(String firstName, String lastName, int role, int phoneNumber, String email, String passwordHash) {
		FirstName = firstName;
		LastName = lastName;
		Role = role;
		PhoneNumber = phoneNumber;
		Email = email;
		PasswordHash = passwordHash;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public int getRole() {
		return Role;
	}

	public void setRole(int role) {
		Role = role;
	}

	public int getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	
	public String getPasswordHash() {
		return PasswordHash;
	}
	
	public void setPasswordHash(String passwordHash) {
		PasswordHash = passwordHash;
	}
	@Override
	public String toString() {
		return "User{" +
				"Id=" + Id +
				", FirstName='" + FirstName + '\'' +
				", LastName='" + LastName + '\'' +
				", Role=" + Role +
				", PhoneNumber=" + PhoneNumber +
				", Email='" + Email + '\'' +
				", PasswordHash='" + PasswordHash + '\'' +
				'}';
	}
}
