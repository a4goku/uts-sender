package uts.sender.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import uts.sender.entity.TradeDetail;
import uts.sender.listener.ApplicationFactory;
import uts.sender.protocol.Resp;
import uts.sender.service.TradeDetailService;
import uts.sender.utils.Const;
import uts.sender.utils.FastJsonConvertUtil;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            Resp resp = (Resp)msg;
            String id = resp.getId();
            String tag = resp.getTag();
            String type = resp.getType();
            String responseCode = resp.getResponseCode();
            String responseMessage = resp.getResponseMessage();

            if(Const.TRADE_DETAIL.equals(tag)){
                TradeDetailService tradeDetailService = (TradeDetailService)ApplicationFactory.getBean("tradeDetailService");
                if(Const.RESPONSE_CODE_OK.equals(responseCode)){
                    if(Const.UPDATE.equals(type)){
                        System.err.println("-------------更新同步标志,id: " + id);
                        tradeDetailService.updateStatusById(id);
                    }
                }
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

}
