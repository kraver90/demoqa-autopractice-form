package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutopracticeFormTest {

    @BeforeAll
    static void beforeAll(){
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

        //выбираем календарь и заполняем дд.мм.гг (Не получается победить день в datapicker)
//        $("#dateOfBirthInput").click();
//        $(".react-datepicker__month-select").selectOptionByValue("6");
//        $(".react-datepicker__year-select").selectOptionByValue("1990");
//        $(".react-datepicker__day").findElement(byText("18")).click();

        //Subjects (пока победить не получается)
//        $(".subjects-auto-complete__value-container subjects-auto-complete__value-container--is-multi").setValue("Arts").pressEnter();

        //Picture

        //Hobbies
        $(byText("Reading")).click();
        $(byText("Music")).click();

        //Address
        $("#currentAddress").setValue("Мой адрес не дом и не улица, мой адрес сегодня такой");

        //State and City
        $(byText("Select State")).click();
        $(byText("NCR")).click();
        $(byText("Select City")).click();
        $(byText("Delhi")).click();

        //Click Submit
        $(byText("Submit")).click();

        //Сравниваем полученное значение
        $(".table-responsive").shouldHave(text("Student Name Dmitriy Kochurov"), text("Student Email test@test.ru"),
                text("Mobile 9000431111"), text("Gender Male"), text("Date of Birth 27 October,2021"), text("Reading, Music"),
                text("Address Мой адрес не дом и не улица, мой адрес сегодня такой"), text("State and City NCR Delhi"));

    }
}
