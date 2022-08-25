package ru.businesschampions.volleyball;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebTests {

    @DisplayName("Проверка наличия игрока в волейбольной команде")
    @Test
    void isPlayerPresentInTeam(){
        open("https://volleyball.businesschampions.ru/");
        $("nav[id='main-menu'] a[title='Команды']").hover();
        $("div.teams-menu").shouldBe(Condition.visible);
        $("div.teams-menu").$(byText("ГИЛС и НП")).click();
        int r = 1;
        $("section header").$(byText("ГИЛС и НП")).shouldBe(Condition.visible);



    }

}
