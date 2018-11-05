package Control;

import HTTPHeader.RequestHttp;
import HTTPHeader.ResponseHttp;

public class TestClass {
    public static void main(String[] args)
    {
        // How to make http response
        ResponseHttp res = new ResponseHttp(ResponseHttp.StatusCode.OK);
        System.out.println(res.GetMessage());

        // How to analyze http request
        RequestHttp req = new RequestHttp("GET /index/index.html HTTP/1.1\r\nHost: 127.0.0.1\r\nUser-Agent:Firefox/3.6.10\r\n\r\nAssdead");
        System.out.println(req.GetPathName());
    }
}
