package com.pupilarena.model;

import java.util.List;

public class Groups {
	private int groupId,capacity;
	private String groupName;
	private List<Users> members;
	
	public Groups(){
		
	}
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<Users> getMembers() {
		return members;
	}
	public void setMembers(List<Users> members) {
		this.members = members;
	}
	
	
}
