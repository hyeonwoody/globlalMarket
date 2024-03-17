package com.toyproject.globalMarket.repository;

import com.toyproject.globalMarket.DTO.category.CategoryNaverDTO;
import com.toyproject.globalMarket.entity.CategoryNaverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryNaverEntity, Long> {

    @Query("SELECT u.category_naver_id FROM CategoryNaverEntity u WHERE u.name = :name")
    default Long findIdByName(@Param("name") String lastCategory){
        FileReader fileReader = null;
        List<CategoryNaverDTO> categorynamverDTOList = new ArrayList<>();
        try {
            fileReader = new FileReader(System.getProperty("user.dir") + "/src/main/resources/categoryNaver.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(fileReader);
            jsonNode = jsonNode.get("category_naver");
            for (JsonNode element :jsonNode){
                if (lastCategory.equals(element.get("name"))){
                    return element.get("category_naver_id").asLong();
                }
            }
        }
        catch (IOException e) {
            // Handle the IOException here
            // You can log the error, display a message to the user, or exit the program
            System.err.println("Error reading file: " + e.getMessage());
        }
        return null;
    }

    @Query("SELECT u.category_naver_id FROM CategoryNaverEntity u WHERE u.whole_category_name = :whole_category_name")
    default Long findIdByWhole_category_name(@Param("whole_category_name") String wholeName){
        FileReader fileReader = null;
        List<CategoryNaverDTO> categorynamverDTOList = new ArrayList<>();
        try {
            fileReader = new FileReader(System.getProperty("user.dir") + "/src/main/resources/categoryNaver.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(fileReader);
            jsonNode = jsonNode.get("category_naver");
            for (JsonNode element :jsonNode){
                if (wholeName.equals(element.get("whole_category_name").asText())){
                    return element.get("category_naver_id").asLong();
                }
            }
        }
        catch (IOException e) {
            // Handle the IOException here
            // You can log the error, display a message to the user, or exit the program
            System.err.println("Error reading file: " + e.getMessage());
        }
        return null;
    }

    @Query("SELECT u.category_naver_id FROM CategoryNaverEntity u WHERE u.name = :name AND u.last = TRUE")
    default Long findLastIdByName (@Param("name") String lastCategory){
        FileReader fileReader = null;
        List<CategoryNaverDTO> categorynamverDTOList = new ArrayList<>();
        try {
            fileReader = new FileReader(System.getProperty("user.dir") + "/src/main/resources/categoryNaver.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(fileReader);
            jsonNode = jsonNode.get("category_naver");
            for (JsonNode element :jsonNode){
                if (lastCategory.equals(element.get("name").asText()) && (element.get("last").asBoolean())){
                    return element.get("category_naver_id").asLong();
                }
            }
        }
        catch (IOException e) {
            // Handle the IOException here
            // You can log the error, display a message to the user, or exit the program
            System.err.println("Error reading file: " + e.getMessage());
        }
        return null;
    }

    @Query("SELECT u.name FROM CategoryNaverEntity u WHERE u.category_naver_id = :category_naver_id")
    default String findNameByCategory_naver_id(@Param("category_naver_id") Long id){
        FileReader fileReader = null;
        List<CategoryNaverDTO> categorynamverDTOList = new ArrayList<>();
        try {
            fileReader = new FileReader(System.getProperty("user.dir") + "/src/main/resources/categoryNaver.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(fileReader);
            jsonNode = jsonNode.get("category_naver");
            for (JsonNode element :jsonNode){
                if (id == element.get("category_naver_id").asLong()){
                    return element.get("name").asText();
                }
            }
        }
        catch (IOException e) {
            // Handle the IOException here
            // You can log the error, display a message to the user, or exit the program
            System.err.println("Error reading file: " + e.getMessage());
        }
        return null;
    }

    default List<CategoryNaverDTO> getCategoryNaverList () {
        FileReader fileReader = null;
        List<CategoryNaverDTO> categorynaverDTOList = new ArrayList<>();
        try {
            fileReader = new FileReader(System.getProperty("user.dir") + "/src/main/resources/categoryNaver.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(fileReader);
            jsonNode = jsonNode.get("category_naver");
            for (JsonNode element :jsonNode){
                CategoryNaverDTO category = new CategoryNaverDTO();
                category.setLast(element.get("last").asBoolean());
                category.setId(element.get("category_naver_id").asText());
                category.setName(element.get("name").asText());
                category.setWholeCategoryName(element.get("whole_category_name").asText());


                categorynaverDTOList.add(category);
            }
        }
        catch (IOException e) {
            // Handle the IOException here
            // You can log the error, display a message to the user, or exit the program
            System.err.println("Error reading file: " + e.getMessage());
        }
        return categorynaverDTOList;
    }
    default void APItoSave(List<CategoryNaverDTO> cateogryNaverDTOList){
//        for (CategoryNaverDTO categoryNaverDTO : cateogryNaverDTOList){
//            CategoryNaverEntity categoryNaverEntity = new CategoryNaverEntity();
//            categoryNaverEntity.setWhole_category_name(categoryNaverDTO.getWholeCategoryName());
//            categoryNaverEntity.setName(categoryNaverDTO.getName());
//            categoryNaverEntity.setLast(categoryNaverDTO.isLast());
//            categoryNaverEntity.setCategory_naver_id (Long.parseLong(categoryNaverDTO.getId()));
//            save(categoryNaverEntity);
//        }
    }
}