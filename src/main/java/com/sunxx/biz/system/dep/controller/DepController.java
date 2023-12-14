package com.sunxx.biz.system.dep.controller;

import com.sunxx.biz.system.dep.domain.Dep;
import com.sunxx.biz.system.dep.controller.bean.DepAdd;
import com.sunxx.biz.system.dep.service.DepService;
import com.sunxx.biz.system.role.domain.Role;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
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

    /**
     * 分页查询
     */
    @GetMapping(value = "/page")
    public Page<Dep> page(Pageable pageable) {
        return depService.page(pageable);
    }

    public List<Dep> list() {
        return null;
    }

    /**
     * 新增
     *
     * @param param 入参
     */
    @PostMapping(value = "/add")
    public void create(@Valid DepAdd param) {
        depService.create(param.covert());
    }
}
