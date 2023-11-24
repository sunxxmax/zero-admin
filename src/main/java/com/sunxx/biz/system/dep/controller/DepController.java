package com.sunxx.biz.system.dep.controller;

import com.sunxx.biz.system.dep.domain.Dep;
import com.sunxx.biz.system.dep.controller.bean.DepAdd;
import com.sunxx.biz.system.dep.service.DepService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/dep")
public class DepController {

    @Resource
    private DepService depService;



    public List<Dep> list() {
        return null;
    }

    /**
     * 新增
     *
     * @param param 入参
     */
    @PostMapping(value = "/add")
    public void add(@Valid DepAdd param) {
        depService.add(param.covert());
    }


}
