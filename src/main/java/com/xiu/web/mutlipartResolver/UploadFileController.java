package com.xiu.web.mutlipartResolver;

import com.xiu.dto.FileObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.StandardServletAsyncWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName UploadFileController
 * @Desc 文件上传请求接口
 * @Author xieqx
 * @Date 2020/11/29 9:46
 **/
@Controller
@RequestMapping(value = "/upload")
public class UploadFileController {
    //进入文件上传页面
    @GetMapping(value = "index")
    public String toUpload(HttpServletRequest request){
        System.out.println("跳转到文件上页面");
        //返回时String 类型的View 对象
        return "upload/index";
    }

    //从request中获取文件信息
    @RequestMapping(value = "uploadByRequest",method = RequestMethod.POST)
    @ResponseBody
    public String uploadByRequest(HttpServletRequest request){
        try {
            Collection<Part> parts = request.getParts();
            Iterator<Part> iterator = parts.iterator();
            for(;iterator.hasNext();){
                Part next = iterator.next();
                InputStream inputStream = next.getInputStream();
                //打印文件信息
                processFile(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "success";
    }

    //直接获取文件信息
    //从request中获取文件信息
    @RequestMapping(value = "uploadByMultiPartFile",method = RequestMethod.POST)
    @ResponseBody
    public String uploadByRequest(HttpServletRequest request,MultipartFile requestMultiPartFile){
        Map<String, MultipartFile> formMultipartFile = getFormMultipartFile(request);

        InputStream inputStream = null;
        try {
            inputStream = requestMultiPartFile.getInputStream();

            processFile(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }

    private void processFile(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        System.out.print("文件上传信息: ");
        while (inputStream.read(buffer) != -1) {
            //打印文件信息
            System.out.print(new String(buffer));
        }
    }

    //获取文件信息和其他上传字段
    //直接获取文件信息
    //从request中获取文件信息
    @RequestMapping(value = "uploadByFields",method = RequestMethod.POST)
    @ResponseBody
    public String uploadByFields(MultipartFile fileInfo,String fileName){
        InputStream inputStream = null;
        try {
            inputStream = fileInfo.getInputStream();
            System.out.println("文件名称："+fileName);
            processFile(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }
    //文件上传信息和其他字段信息在一个实体对象中
    //获取文件信息和其他上传字段
    //直接获取文件信息
    //从request中获取文件信息
    @RequestMapping(value = "uploadByObject",method = RequestMethod.POST)
    @ResponseBody
    public String uploadByObject(FileObject file){
        InputStream inputStream = null;
        try {
            inputStream = file.getFileInfo().getInputStream();
            System.out.println("文件名称："+file.getFileName());
            System.out.println("文件描述："+file.getFileDesc());
            processFile(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }


    /**
     * 获取表单上传的文件
     * 配置文件中如果去除了springboot项目默认的多部件解析器 依赖commons-fileUpload包
     * 使用springMvc中的CommonsMultipartResolver进行解析
     * 将传递HttpServletRequest或者ShiroHttpServletRequest解析成为MultipartHttpServletRequest并返回表单中所有的MultipartFile
     * @Author 李洪斌
     * @Description //TODO
     * @Date 2019/7/23 19:09
     * @Param
     * @return
     **/
    public Map<String, MultipartFile> getFormMultipartFile(HttpServletRequest request) {
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        //检查form中是否有enctype="multipart/form-data"
        if (resolver.isMultipart(request)) {
            return multipartRequest.getFileMap();
        }
        return null;
    }

}
