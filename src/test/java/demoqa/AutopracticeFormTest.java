package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutopracticeFormTest {

    @BeforeAll
    static void beforeAll(){
        //возможно ли записать в одну строчку? и сделать так чтобы сразу открывался браузер в нужном разрешении?
        Configuration.browser = "Firefox";
        Configuration.browserSize = "1920x1080";
    }


    @Test
    void fullFormTest(){
        //заходим на сайт demoqa
        open("https://demoqa.com/automation-practice-form");

        //заполняем основные поля
        $("#firstName").setValue("Dmitriy");
        $("#lastName").setValue("Kochurov");
        $("#userEmail").setValue("test@test.ru");
        $(byText("Male")).click();
        $("#userNumber").setValue("9000431111");
//        $("input[id=gender-radio-1]").click(); не срабатывает подобным образом потому что один элемент затемняет его

        //выбираем календарь и заполняем дд.мм.гг
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("6");
        $(".react-datepicker__year-select").selectOptionByValue("1990");
        $(".react-datepicker__month").findElement(byText("18")).click();

        //Subjects (пока победить не получается, пробовал и через различные классы и через xpath)
//        $x("//*[@class='subjects-auto-complete__value-container--is-multi']").setValue("Arts").pressEnter();

        //Picture
        $("input[id=uploadPicture]").uploadFromClasspath("image (6).png");

        //Hobbies (возможно написать в одну строчку во втором варианте?)
        $("#hobbiesWrapper").findElement(byText("Reading")).click();
        $("#hobbiesWrapper").findElement(byText("Music")).click();
//        $(byText("Reading")).click();
//        $(byText("Music")).click();

        //Address (на русском значения водились в поломанной кодировке, добавил в build.gradle запись)
        $("#currentAddress").setValue("Мой адрес не дом и не улица, мой адрес сегодня такой");

        //State and City (не совсем нравится примененное решение, не очень элегантно получается, но зато работает)
        $(byText("Select State")).click();
        $(byText("NCR")).click();
        $(byText("Select City")).click();
        $(byText("Delhi")).click();

        //Click Submit (как я понимаю вариант с id всегда предпочтительнее чем вариант с поиском текста?)
        $("#submit").click();
//        $(byText("Submit")).click();

        //Сравниваем полученное значение
        $(".table-responsive").shouldHave(text("Student Name Dmitriy Kochurov"), text("Student Email test@test.ru"),
                text("Mobile 9000431111"), text("Gender Male"), text("Date of Birth 18 July,1990"), text("Reading, Music"),
                text("Picture image (6).png"), text("Address Мой адрес не дом и не улица, мой адрес сегодня такой"),
                text("State and City NCR Delhi"));
    }
}
