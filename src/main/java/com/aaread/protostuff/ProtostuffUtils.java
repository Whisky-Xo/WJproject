package com.aaread.protostuff;

import java.io.IOException;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;

/**
 * Protostuff 序列、反序列工具
 * @title ProtostuffUtils
 * @description TODO 
 * @author Clark
 * @date 2014年7月8日
 * @version 1.0
 */
public class ProtostuffUtils {

    public static <T> byte[] toByteArray(T message, Schema<T> schema, LinkedBuffer buffer) {
        return ProtobufIOUtil.toByteArray(message, schema, buffer);
    }

    public static <T> void mergeFrom(byte[] in, T message, Schema<T> schema) throws IOException {
        ProtobufIOUtil.mergeFrom(in, message, schema);
    }
}
