package com.group.chat.redis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;
import com.group.chat.entity.AnswerGroup;

import java.util.*;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate mStringRedisTemplate;
    private ObjectMapper objectMapper;
   // @Autowired
 //   private final RedisTemplate<String, AnswerGroup> redisTemplate;


    public RedisUtil(StringRedisTemplate stringRedisTemplate,ObjectMapper objectMapper) {
        this.mStringRedisTemplate = stringRedisTemplate;
        this.objectMapper = objectMapper;
     //   this.redisTemplate= redisTemplate;
    }

    public void cacheString(String key, String value) {
        mStringRedisTemplate.opsForValue().set(key, value);
    }

    public String getString(String key) {
        return mStringRedisTemplate.opsForValue().get(key);
    }

    public void cacheInt(String key, int value) {
        mStringRedisTemplate.opsForValue().set(key, String.valueOf(value));
    }

    public int getInt(String key) {
        String value = mStringRedisTemplate.opsForValue().get(key);
        return value != null ? Integer.parseInt(value) : 0;
    }

    public void cacheLong(String key, long value) {
        mStringRedisTemplate.opsForValue().set(key, String.valueOf(value));
    }

    public long getLong(String key) {
        String value = mStringRedisTemplate.opsForValue().get(key);
        return value != null ? Long.parseLong(value) : 0L;
    }


    public void cacheAnswerGroup(String groupID, AnswerGroup answerGroup) {
        String key = "AnswerGroup::" + answerGroup.getGroupID().toString();
        Jackson2JsonRedisSerializer<AnswerGroup> serializer = new Jackson2JsonRedisSerializer<>(AnswerGroup.class);
        byte[] serializedValue = serializer.serialize(answerGroup);
        String value = new String(serializedValue);
        mStringRedisTemplate.opsForValue().set(key, value);
       // String key = "answerGroup:" + answerGroup.getGroupID();
      //  mStringRedisTemplate.opsForValue().set(key, answerGroup.serialize());
    }

    public AnswerGroup getAnswerGroup(String groupID) {
        Jackson2JsonRedisSerializer<AnswerGroup> serializer = new Jackson2JsonRedisSerializer<>(AnswerGroup.class);
        String serializedValue = mStringRedisTemplate.opsForValue().get(groupID);
        if (serializedValue != null) {
            return serializer.deserialize(serializedValue.getBytes());
        }
        return null;
    }

    public void cacheDescendingIntList(String key, List<Integer> intList) {
        ZSetOperations<String, String> zSetOperations = mStringRedisTemplate.opsForZSet();
        for (Integer num : intList) {
            zSetOperations.add(key, String.valueOf(num), num);
        }
    }

    public List<Integer> getDescendingIntList(String listKey) {

        String listKey = "GroupIDList";  // zset, 1,2,3,4,5,6...
        [
        {
            "value": "1",
            "score": 1
        }
        {
            "value": "2",
            "score": 2,
        }
        ...
        {
            "value": "6",
            "score": 6
        }
        ]


        Set<String> set = mStringRedisTemplate.opsForZSet().range(listKey, 0, -1);
        ["1", "2", "3", ...，"6"];


        mStringRedisTemplate.opsForZSet().add(listKey, "11", 11);


        List<Integer> result = new ArrayList<>();

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            int i = Integer.parseInt(value);
            result.add(i);
        }
        // 1 2 3 4 5 6
        return result;



        ZSetOperations<String, String> zSetOperations = mStringRedisTemplate.opsForZSet();
        Set<String> stringSet = zSetOperations.reverseRange(key, 0, -1);
        List<Integer> intList = new ArrayList<>();
        for (String numStr : stringSet) {
            intList.add(Integer.parseInt(numStr));
        }
        return intList;
    }

  //  public void cacheAnswerGroups(List<AnswerGroup> answerGroups) throws JsonProcessingException {
   //     for (AnswerGroup group : answerGroups) {
   //         String json = objectMapper.writeValueAsString(group);
  //          mStringRedisTemplate.opsForHash().put("answerGroups", group.getGroupID(), json);
  //      }
   // }

    public void cacheAnswerGroups(List<AnswerGroup> answerGroups) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        String key1 = "AnswerGroup::1";
        String key2 = "AnswerGroup::2";
        String value1 = "{groupID: 1}";
        String value2 = "{groupID: 2}";
        map.put(key1, value1);
        map.put(key2, value2);
        mStringRedisTemplate.opsForValue().multiSet(map);

        for (AnswerGroup group: answerGroups) {
            String key = "AnswerGroup::" + group.getGroupID();
            String value = jackson();
            map.put(key, value);
        }

        mStringRedisTemplate.opsForValue().multiSet(map);

        String json = objectMapper.writeValueAsString(answerGroups);
        mStringRedisTemplate.opsForValue().set("answerGroups", json);
    }

 //   public Map<String, AnswerGroup> getAnswerGroups(List<String> groupIds) throws JsonProcessingException {
  //      Map<String, AnswerGroup> resultMap = new HashMap<>();
   //     for (String groupId : groupIds) {
   //         String json = mStringRedisTemplate.opsForValue().get(groupId);
   //         if (json != null) {
   //             AnswerGroup answerGroup = objectMapper.readValue(json, AnswerGroup.class);
  //              resultMap.put(groupId, answerGroup);
  //          }
  //      }
   //     return resultMap;
  //  }

    public JSONArray getAnswerGroups(List<String> groupIDs) {
        String json = mStringRedisTemplate.opsForValue().get(groupIDs);
        JSONArray resultMap = new JSONArray();
        if (json != null) {
            try {
                List<AnswerGroup> answerGroups = objectMapper.readValue(json, new TypeReference<List<AnswerGroup>>() {});
                resultMap.put(answerGroups);
            } catch (JsonProcessingException e) {
                // Handle exception
            }
        }
        return new JSONArray();
    }


    public JSONArray getAnswerGroups() {
        String listkey = "GroupIDList";
        List<Integer> idList = getDescendingIntList(listkey);

        List<String> keys = new ArrayList<>();
        for (int id: idList) {
            String key = "AnswerGroup::" + id;
            keys.add(key);
        }
        List<String> values = mStringRedisTemplate.opsForValue().multiGet(keys);

        List<AnswerGroup> result = new ArrayList<>();
        for (String value: values) {
            AnswerGroup group = 反序列化(value);
            result.add(group);
        }

        Integer.parseInt(str);  "123" -> 123
        Long.parseLong(str);
        Double.parseDouble(str);
        Boolean.parseBoolean(str);

        return result;


        String json = mStringRedisTemplate.opsForValue().get(groupIDs);
        JSONArray resultMap = new JSONArray();
        if (json != null) {
            try {
                List<AnswerGroup> answerGroups = objectMapper.readValue(json, new TypeReference<List<AnswerGroup>>() {});
                resultMap.put(answerGroups);
            } catch (JsonProcessingException e) {
                // Handle exception
            }
        }
        return new JSONArray();
    }


    /**
     * 判断当前Redis缓存中是否有Key
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        Boolean has = mStringRedisTemplate.hasKey(key);
        return has != null && has;
    }
}
