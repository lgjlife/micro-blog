package com.microblog.user.service.bloom;

import io.rebloom.client.Client;

public class BloomService {

    private Client client;

    public void init(){

        client = new Client("localhost",6379);
    }
}
