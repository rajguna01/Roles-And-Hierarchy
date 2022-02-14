package controller;

import database.RolesAndUserOperations;
import models.Role;
import utility.Utils;

public class MainController {
	public static void addSubRole()
	{
		RolesAndUserOperations.addSubRole();
		operations();
	}
	public static void displayRoles()
	{
		RolesAndUserOperations.displayRoles();
		operations();
	}
	public static void deleteRole()
	{
		RolesAndUserOperations.deleteRole();
		operations();
	}
	public static void addUser()
	{
		RolesAndUserOperations.addUser();
		operations();

	}
	public static void displayUser() 
	{
		RolesAndUserOperations.displayUsers();
		operations();
	}
	public static void displayUserAndSubUsers() 
	{
		RolesAndUserOperations.displayUserAndSubUsers();
		operations();
	}
	public static void deleteUser()
	{
		RolesAndUserOperations.deleteUser();
		operations();
	}
	public static void numberOfUserFromTop()
	{
		RolesAndUserOperations.numberOfUserFromTop();
		operations();
	}
	public static void heightOfRoleHierarchy()
	{
		RolesAndUserOperations.heightOfHierarchy();
		operations();
	}
	public static void commonBossOfUsers()
	{
		RolesAndUserOperations.findCommonBossOfUsers();
		operations();
	}
	public static void operations()
	{
		System.out.println("\n1. Add Sub Role.\n2. Display Roles\n3. Delete Role\n4. Add User\n5. Display User");
		System.out.println("6. Display Users and Sub Users\n7. Delete User\n8. Number of users from top\n9. Height of role hierarchy\n10. Common Boss of Users");
		System.out.println("\nOperation to be performed : ");
		
		int operation = Utils.getInputFromConsole();
		
		switch(operation)
		{
			case 1:
					addSubRole();
					break;
			case 2:
					displayRoles();
					break;
			case 3:
					deleteRole();
					break;
			case 4:
					addUser();
					break;
			case 5:
					displayUser();
					break;
			case 6:
					displayUserAndSubUsers();
					break;
			case 7:
					deleteUser();
					break;
			case 8:
					numberOfUserFromTop();
					break;
			case 9:
					heightOfRoleHierarchy();
					break;
			case 10:
					commonBossOfUsers();
					break;
			default:
					System.out.println("wrong input.. plese enter correctly.");
					operations();
			
		}
	}
	public static void main(String[] args)
	{
		System.out.println("Enter root role name : ");
		String rootRoleName = Utils.getInputFromUser();
		Role role = new Role(rootRoleName,null);
		RolesAndUserOperations.roleMap.put(role.getName(), role);
		RolesAndUserOperations.setRootRole(role);
		operations();
	}

}
