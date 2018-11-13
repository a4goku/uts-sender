package uts.sender.service.spi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uts.sender.entity.TradeDetail;
import uts.sender.service.TradeDetailService;
import uts.sender.utils.GeneratorDataUtil;

@RestController
public class SenderTradeDetailController {
    @Autowired
    private TradeDetailService tradeDetailService;

    @RequestMapping("/sender")
    public void sender(@RequestParam("count") Integer count) throws Exception{
        for(int i = 0; i < count; i++){
            TradeDetail td = GeneratorDataUtil.generator();

            //拿到数据以后调用service
            tradeDetailService.sender(td);
        }
    }
}
