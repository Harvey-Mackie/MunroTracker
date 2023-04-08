package com.munro.api.integration;

import com.munro.api.ApiApplication;
import com.munro.api.model.domain.MunroEntity;
import com.munro.api.model.domain.UserEntity;
import com.munro.api.repository.MunroRepository;
import com.munro.api.repository.UserRepository;
import com.munro.api.service.MunroService;
import com.munro.api.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ApiApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties"
)
class MunroTests {

    //Properties required for testing - assigned in BeforeEach method.
    private MunroEntity selectedMunro;
    private UserEntity user1;
    private UserEntity user2;

    //Dependencies injected.
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private MunroService munroService;

    @Autowired
    private MunroRepository munroRepository;

    @BeforeEach
    void setUpUsersAndMunros(){
        user1= userRepository.save(new UserEntity("Harvey", "Harvey@mailinator.com"));
        user2 = userRepository.save(new UserEntity("Steve", "Steve@mailinator.com"));

        userService.followUser(user1.getId(), user2.getId());
        munroRepository.save(new MunroEntity("Ben Nevis", 10, 10, 10, "Scotland", "N/A"));
        selectedMunro = munroRepository.findAll().stream().findFirst().get();
    }

    @Test
    void shouldCompleteMunro(){
        munroService.SetMunroToComplete(user2.getId(), selectedMunro.getId(), LocalDate.now());
        var munroCollection = munroService.getMunros(user2.getId());

        var munro = munroCollection.stream().findFirst().get();

        Assert.assertEquals("Ben Nevis", munro.getName());
        Assert.assertTrue(munro.isCompleted());
    }

    @Test
    void shouldShowCompletedMunrosOnFollowersFeed(){
        munroService.SetMunroToComplete(user2.getId(), selectedMunro.getId(), LocalDate.now());

        var feed = munroService.feed(user1.getId());

        Assert.assertEquals(1, feed.size());
        Assertions.assertTrue(feed.stream().findFirst().get().isCompleted());
    }

    @Test
    void shouldAllowUserToPostCommentOnCompletedMunro(){
        var selectedMunroPostingId = munroService.SetMunroToComplete(user2.getId(), selectedMunro.getId(), LocalDate.now());

        munroService.postComment(selectedMunroPostingId, user1.getId(), "Test");

        var munro = munroService.getMunroCompleted(selectedMunroPostingId);

        var munroComments = munro.getMunroCompletedCommentDtoList();

        Assertions.assertNotNull(munroComments);
        Assertions.assertEquals(1, munroComments.size());

        var firstComment = munroComments.stream().findFirst().get();
        Assertions.assertSame("Test", firstComment.getComment());
        Assertions.assertSame(firstComment.getUserName(), user1.getName());
    }

    @Test
    void shouldAllowUserToKudosACompletedMunro(){
        var selectedMunroPostingId = munroService.SetMunroToComplete(user2.getId(), selectedMunro.getId(), LocalDate.now());

        munroService.giveKudos(selectedMunroPostingId, user1.getId());
        var munro = munroService.getMunroCompleted(selectedMunroPostingId);
        var munroKudos = munro.getMunroCompletedKudosDtoList();

        Assertions.assertNotNull(munroKudos);
        Assertions.assertEquals(1, munroKudos.size());
        Assertions.assertSame(munroKudos.stream().findFirst().get().getUserName(), user1.getName());
    }

    @Test
    void shouldReturnWeather(){
        var weatherEntities = munroService.getWeatherForecast(selectedMunro.getId());
        Assert.assertNotNull(weatherEntities);
    }
}
