package com.back.recipe;

import com.back.recipe.recipe.RecipeEntity;
import com.back.recipe.recipe.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class RecipeRepositoryTest {

    @Autowired
    RecipeRepository recipeRepository;

    @Test
    void saveRecipeTest() {

        //GIVEN : 엽기떡볶이 데이터를 껍데기에 세팅한다.
        RecipeEntity recipeEntity = new RecipeEntity();

        recipeEntity.setTitle("테스트용 엽기떡볶이");
        recipeEntity.setVideoUrl("https://tiktok.com/tteokbokki");

        //WHEN : 택배 기사에게 저장을 지시하고, 저장된 결과를 받아온다.
        RecipeEntity savedRecipe = recipeRepository.save(recipeEntity);

        assertThat(savedRecipe.getTitle()).isEqualTo("테스트용 엽기떡볶이");

        assertThat(savedRecipe.getId()).isNotNull();
    }

    @Test
    void findRecipeTest() {
        //GIVEN
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setTitle("테스트용 닭가슴살");
        recipeEntity.setVideoUrl("https://tiktok.com/chicken");
        RecipeEntity savedRecipe = recipeRepository.save(recipeEntity);

        // WHEN
        RecipeEntity foundRecipe = recipeRepository.findById(savedRecipe.getId())
                .orElseThrow(() -> new IllegalArgumentException("레시피가 없습니다"));

        //THEN
        assertThat(foundRecipe.getTitle()).isEqualTo("테스트용 닭가슴살");
    }

    @Test
    void updateRecipeTest() {
        //GIVEN
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setTitle("테스트용 현미밥");
        recipeEntity.setVideoUrl("https://tiktok.com/rice");
        RecipeEntity savedRecipe = recipeRepository.save(recipeEntity);

        //WHEN
        savedRecipe.setTitle("테스트용 백미밥");
        RecipeEntity updatedRecipe = recipeRepository.findById(savedRecipe.getId())
                .orElseThrow(() -> new IllegalArgumentException ("몰라 이거 예외 맞는지"));

        //THEN
        assertThat(updatedRecipe.getTitle()).isEqualTo("테스트용 백미밥");
    }

    @Test
    void deleteRecipeTest() {
        //GIVEN
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setTitle("테스트용 닭다리살");
        recipeEntity.setVideoUrl("https://tiktok.com/chickenleg");
        RecipeEntity savedRecipe = recipeRepository.save(recipeEntity);

        //when
        recipeRepository.deleteById(savedRecipe.getId());

        //then
        assertThat(recipeRepository.findById(savedRecipe.getId())).isEmpty();
    }
}
