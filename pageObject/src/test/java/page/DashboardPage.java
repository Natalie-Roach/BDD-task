package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.*;
import static groovy.xml.dom.DOMCategory.text;

public class DashboardPage {
    private SelenideElement heading = $x("//*[@data-test-id=\"dashboard\"]");

    private SelenideElement firstCard = $x("//*[@data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
    private SelenideElement secondCard = $x("//*[@data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");
    private ElementsCollection depositButton = $$x("//*[@data-test-id=\"action-deposit\"]");
    private SelenideElement reloadButton = $x("//*[@data-test-id=\"action-reload\"]");
    private SelenideElement cancelButton = $x("//*[@data-test-id=\"action-cancel\"]");

    private static final ElementsCollection cards = $$x("//*[@class='list__item']//self::div");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public int getCardBalance(String id) {
        String cardBalance = cards.findBy(Condition.text(DataHelper.getCardInfo(id).getNumber().substring(16))).getText();
        return extractBalance(cardBalance);
    }

    private int extractBalance(String cardBalance) {
        val start = cardBalance.indexOf(balanceStart);
        val finish = cardBalance.indexOf(balanceFinish);
        val value = cardBalance.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void getMoneyTransferFromSecondToFirstCard() {
        depositButton.first().click();
    }

    public void getMoneyTransferFromFirstToSecondCard() {
        depositButton.last().click();
    }

    public void reloadBalance() {
        reloadButton.click();
    }

    public void cancelMoneyTransfer() {
        cancelButton.click();
    }
}