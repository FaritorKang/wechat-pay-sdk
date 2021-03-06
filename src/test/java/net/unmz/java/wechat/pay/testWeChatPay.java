package net.unmz.java.wechat.pay;

import net.unmz.java.util.code.StrCodeUtils;
import net.unmz.java.util.json.JsonUtils;
import net.unmz.java.wechat.pay.dto.request.*;
import net.unmz.java.wechat.pay.dto.response.*;
import net.unmz.java.wechat.pay.exception.WeChatException;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付接口单元测试
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-4-1 12:30
 * @since JDK 1.8
 */

public class testWeChatPay {

    /**
     *
     * 以下3个属性可以在静态方法块中完成，随着类的加载进行初始值，不用每次使用前加载
     *
     * 而WeChatPay.setUseCert(true);是用来判断是否使用证书，需要在每次使用前进行赋值
     */
    static {
        WeChatPay.setAppKey("商户密钥");//可在每次调用前定义,也可以在处理微信支付类静态代码块中赋初始值
        WeChatPay.setSslCertPassword("商户Id");
        WeChatPay.setSslCertPath("证书路径");
    }

    @Test
    public void testUnifiedOrder() {
        UnifiedOrderRequestDto dto = new UnifiedOrderRequestDto();
        dto.setAppid("AppId");
        dto.setMch_id("商户号");
        dto.setBody("123");
        dto.setNonce_str(StrCodeUtils.getStrCode(16));
        dto.setNotify_url("123");
        dto.setOut_trade_no("111");
        dto.setTrade_type("JSAPI");
        dto.setTotal_fee("123");
        dto.setSpbill_create_ip("192.168.1.1");
        dto.setOpenid("OpenId");

//        dto.setSub_appid("特约商户APPID");
//        dto.setSub_mch_id("特约商户号");
//        dto.setSub_openid("特约商户OpenId");
//        WeChatPay.setAppKey("商户密钥");//当选择服务商模式时,此处的appKey选用服务商统一秘钥

        WeChatPay client = new WeChatUnifiedOrder();
        try {
            UnifiedOrderResponseDto responseDto = (UnifiedOrderResponseDto) client.execute(dto);
            System.out.println(responseDto.getPrepay_id());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testOrderQuery() {
        OrderQueryRequestDto dto = new OrderQueryRequestDto();
        dto.setAppid("AppId");
        dto.setMch_id("商户号");
        dto.setNonce_str(StrCodeUtils.getStrCode(16));
        dto.setOut_trade_no("111");

//        dto.setSub_appid("特约商户APPID");
//        dto.setSub_mch_id("特约商户号");
//        WeChatPay.setAppKey("商户密钥");//当选择服务商模式时,此处的appKey选用服务商统一秘钥

        try {
            WeChatPay client = new WeChatOrderQuery();
            OrderQueryResponseDto responseDto = (OrderQueryResponseDto) client.execute(dto);
            System.out.println(responseDto.getTrade_state_desc());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCloseOrder() {
        CloseOrderRequestDto dto = new CloseOrderRequestDto();
        dto.setAppid("AppId");
        dto.setMch_id("商户号");
        dto.setNonce_str(StrCodeUtils.getStrCode(16));
        dto.setOut_trade_no("1231");

//        dto.setSub_appid("特约商户APPID");
//        dto.setSub_mch_id("特约商户号");
//        WeChatPay.setAppKey("商户密钥");//当选择服务商模式时,此处的appKey选用服务商统一秘钥

        try {
            WeChatPay client = new WeChatCloseOrder();
            CloseOrderResponseDto responseDto = (CloseOrderResponseDto) client.execute(dto);
            System.out.println(responseDto.getReturn_msg());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCallback() throws WeChatException {
        HttpServletRequest request = null;

        WeChatCallBackDto dto = WeChatCallBack.callBack(request);
        String result = dto.getResult_wecaht_message();//用于响应给微信,告知微信成功或者失败

        if ("success".equalsIgnoreCase(dto.getReturn_code())) {
            //成功后的业务逻辑
        }
    }


    @Test
    public void testRefundQuery() {
        RefundQueryRequestDto dto = new RefundQueryRequestDto();
        dto.setAppid("AppId");
        dto.setMch_id("商户号");
        dto.setNonce_str(StrCodeUtils.getStrCode(16));
        dto.setOut_trade_no("1231");

//        dto.setSub_appid("特约商户APPID");
//        dto.setSub_mch_id("特约商户号");
//        WeChatPay.setAppKey("商户密钥");//当选择服务商模式时,此处的appKey选用服务商统一秘钥

        try {
            WeChatPay client = new WeChatRefundQuery();
            Map<String, String> map = client.executeRespMap(dto);
//            RefundQueryResponseDto responseDto = (RefundQueryResponseDto) client.execute(dto);//该方法动态参数解析还有部分问题,建议先用map接收
//            System.out.println(responseDto.getReturn_msg());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testRefundAble() {
        RefundRequestDto dto = new RefundRequestDto();
        dto.setAppid("AppId");
        dto.setMch_id("商户号");
        dto.setNonce_str(StrCodeUtils.getStrCode(16));
        dto.setOut_trade_no("1231");

//        dto.setSub_appid("特约商户APPID");
//        dto.setSub_mch_id("特约商户号");
//        WeChatPay.setAppKey("商户密钥");//当选择服务商模式时,此处的appKey选用服务商统一秘钥

        try {
            WeChatPay client = new WeChatRefundAble();
            WeChatPay.setUseCert(true);//是否使用证书
            Map<String, String> map = client.executeRespMap(dto);
//            RefundResponseDto responseDto = (RefundResponseDto) client.execute(dto);//该方法动态参数解析还有部分问题,建议先用map接收
//            System.out.println(responseDto.getReturn_msg());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void testRefundOrder(HttpServletRequest request) {
        try {
            WeChatRefundCallBackDto dto = WeChatRefundCallBack.callBack(request);
            System.out.println(JsonUtils.toJSON(dto));
        } catch (WeChatException e) {
            e.printStackTrace();
        }
    }

}
