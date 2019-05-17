package com.flx.springboot.scaffold.test;

import com.alibaba.fastjson.JSONObject;
import com.flx.springboot.scaffold.common.utils.json.JsonParser;
import com.flx.springboot.scaffold.test.entity.Student;
import com.flx.springboot.scaffold.test.entity.Work;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author Fenglixiong
 * @Create 2018.12.04 10:34
 * @Description
 **/
public class JsonParserTest {

    public static void main(String[] args) {

        Student student = new Student();
        Student student1 = new Student();
        student.setId(1);
        student.setAge(22);
        student.setName("marry");

        student.setWork(new Work(1,"zuoye","lalala"));
        student1.setWork(new Work(3,"ssss","jjjjjj"));
        List<Work> works = new ArrayList<>();
        works.add(new Work(1,"zzz1","zzz1"));
        works.add(new Work(2,"zzz2","zzz2"));
        works.add(new Work(3,"zzz3","zzz3"));
        student.setWorks(works);
        Set<String> set = new HashSet<String>();
        set.add("sss1");
        set.add("sss2");
        set.add("sss3");
        student.setFriends(set);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("student1",student);
        jsonObject.put("foot","anta");
        jsonObject.put("hello","hh");
        jsonObject.put("student2",student1);
        String json = jsonObject.toJSONString();
        System.out.println(json);
        String result = JsonParser.readVal(json,"student1.works");
        System.out.println(result);

    }

}
