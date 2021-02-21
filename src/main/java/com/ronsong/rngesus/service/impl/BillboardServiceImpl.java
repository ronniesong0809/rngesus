package com.ronsong.rngesus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ronsong.rngesus.mapper.BillboardMapper;
import com.ronsong.rngesus.model.entity.Billboard;
import com.ronsong.rngesus.service.BillboardService;
import org.springframework.stereotype.Service;

@Service
public class BillboardServiceImpl extends ServiceImpl<BillboardMapper, Billboard> implements BillboardService {
}
