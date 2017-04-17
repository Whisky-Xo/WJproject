package com.aaread.protostuff;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * Protostuff 序列、反序列工具
 * @title ProtostuffCodec
 * @description TODO 
 * @author Clark
 * @date 2014年7月8日
 * @version 1.0
 */
public class ProtostuffCodec{
    public static final String NAME = "protostuff";
    static {
        System.setProperty("protostuff.runtime.collection_schema_on_repeated_fields", "true");
        System.setProperty("protostuff.runtime.morph_collection_interfaces", "true");
        System.setProperty("protostuff.runtime.morph_map_interfaces", "true");
    }
    private ThreadLocal<LinkedBuffer> linkedBuffer = new ThreadLocal<LinkedBuffer>() {
        @Override
        protected LinkedBuffer initialValue() {
            return LinkedBuffer.allocate(500);
        }
    };

    public <T> T decode(Class<T> clazz, byte[] bytes) throws Exception {
        Schema<T> schema = RuntimeSchema.getSchema(clazz);
        T content = clazz.newInstance();
        ProtostuffUtils.mergeFrom(bytes, content, schema);
        return content;
    }

	public <T> List<T> decodeList(Class<T> clazz, byte[] bytes)
			throws Exception {
		Schema<T> schema = RuntimeSchema.getSchema(clazz);
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		List<T> parsedList = ProtobufIOUtil.parseListFrom(in, schema);
		return parsedList;
	}

    public <T> byte[] encode(Class<T> clazz, T object) throws Exception {
        try {
            Schema<T> schema = RuntimeSchema.getSchema(clazz);
            byte[] protostuff = ProtostuffUtils.toByteArray(object, schema, linkedBuffer.get());
            return protostuff;
        } finally {
            linkedBuffer.get().clear();
        }
    }
    
    public <T> byte[] encodeList(Class<T> clazz, List<T> object) throws Exception {
    	try {
    		Schema<T> schema = RuntimeSchema.getSchema(clazz);
    		ByteArrayOutputStream out=new ByteArrayOutputStream();
    		ProtobufIOUtil.writeListTo(out,object,schema,linkedBuffer.get());
    		byte[] protostuff = out.toByteArray();
    		return protostuff;
    	} finally {
    		linkedBuffer.get().clear();
    	}
    }

}
