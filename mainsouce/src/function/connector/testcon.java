package function.connector;

import function.pdfwriter.PDFWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class testcon {
    public static void main(String[] args){
        Civil_Connector con = new Civil_Connector();
        con.start();

        QueryRequest<Simple_doc> doc = new QueryRequest<>(
                "select * from simple_doc where simple_doc_code like ?",
                2,
                Simple_doc.class,
                con
        );


        Simple_doc data = doc.getSingleResult();

        System.out.println(data);

        new PDFWriter(3,"123456-1234567",con);

    }
}
