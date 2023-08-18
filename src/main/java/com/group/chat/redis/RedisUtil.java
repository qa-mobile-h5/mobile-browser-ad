package com.group.chat.redis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.chat.service.ReadAnswerGroupService;
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

     //   String listKey = "GroupIDList";  // zset, 1,2,3,4,5,6...

        Set<String> set = mStringRedisTemplate.opsForZSet().range(listKey, 0, -1);
    //    ["1", "2", "3", ...，"6"];

     //   mStringRedisTemplate.opsForZSet().add(listKey, "11", 11);

        List<Integer> result = new ArrayList<>();

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            int i = Integer.parseInt(value);
            result.add(i);
        }
        // 1 2 3 4 5 6
        return result;



      /*  ZSetOperations<String, String> zSetOperations = mStringRedisTemplate.opsForZSet();
        Set<String> stringSet = zSetOperations.reverseRange(key, 0, -1);
        List<Integer> intList = new ArrayList<>();
        for (String numStr : stringSet) {
            intList.add(Integer.parseInt(numStr));
        }*/
       // return intList;
    }

  //  public void cacheAnswerGroups(List<AnswerGroup> answerGroups) throws JsonProcessingException {
   //     for (AnswerGroup group : answerGroups) {
   //         String json = objectMapper.writeValueAsString(group);
  //          mStringRedisTemplate.opsForHash().put("answerGroups", group.getGroupID(), json);
  //      }
   // }

    public void cacheAnswerGroups(List<AnswerGroup> answerGroups) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
       /* String key1 = "AnswerGroup::1";
        String key2 = "AnswerGroup::2";
        String value1 = "{groupID: 1}";
        String value2 = "{groupID: 2}";
        map.put(key1, value1);
        map.put(key2, value2);*/

        ZSetOperations<String, String> zSetOperations = mStringRedisTemplate.opsForZSet();
        for (AnswerGroup group : answerGroups) {
         //   zSetOperations.add(String.valueOf(group.getGroupID()), String.valueOf(group), group.getGroupID());
        }


        for (AnswerGroup group: answerGroups) {
            String key = "AnswerGroup::" + group.getGroupID();
            Jackson2JsonRedisSerializer<AnswerGroup> serializer = new Jackson2JsonRedisSerializer<>(AnswerGroup.class);
            byte[] serializedValue = serializer.serialize(group);
            String value = new String(serializedValue);
            map.put(key, value);
        }
        mStringRedisTemplate.opsForValue().multiSet(map);

      //  String json = objectMapper.writeValueAsString(answerGroups);
      //  mStringRedisTemplate.opsForValue().set("answerGroups", json);
    }

   /* public List<AnswerGroup> getAnswerGroups(List<String> groupIDs) {
        List<AnswerGroup> result = new ArrayList<>();
        for (String num : groupIDs) {
            result.add(getAnswerGroup(num));
        }
        return result;
    }*/

   public static int prev,tot;

   public List<AnswerGroup> getAnswerGroups(Integer prev_group_id) {
        String listkey = "GroupIDList";
        List<Integer> idList = getDescendingIntList(listkey);

        List<String> keys = new ArrayList<>();
       // int prev_group_id = ReadAnswerGroupService.x;
        int index ;
        if (prev_group_id==0) {
            index=idList.size();
        }
        else {
            index = idList.indexOf(prev_group_id);
        }

        tot=0;

        for (int i=index; i>0; i--) {
            String key = "AnswerGroup::" + idList.get(i-1);
            keys.add(key);
            tot++;
            prev=i;
            if (tot==6) break;
        }
        if (tot==6) prev++;
        List<String> values = mStringRedisTemplate.opsForValue().multiGet(keys);
        List<AnswerGroup> result = new ArrayList<>();
        for (String value: values) {
            if (value==null) break;
            Jackson2JsonRedisSerializer<AnswerGroup> serializer = new Jackson2JsonRedisSerializer<>(AnswerGroup.class);
            byte[] serializedValue = value.getBytes();
            AnswerGroup group = serializer.deserialize(serializedValue);
            result.add(group);
        }

   /*   Integer.parseInt(str);  "123" -> 123
        Long.parseLong(str);
        Double.parseDouble(str);
        Boolean.parseBoolean(str);*/

        return result;
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
