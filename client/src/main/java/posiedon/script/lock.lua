local expire=tonumber(ARGV[2])
local ret=redis.call('set',KEYS[1],ARGV[1],'NX','PX',expire)
local strret=tostring(ret)
-- 用于查看结果，获取锁成功后返回随机结婚"table: 0x55d040f2edc0",否则返回false
redis.call('set','result',strret)
if strret == 'false' then
    return false
else
    return true
end