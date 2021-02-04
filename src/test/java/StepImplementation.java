import com.thoughtworks.gauge.Step;
import org.junit.Assert;

public class StepImplementation extends BaseTest {


    @Step("ToDo anasayfasina git")
    public void mainPage() {
        getUrl();
        System.out.println("Anasayfa yuklendi.");
    }

    @Step("<Element> butonuna tikla")
    public void clickButton(String element) {
        clickElement(element);
        System.out.println(element + " butonuna tiklandi");
    }

    @Step("<saniye> sn. bekle")
    public void waitSecond(int key) throws InterruptedException {
        Thread.sleep(key * 1000);
        System.out.println(key + " sn. beklendi.");
    }

    @Step("<input> a <inputValue> gir ve gonder")
    public void fillInput(String input, String key) {
        sendKeyElements(input, key);
        sendEnter(input);
        System.out.println(key + " gonderildi.");
    }

    @Step("<Element> kontrol et")
    public void checkStep(String element) {
        try {
            findElement(element);
            System.out.println(element + " kontrol edildi.");
        } catch (Exception e) {
            Assert.fail("Element bulunamadi.");
        }
    }

    @Step("<Element> bos kontrol et")
    public void emptyCheck(String element) {
        nonExist(element);
        System.out.println("Listenin bos oldugu kontrol edildi.");
        ;
    }

    @Step("<index> siradaki <elementi> kontrol et")
    public void checkStepList(int index, String element) {
        checkListElement(index, element);
        System.out.println(element + " liste elementi kontrol edildi.");
    }

    @Step("<index> siradaki <Element> elementine tikla")
    public void clickListItem(int index, String element) {
        clickListElement(index, element);
        System.out.println(element + " liste elementine tiklandi");
    }

    @Step("<element> yuklenmesini bekle")
    public void waitPage(String element) {
        waitUntilExist(element);
    }

    @Step("<0> siradaki <element> uzerine git")
    public void hooverItem(int index, String element) {
        hooverListElement(index, element);
    }
}
