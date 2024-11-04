package my.project.org;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.infinispan.Cache;
import org.jboss.logging.Logger;

@Stateless
public class SimpleService {

    private static Logger LOG = Logger.getLogger(SimpleService.class);

    /*@Resource(lookup = "java:jboss/infinispan/cache/simpleContainer/default")
    private Cache<String, String> simpleCache;*/

    @Inject
    @SimpleCache
    private Cache<String, String> cache;

    public String sayHello(String user) {

        String cachedValue = cache.get(user);
        if (cachedValue == null) {
            cachedValue = "Hello " + user;
            cache.put(user, cachedValue);
        } else {
            return cachedValue + " from Cache";
        }
        return cachedValue;
    }
}
