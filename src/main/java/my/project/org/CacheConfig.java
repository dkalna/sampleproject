package my.project.org;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.infinispan.Cache;
import org.infinispan.cdi.embedded.ConfigureCache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class CacheConfig {

    @ConfigureCache("simpleCache")
    @SimpleCache
    @Produces
    public Configuration simpleCacheConfiguration() {
        return new ConfigurationBuilder()
                .memory()
                .maxCount(1000)
                .build();
    }

    /*@SimpleCache
    @Produces
    @ApplicationScoped
    public EmbeddedCacheManager defaultEmbeddedCacheManager() {
        return new DefaultCacheManager(
                new GlobalConfigurationBuilder().transport().defaultTransport().build());
    }*/
}
