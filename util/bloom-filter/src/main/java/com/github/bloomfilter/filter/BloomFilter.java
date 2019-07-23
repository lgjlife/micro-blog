package com.github.bloomfilter.filter;

public interface BloomFilter {

    boolean add(final String key,final String value);

    boolean[] addMulti(final String key, final String... values);

    boolean exists(final String key, final String value);

    boolean[] existsMulti(final String key, final String... values);
}
