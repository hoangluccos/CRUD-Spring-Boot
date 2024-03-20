package com.example.models;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	public static List<User> ls = new ArrayList<User>() ;
	
	public User findByUsername(String username) {
		for(User user : ls) {
			if(user.getUsername().equals(username))
				return user;
		}
		
		return null;
	}
	public int update(User user) {
		for(int i = 0; i < ls.size(); i++) {
			if(ls.get(i).getUsername().equals(user.getUsername())) {
				ls.set(i,user); 
				return i;
			}
		}
		return -1;
	}
	public int save(User user) {
		if(findByUsername(user.getUsername()) != null) {
			update(user);
		}
		else {
			ls.add(user);
		}
		return 1;
	}
	public int delete(User user) {
		User u = findByUsername(user.getUsername());
		if( u != null) {
			ls.remove(u);
			return 1;
		}
		return 0;
	}
	public List<User> getAll(){
		return ls;
	}
}
