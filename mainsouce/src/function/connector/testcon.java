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

        Members newmember = new Members();
        Members updatemem = new Members();
        newmember.setMember_id("afasdf");


        con.insert(newmember);
        con.update(updatemem);
        // select * from District;
        List<District> aldis = con.selectAll(District.class);

        Sinmungo s = con.find(Sinmungo.class,51);

        for(District d : aldis){
            System.out.println(d);
        }


        Simple_doc m = con.find(Simple_doc.class,2);

        System.out.println(m);
    }
}
