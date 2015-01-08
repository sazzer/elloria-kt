package uk.co.grahamcox.elloria.webapp

import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.format.DateTimeFormatter
import org.junit.Test

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Integration test for the Debug Controller
 */
class DebugControllerIntegrationTest : ControllerTestBase() {
    /**
     * Test the request to get the current time
     * @throws Exception never
     */
    [Test]
    fun getNow() {
        perform(get("/api/debug/now"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith("text/plain"))
            .andExpect(content().string("2015-01-08T12:40:00Z[UTC]"))
    }

    /**
     * Test the request to get the build info
     * @throws Exception never
     */
    [Test]
    fun getBuildInfo() {
        perform(get("/api/debug/buildInfo"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andExpect(jsonPath("$.['project.name']").value("Elloria - Main Webapp"))
            .andExpect(jsonPath("$.['project.version']").value("1.0-SNAPSHOT"))
            .andExpect(jsonPath("$.['build.time']").value("8 January 2015, 12:26:58 +00:00"))
            .andExpect(jsonPath("$.['git.revision']").value("69f8dec752d14091bfa856e93f8056f36a8495a9"))
    }

}