redis.call('set', KEYS[1], ARGV[1])
redis.call('set', KEYS[2], ARGV[2])
local n1 = tonumber(redis.call('get', KEYS[1]))
local n2 = tonumber(redis.call('get', KEYS[2]))
if n1 > n2 then
    return 1
end 
if n1 == n2 then
    return 0
end
if n1 < n2 then 
    return 2
end