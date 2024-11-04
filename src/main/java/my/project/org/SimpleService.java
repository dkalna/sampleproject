package my.project.org;

import jakarta.ejb.Stateless;
import org.jboss.logging.Logger;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;

@Stateless
public class SimpleService {

    private static Logger LOG = Logger.getLogger(SimpleService.class);

    @CacheResult
    public String sayHello(@CacheKey String name) {
        LOG.info(System.nanoTime());
        return "Hello " + name;
    }
}
