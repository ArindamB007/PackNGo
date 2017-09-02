package com.png.menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.png.data.entity.Role;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MenuMapper {
    public static Collection<Menu> getMenu(Set<Role> roleSet) throws IOException{
        Collection<Menu> menuUnedited;
        Collection<Menu> menuEdited = new ArrayList<Menu>();
        Set <String> actualRoles = new HashSet<>();
        File jsonFile = new File("src/main/resources/menu.json");
        ObjectMapper mapper = new ObjectMapper();
        MenuList menuList = mapper.readValue(jsonFile,MenuList.class);
        System.out.println(mapper.writeValueAsString(menuList));
        if (roleSet == null|| roleSet.isEmpty()){ //if user is not logged in
            return menuList.menusDefault;
        }
        for (Role role:roleSet) {
            actualRoles.add(role.getName());
        }
        if (actualRoles.contains("ROLE_ADMIN")){
        	menuUnedited = menuList.menusUser; // change to menuAdmin
        }
        else if (actualRoles.contains("ROLE_PROPERTY_ADMIN")) {
        	menuUnedited = menuList.menusUser; // change to menuPropertyAdmin
        }
        else
        	menuUnedited = menuList.menusUser;
        for(Menu menu:menuUnedited){
        	if (menu.getMenuLabel().equals("Account")){
        		menu.setMenuLabel("My Account");
        	}
        	menuEdited.add(menu);
        	
        }
        return menuEdited;

    }
}
