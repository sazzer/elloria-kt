package uk.co.grahamcox.elloria.cucumber.pages

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.NoSuchElementException

/**
 * Find an element inside the given base element, waiting and retrying a number of times until we succeed
 * @param selector The selector to use
 * @return the found element
 */
fun SearchContext.eventuallyFindElement(selector: By) : WebElement {
    var counter: Int = 0;

    while (counter < 5) {
        try {
            return this.findElement(selector)
        } catch (e: NoSuchElementException) {
            Thread.sleep(100)
            counter++
        }
    }

    throw NoSuchElementException("Failed to find element: ${selector}")
}

/**
 * Wait until the element is visible and then execute the callback. If the element isn't visible after a while then
 * give up and fail
 */
fun WebElement.whenDisplayed(callback: (WebElement) -> Unit) {
    var counter: Int = 0;

    while (counter < 5) {
        if (this.isDisplayed()) {
            callback(this)
            return
        } else {
            Thread.sleep(100)
            counter++
        }
    }

    throw NoSuchElementException("Element never became visible")
}