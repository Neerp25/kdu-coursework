package com.example.demo.Configuration;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListenerAdapter;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class Cacheconfig {
    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager() {
        net.sf.ehcache.config.CacheConfiguration geocoding = new net.sf.ehcache.config.CacheConfiguration();
        geocoding.setName("geocoding");
        geocoding.setMemoryStoreEvictionPolicy("LRU");
        geocoding.setMaxEntriesLocalHeap(10);
        geocoding.setEternal(false);
        geocoding.internalSetTimeToLive(600);

        net.sf.ehcache.config.CacheConfiguration revGeocoding = new net.sf.ehcache.config.CacheConfiguration();
        revGeocoding.setName("reverse-geocoding");
        revGeocoding.setMemoryStoreEvictionPolicy("LRU");
        revGeocoding.setMaxEntriesLocalHeap(10);
        revGeocoding.setEternal(false);
        revGeocoding.internalSetTimeToLive(600);

        net.sf.ehcache.config.Configuration configs = new net.sf.ehcache.config.Configuration();
        configs.addCache(geocoding);
        configs.addCache(revGeocoding);

        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.newInstance(configs);

        cacheManager.getCache("geocoding").getCacheEventNotificationService().registerListener(new CustomCacheEventListener());
        cacheManager.getCache("reverse-geocoding").getCacheEventNotificationService().registerListener(new CustomCacheEventListener());

        return cacheManager;
    }

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager()).getCacheManager();

    }

    private static class CustomCacheEventListener extends CacheEventListenerAdapter {
        @Override
        public void notifyElementRemoved(Ehcache ehcache, Element element) throws CacheException {
            log.info("Cache Element Removed: {} \n", element.getObjectKey());
        }

        @Override
        public void notifyElementPut(Ehcache ehcache, Element element) throws CacheException {
            log.info("Cache Element Put: {} - {} \n", element.getObjectKey(), element.getObjectValue());
        }

        @Override
        public void notifyElementUpdated(Ehcache ehcache, Element element) throws CacheException {
            log.info("Cache Element Updated: {} - {} \n", element.getObjectKey(), element.getObjectValue());
        }

        @Override
        public void notifyElementExpired(Ehcache ehcache, Element element) {
            log.info("Cache Element Expired: {} \n", element.getObjectKey());
        }

        @Override
        public void notifyElementEvicted(Ehcache ehcache, Element element) {
            log.info("Cache Element Evicted: {} \n", element.getObjectKey());
        }

        @Override
        public void notifyRemoveAll(Ehcache ehcache) {
            log.info("Cache All Elements Removed \n");
        }
    }
}
