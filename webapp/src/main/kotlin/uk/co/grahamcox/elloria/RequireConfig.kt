package uk.co.grahamcox.elloria

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.util.Properties
import org.springframework.core.io.Resource
import com.google.common.base.CaseFormat

/**
 * Representation of a Dependency
 * @param artifactId The Artifact ID
 * @param version The version
 */
data class Dependency(val artifactId: String, val version: String) {
    /**
     * Generate the name of the RequireJS module. This is the artifact ID, in Camel Case
     * @return the name of the RequireJS module
     */
    fun moduleName() = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, artifactId.replace("-", "_"))
    /**
     * Generate the path to the RequireJS module. This is currently always a WebJar path
     * @return the path to the module
     */
    fun modulePath() = "/webjars/${artifactId}/${version}/${artifactId}"
}

/**
 * Controller to get RequireJS configuration from
 * @param dependenciesResource The file containing all of the dependencies, from which we can calculate what we need to know
 */
[Controller]
class RequireConfigController(private val dependenciesResource: Resource) {
    /** The actual RequireJS module configuration */
    private val modules: Map<String, String>

    {
        val dependencies = Properties()
        dependencies.load(dependenciesResource.getInputStream())

        modules = dependencies.filter { input ->
            input.key.toString().startsWith("org.webjars/") && input.key.toString().endsWith("/version")
        }.map { input ->
            val key = input.key.toString()
            val version = input.value.toString()
            val parts = key.split("/")
            Dependency(parts[1], version)
        }.map { input ->
            input.moduleName() to input.modulePath()
        }.toMap()
    }

    /**
     * Build the Javascript configuration for RequireJS to work
     * @return the view name
     */
    [RequestMapping(value = array("/requireConfig.js"))]
    fun getRequireJsConfig(): ModelAndView {
        val mav = ModelAndView("/requireConfig")
        mav.addObject("modules", modules)
        return mav
    }
}