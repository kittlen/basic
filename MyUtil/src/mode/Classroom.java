package mode;

import java.util.List;

public class Classroom {
	private String id;
	private User user;
	private List<String> classroomPhoto;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<String> getClassroomPhoto() {
		return classroomPhoto;
	}
	public void setClassroomPhoto(List<String> classroomPhoto) {
		this.classroomPhoto = classroomPhoto;
	}
	
	

}
