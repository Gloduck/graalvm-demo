package cn.gloduck;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;

import java.io.IOException;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author Gloduck
 */
public class NativeStarter {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = NativeStarter.class.getResourceAsStream("config.json");
        String json = IoUtil.read(inputStream, Charset.defaultCharset());
        System.out.println(json);
    }
}
