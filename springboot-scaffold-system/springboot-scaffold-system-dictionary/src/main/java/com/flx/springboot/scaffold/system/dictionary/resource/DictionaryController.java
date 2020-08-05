package com.flx.springboot.scaffold.system.dictionary.resource;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.system.dictionary.service.DictionaryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author Fenglixiong
 * @Create 2020/8/2 21:36
 * @Description
 **/
@Api(tags = "字典管理")
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping("/query")
    public ResultResponse query(@RequestBody HashMap<String,Object> query){
        try {
            return ResultResponse.success(dictionaryService.query(query));
        }catch (Exception e){
            return ResultResponse.error();
        }
    }

}
