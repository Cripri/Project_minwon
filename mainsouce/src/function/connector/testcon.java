package function.connector;

import function.pdfwriter.PDFWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static gui.mainframe.MainFrameState.civil;


public class testcon {
    public static void main(String[] args){
        Civil_Connector con = new Civil_Connector();
        con.start();

        Simple_doc m = con.find(Simple_doc.class,2);

        System.out.println(m);
    }
}
