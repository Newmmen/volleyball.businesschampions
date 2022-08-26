package ru.businesschampions.volleyball;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WebTests {

    @ValueSource(strings = {"Козлов Александр", "Ермошин Дмитрий"})
    @DisplayName("Проверка наличия игрока в волейбольной команде Альфа-Банк")
    @ParameterizedTest(name = "Проверка наличия игрока \"{0}\" в волейбольной команде Альфа-Банк")
    void isPlayerPresentInAlfaBank(String playerName) {
        open("https://volleyball.businesschampions.ru/");
        $("nav[id='main-menu'] a[title='Команды']").hover();
        $("div.teams-menu").shouldBe(Condition.visible);
        $("div.teams-menu").$(byText("Альфа-Банк")).click();
        $(byTagAndText("header", "Альфа-Банк")).shouldBe(Condition.visible);
        $("[class='ruler w100p text-center sort']").$(byText(playerName)).click();
        $(byTagAndText("header", playerName)).shouldBe(Condition.visible);

    }

    @CsvSource(value = {
            "Шемчук Петр, ГИЛС и НП",
            "Козлов Александр, Альфа-Банк"
    })
    @DisplayName("Проверка наличия указанного игрока в заданной волейбольной команде")
    @ParameterizedTest(name = "Проверка наличия игрока \"{0}\" в волейбольной команде \"{1}\"")
    void isPlayerPresentInTeam(String playerName, String teamName) {
        open("https://volleyball.businesschampions.ru/");
        $("nav[id='main-menu'] a[title='Команды']").hover();
        $("div.teams-menu").shouldBe(Condition.visible);
        $("div.teams-menu").$(byText(teamName)).click();
        $(byTagAndText("header", teamName)).shouldBe(Condition.visible);
        $("[class='ruler w100p text-center sort']").$(byText(playerName)).click();
        $(byTagAndText("header", playerName)).shouldBe(Condition.visible);

    }

    static Stream<Arguments> darePlayersInTeamList() {
        return Stream.of(
                Arguments.of("ГИЛС и НП", List.of("Шемчук Петр", "Крылов Вячеслав")),
                Arguments.of("Альфа-Банк", List.of("Козлов Александр", "Ермошин Дмитрий"))
        );
    }

    @MethodSource("darePlayersInTeamList")
    @DisplayName("Проверка наличия указанного списка игроков в заданной волейбольной команде")
    @ParameterizedTest(name = "Проверка наличия игроков \"{0}\" в волейбольной команде \"{1}\"")
    void arePlayersInTeamList(String lang, List<String> playersList) {

        open("https://volleyball.businesschampions.ru/");
        $("nav[id='main-menu'] a[title='Команды']").hover();
        $("div.teams-menu").shouldBe(Condition.visible);
        $("div.teams-menu").$(byText(lang)).click();
        $(byTagAndText("header", lang)).shouldBe(Condition.visible);
        $$("[class='ruler w100p text-center sort'] a[href*='players']").shouldHave(CollectionCondition.containExactTextsCaseSensitive(playersList));


    }

}
