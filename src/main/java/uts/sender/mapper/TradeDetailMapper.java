package uts.sender.mapper;

import uts.sender.config.database.BaseMapper;
import uts.sender.entity.TradeDetail;

public interface TradeDetailMapper{
    int deleteByPrimaryKey(String id);

    int insert(TradeDetail record);

    int insertSelective(TradeDetail record);

    TradeDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TradeDetail record);

    int updateByPrimaryKey(TradeDetail record);
}
