package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Role;
import models.User;
import utility.Utils;

public class RolesAndUserOperations {
	public static Map<String, Role> roleMap = new HashMap<>();
	private static Role rootRole;
	public static int height;
	public static Map<String, User> userMap = new HashMap<>(); 
	public static List<List<Role>> roleHierarchy = new ArrayList<>(); 
	public static void setRootRole(Role role)
	{
		role.setParent(null);
		rootRole = role;
	}
	
	public static Role getRootRole()
	{
		return rootRole;
	}
	
	public static void displayRoles() 
	{
		getRootRole().store();
		getRootRole().print();
	}
	
	public static void displayUserAndSubUsers()
	{
		getRootRole().store();
		getRootRole().storeSubUserInUser();
		for(String userName : userMap.keySet())
		{
			User user = userMap.get(userName);
			System.out.print(userName + " - ");
			List<User> userList = user.getSubUserList();
			for(int index = 0; index < userList.size(); index ++)
			{
				System.out.print(userList.get(index).getUserName());
				if(index != userList.size()-1)
				{
					System.out.print(", ");
				}
			}
			System.out.println();
		}
	}
	public static void addSubRole()
	{
		System.out.println("Enter Sub Role : ");
		String roleName = Utils.getInputFromUser();
		if(!roleMap.containsKey(roleName))
		{
			System.out.println("Enter Reportig To Role Name : ");
			String reportingRoleName = Utils.getInputFromUser();
			if(roleMap.containsKey(reportingRoleName))
			{
				Role parantRole = roleMap.get(reportingRoleName);
				Role role = new Role(roleName,parantRole);
				parantRole.addSubRole(role);
				roleMap.put(role.getName(), role);
				System.out.println("added...");
			}
			else
			{
				System.out.println("You are entered wrong input :-)");
				addSubRole();
			}
		}
		else
		{
			System.out.println("The role is already there :-)");
			addSubRole();
		}
		
	}
	
	public static void deleteRole()
	{
		System.out.println("Enter the role to be deleted : ");
		String deletedRoleName = Utils.getInputFromUser();
		if(roleMap.containsKey(deletedRoleName)) 
		{
			System.out.println("Enter the role to be transferred : ");
			String transferredRoleName = Utils.getInputFromUser();
			Role deletedRole = roleMap.get(deletedRoleName);
			if(roleMap.containsKey(transferredRoleName))
			{
				Role parentForDeletedRole = deletedRole.getParent();
				Role transferredRole = roleMap.get(transferredRoleName);
				if(transferredRole.getParent() == null)
				{
					parentForDeletedRole.removeSubRole(deletedRole);
					transferredRole.addAllSubRole(deletedRole.getSubRole());
				}
				else
				{
					Role parentForTransferedRole = transferredRole.getParent();
					parentForTransferedRole.removeSubRole(transferredRole);
					transferredRole.addAllSubRole(deletedRole.getSubRole());
					if(deletedRole.getParent() == null) 
					{
						setRootRole(transferredRole);
					}
					else 
					{
						parentForDeletedRole.replaceSubRole(deletedRole, transferredRole);
						if(!parentForDeletedRole.getName().equals(transferredRole.getName()))
						{
							
							transferredRole.setParent(parentForDeletedRole);
						}
					}
				}
				roleMap.remove(deletedRoleName);
				System.out.println("deleted..");
				for(String userName : userMap.keySet())
				{
					User user = userMap.get(userName);
					if(user.getRoleName().equals(deletedRoleName))
					{
						user.setRole(transferredRole);
					}
				}
			}
			else
			{
				System.out.println("wrong input....");
			}		
        }
		else
		{
			System.out.println("wrong input....");
		}

	}
	public static void addUser()
	{
		System.out.println("Enter User Name : ");
		String userName = Utils.getInputFromUser();
		System.out.println("Enter Role : ");
		String roleName = Utils.getInputFromUser();
		if(roleMap.containsKey(roleName))
		{
			Role role = roleMap.get(roleName);
			if(userMap.containsKey(userName))
			{
				User user = userMap.get(userName);
				if(user.getRoleName() != null)
				{
					System.out.println("the user is assinged to " + user.getRoleName());
				}
				else
				{
					user.setRole(role);
					role.addUser(user);
					System.out.println("added...");
				}
			}
			else
			{
				User user = new User(userName, role);
				role.addUser(user);
				userMap.put(userName,user);
				System.out.println("added...");
			}
		}
		else
		{
			System.out.println("You are Entered Wrong Role!!");
		}
	}
	public static void displayUsers()
	{
		for(String userName : userMap.keySet())
		{
			String roleName = userMap.get(userName).getRoleName();
			System.out.println(userName + " - " + roleName);
		}
	}
	public static void deleteUser()
	{
		System.out.println("Enter username to be deleted : ");
		String deletedUserName = Utils.getInputFromUser();
		if(userMap.containsKey(deletedUserName))
		{
			User user = userMap.get(deletedUserName);
			Role role = user.getRole();
			role.removeUser(user);
			userMap.remove(deletedUserName);
			for(String userName : userMap.keySet())
			{
				List<User> subUsers = userMap.get(userName).getSubUserList();
				if(subUsers.contains(user))
				{
					userMap.get(userName).getSubUserList().remove(user);
					System.out.println("deleted...");
				}
			}
			displayUserAndSubUsers();
		}
		else
		{
			System.out.println("wrong user name...");
		}
		
		
	}
	public static void heightOfHierarchy()
	{
		Role rootRole = getRootRole();
		rootRole.findHierarchyHieght();
	}
	public static void numberOfUserFromTop()
	{
		System.out.println("Enter user name : ");
		String userName = Utils.getInputFromUser();
		if(userMap.containsKey(userName))
		{
			User user = userMap.get(userName);
			if(rootRole.getUsers().contains(user))
			{
				System.out.println("Number of users from top : 0");
			}
			else
			{
				rootRole.findNumberOfUserFromTop(user);
			}
		}
		else
		{
			System.out.println("wrong user name..");
		}
	}
	public static void findCommonBossOfUsers()
	{
		System.out.println("Enter User 1 : ");
		String userOne = Utils.getInputFromUser();
		if(userMap.containsKey(userOne))
		{
			User user1 = userMap.get(userOne);
			System.out.println("Enter User 2 : ");
			String userTwo = Utils.getInputFromUser();
			if(userMap.containsKey(userTwo))
			{
				User user2 = userMap.get(userTwo);
				rootRole.storeSubUserInUser();
				rootRole.findCommmonBossForUsers(user1, user2);
			}
			else
			{
				System.out.println("wrong user name!!");
				findCommonBossOfUsers();
			}
		}
		else
		{
			System.out.println("wrong user name!!");
			findCommonBossOfUsers();
		}
	}
}
