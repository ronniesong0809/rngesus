package com.ronsong.rngesus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ronsong.rngesus.common.api.ApiResult;
import com.ronsong.rngesus.model.entity.Billboard;
import com.ronsong.rngesus.service.BillboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/billboard")
public class BillboardController extends BaseController {
    @Resource
    private BillboardService billboardService;

    @GetMapping("/show")
    public ApiResult<Billboard> getNotices() {
        List<Billboard> list = billboardService.list(new LambdaQueryWrapper<Billboard>().eq(Billboard::isShow, true));
        return ApiResult.success(list.get(list.size() - 1));
    }
}
