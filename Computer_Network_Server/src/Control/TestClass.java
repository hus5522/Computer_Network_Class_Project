package Control;

import Former.HttpFormer;
import HTTPHeader.*;
import Helper.FileReader;

public class TestClass {
    public static void main(String[] args)
    {
        // How to make http response
        ResponseHttp res = new ResponseHttp(ResponseHttp.StatusCode.OK);
        HttpFormer httpformer = new HttpFormer(res);

        AcceptRangeResponseHeader rangeHeader = new AcceptRangeResponseHeader(AcceptRangeResponseHeader.RangeType.bytes);
        httpformer.AddHeaderField(rangeHeader);

        KeepAliveResponseHeader keepAliveHeader = new KeepAliveResponseHeader(5, 100);
        httpformer.AddHeaderField(keepAliveHeader);

        ContentTypeResponseHeader contentTypeHeader = new ContentTypeResponseHeader(ContentTypeResponseHeader.ContentType.text_html, ContentTypeResponseHeader.CharType.utf_8);
        httpformer.AddHeaderField(contentTypeHeader);
        System.out.println(httpformer.toString());

        // How to analyze http request
        RequestHttp req = new RequestHttp("GET /index/index.html HTTP/1.1\r\nHost: 127.0.0.1\r\nUser-Agent:Firefox/3.6.10\r\n\r\nAssdead");
        System.out.println(req.GetPathName());


        FileReader fileReader = new FileReader("C:\\Users\\heoyouseong\\Desktop\\까치");

        for(String data : fileReader.GetLastModifiedDate()) {
            System.out.println(data);
        }

    }
}
