import org.junit.jupiter.api.BeforeEach
import srangeldev.Cache.CacheImpl
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.*

class CacheImplTest {
    private lateinit var cache: CacheImpl<String, String>

    @BeforeEach
    fun setUp() {
        cache = CacheImpl()
    }

    @Test
    fun get() {
        cache.put("k1", "v1")
        cache.put("k2", "v2")
        cache.put("k3", "v3")
        cache.put("k4", "v4")
        cache.put("k5", "v5")

        assertNotNull(cache.get("k4"))

        cache.put("k6", "v6")

        assertNull(cache.get("k1"))
        assertNotNull(cache.get("k2"))
        assertNotNull(cache.get("k3"))
        assertNotNull(cache.get("k4"))
        assertNotNull(cache.get("k5"))
        assertNotNull(cache.get("k6"))
    }

    @Test
    fun remove() {
        cache.put("k1", "v1")
        cache.put("k2", "v2")

        cache.remove("k1")
        assertNull(cache.get("k1"))
        assertNotNull(cache.get("k2"))
    }

    @Test
    fun put() {
        cache.put("k1", "v1")
        assertEquals("v1", cache.get("k1"))

        cache.put("k1", "v2")
        assertEquals("v2", cache.get("k1"))
    }

    @Test
    fun keys() {
        cache.put("k1", "v1")
        cache.put("k2", "v2")
        cache.put("k3", "v3")

        val keys = cache.keys()
        assertTrue(keys.contains("k1"))
        assertTrue(keys.contains("k2"))
        assertTrue(keys.contains("k3"))
    }

    @Test
    fun values() {
        cache.put("k1", "v1")
        cache.put("k2", "v2")
        cache.put("k3", "v3")

        val values = cache.values()
        assertTrue(values.contains("v1"))
        assertTrue(values.contains("v2"))
        assertTrue(values.contains("v3"))
    }

    @Test
    fun clear() {
        cache.put("k1", "v1")
        cache.put("k2", "v2")

        cache.clear()
        assertNull(cache.get("k1"))
        assertNull(cache.get("k2"))
        assertEquals(0, cache.size())
    }

    @Test
    fun size() {
        assertEquals(0, cache.size())

        cache.put("k1", "v1")
        assertEquals(1, cache.size())

        cache.put("k2", "v2")
        assertEquals(2, cache.size())
    }

    @Test
    fun entries() {
        cache.put("k1", "v1")
        cache.put("k2", "v2")

        val entries = cache.entries()
        assertEquals(2, entries.size)
    }
}
