package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDelivery {

    String generateDate(int days){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    @Test
    void cardDeliveryFormValid(){
        String date = generateDate(4);
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Москва");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+79211234567");
        $("[data-test-id='agreement']").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Успешно")).shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

}
