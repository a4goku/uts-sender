package uts.sender.service.spi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uts.sender.entity.TradeDetail;
import uts.sender.service.TradeDetailService;
import uts.sender.utils.GeneratorDataUtil;

@RestController
public class SenderTradeDetailController {
    private TradeDetailService tradeDetailService;

    @RequestMapping("/sender")
    public void sender(@RequestParam("count") Integer count) throws Exception{
        for(int i = 0; i < 10; i++){
            TradeDetail td = GeneratorDataUtil.generator();

            //拿到数据以后调用service
            tradeDetailService.sender(td);
        }
    }
}
