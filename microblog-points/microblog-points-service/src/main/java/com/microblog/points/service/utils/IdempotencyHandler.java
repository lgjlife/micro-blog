package com.microblog.points.service.utils;

import java.util.concurrent.TimeUnit;

public interface IdempotencyHandler {

    boolean checkConsumption(long messageId, long time, TimeUnit unit);
    void reDelete(long messageId);
}
