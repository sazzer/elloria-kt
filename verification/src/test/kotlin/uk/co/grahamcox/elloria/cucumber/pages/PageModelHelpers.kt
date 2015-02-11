package uk.co.grahamcox.elloria.cucumber.pages

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.NoSuchElementException

/**
 * Find an element inside the given base element, waiting and retrying a number of times until we succeed
 * @param base The base element to look inside
 * @param selector The selector to use
 * @return the found element
 */
fun eventuallyFindElement(base: SearchContext, selector: By) : WebElement {
    var result: WebElement? = null
    var counter: Int = 0;

    while (counter < 5) {
        try {
            return base.findElement(selector)
        } catch (e: NoSuchElementException) {
            Thread.sleep(100)
            counter++
        }
    }

    throw NoSuchElementException("Failed to find element: ${selector}")
}