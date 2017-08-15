package com.png.menu;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MenuMapper {
    public static MenuList getMenu() throws IOException{
        File jsonFile = new File("src/main/resources/menu.json");
        ObjectMapper mapper = new ObjectMapper();
        MenuList menuList = mapper.readValue(jsonFile,MenuList.class);
        System.out.println(mapper.writeValueAsString(menuList));
        return menuList;
    }
}
