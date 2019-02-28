package com.cht.testspringboot.configuration;


import com.cht.testspringboot.bean.TestConverterObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;

import static org.springframework.http.converter.FormHttpMessageConverter.DEFAULT_CHARSET;

/**
 * @auther chen.haitao
 * @date 2019-02-28
 */
public class MyHttpMSGConverter extends AbstractHttpMessageConverter<TestConverterObj> {


    public MyHttpMSGConverter(){
        super(new MediaType("application","x-cht",DEFAULT_CHARSET));
    }


    @Override
    protected boolean supports(Class aClass) {
        System.out.println("判断是否符合AbstractHttpMessageConverter");
        if(TestConverterObj.class.isAssignableFrom(aClass)){
            return true;
        }
        return false;
    }

    @Override
    protected TestConverterObj readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), DEFAULT_CHARSET);
        String[] tempArr = temp.split("-");
        return new TestConverterObj(new Integer(tempArr[0]), tempArr[1]);
    }

    @Override
    protected void writeInternal(TestConverterObj o, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello: " + o.getId() + "-" + o.getName();
        StreamUtils.copy(out, DEFAULT_CHARSET, httpOutputMessage.getBody());
    }
}
