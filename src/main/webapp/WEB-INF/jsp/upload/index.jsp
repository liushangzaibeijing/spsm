<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>

<body>
<h2>文件上传</h2>
<form action="uploadByRequest" enctype="multipart/form-data" method="POST">
    上传文件（从request中获取文件信息）: <input type="file" name="requestFile" /><input
        type="submit" value="提交" />
</form>

<form action="uploadByMultiPartFile" enctype="multipart/form-data" method="POST">
    上传文件（MultiPartFile）: <input type="file" name="requestMultiPartFile" /> <input
        type="submit" value="提交" />
</form>
<form action="uploadByFields" enctype="multipart/form-data" method="POST">
    上传文件（多字段）: <input type="file" name="fileInfo" />
    文件名: <input type="text" name="fileName" />
    <input type="submit" value="提交" />
</form>
<form action="uploadByObject" enctype="multipart/form-data" method="POST">
    上传文件（bean对象）: <input type="file" name="fileInfo" />
    文件名: <input type="text" name="fileName" />
    文件描述: <input type="text" name="fileDesc" />
    <input type="submit" value="提交" />
</form>


</body>
</html>
