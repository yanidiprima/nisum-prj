package com.sinum.user.utils;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MockUtils {
	
	public static String convertToJsonString(Object object) throws Exception {
	    try {
	      return new ObjectMapper().writeValueAsString(object);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	  }

		private static String fileEntity = "entity.json";
	    private static String fileDto = "dto.json";

	    /**
	     *
	     * @param path
	     * @param clazz
	     * @param <T>
	     * @return
	     * @throws IOException
	     */
	    public static <T> T getMock(String path, Class<T> clazz) throws IOException {

	        ObjectMapper objectMapper = new ObjectMapper();
	        ClassPathResource classPathResource = new ClassPathResource(path);
	        return objectMapper.readValue(classPathResource.getInputStream(), clazz);
	    }

	    /**
	     *
	     * @param clazz
	     * @param <T>
	     * @return
	     * @throws IOException
	     */
	    public static <T> T getMockEntity(Class<T> clazz) throws IOException {

	        ObjectMapper objectMapper = new ObjectMapper();
	        ClassPathResource classPathResource = new ClassPathResource(fileEntity);
	        return objectMapper.readValue(classPathResource.getInputStream(), clazz);
	    }

	    /**
	     *
	     * @param clazz
	     * @param <T>
	     * @return
	     * @throws IOException
	     */
	    public static <T> T getMockDto(Class<T> clazz) throws IOException {

	        ObjectMapper objectMapper = new ObjectMapper();
	        ClassPathResource classPathResource = new ClassPathResource(fileDto);
	        return objectMapper.readValue(classPathResource.getInputStream(), clazz);
	    }
}
