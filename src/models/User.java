package models;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String userName;
	private Role role;
	private List<User> subUserList;
	public User(String userName, Role role)
	{
		this.userName = userName;
		this.role = role;
		this.subUserList = new ArrayList<User>();
	}
	public String getUserName()
	{
		return userName;
	}
	public String getRoleName()
	{
		if(role == null)
		{
			return "null";
		}
		return role.getName();
	}
	public List<User> getSubUserList()
	{
		return subUserList;
	}
	public void setSubUserList(User user)
	{
		subUserList.add(user);
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

}
