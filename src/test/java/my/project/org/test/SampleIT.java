package my.project.org.test;

import jakarta.inject.Inject;
import my.project.org.SimpleService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;

/**
 * Sample integration test: demonstrates how to create the WAR file using the ShrinkWrap API.
 *
 * Delete this file if no integration test is required.
 */
@ExtendWith(ArquillianExtension.class)
public class SampleIT {

    @Inject
    private SimpleService simpleService;

    /**
     * Creates the WAR file that is deployed to the server.
     *
     * @return WAR archive
     */
    @Deployment
    public static Archive<?> getWarArchive() {
        // Import the web archive that was created by Maven:
        File f = new File("./target/sampleproject.war");
        if (f.exists() == false) {
            throw new RuntimeException("File " + f.getAbsolutePath() + " does not exist.");
        }
        WebArchive war = ShrinkWrap.create(ZipImporter.class, "sampleproject.war").importFrom(f).as(WebArchive.class);

        // Add the package containing the test classes:
        war.addPackage("my.project.org.test");

        // Export the WAR file to examine it in case of problems:
        // war.as(ZipExporter.class).exportTo(new File("c:\\temp\\test.war"), true);

        return war;
    }

    /**
     * A sample test...
     */
    @Test
    public void test() {
        // This line will be written on the server console.
        Assertions.assertEquals("Hello test1", simpleService.sayHello("test1"));
        Assertions.assertEquals("Hello test2", simpleService.sayHello("test2"));
        Assertions.assertEquals("Hello test3", simpleService.sayHello("test3"));
        Assertions.assertEquals("Hello test1 from cache", simpleService.sayHello("test1"));
        Assertions.assertEquals("Hello test4", simpleService.sayHello("test4"));
        Assertions.assertEquals("Hello test2 from cache", simpleService.sayHello("test2"));
    }
}
