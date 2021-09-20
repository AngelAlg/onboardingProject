package com.amdocs.test.beans;

public class User {
	public static boolean admin;
	
	private String username;
	private String phone_no;
	private String email;
	private String address;
	private String reg_date;
	private String password;
	private String upload_photo;
	private String course_id;
	
	public static boolean isAdmin() {
		return admin;
	}
	public static void setAdmin(boolean admin) {
		User.admin = admin;
	}
	
public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
private String user_id;
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPhone_no() {
	return phone_no;
}
public void setPhone_no(String phone_no) {
	this.phone_no = phone_no;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getReg_date() {
	return reg_date;
}
public void setReg_date(String reg_date) {
	this.reg_date = reg_date;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getUpload_photo() {
	return upload_photo;
}
public void setUpload_photo(String upload_photo) {
	this.upload_photo = upload_photo;
}


}
