package uts.sender.service;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uts.sender.entity.TradeDetail;
import uts.sender.mapper.TradeDetailMapper;
import uts.sender.netty.NettyClient;
import uts.sender.protocol.Req;
import uts.sender.utils.Const;

@Service
public class TradeDetailService {
    @Autowired
    private TradeDetailMapper tradeDetailMapper;

    @Transactional
    public void sender(TradeDetail td){
        //1 insert
        tradeDetailMapper.insert(td);
        //2 transfer
        ChannelFuture cf = NettyClient.getInstance().getChannelFuture();
        Req req = new Req();
        req.setId(td.getId());
        req.setRequestMessage(JSONObject.toJSONString(td));
        req.setTag(Const.TRADE_DETAIL);
        req.setType(Const.SAVE);

        cf.channel().writeAndFlush(req);
    }

    public int updateStatusById(String id){
        TradeDetail td = tradeDetailMapper.selectByPrimaryKey(id);
        td.setStatus("1");
        if (td != null) {
            return tradeDetailMapper.updateByPrimaryKey(td);
        }
        return 0;
    }
}
