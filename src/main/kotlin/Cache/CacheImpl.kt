package srangeldev.Cache

class CacheImpl<K,V>(
    private val capacidad: Int
): Cache<K, V> {
    override fun get(key: K): V? {
        TODO("Not yet implemented")
    }

    override fun put(key: K, value: V): V? {
        TODO("Not yet implemented")
    }

    override fun remove(key: K): V? {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun size(): Int {
        TODO("Not yet implemented")
    }

    override fun keys(): Set<K> {
        TODO("Not yet implemented")
    }

    override fun values(): Collection<V> {
        TODO("Not yet implemented")
    }

    override fun entries(): Set<Map.Entry<K, V>> {
        TODO("Not yet implemented")
    }
}