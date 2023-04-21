import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideCardTest {

    public String localDate(int day) {
        LocalDate date = LocalDate.now();
        String plusDays = String.valueOf(LocalDate.now());
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldDeliveryCard() {
        open("http://localhost:9999/");
        $("[data-test-id='city' ] input").setValue("Новосибирск");
        $("[data-test-id='date'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(localDate(3));
        $("[data-test-id='name'] input").setValue("Примак Вячеслав");
        $("[data-test-id='phone'] input").setValue("+79998887771");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + localDate(3)), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }
}
