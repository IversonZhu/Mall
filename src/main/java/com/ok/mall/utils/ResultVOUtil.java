package com.ok.mall.utils;

import com.ok.mall.vo.ResultVO;

/**
 * @ClassName ResultVOUtil
 * @Description TODO
 * @Author Iverson
 * @Date 2018/6/17 0:00
 **/
public class ResultVOUtil {

    public static ResultVO success (Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;
    }

    public static ResultVO success () {
        return success(null);
    }

    public static ResultVO error (Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
