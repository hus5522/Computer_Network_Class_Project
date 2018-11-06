package Control;

import Former.HttpFormer;
import HTTPHeader.*;
import Helper.LocalFileReader;

import java.io.IOException;

public class TestClass {
    public static void main(String[] args)
    {
        CloudServerManager server = CloudServerManager.getInstance();
        server.OperateServer();

    }
}
