package cache

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import srangeldev.Cache.CacheImpl

class CacheImplTest {
    private lateinit var lruCache: CacheImpl<String, String>

    @BeforeEach
    fun setUp() {
        lruCache = CacheImpl()
    }

    @Test
    fun get() {
        lruCache.put("key1", "value1")
        lruCache.put("key2", "value2")
        lruCache.put("key3", "value3")
        lruCache.put("key4", "value4")
        lruCache.put("key5", "value5")

        assertNotNull(lruCache.get("key4"))

        lruCache.put("key6", "value6")

        assertNull(lruCache.get("key1"))
        assertNotNull(lruCache.get("key2"))
        assertNotNull(lruCache.get("key3"))
        assertNotNull(lruCache.get("key4"))
        assertNotNull(lruCache.get("key5"))
        assertNotNull(lruCache.get("key6"))
    }

    @Test
    fun remove() {
    }

    @Test
    fun put() {
    }

    @Test
    fun keys() {
    }

    @Test
    fun values() {
    }

    @Test
    fun clear() {
    }

    @Test
    fun size() {
    }

    @Test
    fun entries() {
    }

}