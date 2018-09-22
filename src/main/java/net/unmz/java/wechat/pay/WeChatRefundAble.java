package net.unmz.java.wechat.pay;

import net.unmz.java.util.json.JsonUtils;
import net.unmz.java.util.xml.XmlUtils;
import net.unmz.java.wechat.pay.constants.WeChatResponseCodeEnum;
import net.unmz.java.wechat.pay.constants.WeChatURLEnum;
import net.unmz.java.wechat.pay.dto.BaseRequestDto;
import net.unmz.java.wechat.pay.dto.BaseResponseDto;
import net.unmz.java.wechat.pay.dto.request.RefundRequestDto;
import net.unmz.java.wechat.pay.dto.response.RefundResponseDto;
import net.unmz.java.wechat.pay.exception.WeChatException;
import org.apache.commons.lang3.StringUtils;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付申请退款接口
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018/4/2 12:15
 * @since JDK 1.8
 */
public class WeChatRefundAble extends WeChatPay {

    @Override
    public BaseResponseDto execute(BaseRequestDto dto) throws Exception {
        String result = doPostWeChetRequest(dto, WeChatURLEnum.REFUNDABLE.getUrl());
        RefundResponseDto responseDto = JsonUtils.toBean(XmlUtils.toString(result, "utf-8"), RefundResponseDto.class);
        System.out.println("WeChat return message : " + JsonUtils.toJSON(responseDto));
        if (WeChatResponseCodeEnum.SUCCESS.getCode().equals(responseDto.getResult_code())
                && WeChatResponseCodeEnum.SUCCESS.getCode().equals(responseDto.getReturn_code()))
            return responseDto;
        else if (StringUtils.isNotBlank(responseDto.getErr_code()))
            throw new WeChatException(responseDto.getErr_code_des());
        throw new WeChatException(responseDto.getReturn_msg());
    }

    /**
     * 校验必填请求参数
     *
     * @param dto 申请退款实体
     */
    @Override
    protected void validateParams(BaseRequestDto dto) {
        super.validateParams(dto);
        RefundRequestDto requestDto = (RefundRequestDto) dto;
        if (StringUtils.isBlank(requestDto.getTransaction_id()))
            throw new IllegalArgumentException("WeChat Request params transaction_id is null");
        if (requestDto.getTransaction_id().length() > 32)
            throw new IllegalArgumentException("WeChat Request params transaction_id is too long");

        if (StringUtils.isBlank(requestDto.getOut_trade_no()))
            throw new IllegalArgumentException("WeChat Request params out_trade_no is null");
        if (requestDto.getOut_trade_no().length() > 32)
            throw new IllegalArgumentException("WeChat Request params out_trade_no is too long");

        if (StringUtils.isBlank(requestDto.getOut_refund_no()))
            throw new IllegalArgumentException("WeChat Request params out_refund_no is null");
        if (requestDto.getOut_refund_no().length() > 64)
            throw new IllegalArgumentException("WeChat Request params out_refund_no is too long");

        if(StringUtils.isBlank(requestDto.getTotal_fee()))
            throw new IllegalArgumentException("WeChat Request params total_fee is null");
        if(StringUtils.isBlank(requestDto.getRefund_fee()))
            throw new IllegalArgumentException("WeChat Request params refund_fee is null");
    }
}