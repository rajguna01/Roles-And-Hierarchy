package models;

import java.util.ArrayList;
import java.util.List;

public class Role {
	private String name;
	private List<User> users;
	private List<Role> subRoles;
	private Role parent;
	public  List<List<Role>> roleHierarchy; 
	public 	List<Role> roles;
	public Role(String name, Role parent)
	{
		this.name = name;
		this.users = new ArrayList<User>();
		this.subRoles = new ArrayList<Role>();
		this.parent = parent;
	}
	public void storeSubUserInUser(User user)
	{
		for(int index = 1; index < roleHierarchy.size(); index ++)
		{
			List<Role> subRole = roleHierarchy.get(index);
			for(int innerIndex = 0; innerIndex < subRole.size(); innerIndex ++)
			{
				List<User> users = subRole.get(innerIndex).getUsers();
				for(int userIndex = 0; userIndex < users.size(); userIndex ++)
				{
					User subUser = users.get(userIndex);
					if(!user.getSubUserList().contains(subUser))
					{
						user.setSubUserList(subUser);
					}
				}
			}
		}
	}
	public void storeSubUserInUser()
	{
		for(int index = 0; index < roles.size(); index ++)
		{
			roles.get(index).store();
			List<User> users = roles.get(index).getUsers();
			if(users.size() > 0)
			{
				for(int userIndex = 0; userIndex < users.size(); userIndex ++ )
				{
					roles.get(index).storeSubUserInUser(users.get(userIndex));
				}
				
			}
		}
	}
	public void print()
	{
		for(int index = 0; index < roles.size(); index ++)
		{
			System.out.print(roles.get(index).getName());
			if(index != roles.size()-1)
			{
				System.out.print(",");
			}
		}
	}
	
	public void findNumberOfUserFromTop(User user)
	{
		store();
		storeSubUserInUser();
		int numberOfUserFromTop  = -1;
		for(int index = 0; index < roles.size(); index ++)
		{
			if(roles.get(index).getUsers().size() > 0)
			{
				List<User> userList = roles.get(index).getUsers();
				if(userList.size() > 0) 
				{
					User firstUser = userList.get(0);
					List<User> subUserList = firstUser.getSubUserList();
					numberOfUserFromTop =  subUserList.indexOf(user);
					if(numberOfUserFromTop != -1)
					{
						System.out.println("Number of users from top : " + numberOfUserFromTop );
						break;
					}
				}
			}
		}
		if(numberOfUserFromTop == -1)
		{
			System.out.println("Number of users from top : 0");
		}
	}
	public void findCommmonBossForUsers(User user1, User user2)
	{
		store();
		for(int index = 0; index < roles.size(); index ++ )
		{
			List<User> users = roles.get(index).getUsers();
			for(int userIndex = 0; userIndex < users.size(); userIndex ++)
			{
				List<User> subUser = users.get(userIndex).getSubUserList();
				
				if(subUser.contains(user1) && subUser.contains(user2))
				{
					System.out.println("Top most common Boss : " + users.get(userIndex).getUserName());
					return;
				}
			}
			
		}
		System.out.println("No common boss for this users");
	}
	public void store()
	{
		roleHierarchy = new ArrayList<>();
		roles = new ArrayList<>();
		List<Role> rootRoleList = new ArrayList<>();
		rootRoleList.add(this);
		roles.add(this);
		roleHierarchy.add(rootRoleList);
		for(int index = 0; index < roleHierarchy.size(); index ++)
		{
			List<Role> subRoles = new ArrayList<>();
			List<Role> list = roleHierarchy.get(index);
			for(int innerIndex = 0; innerIndex < list.size(); innerIndex ++)
			{
				List<Role> subRoleInList = list.get(innerIndex).getSubRole();
				for(int subRoleIndex = 0; subRoleIndex < subRoleInList.size(); subRoleIndex ++)
				{
					subRoles.add(subRoleInList.get(subRoleIndex));
					roles.add(subRoleInList.get(subRoleIndex));
				}
			}
			if(subRoles.size() > 0)
			{
				roleHierarchy.add(subRoles);
			}
				
		}	
	}
	
	public void findHierarchyHieght()
	{
		store();
		System.out.println("hieght - " + roleHierarchy.size());
	}
	public void removeSubRole(Role role)
	{
		subRoles.remove(role);
	}
	
	public void replaceSubRole(Role replaceRole, Role replaceByRole)
	{
		removeSubRole(replaceRole);
		addSubRole(replaceByRole);
	}
	
	
	public int getRoleListSize()
	{
		return subRoles.size();
	}
	
	public String getName() 
	{
		return name;
	}
	public List<Role> getSubRole()
	{
		return subRoles;
	}
	public Role getParent()
	{
		return parent;
	}
	public void addSubRole(Role role)
	{
		subRoles.add(role);
	}
	public void addAllSubRole(List<Role> allRole) {
		subRoles.addAll(allRole);
	}
	
	public void setParent(Role parent)
	{
		this.parent = parent;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(User user) {
		this.users.add(user);
	}
	public void addUser(User user)
	{
		users.add(user);
	}
	public void removeUser(User user)
	{
		users.remove(user);
	}

}
