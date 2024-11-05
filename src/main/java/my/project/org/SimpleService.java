package my.project.org;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.infinispan.Cache;
import org.jboss.as.clustering.infinispan.cache.LazyCache;
import org.jboss.as.clustering.infinispan.manager.DefaultCacheContainer;
import org.jboss.logging.Logger;

@Stateless
public class SimpleService {

    private static Logger LOG = Logger.getLogger(SimpleService.class);

    @Resource(lookup = "java:jboss/infinispan/cache/simpleContainer/default")
    private LazyCache<String, String> cache;

    @Resource(lookup = "java:jboss/infinispan/container/simpleContainer")
    private DefaultCacheContainer container;

    @Inject
    @SimpleCache
    private Cache<String, String> simpleCache;

    public String sayHello(String user) {

        String cachedValue = cache.get(user);
        if (cachedValue == null) {
            cachedValue = "Hello " + user;
            cache.put(user, cachedValue);
        } else {
            return cachedValue + " from cache";
        }
        return cachedValue;
    }
}
