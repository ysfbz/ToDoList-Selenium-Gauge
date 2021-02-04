package helper;

import model.ElementInfo;
import org.openqa.selenium.By;

public class ElementHelper
{

  public static By getElementInfoToBy(ElementInfo elementInfo) {
    By by = null;
    if (elementInfo.getType().equals("css")) {
      by = By.cssSelector(elementInfo.getValue());
    } else if (elementInfo.getType().equals("id")) {
      by = By.id(elementInfo.getValue());
    }else if (elementInfo.getType().equals("xpath")) {
      by = By.xpath(elementInfo.getValue());
    }else if (elementInfo.getType().equals("linkText")) {
      by = By.partialLinkText(elementInfo.getValue());
    }
    return by;
  }
}