package rs.webprogramming.homework_vi.main

import org.apache.catalina.WebResourceRoot
import org.apache.catalina.core.StandardContext
import org.apache.catalina.startup.Tomcat
import org.apache.catalina.webresources.DirResourceSet
import org.apache.catalina.webresources.StandardRoot
import rs.app.povezi.util.Logger
import java.io.File

fun main(args: Array<String>) {
    Logger.logInfo("App starting...")

    val webappDirLocation = "src/main/webapp/"
    val tomcat = Tomcat()
    tomcat.setPort(8080)

    val ctx = tomcat.addWebapp("/", File(webappDirLocation).absolutePath) as StandardContext

    val additionWebInfClasses = File("target/classes")
    val resources: WebResourceRoot = StandardRoot(ctx)
    resources.addPreResources(DirResourceSet(resources, "/WEB-INF/classes",
            additionWebInfClasses.absolutePath, "/"))
    ctx.resources = resources

    tomcat.addUser("pavle", "pass")
    tomcat.addRole("pavle", "user")

    tomcat.start()
    tomcat.server.await()
}