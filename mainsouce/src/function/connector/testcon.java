package function.connector;

import function.pdfwriter.PDFWriter;
import gui.mainframe.MainFrameState;

import java.util.List;

public class testcon {
    public static void main(String[] args) {
        MainFrameState.civil.start();

        QueryRequest<Members> mem = new QueryRequest<>(
                "SELECT * FROM members WHERE MEMBER_ID like ?",
                "아이디 가져오기",
                "Members",
                "joyj",
                QueryType.SELECT,
                Members.class
        );

//        QueryRequest<Simple_doc> target = new QueryRequest<>(
//                "select * from simple_doc",
//                "findallSimple_doc",
//                "Simple_doc",
//                null,
//                QueryType.SELECT,
//                Simple_doc.class
//        );

        try {
            //query.putQuery(target);
            MainFrameState.civil.putQuery(mem);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Members mem1 = mem.getSingleResult();
        List<Members> member = mem.getResultList();
        //List<Simple_doc> data = target.getResultList();
//        for(Simple_doc s : data){
//            System.out.println(s);
//        }
//        for(Members m : member){
//            System.out.println(m);
//        }
        System.out.println(mem1.getMember_id());



        //new PDFWriter(1,"123456-1234567",query);

    }
}
