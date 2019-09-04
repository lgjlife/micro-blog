
-- return 123;


--local curId = redis.call('incr',KEYS[2]);
--return curId;

-- KEY[1] 为原始的id

local curId = redis.call('get',KEYS[1]);
if(curId)
    then
       -- curId = 234;

    else
        curId = redis.call('incr',KEYS[2]);
        redis.call('set',KEYS[1],curId);
        redis.call('set',curId,KEYS[1]);
end;

return curId;

-- KEYS[3] ..
