package page;

import data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class LoginPage {

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }

    public void invalidLogin() {
        $x("//div[contains (text(), 'Ошибка! ')]").shouldBe(Condition.visible);
    }
}