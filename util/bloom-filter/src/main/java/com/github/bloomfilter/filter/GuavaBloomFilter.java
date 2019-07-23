package com.github.bloomfilter.filter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

public class GuavaBloomFilter implements BloomFilterBase {

    private BloomFilter bloomFilter = BloomFilter.create(Funnels.stringFunnel( Charset.defaultCharset()),1000000);

    @Override
    public boolean add(String key, String value) {
        return bloomFilter.put(key+value);
    }

    @Override
    public boolean[] addMulti(String key, String... values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean exists(String key, String value) {
        return bloomFilter.mightContain(key+value);
    }

    @Override
    public boolean[] existsMulti(String key, String... values) {
        throw new UnsupportedOperationException();
    }
}
