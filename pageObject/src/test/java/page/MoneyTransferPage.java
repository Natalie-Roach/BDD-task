package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import static com.codeborne.selenide.Selenide.$x;
import java.time.Duration;

public class MoneyTransferPage {
    private SelenideElement transferAmount = $x("//*[@data-test-id=\"amount\"]//self::input");
    private SelenideElement fromField = $x("//*[@data-test-id=\"from\"]//self::input");
    private SelenideElement transferButton = $x("//*[@data-test-id=\"action-transfer\"]");
    private SelenideElement errorMessage = $x("//*[@data-test-id=\"error-notification\"]");

    public DashboardPage moneyTransfer(DataHelper.CardInfo from, String amountToTransfer) {
        transferAmount.setValue(amountToTransfer);
        fromField.setValue(from.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public void getError() {
        errorMessage.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

}